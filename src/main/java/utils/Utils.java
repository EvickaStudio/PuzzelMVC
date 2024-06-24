package utils;

import javax.swing.JOptionPane;
import javax.swing.JSlider;

public class Utils {
    // get the char at the position
    public static String charAtPosition(int position) {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(alphabet.charAt(position));
    }

    public static int cleanSize(int sz){
        // 1 < size < 6
        // limit because there are only 26 characters in the alphabet
        // 36 would be too much
        return Math.max(2, Math.min(sz, 5));
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
