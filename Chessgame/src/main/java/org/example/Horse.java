package org.example;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean movementPatternIsCorrect(int line, int column, int toLine, int toColumn) {
        return (Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1) || (Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2);
    }

    @Override
    public boolean pathIsClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return true;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
