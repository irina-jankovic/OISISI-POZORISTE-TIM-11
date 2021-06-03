package pozoriste.show;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ShowsTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4455631963210013570L;

	public ShowsTable() {
		JTable table = new JTable();
		table.setRowHeight(22);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new TableModelForShows());
		table.getTableHeader().setFont(new Font("arial", Font.BOLD, 14));
		add(new JScrollPane(table));
	}

}