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
   }
   //write a code for Swap nodes in a linked list without swapping data
   
}
