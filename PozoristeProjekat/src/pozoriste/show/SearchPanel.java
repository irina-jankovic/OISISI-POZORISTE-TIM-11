package pozoriste.show;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;

public class SearchPanel extends JPanel {

	/**
	 * 
	 */
	private JTextField text = null;
	private JTextField text2 = null;
	private JSpinner dateField2 = null;
	private JSpinner dateField = null;
	private static final long serialVersionUID = 2375701079873664880L;

	public SearchPanel(JTable table) {
		JComboBox<String> combo = new JComboBox<>();
		combo.addItem("Naziv");
		combo.addItem("Cena");
		combo.addItem("Datum");
		add(combo);

		JPanel inputs = new JPanel();
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputs.removeAll();
				if (combo.getSelectedIndex() == 0) {
					text = new JTextField();
					inputs.add(text);
					text.setPreferredSize(new Dimension(310, 30));
				} else if (combo.getSelectedIndex() == 1) {
					text = new JTextField();
					inputs.add(text);
					text.setPreferredSize(new Dimension(150, 30));

					text2 = new JTextField();
					inputs.add(text2);
					text2.setPreferredSize(new Dimension(150, 30));
				} else {

					JCheckBox c1 = new JCheckBox("", true);
					JCheckBox c2 = new JCheckBox("", true);

					dateField = new JSpinner(new SpinnerDateModel());
					JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(dateField, "yyyy-MM-dd HH:mm");
					dateField.setEditor(timeEditor);
					dateField.setValue(new Date());

					dateField2 = new JSpinner(new SpinnerDateModel());
					JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(dateField2, "yyyy-MM-dd HH:mm");
					dateField2.setEditor(timeEditor2);
					dateField2.setValue(new Date());
					inputs.add(c1);

					inputs.add(dateField);
					inputs.add(c2);

					inputs.add(dateField2);

					dateField.setPreferredSize(new Dimension(125, 30));

					dateField2.setPreferredSize(new Dimension(125, 30));
					c1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							dateField.setEnabled(c1.isSelected());
						}
					});

					c2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							dateField2.setEnabled(c2.isSelected());
						}
					});

				}

				JButton button = new JButton("Filtriraj");

				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (combo.getSelectedIndex() == 0) {
							((DefaultRowSorter) table.getRowSorter()).setRowFilter(nameFilter(text.getText()));
						} else if (combo.getSelectedIndex() == 1) {
							float min = Float.MIN_VALUE;
							float max = Float.MAX_VALUE;
							try {
								min = Float.parseFloat(text.getText());
							} catch (Exception ex) {
							}
							try {
								max = Float.parseFloat(text2.getText());
							} catch (Exception ex) {
							}
							((DefaultRowSorter) table.getRowSorter()).setRowFilter(priceFilter(min, max));
						} else {

							Date dateMin = ((Date) dateField.getValue());
							Date dateMax = ((Date) dateField2.getValue());
							Date minD = dateField.isEnabled() ? dateMin : null;
							Date maxD = dateField2.isEnabled() ? dateMax : null;
							((DefaultRowSorter) table.getRowSorter()).setRowFilter(dateFilter(minD, maxD));

						}
					}
				});

				Color backgroundColor = new Color(240, 240, 240);

				button.setFont(new Font("Tahoma", Font.PLAIN, 16));
				button.setBackground(backgroundColor);

				inputs.add(button);
				inputs.revalidate();
				inputs.repaint();
			}
		});
		add(inputs);
		combo.setSelectedIndex(0);

	}

	// filter za stringove, regex ingoring case
	public RowFilter nameFilter(String text) {
		return RowFilter.regexFilter("(?i)" + text, 0);
	}

	// filter za cenu, zadrzava redove gde je cena izmedju min i max, kod sa neta
	public static RowFilter<Object, Object> priceFilter(float min, float max) {
		return new RowFilter<Object, Object>() {
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				TableModelForShows t = (TableModelForShows) entry.getModel();
				float price = (float) t.getValueAt((int) entry.getIdentifier(), 2);
				return price >= min && price <= max;
			}
		};

	}

	// filter za datum, kao ovaj iznad
	public static RowFilter<Object, Object> dateFilter(Date min, Date max) {
		return new RowFilter<Object, Object>() {
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				TableModelForShows t = (TableModelForShows) entry.getModel();
				String date = (String) t.getValueAt((int) entry.getIdentifier(), 1);
				Date dateDate;
				try {
					dateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);

					boolean minB = min == null || !dateDate.before(min);
					boolean maxB = max == null || !dateDate.after(max);

					return maxB && minB;
				} catch (ParseException e) {
				}
				return true;
			}
		};

	}

}
