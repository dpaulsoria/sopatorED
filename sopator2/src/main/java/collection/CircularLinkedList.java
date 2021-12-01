/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author danny
 * @param <E>
 */
public class CircularLinkedList<E> implements List<E> {

    private CircularNode<E> tail;
    private int size= 0;
    
    public CircularLinkedList(CircularNode<E> tail, int size) {
        this.tail = tail;
    }
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }
    public CircularNode<E> getLast() {
        return this.tail;
    }
    
    public CircularNode<E> getFirst() {
        return this.tail.getNextNode();
    } 

    public void setLast(CircularNode<E> last) {
        tail.setContent(last.getContent());
    }

    public void setFirst(CircularNode<E> first) {
        tail.getNextNode().setContent(first.getContent());
    }
    
    public void desplazarDer() {
        tail = tail.getPrevNode();
    }
    
    public void desplazarIzq() {
        tail = tail.getNextNode();
    }
    private CircularNode<E> getNode(int index) {
        CircularNode<E> last = tail;
        if (index <= size / 2) {
            for (; index > 0; index--) {
                last = last.getNextNode();
            }
        } else if (index > size / 2) {
            index = size - index;
            for (; index > 0; index--) {
                last = last.getPrevNode();
            }
        }
        return last;
    }
    
    @Override
    public boolean isEmpty() {
       return (this.size() == 0);
    }
    
    @Override
    public boolean addFirst(E e) {
       if(e != null){
            if(size() == 0) {
                tail = new CircularNode(e);
            } else {
                CircularNode<E> newFirst = new CircularNode(e);  
                newFirst.setNextNode(tail.getNextNode());
                newFirst.setPrevNode(tail);
                tail.getNextNode().setPrevNode(newFirst);
                tail.setNextNode(newFirst);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addLast(E e) {
        if (addFirst(e)) {
            System.out.println("addlast");
            tail = tail.getNextNode();
            return true;
        } else return false;
    }

    @Override
    public int size() {
        int c = 0; // Contador para el size
        if (tail != null)
            c = 1;
        else return c;
        
        CircularNode<E> cursor = tail.getNextNode();
        while(cursor != tail){
            cursor = cursor.getNextNode();
            c++;
        }
        return c;
    }

    @Override
    public void clear() {
        this.tail = null;
        this.size = 0;
    }
    @Override
    public E removeFirst() {
        if(isEmpty())
            return null;
        
        CircularNode<E> head = tail.getNextNode();
        tail.setNextNode(head.getNextNode());
        head.getNextNode().setPrevNode(tail);
        
        return head.getContent();
    }
    @Override
    public E removeLast() {
        if(isEmpty()) 
            return null;
        
        CircularNode<E> currentLast = tail;
        tail.getPrevNode().setNextNode(tail.getNextNode());
        tail.getNextNode().setPrevNode(tail.getPrevNode());
        
        tail = tail.getPrevNode();
        return currentLast.getContent();
    }

    @Override
    public boolean add(int index, E element) {
        System.out.println("size:" + size());
        if(element == null)
            return false;
        if(index > size() || index < 0)
            return false;
        if(index == 0) {
            return addFirst(element);
        }
        if(index == size()-1) 
            return addLast(element);         
        CircularNode<E> new_node = new CircularNode(element);   
        CircularNode<E> current = getNode(index); 
        new_node.setPrevNode(current.getPrevNode());
        current.getPrevNode().setNextNode(new_node);
        new_node.setNextNode(current);
        current.setPrevNode(new_node);        
        return true;
    }
    
    @Override
    public E remove(int index) {
        if(isEmpty() || index > size() || index < -1) 
            return null;
        if(index == 0) 
            return removeFirst();
        if(index == size()-1) 
            return removeLast();
        
        CircularNode<E> current = getNode(index);
        current.getPrevNode().setNextNode(current.getNextNode());
        current.getNextNode().setPrevNode(current.getPrevNode());
        
        return current.getContent();
    }

    @Override
    public E get(int index) {
        return getNode(index).getContent();
    }

    @Override
    public E set(int index, E element) {
        if(isEmpty() || index > size() || index < 0)
            return null;        
        CircularNode<E> current = getNode(index);
        E tmp = current.getContent();
        current.setContent(element);        
        return tmp;
    }
    
    @Override
    public String toString() {
        if (isEmpty())
            return "";
        String result = "";
        CircularNode<E> cursor = tail;
        result += cursor.getContent() + " "; 
        cursor = cursor.getNextNode();
        while(cursor != tail) {            
            result += cursor.getContent().toString() + " ";
            cursor = cursor.getNextNode();
        }
        
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator() {
            CircularNode<E> cursor = tail.getNextNode();
            @Override
            public boolean hasNext() {
                return (cursor != null);
            }
            @Override
            public E next() {
                if (cursor == tail) {
                    cursor = null;
                    return tail.getContent();
                }
                E content = cursor.getContent();
                cursor = cursor.getNextNode();
                return content;
            }
        };
        return it;
    }
    
}
