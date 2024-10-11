package org.example;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean movementPatternIsCorrect(int line, int column, int toLine, int toColumn) {
        if ("Black".equals(this.getColor())) {
            if (column != toColumn) {
                return line - toLine == 1 && Math.abs(column - toColumn) == 1;
            } else {
                if (line != 6) {
                    return line - toLine == 1;
                } else {
                    return line - toLine == 1 || line - toLine == 2;
                }
            }
        } else {
            if (column != toColumn) {
                return toLine - line == 1 && Math.abs(column - toColumn) == 1;
            } else {
                if (line != 1) {
                    return toLine - line == 1;
                } else {
                    return toLine - line == 1 || toLine - line == 2;
                }
            }
        }
    }

    @Override
    public boolean pathIsClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if ("Black".equals(this.getColor())) {
            if (line - toLine == 2) {
                return chessBoard.board[line - 1][column] == null;
            }
        } else {
            if (toLine - line == 2) {
                return chessBoard.board[line + 1][column] == null;
            }
        }
        return true;
    }


    @Override
    public String getSymbol() {
        return "P";
    }

    public boolean isAtStartPosition(int line) {
        return ("White".equals(this.getColor()) && line == 1) || ("Black".equals(this.getColor()) && line == 6);
    }
}
