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
       return (this.size == 0);
    }
    
    @Override
    public boolean addFirst(E e) {
       CircularNode<E> new_first = new CircularNode(e);
        if (isEmpty()) {
            tail = new_first;
            tail.setNextNode(tail);
            tail.setPrevNode(tail);
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
            tail = new_last;
            tail.setNextNode(tail);
            tail.setPrevNode(tail);
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
    public int size() {
    return size;
    }

    @Override
    public void clear() {
        this.tail = null;
        this.size = 0;
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
        for (CircularNode<E> e = tail.getNextNode(); pos<size; e = e.getNextNode()) {
            if (pos == index) {
                E tmp = e.getContent();
                e.getPrevNode().setNextNode(e.getNextNode());
                e.getNextNode().setPrevNode(e.getPrevNode());
                size--;
                return tmp;
            }
            pos++;
        }
        return null;
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
            CircularNode<E> nuevo = new CircularNode(element);
            CircularNode<E> current = getNode(index);
            current.getPrevNode().setNextNode(nuevo);
            nuevo.setNextNode(current);
            nuevo.setPrevNode(current.getPrevNode());
            current.setPrevNode(nuevo);
            return true;
//            int pos = 0;
//            for (CircularNode<E> e = tail.getNextNode(); pos<size; e = e.getNextNode()) {
//                if (pos == index) {
//                    size++;
//                    e.getPrevNode().setNextNode(nuevo);
//                    nuevo.setNextNode(e);
//                    nuevo.setPrevNode(e.getPrevNode());
//                    e.setPrevNode(nuevo);
//                    return true;
//                }
//                pos++;
//            }
        }
    }

    @Override
    public E get(int index) {
        return getNode(index).getContent();
//        if (index >= size) {
//            return null;
//        } else if (index == 0) {
//            return tail.getNextNode().getContent();
//        } else if (index == size-1) {
//            return tail.getContent();
//        } else {
//            int pos = 0;
//            for (CircularNode<E> e = tail.getNextNode(); pos < size; e = e.getNextNode()) {
//                if (index == pos) {
//                    return e.getContent();
//                }
//                pos++;
//            }
//        }
//        return null;
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
            CircularNode<E> current = tail.getNextNode();
            while(pos < size) {
                if (pos == index) {
                    current.setContent(element);
                    return element;
                }     
                pos++;
                current = current.getNextNode();
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
