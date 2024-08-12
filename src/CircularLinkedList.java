import java.awt.*;

public class CircularLinkedList<T extends Comparable<T>> {
    private Node<T> head,tail;
    private  int size=0;
    private  static class Node<T>{
        T value;
        Node<T> next;
        public Node(T value){
            this.value=value;
        }
    }
    public void insertFirst(T value){
        Node<T> newNode = new Node<>(value);
        if (head==null){
            head=newNode;
            tail=newNode;
            head.next=tail;
        }
        else {
            newNode.next=head;
            tail.next=newNode;
            head=newNode;
        }
        size++;
    }
    public void add(T value){
        if (head==null){
            insertFirst(value);
        }
        Node<T> newNode = new Node<>(value);
        tail.next=newNode;
        tail=newNode;
        tail.next=head;
        size++;
    }
    public void display(){
        Node<T> current =head;
        System.out.print("[ ");
        do {
            System.out.print(current.value+" ");
            current=current.next;
        }while (current!=head);
        System.out.println("]");

    }
    //function Split a Circular Linked List into two halves
    private Node<T> getMid(){
        Node<T> slow=head,fast=head;
        do {
            slow=slow.next;
            fast=fast.next.next;
        }while (fast.next!=head&&fast.next.next!=head);
        return slow;
    }
    public void splitTwoHalves(){
        Node<T> currentHead=head;
        Node<T> mid=getMid();
        Node<T> head2=mid.next;
        mid.next=currentHead;
        tail.next=head2;
        diplay(currentHead);
        diplay(head2);
        mid.next=head2;
        tail.next=currentHead;
    }

    private void diplay(Node<T> head) {
        Node<T> current=head;
        System.out.print("[ ");
        do {
            System.out.print(current.value+" ");
            current=current.next;
        }while (current!=head);
        System.out.println("]");

    }
}
