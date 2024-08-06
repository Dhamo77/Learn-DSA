public class LinkedList<T extends Comparable<T> > {
   private Node<T> head;
    private  int size=0;
    public int size(){
        return size;
    }
  private static class  Node<T extends Comparable<T>> {
       T value;
       Node<T> head;
       Node next;
       public Node(T value){
           this.value= value;
           this.next=null;
       }
   }
   public   boolean insertFirst(T value){
      Node<T> newnode = new Node<>(value);
      if (head==null){
          head=newnode;
          head.next=null;
      }
      else {
          newnode.next=head;
          head=newnode;
      }
       size++;
       return true;
   }
    public  boolean  add(T value){
      if (head==null){
          insertFirst(value);
          return true;
      }else {
          Node<T> newnode = new Node<>(value);
          Node<T> current = head;
          while (current.next != null)
              current = current.next;
          current.next = newnode;
          size++;
      }
        return true;
    }
    public   boolean insertLast(T value){
      return add(value);
    }
   public  void display(){
      Node<T> current = head;
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
      Node<T> pre_x=null;
      Node<T> pre_y=null;
      Node<T> curr_x =head;
        Node<T> curr_y =head;
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
      Node<T> current=head,pre=null,temp;
      while (current!=null){
          temp=current.next;
        current.next=pre;
        pre=current;
        current=temp;

      }
        head=pre;
    }
   //Merge two sorted linked lists
    public LinkedList<T> merge(LinkedList<T> list1,LinkedList<T> list2){
        LinkedList<T> temp=new LinkedList<>();
        Node<T> l1=list1.head;
        Node<T> l2=list2.head;
        if (l1.value.compareTo(l2.value) <= 0) {
            temp.head = l1;
            l1 = l1.next;
        } else {
            temp.head = l2;
            l2 = l2.next;
        }
        Node<T> dummy =temp.head;
        while (l1 != null && l2 != null) {
            if (l1.value.compareTo(l2.value) <= 0) {
                dummy.next = l1;
                l1 = l1.next;
            } else {
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }
        if (l1 != null) {
            dummy.next = l1;
        } else {
            dummy.next = l2;
        }
        return temp;
    }
   
   //  the below function for merge sort in Linked List
    public void mergeSort(){
      head= mergeSort(head);
    }
    private Node<T> mergeSort(Node<T> h){
        if (h==null||h.next==null){
            return h;
        }
        Node<T> middle = get_middle(h);
        Node<T> mid_nex=middle.next;
        middle.next=null;
        Node<T> left = mergeSort(h);
        Node<T> right= mergeSort(mid_nex);
        return merge_node(left,right);
    }
    private Node<T> get_middle(Node<T> h){
        if (h==null)
            return null;
        Node<T> slow=h,fast=h;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    private Node<T> merge_node(Node<T> n1,Node<T> n2){
        if (n1 == null)
            return n2;
        if (n2 == null)
            return n1;
        Node<T> result;
        if (n1.value.compareTo(n2.value) <= 0) {
            result = n1;
            result.next = merge_node(n1.next, n2);
        } else {
            result = n2;
            result.next = merge_node(n1, n2.next);
        }
        return result;
    }
   // function for Reverse a Linked List in groups of given size
    public void reverse(int size){
        if (size>=size()){
            reverse();
            return;
        }
        Node<T> getnode=getnode(head,size);
        Node<T> get_next=reverse(getnode.next);
        getnode.next=null;
        reverse();
        Node current =head;
        while (current.next!=null){
            current=current.next;
        }
        current.next=get_next;
    }

    private Node<T> getnode(Node<T> head, int size) {
        Node current =head;
        for (int i=1;i<=size;i++){
            if (i==size)
                return current;
            current=current.next;
        }
        return current;
    }
    private Node<T> reverse(Node<T> h){
        Node<T> current=h,pre=null,temp;
        while (current!=null){
            temp=current.next;
            current.next=pre;
            pre=current;
            current=temp;
        }
        return pre;
    }
   // function for Detect and Remove Loop in a Linked List
    public  boolean detectLoop(){
      return findLoop(head);
    }
    private boolean findLoop(Node<T> h){
        Node<T> slow=h,fast=h;
        while (slow!=null&&fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                deleteLoop(slow,h);
                return true;
            }
        }
        return false;
    }
    private void deleteLoop(Node<T> slow, Node<T> h) {
        Node<T> p1=slow,p2=slow,temp=p1;
        int k=1;
        while (p1.next!=p2){
            temp=p1;
            p1=p1.next;
            k++;
        }
        temp.next=null;
    }
   // function for Rotate a Linked List
    public void rotateList(int k){
        if (head == null || k == 0) {
            return;
        }
        Node<T> position = getnode(head,k);
        Node<T> newHead=position.next;
        position.next=null;
        Node<T> current =newHead;
        while (current.next!=null){
            current=current.next;
        }
        current.next=head;
        head=newHead;
    }
   // write a function for Add two numbers represented by Linked List
}
