// implement the stack using circular list
public class Stack_LinkedList<T extends Comparable<T>> {
    Node<T> head;
    private int size;
    private static class Node<T>{
        T value;
         Node<T> next;
        public Node(T value){
            this.value=value;
            this.next=null;
        }
    }
    public void push(T value){
        Node<T> newnode =new Node<>(value);
        if (head == null) {
           head=newnode;
           head.next=head;
        }
       else {
          Node<T> current =head;
          while (current.next!=head){
              current=current.next;
          }
          newnode.next=head;
          head=newnode;
          current.next=head;
        }

        size++;
    }
    public T peek(){
        if (head==null){
            return null;
        }
        return head.value;
    }
    public T pop(){
        if (head==null){
            return null;
        }
        T value= head.value;
        Node<T> current =head;
        while (current.next!=head){
            current=current.next;
        }
        head = head.next;
        current.next=head;
        size--;
        return value;
    }
    public  int size(){
        return size;
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
    // Reverse the stack without extra space
    public  void reverse(){
        if (head == null || head.next == head) {
            return;
        }
        Node<T> current =head,prev=head,next;
        do {
            next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }while (current!=head);
        head.next=prev;
        head=prev;
    }
}
