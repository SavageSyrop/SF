package org.example;

public abstract class ChessPiece {
    private final String color;
    private boolean check;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean movesInSamePosition(int line, int column, int toLine, int toColumn) {
        return line == toLine && column == toColumn;
    }

    public abstract boolean movementPatternIsCorrect(int line, int column, int toLine, int toColumn);

    public boolean finalPositionIsEmptyOrEnemy(ChessBoard chessBoard, int toLine, int toColumn) {
        ChessPiece finalPosition = chessBoard.board[toLine][toColumn];
        return finalPosition == null || !finalPosition.getColor().equals(chessBoard.nowPlayer) ;
    }

    public abstract boolean pathIsClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return coordinatesAreCorrect(chessBoard, line, column, toLine, toColumn) && movementPatternIsCorrect(line, column, toLine, toColumn) && finalPositionIsEmptyOrEnemy(chessBoard, toLine, toColumn) && pathIsClear(chessBoard, line, column, toLine, toColumn);
    }

    public boolean coordinatesAreCorrect(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn) && !movesInSamePosition(line, column, toLine, toColumn);
    }

    public abstract String getSymbol();
}
