/*
参与者：高涵宸，胡景瑞
类作用：处理语法树，计算坐标值，进行坐标变换，画出图形
 */

package Semantic;

import Main.Draw;
import Main.Point;
import Parser.Parser;
import Parser.TreeNode;

public class Semantic extends Parser {

    public double Origin_x;    //origin的那两个值
    public double Origin_y;
    public double Scale_x;      //Scale的那两个值
    public double Scale_y;
    public double rot_angle;    //rot的角度
    public Draw draw;

    public Semantic(Draw draw)
    {
        super();                       //可有有无，继承类都会默认访问父类的无参构造函数，若父类有构造函数但不是无参的，则必须重写无参构造函数，否则报错
        Origin_x = 0;
        Origin_y = 0;
        Scale_x = 1;
        Scale_y = 1;
        rot_angle = 0;
        this.draw = draw;
    }

    public Double GetTreeValue(TreeNode root)                  //获取语法树上的值
    {
        if (root == null)
        {
            return 0.0;
        }
        switch (root.OpCode)
        {
            case PLUS:
                return GetTreeValue(root.case_operator.left) + GetTreeValue(root.case_operator.right);
            case MINUS:
                return GetTreeValue(root.case_operator.left) - GetTreeValue(root.case_operator.right);
            case MUL:
                return GetTreeValue(root.case_operator.left) * GetTreeValue(root.case_operator.right);
            case DIV:
                return GetTreeValue(root.case_operator.left) / GetTreeValue(root.case_operator.right);
            case POWER:
                return Math.pow(GetTreeValue(root.case_operator.left), GetTreeValue(root.case_operator.left));
            case FUNC:
                return root.case_func.func.calculate(GetTreeValue(root.case_func.child));
            case CONST_ID:
                return root.case_const;
            case T:
                return root.case_parmPtr.getA();
            default:
                return 0.0;
        }
    }

    public void CalCord(TreeNode hor_ptr, TreeNode ver_ptr, Point point) //计算点的坐标值：首先获取坐标值，然后进行坐标变换
    {
        double x_val;
        double x_temp;
        double y_val;

        x_val = GetTreeValue(hor_ptr);   //计算点的原始坐标
        y_val = GetTreeValue(ver_ptr);

        x_val *= Scale_x;				//比列变换
        y_val *= Scale_y;

        x_temp = x_val * Math.cos(rot_angle) + y_val * Math.sin(rot_angle);
        y_val = y_val * Math.cos(rot_angle) - x_val * Math.sin(rot_angle);
        x_val = x_temp;                 //旋转变换

        x_val += Origin_x;				//平移变换
        y_val += Origin_y;

        point.setX(x_val);
        point.setY(y_val);
    }

    public void DeleteTree(TreeNode root)
    {
        if (root == null)
        {
            return;
        }
        switch (root.OpCode)
        {
            case PLUS: 						//两个孩子的内部节点
            case MINUS:
            case MUL:
            case DIV:
            case POWER:
                DeleteTree(root.case_operator.left);
                DeleteTree(root.case_operator.right);
                break;
            case FUNC:						//一个孩子的内部节点
                DeleteTree(root.case_func.child);
                break;
            default:						//叶子节点
                break;
        }
    }

    public void DrawLoop(double start_val,double end_val,double step_val,TreeNode x_ptr,TreeNode y_ptr)
    {
        double x_val = 0;
        double y_val = 0;
        Point point = new Point(x_val,y_val);

        for(parameter.setA(start_val); parameter.getA() <= end_val; parameter.setA(parameter.getA() + step_val))
        {
            CalCord(x_ptr, y_ptr, point);  //计算当前点
            draw.draw(point);   //画出当前点
        }
    }

    @Override
    public void OriginStatement()
    {
        super.OriginStatement();
        Origin_x = GetTreeValue(x_ptr);
        DeleteTree(x_ptr);
        Origin_y = GetTreeValue(y_ptr);
        DeleteTree(y_ptr);
    }

    @Override
    public void RotStatement()
    {
        super.RotStatement();
        rot_angle = GetTreeValue(angle_ptr);
        DeleteTree(angle_ptr);
    }

    @Override
    public void ScaleStatement()
    {
        super.ScaleStatement();
        Scale_x = GetTreeValue(x_ptr);
        DeleteTree(x_ptr);
        Scale_y = GetTreeValue(y_ptr);
        DeleteTree(y_ptr);
    }

    @Override
    public void ForStatement()
    {
        double start_val;
        double end_val;
        double step_val;
        super.ForStatement();

        start_val = GetTreeValue(start_ptr);
        end_val = GetTreeValue(end_ptr);
        step_val = GetTreeValue(step_ptr);

        DrawLoop(start_val, end_val, step_val, x_ptr, y_ptr);

        DeleteTree(start_ptr);
        DeleteTree(end_ptr);
        DeleteTree(step_ptr);
        DeleteTree(x_ptr);
        DeleteTree(y_ptr);

    }
}
