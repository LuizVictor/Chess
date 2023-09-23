import Chess.ChesMatch;
import App.UI;
import Chess.ChessPiece;
import Chess.ChessPosition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChesMatch chesMatch = new ChesMatch();
        while (true) {
            UI.printBoard(chesMatch.getPieces());
            System.out.println();

            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(scanner);
            System.out.print("Target: ");
            ChessPosition target = UI.readChessPosition(scanner);

            ChessPiece capturedPiece = chesMatch.performChessMove(source, target);
        }
    }
}
