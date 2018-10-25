package demo.visual;

import java.awt.Point;

import javax.swing.*;

import minidraw.framework.*;
import minidraw.standard.*;

/**
 * Demonstrate multiple views.
 * 
 */
public class MultiView {

  public static void main(String[] args) {
    Factory f = new ChessBackgroundFactory();
    DrawingEditor editor = new MiniDrawApplication("Multi view", f);
    editor.open();

    Figure blackKing = new ImageFigure("bking",
        new Point(14 + 3 * 40, 14 + 0 * 40));
    editor.drawing().add(blackKing);

    Figure whiteKing = new ImageFigure("wking",
        new Point(14 + 3 * 40, 14 + 7 * 40));
    editor.drawing().add(whiteKing);

    editor.setTool(new SelectionTool(editor));

    // create second view
    JFrame newWindow = new JFrame("Extra View");
    JFrame.setDefaultLookAndFeelDecorated(true);
    newWindow.setLocation(620, 20);
    newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    DrawingView extraView = f.createDrawingView(editor);
    JPanel panel = (JPanel) extraView;
    newWindow.getContentPane().add(panel);
    newWindow.pack();
    newWindow.setVisible(true);
  }
}
