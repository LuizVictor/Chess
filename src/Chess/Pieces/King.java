package Chess.Pieces;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position currentPosition = new Position(0, 0);
        int[] rows = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] columns = {0, 0, -1, 1, -1, 1, -1, 1};

        for (int i = 0; i < rows.length; i++) {
            int row = position.getRow() + rows[i];
            int column = position.getColumn() + columns[i];

            currentPosition.setValues(row, column);

            if (getBoard().positionExists(currentPosition) && canMove(currentPosition)) {
                matrix[row][column] = true;
            }
        }

        return matrix;
    }

    @Override
    public String toString() {
        return "K";
    }
}
