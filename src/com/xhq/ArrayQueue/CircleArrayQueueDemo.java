package com.xhq.ArrayQueue;


import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //  测试环形队列
        //创建一个队列
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);

        char key = ' ';//接受用户输入

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        System.out.println("环形队列测试");
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
                    circleArrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int num = circleArrayQueue.getQueue();
                        System.out.println("取出的数是：" + num);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'h':
                    circleArrayQueue.headQueue();
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

class CircleArrayQueue{
    private int maxSize;//最大容量
    private int front;//队列头,指向队列第一个元素的位置
    private int rear;//队列尾，指向最后一个元素的后一个位置
    private int[] arr;//存放队列的数组

    //创建队列的构造器
    public CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //取出队列中的数据，并返回该数据
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法取出数据");
        }
        int temp = arr[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    //显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列中没有数据");
            return;
        }

        for(int i = front; i < front + size(); i++){
            System.out.println("arr[" + i % maxSize + "] = " + arr[i % maxSize]);
        }
    }

    //显示队列的头数据，不取出
    public void headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列中没有数据");
        }
        System.out.println("头数据为：" + arr[front]);
    }

    public int size(){
        return (rear - front + maxSize) % maxSize;
    }
}