/*
参与者：高涵宸，胡景瑞
类作用：用于存储case语句中的参数
 */

package Parser;

//用于存储case语句中的参数
public class CaseParamPtr {
    double num; //参数

    public CaseParamPtr(double a)
    {
        this.num = a;
    }

    public double getA()
    {
        return num;
    }

    public void setA(double a)
    {
        this.num = a;
    }
}
