package com.xhq.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "2+30*2-10";

        //创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义需要的变量
        int index = 0;//用于扫描expression
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到char保存到ch
        String keepNum = "";//用于拼接多位数

        //开始循环扫描expression
        while(true){
            //依次得到expression的每个字符
            ch = expression.substring(index, index + 1).charAt(0);

            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)){
                if(operStack.isEmpty()){
                    operStack.push(ch);
                }else{//符号栈不为空

                    //ch的优先级小于等于符号栈栈顶元素的优先级
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }
            }else{//ch是数

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression最后一位，直接入栈
                if(index == expression.length() -1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是否是数字。若是数字，则继续扫描，若是运算符，则入栈
                    if(operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))){
                        //若后一位是操作符，直接入栈
                        numStack.push(Integer.parseInt(keepNum));

                        //清空keepNum
                        keepNum = "";
                    }
                }
            }

            index++;
            if(index == expression.length()){
                break;
            }
        }

        //当expression扫描完毕，就顺序从数字栈和符号栈中pop出相应的数和符号
        while(true){
            //若符号栈为空，说明计算到最后一个结果，数栈中只有最后一个数
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2,oper);
            numStack.push(res);
        }

        //将最后的数pop出,就是结果
        res = numStack.pop();
        System.out.println(expression + "结果为：" + res);
    }
}


class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = - 1;
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize -1;
    }

    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //push
    public void push(int n){
        if(isFull()){
            System.out.println("栈满，无法入栈");
            return;
        }
        top++;
        stack[top] = n;
    }

    //pop
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，无法弹栈");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //show
    public void show(){
        for(int i = top; i >= 0; i--){
            System.out.println("stack[" + i + "]=" + stack[i]);
        }
    }

    //返回当前栈顶的值，但不pop
    public int peek(){
        return stack[top];
    }

    //返回运算符的优先级，优先级用数字表示，数字越大，优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;//假定只有+ - * /
        }
    }

    //判断是否是运算符
    public boolean isOper(int oper){
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    //计算
    public int cal(int num1, int num2, int oper){
        int res = 0;//计算结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}