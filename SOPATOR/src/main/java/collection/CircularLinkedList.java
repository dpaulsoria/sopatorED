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
        this.size = size;
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

    public void setLast(CircularNode<E> tail) {
        this.tail.getPrevNode().setNextNode(tail);
        tail.setPrevNode(this.tail.getPrevNode());
        tail.setNextNode(this.tail.getNextNode());
        this.tail.getNextNode().setPrevNode(tail);
        this.tail = tail;        
    }

    public void setFirst(CircularNode<E> first) {
        this.tail.getNextNode().getNextNode().setPrevNode(first);
        first.setNextNode(this.tail.getNextNode().getNextNode());
        this.tail.setNextNode(first);
        first.setPrevNode(this.tail);
    }
    
    @Override
    public boolean isEmpty() {
       return (this.size == 0);
    }
    
    @Override
    public boolean addFirst(E e) {
       CircularNode<E> new_first = new CircularNode(e);
        if (isEmpty()) {
            tail = new_first;
            tail.setNextNode(new_first);
            tail.setPrevNode(new_first);
        } else {
            tail.getNextNode().setPrevNode(new_first);
            new_first.setNextNode(tail.getNextNode());
            tail.setNextNode(new_first);
        }
        size++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
    CircularNode<E> new_last = new CircularNode(e);
        if (isEmpty()) {
            new_last.setNextNode(new_last);
            new_last.setPrevNode(new_last);
            tail = new_last;
        }else {
            tail.getNextNode().setPrevNode(new_last);
            new_last.setNextNode(tail.getNextNode());
            tail.setNextNode(new_last);
            tail = new_last;            
        }
        size++;
        return true;
    }

   @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E first = tail.getNextNode().getContent();
        tail.setNextNode(tail.getNextNode().getNextNode());
        tail.getNextNode().getNextNode().setPrevNode(tail);
        size--;
        if (size == 1) {
            E tmp = tail.getContent();
            tail = null;
            size--;
            return tmp;
        }
        return first;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            E tmp = tail.getContent();
            tail = null;
            size--;
            return tmp;
        }
        CircularNode<E> new_last = tail.getPrevNode();
        E tmp = tail.getContent();
        new_last.setNextNode(tail.getNextNode());
        tail.getNextNode().setPrevNode(new_last);
        tail = new_last;
        size--;
        return tmp;
    }

    @Override
    public int size() {
    return size;
    }

    @Override
    public void clear() {
        this.tail = null;
        this.size = 0;
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
                    // CircularNode<E> tmp = e.getNextNode();
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
        if (index > size || index < 0) {
            return null;
        }
        if (index == size - 1) {
            return removeLast();
        }
        if (index == 0) {
            return removeFirst();
        }
        int pos = 0;
        for (CircularNode<E> e = tail.getNextNode(); e.getNextNode() != tail; e = e.getNextNode()) {
            if (pos == index) {
                e.getPrevNode().setNextNode(e.getNextNode());
                e.getNextNode().setPrevNode(e.getPrevNode());
                size--;
                return e.getContent();
            }
            pos++;
        }
        return null;
    }

    @Override
    public E get(int index) {
        int pos = 0;
        if (index == 0) {
            return tail.getNextNode().getContent();
        } else if (index == this.size() - 1) {
            return tail.getContent();
        }
        for (CircularNode<E> e = tail.getNextNode(); e != tail; e = e.getNextNode()) {
            if (index == pos) {
                return e.getContent();
            }
            pos++;
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        CircularNode<E> to_set = new CircularNode(element);
        if (index == 0) {
            setFirst(to_set);
            return element;
        } else if (index > size || index < 0) {
            return null;
        } else if (index == size - 1) {
            setLast(to_set);
            return element;
        } else {
            int pos = 0;
            for (CircularNode<E> e = tail.getNextNode(); e != tail; e = e.getNextNode()) {
                if (pos == index) {
                    size++;
                    e.getPrevNode().setNextNode(to_set);
                    to_set.setPrevNode(e.getPrevNode());
                    e.getNextNode().setPrevNode(to_set);
                    to_set.setNextNode(e.getNextNode());
                    e = null;
                    return element;
                }
                pos++;
            }
        }
        return null;
    }

    @Override
    public E insertAt(E[] elements, int position) {
        if (position == 0) {
            for(int i=elements.length-1; i>-1; i--) {
                addFirst(elements[i]);
            }
            return elements[0];
        } else if (position > size || position < 0) {
            return null;
        } else if (position == size - 1) {
            for (E element : elements) {
                addLast(element);
            }
            return elements[0];
        } else {
            int pos = 0;
            for (CircularNode<E> e = tail.getNextNode(); e != tail; e = e.getNextNode()) {
                if (pos == position) {
                    for (E element : elements) {
                        this.add(pos, element);
                        size++;
                    }
                    this.add(pos+elements.length, e.getContent());
                    return e.getContent();
                }
                pos++;
            }
        }
        return null;
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