package com.xhq.ArrayQueue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //  测试队列
        //创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);

        char key = ' ';//接受用户输入

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        //输出一个菜单
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):取出数据");
            System.out.println("h(head):显示头数据");
            System.out.println("e(exit):退出系统");

            key = scanner.next().charAt(0);//获取一个字符

            switch(key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int num = arrayQueue.getQueue();
                        System.out.println("取出的数是：" + num);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'h':
                    arrayQueue.headQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("退出系统成功");
                    break;
                default:
                    break;
            }

        }
    }
}

//非环形队列，数组只能使用一次，不能复用
class ArrayQueue{
    private int maxSize;//最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//存放队列的数组

    //创建队列的构造器
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        front = -1;//队列头的前一个位置
        rear = -1;//队列尾的位置
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否空
    public boolean isEmpty(){
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满，无法添加数据。");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //取出队列中的数据，并返回该数据
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法取出数据");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列中没有数据");
            return;
        }

        for(int i = front + 1; i < arr.length; i++){
            System.out.println("arr[" + i + "] = " + arr[i]);
        }
    }

    //显示队列的头数据，不取出
    public void headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列中没有数据");
        }
        System.out.println("头数据为：" + arr[front + 1]);
    }
}