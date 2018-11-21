package text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawChessBoard extends JPanel implements MouseListener{//监听器鼠标监听的接口
	final static int BLACK=0;
	final static int WHITE=1;
	public int chessColor = BLACK;
	int chessman_width=30;//棋盘宽
	
	public Image boardImg;
	final private int ROWS = 14;//行
	Chessman[][] chessStatus=new Chessman[ROWS+1][ROWS+1];	

	public DrawChessBoard() {
		boardImg = Toolkit.getDefaultToolkit().getImage("res/drawable/chessboard2.png");//绝对路径的文件形式
		if(boardImg == null)
			System.err.println("背景画板没有找到");

		addMouseListener(this);
	}	
	@Override
	protected void paintComponent(Graphics g) {//绘画部件
		// TODO Auto-generated method stub
		super.paintComponent(g);

		int imgWidth = boardImg.getHeight(this);//获取当前图片的长和宽
		int imgHeight = boardImg.getWidth(this);
		int FWidth = getWidth();
		int FHeight= getHeight();

		int x=(FWidth-imgWidth)/2;
		int y=(FHeight-imgHeight)/2;

		int span_x=imgWidth/ROWS;//跨度
		int span_y=imgHeight/ROWS;

		g.drawImage(boardImg, x, y, null);

		//画横线
		for(int i=0;i<ROWS;i++)
		{
			g.drawLine(x, y+i*span_y, FWidth-x,y+i*span_y);//后面两个应该是坐标
		}
		//画竖线
		for(int i=0;i<ROWS;i++)
		{
			g.drawLine(x+i*span_x, y, x+i*span_x,FHeight-y);
		}

		//画棋子
		for(int i=0;i<ROWS+1;i++)
		{
			for(int j=0;j<ROWS+1;j++)
			{
				if(chessStatus[i][j]!=null&&chessStatus[i][j].getPlaced()==true)//数组的检查
				{
					//System.out.println("draw chessman "+i+" "+j);
					int pos_x=x+i*span_x;
					int pos_y=y+j*span_y;
					float radius_b=40;
					float radius_w=80;
					float[] fractions = new float[]{0f,1f};
					java.awt.Color[] colors_b = new java.awt.Color[]{Color.BLACK,Color.WHITE};
					Color[] colors_w = new Color[]{Color.WHITE,Color.BLACK};
					RadialGradientPaint paint;
					if(chessStatus[i][j].getColor()==1)
					{
						//System.out.println("draw white chess");
						paint = new RadialGradientPaint(pos_x-chessman_width/2f, pos_y-chessman_width/2f, radius_w*2, fractions, colors_w);
					}else{
						//System.out.println("draw black chess");
						paint = new RadialGradientPaint(pos_x-chessman_width/2f, pos_y-chessman_width/2f, radius_b*2, fractions, colors_b);
					}
					((Graphics2D)g).setPaint(paint);

					((Graphics2D)g).fillOval(pos_x-chessman_width/2,pos_y-chessman_width/2,chessman_width,chessman_width);
				}
			}
		}
	}

	@Override
	//当用户按下鼠标按钮时发生
	public void mousePressed(MouseEvent e) {
		int point_x=e.getX();
		int point_y=e.getY();

		int imgWidth = boardImg.getHeight(this);
		int imgHeight = boardImg.getWidth(this);
		int FWidth = getWidth();
		int FHeight= getHeight();

		int x=(FWidth-imgWidth)/2;
		int y=(FHeight-imgHeight)/2;

		int span_x=imgWidth/ROWS;
		int span_y=imgHeight/ROWS;

		//System.out.println("press");
		int status_x = 0;
		int status_y = 0;
		if(point_x>=x && point_x<=x+imgWidth && point_y>=y && point_y <= y+imgHeight)
		{
			//System.out.println("合法");
			for(int i=0;i<ROWS+1;i++)
			{
				if(point_x>=x-chessman_width/2+1+i*span_x)
				{
					if(point_x<=x+chessman_width/2-1+i*span_x)//如果是width/2会在中间点出现两个匹配值
					{
						//System.out.println("point x "+i+" "+point_x+" "+(x-chessman_width/2+i*span_x)+" "+(x+chessman_width/2+i*span_x));
						status_x = i;
					}
				}
			}
			for(int i=0;i<ROWS+1;i++)
			{
				if(point_y>=y-chessman_width/2+1+i*span_y)
				{
					if(point_y <= y+chessman_width/2-1+i*span_y)
					{
						//System.out.println("point y "+i+" "+point_y+" "+(y-chessman_width/2+1+i*span_y)+" "+(y+chessman_width/2-1+i*span_y));
						status_y = i;
					}
				}
			}
			
			if(chessStatus[status_x][status_y]==null||chessStatus[status_x][status_y].getPlaced()==false)
			{
				Chessman chessman = new Chessman(chessColor, true);
				chessStatus[status_x][status_y]=chessman;
				System.out.println("chess color:"+chessColor);
				if(chessColor==BLACK)
				{
					chessColor = WHITE;
				}else {
					chessColor = BLACK;
				}
				repaint();
				
				//如果胜出，给出提示信息
				if(isWin(status_x, status_y, chessStatus))
				{
					System.out.println("WIN!!!!!");
					String winner;
					//如果下一子是白色的，那么此次为黑方
					if(chessColor == WHITE)
						winner = "黑方";
					else
						winner = "白方";
					String mString = String.format("恭喜，%s WIN!!!!!", winner);
					JOptionPane.showMessageDialog(this, mString);
				}
			}
		}
	}
	@Override
	//当用户按下并松开鼠标按钮时发生
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	boolean isWin(int point_x,int point_y,Chessman[][] cm)
	{	
		for(int i=0;i<ROWS+1;i++)
		{
			for(int j=0;j<ROWS+1;j++)
			{
				//横向查找
				if(chessStatus[i][j]!=null&&chessStatus[i][j].getPlaced()==true)
				{
					int matchColor = chessStatus[i][j].getColor();
					//向右侧查找
					for(int n=1;n<=4;n++)
					{
						if((i+n>=0)&&(i+n)<=ROWS)
						{
							if(chessStatus[i+n][j]!=null&&chessStatus[i+n][j].getPlaced()==true&&chessStatus[i+n][j].getColor()==matchColor)
							{
								chessStatus[i][j].matchCount++;
								System.out.println("pos:"+i+" "+j+" right count++:"+(i+n)+" "+j+" count:"+chessStatus[i][j].matchCount);
								if(chessStatus[i][j].matchCount==5)
								{
									return true;
								}
							}else
							{
								break;
							}	
						}
					}
					//向左侧查找
					for(int n=1;n<=4;n++)
					{
						if((i-n>=0)&&(i-n)<=ROWS)
						{
							if(chessStatus[i-n][j]!=null&&chessStatus[i-n][j].getPlaced()==true&&chessStatus[i-n][j].getColor()==matchColor)
							{
								chessStatus[i][j].matchCount++;
								System.out.println("pos:"+i+" "+j+" "+"left count++:"+(i-n)+" "+j+" count:"+chessStatus[i][j].matchCount);
								if(chessStatus[i][j].matchCount==5)
								{
									return true;
								}
							}else
							{
								if(chessStatus[i-n][j]!=null)
								{
									chessStatus[i][j].matchCount = 1;
								}
								break;
							}
						}
					}
					chessStatus[i][j].matchCount=1;//refresh count
				}
			}
		}

		for(int i=0;i<ROWS+1;i++)
		{
			for(int j=0;j<ROWS+1;j++)
			{
				//纵向
				if(chessStatus[i][j]!=null&&chessStatus[i][j].getPlaced()==true)
				{
					int matchColor = chessStatus[i][j].getColor();
					//向下查找，左上角为坐标原点，y轴正方向向下
					for(int n=1;n<=4;n++)
					{
						if((j+n>=0)&&(j+n)<=ROWS)
						{
							if(chessStatus[i][j+n]!=null&&chessStatus[i][j+n].getPlaced()==true&&chessStatus[i][j+n].getColor()==matchColor)
							{
								chessStatus[i][j].matchCount++;
								System.out.println("pos:"+i+" "+j+" up count++:"+(i)+" "+(j+n)+" count:"+chessStatus[i][j].matchCount);
								if(chessStatus[i][j].matchCount==5)
								{
									return true;
								}
							}else
							{
								break;
							}	
						}
					}
					//向上查找
					for(int n=1;n<=4;n++)
					{
						if((j-n>=0)&&(j-n)<=ROWS)
						{
							if(chessStatus[i][j-n]!=null&&chessStatus[i][j-n].getPlaced()==true&&chessStatus[i][j-n].getColor()==matchColor)
							{
								chessStatus[i][j].matchCount++;
								System.out.println("pos:"+i+" "+j+" "+"left count++:"+(i)+" "+(j-n)+" count:"+chessStatus[i][j].matchCount);
								if(chessStatus[i][j].matchCount==5)
								{
									return true;
								}
							}else
							{
								if(chessStatus[i][j-n]!=null)
								{
									chessStatus[i][j].matchCount = 1;
								}
								break;
							}
						}
					}
					chessStatus[i][j].matchCount=1;//refresh count
				}
			}
		}

		//方向：左上右下
		for(int i=0;i<ROWS+1;i++)
		{
			for(int j=0;j<ROWS+1;j++)
			{
				//左上
				if(chessStatus[i][j]!=null&&chessStatus[i][j].getPlaced()==true)
				{
					int matchColor = chessStatus[i][j].getColor();
					//向下查找，左上角为坐标原点，y轴正方向向下
					for(int n=1;n<=4;n++)
					{
						if((j-n>=0)&&(j-n)<=ROWS&&(i-n)>=0&&(i-n)<=ROWS)
						{
							if(chessStatus[i-n][j-n]!=null&&chessStatus[i-n][j-n].getPlaced()==true&&chessStatus[i-n][j-n].getColor()==matchColor)
							{
								chessStatus[i][j].matchCount++;
								System.out.println("pos:"+i+" "+j+" up count++:"+(i-n)+" "+(j-n)+" count:"+chessStatus[i][j].matchCount);
								if(chessStatus[i][j].matchCount==5)
								{
									return true;
								}
							}else
							{
								break;
							}	
						}
					}
					//右下
					for(int n=1;n<=4;n++)
					{
						if((j+n>=0)&&(j+n)<=ROWS&&(i+n)>=0&&(i+n)<=ROWS)
						{
							if(chessStatus[i+n][j+n]!=null&&chessStatus[i+n][j+n].getPlaced()==true&&chessStatus[i+n][j+n].getColor()==matchColor)
							{
								chessStatus[i][j].matchCount++;
								System.out.println("pos:"+i+" "+j+" "+"left count++:"+(i+n)+" "+(j+n)+" count:"+chessStatus[i][j].matchCount);
								if(chessStatus[i][j].matchCount==5)
								{
									return true;
								}
							}else
							{
								if(chessStatus[i+n][j+n]!=null)
								{
									chessStatus[i][j].matchCount = 1;
								}
								break;
							}
						}
					}
					chessStatus[i][j].matchCount=1;//refresh count
				}
			}
		}

		//方向：左下右上
		for(int i=0;i<ROWS+1;i++)
		{
			for(int j=0;j<ROWS+1;j++)
			{
				//左下
				if(chessStatus[i][j]!=null&&chessStatus[i][j].getPlaced()==true)
				{
					int matchColor = chessStatus[i][j].getColor();
					//向下查找，左上角为坐标原点，y轴正方向向下
					for(int n=1;n<=4;n++)
					{
						if((j+n>=0)&&(j+n)<=ROWS&&(i-n)>=0&&(i-n)<=ROWS)
						{
							if(chessStatus[i-n][j+n]!=null&&chessStatus[i-n][j+n].getPlaced()==true&&chessStatus[i-n][j+n].getColor()==matchColor)
							{
								chessStatus[i][j].matchCount++;
								System.out.println("pos:"+i+" "+j+" up count++:"+(i-n)+" "+(j+n)+" count:"+chessStatus[i][j].matchCount);
								if(chessStatus[i][j].matchCount==5)
								{
									return true;
								}
							}else
							{
								break;
							}	
						}
					}
					//右上
					for(int n=1;n<=4;n++)
					{
						if((j-n>=0)&&(j-n)<=ROWS&&(i+n)>=0&&(i+n)<=ROWS)
						{
							if(chessStatus[i+n][j-n]!=null&&chessStatus[i+n][j-n].getPlaced()==true&&chessStatus[i+n][j-n].getColor()==matchColor)
							{
								chessStatus[i][j].matchCount++;
								System.out.println("pos:"+i+" "+j+" "+"left count++:"+(i+n)+" "+(j-n)+" count:"+chessStatus[i][j].matchCount);
								if(chessStatus[i][j].matchCount==5)
								{
									return true;
								}
							}else
							{
								if(chessStatus[i+n][j-n]!=null)
								{
									chessStatus[i][j].matchCount = 1;
								}
								break;
							}
						}
					}
					chessStatus[i][j].matchCount=1;//refresh count
				}
			}
		}		

		return false;	
	}
}