/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.sopaletras;

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
    
    public CircularNode<E> getLast() {
        return this.tail;
    }
    
    public CircularNode<E> getFirst() {
        return this.tail.getNextNode();
    } 

    public void setLast(CircularNode<E> tail) {
        this.tail = tail;
    }
    
    public boolean isEmpty() {
       return (this.size == 0);
    }
    
    @Override
    public boolean addFirst(E e) {
       CircularNode<E> nuevo = new CircularNode(e);
        if (isEmpty()) {
            tail = nuevo;
            tail.setNextNode(nuevo);
            //tail.setPrevNode(nuevo);
        } else {
            //tail.setPrevNode().setNext(nuevo);
            nuevo.setNextNode(tail);
            //nuevo.setPrev(tail.setPrevNode());
        }
        size++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
    CircularNode<E> nuevo = new CircularNode(e);
        if (isEmpty()) {
            nuevo.setNextNode(nuevo);
            //nuevo.setPrevNode(nuevo);
            tail = nuevo;
        }else {
            //nuevo.setPrevNode(tail);
            tail.setNextNode(nuevo);
            //tail.setNextNode().setPrevNode(nuevo);
            //nuevo.setNext(tail.setNextNode());
            tail = nuevo;
        }
        size++;
        return true;
    }

    }

    @Override
    public E removeFirst() {
    if (isEmpty()) {
            return null;
        }
        E primero = tail.getNextNode().getElement();
        tail.setNextNode(tail.getNextNode().getNextNode());
        //tail.getNextNode().getNextNode().setPrevNode(tail);
        size--;
        if (size == 1) {
            tail = null;
        }
        return primero;
    }

    @Override
    public E removeLast() {
            if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            E temp = tail.getElement();
            tail = null;
            size--;
            return temp;
        }
        CircularNode<E> newtail = tail.getPrev();
        E temp = tail.getElement();
        newtail.setNextNode(tail.getNextNode());
        tail.getNextNode().setPrevNode(newtail);
        tail = newtail;
        size--;
        return temp;
    }

    @Override
    public int size() {
    return size;
    }

    @Override
    public boolean isEmpty() {
    return size == 0;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(int index, E element) {
    if (index == 0) {
            addFirst(element);
        } else if (index > size || index < 0) {
            return false;
        } else if (index == size - 1) {
            addLast(element);
        } else {
            int pos = 0;
            for (CircularNode<E> e = tail.getNextNode(); e != tail; e = e.getNextNode()) {
                if (pos == index) {
                    size++;
                    CircularNode<E> temp = e.getNextNode();
                    CircularNode<E> nuevo = new CircularNode(element);
                    e.getPrevNode().setNextNode(nuevo);
                    nuevo.setNextNode(e);
                    nuevo.setPrevNode(e.getPrevNode());
                    e.setPrevNode(nuevo);
                    return true;
                }
                pos++;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
    if (isEmpty()) {
            return null;
        }
        if (i > size || i < 0) {
            return null;
        }
        if (i == size - 1) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        int pos = 0;
        for (CircularNode<E> e = tail.getNextNode(); e.getNextNode() != tail; e = e.getNextNode()) {
            if (pos == i) {
                e.getPrevNode().setNextNode(e.getNextNode());
                //e.getNextNode().setPrevNode(e.getPrevNode());
                size--;
                return e.getElement();
            }
            pos++;
        }
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E insertAt(E[] elements, int position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> findAll(Comparator<E> cmp, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        List.super.forEach(action); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Spliterator<E> spliterator() {
        return List.super.spliterator(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
