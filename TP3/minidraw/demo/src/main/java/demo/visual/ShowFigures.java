package demo.visual;

import java.awt.Point;

import minidraw.framework.*;
import minidraw.standard.*;

/**
 * Test that figures can be added to the drawing and that the selection tool
 * works. Test that selection tool can move individual figures as well as select
 * a set of figures for composite movement.
 * 
 */
public class ShowFigures {

  public static void main(String[] args) {
    DrawingEditor editor = new MiniDrawApplication(
        "Test: Figures appear; select tool works", new ChessBackgroundFactory());
    editor.open();
    Figure blackKing = new ImageFigure("bking",
        new Point(14 + 3 * 40, 14 + 0 * 40));
    editor.drawing().add(blackKing);

    Figure whiteKing = new ImageFigure("wking",
        new Point(14 + 3 * 40, 14 + 7 * 40));
    editor.drawing().add(whiteKing);

    Figure blackPawn = new ImageFigure("bpawn",
        new Point(14 + 4 * 40, 14 + 1 * 40));
    editor.drawing().add(blackPawn);

    Figure whitePawn = new ImageFigure("wpawn",
        new Point(14 + 4 * 40, 14 + 6 * 40));
    editor.drawing().add(whitePawn);

    editor.setTool(new SelectionTool(editor));

  }
}
