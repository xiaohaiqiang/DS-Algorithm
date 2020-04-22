package com.xhq.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

class CircleSingleLinkedList{
    private Boy first = null;

    public void addBoy(int num){//添加num个Boy
        if(num < 1){
            System.out.println("添加的个数必须为正整数");
            return ;
        }
        Boy temp = null;

        for(int i = 1; i <= num; i++){
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                first.setNext(first);
                temp = first;
            }
            temp.setNext(boy);
            boy.setNext(first);
            temp = temp.getNext();
        }
    }

    //遍历环形链表
    public void showBoy(){

        if(first == null){
            System.out.println("环形链表为空");
            return;
        }
        Boy temp = first;

        while(true){

            System.out.println("第"+temp.getNo()+"个小孩");
            if(temp.getNext() == first){
                break;
            }

            temp = temp.getNext();
        }

    }

    public void countBoy(int startNum, int countNum, int nums){
        //先对数据进行校验
        if(startNum < 1 || startNum > nums || countNum < 1){
            System.out.println("输入的参数有误");
            return ;
        }

        //创建辅助变量temp，先指向环形链表的最后一个节点
        Boy temp = first;
        while(true){
            if(temp.getNext() == first){
                break;
            }
            temp = temp.getNext();
        }

        //报数前，先让first和temp移动到开始报数的位置
        for(int i = 0; i < startNum - 1; i++){
            first = first.getNext();
            temp = temp.getNext();
        }

        //开始报数，每次移动(countNum -1)，然后出圈，直到剩下一个人
        while(true){
            if(first.getNext() == first){
                break;
            }
            for(int i = 0; i < countNum - 1; i++){
                first = first.getNext();
                temp = temp.getNext();
            }
            System.out.println("小孩"+first.getNo()+"出圈");
            first = first.getNext();
            temp.setNext(first);
        }
        System.out.println("圈中最后一个小孩是："+ first.getNo());
    }
}

class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}