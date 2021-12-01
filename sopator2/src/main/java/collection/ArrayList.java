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


public class ArrayList<E> implements List<E> {

    // arreglo de elementos
    private int capacity = 100;
    private E[] elements = null;
    private int effectiveSize = 0;

    public ArrayList() {
        this.elements = (E[]) (new Object[capacity]);
        this.effectiveSize = 0;
    }

    private boolean isFull() {
        return this.elements.length == effectiveSize;
    }

    private void addCapacity() {
        E[] tmp = (E[]) new Object[this.capacity * 2];
        for (int i = 0; i < this.capacity; i++) {
            tmp[i] = this.elements[i];
        }
        this.elements = tmp;
        this.capacity = this.capacity * 2;
    }

    @Override
    public boolean addFirst(E element) {
        if(element == null){
            return false;
        }else if(this.isEmpty()){
            this.elements[this.effectiveSize] = element;
            this.effectiveSize++;
            return true;
        }else if (this.capacity == this.effectiveSize){
            addCapacity();
        }        
        for(int i = this.effectiveSize -1 ; i > -1; i-- ){
            this.elements[i+1] = this.elements[i];
        } 
        this.elements[0] = element;
        this.effectiveSize++;
        return true;   
    }

    @Override
    public boolean addLast(E element) {
        if(element == null){
            return false;
        } else if(this.isEmpty()) {
            this.elements[this.effectiveSize] = element;
            this.effectiveSize++;
            return true;
        } else if(this.capacity == this.effectiveSize){
            this.addCapacity();
        }
        this.elements[this.effectiveSize] = element;
        this.effectiveSize++;
        return true;
    }

    @Override
    public E removeFirst() {
        E tmp = this.elements[0];
        if (this.isEmpty())
            return tmp;
        for (int i = 0; i <= this.size(); i++) {
            this.elements[i] = this.elements[i+1];
        }
        this.elements[0] = null;
        for(int i = 1; i < this.size(); i++) {
            this.elements[i-1] = this.elements[i];
        }
        this.effectiveSize--;
        return tmp;
    }

    @Override
    public E removeLast() {
        if (this.isEmpty())
            return (E)this.elements;
        E tmp = this.elements[this.effectiveSize-1];
        this.elements[this.effectiveSize-1] = null;
        this.effectiveSize--;
        return tmp;
    }

    @Override
    public int size() {
        return this.effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return this.effectiveSize == 0;
    }

    @Override
    public void clear() {
        this.effectiveSize = 0;
        this.elements = null;
    }

    @Override
    public boolean add(int index, E element) {
        if (index >= 0) {
            if (isEmpty()) {
                this.elements[effectiveSize] = element;
                effectiveSize++;
                return true;
            } else if (isFull()) {
                addCapacity();
            }
            elements[index] = element;
            effectiveSize++;
            return true;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (index >= 0) {
            if (this.isEmpty()) {
                return null;
            }
            E tmp = this.elements[index];
            for (int i = index; i<this.size(); i++) {
                this.elements[i] = this.elements[i+1];
            }
            this.elements[this.effectiveSize-1] = null;
            this.effectiveSize--;
            return tmp;
        } else {
            return null;
        }
    }

    @Override
    public E get(int index) {
        if (this.isEmpty()) {
            return null;
        }
        if (index >= 0 && index < this.size()) {
            return this.elements[index];
        } else {
            return null;
        }
    }

    
    @Override
    public E set(int index, E element) {
        if (index < 0) {
            return null;
        } else if (element == null) {
            return null;
        } else if (this.isEmpty()) {
            this.elements[this.effectiveSize] = element;
            return element;
        }
        this.elements[index] = element;
        return element;
    }
    
    
    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator it = new Iterator<E>() {
            
            int cursor = 0;
            
            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                E element = elements[cursor];
                cursor++;
                return element;
            }
            
        };
        return it;
    }
    
    public static ArrayList<Integer> sumarNumerosGrandes(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i:list1) {
            tmp.addLast(i);
        }
        int c = list1.size()-1;
        int m;
        
        for (int i = list2.size()-1; i > -1; i--) {
            m = tmp.get(c)+list2.get(i);
            if (m > 9) {
                tmp.set(c-1, tmp.get(c-1)+1);
                tmp.set(c, m-10);
            } else {
                tmp.set(c, m);
            }
            c--;
        }
        int aux;
        for (int j = 0; j<tmp.size()-1; j++) {
            for (int i = 0; i<tmp.size()-1; i++) {
                aux = tmp.get(i);
                if (aux > 9 && i == 0) {
                    for (int k = tmp.size()-1; k > -1; k--) {
                        aux = tmp.get(k);
                        tmp.set(k+1, aux);
                    }
                    tmp.set(i, 1);
                } else if (aux > 9 && i != 0) {
                    tmp.set(i, 0);
                    tmp.set(i-1, tmp.get(i-1)+1);
                }
            }
        }
        /* Inserción
        for (int i=0; i<tmp.size();i++) {
            idx = i;
            aux = tmp.get(i);
        while((idx > 0) && (tmp.get(idx-1) > aux)) {
            tmp.set(idx, tmp.get(idx-1));
            idx--;
        }
        tmp.set(idx, aux);
        
        */
        
        return tmp;
    }
    
    public void removerElementos(int[] elements) {
        for (int e:elements) {
            this.remove(e);
        }
    }

    public boolean isPalindromo() {
        final int SIZE = this.size();
        int c = 0;
        while(true) {
            E tmp = this.get(c);
            E tmp1 = this.get(c+1);
            E tmp2 = this.get(SIZE-c-1);
            if (SIZE % 2 == 0 && c+1 == SIZE/2) {
                System.out.println(tmp + " == " + tmp1 + " > " + c);
                return tmp.equals(tmp1);
            } else if (SIZE % 2 != 0 && c == (SIZE - 1)/2) {
                return true;
            }
            if (tmp.equals(tmp2)) {
                System.out.println(tmp + " == " + tmp2 + " > " + c);
                c++;
                continue;
            } else {
                System.out.println(tmp + " == " + tmp2 + " > " + c);
                return false;
            }
        }
    }
    
    public ArrayList<E> revertir() {
        final int SIZE = this.size();
        ArrayList<E> tmp = new ArrayList<>();
        int c = 0;
        for(int i = SIZE-1; i > -1; i--) {
            tmp.set(c, this.get(i));
            c++;
        }
        return tmp;
    }

    @Override
    public boolean contains(E element) {
        int times = 0;
        for(E e:this) {
            if (e.equals(element)) {
                times++;
            }
        }
        return (times!=0);
    }

    @Override
    public int indexOf(E element) {
        if (element.equals(this.elements[0])) 
            return 0;
        if (element.equals(this.elements[effectiveSize-1]))
            return effectiveSize-1;
        int c = 0;
        for(E e:elements) {
            if (e.equals(element))
                return c;
            c++;
        }
        return -1;
    }

}

