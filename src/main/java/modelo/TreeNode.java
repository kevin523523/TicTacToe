/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class TreeNode<E> {

    private E content;
    public List<Tree<E>> children;

    public TreeNode(E content) {
        this.content = content;
        this.children = new LinkedList<>();
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public List<Tree<E>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<E>> children) {
        this.children = children;
    }

    public void addChild(Tree<E> child) {
        children.add(child);
    }

    @Override
    public String toString() {
        if (content instanceof String[][]) {
            return arrayToString((String[][]) content);
        } else {
            return content.toString();
        }
    }

    private String arrayToString(String[][] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] row : array) {
            for (String value : row) {
                stringBuilder.append(value).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
