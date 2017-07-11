/**
 * 08722 Data Structures for Application Programmers.
 * Homework 3 Sorted Singly Linked List using Recursion.
 *
 *
 * Andrew ID: hramasub .
 * @author HaryKrishnan Ramasubramanian .
 */
public class SortedLinkedList implements MyListInterface {
/**
 * private head points to the top of the LinkedList.
 */
    private Node head;
/**
 * No argument constructor instantiates head to null.
 */
    SortedLinkedList() {
        head = null;
    }
/**
 * addHelper is the private helper method to the constructor to add each word of the unsorted array recursively.
 * @param unsorted is a String array of words taken as the input.
 * @param index is the current position used for recursively traversing the unsorted array.
 */
    private void addHelper(String[] unsorted, int index) {

        if (index == unsorted.length) {
            return;
        } else {
            add(unsorted[index]);
            addHelper(unsorted, ++index);
        }



    }
/**
 * SortedLinkedList parameter constructor that takes in the unsorted array as input.
 * @param unsorted is the unsorted array that is taken as the input.
 */
    SortedLinkedList(String[] unsorted) {
        int index = 0;
        head = null;
         addHelper(unsorted, index);
    }



/**
 * sortAdd is the private helper method used for adding recursively.
 * @param value is the String word that needs to be added.
 * @param temp is the head reference used to traverse and add.
 */
    private  void sortAdd(String value, Node temp) {
        if (isEmpty()) {
            head = new Node(value, head);
            return;
        }
        if (value.equals(temp.data)) {
            return;
        }
      if (temp == head && value.compareToIgnoreCase(temp.data) < 0) {
         head = new Node(value, head);
         return;

     }

        if (temp.next == null || value.compareToIgnoreCase(temp.next.data) < 0) {

            temp.next = new Node(value, temp.next);
            return;
        } else {
         sortAdd(value, temp.next);
    }
    }


    @Override
    public void add(String value) {
        // TODO Auto-generated method stub

        if (value == null) {
            return;
        }
       Node temp = head;
       sortAdd(value, temp);
   }
/**
 * size is the private static helper method used to find out the size using recursion.
 * @param temp is the reference to head using which the list is traversed and the size is calculated.
 * @return int returns the current size of the list.
 */
    private static int size(Node temp) {
        if (temp == null) {
            return 0;
        }
        if (temp.next == null) {
            return 1;

        } else {
        return 1 + size(temp.next);
    }
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        Node temp = head;
        return size(temp);
    }
/**
 * Display is the private static helper method used to display the entire LinkedList recursively.
 * @param temp is a reference to head from which the entire list has to be printed.
 */
    private static void display(Node temp) {

        if (temp == null) {
            System.out.println("[]");
            return;
        } else if (temp.next == null) {
            System.out.print(temp);
            return;
        } else {
            System.out.print(temp);
            display(temp.next);
        }

    }

    @Override
    public void display() {
        // TODO Auto-generated method stub
        Node temp = head;

        display(temp);
    }
/**
 * contains is the private helper method used to find the presence of a String recursively.
 * @param key is the given String whose presence has to be checked.
 * @param temp Node is used for traversal.
 * @return boolean returns true if present else false.
 */
    private  boolean contains(String key, Node temp) {
        if (temp.data.equalsIgnoreCase(key)) {
            return true;
        } else if (temp.next == null) {
            return false;
        } else if (key.compareToIgnoreCase(temp.data) < 0) {

            return false;
        } else {
            return contains(key, temp.next);

    }
    }
    @Override
    public boolean contains(String key) {
        // TODO Auto-generated method stub
        Node temp = head;
       return contains(key, temp);
    }




    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public String removeFirst() {
        // TODO Auto-generated method stub
      if (head == null) {
            throw new RuntimeException("RuntimeException");
        } else {
        String res = head.data;
        head = head.next;
        return res;
    }
    }
/**
 * findPrev is the private static helper method used to find the Node previous to a given index.
 * @param pos of type int is to keep track of the current position.
 * @param index of type int is the target position to be reached.
 * @param temp of type Node is used for traversal.
 * @return Node which is previous to the given index is returned.
 */
    private static Node findPrev(int pos, int index, Node temp) {
        if (pos == index - 1) {
            return temp;
        } else {
        return findPrev(++pos, index, temp.next);
    }
    }

    @Override
    public String removeAt(int index) {
        // TODO Auto-generated method stub
        if (index < 0 || index >= size() || head == null) {
            throw new RuntimeException("RuntimeException");
        } else if (index == 0) {
            //return removeFirst();
            String res = head.data;
            head = head.next;
            return res;
        } else {
            int pos = 0;
        Node temp = head;
        Node prev = findPrev(pos, index, temp);
        Node curr = prev.next;
        prev.next = curr.next;
        curr.next = null;
        return curr.data;
    }
    }
/**
 * private nested Node class.
 * @author HaryKrishnan Ramasubramanian.
 *
 */
    private  class Node {
/**
 * private String stores the data.
 */
        private String data;
/**
 * private next stores the reference to the next Node.
 */
        private Node next;
/**
 * Node is a parameter constructor.
 * @param s String is the input word.
 * @param n Node is the next reference.
 */
        Node(String s, Node n) {
            data = s;
            next = n;
        }

        @Override
        public String toString() {

            StringBuilder res = new StringBuilder();
            if (this == head) {
            res.append("[").append(this.data).append(", ");
            } else if (this.next == null) {
            res.append(this.data).append("]").append("\n");
            } else {
                res.append(this.data).append(", ");
            }
            return res.toString();
        }
     }
}
