package org.example;

import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.lang.Math.abs;
import static java.util.Objects.nonNull;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean movementPatternIsCorrect(int line, int column, int toLine, int toColumn) {
        int horizontalMoving = abs(line) - abs(toLine);
        int verticalMoving = abs(column) - abs(toColumn);
        return horizontalMoving == 0 && verticalMoving != 0 || horizontalMoving != 0 && verticalMoving == 0;
    }

    @Override
    public boolean canAttack(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return movementPatternIsCorrect(line, column, toLine, toColumn) && pathIsClear(chessBoard, line, column, toLine, toColumn) ;
    }

    @Override
    public boolean pathIsClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean horizontalMoving = abs(line) - abs(toLine) == 0;
        MovingParams movingParams = calculateMovingParams(line, column, toLine, toColumn);
        if (horizontalMoving) {
            for (int i = movingParams.getStarPos(); movingParams.getNeedToChangePos().test(i); i = movingParams.getChangePos().apply(i)) {
                if (nonNull(chessBoard.board[line][i])) {
                    return false;
                }
            }
        } else {
            for (int i = movingParams.getStarPos(); movingParams.getNeedToChangePos().test(i); i = movingParams.getChangePos().apply(i)) {
                if (nonNull(chessBoard.board[i][column])) {
                    return false;
                }
            }
        }
        return true;
    }

    private MovingParams calculateMovingParams(int line, int column, int toLine, int toColumn) {
        MovingParams movingParams = new MovingParams();
        if (column > toColumn) {
            movingParams.setStarPos(column - 1);
            movingParams.setNeedToChangePos((pos) -> pos > toColumn);
            movingParams.setChangePos((pos) -> --pos);
        } else if (column < toColumn) {
            movingParams.setStarPos(column + 1);
            movingParams.setNeedToChangePos((pos) -> pos < toColumn);
            movingParams.setChangePos((pos) -> ++pos);
        } else if (line > toLine) {
            movingParams.setStarPos(line - 1);
            movingParams.setNeedToChangePos((pos) -> pos > toLine);
            movingParams.setChangePos((pos) -> --pos);
        } else if (line < toLine) {
            movingParams.setStarPos(line + 1);
            movingParams.setNeedToChangePos((pos) -> pos < toLine);
            movingParams.setChangePos((pos) -> ++pos);
        }
        return movingParams;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
