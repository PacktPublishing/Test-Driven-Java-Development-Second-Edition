package com.packtpublishing.tddjava.ch03tictactoe.mongo;

import org.jongo.marshall.jackson.oid.Id;

public class TicTacToeBean {

    @Id
    private int turn;
    public int getTurn() {
        return turn;
    }

    private int x;
    public int getX() {
        return x;
    }

    private int y;
    public int getY() {
        return y;
    }

    private char player;
    public char getPlayer() {
        return player;
    }

    public TicTacToeBean() { }
    public TicTacToeBean(int turn, int x, int y, char player) {
        this.turn = turn;
        this.x = x;
        this.y = y;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicTacToeBean that = (TicTacToeBean) o;
        if (player != that.player) return false;
        if (turn != that.turn) return false;
        if (x != that.x) return false;
        if (y != that.y) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = turn;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + (int) player;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Turn: %d; X: %d; Y: %d; Player: %s", turn, x, y, player);
    }
}
