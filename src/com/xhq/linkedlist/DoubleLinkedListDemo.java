package com.xhq.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        HeroNode2 hero1 = new HeroNode2(1,"宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        doubleLinkedList.delete(hero4);
        System.out.println("删除"+hero4.getName()+"后的链表：");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    public void setHead(HeroNode2 head) {
        this.head = head;
    }

    //add
    public void add(HeroNode2 heroNode2){

        HeroNode2 temp = head;
        while(true){
            if(temp.getNext() == null){
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(heroNode2);
        heroNode2.setPre(temp);
    }

    //delete
    public void delete(HeroNode2 heroNode2){

        HeroNode2 temp = head.getNext();
        boolean flag = false;
        if(head.getNext() == null){
            System.out.println("链表为空");
            return;
        }
        while(flag == false){
            if(temp == heroNode2){
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if(flag == true){
            temp.getPre().setNext(temp.getNext());
            if(temp.next != null) {
                temp.getNext().setPre(temp.getPre());
            }
        }
    }

    //显示链表（遍历
    public void list(){
        //判断链表是否为空
        if(head.getNext() == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head;
        while(true){
            if(temp.getNext() == null){
                break;
            }
            temp = temp.getNext();
            System.out.println(temp);
        }
    }

}

class HeroNode2{
    private int no;
    private String name;
    private String nickname;
    HeroNode2 next;
    HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode2 getNext() {
        return next;
    }

    public void setNext(HeroNode2 next) {
        this.next = next;
    }

    public HeroNode2 getPre() {
        return pre;
    }

    public void setPre(HeroNode2 pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}