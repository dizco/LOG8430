package demo.breakthrough;

import java.awt.Point;

import minidraw.boardgame.PositioningStrategy;

/**
 * The strategy for positioning chess pawns properly in the center of a square
 * on the chess board. 
 *  */
public class ChessBoardPositioningStrategy
    implements PositioningStrategy<Position> {

  @Override
  public Point calculateFigureCoordinatesIndexedForLocation(Position location,
      int index) {
    // ignore index, there is only one piece at a time on any square
    return new Point(
        location.c * Constants.SQUARE_SIZE + Constants.SQUARE_OFFSET_X,
        location.r * Constants.SQUARE_SIZE + Constants.SQUARE_OFFSET_Y);
  }

  @Override
  public Point calculateFigureCoordinatesForProps(String keyOfProp) {
    // no props are used.
    return null;
  }

}
