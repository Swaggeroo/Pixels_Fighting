import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startGUI {
    private JTextField pixelsField;
    private JTextField zoomField;
    private JTextField delayField;
    private JTextField threadField;
    private JButton startButton;
    private JButton cancelButton;
    private JPanel jPannel;
    private static JFrame gui;

    public startGUI() {

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pixels = Integer.parseInt(pixelsField.getText());
                int zoom = Integer.parseInt(zoomField.getText());
                int delay = Integer.parseInt(delayField.getText());
                if (threadField.getText().equals("")){
                    PixelsFighting pixelsFighting = new PixelsFighting(delay,pixels,zoom);
                }else{
                    int threads = Integer.parseInt(threadField.getText());
                    PixelsFighting pixelsFighting = new PixelsFighting(delay,pixels,zoom,threads);
                }
                gui.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        gui = new JFrame("Pixels Fighting(Settings)");
        gui.setContentPane(new startGUI().jPannel);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setLocationRelativeTo(null);
        try {
            gui.setIconImage(Toolkit.getDefaultToolkit().getImage(startGUI.class.getResource("/img.png")));
        }catch (Exception e){
            e.printStackTrace();
        }
        gui.setVisible(true);
    }
}
