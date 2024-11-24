/*
参与者：高涵宸，胡景瑞
类作用：实现sqrt函数
 */

package Funcs;

import Scanner.Func;

//实现sqrt函数
public class Func_Sqrt implements Func {

    @Override
    public double calculate(double num) {
        return Math.sqrt(num);
    }
}
