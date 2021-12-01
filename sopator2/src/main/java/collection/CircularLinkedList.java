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

    public void setLast(CircularNode<E> last) {
        tail.setContent(last.getContent());
    }

    public void setFirst(CircularNode<E> first) {
        tail.getNextNode().setContent(first.getContent());
    }
    
    public void desplazarDerecha() {
        int n = 1;
        Iterator<E> it = this.iterator();
        ArrayList<E> array = new ArrayList<>();

        for(int i = 0; i<this.size(); i++) {
            array.addLast(tail.getContent());
        }
        int i = 0;
        while(it.hasNext()) {
            array.set(n, it.next());
            if (n == this.size()-1) {
                array.set(i, it.next());
                i++;
            } else {
                 n++;
            }
        }
        CircularNode<E> header = tail.getNextNode();
        for(E e:array) {
            header.setContent(e);
            header = header.getNextNode();
            if (header == tail.getNextNode()) {
                break;
            }
        }
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
        } else if (size == 1) {
            CircularNode<E> currentFirst = tail.getNextNode();
            currentFirst.setPrevNode(new_first); // New First <- Circular First
            new_first.setNextNode(currentFirst); // New First -> Current First 
            new_first.setPrevNode(tail); // Tail <- New First
            tail.setNextNode(new_first); // Tail -> New Firts
        } else {
            CircularNode<E> currentFirst = tail.getNextNode();;
            tail.setNextNode(new_first); // Tail -> New First
            new_first.setPrevNode(tail); // Tail <- New First
            new_first.setNextNode(currentFirst); // New First -> Current First
            currentFirst.setPrevNode(new_first); // Current First <- New First
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
        }else if (size == 1) {
            CircularNode<E> currentLast = tail;
            currentLast.setNextNode(new_last); // Current Node -> New last
            currentLast.setPrevNode(new_last); // New Last <- Circular Node
            new_last.setNextNode(currentLast); // New Last -> Current Last 
            new_last.setPrevNode(currentLast); // Current Last <- New Last
            tail = new_last;
        } else {
            CircularNode<E> currentLast = tail;
            CircularNode<E> header = tail.getNextNode();
            currentLast.setNextNode(new_last);
            new_last.setPrevNode(currentLast);
            new_last.setNextNode(header);
            header.setPrevNode(new_last);
            tail = new_last;
            
        }
        size++;
        return true;
    }

   @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            E tmp = tail.getNextNode().getContent();
            tail.setContent(null);
            tail = null;
            size--;
            return tmp;
        } else {
            CircularNode<E> currentFirst = tail.getNextNode();
            CircularNode<E> nextHeader = tail.getNextNode().getNextNode();
            tail.setNextNode(nextHeader);
            nextHeader.setPrevNode(tail);
            size--;
            return currentFirst.getContent();
        }
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            E tmp = tail.getContent();
            tail.setContent(null);
            tail = null;
            size--;
            return tmp;
        } else {
            CircularNode<E> currentLast = tail;
            CircularNode<E> header = tail.getNextNode();
            CircularNode<E> prevLast = tail.getPrevNode();
            prevLast.setNextNode(header);
            header.setPrevNode(prevLast);
            tail = prevLast;            
            size--;
            return currentLast.getContent();
        }
    }

    @Override
    public int size() {
    return size;
    }

    @Override
    public void clear() {
        
//        CircularNode<E> tmp;
//        for (int i = 0; i<size; i++) {
//            tmp = getNode(i);
//            tmp.setContent(null);
//        }
        
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean add(int index, E element) {
        if (index == 0) {
            return addFirst(element);
        } else if (index > size || index < 0) {
            return false;
        } else if (index == size) {
            return addLast(element);
        } else {
            CircularNode<E> newNode = new CircularNode(element);
            CircularNode<E> currentNode = getNode(index);
            CircularNode<E> prevNode = getNode(index-1);
            prevNode.setNextNode(newNode); // Prev Node -> New Node
            newNode.setPrevNode(prevNode); // Prev Node <- New Node
            newNode.setNextNode(currentNode); // New Node -> Current Node
            currentNode.setPrevNode(newNode); // New Node <- Current Node
            size++;
            return true;
        }
    }
    
    private CircularNode<E> getNode(int index) {
        CircularNode<E> tmp = tail;
        if (index <= size / 2) {
            for (; index > 0; index--) {
                tmp = tmp.getNextNode();
            }
        } else if (index > size / 2) {
            index = size - index;
            for (; index > 0; index--) {
                tmp = tmp.getPrevNode();
            }
        }
        return tmp;
    }
    
    
//    private E unlink(CircularNode<E> cn) {
//        CircularNode<E> prevNode = cn.getPrevNode();
//        CircularNode<E> nextNode = cn.getNextNode();
//        if (prevNode != null) {
//            return nextNode.setPrevNode(prevNode);
//        }
//        if (nextNode != null) {
//            return prevNode.setNextNode(nextNode);
//        }
//        return cn.getContent();
//    }
    
    @Override
    public E remove(int index) {
        if (isEmpty() || index > size || index < 0) {
            return null;
        } else if (index == size - 1) {
            return removeLast();
        } else if (index == 0) {
            return removeFirst();
        } else {
            CircularNode<E> currentNode = getNode(index);
            CircularNode<E> prevNode = currentNode.getPrevNode();
            CircularNode<E> nextNode = currentNode.getNextNode();
            prevNode.setNextNode(nextNode);
            nextNode.setPrevNode(prevNode);
            return currentNode.getContent();
        }
    }

    @Override
    public E get(int index) {
        if (index >= size) {
            return null;
        } else if (index == 0) {
            return tail.getNextNode().getContent();
        } else if (index == size-1) {
            return tail.getContent();
        } else {
            return getNode(index).getContent();
        }
    }

    @Override
    public E set(int index, E element) {
        CircularNode<E> to_set = new CircularNode(element);
        if (index == 0) {
            setFirst(to_set);
            return element;
        } else if (index > size || index < 0 || element == null) {
            return null;
        } else if (index == size - 1) {
            setLast(to_set);
            return element;
        } else {
            CircularNode<E> current = getNode(index);
            current.setContent(element);
            return element;
        }
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
    public String toString() {
        String result = "";
        Iterator<E> it = this.iterator();
        while(it.hasNext()) {
            result += it.next() + " ";
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator() {
            CircularNode<E> cursor = tail.getNextNode();
            int c = 0;
            @Override
            public boolean hasNext() {
                return (c < size);
            }
            @Override
            public E next() {
                E content = cursor.getContent();
                cursor = cursor.getNextNode();
                c++;
                return content;
            }
        };
        return it;
    }

    @Override
    public List<E> findAll(Comparator<E> cmp, E e) {
        if (cmp == null)
           throw new IllegalArgumentException("Comparator cannot be null");
        List<E> container = new CircularLinkedList<>();
        for (E item : this) {
            if(cmp.compare(item, e) == 0)
                container.addLast(item);
        }
        return container;
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
