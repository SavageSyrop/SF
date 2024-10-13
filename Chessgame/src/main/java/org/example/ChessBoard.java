package org.example;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                if (board[endLine][endColumn].getSymbol().equals("R") || board[endLine][endColumn].getSymbol().equals("K")) {
                    board[endLine][endColumn].setCheck(false);
                }
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castlingShort() {
        if (nowPlayer.equals("White")) {
            if (board[0][4] == null || board[0][7] == null) return false;
            if (board[0][4].getSymbol().equals("K") && board[0][7].getSymbol().equals("R") &&
                    board[0][5] == null && board[0][6] == null) {
                if (board[0][4].getColor().equals("White") && board[0][7].getColor().equals("White") &&
                        board[0][4].isCheck() && board[0][7].isCheck()
                        && new King("White").pathIsClear(this, 0, 3, 0, 4)
                        && new King("White").pathIsClear(this, 0, 4, 0, 5)
                        && new King("White").pathIsClear(this, 0, 5, 0, 6)) {
                    board[0][4] = null;
                    board[0][5] = new Rook("White");
                    board[0][5].setCheck(false);
                    board[0][6] = new King("White");
                    board[0][6].setCheck(false);
                    board[0][7] = null;
                    nowPlayer = "Black";
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][4] == null || board[7][7] == null) return false;
            if (board[7][4].getSymbol().equals("K") && board[7][7].getSymbol().equals("R") &&
                    board[7][5] == null && board[7][6] == null) {
                if (board[7][4].getColor().equals("Black") && board[7][7].getColor().equals("Black") &&
                        board[7][4].isCheck() && board[7][7].isCheck()
                        && new King("Black").pathIsClear(this, 7, 3, 7, 4)
                        && new King("Black").pathIsClear(this, 7, 4, 7, 5)
                        && new King("Black").pathIsClear(this, 7, 5, 7, 6)) {
                    board[7][4] = null;
                    board[7][5] = new Rook("Black");
                    board[7][5].setCheck(false);
                    board[7][6] = new King("Black");
                    board[7][6].setCheck(false);
                    board[7][7] = null;
                    nowPlayer = "White";
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castlingLong() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") &&
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].isCheck() && board[0][4].isCheck()
                        && new King("White").pathIsClear(this, 0, 3, 0, 4)
                        && new King("White").pathIsClear(this, 0, 2, 0, 3)
                        && new King("White").pathIsClear(this, 0, 1, 0, 2)
                        && new King("White").pathIsClear(this, 0, 0, 0, 1)) {
                    board[0][4] = null;
                    board[0][3] = new Rook("White");
                    board[0][3].setCheck(false);
                    board[0][2] = new King("White");
                    board[0][2].setCheck(false);
                    board[0][0] = null;
                    nowPlayer = "Black";
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") &&
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].isCheck() && board[7][4].isCheck()
                        && new King("Black").pathIsClear(this, 7, 3, 7, 4)
                        && new King("Black").pathIsClear(this, 7, 2, 7, 3)
                        && new King("Black").pathIsClear(this, 7, 1, 7, 2)
                        && new King("Black").pathIsClear(this, 7, 0, 7, 1)) {
                    board[7][4] = null;
                    board[7][3] = new Rook("Black");
                    board[7][3].setCheck(false);
                    board[7][2] = new King("Black");
                    board[7][2].setCheck(false);
                    board[7][0] = null;
                    nowPlayer = "White";
                    return true;
                } else return false;
            } else return false;
        }
    }
}
