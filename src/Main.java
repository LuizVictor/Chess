import Chess.ChesMatch;
import App.UI;
import Chess.ChessException;
import Chess.ChessPiece;
import Chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChesMatch chesMatch = new ChesMatch();

        while (true) {
            try {
                UI.clearScreen();
                UI.printMatch(chesMatch);
                System.out.println();

                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(scanner);

                boolean[][] possibleMoves = chesMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chesMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(scanner);

                ChessPiece capturedPiece = chesMatch.performChessMove(source, target);
            } catch (ChessException | InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }
    }
}
