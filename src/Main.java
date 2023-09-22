import BoardGame.Board;
import BoardGame.Position;
import Chess.ChesMatch;
import app.UI;

public class Main {
    public static void main(String[] args) {
        ChesMatch chesMatch = new ChesMatch();
        UI.printBoard(chesMatch.getPieces());
    }
}
