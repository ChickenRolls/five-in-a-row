package text;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener{
	JButton but1,but2,but3;
	public Main() {
		this.setTitle("五子棋");
		this.setSize(300, 350);
		this.setLayout(new GridLayout(4,1)); 
		but1=new JButton("左右手互博");
		but2=new JButton("人机对战");
		but3=new JButton("联网对战");
		JPanel jp1=new JPanel();
		jp1.add(but1);
		JPanel jp2=new JPanel();
		jp2.add(but2);
		JPanel jp3=new JPanel();
		jp3.add(but3);
		but1.setActionCommand("ent1");
		but1.addActionListener(this);
		but2.setActionCommand("ent2");
		but2.addActionListener(this);
		but3.setActionCommand("ent3");
		but3.addActionListener(this);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd =e.getActionCommand();
		if(cmd.equals("ent1")) {
			zuoye m = new zuoye();
			m.setSize(800, 800);
			m.setVisible(true);//可见
		}
		if(cmd.equals("ent2")) {
			new ChessMap();
			this.setVisible(false);
		}
		
		// TODO Auto-generated method stub
		
	}
	public static void main(String[]args) {//              main函数的入口在这！
		new Main();
	}

}
