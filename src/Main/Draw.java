package Main;

import javax.swing.*;
import java.awt.*;

public class Draw extends JFrame {


    private Graphics g;
    private Color backColor = new Color(0xffffff);



    /**
     * DrawSee构造方法
     */
    public Draw()
    {

        Container p = getContentPane();
        setSize(1000,1000);
        setVisible(true);
        p.setBackground(backColor);
        getContentPane().setLayout(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g = this.getGraphics();
        g.translate(0,0);
        g.setColor(Color.BLACK);
        paintComponents(g);
    }

//    public void setsize()   //设置点的大小以及绘图速度
//	{
//    	String s = textField.getText();
//    	size = Integer.parseInt(s);
//    	time = Double.parseDouble(textField_1.getText());
//  //      Main.semanticImp.Parser("C:\\Users\\官瑞涛\\Desktop\\test.txt");
//	}

//	public void drawPoint(int x,int y)
//    {
//        jg.drawLine(x,y,x,y);
//    }
//    public void drawManyPoint(int x,int y)
//    {
//
//    	  Graphics2D g2d = (Graphics2D)g;
//    	  g2d.drawLine(x,y,x,y);
//    	  g2d.setStroke(new BasicStroke());
//
//    }
//
//    public void draw(Point point)
//    {
//        drawManyPoint((int)point.getX(),(int)point.getY());
//    }

    public void draw(Point point)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawLine((int)point.getX(),(int)point.getY(),(int)point.getX(),(int)point.getY());
    }
}
