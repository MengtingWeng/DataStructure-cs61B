/* HashTableChained.java */

package HW6.dict;

import HW5.list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  private int capacity;
  private List[] buckets;
  private int size;
  public int collision;
  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    this.capacity = sizeEstimate;
    this.buckets = new DList[capacity];
    this.size = 0;
    this.collision = 0;
    for (int i = 0; i < this.capacity; i++) {
      buckets[i] = new DList();
    }
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    this(97);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    return Math.abs(code) % capacity;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/
  public int capacity() {
    return this.capacity;
  }

  public int size() {
    // Replace the following line with your solution.

    return this.size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return this.size == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    int hashcode = compFunction(key.hashCode());
    List curList = buckets[hashcode];
    Entry e = new Entry();
    e.key = key;
    e.value = value;
    if (curList.length() > 0) {
      this.collision++;
    }
    curList.insertBack(e);
    this.size++;
    return e;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int hashcode = compFunction(key.hashCode());
    List curList = buckets[hashcode];
    ListNode cur = curList.front();
    while (cur.isValidNode()) {
      try {
        if (((Entry)cur.item()).key.equals(key)) {
          return (Entry)cur.item();
        }
        cur = cur.next();
      } catch (InvalidNodeException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int hashcode = compFunction(key.hashCode());
    List curList = buckets[hashcode];
    ListNode cur = curList.front();
    while (cur.isValidNode()) {
      try {
        if (key.equals(((Entry)cur.item()).key)) {
          Entry e = (Entry) cur.item();
          cur.remove();
          this.size--;
          return e;
        }
      } catch (InvalidNodeException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    for(int i = 0; i < this.capacity; i++){
      buckets[i] = new DList();
    }
    this.size = 0;
    this.collision = 0;
  }

  public String toString(){
    String p = new String();
    for(int i = 0; i < capacity; i++){
      p = p + "[" + buckets[i].length() + "]";
      if(((i + 1) % 20) == 0) {
        p = p + "\n";
      }
    }
    return p;
  }

  public double collision(){
    return (double)(size - capacity + capacity * Math.pow((double)(1 - 1.0 / (double)capacity), (double)size));
  }


}
