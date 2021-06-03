package pozoriste.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginPanel extends JPanel {
	private JTextField usernameField;
	private JPasswordField passwordField;

	public LoginPanel() {
		
		Color backgroundColor=new Color(240, 240, 240);//for inputs 
		setLayout(new GridBagLayout());
		JPanel fields = new JPanel(new GridLayout(3, 2));

		fields.setBackground(Color.lightGray);//for small panel
		
		setBackground(new Color(200, 200, 200));
		
		//login dugme
		JButton loginBtn = new JButton("Uloguj se");
		loginBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		loginBtn.setBackground(backgroundColor);

		
		JLabel usernameLabel = new JLabel("Korisnicko ime:");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(usernameLabel);

		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(usernameField);

		usernameField.setBackground(backgroundColor);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Sifra:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setColumns(10);

		passwordField.setBackground(backgroundColor);
		fields.add(passwordField);
		
		fields.add(new JLabel());

		fields.add(loginBtn);
		add(fields);


		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				                
		            if(UserFunctions.login(usernameField.getText(), passwordField.getText())) {
				  //TODO prikazati login stranicu
		            }else {
	         	         JOptionPane.showMessageDialog(null, "Pogresni podaci");
				}
			}
		});
	}

}
