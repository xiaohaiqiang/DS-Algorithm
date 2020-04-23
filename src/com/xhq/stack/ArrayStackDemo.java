package com.xhq.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        boolean loop = true;
        String key = "";
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("show:展示栈");
            System.out.println("exit:退出程序");

            key = scanner.next();

            switch(key){
                case "push":
                    System.out.println("请输入一个数：");
                    int n = scanner.nextInt();
                    arrayStack.push(n);
                    break;
                case "pop":
                    try{
                        int value = arrayStack.pop();
                        System.out.println("出栈的数据是：" + value);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case "show":
                    arrayStack.show();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出成功");
    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top;

    public ArrayStack(int maxSize) {
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
}