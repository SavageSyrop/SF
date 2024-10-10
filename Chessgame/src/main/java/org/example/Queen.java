package org.example;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn) && !movesInSamePosition(line, column, toLine, toColumn)) {
            return checkCleanPath(chessBoard, line, column, toLine, column);
        } else {
            return false;
        }
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
