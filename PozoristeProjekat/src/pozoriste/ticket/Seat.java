package pozoriste.ticket;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pozoriste.user.UserFunctions;

public class Seat extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3992834502915082078L;

	private boolean isSelected;
	private int row, col;

	public Seat(int row, int column, boolean notFree) {
		col = column;
		this.row = row;
		add(new JLabel("R: " + row + " K:" + column));
		JCheckBox jcb = new JCheckBox();
		setBackground(new Color(50, 240, 50));

		add(jcb);
		if (notFree)
			setBackground(Color.RED);

		if (notFree || UserFunctions.getCurrentUser().getType().equals("admin")) {
			jcb.setEnabled(false);
		}



		jcb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isSelected = jcb.isSelected();
			}
		});
	}

	public boolean isSelected() {
		return isSelected;
	}

	public int getRow() {
		return row-1;
	}

	public int getColumn() {
		return col-1;
	}
}
