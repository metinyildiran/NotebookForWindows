import javax.swing.*;
import java.awt.*;

public class NoteClass extends JPanel {

    Color primaryColor = new Color(49, 67, 91);

    public NoteClass() {
        JPanel jPanel = new JPanel();

        jPanel.setBackground(Color.WHITE);
        jPanel.setSize(500, 500);
        jPanel.setBackground(primaryColor);
        add(jPanel);

    }

}
