package de.evicka;

import de.evicka.controller.Controller;
import de.evicka.model.Model;
import de.evicka.view.View;
import de.evicka.utils.Utils;

public class Main {
    public static void main(String[] args) {
        // Get the grid size from the user
        int size = Utils.gridSize();

        // Initiate the model & view with the given size
        Model model = new Model(size);
        View view = new View(size);

        // Initiate the controller with the model & view
        new Controller(model, view);

        // Update the view with the new board
        view.updateBoard(model.getBoard());
    }
}