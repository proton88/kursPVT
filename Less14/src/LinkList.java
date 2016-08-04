import java.util.NoSuchElementException;

/**
 * Created by User on 28.07.2016.
 */
public class LinkList<T> {
    private Node top;
    private Node tail;
    private Node prev;
    private int size;

    public LinkList() {
        tail=new Node();
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public Node getTop() {
        return top;
    }

    public boolean isEmpty() {
        if (top == null)
            return true;
        return false;
    }

    public void add(T elm) {
        Node nod = new Node();
        nod.setElement(elm);
        nod.setNext(null);
        tail.setNext(nod);
        tail=nod;
        if (top==null){
            top=nod;
        }
        size++;
    }

    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T obj=(T)tail.getElement();

        if (size==1){
            top=null;
            tail=null;
            size--;
            return obj;
        }
        else {
            prev = top;
            while (prev.getNext().getNext() != null) {
                prev = prev.getNext();
            }
            tail = prev;
            tail.setNext(null);
        }
        size--;
        return obj;
    }

    public void push(T elm) {
        Node nod = new Node();
        nod.setElement(elm);
        nod.setNext(top);
        top = nod;
        size++;

    }

    public T top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return (T)top.getElement();
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T obj = (T)top.getElement();
        top = top.getNext();
        size--;
        return obj;
    }

    private class Node<T> {
        private T element;
        private Node next;

        public Node() {

        }

        public Node(T element, Node next) {
            this.next = next;
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public Node getNext() {
            return next;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }
}
