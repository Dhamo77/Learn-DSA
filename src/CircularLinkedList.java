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
}
