package minidraw.visual;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import minidraw.framework.*;
import minidraw.standard.*;

/**
 * Demonstrate MiniDraw ability to: A) Handle multiple windows B) Define new
 * Figure instances (green rectangles) C) Define a new tool to create rectangles
 * D) Switching tools at run-time E) Showing text in the status window
 * 
 */
public class ShowRectangle {

  public static void main(String[] args) {
    Factory f = new EmptyCanvasFactory();
    DrawingEditor window = new MiniDrawApplication(
        "Create and move rectangles - use the mouse", f);
    Tool rectangleDrawTool = new RectangleTool(window),
        selectionTool = new SelectionTool(window);
    addToolSelectMenusToWindow(window, rectangleDrawTool, selectionTool);
    window.open();

    window.setTool(new RectangleTool(window));

    // create second view
    JFrame newWindow = new JFrame("Extra View");
    newWindow.setLocation(620, 20);
    newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    DrawingView extraView = f.createDrawingView(window);
    JPanel panel = (JPanel) extraView;
    newWindow.getContentPane().add(panel);
    newWindow.pack();
    newWindow.setVisible(true);
  }

  public static void addToolSelectMenusToWindow(final DrawingEditor window,
      final Tool rectangleTool, final Tool selectionTool) {
    // Here I use the knowledge that 'window' is a MiniDrawApplication
    JFrame frame = (JFrame) window;

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;

    menuBar = new JMenuBar();
    menu = new JMenu("Tool");
    menuBar.add(menu);

    menuItem = new JMenuItem("Rectangle");
    menuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        window.setTool(rectangleTool);
        window.showStatus("Rectangle tool active.");
      }
    });
    menu.add(menuItem);

    menuItem = new JMenuItem("Selection");
    menuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        window.setTool(selectionTool);
        window.showStatus("Selection tool active.");
      }
    });
    menu.add(menuItem);

    frame.setJMenuBar(menuBar);
  }
}

class EmptyCanvasFactory implements Factory {

  @Override
  public DrawingView createDrawingView(DrawingEditor editor) {
    return new StandardDrawingView(editor, new Dimension(400, 200));
  }

  @Override
  public Drawing createDrawing(DrawingEditor editor) {
    return new StandardDrawing();
  }

  @Override
  public JTextField createStatusField(DrawingEditor editor) {
    return new JTextField();
  }
}

/** A rectangle figure */
class RectangleFigure extends AbstractFigure {

  private Rectangle fDisplayBox;

  public RectangleFigure(Point origin, Point corner) {
    basicResize(origin, corner);
  }

  public RectangleFigure(Point origin) {
    basicResize(origin, origin);
  }

  protected void basicResize(Point origin, Point corner) {
    fDisplayBox = new Rectangle(origin);
    fDisplayBox.add(corner);
  }

  @Override
  public Rectangle displayBox() {
    return new Rectangle(fDisplayBox.x, fDisplayBox.y, fDisplayBox.width,
        fDisplayBox.height);
  }

  @Override
  protected void basicMoveBy(int x, int y) {
    fDisplayBox.translate(x, y);
  }

  protected void resize(Point origin, Point corner) {
    willChange();
    basicResize(origin, corner);
    changed();
  }

  @Override
  public void draw(Graphics g) {
    Rectangle r = displayBox();
    g.setColor(Color.GREEN);
    g.fillRect(r.x, r.y, r.width, r.height);
    g.setColor(Color.BLACK);
    g.drawRect(r.x, r.y, r.width - 1, r.height - 1);
  }

}

/** A tool to create rectangles */
class RectangleTool extends AbstractTool {
  private RectangleFigure f;

  public RectangleTool(DrawingEditor editor) {
    super(editor);
    f = null;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    super.mouseDown(e, x, y);
    f = new RectangleFigure(new Point(x, y));
    editor.drawing().add(f);
  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y) {
    f.resize(new Point(fAnchorX, fAnchorY), new Point(x, y));
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    if (f.displayBox().isEmpty()) {
      editor.drawing().remove(f);
    }
    f = null;
  }
}
