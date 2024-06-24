package de.evicka.view;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;
import de.evicka.utils.Utils;

public class View extends JFrame {
    private JButton[][] buttons;
    private int size;
    private JButton shuffleButton;
    private JLabel moveCounter;
    private Font font = new Font("Segoe UI", Font.BOLD, 20);

    public View(int size) {
        this.size = size;
        this.buttons = new JButton[this.size][this.size];
        setTitle("Puzzle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());
        initializeButtons();
        shuffleButton = new JButton("Shuffle");
        moveCounter = new JLabel("Moves: 0 ");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(shuffleButton, BorderLayout.WEST);
        bottomPanel.add(moveCounter, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void initializeButtons() {
        JPanel gridPanel = new JPanel(new GridLayout(size, size, 4, 4));
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].putClientProperty("row", row);
                buttons[row][col].putClientProperty("col", col);
                buttons[row][col].setForeground(Color.BLACK);
                buttons[row][col].setFont(font);
                gridPanel.add(buttons[row][col]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    public void addShiftListener(BiConsumer<Integer, Integer> listener) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int fr = row;
                int fc = col;
                buttons[row][col].addActionListener(e -> listener.accept(fr, fc));
            }
        }
    }

    public void addShuffleListener(Runnable listener) {
        shuffleButton.addActionListener(e -> listener.run());
    }

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
                        buttons[row][col].setBackground(Color.GREEN);
                    } else {
                        buttons[row][col].setBackground(Color.ORANGE);
                    }
                }
            }
        }
    }

    public void showWinMessage(int moves) {
        JOptionPane.showMessageDialog(this, "You solved the puzzle in " + moves + " moves!");
    }

    public void updateMoveCounter(int moves) {
        moveCounter.setText("Moves: " + moves + " ");
    }
}