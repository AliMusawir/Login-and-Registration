import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JDialog{
    private JTextField tfemail;
    private JTextField tfpassword;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel Jlogin;
    public User user;
    public String globle;

    public Login (JFrame parent)
    {
        setTitle("Registeration");
        setContentPane(Jlogin);
        setMinimumSize(new Dimension(459,450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Registeration rg=new Registeration(null);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email=tfemail.getText();
                String password=tfpassword.getText();
                if( email.isEmpty() || password.isEmpty() ) {
                    JOptionPane.showMessageDialog(Login.this,"fill the all fields ","Try Again",JOptionPane.ERROR_MESSAGE);

                }
          else {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "soomro");
                        Statement stmt = conn.createStatement();
                        String sql = "SELECT * FROM registeration  WHERE EmailL=? AND Password=?";
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, email);
                        preparedStatement.setString(2, password);

                        ResultSet resultSet =preparedStatement.executeQuery();
                        if (resultSet.next() )
                        {
                            try {

                                Statement stmts = conn.createStatement();
                                String mysql = "Select Name From registeration  where Emaill=?";
                                PreparedStatement preparedStatements = conn.prepareStatement(mysql);
                                preparedStatements.setString(1,email);
                                ResultSet resultSets =preparedStatements.executeQuery();
                                if(resultSets.next())
                                {
                                    user =new User();
                                   globle= user.name=resultSets.getString("Name");
                                }

                            }
                            catch (Exception r)
                            {
                                System.out.println(r);
                            }
                            dispose();

                        }
                        else{
                            JOptionPane.showMessageDialog(Login.this,"Email or Password is invalid","Try Again",JOptionPane.ERROR_MESSAGE);


                        }


                        stmt.close();
                        conn.close();
                    } catch (Exception x) {
                        System.err.println("Got an exception!");
                        // printStackTrace method
                        // prints line numbers + call stack
                        x.printStackTrace();
                        // Prints what exception has been thrown
                        System.out.println(e);
                    }
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        Login lg =new Login(null);
        User user=lg.user;
        if(user !=null)
        {
            System.out.println("Successful Athentication " );
            System.out.println( "First name = "+ user.name);
        }
        else
        {
            System.out.println("Not Athentication ");
        }
    }
}