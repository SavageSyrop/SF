package org.example;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class MovingParams {
    private Predicate<Integer> needToChangePos;
    private Consumer<Integer> changePos;
    private int starPos;
    private int finishPos;

    public Predicate<Integer> getNeedToChangePos() {
        return needToChangePos;
    }

    public void setNeedToChangePos(Predicate<Integer> needToChangePos) {
        this.needToChangePos = needToChangePos;
    }

    public Consumer<Integer> getChangePos() {
        return changePos;
    }

    public void setChangePos(Consumer<Integer> changePos) {
        this.changePos = changePos;
    }

    public int getStarPos() {
        return starPos;
    }

    public void setStarPos(int starPos) {
        this.starPos = starPos;
    }

    public int getFinishPos() {
        return finishPos;
    }

    public void setFinishPos(int finishPos) {
        this.finishPos = finishPos;
    }
}
