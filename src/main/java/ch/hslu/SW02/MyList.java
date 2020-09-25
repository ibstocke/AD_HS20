/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW02;

/**
 *
 * @author Philipp
 * @param <T> Generic type
 */
public class MyList<T> implements Iterable<T>, IMyStack<T> {

    private Node<T> m_head;

    public MyList() {
        m_head = null;
    }

    @Override
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNextNode(m_head);
        m_head = newNode;
    }

    public boolean isEmpty() {
        return m_head == null;
    }

    public int getSize() {
        Node<T> currentNode = m_head;
        int iNoOfElements = 0;
        while (currentNode != null) {
            iNoOfElements++;
            currentNode = currentNode.getNextNode();
        }
        return iNoOfElements;
    }

    public Node<T> getHead() {
        return m_head;
    }

    public void setHead(Node<T> newNode) {
        m_head = newNode;
    }

    @Override
    public T pop() {
        if (m_head == null) {
            return null;
        }
        Node<T> currentNode = m_head;
        T tmpData = currentNode.getData();
        setHead(currentNode.getNextNode());
        currentNode.deleteNode();
        return tmpData;
    }

    @Override
    public String toString() {
        int iSize = getSize();
        if (iSize == 0 || m_head == null) {
            return "List is empty";
        }
        Node<T> currentNode = m_head;
        Object currentData = currentNode.getData();

        int cnt = 1;
        String strList = "(" + Integer.toString(cnt++) + ") data: [" + currentData.toString() + "]\n";

        for (int i = 0; i < iSize - 1; i++) {
            currentNode = currentNode.getNextNode();
            if (currentNode == null) {
                break;
            }
            currentData = currentNode.getData();
            strList = strList + "(" + Integer.toString(cnt++) + ") data: [" + currentData.toString() + "]\n";
        }
        return strList;
    }

    @Override
    public MyListIterator<T> iterator() {
        return new MyListIterator<>(this);
    }

    public static void main(final String[] args) {
        MyList<Allocation> myList = new MyList<>();
        Allocation allocation1 = new Allocation(1, 10);
        Allocation allocation2 = new Allocation(2, 20);
        Allocation allocation3 = new Allocation(3, 30);
        Allocation allocation4 = new Allocation(4, 40);

        myList.push(allocation1);
        myList.push(allocation2);
        myList.push(allocation3);
        myList.push(allocation4);
        myList.toString();

        MyListIterator<Allocation> itr = myList.iterator();
        itr.remove();

        String infoStr;
        infoStr = myList.toString();
        System.out.println(infoStr);

        myList.pop();
        myList.toString();

        IMyStack<String> myStack = new MyList<>();
        myStack.push("String1");
        myStack.push("String2");
        myStack.push("String3");
        myStack.toString();
        infoStr = myStack.toString();
        System.out.println(infoStr);
        myStack.pop();
        infoStr = myStack.toString();
        System.out.println(infoStr);
    }
}
