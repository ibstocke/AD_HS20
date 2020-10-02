/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW03;

/**
 *
 * @author Philipp
 */
public class MyTree<K extends Comparable<K>, V> implements IMyTree<K, V> {

    private TreeNode<K, V> m_rootNode;

    public MyTree() {
        m_rootNode = null;
    }

    @Override
    public boolean add(K key, V value) {
        TreeNode<K, V> newNode = new TreeNode<>(key, value);
        boolean fAdded = false;

        if (m_rootNode == null) {
            m_rootNode = newNode;
            fAdded = true;
        } else {
            TreeNode<K, V> currentNode = m_rootNode;
            int iCompareResult = 0;

            while (currentNode != null) {
                iCompareResult = newNode.compareTo(currentNode);
                if (iCompareResult < 0) {
                    if (currentNode.getLeftTreeNode() != null) {
                        currentNode = currentNode.getLeftTreeNode();
                    } else {
                        currentNode.setLeftTreeNode(newNode);
                        fAdded = true;
                        break;
                    }
                } else if (iCompareResult > 0) {
                    if (currentNode.getRightTreeNode() != null) {
                        currentNode = currentNode.getRightTreeNode();
                    } else {
                        currentNode.setRightTreeNode(newNode);
                        fAdded = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        return fAdded;
    }

    @Override
    public boolean search(K key) {
        TreeNode<K, V> treeNode = getTreeNode(key);
        return treeNode != null;
    }

    @Override
    public V getValue(K key) {
        TreeNode<K, V> treeNode = getTreeNode(key);
        if (treeNode == null) {
            return null;
        }

        return treeNode.getValue();
    }

    @Override
    public boolean setValue(K key, V value) {
        TreeNode<K, V> treeNode = getTreeNode(key);
        if (treeNode == null) {
            return false;
        }
        treeNode.setValue(value);
        return true;
    }

    @Override
    public V remove(K key) {
        if (m_rootNode == null) {
            return null;
        }

        TreeNode<K, V> currentNode = m_rootNode;
        TreeNode<K, V> parrentNode = m_rootNode;

        int iCompareResult = 0;
        while (currentNode != null) {
            iCompareResult = key.compareTo(currentNode.getKey());

            if (iCompareResult == 0) {
                break;
            }
            parrentNode = currentNode;

            if (iCompareResult < 0) {
                currentNode = currentNode.getLeftTreeNode();
            } else if (iCompareResult > 0) {
                currentNode = currentNode.getRightTreeNode();
            }
        }

        if (currentNode == null) {
            return null;
        }

        V valueOfRemNode = currentNode.getValue();
        boolean fIsLeftOfParrent = parrentNode.getLeftTreeNode() == currentNode;

        if (currentNode.getLeftTreeNode() == null && currentNode.getRightTreeNode() == null) {
            //Node has no children, simple delet it
            if (m_rootNode == currentNode) {
                m_rootNode = null;
            } else if (fIsLeftOfParrent) {
                parrentNode.setLeftTreeNode(null);
            } else {
                parrentNode.setRightTreeNode(null);
            }
        } else if (currentNode.getLeftTreeNode() != null && currentNode.getRightTreeNode() != null) {
            //Node has two children
            TreeNode<K, V> smallestNode = currentNode.getRightTreeNode();
            TreeNode<K, V> parrentSmallestNode = currentNode;
            while (smallestNode.getLeftTreeNode() != null) {
                parrentSmallestNode = smallestNode;
                smallestNode = smallestNode.getLeftTreeNode();
            }
            if (smallestNode.getRightTreeNode() != null) {
                parrentSmallestNode.setLeftTreeNode(smallestNode.getRightTreeNode());
            } else {
                parrentSmallestNode.setLeftTreeNode(null);
            }
            smallestNode.setLeftTreeNode(currentNode.getLeftTreeNode());
            smallestNode.setRightTreeNode(currentNode.getRightTreeNode());

            if (parrentNode == m_rootNode) {
                m_rootNode = smallestNode;
            } else {
                if (fIsLeftOfParrent) {
                    parrentNode.setLeftTreeNode(smallestNode);
                } else {
                    parrentNode.setRightTreeNode(parrentNode);
                }
            }

        } else if (currentNode.getLeftTreeNode() != null) {
            //Node has one children
            if (fIsLeftOfParrent) {
                parrentNode.setLeftTreeNode(currentNode.getLeftTreeNode());
            } else {
                parrentNode.setRightTreeNode(currentNode.getLeftTreeNode());
            }
        } else {
            if (fIsLeftOfParrent) {
                parrentNode.setLeftTreeNode(currentNode.getRightTreeNode());
            } else {
                parrentNode.setRightTreeNode(currentNode.getRightTreeNode());
            }
        }

        return valueOfRemNode;
    }

    @Override
    public int getNoOfNodes() {
        if (m_rootNode == null) {
            return 0;
        }
        int[] cnt = new int[1];
        cntInorder(m_rootNode, cnt);
        return cnt[0];
    }

    @Override
    public boolean isEmpty() {
        return m_rootNode == null;
    }

    @Override
    public String toString() {
        String[] dataString = new String[1];
        dataString[0] = "";
        if (m_rootNode != null) {
            toStringInorder(m_rootNode, dataString);
        }
        return dataString[0];
    }

    private TreeNode<K, V> getTreeNode(K key) {
        TreeNode<K, V> currentNode = m_rootNode;

        int iCompareResult = 0;
        while (currentNode != null) {
            iCompareResult = key.compareTo(currentNode.getKey());

            if (iCompareResult < 0) {
                if (currentNode.getLeftTreeNode() != null) {
                    currentNode = currentNode.getLeftTreeNode();
                } else {
                    return null;
                }

            } else if (iCompareResult > 0) {
                if (currentNode.getRightTreeNode() != null) {
                    currentNode = currentNode.getRightTreeNode();
                } else {
                    return null;
                }

            } else {
                return currentNode;
            }
        }

        return null;
    }

    private void cntInorder(TreeNode<K, V> currentNode, int[] cnt) {
        if (currentNode == null) {
            return;
        }
        cntInorder(currentNode.getLeftTreeNode(), cnt);
        cnt[0]++;
        cntInorder(currentNode.getRightTreeNode(), cnt);
    }

    private void toStringInorder(TreeNode<K, V> currentNode, String[] dataString) {
        if (currentNode == null) {
            return;
        }
        toStringInorder(currentNode.getLeftTreeNode(), dataString);
        dataString[0] += currentNode.toString() + "\n";
        toStringInorder(currentNode.getRightTreeNode(), dataString);
    }

    public static void main(String[] args) {
        IMyTree<Integer, String> myTree = new MyTree<>();
        myTree.add(3, "root");
        myTree.add(1, "String1");
        myTree.add(2, "String2");
        myTree.add(7, "String5");
        myTree.add(4, "String4");
        myTree.add(5, "String6");
        myTree.add(8, "String6");
        System.out.println(myTree.toString());

        myTree.remove(5);
        System.out.println(myTree.toString());
    }

}
