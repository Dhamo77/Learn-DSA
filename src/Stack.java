public class Stack<T extends Comparable<T>> {
    private Object[] array;
    private final int capacity=10;
    private  int size;
    public Stack(){
        array =new Object[capacity];
    }
    public void push(T value){
        if (size== array.length){
            resize();
        }
        array[size++]=value;
    }
    private void resize(){
        Object[] temp =new Object[2*array.length];
        System.arraycopy(array, 0, temp, 0, array.length);
        array= temp;
    }
    public T peek(){
        if (isEmpty()){
            return null;
        }
        T value = (T) array[size-1];
        return value;
    }
    public T pop(){
        if (isEmpty()){
            return null;
        }
        T value = (T) array[--size];
        array[size] = null;
        return  value;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void display(){
        System.out.print("[ ");
        for (int i=0;i<size;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println("]");
    }
}
