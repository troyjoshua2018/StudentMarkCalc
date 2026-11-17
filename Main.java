import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            StudentMarkCalc calculator = new StudentMarkCalc();
            calculator.setVisible(true);
        });

    }
}
