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

public class bed extends JFrame implements ActionListener {
	boolean flag=true;
	String str;
	bed(String strstr)
	{
		str=strstr;
		flag=false;
		conmysql.sqlsel(this, table, "select *  from bed",len);
	}
	bed(){
		conmysql.sqlsel(this, table, "select *  from bed",len);
	}
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
		 bed dome = new bed();
		dome.create();
	}
	
	JFrame f = new JFrame("��λ��ѯ");
	JButton b1 = new JButton("����´�λ");
	JButton b3 = new JButton("ɾ����λ��Ϣ");
	JButton b4 = new JButton("��ѯ���д�λ��Ϣ");
	JButton b5 = new JButton("����");
	
	String[] enss = {"id","state"};
	int len=enss.length;
	JTextField tf[] = new JTextField[len];
	String[] cloum = { "��λ���", "��λ״̬"};
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
		if(flag)p1.add(b1);
		if(flag)p1.add(b3);
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
			
			String str = "INSERT INTO bed (id,state)"
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
			String str="DELETE  FROM  bed where id='" + tf[0].getText() + "';";
			conmysql.sqlexe(this, str, "ɾ���ɹ�", "ɾ��ʧ��");
		}
		if (b4.equals(e.getSource())) {// ��ѯȫ��Ա����Ϣ 
			conmysql.sqlsel(this, table, "select *  from bed",len);
		}
		if (b5.equals(e.getSource())) {// ����
			if(flag) {Salarymanageface gl = new Salarymanageface();
			gl.create();
			}
			else
			{
				stafflanding gl = new stafflanding(str);
				gl.create();
			}
			f.dispose();
		}
	}
}