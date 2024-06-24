package de.evicka.controller;

import de.evicka.model.Model;
import de.evicka.view.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.addShiftListener((row, col) -> this.clicked(row, col));
        this.view.addShuffleListener(() -> this.shuffle());
    }

    private void clicked(int row, int col) {
        if (model.shift(row, col)) {
            view.updateBoard(model.getBoard());
            view.updateMoveCounter(model.getMoves());
            if (model.isSolved()) {
                view.showWinMessage(model.getMoves());
            }
        }
    }

    private void shuffle() {
        model.reset();
        view.updateBoard(model.getBoard());
        view.updateMoveCounter(model.getMoves());
    }
}
