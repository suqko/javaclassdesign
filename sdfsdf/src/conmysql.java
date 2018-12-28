 import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

 public class conmysql  {
	 static boolean judgesql(JFrame op,String number,String state)
	 {
			String consql="select * from bed ";
			Connection con;
			Statement sql;
			try {
				Class.forName("org.gjt.mm.mysql.Driver");
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(op, "连接数据库失败！");
			}

			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classdesign", "root", "sunpb");
				sql = con.createStatement();
				
				
				//System.out.println(consql+"where id='"+number+"';");
				ResultSet rs=sql.executeQuery(consql+"where id='"+number+"';");
				rs.next();
				String tm=rs.getString("state");	
				//System.out.println(tm+"!");
				//System.out.println(state);

				con.close();
				
				if(!state.equals(tm))
				{
					JOptionPane.showMessageDialog(op, "床位状态有误");
					return false;
				}
				else 
					return true;
					
				
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(op, "床位状态有误");
			}
		return false;
		 
	 }
	 static void consql(JFrame op,String name,String state)
	 {
		 	String sqlcode="select *  from patient where name='" + name + "';";
			String consql="UPDATE bed SET state='" + state + "' ";
			Connection con;
			Statement sql;
			try {
				Class.forName("org.gjt.mm.mysql.Driver");
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(op, "连接数据库失败！");
			}

			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classdesign", "root", "sunpb");
				sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				
				//System.out.println(insertStr);
				
				ResultSet rs=sql.executeQuery(sqlcode);
				System.out.println(sqlcode);
				rs.next();
				String number=rs.getString("number");
				System.out.println(number);
				System.out.println(consql+"where id='"+number+"';");
				sql.executeUpdate(consql+"where id='"+number+"';");
				System.out.println(consql+"where id='"+number+"';");
				con.close();
				JOptionPane.showMessageDialog(op, "床位更新成功");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(op, "床位更新失败");
			}
	 }
	 static void sqlexe(JFrame op,String sqlcode,String yes,String no)
		{
			Connection con;
			Statement sql;
			try {
				Class.forName("org.gjt.mm.mysql.Driver");
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(op, "连接数据库失败！");
			}

			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classdesign", "root", "sunpb");
				sql = con.createStatement();
				
				//System.out.println(insertStr);
				sql.executeUpdate(sqlcode);
				con.close();
				JOptionPane.showMessageDialog(op, yes);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(op, no);
			}
		}
	 static void sqlsel(JFrame op,JTable table,String sqlcode,int co)
	 {
		 	Connection con;
			Statement sql;
			ResultSet rs;

			try {
				Class.forName("org.gjt.mm.mysql.Driver");
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(op, "连接数据库失败！");
			}

			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classdesign", "root", "sunpb");
				sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
					

					for (int i = 0; i < 50; i++)
						for (int j = 0; j < co; j++)
							table.setValueAt("", i, j);
					rs = sql.executeQuery(sqlcode);
					int k = -1;
					while (rs.next()) {
						k++;
						for(int i=1;i<=co;i++)
							table.setValueAt(rs.getString(i), k, i-1);
					}
					con.close();
				}
			 catch (SQLException e1) {
				JOptionPane.showMessageDialog(op, "查询失败！");
			}
	 }
	
 }


