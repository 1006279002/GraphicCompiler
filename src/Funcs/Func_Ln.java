/*
参与者：高涵宸，胡景瑞
类作用：实现对数函数
 */

package Funcs;

import Scanner.Func;

//实现对数函数
public class Func_Ln implements Func {

    @Override
    public double calculate(double num)
    {
        return Math.log(num);
    }

}