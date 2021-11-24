package espol.collection;

import java.util.Comparator;

public interface List<E> extends Iterable<E> {

    public boolean addFirst(E e); // inserta el elemento e al inicio

    public boolean addLast(E e); // inserta el elemento e al final

    public E removeFirst(); // remueve el elemento al inicio de la lista

    public E removeLast(); // remueve el elemento al final de la lista

    public int size();

    public boolean isEmpty();

    public void clear();
    
    public boolean add(int index, E element); // inserta element en la posición index

    public E remove(int index); // remueve y retorna el elemento en la posición index

    public E get(int index); // retorna el elemento ubicado en la posición index

    public E set(int index, E element); // setea el element en la posición index
    
    public E insertAt(E[] elements, int position);
    
    List<E> findAll(Comparator<E> cmp, E e);
}
