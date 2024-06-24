package de.evicka.utils;

import javax.swing.JOptionPane;
import javax.swing.JSlider;

public class Utils {
    // get the char at the position
    public static String charAtPosition(int position) {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(alphabet.charAt(position));
    }

    // slider to select the grid size for the shuffl game
    public static int gridSize() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 2, 5, 2);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JOptionPane.showMessageDialog(null, slider, "Select Grid Size", JOptionPane.PLAIN_MESSAGE);
        return slider.getValue();
    }
}
