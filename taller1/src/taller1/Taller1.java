/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller1;

/**
 *
 * @author danny
 */
public class Taller1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinaryTree<Integer> bt = new BinaryTree<>();
        bt.setLeft(new BinaryTree(1));
        bt.setLeft(new BinaryTree(2));
        bt.getLeft().setLeft(new BinaryTree(3));
        bt.getLeft().setLeft(new BinaryTree(4));
        bt.getRight().setLeft(new BinaryTree(5));
        bt.getRight().setLeft(new BinaryTree(6));
        bt.getRight().getRight().setLeft(new BinaryTree(7));
        System.out.println(bt.getLeft().toString());    
    }
    
}
