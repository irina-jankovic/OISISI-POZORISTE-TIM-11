package pozoriste.report;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModelForReports extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> columns = new ArrayList<String>();

	private Report report;

	public TableModelForReports(Report r) {
		columns.add("ID");
		report = r;
		columns.add("Cena");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return report.getIds().size();
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
		if (columnIndex == 1)
			return Float.class;
		return String.class;
	}

	@Override
	public Object getValueAt(int r, int c) {
		if (c == 0)
			return report.getIds().get(r);
		return report.getPrices().get(r);
	}

}
