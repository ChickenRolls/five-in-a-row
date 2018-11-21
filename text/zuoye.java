package text;
import java.awt.Container;
import javax.swing.JFrame;
import text.DrawChessBoard;
public class zuoye  extends JFrame{
	private DrawChessBoard drawChessBoard;//画棋盘
	public zuoye(){
        drawChessBoard = new DrawChessBoard();
		setTitle("五子棋");
		Container containerPane =getContentPane();//容器盘子
		containerPane.add(drawChessBoard);	//容器里面装画棋盘的算法	
	}
		

}
