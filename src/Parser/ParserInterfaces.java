/*
参与者：高涵宸，胡景瑞
接口作用：定义了Parser类的接口，包括分析器所需的辅助子程序、主要产生式的递归子程序、构造语法树的节点、嵌入测试语句
 */

package Parser;

import Scanner.Func;
import Scanner.Token_Type;

public interface ParserInterfaces {

    //辅助子程序
	void FetchToken(); //获取记号
	void MatchToken(Token_Type token_Type);	//匹配终结符
	void SyntaxError(int case_value); //出错处理
	void PrintSyntaxTree(TreeNode root,int indent); //打印语法树

	//主要产生式的递归子程序
	void parser(String file_name);
	void Program();
	void Statement();
	void OriginStatement();
	void RotStatement();
	void ScaleStatement();
	void ForStatement();
    void ColorStatement();
    void Colors();

	//消除左递归与左因子用
    TreeNode Expression();
	TreeNode Term();
	TreeNode Factor();
	TreeNode Component();
	TreeNode Atom();

	//构造语法树的节点
	TreeNode MakeTreeNode(Token_Type token_Type,TreeNode left,TreeNode right);		//二元运算
	TreeNode MakeTreeNode(Token_Type token_Type);									//叶子结点，变量T
	TreeNode MakeTreeNode(Token_Type token_Type,double value);     					//叶子结点，常数
	TreeNode MakeTreeNode(Token_Type token_Type,Func caseParmPtr,TreeNode value); 	//函数以及函数地址

	//嵌入测试语句
	void Enter(String s);
	void Exit(String s);
	void Match(String s);
}
