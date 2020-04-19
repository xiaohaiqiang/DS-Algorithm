package com.xhq.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        HeroNode hero1 = new HeroNode(1,"宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用", "智多星");
        HeroNode hero4 = new HeroNode(4,"林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //不按编号添加英雄
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero2);

        //按编号添加英雄
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("更新"+ newHeroNode.getNo() +"后的链表");
        singleLinkedList.list();

        //测试删除节点
        System.out.println("============================================");
        System.out.println("删除前的链表：");
        singleLinkedList.list();
        singleLinkedList.delete(1);
        //singleLinkedList.delete(4);
        System.out.println("删除后的链表：");
        singleLinkedList.list();

        //测试求单链表的有效节点个数
        System.out.println("==============================================");
        System.out.println("有效结点的个数为：" + getLength(singleLinkedList.getHead()));

        //测试获取倒数第k个节点
        int k = 2;
        System.out.println("倒数第"+ k +"个节点为：" + findLastIndexNode(singleLinkedList.getHead(), k));

        //测试反转链表
        System.out.println("=================================================");
        System.out.println("反转前链表：");
        singleLinkedList.list();

        SingleLinkedListDemo.reverseList(singleLinkedList.getHead());
        System.out.println("反转后链表：");
        singleLinkedList.list();

        //测试逆序打印链表
        System.out.println("=================================================");
        System.out.println("原链表结构：");
        singleLinkedList.list();
        System.out.println("逆序打印：");
        SingleLinkedListDemo.reversePrint(singleLinkedList.getHead());
    }

    //返回有效节点的个数
    public static int getLength(HeroNode head){
        if(head.getNext() == null){//空链表
            return 0;
        }
        int count = 0;
        HeroNode temp = head;
        while(true){
            if(temp.getNext() == null){
                break;
            }
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    //返回链表倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head, int k){
        if(head.getNext() == null){
            //链表为空
            return null;
        }

        //获取链表的总长度
        int size = getLength(head);
        if(k <= 0 || k > size){
            return null;
        }
        HeroNode temp = head;
        for(int i = 0; i <= size - k; i++){
            temp = temp.getNext();
        }

        return temp;
    }

    //将单链表反转
    public static void reverseList(HeroNode head){
        //链表为空或者只有一个元素，无需反转
        if(head.getNext() == null || head.getNext().getNext() == null){
            return ;
        }

        HeroNode temp = head.getNext();
        HeroNode next = null;//指向当前节点temp的下一个节点

        HeroNode reverseHead = new HeroNode(0,"","");
        while(temp != null){
            next = temp.getNext();
            temp.setNext(reverseHead.getNext());
            reverseHead.setNext(temp);
            temp = next;
        }

        head.setNext(reverseHead.getNext());
    }

    //逆序打印链表（链表结构不变
    public static void reversePrint(HeroNode head){
        if(head.getNext() == null){
            return ;
        }

        HeroNode temp = head.getNext();

        Stack<HeroNode> heroNodeStack = new Stack<HeroNode>();

        while(temp != null){
            heroNodeStack.push(temp);
            temp = temp.getNext();
        }

        while(heroNodeStack.size() > 0){
            System.out.println(heroNodeStack.pop());
        }
    }
}

//定义SingleLinkedList管理英雄
class SingleLinkedList{
    //先初始化一个头结点，头结点不动，不存储数据
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    //添加节点到单向链表
    //当不考虑编号时，先遍历找到链表最后一个节点，新节点加在最后
    public void add(HeroNode heroNode){
        //头结点不动，需要一个temp
        HeroNode temp = head;

        while(true){
            if(temp.getNext() == null){
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(heroNode);
    }

    //按照编号顺序添加英雄
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;//false表示要添加的英雄链表中没有

        while(true){
            if(temp.getNext() == null){
                break;
            }
            if(temp.getNo() == heroNode.getNo()){
                flag = true;
                break;
            }
            if(temp.getNext().getNo() > heroNode.getNo()){
                break;
            }

            temp = temp.getNext();
        }

        if(flag == true){
            System.out.println("您要添加的英雄"+ heroNode.getName() +"已经存在");
            return;
        }else{
            heroNode.setNext(temp.getNext());
            temp.setNext(heroNode);
        }
    }

    //删除节点
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;//是否找到要删除的节点
        while(true){
            if(temp.getNext() == null){
                //遍历结束，没有找到要删除的节点
                break;
            }
            if(temp.getNext().getNo() == no){
                //找到
                flag = true;
                break;
            }

            temp = temp.getNext();
        }

        if(flag == true){
            //找到
            temp.setNext(temp.getNext().getNext());
        }else{
            System.out.println("链表中不存在"+  no +"节点");
        }
    }

    //修改节点的信息
    //先遍历找到链表中与newHeroNode编号相同的节点，再修改信息
    public void update(HeroNode newHeroNode){
        HeroNode temp = head.getNext();
        boolean flag = false;//是否找到
        while(true){
            if(temp == null){//遍历结束，未找到
                break;
            }
            if(temp.getNo() == newHeroNode.getNo()){
                //找到编号相同的节点
                flag = true;
                break;
            }

            temp = temp.getNext();
        }
        if(flag == false){
            //未找到
            System.out.println("您要修改的节点"+ newHeroNode.getNo() +"不存在");
            return ;
        }else{
            temp.setName(newHeroNode.getName());
            temp.setNickname(newHeroNode.getNickname());
        }
    }

    //显示链表（遍历
    public void list(){
        //判断链表是否为空
        if(head.getNext() == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head;
        while(true){
            if(temp.getNext() == null){
                break;
            }
            temp = temp.getNext();
            System.out.println(temp);
        }
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    private int no;
    private String name;
    private String nickname;
    private HeroNode next;

    public HeroNode(int no, String name, String nickname) {
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

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
