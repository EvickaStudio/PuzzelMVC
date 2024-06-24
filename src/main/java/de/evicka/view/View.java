package de.evicka.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import de.evicka.utils.Utils;

public class View extends JFrame {
    private JButton[][] buttons; // the buttons of the board
    private int size; // the size of the board
    private JButton shuffleButton; // the shuffle button
    private JLabel moveCounter; // the move counter
    private Font font = new Font("Segoe UI", Font.BOLD, 20); // the font of the buttons

    public View(int zs) {
        this.size = zs; // clean the size
        this.buttons = new JButton[this.size][this.size]; // create the buttons
        setTitle("Puzzle Game"); // Set the window title here
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // set the layout to border layout
        setLayout(new BorderLayout());
        // initialize the buttons
        // this creates a new grid layout
        // in the center of the BorderLayout
        initializeButtons();

        // create the reset button
        shuffleButton = new JButton("Shuffle");
        // create the move counter
        moveCounter = new JLabel("Moves: 0 ");
        // create the bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(shuffleButton, BorderLayout.WEST); // left
        bottomPanel.add(moveCounter, BorderLayout.EAST); // right

        add(bottomPanel, BorderLayout.SOUTH); // add the bottom panel to the south of the frame
        setVisible(true);
    }

    public JButton[][] getButtons() {
        return this.buttons;
    }

    private void initializeButtons() {
        // Create a grid of buttons size x size
        JPanel gridPanel = new JPanel(new GridLayout(size, size, 4, 4));
        // for each row and column
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                // create a new button
                buttons[row][col] = new JButton();
                // set the row and col of the button
                buttons[row][col].putClientProperty("row", row);
                buttons[row][col].putClientProperty("col", col);
                buttons[row][col].setForeground(Color.BLACK); // set the text color to black
                buttons[row][col].setFont(font); // set the font
                gridPanel.add(buttons[row][col]); // add the button to the grid
            }
        }
        add(gridPanel, BorderLayout.CENTER); // add the grid to the center
    }

    // shift listener for each button
    public void addShiftListener(ActionListener listener) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                buttons[row][col].addActionListener(listener);
            }
        }
    }

    // shuffle listener
    public void addShuffleListener(ActionListener listener) {
        shuffleButton.addActionListener(listener);
    }

    // update the board
    public void updateBoard(int[][] board) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int value = board[row][col];
                if (value == -1) {
                    buttons[row][col].setText("");
                    buttons[row][col].setBackground(Color.WHITE);
                } else {
                    buttons[row][col].setText(Utils.charAtPosition(value));
                    int expectedValue = row * size + col;
                    if (value == expectedValue) {
                        buttons[row][col].setBackground(Color.GREEN); // Correct position
                    } else {
                        buttons[row][col].setBackground(Color.ORANGE); // Otherwise, set to orange
                    }
                }
            }
        }
    }

    // Simple win message that pops up when the game is solved and shows the number
    // of moves
    public void showWinMessage(int moves) {
        JOptionPane.showMessageDialog(this, "You solved the puzzle in " + moves + " moves!");
    }

    // update the move counter
    public void updateMoveCounter(int moves) {
        moveCounter.setText("Moves: " + moves + " ");
    }
}