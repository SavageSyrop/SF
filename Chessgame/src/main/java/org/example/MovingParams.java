package org.example;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MovingParams {
    private Predicate<Integer> needToChangePos;
    private Function<Integer, Integer> changePos;
    private int starPos;

    public Predicate<Integer> getNeedToChangePos() {
        return needToChangePos;
    }

    public void setNeedToChangePos(Predicate<Integer> needToChangePos) {
        this.needToChangePos = needToChangePos;
    }

    public Function<Integer, Integer> getChangePos() {
        return changePos;
    }

    public void setChangePos(Function<Integer, Integer> changePos) {
        this.changePos = changePos;
    }

    public int getStarPos() {
        return starPos;
    }

    public void setStarPos(int starPos) {
        this.starPos = starPos;
    }
}
