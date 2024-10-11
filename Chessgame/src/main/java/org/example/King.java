package org.example;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean movementPatternIsCorrect(int line, int column, int toLine, int toColumn) {
        return Math.abs(line - toLine) == 1 || Math.abs(column - toColumn) == 1;
    }

    @Override
    public boolean pathIsClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!chessBoard.board[i][j].getColor().equals(chessBoard.nowPlayer) && chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j, toLine, toColumn)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
