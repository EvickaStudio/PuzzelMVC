package de.evicka.controller;

import de.evicka.model.Model;
import de.evicka.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller {
    private Model model;
    private View view;

    // Controller initiates with the model and view
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        // Add listeners from the view
        this.view.addShiftListener(new ShiftListener());
        this.view.addShuffleListener(new ShuffleListener());
    }

    // ActionListener for shifting the buttons
    class ShiftListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the button that was clicked
            JButton button = (JButton) e.getSource();
            // Get the row and column of the button
            int row = (int) button.getClientProperty("row");
            int col = (int) button.getClientProperty("col");
            // if the button can be shifted
            if (model.shift(row, col)) {
                // update the board and move counter
                view.updateBoard(model.getBoard());
                view.updateMoveCounter(model.getMoves());
                // if the board is solved show the win message
                if (model.isSolved()) {
                    view.showWinMessage(model.getMoves());
                }
            }
        }
    }

    // ActionListener for shuffling the board
    class ShuffleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // reset the model
            model.reset();
            // update the board with the new shuffled board
            view.updateBoard(model.getBoard());
            // update the move counter
            view.updateMoveCounter(model.getMoves());
        }
    }
}