package App;

import Chess.ChesMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import Chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner scanner) {
        try {
            String string = scanner.nextLine();
            char column = string.charAt(0);
            int row = Integer.parseInt(string.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException exception) {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8");
        }
    }

    public static void printMatch(ChesMatch chesMatch, List<ChessPiece> captured) {
        printBoard(chesMatch.getPieces());
        System.out.println();

        printCapturedPieces(captured);
        System.out.println();

        System.out.println("Turn: " + chesMatch.getTurn());
        gameStatus(chesMatch);
    }

    private static void gameStatus(ChesMatch chesMatch) {
        if (!chesMatch.getCheckMate()) {
            System.out.println("Waiting player: " + chesMatch.getCurrentPlayer());
            checkStatus(chesMatch);
            return;
        }

        System.out.println("CHECKMATE!");
        System.out.println("Winner: " + chesMatch.getCurrentPlayer());
    }

    private static void checkStatus(ChesMatch chesMatch) {
        if (chesMatch.getCheck()) {
            System.out.println("CHECK!");
        }
    }

    public static void printBoard(ChessPiece[][] pieces) {
        printChessBoard(pieces, null);
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        printChessBoard(pieces, possibleMoves);
    }

    private static void printChessBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                boolean isMovable = possibleMoves != null && possibleMoves[i][j];
                printPiece(pieces[i][j], isMovable);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }

        String line = "-" + ANSI_RESET;
        String white = ANSI_WHITE + piece + ANSI_RESET;
        String black = ANSI_YELLOW + piece + ANSI_RESET;
        System.out.print((piece == null) ? line : (piece.getColor() == Color.WHITE) ? white : black);

        System.out.print(" ");
    }

    private static void printCapturedPieces(List<ChessPiece> captured) {
        List<ChessPiece> white = captured.stream().filter(piece -> piece.getColor() == Color.WHITE).toList();
        List<ChessPiece> black = captured.stream().filter(piece -> piece.getColor() == Color.BLACK).toList();
        System.out.println("Captured pieces: ");

        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.print(Arrays.toString(white.toArray()));
        System.out.println(ANSI_RESET);

        System.out.print("Black: ");
        System.out.print(ANSI_YELLOW);
        System.out.print(Arrays.toString(black.toArray()));
        System.out.println(ANSI_RESET);
    }
}
