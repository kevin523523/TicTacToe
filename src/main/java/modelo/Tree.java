/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author kevin
 */
public class Tree<E> {

    public TreeNode<E> root;

    public Tree() {
        this.root = null;
    }

    public Tree(E root) {
        this.root = new TreeNode<>(root);
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public E getRoot() {
        return root.getContent();
    }

    public TreeNode getRootNode() {
        return this.root;
    }

    private void setRootNode(TreeNode<E> root) {
        this.root = root;
    }

    public void setRoot(E content) {
        this.root.setContent(content);
    }

    public boolean isLeaf() {
        return this.root.getChildren().isEmpty();
    }
    

}
