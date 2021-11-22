/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.sopaletras;

/**
 *
 * @author danny
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
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public CircularNode<E> getNextNode() {
        return nextNode;
    }
    
    public CircularNode<E> getPrevNode() {
        return prevNode;
    }
    
    public void setPrevNode(CircularNode<E> p) {
        prevNode = p;
    }
    
    public void setNextNode(CircularNode<E> nextNode) {
        this.nextNode = nextNode;
    }
   
    
}
