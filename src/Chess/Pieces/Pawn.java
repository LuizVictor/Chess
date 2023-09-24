package Chess.Pieces;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChesMatch;
import Chess.ChessPiece;
import Chess.Color;

public class Pawn extends ChessPiece {
    private ChesMatch chesMatch;

    public Pawn(Board board, Color color, ChesMatch chesMatch) {
        super(board, color);
        this.chesMatch = chesMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        int currentColor = (getColor() == Color.WHITE) ? -1 : 1;
        Position currentPosition = new Position(position.getRow(), position.getColumn());

        moveOnePosition(matrix, currentPosition, currentColor);

        if (getMoveCount() == 0) {
            Position doublePosition = new Position(position.getRow() + 2 * currentColor, position.getColumn());
            Position startPosition = new Position(position.getRow() + currentColor, position.getColumn());
            moveTwoPosition(matrix, currentPosition, doublePosition, startPosition);
        }

        diagonalMove(matrix, currentPosition, currentColor);
        enPassantMove(matrix, currentColor);

        return matrix;
    }

    private void moveOnePosition(boolean[][] matrix, Position currentPosition, int currentColor) {
        currentPosition.setValues(currentPosition.getRow() + currentColor, currentPosition.getColumn());
        if (getBoard().positionExists(currentPosition) && !getBoard().thereIsAPiece(currentPosition)) {
            matrix[currentPosition.getRow()][currentPosition.getColumn()] = true;
        }
    }

    private void moveTwoPosition(boolean[][] matrix, Position currentPosition, Position doublePosition, Position startPosition) {
        currentPosition.setValues(doublePosition.getRow(), doublePosition.getColumn());
        if (getBoard().positionExists(currentPosition) && !getBoard().thereIsAPiece(currentPosition)) {
            matrix[currentPosition.getRow()][currentPosition.getColumn()] = true;
        }

        currentPosition.setValues(startPosition.getRow(), startPosition.getColumn());
        if (getBoard().positionExists(currentPosition) && !getBoard().thereIsAPiece(currentPosition)) {
            matrix[currentPosition.getRow()][currentPosition.getColumn()] = true;
        }
    }

    private void diagonalMove(boolean[][] matrix, Position currentPosition, int currentColor) {
        int[] columns = {-1, 1};
        for (int column : columns) {
            currentPosition.setValues(position.getRow() + currentColor, position.getColumn() + column);
            if (getBoard().positionExists(currentPosition) && isThereOpponentPiece(currentPosition)) {
                matrix[currentPosition.getRow()][currentPosition.getColumn()] = true;
            }
        }
    }

    private void enPassantMove(boolean[][] matrix, int currentColor) {
        if ((currentColor == -1 && position.getRow() == 3) || (currentColor == 1 && position.getRow() == 4)) {
            Position left = new Position(position.getRow(), position.getColumn() - 1);
            Position right = new Position(position.getRow(), position.getColumn() + 1);

            checkEnPassant(matrix, left, currentColor);
            checkEnPassant(matrix, right, currentColor);
        }
    }

    private void checkEnPassant(boolean[][] matrix, Position target, int currentColor) {
        if (getBoard().positionExists(target) &&
                isThereOpponentPiece(target) &&
                getBoard().piece(target) == chesMatch.getEnPassantVulnerable()) {
            matrix[target.getRow() + currentColor][target.getColumn()] = true;
        }
    }

    @Override
    public String toString() {
        return "P";
    }
}
