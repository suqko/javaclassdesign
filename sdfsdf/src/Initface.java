
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.jb2011.lnf.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.sun.awt.AWTUtilities;
//import com.sun.awt.*;
class Initface extends JFrame implements ActionListener {
	JFrame frame = new JFrame("医院管理系统");
	JLabel label = new JLabel("", JLabel.CENTER);
	JButton button1 = new JButton("进入系统");
	JButton button2 = new JButton("退出系统");
	ImageIcon im = new ImageIcon("img/医学.png");
	
	
	JLabel a1 = new JLabel(im);
	void Create() {

		JPanel pcontentPane = (JPanel) frame.getContentPane();
		//frame.setUndecorated(true);
		//AWTUtilities.setWindowOpaque(frame, false);
		//frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		pcontentPane.add(a1);
		pcontentPane.add(label);

		pcontentPane.setLayout(new FlowLayout());
		pcontentPane.add(button1);
		pcontentPane.add(button2);

		pcontentPane.setVisible(true);
		button1.addActionListener(this);
		button2.addActionListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setBounds(600, 280, 270, 300);
		frame.setVisible(true);
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
		Initface dome = new Initface();
		dome.Create();
	}

	public void actionPerformed(ActionEvent e) {
		if (button1.equals(e.getSource())) {
			Landingface dl = new Landingface();
			dl.create();
			frame.dispose();
		}

		if (button2.equals(e.getSource())) {// 退出
			System.exit(0);

		}
	}
}