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
    
    @Override
    public boolean addFirst(E e) {
       Node<E> nuevo = new Node(e);
        if (isEmpty()) {
            tail = nuevo;
            tail.setNext(nuevo);
            tail.setPrev(nuevo);
        } else {
            tail.getPrev().setNext(nuevo);
            nuevo.setNext(tail);
            nuevo.setPrev(tail.getPrev());
        }
        size++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
    Node<E> nuevo = new Node(e);
        if (isEmpty()) {
            nuevo.setNext(nuevo);
            nuevo.setPrev(nuevo);
            tail = nuevo;
        }else {
            nuevo.setPrev(tail);
            tail.setNext(nuevo);
            tail.getNext().setPrev(nuevo);
            nuevo.setNext(tail.getNext());
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
        E primero = tail.getNext().getElement();
        tail.setNext(tail.getNext().getNext());
        tail.getNext().getNext().setPrev(tail);
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
        Node<E> newtail = tail.getPrev();
        E temp = tail.getElement();
        newtail.setNext(tail.getNext());
        tail.getNext().setPrev(newtail);
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
            for (Node<E> e = tail.getNext(); e != tail; e = e.getNext()) {
                if (pos == index) {
                    size++;
                    Node<E> temp = e.getNext();
                    Node<E> nuevo = new Node(element);
                    e.getPrev().setNext(nuevo);
                    nuevo.setNext(e);
                    nuevo.setPrev(e.getPrev());
                    e.setPrev(nuevo);
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
        for (Node<E> e = tail.getNext(); e.getNext() != tail; e = e.getNext()) {
            if (pos == i) {
                e.getPrev().setNext(e.getNext());
                e.getNext().setPrev(e.getPrev());
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
