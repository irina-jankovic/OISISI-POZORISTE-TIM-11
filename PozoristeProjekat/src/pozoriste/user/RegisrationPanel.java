package pozoriste.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pozoriste.MainWindow;

public class RegisrationPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -2379583815311076207L;

	public RegisrationPanel() {

		Color backgroundColor = new Color(240, 240, 240);// for inputs
		setLayout(new GridBagLayout());
		JPanel fields = new JPanel(new GridLayout(6, 2));

		fields.setBackground(Color.lightGray);// for small panel

		setBackground(new Color(200, 200, 200));

		// dugme za registraciju
		JButton registerBtn = new JButton("Registruj se");
		registerBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		registerBtn.setBackground(backgroundColor);

		JLabel usernameLabel = new JLabel("Korisnicko ime:");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(usernameLabel);

		JTextField usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(usernameField);

		JLabel firstName = new JLabel("Ime:");
		firstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(firstName);

		JTextField firstNameField = new JTextField();
		firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(firstNameField);
		firstNameField.setBackground(backgroundColor);

		JLabel lastName = new JLabel("Prezime:");
		lastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(lastName);

		JTextField lastNameField = new JTextField();
		lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(lastNameField);
		lastNameField.setBackground(backgroundColor);

		usernameField.setBackground(backgroundColor);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Lozinka:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(lblPassword);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setColumns(10);

		passwordField.setBackground(backgroundColor);
		fields.add(passwordField);

		JLabel lblPassword2 = new JLabel("Ponovljena Lozinka:");
		lblPassword2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(lblPassword2);

		JPasswordField passwordField2 = new JPasswordField();
		passwordField2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField2.setColumns(10);

		passwordField2.setBackground(backgroundColor);
		fields.add(passwordField2);

		JButton back= new JButton("Nazad");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.setView(new LoginPanel());
			}
		});
		fields.add(back);

		fields.add(registerBtn);
		add(fields);

		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = usernameField.getText();
				if (username.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Korisnicko ime nije uneto");
					return;
				}

				String firstname = firstNameField.getText();
				if (firstname.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Ime nije uneto");
					return;

				}

				String lastname = lastNameField.getText();
				if (lastname.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Prezime nije uneto");
					return;

				}

				String password1 = passwordField.getText();
				if (password1.equals("")) {
					JOptionPane.showMessageDialog(null, "Lozinka nije uneta");
					return;

				}

				String password2 = passwordField2.getText();
				if (!password1.equals(password2)) {
					JOptionPane.showMessageDialog(null, "Lozinke se ne poklapaju");
					return;

				}
				User u= new User();
				u.setFirstName(firstname);
				u.setLastName(lastname);
				u.setPassword(password1);
				u.setUsername(username);
				if(UserFunctions.register(u)) {
					MainWindow.setView(new LoginPanel());
				}else {
					JOptionPane.showMessageDialog(null, "Korisnicko ime je zauzeto");

				}

			}
		});
	}

}
