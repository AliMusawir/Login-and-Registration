import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Client extends JDialog{

    private JPanel panel1;
    private JButton logbtn;
    private JLabel jlable;
    public Login lg;


    public Client (JFrame parent) {
        setTitle("Registeration");
        setContentPane(panel1);
        setMinimumSize(new Dimension(459, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        logbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login lg=new Login(null);

            }
        });
        lg =new Login(null);
        String x=lg.globle;
        jlable.setText("to "+x);
        setVisible(true);

    }

    public static void main(String[] args) {
        Client cl=new Client(null);






    }
}
