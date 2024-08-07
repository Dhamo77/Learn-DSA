public class DoublyLinkedList<T extends Comparable<T>> {
    Node<T> head;
    static class Node<T>{
        T value;
        Node<T> next,previous;
        public Node(T value){
            this.value=value;
            this.next=null;
            this.previous=null;
        }
    }
    public boolean insertFirst(T value){
        Node<T> newnode = new Node<>(value);
        if (head==null){
            head=newnode;
            head.previous=null;
            head.next=null;
        }
        else{
            head.previous=newnode;
            newnode.next=head;
            head=newnode;
        }
        return true;
    }
    public void display(){
        Node<T> current=head;
        System.out.print("[ ");
        while (current!=null){
            System.out.print(current.value+" ");
            current=current.next;
        }
        System.out.println("]");
    }
}
