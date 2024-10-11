package org.example;

import static java.lang.Math.abs;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean movementPatternIsCorrect(int line, int column, int toLine, int toColumn) {
        int horizontalMoving = abs(line) - abs(toLine);
        int verticalMoving = abs(column) - abs(toColumn);
        return horizontalMoving == 0 && verticalMoving != 0
                || horizontalMoving != 0 && verticalMoving == 0
                || Math.abs(line - toLine) == Math.abs(column - toColumn);
    }

    @Override
    public boolean pathIsClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        MovingParams movingParamsY = calculateMovingParams(line, toLine);
        MovingParams movingParamsX = calculateMovingParams(column, toColumn);

        for (int i = movingParamsY.getStarPos(), j = movingParamsX.getStarPos();
             movingParamsY.getNeedToChangePos().test(i) || movingParamsX.getNeedToChangePos().test(i);
             movingParamsY.getChangePos().accept(i), movingParamsX.getChangePos().accept(j)) {
            if (chessBoard.board[i][j] != null) {
                return false;
            }
        }
        return true;
    }

    private MovingParams calculateMovingParams(int from, int to) {
        MovingParams movingParams = new MovingParams();
        if (from > to) {
            movingParams.setStarPos(to);
            movingParams.setNeedToChangePos((a) -> a > from);
            movingParams.setChangePos((a) -> a--);
        } else if (from < to) {
            movingParams.setStarPos(from);
            movingParams.setNeedToChangePos((a) -> a < from);
            movingParams.setChangePos((a) -> a++);
        } else {
            movingParams.setStarPos(from);
            movingParams.setNeedToChangePos((a) -> false);
            movingParams.setChangePos((a) -> {});
        }
        return movingParams;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
