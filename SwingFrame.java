import javax.swing.*;

/**
 * Optional entry point kept for older scripts; launches the same UI as {@link game}.
 */
public class SwingFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new game());
    }
}
