package ca.bcit.comp2526.a2c;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DoubleLinkedList.
 *
 * A generic double linked list class.
 *
 * @param <E> Generic type for iterable.
 * @author Felix Lin
 * @version 1.0
 */
public class DoubleLinkedList<E> implements Serializable, Iterable<E> {

    private Node<E> head;

    private Node<E> tail;

    private int size;

    /**
     * Node.
     *
     * A inner class, that contains the nodes.
     *
     * @param <E> generic type that node can take.
     */
    public static class Node <E> implements Serializable {

        private E data;

        private Node<E> next;

        private Node<E> previous;

        /**
         * Constructs a node.
         *
         * @param data the data that the node will take.
         */
        public Node(E data) {
            this.data = data;
            next = null;
            previous = null;
        }
    }

    /**
     * Returns a Linked List Iterator object.
     *
     * @return a linked list iterator object.
     */
    public Iterator<E> iterator() {
        /**
         * LinkedListIterator.
         *
         * Iterator class that iterates through a linked list.
         */
        class LinkedListIterator implements Iterator<E> {
            private Node<E> nextNode;

            /**
             * Constructs a linked list iterator.
             */
            LinkedListIterator() {
                nextNode = head;
            }

            /**
             * Checks if there is a next node to
             * iterate to.
             *
             * @return true if there is a node to iterate to.
             */
            public boolean hasNext() {
                if (size == 0) {
                    return false;
                }
                return nextNode != null;
            }

            /**
             * Iterates to the next node.
             *
             * @return returns the data that is next in line for the
             * node to move to.
             */
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = nextNode.data;
                nextNode = nextNode.next;
                return data;
            }

            /**
             * Remove node.
             */
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
        
        return new LinkedListIterator();
    }

    /**
     * Prints the data of the node.
     */
    public void print() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
    }

    /**
     * Gets the size of the linked list.
     *
     * @return the size of the linked list.
     */
    public int size() {
        return size;
    }

    /**
     * Adds new data to the front of the list.
     *
     * @param e generic data to be added.
     *
     * @throws CouldNotAddException throws an exception if
     *                              the data cannot be added.
     */
    public void addToFront(E e) throws CouldNotAddException {
        Node<E> newNode = new Node<E>(e);
        if (e == null) {
            throw new CouldNotAddException("Can not add");
        }
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
            size++;
        }
    }

    /**
     * Removes the first node of the linked list.
     *
     * @return the data that is removed.
     * @throws CouldNotRemoveException Throws an exception when
     *                                  the node cannot be removed.
     */
    public E removeFromFront() throws CouldNotRemoveException {
        if (head == null) {
            throw new CouldNotRemoveException("Could not remove");
        }
        E data = null;
        data = head.data;
        head = head.next;
        size--;
        return data;
    }

    /**
     * Gets the data of the first
     * node in the linked list.
     *
     * @return the data of the first node
     *          in the double linked list.
     */
    public E getFirst() {
        if (size == 0) {
            return null;
        }
        return head.data;
    }

    /**
     * Gets the data of the last
     * node in the linked list.
     *
     * @return the data of the last node
     *          in the double linked list.
     */
    public E getLast() {
        if (size == 0) {
            return null;
        }
        return tail.data;
    }

    /**
     * Adds data to the end of the double linked list.
     *
     * @param e the data to be added.
     * @throws CouldNotAddException the data cannot be added.
     */
    public void addToEnd(E e) throws CouldNotAddException {
        Node<E> newNode = new Node<E>(e);
        if (e == null) {
            throw new CouldNotAddException("Can not add");
        }
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    /**
     * Removes data from the end of the double linked list.
     *
     * @return returns the data that is removed.
     * @throws CouldNotRemoveException the data cannnot be removed.
     */
    public E removeFromEnd() throws CouldNotRemoveException {
        if (tail == null) {
            throw new CouldNotRemoveException("Could not remove");
        }
        E data = null;
        data = tail.data;
        tail = tail.previous;
        size--;
        return data;
    }


}
