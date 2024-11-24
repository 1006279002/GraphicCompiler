/*
参与者：高涵宸，胡景瑞
类作用：Token类用于存储词法分析器分析出的记号，包括记号的类别、原始输入的字符串、常数的值、函数接口等属性。
 */

package Scanner;

//存储词法分析器分析出的记号
public class Token {

    public Token_Type token_Type; //类别
	public String lexeme;		  //属性，原始输入的字符串
	public double value;		  //属性，若记号是常数则存常数的值
	public Func func;			  //属性，若记号是函数则调用函数接口

	//构造函数，用于规定Token表中的常规Token
	public Token(Token_Type token_Type, String lexeme, double value, Func func)
	{
		super();
		this.token_Type = token_Type;
		this.lexeme = lexeme;
		this.value = value;
		this.func = func;
	}

	//构造函数，用于接收非常规Token，便于后续处理
	public Token()
	{
		super();
		this.token_Type = Token_Type.NONTOKEN;
		this.lexeme = "";
		this.value = 0;
		this.func = null;
	}
}
