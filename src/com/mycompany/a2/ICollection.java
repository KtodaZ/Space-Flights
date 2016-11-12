package com.mycompany.a2;


public interface ICollection<E> {
    /**
     * Returns number of elements in this collection
     * @return the number of elements in this collection.
     */
    int size();
    
    /**
     * Returns true if this collection contains no elements.
     * @return true if this collection contains no elements.
     */
    boolean isEmpty();
    
    /**
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     *
     * @return an <tt>Iterator</tt> over the elements in this collection
     */
    IIterator<E> getIterator();
    
    
    /**
     * Add an element to the collection.
     * @param e The element you want to add.
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this collection
     * @throws IllegalArgumentException if some property of the element
     *         prevents it from being added to this collection
     * @throws NullPointerException if the specified element is null and this
     *         collection does not permit null elements
     */
    boolean add(E e);
    
    /**
     * Removes all of the elements from this collection.
     * The collection will be empty after this method returns.
     */
    void clear();

}
