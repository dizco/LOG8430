package demo.visual;

import javax.swing.JTextField;

import minidraw.framework.*;
import minidraw.standard.*;

/**
 * A factory just for testing purposes. Defines a chess board background. 

 */
class ChessBackgroundFactory implements Factory {
  @Override
  public DrawingView createDrawingView(DrawingEditor editor) {
    DrawingView view = new StdViewWithBackground(editor, "chessboard");
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
