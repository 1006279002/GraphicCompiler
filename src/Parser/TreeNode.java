/*
参与者：高涵宸，胡景瑞
类作用：语法树节点类，包含了语法树节点的各种属性，如记号种类，二元运算，函数调用，常量，参数T，以及参数T的指针
 */

package Parser;

import Scanner.Func;
import Scanner.Token_Type;

public class TreeNode {

    public Token_Type OpCode;  									// 记号(算符)种类
	public CaseOperator case_operator; 							//二元运算
	public CaseFunc case_func; 									//1个孩子，默认为函数调用
	public double case_const;  									//常量
	public CaseParamPtr case_parmPtr = new CaseParamPtr(0); 	//参数T，默认初始值为0；

	public TreeNode()
	{
		case_operator = new CaseOperator();
		case_func = new CaseFunc();
	}

	//二元运算存在左右两个孩子
	public static class CaseOperator
	{
		public TreeNode left;									//左孩子
		public TreeNode right;									//右孩子
	}


	//函数调用存在一个孩子，记录函数和参数
	public static class CaseFunc
	{
		public TreeNode child;									//参数
		public Func func;										//函数
	}
}
