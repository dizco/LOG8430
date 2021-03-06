package demo.visual;

import minidraw.framework.Drawing.DrawingEditor;
import minidraw.framework.Drawing.MiniDrawApplication;

/*
 * Basic demo of opening window with a static image background.

 */

public class ShowBackground {

  public static void main(String[] args) {
    DrawingEditor window = new MiniDrawApplication(
        "Static background image load testing", new ChessBackgroundFactory());
    window.open();
  }
}
