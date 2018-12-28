import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Locale;

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

import com.eltima.components.ui.DatePicker;

import javafx.scene.control.SplitPane;

public class patientmanage extends JFrame implements ActionListener {
	String str;
	boolean flag=true;
	patientmanage(String strstr)
	{
		str=strstr;
		flag=false;
		String tmp="select *  from patient";
		if(!flag)tmp+=" where doctor='"+str+"';";
		System.out.println(tmp);
		conmysql.sqlsel(this, table, tmp,8);
	}
	patientmanage(){
		String tmp="select *  from patient";
		if(!flag)tmp+=" where doctor='"+str+"';";
		System.out.println(tmp);
		conmysql.sqlsel(this, table, tmp,8);
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
		patientmanage dome = new patientmanage();
		dome.create();
	}
	JFrame f = new JFrame("���߹���");
	JButton b1 = new JButton("¼��");
	JButton b2 = new JButton("�޸�");
	JButton b3 = new JButton("ɾ��");
	JButton b4 = new JButton("��ѯ����");
	JButton b5 = new JButton("����");
	JTextField tf1 = new JTextField(8);
	JTextField tf2 = new JTextField(8);
	JTextField tf3 = new JTextField(8);
	JTextField tf4 = new JTextField(7);

	JTextField tf5 = new JTextField(8);
	JTextField tf6 = new JTextField(8);
	JTextField tf7 = new JTextField(8);
	JTextField tf8 = new JTextField(7);
	//String[] cloum = { "ְ����", "����", "����", "�»�������", "��н" };
	String[] cloum = { "��������", "�Ա�", "��Ժʱ��","��������", "����״��", "����ҽ��","�����","������"};
	Object[][] row = new Object[50][8];
	JTable table = new JTable(row, cloum);
	DatePicker datepick;
	//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

	JScrollPane scrollpane = new JScrollPane(table);	
	
	//table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

	JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private static DatePicker getDatePicker() {
        final DatePicker datepick;
        // ��ʽ
        String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
        // ��ǰʱ��
        Date date = new Date();
        // ����
        Font font = new Font("Times New Roman", Font.BOLD, 14);

        Dimension dimension = new Dimension(177, 24);

        int[] hilightDays = { 1, 3, 5, 7 };

        int[] disabledDays = { 4, 6, 5, 9 };
    //���췽������ʼʱ�䣬ʱ����ʾ��ʽ�����壬�ؼ���С��
        datepick = new DatePicker(date, DefaultFormat, font, dimension);

        datepick.setLocation(137, 83);//������ʼλ��
        /*
        //Ҳ����setBounds()ֱ�����ô�С��λ��
        datepick.setBounds(137, 83, 177, 24);
        */
        // ����һ���·�����Ҫ������ʾ������
        datepick.setHightlightdays(hilightDays, Color.red);
        // ����һ���·��в���Ҫ�����ӣ��ʻ�ɫ��ʾ
        datepick.setDisableddays(disabledDays);
        // ���ù���
        datepick.setLocale(Locale.CANADA);
        // ����ʱ�����ɼ�
        datepick.setTimePanleVisible(true);
        return datepick;
    }

	void create() {

		JPanel p = (JPanel) f.getContentPane();
		p.setLayout(new FlowLayout());
		//p.add(scrollpane);
		p.add(splitpane);

		JPanel p1 = new JPanel();
		if(flag)p1.add(b1);
		p1.add(b2);
		if(flag)p1.add(b3);
		p1.add(b4);
		p1.add(b5);

		JPanel p2 = new JPanel();

		//p2.setBackground(Color.cyan);
		p2.add(scrollpane);
		//p2.add(scrollbar);

		datepick = getDatePicker();
		//p.add(datepick);
		
		JPanel p3 = new JPanel();

		p.setLayout(new FlowLayout());
		p.add(new JLabel(""));
		p.add(new JLabel("��������"));
		p.add(tf1);
		p.add(new JLabel("    �Ա�"));
		p.add(tf2);
		p.add(new JLabel("��Ժʱ��"));
		p.add(datepick);
		p.add(new JLabel("������������"));
		p.add(tf4);
		p.add(new JLabel("����״��"));
		p.add(tf5);
		p.add(new JLabel("����ҽ��"));
		p.add(tf6);
		p.add(new JLabel("  �����"));
		p.add(tf7);
		p.add(new JLabel("  ���˲�����"));
		p.add(tf8);
		splitpane.add(p1, splitpane.TOP);
		splitpane.add(p2, splitpane.BOTTOM);
		splitpane.setDividerLocation(50);
		//p.setBackground(Color.CYAN);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);

		f.setBounds(600, 100, 530, 650);
		f.setResizable(true);// ���Ե��������С
		f.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (b1.equals(e.getSource())) { // ¼��
			String str = "INSERT INTO patient (name,gender,time,de,state,doctor,housenumber,number)"
					+ "VALUES('" + tf1.getText() + "','" + tf2.getText()+"','" + datepick.getText()
					+ "','" + tf4.getText() +"','" + tf5.getText()
					+"','" + tf6.getText()+"','" + tf7.getText()
					+"','" + tf8.getText()+"');";
			if(conmysql.judgesql(this, tf8.getText(), "δռ��"))
			{
				System.out.println(str);
				conmysql.sqlexe(this,str,"¼��ɹ�!","¼��ʧ��!");
				conmysql.consql(this,tf1.getText(),"��ռ��");
			}

		}

		if (b2.equals(e.getSource())) {// �޸�
			
			if(tf1.getText().isEmpty())JOptionPane.showMessageDialog(this, "�������ݿ�ʧ�ܣ�");
			else
			{
				String str= "UPDATE patient SET  ";
				if(!tf2.getText().isEmpty())str+="gender='"+tf2.getText()+"',";
				if(!tf3.getText().isEmpty())str+="time='"+tf3.getText()+"',";
				if(!tf4.getText().isEmpty())str+="de='"+tf4.getText()+"',";
				if(!tf5.getText().isEmpty())str+="state='"+tf5.getText()+"',";
				if(!tf6.getText().isEmpty())str+="doctor='"+tf6.getText()+"',";
				if(!tf7.getText().isEmpty())str+="housenumber='"+tf7.getText()+"',";
				if(!tf8.getText().isEmpty())str+="number='"+tf8.getText()+"',";
				str=str.substring(0,str.length()-1)+" where name='" + tf1.getText() + "';";
				//System.out.println(str);
				if(!tf8.getText().isEmpty())
				{
					
					if(conmysql.judgesql(this, tf8.getText(), "δռ��"))
					{
						conmysql.consql(this,tf1.getText(),"δռ��");
						conmysql.sqlexe(this,str,"�޸ĳɹ�!","�޸�ʧ��!");
						conmysql.consql(this,tf1.getText(),"��ռ��");
					}
				}
				else
					conmysql.sqlexe(this,str,"�޸ĳɹ�!","�޸�ʧ��!");
				
			}
		}

		if (b3.equals(e.getSource())) {// ɾ��
			
			String str="DELETE  FROM  patient where name='" + tf1.getText() + "';";
			conmysql.consql(this,tf1.getText(),"δռ��");
			conmysql.sqlexe(this, str, "ɾ���ɹ�", "ɾ��ʧ��");
		}
		if (b4.equals(e.getSource())) {// ��ѯȫ��
			String tmp="select *  from patient";
			if(!flag)tmp+=" where doctor='"+str+"';";
			System.out.println(tmp);
			conmysql.sqlsel(this, table, tmp,8);
		}
		if (b5.equals(e.getSource())) {// ����
			if(flag) {
				Salarymanageface gl=new Salarymanageface();
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