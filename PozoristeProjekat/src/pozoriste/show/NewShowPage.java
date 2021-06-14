package pozoriste.show;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import pozoriste.MainWindow;

public class NewShowPage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2379583815311076207L;

	public NewShowPage(Show s) {

		Color backgroundColor = new Color(240, 240, 240);// for inputs
		setLayout(new GridBagLayout());
		JPanel fields = new JPanel(new GridLayout(5, 2));
		fields.setPreferredSize(new Dimension(400, 150));
		fields.setBackground(Color.lightGray);// for small panel

		setBackground(new Color(200, 200, 200));

		JButton addBtn = new JButton(s == null ? "Dodaj" : "Izmeni");
		addBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		addBtn.setBackground(backgroundColor);

		JLabel nameLabel = new JLabel("Naziv:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(nameLabel);

		JTextField nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameField.setBackground(backgroundColor);
		fields.add(nameField);

		JLabel descriptionLable = new JLabel("Opis:");
		descriptionLable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(descriptionLable);

		JTextArea descriptionField = new JTextArea();

		descriptionField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descriptionField.setBackground(backgroundColor);
		fields.add(new JScrollPane(descriptionField));

		JLabel priceLabel = new JLabel("Cena:");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(priceLabel);

		JTextField priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceField.setBackground(backgroundColor);
		fields.add(priceField);

		JLabel dateLabel = new JLabel("Datum:");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(dateLabel);

		JSpinner dateField = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(dateField, "yyyy-MM-dd HH:mm");
		dateField.setEditor(timeEditor);
		dateField.setValue(new Date());
		dateField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		timeEditor.getComponent(0).setBackground(backgroundColor);
		fields.add(dateField);

		JButton back = new JButton("Nazad");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.setView(new ShowsTable());
			}
		});
		fields.add(back);

		fields.add(addBtn);
		add(fields);

		if (s != null) {
			nameField.setText(s.getName());
			descriptionField.setText(s.getDescription());
			dateField.setValue(s.getDate());
			priceField.setText("" + s.getPrice());
		}

		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String title = nameField.getText();
				if (title.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Naziv nije unet");
					return;
				}

				String description = descriptionField.getText();
				if (description.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Opis nije unet");
					return;

				}

				String price = priceField.getText();
				if (price.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Cena nije uneta");
					return;
				}
				try {
					Float.parseFloat(price);
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "Cena nije u ispravnom formatu");
					return;
				}

				Date date = ((Date) dateField.getValue());
				if (date.before(new Date())) {
					JOptionPane.showMessageDialog(null, "Datum je u proslosti");
					return;
				}

				Show mewS = s != null ? s : new Show();
				mewS.setDate(date);
				mewS.setDescription(description);
				mewS.setPrice(Float.parseFloat(price));
				mewS.setName(title);
				if (s != null) ShowFunctions.update(s);
				else
					ShowFunctions.addShow(mewS);
				MainWindow.setView(new ShowsTable());

			}
		});
	}

}
