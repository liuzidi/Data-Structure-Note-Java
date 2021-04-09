package LinkedList;

import java.util.Objects;

/**
 * @author:liuzidi
 * @Description: 一个PersonNode为元素的单链表实现
 */

public class SingleLinkedList {

    public PersonNode head = new PersonNode();//头结点
    public int SIZE = 0;//记录链表中目前的长度

    public boolean isEmpty() {//判断链表是否为空
        return SIZE == 0;
    }

    public void appendElement(PersonNode t) {//链表末端添加新元素t
        PersonNode currentPointer = head;
        for (int i = 0; i < SIZE; i++) {
            currentPointer = currentPointer.next;
        }
        currentPointer.next = t;
        SIZE++;
    }

    public void delElement(PersonNode t) {//删除链表上的t对象
        PersonNode currentPointer = head;
        for (int i = 0; i < SIZE; i++) {
            if (currentPointer.next == t) {
                currentPointer.next = t.next;
                t.next = null;
                SIZE--;
                break;
            }
            currentPointer = currentPointer.next;
            if (i == SIZE - 1) {
                throw new RuntimeException("未找到该元素！");
            }
        }
    }

    public void replaceElement(PersonNode oldT, PersonNode newT) {//用newT替换链表上的oldT元素
        PersonNode currentPointer = head;
        for (int i = 0; i < SIZE; i++) {
            if (currentPointer.next == oldT) {
                if (i != SIZE - 2) {
                    newT.next = currentPointer.next.next;
                }
                currentPointer.next = newT;
                break;
            }
            currentPointer = currentPointer.next;
            if (i == SIZE - 1) {
                throw new RuntimeException("未找到该元素！");
            }
        }
    }

    public void insertElement(PersonNode t, int place) {//将对象t插入在place位置上，即插在原place-1 和 place位置之间
        PersonNode currentPointer = head;
        for (int i = 0; i < place - 1; i++) {
            currentPointer = currentPointer.next;
        }
        t.next = currentPointer.next;
        currentPointer.next = t;
        SIZE++;
    }

    public void SearchElement(PersonNode t) {//搜索链表上的t对象，若有则返回t对象的详细信息
        PersonNode currentPointer = head;
        for (int i = 0; i < SIZE; i++) {
            if (currentPointer.equals(t)) {
                System.out.println("已找到，其详细信息和位置为：");
                System.out.println(i+". "+currentPointer);
                break;
            }
            if(currentPointer.next != null){
                currentPointer = currentPointer.next;
            }
            if(i == SIZE -1){
                throw new RuntimeException("未找到该元素！");
            }
        }
    }

    public void printLinkedList() {//打印链表当前所有元素
        PersonNode currentPointer = head;
        System.out.println("\n链表当前成员有：");
        for (int i = 0; i < SIZE; i++) {
            if (currentPointer.next != null){
            currentPointer = currentPointer.next;
            }
            System.out.println((i+1)+". "+currentPointer);
        }
    }

}

class PersonNode {
    public int age;
    public String name;
    public PersonNode next;

    public PersonNode(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public PersonNode() {
    }

    @Override
    public String toString() {//重写toString方法
        return "PersonNode{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonNode that = (PersonNode) o;
        if (age != that.age) return false;
        if (!name.equals(that.name)) return false;
        return Objects.equals(next, that.next);
    }

}

class Test {
    public static void main(String[] args) {
        SingleLinkedList l = new SingleLinkedList();
        PersonNode p1 = new PersonNode(11, "lzd");
        PersonNode p2 = new PersonNode(19, "wgl");
        PersonNode p3 = new PersonNode(26, "qrx");
        PersonNode p4 = new PersonNode(56, "cy");
        PersonNode p5 = new PersonNode(29, "yhf");
        PersonNode p6 = new PersonNode(28, "zzj");

        l.appendElement(p1);
        l.appendElement(p2);
        l.appendElement(p3);
        l.appendElement(p4);
        l.printLinkedList();

        l.delElement(p2);
        l.printLinkedList();

        l.replaceElement(p4,p5);
        l.printLinkedList();

        l.appendElement(p6);
        l.printLinkedList();

        l.insertElement(p4,3);
        l.printLinkedList();

        l.SearchElement(p1);
    }
}