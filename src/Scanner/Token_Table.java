/*
参与者：高涵宸，胡景瑞
类作用：Token_Table类用于存储所有的Token，包括常量、保留字、函数名等，以及它们的值和对应的函数对象。
 */

package Scanner;

import Funcs.*;

//存储所有的Token
public class Token_Table {
    public static Token[] token_table = new Token[]
			{
				new Token(Token_Type.CONST_ID, "PI", 3.1415926535, null),	//常数PI
                new Token(Token_Type.CONST_ID, "E", 2.71828,null),		//常数E
                new Token(Token_Type.T, "T", 0.0,null),					//参数T
                new Token(Token_Type.ORIGIN, "ORIGIN", 0.0,null),			//保留字，表示坐标原点
                new Token(Token_Type.SCALE, "SCALE", 0.0,null),			//保留字，表示坐标缩放
                new Token(Token_Type.ROT, "ROT", 0.0,null),				//保留字，表示坐标旋转
                new Token(Token_Type.IS, "IS", 0.0,null),					//保留字，表示赋值
                new Token(Token_Type.FOR, "FOR", 0.0,null),				//保留字，表示循环的参数
                new Token(Token_Type.FROM, "FROM", 0.0,null),				//保留字，表示循环的起始值
                new Token(Token_Type.TO, "TO", 0.0,null),					//保留字，表示循环的终止值
                new Token(Token_Type.STEP, "STEP", 0.0,null),				//保留字，表示循环的步长
                new Token(Token_Type.DRAW, "DRAW", 0.0,null),				//保留字，表示画图
                new Token(Token_Type.FUNC, "COS", 0.0,new Func_Cos()),			//函数COS
                new Token(Token_Type.FUNC, "SIN", 0.0, new Func_Sin()),		//函数SIN
                new Token(Token_Type.FUNC, "LN", 0.0,new Func_Ln()),			//函数LN
                new Token(Token_Type.FUNC, "EXP", 0.0, new Func_Exp()),		//函数EXP
                new Token(Token_Type.FUNC, "SQRT", 0.0, new Func_Sqrt()),		//函数SQRT
                new Token(Token_Type.FUNC, "TAN", 0.0, new Func_Tan()),		//函数TAN
                new Token(Token_Type.COLOR, "COLOR", 0.0,null),			//保留字，表示画图颜色
                new Token(Token_Type.RED, "RED", 0.0,null),				//保留字，表示红色
                new Token(Token_Type.BLACK, "BLACK", 0.0,null),			//保留字，表示黑色
			};
}
