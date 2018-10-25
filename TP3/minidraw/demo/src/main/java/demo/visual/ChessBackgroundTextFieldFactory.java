package demo.visual;

import javax.swing.JTextField;

import minidraw.framework.DrawingEditor;

/**
 * A factory that creates the status line as well.
 * 
 */
class ChessBackgroundTextFieldFactory extends ChessBackgroundFactory {

  @Override
  public JTextField createStatusField(DrawingEditor editor) {
    return new JTextField("Hola...");
  }
}