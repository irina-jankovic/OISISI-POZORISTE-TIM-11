package pozoriste.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

		fields.add(new JLabel());

		fields.add(registerBtn);
		add(fields);

		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

}
