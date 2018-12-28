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

public class modify extends JFrame implements ActionListener {
	String namestr;
	boolean flag=true;
	modify(){
		String tmp="select *  from doctor";
		if(!flag)tmp+=" where name='"+namestr+"';";
		System.out.println(tmp);
		conmysql.sqlsel(this, table, tmp,len);
	}
	modify(String strstr){
		namestr=strstr;
		flag=false;
		String tmp="select *  from doctor";
		if(!flag)tmp+=" where name='"+namestr+"';";
		System.out.println(tmp);
		conmysql.sqlsel(this, table, tmp,len);
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
		 modify dome = new modify();
		dome.create();
	}
	
	JFrame f = new JFrame("Ա����Ϣ����");
	JButton b1 = new JButton("�����Ա����Ϣ");
	JButton b2 = new JButton("�޸�Ա����Ϣ");
	JButton b3 = new JButton("ɾ��Ա����Ϣ");
	JButton b4 = new JButton("��ѯ������Ϣ");

	
	JButton b5 = new JButton("����");
	JTextField tf[] = new JTextField[20];
	String[] enss = {"id","name","division","workname","education","gender","birthday","nativeplace",
			"nationality","nation","numberid","marriage","Healthy","time","state","address","telephone","email","workid"};
	int len=enss.length;
	String[] cloum = { "Ա����", "����", "����", "ְ��", "ѧ��", "�Ա�" ,"����","����","����","����","���֤��","����",
			"����״̬","�μӹ���ʱ��","Ա��״̬","��ͥסַ","��ϵ�绰","email","������λ"};
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
		if(flag)p1.add(b2);
		if(flag)p1.add(b3);
		//b4.setName(tmpstr);
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
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		f.setBounds(550, 70, 530, 750);
		f.setResizable(true);
// ���Ե��������С   
		f.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (b1.equals(e.getSource())) { // �����Ա����Ϣ
			
			String str = "INSERT INTO doctor (id,name,division,workname,education,gender,birthday,"
					+ "nativeplace,nationality,nation,numberid,marriage,Healthy,time,state,address,telephone,email,workid)"
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
		if (b2.equals(e.getSource())) {// �޸�Ա����Ϣ
			if(tf[0].getText().isEmpty())JOptionPane.showMessageDialog(this, "������Ա����");
			else
			{
				String str= "UPDATE doctor SET  ";
				for(int i=1;i<len;i++)
				{
					if(!tf[i].getText().isEmpty())str+=enss[i]+"='"+tf[i].getText()+"',";
				}
				
				str=str.substring(0,str.length()-1)+" where id='" + tf[0].getText() + "';";
				//System.out.println(str);
				conmysql.sqlexe(this,str,"�޸ĳɹ�!","�޸�ʧ��!");
				
			}
		}
		if (b3.equals(e.getSource())) {// ɾ��Ա����Ϣ 
			String str="DELETE  FROM  doctor where id='" + tf[0].getText() + "';";
			conmysql.sqlexe(this, str, "ɾ���ɹ�", "ɾ��ʧ��");
		}
		if (b4.equals(e.getSource())) {// ��ѯȫ��Ա����Ϣ 
			String tmp="select *  from doctor";
			if(!flag)tmp+=" where name='"+namestr+"';";
			System.out.println(tmp);
			conmysql.sqlsel(this, table, tmp,len);
		}
		if (b5.equals(e.getSource())) {// ����
			if(flag)
			{
				Salarymanageface gl = new Salarymanageface();
				gl.create();
			}
			else
			{
				stafflanding gl = new stafflanding(namestr);
				gl.create();
			}
			f.dispose();
		}
	}
}