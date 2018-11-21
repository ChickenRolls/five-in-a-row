package text;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class himi extends JFrame implements ActionListener{

	JButton but1,but2;
	public himi() {
		this.setTitle("选择先手");
		this.setSize(300, 350);
		this.setLayout(new GridLayout(2,1));
		but1=new JButton("人类先手");
		but2=new JButton("机器先手");
		JPanel jp1=new JPanel();
		jp1.add(but1);
		JPanel jp2=new JPanel();
		jp2.add(but2);
		but1.setActionCommand("ent1");
		but1.addActionListener(this);
		but2.setActionCommand("ent2");
		but2.addActionListener(this);
		this.add(jp1);
		this.add(jp2);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String cmd =e.getActionCommand();
		if(cmd.equals("ent1")) {
			//new ();
		}
		if(cmd.equals("ent2")) {
			
		}
	}
	
}