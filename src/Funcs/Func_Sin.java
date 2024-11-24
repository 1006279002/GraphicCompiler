/*
参与者：高涵宸，胡景瑞
类作用：实现sin函数
 */

package Funcs;

import Scanner.Func;

//实现sin函数
public class Func_Sin implements Func {

    @Override
    public double calculate(double num)
    {
        return Math.sin(num);
    }

}