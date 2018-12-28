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
	JFrame f = new JFrame("患者管理");
	JButton b1 = new JButton("录入");
	JButton b2 = new JButton("修改");
	JButton b3 = new JButton("删除");
	JButton b4 = new JButton("查询所有");
	JButton b5 = new JButton("返回");
	JTextField tf1 = new JTextField(8);
	JTextField tf2 = new JTextField(8);
	JTextField tf3 = new JTextField(8);
	JTextField tf4 = new JTextField(7);

	JTextField tf5 = new JTextField(8);
	JTextField tf6 = new JTextField(8);
	JTextField tf7 = new JTextField(8);
	JTextField tf8 = new JTextField(7);
	//String[] cloum = { "职工号", "姓名", "津贴", "月基本工资", "月薪" };
	String[] cloum = { "病人姓名", "性别", "入院时间","所属科室", "病人状况", "主治医生","房间号","病床号"};
	Object[][] row = new Object[50][8];
	JTable table = new JTable(row, cloum);
	DatePicker datepick;
	//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

	JScrollPane scrollpane = new JScrollPane(table);	
	
	//table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

	JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private static DatePicker getDatePicker() {
        final DatePicker datepick;
        // 格式
        String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);

        Dimension dimension = new Dimension(177, 24);

        int[] hilightDays = { 1, 3, 5, 7 };

        int[] disabledDays = { 4, 6, 5, 9 };
    //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(date, DefaultFormat, font, dimension);

        datepick.setLocation(137, 83);//设置起始位置
        /*
        //也可用setBounds()直接设置大小与位置
        datepick.setBounds(137, 83, 177, 24);
        */
        // 设置一个月份中需要高亮显示的日子
        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CANADA);
        // 设置时钟面板可见
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
		p.add(new JLabel("病人姓名"));
		p.add(tf1);
		p.add(new JLabel("    性别"));
		p.add(tf2);
		p.add(new JLabel("入院时间"));
		p.add(datepick);
		p.add(new JLabel("病人所属科室"));
		p.add(tf4);
		p.add(new JLabel("病人状况"));
		p.add(tf5);
		p.add(new JLabel("主治医生"));
		p.add(tf6);
		p.add(new JLabel("  房间号"));
		p.add(tf7);
		p.add(new JLabel("  病人病床号"));
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
		f.setResizable(true);// 可以调整界面大小
		f.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (b1.equals(e.getSource())) { // 录入
			String str = "INSERT INTO patient (name,gender,time,de,state,doctor,housenumber,number)"
					+ "VALUES('" + tf1.getText() + "','" + tf2.getText()+"','" + datepick.getText()
					+ "','" + tf4.getText() +"','" + tf5.getText()
					+"','" + tf6.getText()+"','" + tf7.getText()
					+"','" + tf8.getText()+"');";
			if(conmysql.judgesql(this, tf8.getText(), "未占用"))
			{
				System.out.println(str);
				conmysql.sqlexe(this,str,"录入成功!","录入失败!");
				conmysql.consql(this,tf1.getText(),"已占用");
			}

		}

		if (b2.equals(e.getSource())) {// 修改
			
			if(tf1.getText().isEmpty())JOptionPane.showMessageDialog(this, "连接数据库失败！");
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
					
					if(conmysql.judgesql(this, tf8.getText(), "未占用"))
					{
						conmysql.consql(this,tf1.getText(),"未占用");
						conmysql.sqlexe(this,str,"修改成功!","修改失败!");
						conmysql.consql(this,tf1.getText(),"已占用");
					}
				}
				else
					conmysql.sqlexe(this,str,"修改成功!","修改失败!");
				
			}
		}

		if (b3.equals(e.getSource())) {// 删除
			
			String str="DELETE  FROM  patient where name='" + tf1.getText() + "';";
			conmysql.consql(this,tf1.getText(),"未占用");
			conmysql.sqlexe(this, str, "删除成功", "删除失败");
		}
		if (b4.equals(e.getSource())) {// 查询全部
			String tmp="select *  from patient";
			if(!flag)tmp+=" where doctor='"+str+"';";
			System.out.println(tmp);
			conmysql.sqlsel(this, table, tmp,8);
		}
		if (b5.equals(e.getSource())) {// 返回
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