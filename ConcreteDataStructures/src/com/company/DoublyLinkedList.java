package com.company;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    //Empty this linked list, O(n)
    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    //Return the size of the linked list
    public int size() {
        return size;
    }

    //Is this linked list empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    //Add an element to the tail of the linked list O(1)
    public void add(T elem) {
        addLast(elem);
    }

    //add an element to the beginning of this linked list , O(1)
    public void addFirst(T elem) {

        //The linked list is empty
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.prev = new Node<T>(elem, null, null);
            head = head.prev;
        }
        size++;
    }

    //Add a node to the tail of the linked list , 0(1)
    public void addLast(T elem) {

        //The linked list is empty
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    //Check the value of the first node if it exists, 0(1);
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    //Check the value of the last node if it exists, 0(1)
    public T removeFirst() {

        if (isEmpty()) throw new RuntimeException("Empty List");//Can't remove data from an empty List -_-

        //Extract the data at the head and move the head pointer forwards one node
        T data = head.data;
        head = head.next;
        --size;
        if (isEmpty()) tail = null;//if the list is empty set the tail to null as well
        else head.prev = null;//Do a memory clean of the previous node
        return data;//return the data that was at the first node we just removed
    }

    //Remove the last value at the tail of the linked list O(1)
    public T removeLast() {

        if (isEmpty()) throw new RuntimeException("Empty list");//cant remove data from an empty list -_-
        //Extract the data at the tail and move the tail pointer backwards one node
        T data = tail.data;
        tail = tail.prev;
        --size;

        if (isEmpty()) head = null;//if the list is now empty set the head to null as well
        else tail.next = null;//Do a memory clean of the node that was just removed
        return data;
    }

    //Removed an arbitrary node from the linked list, O(1)
    private T remove(Node<T> node) {

        //if the node to remove is somewhere either at the head or the tail handle those immediately
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();
        //Make the pointers of adjacent nodes to skip over 'node'
        node.next.prev = node.prev;
        node.prev.prev = node.next;

        T data = node.data;//Temporary store the data we want to return

        node.data = null;//memory cleanup
        node = node.prev = node.next = null;
        --size;

        return data;//Return the data at the node we just removed
    }

    //Remove a node at a particular index, O(n)
    public T removeAt(int index) {
        //make sure the index provided is valid -_-
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        int i;
        Node<T> trav;
        //Search from the front of the list
        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++)
                trav = trav.next;
        } else
            for (i = size - 1, trav = tail; i != index; i--)
                trav = trav.prev;
        return remove(trav);
    }

    //remove a particular value in the linked list, O(n)
    public boolean remove(Object obj) {
        Node<T> trav = head;
        //support searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
            //Search for non null object
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    //Find the index of a particular value in the linked list O(n)
    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;
        //Support searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next, index++)
                if (trav.data == null)
                    return index;
        }//Search for non null object
        else
            for (trav = head; trav != null; trav = trav.next, index++)
                if (obj.equals(trav.data))
                    return index;
        return -1;
    }

    //Check if a value is contained within the linked list
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() { T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data + ",");
            trav = trav.next;
        }
        sb.append("]");
        return sb.toString();
    }

    //internal node class to represent data
    private static class Node<T> {
        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
