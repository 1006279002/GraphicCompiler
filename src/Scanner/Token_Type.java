/*
参与者：高涵宸，胡景瑞
类作用：定义了Token的类型
 */

package Scanner;

//枚举类型，定义了Token的类型，同一行的Token类型相同
public enum Token_Type {
    ORIGIN,SCALE,ROT,IS,   			 	//保留字
	TO,STEP,DRAW,FOR,FROM, 			 	//保留字
	T,					  			 	//参数
	SEMICO,L_BRACKET,R_BRACKET,COMMA,	//分隔符，分别为分号、左括号、右括号、逗号
	PLUS,MINUS,MUL,DIV,POWER,        	//运算符，分别为加、减、乘、除、乘方
	FUNC,							 	//函数(调用)
	CONST_ID,						 	//常数
	NONTOKEN,					 	 	//空记号(源程序结束)
	ERRTOKEN,                        	//出错记号(非法输入)
	COLOR, RED, BLACK				 	//画图颜色
}
