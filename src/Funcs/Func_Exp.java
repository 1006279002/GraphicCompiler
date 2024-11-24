/*
参与者：高涵宸，胡景瑞
类作用：实现指数函数
 */

package Funcs;

import Scanner.Func;

//实现指数函数
public class Func_Exp implements Func {

    @Override
    public double calculate(double num)
    {
        return Math.exp(num);
    }

}