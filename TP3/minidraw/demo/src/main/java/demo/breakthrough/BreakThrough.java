package demo.breakthrough;

import javax.swing.JTextField;

import minidraw.boardgame.*;
import minidraw.framework.*;
import minidraw.standard.*;

/**
 * Experimental stuff. Testing the 'boardgame' package within Minidraw.
 * Boardgame uses a number of design patterns to allow quick development of user
 * interfaces for board games.
 *
 * Note: No real breakthrough domain code is implemented, only stub code to test
 * the visual code. 
 */
public class BreakThrough {

  public static void main(String[] args) {
    Game game = new GameStub();
    DrawingEditor window = new MiniDrawApplication(
        "Breakthrough Demo: (0,0) illegal", new BreakthroughFactory(game));
    window.open();

    ((GameStub) game).addObserver((BoardDrawing<Position>) window.drawing());
    window.setTool(new BoardActionTool(window));
  }
}

interface Game {
  public static final int WHITE = +1;
  public static final int NONE = 0;
  public static final int BLACK = -1;

  public boolean move(Position from, Position to);

  public int get(Position p);
}

class GameStub implements Game {
  int[][] board = new int[8][8];

  public GameStub() {
    setAllInRowTo(0, BLACK);
    setAllInRowTo(1, BLACK);
    setAllInRowTo(6, WHITE);
    setAllInRowTo(7, WHITE);
  }

  @Override
  public int get(Position p) {
    return board[p.r][p.c];
  }

  private void setAllInRowTo(int row, int value) {
    for (int column = 0; column < 8; column++) {
      board[row][column] = value;
    }
  }

  @Override
  public boolean move(Position from, Position to) {
    System.out
        .println("GameStub: moving in domain code: " + from + " -> " + to);
    if (to.r == 0 && to.c == 0) {
      return false;
    }
    observer.pieceMovedEvent(from, to);
    return true;
  }

  private BoardGameObserver<Position> observer;

  public void addObserver(BoardGameObserver<Position> observer) {
    this.observer = observer;
  }
}

class BreakthroughFactory implements Factory {
  private Game game;

  public BreakthroughFactory(Game game) {
    super();
    this.game = game;
  }

  @Override
  public Drawing createDrawing(DrawingEditor editor) {
    return new BoardDrawing<Position>(new BreakthroughPieceFactory(game),
        new ChessBoardPositioningStrategy(),
        null /* no props in breakthrough */ );
  }

  @Override
  public DrawingView createDrawingView(DrawingEditor editor) {
    return new StdViewWithBackground(editor, "chessboard");
  }

  @Override
  public JTextField createStatusField(DrawingEditor arg0) {
    return null;
  }
}