package demo.visual;

import java.awt.Point;

import minidraw.framework.Drawing.DrawingEditor;
import minidraw.framework.Drawing.ImageFigure;
import minidraw.framework.Drawing.MiniDrawApplication;
import minidraw.framework.Drawing.SelectionTool;
import minidraw.framework.Figure.CompositeFigure;
import minidraw.framework.Figure.Figure;
import minidraw.framework.Figure.GroupFigure;

/**
 * Demonstrate putting several figures into a composite figure.
 * 
 */
public class ShowCompositeFigure {

  public static void main(String[] args) {
    DrawingEditor window = new MiniDrawApplication(
        "CompositeFigure test: moves as a unit", new ChessBackgroundFactory());
    window.open();

    Figure blackKing = new ImageFigure("bking",
        new Point(14 + 3 * 40, 14 + 0 * 40));
    Figure blackPawn = new ImageFigure("bpawn",
        new Point(14 + 4 * 40, 14 + 1 * 40));

    CompositeFigure composite = new GroupFigure();

    composite.add(blackKing);
    composite.add(blackPawn);

    window.setTool(new SelectionTool(window));

    window.drawing().add(composite);
  }
}
