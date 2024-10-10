package org.example;

public abstract class ChessPiece {
    private String color;
    private boolean check;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean checkCleanPath(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        String pieceType = chessBoard.board[line][column].getSymbol();
        // TODO придумать способ проверки, но потом все перенести по классам
        switch (pieceType) {
            case "B" -> {
                return true;
            }
            case "Q" -> {
                return true;
            }
            case "R" -> {
                return true;
            }
            case "P" -> {
                return true;
            }
            case "K" -> {
                return chessBoard.isFieldUnderAttack(toLine, toColumn);
            }
        }
        return false;
    }

    public boolean movesInSamePosition(int line, int column, int toLine, int toColumn) {
        return line == toLine && column == toColumn;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();
}
