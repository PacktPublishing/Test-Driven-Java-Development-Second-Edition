package com.packtpublishing.tddjava.ch05connect4;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

/**
 * Test-last implementation
 */
public class Connect4 {

    public enum Color {
        RED('R'), GREEN('G'), EMPTY(' ');

        private final char value;

        Color(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private Color winner;

    private Color currentPlayer = Color.RED;

    public static final int COLUMNS = 7;

    public static final int ROWS = 6;

    public static final int DISCS_FOR_WIN = 4;

    private static final String DELIMITER = "|";

    private Color[][] board = new Color[COLUMNS][ROWS];

    public Connect4() {
        for (Color[] column : board) {
            Arrays.fill(column, Color.EMPTY);
        }
    }

    private void switchPlayer() {
        if (Color.RED == currentPlayer) {
            currentPlayer = Color.GREEN;
        } else {
            currentPlayer = Color.RED;
        }
        System.out.println("Current turn: " + currentPlayer);
    }

    public void printBoard() {
        for (int row = ROWS - 1; row >= 0; --row) {
            StringJoiner stringJoiner = new StringJoiner(DELIMITER, DELIMITER, DELIMITER);
            for (int col = 0; col < COLUMNS; ++col) {
                stringJoiner.add(board[col][row].toString());
            }
            System.out.println(stringJoiner.toString());
        }
    }

    public void putDisc(int column) {
        if (column > 0 && column <= COLUMNS) {
            int numOfDiscs = getNumberOfDiscsInColumn(column - 1);
            if (numOfDiscs < ROWS) {
                board[column - 1][numOfDiscs] = currentPlayer;
                printBoard();
                checkWinCondition(column - 1, numOfDiscs);
                switchPlayer();
            } else {
                System.out.println(numOfDiscs);
                System.out.println("There's no room for a new disc in this column");
                printBoard();
            }
        } else {
            System.out.println("Column out of bounds");
            printBoard();
        }
    }

    private int getNumberOfDiscsInColumn(int column) {
        if (column >= 0 && column < COLUMNS) {
            int row;
            for (row = 0; row < ROWS; row++) {
                if (Color.EMPTY == board[column][row]) return row;
            }
            return row;
        }
        return -1;
    }

    private void checkWinCondition(int col, int row) {
        Pattern winPattern = Pattern.compile(".*" + currentPlayer + "{" + DISCS_FOR_WIN + "}.*");

        // Vertical check
        StringJoiner stringJoiner = new StringJoiner("");
        for (int auxRow = 0; auxRow < ROWS; ++auxRow) {
            stringJoiner.add(board[col][auxRow].toString());
        }
        if (winPattern.matcher(stringJoiner.toString()).matches()) {
            winner = currentPlayer;
            System.out.println(currentPlayer + " wins");
            return;
        }

        // Horizontal check
        stringJoiner = new StringJoiner("");
        for (int column = 0; column < COLUMNS; ++column) {
            stringJoiner.add(board[column][row].toString());
        }
        if (winPattern.matcher(stringJoiner.toString()).matches()) {
            winner = currentPlayer;
            System.out.println(currentPlayer + " wins");
            return;
        }

        // Diagonal checks
        int startOffset = Math.min(col, row);
        int column = col - startOffset, auxRow = row - startOffset;
        stringJoiner = new StringJoiner("");
        do {
            stringJoiner.add(board[column++][auxRow++].toString());
        } while (column < COLUMNS && auxRow < ROWS);
        if (winPattern.matcher(stringJoiner.toString()).matches()) {
            winner = currentPlayer;
            System.out.println(currentPlayer + " wins");
            return;
        }

        startOffset = Math.min(col, ROWS - 1 - row);
        column = col - startOffset;
        auxRow = row + startOffset;
        stringJoiner = new StringJoiner("");
        do {
            stringJoiner.add(board[column++][auxRow--].toString());
        } while (column < COLUMNS && auxRow >= 0);
        if (winPattern.matcher(stringJoiner.toString()).matches()) {
            winner = currentPlayer;
            System.out.println(currentPlayer + " wins");
        }
    }

    public boolean isFinished() {
        if (winner != null) return true;
        int numOfDiscs = 0;
        for (int col = 0; col < COLUMNS; ++col) {
            numOfDiscs += getNumberOfDiscsInColumn(col);
        }
        if (numOfDiscs >= COLUMNS * ROWS) {
            System.out.println("It's a draw");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Connect4 game = new Connect4();
        Scanner scanner = new Scanner(System.in);
        while (!game.isFinished()) {
            System.out.println("Where do the next disc should be placed?");
            game.putDisc(scanner.nextInt());
        }
    }
}
