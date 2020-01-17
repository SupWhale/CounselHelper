import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class CounselMain extends JFrame{
	
	CountselInput counselInput = new CountselInput();
	CounselSearch counselSearch = new CounselSearch();
	
	public CounselMain() {
		setSize(new Dimension(682, 470));
		setLocale(getLocale());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("상담관리");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("상담입력");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CounselMain.this.setContentPane(counselInput);
				CounselMain.this.revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("상담 검색");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CounselMain.this.setContentPane(counselSearch);
				CounselMain.this.revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("끝내기");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("도움말");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("사용법");
		mnNewMenu_1.add(mntmNewMenuItem_3);
	}

	public static void main(String[] args) {
		CounselMain counselMain = new CounselMain();
		counselMain.setVisible(true);
	}

}
