package de.evicka.model;

import javax.swing.*;
import utils.Clean;
import static utils.Chars.getCharAtPosition;

/**
 * Model for the shuffle puzzle game.
 *
 * init with a x*x board of buttons (2x2, 3x3, 4x4, 5x5)
 * shuffle the board
 */
public class Model {
    private JButton[][] board; // the board made of JButtons
    private int size; // size of the board
    private int moves = 0; // for a score

    public Model(int sz) {
        this.size = Clean.cleanSize(sz); // sanitize the size just in case
        this.board = new JButton[size][size]; // create the board as an n x n matrix
        initBoard(); // initialize the board
        shuffle(); // shuffle the board
        this.moves = 0; // the shuffle uses swaps
                        // and so does the moves counter
                        // so we need to reset it
    }

    /**
     * Initialize the board
     */
    private void initBoard() {
        // initialize the board with the numbers from 1 to size^2
        int i = 0;
        // create a new board with the size of the board
        for (int rows = 0; rows < size; rows++) {
            // create a new rows
            for (int col = 0; col < size; col++) {
                // create a new button with the number
                board[rows][col] = new JButton(String.valueOf(getCharAtPosition(i++)));
            }
        }
        // set the last button to be empty
        board[size - 1][size - 1].setText(" ");
    }

    /**
     * Check if the board is solved
     */
    public boolean isSolved() {
        int count = 0;
        // for each button in the board
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // at the end check if the button is empty
                if (i == size - 1 && j == size - 1) {
                    return board[i][j].getText().equals(" ");
                }
                // if the button is not equal to the expected char at the given position
                if (!board[i][j].getText().equals(getCharAtPosition(count++))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Shuffle the board
     */
    private void shuffle() {
        // shuffle the board size * size * 100 times
        int n = size * size * 100;
        for (int i = 0; i < n; i++) {
            // shuffle the board between 0 and size - 1
            int row = (int) (Math.random() * size);
            int col = (int) (Math.random() * size);
            // if the button is not free, try again
            if (!shift(row, col)) {
                i--;
            }
        }
    }

    /**
     * Shift the board
     */
    public boolean shift(int rows, int col) {
        // check if the rows and columns are valid
        if (rows < 0 || rows >= size || col < 0 || col >= size)
            return false;

        // UP
        // check if the button above is free
        if (rows - 1 >= 0 && isFree(rows - 1, col)) {
            swap(rows, col, rows - 1, col);
            return true;
        }

        // DOWN
        // check if the button below is free
        if (rows + 1 < size && isFree(rows + 1, col)) {
            swap(rows, col, rows + 1, col);
            return true;
        }

        // LEFT
        // check if the button left is free
        if (col - 1 >= 0 && isFree(rows, col - 1)) {
            swap(rows, col, rows, col - 1);
            return true;
        }

        // RIGHT
        // check if the button right is free
        if (col + 1 < size && isFree(rows, col + 1)) {
            swap(rows, col, rows, col + 1);
            return true;
        }

        // if the button is not free, try again
        return false;
    }

    /**
     * Swap two buttons
     */
    private void swap(int row1, int col1, int row2, int col2) {
        // Get/ Set the text of the specific button
        String temp = board[row1][col1].getText();
        // swap the text of the two buttons
        board[row1][col1].setText(board[row2][col2].getText());
        // set the text of the second button to the first button's text
        board[row2][col2].setText(temp);
        moves++; // increment the moves
    }

    /**
     * Check if the button is free
     */
    private boolean isFree(int rows, int col) {
        // checks if the text of the button is equal to " "
        return board[rows][col].getText().equals(" ");
    }

    /**
     * Get the moves
     */
    public int getMoves() {
        return this.moves;
    }

    /**
     * Get the board
     */
    public JButton[][] getBoard() {
        return this.board;
    }

    /**
     * Get the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Reset the game
     * Reset the board, shuffle it and reset the moves
     */
    public void reset() {
        initBoard();
        shuffle();
        moves = 0;
    }
}
