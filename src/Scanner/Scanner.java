/*
参与者：高涵宸，胡景瑞
类作用：词法分析器，用于从文本中读取字符，将字符转化为记号
 */

package Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Scanner {
    public static RandomAccessFile randomAccessFile = null;		//用于读取文件
	public static StringBuffer stringBuffer = null;				//用于存储读取的字符
	public static int now_pos = 0;								//用于记录当前读取的位置
	public static int line_num = 0;     						//用于记录出错的行数

	//初始化词法分析器，打开文件
	public static void init_scanner(String fileName)
	{
		try {
			randomAccessFile = new RandomAccessFile(fileName, "rw");	//打开文件
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException("文件未找到");
		}
		try {
			randomAccessFile.seek(0);		//将文件指针指向文件开头
			stringBuffer = new StringBuffer();	//初始化字符缓冲区
		} catch (IOException e)
		{
			throw new RuntimeException("文件读取错误");
		}
	}

	//关闭文件
	public static void close_scanner()
	{
		try
		{
			randomAccessFile.close();
		} catch (IOException e)
		{
			throw new RuntimeException("文件关闭错误");
		}
	}

	//获取记号
	public static Token GetToken()
	{
		Token token = new Token();	//新建一个记号
		int read_char_from_file;	//从文件中读取的字符

		Empty_Token_Buff();           			//清空记号缓冲区
		token.lexeme = stringBuffer.toString(); //将缓冲区的字符串赋予记号中的lexeme
        //如果读取到的字符不是空格、TAB、回车等，则跳出循环
        //过滤程序中的其他符号，直到文件结束返回空记号
        do {
            read_char_from_file = get_char();    //从文件中读取一个字符
            if (read_char_from_file == 65535)    //如果读取到文件结束符，则返回空记号
            {
                token.token_Type = Token_Type.NONTOKEN;
                return token;
            }
            if (read_char_from_file == '\n')    //如果读取到换行符，则行数加一
            {
                line_num++;
            }
        } while (Character.isWhitespace(read_char_from_file));

		add_char_to_stringbuilder((char) read_char_from_file); // 若不是回车、空格、TAB、文件结束符等，则加入字符缓冲区

		if (Character.isLetter(read_char_from_file)) // 若char是alphabet，则它一定是函数、关键字、PI、E等，而非数字
		{
			while (true)
			{
				read_char_from_file = get_char();			//接着读取接下来的字符
				if (Character.isDigit(read_char_from_file) || Character.isLetter(read_char_from_file)) //若是字母或数字，则继续读取
				{
					add_char_to_stringbuilder((char) read_char_from_file);//添加到buffer中，得到一个完整的Token
				}
				else
				{
					break;
				}
			}
			Back_Char();				//循环里多读了一次，读取的位置要前移一位
			token = Judge_key_token(stringBuffer.toString());	//判断所给字符串是否在字符表中，给这个token的type属性赋值
			token.lexeme = stringBuffer.toString();				//完整的Token的字符串在这个buffer中，赋给token.lexeme属性
			return token;										//返回这个token，用于后续处理
		}
		else if (Character.isDigit(read_char_from_file)) // 如果是一个数字，则一定是一个常量
		{
			while (true)
			{
				read_char_from_file = get_char();
				if (Character.isDigit(read_char_from_file))
				{
					add_char_to_stringbuilder((char) read_char_from_file);
				} else
				{
					break;
				}
			}
			if (read_char_from_file == '.') 		// 常量可能为小数
			{
				add_char_to_stringbuilder((char) read_char_from_file);
				while (true)
				{
					read_char_from_file = get_char();
					if (Character.isDigit(read_char_from_file))
					{
						add_char_to_stringbuilder((char) read_char_from_file);
					} else
					{
						break;
					}
				}
			}

			/*读取数字完成*/

			Back_Char();	//循环里多读了一次，读取的位置要前移一位
			token.token_Type = Token_Type.CONST_ID;
			token.value = Double.parseDouble(stringBuffer.toString()); // 将字符串小数变为十进制小数
			return token;							//返回这个token，用于后续处理
		}
		else // 若不是字母和数字，则一定是符号
		{
			switch (read_char_from_file)
			{
			case ',':
				token.token_Type = Token_Type.COMMA;
				break;
			case ';':
				token.token_Type = Token_Type.SEMICO;
				break;
			case '(':
				token.token_Type = Token_Type.L_BRACKET;
				break;
			case ')':
				token.token_Type = Token_Type.R_BRACKET;
				break;
			case '+':
				token.token_Type = Token_Type.PLUS;
				break;
			case '-':
				read_char_from_file = get_char();
				if (read_char_from_file == '-') // 遇到注释 --
				{
					while (read_char_from_file != '\n' && read_char_from_file != -1) // 最大匹配字符串
					{
						read_char_from_file = get_char();
					}
					Back_Char();
					return GetToken(); 		//函数嵌套，读完全部注释，从注释后接着读取与分析字符
				}
				else
				{
					Back_Char(); // 匹配减号
					token.token_Type = Token_Type.MINUS;
					break;
				}
			case '/':
				read_char_from_file = get_char();
				if (read_char_from_file == '/') // 遇到注释 //
				{
					while (read_char_from_file != '\n' && read_char_from_file != -1) // 最大匹配字符串
					{
						read_char_from_file = get_char();
					}
					Back_Char();
					return GetToken(); 		//函数嵌套，读完全部注释，从注释后接着读取与分析字符
				}
				else
				{
					Back_Char();
					token.token_Type = Token_Type.DIV;
					break;
				}
			case '*':
				read_char_from_file = get_char();
				if (read_char_from_file == '*') // 匹配乘方
				{
					token.token_Type = Token_Type.POWER;
                }
				else // 匹配乘法
				{
					Back_Char();
					token.token_Type = Token_Type.MUL;
                }
                break;
				default:
				token.token_Type = Token_Type.ERRTOKEN;
				break;
			}
			token.lexeme = stringBuffer.toString();
		}
		return token;		//返回这个记号token，用于后续处理
	}


	//判断所给字符串是否在字符表中，给这个token的type属性赋值
	public static Token Judge_key_token(String string)
	{
		Token err_token = new Token();		//新建一个错误记号

		for(int i = 0; i < Token_Table.token_table.length; i++)
		{
			if (Token_Table.token_table[i].lexeme.equals(string)) //如果传入的字符串在字符表中能找到则返回该记号，无视大小写
			{
				return Token_Table.token_table[i];
			}
		}
		stringBuffer = new StringBuffer();				//更新字符缓冲区
		err_token.token_Type = Token_Type.ERRTOKEN; 	//如果在字符表中lexeme找不到传入的字符串，则返回错误记号
		return err_token;
	}

	//将读取的字符位置前移一位
	public static void Back_Char()
	{
        now_pos--;
	}

	//将读取的字符加入字符缓冲区
	public static void add_char_to_stringbuilder(char read_char_from_file)
	{
		stringBuffer.append(read_char_from_file);
	}


	//从文件中读取一个字符，利用now_pos记录当前读取的位置来进行获取
	public static int get_char()
	{
		try
		{
			randomAccessFile.seek(now_pos);
			char next_char = (char)randomAccessFile.read();
			now_pos++;
			return Character.toUpperCase(next_char);
		} catch (IOException e)
		{
			throw new RuntimeException("文件读取错误");
		}
	}

	//清空字符缓冲区
	public static void Empty_Token_Buff()
	{
		stringBuffer = new StringBuffer();
	}
}
