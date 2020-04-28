package com.company;

public class Stack<T> implements Iterable {
    private java.util.LinkedList<T> list = new java.util.LinkedList<T>();

    //create an empty stack
    public Stack() {
    }

    //create a Stack with an initial element
    public Stack(T firstElem) {
        push(firstElem);
    }

    //Return the number of Elements in the stack
    public int size() {
        return list.size();
    }

    //Check if the stack isEmpty
    public boolean isEmpty() {
        return size() == 0;
    }

    //Push an element on the stack
    public void push(T elem){
        list.add(elem);
    }

    public T pop(){
        if (isEmpty())
            throw new java.util.EmptyStackException();
        return list.removeLast();
    }

    //Pop an element off the stack - Throws an error if the stack is empty
    public T peek(){
        if (isEmpty())
            throw new java.util.EmptyStackException();
        return list.peekLast();
    }

    //Allow users to iterate through the stack using an iterator
    @Override public java.util.Iterator<T> iterator(){
        return list.iterator();
    }

}
