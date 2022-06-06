
package Payment;

import java.util.LinkedList;

public class Queue<E> {
    
    private java.util.LinkedList<E> list = new java.util.LinkedList<>();
    
    public Queue(E[] e){
        list = new LinkedList<>();
        for (int i = 0; i < e.length; i++) {
            list.addLast(e[i]);
        }
    }

    public Queue() {
        list = new LinkedList<>();
    }
    
    public void enqueue(E e){
        list.addLast(e);
    }
    
    public E dequeue(){
        if (list.isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }
    
    public E getElement(int i){
        if (i<0 || i>list.size()) {
            return null;
        }
        return list.get(i);
    }
    
    public E peek(){
        return list.getFirst();
    }
    
    public int getSize(){
        return list.size();
    }
    
    public boolean contains(E e){
        return list.contains(e);
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
    
}
