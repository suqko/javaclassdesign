
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.mysql.jdbc.PreparedStatement;

// import Query.Querypad;
class Landingface extends JFrame implements ActionListener {
	
	public Landingface() {
	}
	ImageIcon im = new ImageIcon("img/1.png");
	JLabel a2 = new JLabel(im);
	JFrame frame = new JFrame("登陆");
	JLabel label1 = new JLabel("用户名");
	JLabel label2 = new JLabel("密码");
	JButton logonButton1 = new JButton("管理员登录");
	JButton logonButton2 = new JButton("职工登录");
	JButton cancelButton = new JButton("退出");

	JTextField username = new JTextField(9);
	JPasswordField password = new JPasswordField(9);
	static String t1;
	static String t2;

	void create() {
		JPanel p = (JPanel) frame.getContentPane();
		p.setLayout(null);
		
		
		a2.setBounds(90, 0, 200, 200);
		p.add(a2);
		
		label1.setBounds(60, 240, 50, 20);
		p.add(label1);
		label2.setBounds(60, 280, 50, 20);
		p.add(label2);
		
		username.setBounds(130, 240, 180, 20);
		p.add(username);
		
		password.setBounds(130, 280, 180, 20);
		p.add(password);

		logonButton1.setBounds(80, 320, 100, 30);
		p.add(logonButton1);
		logonButton2.setBounds(220, 320, 100, 30);
		p.add(logonButton2);
		//cancelButton.setBounds(300, 360, 60, 30);
		//p.add(cancelButton);

		//p.setBackground(Color.cyan);
		p.setVisible(true);

		logonButton1.addActionListener(this);
		logonButton2.addActionListener(this);
		cancelButton.addActionListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.setBounds(600, 280, 400, 400);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		t1 = username.getText();
		t2 = password.getText();

		if (e.getSource() == logonButton1) {

			if (username.getText().equals("admin") == true
					&& (password.getText().equals("admin") == true))
			{
				JOptionPane.showMessageDialog(this, "登录成功！");
				Salarymanageface gz = new Salarymanageface();
				gz.create();
				frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "输入用户名或密码错误！");
			}
		}
		if (e.getSource() == logonButton2) {
			try {
				Connection con;
				Statement ps;
				ResultSet rs;
				String sql = null;
				Class.forName("org.gjt.mm.mysql.Driver");
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/classdesign", "root", "sunpb");
				sql = "select * from user where TMS_USER='" + t1 + "' and TMS_PSWD='" + t2 + "'";
				ps = (PreparedStatement)
				con.prepareStatement(sql);
				rs = ps.executeQuery(sql);
				if (rs.next())
				{
					if (rs.getString("TMS_USER").equals(t1) && rs.getString("TMS_PSWD").equals(t2))
					{
						stafflanding yg = new stafflanding(rs.getString("name"));
						yg.create();
						frame.dispose();
						JOptionPane.showMessageDialog(this, "登录成功！");
						this.dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "输入用户名或密码错误！");
				}
			} catch (Exception e1) {
// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (cancelButton.equals(e.getSource())) // 退出
		{
			System.exit(0);
		}
	}
}