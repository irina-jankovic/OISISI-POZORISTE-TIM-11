package pozoriste.report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import pozoriste.MainWindow;
import pozoriste.show.ShowsTable;

public class ReportTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4455631963210013570L;

	public ReportTable(String title, Report r, float total) {

		JLabel totallabel = new JLabel("Ukupno: " + total);
		setLayout(new BorderLayout());
		Color backgroundColor = new Color(240, 240, 240);

		JButton back = new JButton("Nazad na predstave");
		back.setFont(new Font("Tahoma", Font.PLAIN, 16));
		back.setBackground(backgroundColor);

		JPanel lp = new JPanel();
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.back();
			}
		});
		lp.add(totallabel);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		totallabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		totallabel.setFont(new Font("arial", Font.BOLD, 18));

		lp.add(back);

		lp.setLayout(new BoxLayout(lp, BoxLayout.Y_AXIS));
		add(lp, BorderLayout.SOUTH);
		JTable table = new JTable();
		table.setRowHeight(22);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new TableModelForReports(r));
		table.getTableHeader().setFont(new Font("arial", Font.BOLD, 14));

		JScrollPane tablepane = new JScrollPane(table);
		add(tablepane);

		table.setAutoCreateRowSorter(true);

		setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel top = new JPanel();
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("arial", Font.BOLD, 22));

		top.add(titleLabel);
		add(top, BorderLayout.NORTH);
	}

}