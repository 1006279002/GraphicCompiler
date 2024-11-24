/*
参与者：高涵宸，胡景瑞
类作用：绘图类，用于绘制图形，调用swing和awt库中的方法
 */

package Main;

import javax.swing.*;
import java.awt.*;

public class Draw extends JFrame {

    private final Graphics g;

    public Draw()
    {

        Container p = getContentPane();
        setSize(1000,1000);
        setVisible(true);
        Color backColor = new Color(0xffffff);
        p.setBackground(backColor);
        getContentPane().setLayout(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g = this.getGraphics();
        g.translate(0,0);
        g.setColor(Color.BLACK);
        paintComponents(g);
    }

    public void draw(Point point)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawLine((int)point.getX(),(int)point.getY(),(int)point.getX(),(int)point.getY());
    }
}
