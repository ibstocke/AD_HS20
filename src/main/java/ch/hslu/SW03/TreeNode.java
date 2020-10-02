/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW03;

/**
 *
 * @author Philipp
 * @param <K> Generic Key type
 * @param <V> Generic Value type
 */
public class TreeNode<K extends Comparable<K>, V> implements Comparable<TreeNode<K, V>> {

    private TreeNode<K, V> m_leftTreeNode;
    private TreeNode<K, V> m_rightTreeNode;
    final private K m_key;
    private V m_value;

    public TreeNode(K key, V value) {
        m_key = key;
        m_value = value;
        m_leftTreeNode = null;
        m_rightTreeNode = null;
    }

    public void setValue(V value) {
        m_value = value;
    }

    public V getValue() {
        return m_value;
    }

    public K getKey() {
        return m_key;
    }

    public TreeNode<K, V> getLeftTreeNode() {
        return m_leftTreeNode;
    }

    public void setLeftTreeNode(TreeNode<K, V> leftTreeNode) {
        m_leftTreeNode = leftTreeNode;
    }

    public TreeNode<K, V> getRightTreeNode() {
        return m_rightTreeNode;
    }

    public void setRightTreeNode(TreeNode<K, V> righTreeNode) {
        m_rightTreeNode = righTreeNode;
    }

    @Override
    public int compareTo(final TreeNode<K, V> otherTreeNode) {
        return m_key.compareTo(otherTreeNode.m_key);
    }

    @Override
    public String toString() {
        return "TreeNode: Key/Value: [" + m_key.toString() + "/" + m_value.toString() + "]";
    }
}
