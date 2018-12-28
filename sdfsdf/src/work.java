import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class work extends JFrame implements ActionListener {
	work()
	{conmysql.sqlsel(this, table, "select *  from work",len);}
	public static void main(String[] args) {
		 try
		    {
			 	BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
		        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		        
		    }
		    catch(Exception e)
		    {
		        //TODO exception
		    }
		 work dome = new work();
		dome.create();
	}
	
	JFrame f = new JFrame("��λ��ѯ");
	JButton b1 = new JButton("����¸�λ");
	JButton b3 = new JButton("ɾ����λ��Ϣ");
	JButton b4 = new JButton("��ѯ���и�λ��Ϣ");
	JButton b5 = new JButton("����");
	
	String[] enss = {"workid","divisionid","workname"};
	int len=enss.length;
	JTextField tf[] = new JTextField[len];
	String[] cloum = { "�������", "���ű��","��λ����"};
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
		p1.add(b1);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		JPanel p2 = new JPanel();
		//p2.setBackground(Color.cyan);
		p2.add(scrollpane);
		p.setLayout(new FlowLayout());
		p.add(new JLabel(""));
		
		
		for(int i=0;i<len;i++)
		{
			tf[i] =new JTextField(10);
			p.add(new JLabel(cloum[i]));
			p.add(tf[i]);
		}
		
		splitpane.add(p1, splitpane.TOP);
		splitpane.add(p2, splitpane.BOTTOM);
		splitpane.setDividerLocation(50);
		//p.setBackground(Color.CYAN);
		b1.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		f.setBounds(550, 70, 530, 650);
		f.setResizable(true);
// ���Ե��������С   
		f.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (b1.equals(e.getSource())) { // �����Ա����Ϣ
			
			String str = "INSERT INTO work (workid,divisionid,workname)"
					+ "VALUES(";
			for(int i=0;i<len;i++)
			{				
				if(i!=0)str+=",";
				str+="'"+tf[i].getText()+"'";
			}
			str+=");";
			System.out.println(str);
			conmysql.sqlexe(this,str,"¼��ɹ�!","¼��ʧ��!");
		}
		
		if (b3.equals(e.getSource())) {// ɾ��Ա����Ϣ 
			String str="DELETE  FROM  work where workid='" + tf[0].getText() + "';";
			conmysql.sqlexe(this, str, "ɾ���ɹ�", "ɾ��ʧ��");
		}
		if (b4.equals(e.getSource())) {// ��ѯȫ��Ա����Ϣ 
			conmysql.sqlsel(this, table, "select *  from work",len);
		}
		if (b5.equals(e.getSource())) {// ����
			Salarymanageface gl = new Salarymanageface();
			gl.create();
			f.dispose();
		}
	}
}