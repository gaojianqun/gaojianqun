package cn.base;


import java.util.Scanner;
import java.util.Stack;

/**
 * Created by gaojianqun on 2017/10/29.
 * Dijkstra(狄克斯特拉)双栈算术表达式算法
 */
public class Evaluate {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Stack<Character> ops = new Stack<Character>();
        Stack<Double> vals = new Stack<Double>();
        while(scanner.hasNext()){
            String s = scanner.nextLine();
            char[] c = s.toCharArray();
            for(int i = 0;i<c.length;i++){
                if(c[i]=='(') ;
                else if(c[i]=='+') ops.push(c[i]);
                else if(c[i]=='-') ops.push(c[i]);
                else if(c[i]=='*') ops.push(c[i]);
                else if(c[i]=='/') ops.push(c[i]);
                else if(c[i]==')'){
                    char op = ops.pop();
                    double val = vals.pop();
                    if(op=='+') val = vals.pop() + val;
                    else if(op=='-') val = vals.pop() - val;
                    else if(op=='*') val = vals.pop() * val;
                    else if(op=='/') val = vals.pop() / val;
                    vals.push(val);
                }
                else{
                    vals.push(Double.parseDouble(Character.toString(c[i])));
                }
            }
            System.out.println(vals.pop());
        }
    }

}
