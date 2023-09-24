package Chess.Pieces;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChesMatch;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {

    private ChesMatch chesMatch;

    public King(Board board, Color color, ChesMatch chesMatch) {
        super(board, color);
        this.chesMatch = chesMatch;
    }

    private boolean testRookCastling(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
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

        if (canPerformKingSideCastling()) {
            matrix[position.getRow()][position.getColumn() + 2] = true;
        }

        if (canPerformQueenSideCastling()) {
            matrix[position.getRow()][position.getColumn() - 2] = true;
        }

        return matrix;
    }

    private boolean canPerformKingSideCastling() {
        if (getMoveCount() != 0 || chesMatch.getCheck()) {
            return false;
        }

        Position kingSideRook = new Position(position.getRow(), position.getColumn() + 3);
        return testRookCastling(kingSideRook) && isPathClearForKingSideCastling();
    }

    private boolean canPerformQueenSideCastling() {
        if (getMoveCount() != 0 || chesMatch.getCheck()) {
            return false;
        }

        Position queenSideRook = new Position(position.getRow(), position.getColumn() - 4);
        return testRookCastling(queenSideRook) && isPathClearForQueenSideCastling();
    }

    private boolean isPathClearForKingSideCastling() {
        Position position1 = new Position(position.getRow(), position.getColumn() + 1);
        Position position2 = new Position(position.getRow(), position.getColumn() + 2);
        return getBoard().piece(position1) == null && getBoard().piece(position2) == null;
    }

    private boolean isPathClearForQueenSideCastling() {
        Position position1 = new Position(position.getRow(), position.getColumn() - 1);
        Position position2 = new Position(position.getRow(), position.getColumn() - 2);
        Position position3 = new Position(position.getRow(), position.getColumn() - 3);
        return getBoard().piece(position1) == null &&
                getBoard().piece(position2) == null &&
                getBoard().piece(position3) == null;
    }

    @Override
    public String toString() {
        return "K";
    }
}
