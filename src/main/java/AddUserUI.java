import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public JFrame view;

    public JButton btnAdd = new JButton("Login");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtUserID = new JTextField(20);
    public JTextField txtPassword = new JTextField(20);

    public boolean running = true;


    public AddUserUI() {
        this.view = new JFrame();

        view.setTitle("User Login");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("User ID "));
        line1.add(txtUserID);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Password "));
        line2.add(txtPassword);
        view.getContentPane().add(line2);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAdd);
        panelButtons.add(btnCancel);
        view.getContentPane().add(panelButtons);

        btnAdd.addActionListener(new AddButtonListener());

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Canceling");
                view.setVisible(false);
            }
        });

    }

    public void run() {
        view.setVisible(true);
    }

    class AddButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();

            String id = txtUserID.getText();
            try {
                user.mUserID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID is invalid!");
                return;
            }


            String password = txtPassword.getText();
            if (password.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty!");
                return;
            }

            user.mPassword = password;

            UserModel login = StoreManager.getInstance().getDataAdapter().loadUser(Integer.parseInt(id));

            if (login.mPassword.equals(user.mPassword)){
                JOptionPane.showMessageDialog(null, "Welcome " + user.mUserID + "!!!");
                running = false;
                view.setVisible(false);
                MainUI ui = new MainUI();
                ui.view.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "Login failed :(");
                view.setVisible(false);
                running = false;

            }

        }
    }

}
