package utils;

import javax.swing.JSlider;
import javax.swing.JOptionPane;

public class Screen {
    // slider to select the grid size for the shuffl game
    public static int getGridSize() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 2, 5, 2);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JOptionPane.showMessageDialog(null, slider, "Select Grid Size", JOptionPane.PLAIN_MESSAGE);
        return slider.getValue();
    }
}