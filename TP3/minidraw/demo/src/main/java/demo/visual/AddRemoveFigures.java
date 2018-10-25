package demo.visual;

import java.awt.Point;
import java.awt.event.MouseEvent;

import minidraw.framework.*;
import minidraw.standard.*;

/**
 * Demonstrates how figures can be added and removed from the drawing and
 * demonstrates how a tool can be implemented for driving a test.
 * 
 */
public class AddRemoveFigures {

  public static void main(String[] args) {
    DrawingEditor window = new MiniDrawApplication(
        "Add + Remove figures: Click for action", new ChessBackgroundFactory());
    window.open();

    window.setTool(new AddRemoveTool(window));
  }
}

class AddRemoveTool extends NullTool {
  private DrawingEditor editor;
  Figure[] list;

  public AddRemoveTool(DrawingEditor e) {
    editor = e;
    list = new Figure[6];
  }

  private int tick = 0;

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    System.out.println("MD: " + tick);
    if (tick < 6) {
      list[tick] = new ImageFigure("bpawn",
          new Point(14 + tick * 40, 14 + tick * 40));
      editor.drawing().add(list[tick]);
    } else {
      editor.drawing().remove(list[tick - 6]);
    }
    tick++;
    if (tick == 12) {
      tick = 0;
    }
  }
}
