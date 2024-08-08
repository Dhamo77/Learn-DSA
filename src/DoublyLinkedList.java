public class DoublyLinkedList<T extends Comparable<T>> {
    private  int size =0;
    private Node<T> head;
    private static class Node<T>{
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
        size++;
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
    public boolean add(T value){
        if (head==null){
            return insertFirst(value);
        }
        Node<T> newnode =new Node<>(value);
        Node<T> current =head;
        while (current.next!=null){
            current=current.next;
        }
        current.next=newnode;
        newnode.previous=current;
        size++;
        return true;
    }
    public boolean addLast(T value){
        return add(value);
    }
    public T deleteLast(){
        Node<T> current =head;
        T value = null;
        if (current==null||current.next==null){
            if (head != null) {
                value = head.value;
                size--;
            }
            head=null;
            return value;
        }
        while (current.next.next!=null){
            current=current.next;
        }
         value = current.next.value;
        current.next=null;
        size--;
        return value;
    }
    public T deleteFirst(){
        Node<T> current =head;
        T value = null;
        if (current==null||current.next==null){
            if (head!=null){
                value= head.value;
                size--;
            }
            head=null;
            return value;
        }
        value=current.value;
        head=current.next;
        size--;
        return value;
    }
    public int size(){
        return size;
    }
    public void displayReverse(){
        Node<T> current =head;
        while (current.next!=null)
            current=current.next;
        System.out.print("[ ");
        while (current!=null){
            System.out.print(current.value+" ");
            current=current.previous;
        }
        System.out.print("]");
        System.out.println();
    }
    // method for reverse the DoublyLinkedList
    public void reverse(){
        Node<T> current =head,temp=null;
        while (current!=null){
            temp=current.previous;
            current.previous= current.next;
            current.next=temp;
            current=current.previous;
        }
        if (temp != null) {
            head = temp.previous;
        }
    }

    private Node<T> getTail(Node<T> head) {
        Node<T> current =head;
        while (current.next!=null){
            current=current.next;
        }
        return current;
    }
    private Node<T> getMid(Node<T> head,Node<T> tail){
        Node<T> slow=head,fast=head;
      while (fast!=tail&&fast.next!=tail){
          slow=slow.next;
          fast=fast.next.next;
      }
        return slow;
    }
    public void swapNode(T value1 ,T value2){
        if (head==null||head.next==null||value1==value2){
            return;
        }
        Node<T> nodeX=head;
        Node<T> nodeY=head;


        while (nodeX.value!=value1&&nodeX.next!=null){
            nodeX=nodeX.next;
        }
        while (nodeY.value!=value2&&nodeY.next!=null){
            nodeY=nodeY.next;
        }
        if (nodeX==null||nodeY==null){
            return;
        }
        if (nodeX.previous!=null){
            nodeX.previous.next=nodeY;
        }
        else
            head = nodeY;
        if (nodeY.previous!=null){
            nodeY.previous.next=nodeX;
        }
        else
            head = nodeX;

        Node<T> temp=nodeX.next;
        nodeX.next=nodeY.next;
        nodeY.next=temp;
    }
}
