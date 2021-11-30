/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

/**
 *
 * @author danny
 * @param <E>
 */
public class CircularNode<E> {
    
    private E content;
    private CircularNode<E> nextNode;
    private CircularNode<E> prevNode;
    
    public CircularNode (E content) {
        this.content = content;
        this.nextNode = null;
        this.prevNode = null;
    }

    public E getContent() {
        return this.content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public CircularNode<E> getNextNode() {
        return this.nextNode;
    }
    
    public CircularNode<E> getPrevNode() {
        return this.prevNode;
    }
    
    public E setPrevNode(CircularNode<E> prevNode) {
        this.prevNode = prevNode;
        return prevNode.getContent();
    }
    
    public E setNextNode(CircularNode<E> nextNode) {
        this.nextNode = nextNode;
        return nextNode.getContent();
    }
    
}
