/*
参与者：高涵宸，胡景瑞
类作用：定义一个点类，用于存储点的坐标，便于通过描点的方式绘制图形
 */

package Main;

public class Point {
    public double x;    //点的横坐标
    public double y;    //点的纵坐标

    //构造函数，初始化点的坐标
    public Point(double x, double y)
    {
        super();
        this.x = x;
        this.y = y;
    }
    //获取点的横坐标
    public double getX()
    {
        return x;
    }
    //设置点的横坐标
    public void setX(double x)
    {
        this.x = x;
    }
    //获取点的纵坐标
    public double getY()
    {
        return y;
    }
    //设置点的纵坐标
    public void setY(double y)
    {
        this.y = y;
    }
}
