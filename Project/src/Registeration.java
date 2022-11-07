import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class Registeration extends JDialog {
    private JTextField tfname;
    private JTextField tfphone;
    private JTextField tfemail;
    private JTextField tfpassword;
    private JTextField tfconfirm;
    private JButton btok;
    private JButton btcancel;
    private JPanel Jregister;
    private JLabel jlable;
    public  String name;


    public Registeration (JFrame parent)
     {
         setTitle("Registeration");
         setContentPane(Jregister);
         setMinimumSize(new Dimension(459,450));
         setModal(true);
         setLocationRelativeTo(parent);
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);


         btok.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String name = tfname.getText();
                 String phone = tfphone.getText();
                 String email = tfemail.getText();
                 String password = tfpassword.getText();
                 String confirm = tfconfirm.getText();

                 System.out.println(password);
                 System.out.println(confirm);
                 if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                     JOptionPane.showMessageDialog(Registeration.this, "Please fill all the fields", "Try again", JOptionPane.ERROR_MESSAGE);

                 }
                 if(!password.equals(confirm))
                 {
                     JOptionPane.showMessageDialog(Registeration.this, "mismatch passwords ", "Try again", JOptionPane.ERROR_MESSAGE);
                     tfpassword.setText("");
                     tfconfirm.setText("");
                 }
                 else {
                     try {
                         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "soomro");
                         Statement statement = conn.createStatement();
                         String sql = "INSERT INTO  registeration (Name,Number,Emaill,Password,ConfirmPassword)" + "values(?,?,?,?,?)";
                         PreparedStatement preparedStatement = conn.prepareStatement(sql);
                         preparedStatement.setString(1, name);
                         preparedStatement.setString(2, phone);
                         preparedStatement.setString(3, email);
                         preparedStatement.setString(4, password);
                         preparedStatement.setString(5, confirm);

                         preparedStatement.executeUpdate();

                         statement.close();
                         conn.close();

                     } catch (Exception x) {
                         // Prints what exception has been thrown
                         System.out.println(x);
                     }
                     User user = new User();
                     user.name = tfname.getText();
                     user.phone = tfphone.getText();
                     user.email = tfemail.getText();
                     user.password = tfpassword.getText();
                     user.confirmpassword = tfconfirm.getText();

                     jlable.setText("Registered successfully");
                     tfname.setText("");
                     tfphone.setText("");
                     tfemail.setText("");
                     tfpassword.setText("");
                     tfconfirm.setText("");

                 }
             }

         });

         btcancel.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose();
             }
         });

         setVisible(true);
     }

    public static void main(String[] args) {

        Registeration regiter =new Registeration(null);
    }
}
