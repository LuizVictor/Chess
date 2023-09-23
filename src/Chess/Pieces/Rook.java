package Chess.Pieces;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // Up, Down, Left, Right

        for (int[] direction : directions) {
            makeMove(matrix, direction[0], direction[1]);
        }

        return matrix;
    }

    private void makeMove(boolean[][] matrix, int row, int column) {
        Position currentPosition = new Position(position.getRow() + row, position.getColumn() + column);

        while (getBoard().positionExists(currentPosition) && !getBoard().thereIsAPiece(currentPosition)) {
            matrix[currentPosition.getRow()][currentPosition.getColumn()] = true;
            currentPosition.setValues(currentPosition.getRow() + row, currentPosition.getColumn() + column);
        }

        if (getBoard().positionExists(currentPosition) && isThereOpponentPiece(currentPosition)) {
            matrix[currentPosition.getRow()][currentPosition.getColumn()] = true;
        }
    }
}
