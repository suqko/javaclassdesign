import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class stafflanding extends JFrame implements ActionListener {

	String str;
	stafflanding(String strstr)
	{
		str = strstr;
	}
	stafflanding(){
	}
	public static void main(String[] args) {
		stafflanding dome = new stafflanding();
		dome.create();
	}
	JFrame f = new JFrame("医院管理系统");
	JButton b1 = new JButton("病人信息查询");
	JButton b2 = new JButton("个人信息管理");
	JButton b3 = new JButton("返回");
	JButton b4 = new JButton("工资报表");
	JButton b5 = new JButton("床位状态查询");

	ImageIcon im = new ImageIcon("img/2.png");
	JLabel a1 = new JLabel(im);

	void create() {
		System.out.println(str);
		JPanel p = (JPanel) f.getContentPane();
		p.setLayout(new FlowLayout());
		a1.setBounds(0, 0, 100, 100);
		p.add(a1);
		p.add(b1);
		p.add(b2);	
		p.add(b4);
		p.add(b5);
		p.add(b3);
		//p.setBackground(Color.cyan);
		p.setVisible(true);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		f.setBounds(650, 280, 280, 350);
		f.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (b5.equals(e.getSource())) {
			bed yg = new bed(str);
			yg.create();
			f.dispose();
		}
		if (b4.equals(e.getSource())) {
			Salary yg = new Salary(str);
			yg.create();
			f.dispose();
		}
		if (b3.equals(e.getSource())) {// 返回
			Landingface d = new Landingface();
			d.create();
			f.dispose();
		}

		if (b2.equals(e.getSource())) {
			modify yg = new modify(str);
			yg.create();
			f.dispose();
		}

		if (b1.equals(e.getSource())) {
			patientmanage dome = new patientmanage(str);
			dome.create();
			f.dispose();
		}
	}
}