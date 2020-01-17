import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CountselInput extends JTabbedPane{
	private JTextField textField_name;
	private JTextArea textArea_memo;
	private JComboBox comboBox_address;
	private JRadioButton radioButton_male;
	private JRadioButton radioButton_female;
	private JTextArea textArea_memo_1;
	private JButton button_save;
	private JButton button_clear;
	private JCheckBox checkbox_foreigner;
	public CountselInput() {
		setSize(new Dimension(869, 561));
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(400, 300));
		addTab("상담입력", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_North = new JPanel();
		panel.add(panel_North, BorderLayout.NORTH);
		
		JLabel label_namePrompt = new JLabel("상담신청자         이름");
		label_namePrompt.setHorizontalAlignment(SwingConstants.LEFT);
		label_namePrompt.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_North.add(label_namePrompt);
		
		textField_name = new JTextField();
		
		panel_North.add(textField_name);
		textField_name.setColumns(10);
		
		JLabel label_addressPrompt = new JLabel("거주지역");
		panel_North.add(label_addressPrompt);
		
		JLabel label_genderPrompt = new JLabel("성별");
		panel_North.add(label_genderPrompt);
		
		comboBox_address = new JComboBox();
		comboBox_address.setModel(new DefaultComboBoxModel(new String[] {"서울", "부산", "기타"}));
		panel_North.add(comboBox_address);
		
		radioButton_male = new JRadioButton("남자");
		panel_North.add(radioButton_male);
		
		radioButton_female = new JRadioButton("여자");
		panel_North.add(radioButton_female);
		ButtonGroup buttonGroup = new ButtonGroup();

		buttonGroup.add(radioButton_female);
		buttonGroup.add(radioButton_male);
		
		
		checkbox_foreigner = new JCheckBox("외국인 여부");
		panel_North.add(checkbox_foreigner);
		
		JPanel panel_South = new JPanel();
		panel.add(panel_South, BorderLayout.SOUTH);
		
		button_clear = new JButton("새 상당 입력");
		panel_South.add(button_clear);
		
		button_save = new JButton("상담 저장");
		panel_South.add(button_save);
		
		
		JPanel panel_Center = new JPanel();
		panel.add(panel_Center, BorderLayout.CENTER);
		panel_Center.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_Center.add(scrollPane, BorderLayout.CENTER);
		
		JLabel label_memoPrompt = new JLabel("상담내용");
		label_memoPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(label_memoPrompt);
		
		textArea_memo_1 = new JTextArea();
		textArea_memo_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		scrollPane.setViewportView(textArea_memo_1);
	
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveMemo();
				}
			
		});
	}
	protected void saveMemo() {
		String name=textField_name.getText();
		if(name.equals("")) {
			message("이름을 입력하세요"); return;
		}
		String memo = textArea_memo_1.getText();
		if(memo.equals("")) { message("상담 내용을 입력하세요"); return; }
		memo = memo.replaceAll("\\s+", "");
		String address=(String)comboBox_address.getSelectedItem();
		if(address.equals("")) { message("주소를 입력하세요"); return; }
		if(!radioButton_male.isSelected()&&!radioButton_female.isSelected())
			{ message("성별을 입력하세요"); return; }
		String gender = radioButton_female.isSelected()? "여자":"남자";		
		String foreigner = checkbox_foreigner.isSelected()? "외국인" : "한국인";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss (E)");
		String date = sdf.format(new Date());
		String v = date +"\t" + name + "\t" + address +"\t" + gender +"\t" + foreigner +"\t" + memo ; 
		
		
		try {
			FileWriter oF = new FileWriter("memo.txt", true);
			oF.write(v+"\n");
			oF.close();
			message("상담 저장 완료");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void message(String s) {
		JOptionPane.showMessageDialog(this, s);
	}
}
