/*
参与者：高涵宸，胡景瑞
类作用：程序入口，主函数
 */

package Main;

import Semantic.Semantic;

//程序入口
public class Main {

    public static void main(String[] args)
    {
        Semantic semanticImp = new Semantic(new Draw());
        semanticImp.parser("test.txt");             //解析文件
    }
}
