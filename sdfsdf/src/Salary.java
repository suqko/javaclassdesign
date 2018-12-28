import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.io.File;
import java.io.IOException;

import jxl.Workbook;
//import jxl.write.Label as ko;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class Salary extends JFrame implements ActionListener {
	boolean flag = true;
	String str;

	Salary(String strstr) {
		str = strstr;
		flag = false;
		conmysql.sqlsel(this, table, "select *  from salary", len);

	}

	public static void writeExcel(JFrame op, String sqlcode) {
		Connection con;
		Statement sql;
		ResultSet rs;
		String[] titleA = { "id", "salary" };
		File fileA = new File("D:/TestFile.xls");
		if (fileA.exists()) {
			fileA.delete();
		}
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(op, "�������ݿ�ʧ�ܣ�");
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classdesign", "root", "sunpb");
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = sql.executeQuery(sqlcode);
			int k = 0;

			try {
				fileA.createNewFile();
				// ����������
				WritableWorkbook workbookA = Workbook.createWorkbook(fileA);
				WritableSheet sheetA = workbookA.createSheet("sheet1", 0);
				Label labelA = null;
				// ��������
				for (int i = 0; i < titleA.length; i++) {
					jxl.write.Label labelA1 = new jxl.write.Label(i, 0, titleA[i]);
					sheetA.addCell(labelA1);
				}
				
				while (rs.next()) {
					k++;
					jxl.write.Label label = new jxl.write.Label(0, k, rs.getString(1));
					sheetA.addCell(label);
					jxl.write.Label label2 = new jxl.write.Label(1, k, rs.getString(2));
					sheetA.addCell(label2);

				}
				
				workbookA.write();
				workbookA.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(op, "ʧ�ܣ���");
			}
			JOptionPane.showMessageDialog(op, "��������D�̸�Ŀ¼");
			con.close();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(op, "ʧ�ܣ�");
		}

	}

	public Salary() {
		conmysql.sqlsel(this, table, "select *  from salary", len);

	}

	public static void main(String[] args) {
		
		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

		} catch (Exception e) {
			// TODO exception
		}
		Salary dome = new Salary();
		dome.create();
	}

	JFrame f = new JFrame("���ʹ���");
	JButton b1 = new JButton("��ӹ�����Ϣ");
	JButton b2 = new JButton("�޸Ĺ�����Ϣ");
	JButton b3 = new JButton("ɾ��������Ϣ");
	JButton b4 = new JButton("��ѯ������Ϣ");
	JButton b6 = new JButton("�������");
	JButton b5 = new JButton("����");

	String[] enss = { "id", "salary" };
	int len = enss.length;
	JTextField tf[] = new JTextField[len];
	String[] cloum = { "��λ���", "нˮ" };
	Object[][] row = new Object[50][len];
	JTable table = new JTable(row, cloum);
	JScrollPane scrollpane = new JScrollPane(table);
	JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

	void create() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JPanel p = (JPanel) f.getContentPane();
		p.setLayout(new FlowLayout());
		p.add(scrollpane);
		p.add(splitpane);
		JPanel p1 = new JPanel();
		if (flag)
			p1.add(b1);
		if (flag)
			p1.add(b2);
		if (flag)
			p1.add(b3);
		p1.add(b4);
		p1.add(b6);
		p1.add(b5);
		JPanel p2 = new JPanel();
		// p2.setBackground(Color.cyan);
		p2.add(scrollpane);
		p.setLayout(new FlowLayout());
		p.add(new JLabel(""));

		for (int i = 0; i < len; i++) {
			tf[i] = new JTextField(10);
			p.add(new JLabel(cloum[i]));
			p.add(tf[i]);
		}

		splitpane.add(p1, splitpane.TOP);
		splitpane.add(p2, splitpane.BOTTOM);
		splitpane.setDividerLocation(50);
		// p.setBackground(Color.CYAN);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		f.setBounds(550, 70, 530, 650);
		f.setResizable(true);
// ���Ե��������С   
		f.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (b1.equals(e.getSource())) { // �����Ա����Ϣ

			String str = "INSERT INTO salary (id,salary)" + "VALUES(";
			for (int i = 0; i < len; i++) {
				if (i != 0)
					str += ",";
				str += "'" + tf[i].getText() + "'";
			}
			str += ");";
			System.out.println(str);

			conmysql.sqlexe(this, str, "¼��ɹ�!", "¼��ʧ��!");
		}
		if (b2.equals(e.getSource())) {// �޸�Ա����Ϣ
			if (tf[0].getText().isEmpty())
				JOptionPane.showMessageDialog(this, "������Ա����");
			else {
				String str = "UPDATE salary SET  ";
				for (int i = 1; i < len; i++) {
					if (!tf[i].getText().isEmpty())
						str += enss[i] + "='" + tf[i].getText() + "',";
				}

				str = str.substring(0, str.length() - 1) + " where id='" + tf[0].getText() + "';";
				// System.out.println(str);
				conmysql.sqlexe(this, str, "�޸ĳɹ�!", "�޸�ʧ��!");

			}
		}
		if (b3.equals(e.getSource())) {// ɾ��Ա����Ϣ
			String str = "DELETE  FROM  salary where id='" + tf[0].getText() + "';";
			conmysql.sqlexe(this, str, "ɾ���ɹ�", "ɾ��ʧ��");
		}
		if (b4.equals(e.getSource())) {// ��ѯȫ��Ա����Ϣ
			conmysql.sqlsel(this, table, "select *  from salary", len);
		}
		if (b5.equals(e.getSource())) {// ����
			if (flag) {
				Salarymanageface gl = new Salarymanageface();
				gl.create();
			} else {
				stafflanding gl = new stafflanding(str);
				gl.create();
			}
			f.dispose();
		}
		if (b6.equals(e.getSource())) {// ��ѯȫ��Ա����Ϣ
			writeExcel(this, "select *  from salary");
		}
	}
}