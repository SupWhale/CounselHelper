import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Expression;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CounselSearch extends JTabbedPane{
	private JTextField textField_query;
	private JTable table;
	private JButton Button_Search;
	private JComboBox comboBox_queryField;
	public CounselSearch() {
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(600, 700));
		addTab("상담 검색", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_South = new JPanel();
		panel.add(panel_South, BorderLayout.SOUTH);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(panel_South, popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("상세내용");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String v = "상담내용\n\n"+table.getModel().getValueAt(row, 5);
				JOptionPane.showMessageDialog(CounselSearch.this, v);
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JLabel lblPage = new JLabel("Page 0 / 0");
		panel_South.add(lblPage);
		
		JButton button_prev = new JButton("이전");
		panel_South.add(button_prev);
		
		JButton button_next = new JButton("다음");
		panel_South.add(button_next);
		
		JSlider slider = new JSlider();
		panel_South.add(slider);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		comboBox_queryField = new JComboBox();
		comboBox_queryField.setModel(new DefaultComboBoxModel(new String[] {"상담일시", "이름", "거주지역","성별", 
				"외국인 여부","상담 내용"}));
		panel_1.add(comboBox_queryField);
		
		textField_query = new JTextField();
		panel_1.add(textField_query);
		textField_query.setColumns(10);
		
		Button_Search = new JButton("상담 검색");
		Button_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Searchmemo();
			}
		});
		panel_1.add(Button_Search);
		
		JScrollPane scrollPane_Center = new JScrollPane();
		panel.add(scrollPane_Center, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()!=MouseEvent.BUTTON1) return;
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"상담일시", "상담자 이름", "거주지역", "성별", "외국인 여부", "상담 내용"
			}
		));
		scrollPane_Center.setViewportView(table);
	}
	protected void Searchmemo() {
		int queryFieldIndex = comboBox_queryField.getSelectedIndex();
		String query = textField_query.getText();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		try {
		FileReader fr = new FileReader("memo.txt");
		BufferedReader iF = new BufferedReader(fr);
		while(true) {
			String line = iF.readLine();
			if(line == null) break;
			String v[]=line.split("\t");
			if(v[queryFieldIndex].contains(query)==false) continue;
			model.addRow(v);
		}
		iF.close();
		table.setModel(model);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
