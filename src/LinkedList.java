public class LinkedList<T> {
   private Node head=null;
    private  int size=0;
  private class  Node{
       T value;
       Node head;
       Node next;
       public Node(T value){
           this.value=value;
           next=null;
       }
   }
   public   boolean insertFirst(T value){
      Node newnode = new Node(value);
      if (head==null){
          head=newnode;
          head.next=null;
      }
      else {
          newnode.next=head;
          head=newnode;
      }
      return true;
   }
    public  boolean  add(T value){
      if (head==null){
          insertFirst(value);
          return true;
      }else {
          Node newnode = new Node(value);
          Node current = head;
          while (current.next != null)
              current = current.next;
          current.next = newnode;
      }
        return true;
    }
    public   boolean insertLast(T value){
      return add(value);
    }
   public  void display(){
      Node current = head;
      while (current!=null){
          System.out.print(current.value+" ");
          current=current.next;
      }
       System.out.println();
   }
   // code for Swap nodes in a linked list without swapping data
    public  void swapNodes(T value1,T value2){
      if (value1==value2)
          return;
      Node pre_x=null;
      Node pre_y=null;
      Node curr_x =head;
        Node curr_y =head;
        while (curr_x!=null&&curr_x.value!=value1){
            pre_x=curr_x;
            curr_x=curr_x.next;
        }
        while (curr_y!=null&&curr_y.value!=value2){
            pre_y=curr_y;
            curr_y=curr_y.next;
        }
        if (curr_y==null||curr_x==null)
            return;
        if (pre_x!=null)
            pre_x.next=curr_y;
        else
            head=curr_y;
        if (pre_y!=null)
            pre_y.next=curr_x;
        else
            head=curr_x;
        Node temp = curr_x.next;
        curr_x.next = curr_y.next;
        curr_y.next = temp;
      }
   // code for reverse the linked list
    public  void reverse(){
      Node current=head,pre=null,temp;
      while (current!=null){
          temp=current.next;
        current.next=pre;
        pre=current;
        current=temp;
      }
        head=pre;
    }
   //Merge two sorted linked lists
}
