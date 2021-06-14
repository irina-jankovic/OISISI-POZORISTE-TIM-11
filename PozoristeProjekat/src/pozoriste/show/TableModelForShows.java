package pozoriste.show;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import pozoriste.user.UserFunctions;

public class TableModelForShows extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> columns = new ArrayList<String>();

	public TableModelForShows() {
		columns.add("Ime");
		columns.add("Datum");
		columns.add("Cena");
		columns.add("Rasprodato");
		columns.add("");// SHOW DETAILS
		try {
			if (UserFunctions.getCurrentUser().getType().equals("admin"))
				columns.add(""); // EDIT ON LY FOR ADMIN
		} catch (Exception e) {
		}

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex >= 4;
	}

	@Override
	public int getRowCount() {
		return ShowFunctions.getShows().size();
	}

	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public String getColumnName(int column) {
		return columns.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 2)
			return Float.class;
		return String.class;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Show show = ShowFunctions.getShows().get(r);
		switch (c) {
			case 0:
				return show.getName();
			case 1:
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				return df.format(show.getDate());
			case 2:
				return show.getPrice();
			case 3:
				return show.isSold() ? "Da" : "Ne";
			case 4:
				return "Vise informacija";
			case 5:
				return "Izmeni";

		}
		return null;
	}

}
