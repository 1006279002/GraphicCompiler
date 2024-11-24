/*
参与者：高涵宸，胡景瑞
类作用：实现tan函数
 */

package Funcs;

import Scanner.Func;

//实现tan函数
public class Func_Tan implements Func {
    @Override
    public double calculate(double num)
    {
        return Math.tan(num);
    }

}