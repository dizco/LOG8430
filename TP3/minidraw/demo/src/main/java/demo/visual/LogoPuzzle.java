package minidraw.visual;

import java.awt.Point;

import javax.swing.JTextField;

import minidraw.framework.*;
import minidraw.standard.*;

/**
 * A (very small) jigsaw puzzle on the Aarhus University Logo. A demonstration
 * of a "minimal" MiniDraw application.
 * 
 */
public class LogoPuzzle {

  public static void main(String[] args) {
    DrawingEditor editor = new MiniDrawApplication("Put the pieces into place",
        new PuzzleFactory());
    editor.open();
    editor.setTool(new SelectionTool(editor));

    Drawing drawing = editor.drawing();
    drawing.add(new ImageFigure("11", new Point(5, 5)));
    drawing.add(new ImageFigure("12", new Point(10, 10)));
    drawing.add(new ImageFigure("13", new Point(15, 15)));
    drawing.add(new ImageFigure("21", new Point(20, 20)));
    drawing.add(new ImageFigure("22", new Point(25, 25)));
    drawing.add(new ImageFigure("23", new Point(30, 30)));
    drawing.add(new ImageFigure("31", new Point(35, 35)));
    drawing.add(new ImageFigure("32", new Point(40, 40)));
    drawing.add(new ImageFigure("33", new Point(45, 45)));
  }
}

class PuzzleFactory implements Factory {

  @Override
  public DrawingView createDrawingView(DrawingEditor editor) {
    DrawingView view = new StdViewWithBackground(editor, "au-logo");
    return view;
  }

  @Override
  public Drawing createDrawing(DrawingEditor editor) {
    return new StandardDrawing();
  }

  @Override
  public JTextField createStatusField(DrawingEditor editor) {
    return null;
  }
}
