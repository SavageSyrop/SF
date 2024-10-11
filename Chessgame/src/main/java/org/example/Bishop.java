package org.example;

public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean movementPatternIsCorrect(int line, int column, int toLine, int toColumn) {
        return Math.abs(line - toLine) == Math.abs(column - toColumn);
    }

    @Override
    public boolean pathIsClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        MovingParams movingParamsY = calculateMovingParams(line, toLine);
        MovingParams movingParamsX = calculateMovingParams(column, toColumn);

        for (int i = movingParamsY.getStarPos(), j = movingParamsX.getStarPos();
             movingParamsY.getNeedToChangePos().test(i);
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
        } else {
            movingParams.setStarPos(from);
            movingParams.setNeedToChangePos((a) -> a < from);
            movingParams.setChangePos((a) -> a++);
        }
        return movingParams;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
