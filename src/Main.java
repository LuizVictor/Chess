import Chess.ChesMatch;
import App.UI;

public class Main {
    public static void main(String[] args) {
        ChesMatch chesMatch = new ChesMatch();
        UI.printBoard(chesMatch.getPieces());
    }
}
