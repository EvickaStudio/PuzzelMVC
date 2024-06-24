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
                buttons[row][col] = createButton(row, col);
                gridPanel.add(buttons[row][col]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    private JButton createButton(int row, int col) {
        JButton button = new JButton();
        button.putClientProperty("row", row);
        button.putClientProperty("col", col);
        button.setForeground(Color.BLACK);
        button.setFont(font);
        return button;
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
                updateButton(buttons[row][col], board[row][col], row, col);
            }
        }
    }

    private void updateButton(JButton button, int value, int row, int col) {
        if (value == -1) {
            button.setText("");
            button.setBackground(Color.WHITE);
        } else {
            button.setText(Utils.charAtPosition(value));
            int expectedValue = row * size + col;
            if (value == expectedValue) {
                button.setBackground(Color.GREEN);
            } else {
                button.setBackground(Color.ORANGE);
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