/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 6.
 * Building Index using BST.
 *
 * Andrew ID: hramasub.
 *
 * @author HaryKrishnan Ramasubramanian.
 */
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
/**
 *
 * BST class that extends Comparable and implements Iterable and BSTInterface.
 *
 * @param <T> accepts class of generic type T.
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * private root instance of type Node<T>.
     */
    private Node<T> root;
    /**
     * private comparator instance of generic type T.
     */
    private Comparator<T> comparator;
/**
 * no Argument BST constructor to instantiate root instance to null.
 */
    public BST() {
        this(null);
    }
/**
 * BST constructor that accepts Comparator<T> as parameter.
 * @param comp Comparator<T> parameter of generic type.
 */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }
/**
 * comparator method that returns the comparator instance.
 * @return Comparator<T> instance of generic type.
 */
    public Comparator<T> comparator() {
        return comparator;
    }
/**
 * getRoot method used to return the root reference of the BST.
 * @return T generic type value of the root Node.
 */
    public T getRoot() {
        if (root == null) {
            return null;
        }
    return root.data;
}
/**
 * public getHeight method used to compute the height of the BST.
 * @return int the height of the given BST.
 */
    public int getHeight() {
        return getHeight(root);
    }
    /**
     * private getHeight helper method to compute the height of the tree
     * recursively.
     * @param temp accepts the root reference of a BST.
     * @return int the height of the BST.
     */
    private int getHeight(Node<T> temp) {
        /* Base case 1: If temp reference has reached null then
         * we have reached the end of our traversal in
         * either the left or right subtree direction, or the root
         * itself is null. In both cases return height as 0.
         * */
        if (temp == null) {
            return 0;
        }
        /* Base case 2: If temp reference has both its left and right
         * child as null then we have reached a leaf node.
         * In which case return the height as 0.
         * */
        if (temp.left == null && temp.right == null) {
            return 0;
        } else {
            /* Recursive case: Recursively call the getHeight method
             * by passing the left and right child reference of the current
             * temp reference and return 1 (for the edge from temp to its child) +
             * the greater of the two subtree heights to compute the total height of the tree,
             * since height is measured along the longest path from the root to the leaf node.
             * */
            int left =  getHeight(temp.left);
            int right = getHeight(temp.right);
            if (left > right) {
            return 1 + left;
            } else {
                return 1 + right;
            }
        }
    }
    /**
     * public getNumberOfNodes method used to compute the no: of Nodes
     * in the BST.
     * @return int the no: of Nodes in the BST.
     */
    public int getNumberOfNodes() {
        return getNumberOfNodes(root);
    }
    /**
     * private getNumberOfNodes helper method used to
     * recursively compute the no: of Nodes in the BST.
     * @param temp gets the reference of the BST root.
     * @return int no: of Nodes in the BST.
     */
    private int getNumberOfNodes(Node<T> temp) {
        /*
         * Base case: If temp reference is null, then we have reached the end of
         * our traversal of the tree either through the left or right subtree direction,
         * in which case return numberOfNodes as 0.
         */
        if (temp == null) {
            return 0;
        } else {
            /*
             * Recursive Case: Return 1 (to take into account of the temp node) +
             * recursively calling the getNumberOfNodes by passing the left child
             * reference and the right child reference. In this way a series of '1's
             * will be added for each node in the left and right subtree until we
             * hit the base case, i.e. a null marking the end of traversal.
             */
            return 1 + getNumberOfNodes(temp.left) + getNumberOfNodes(temp.right);
        }

    }

    @Override
    public T search(T toSearch) {
       return search(root, toSearch);
    }
    /**
     * private search helper method used to recursively search for a given Object toSearch.
     * @param curr reference to the root of the BST.
     * @param toSearch object that has to be searched for in the BST.
     * @return object of type T is returned if found.
     */
    private T search(Node<T> curr, T toSearch) {
        /*
         * Base case 1: If the curr reference points to null which marks the
         * end of the tree traversal (or the root itself is null) or if the given
         * toSearch object is null then we return null as the search result.
         */
        if (curr == null || toSearch == null) {
            return null;
        }
            /*
             * If a comparator's compare instance exists, use it.
             */
            if (this.comparator != null) {
                /*
                 * Recursive case 1: if the compare instance results in the
                 * toSearch object to be greater than the curr.data then we have
                 * to recursively call the search method by passing the right child
                 * reference of the curr along with the toSearch Object.
                 */
                if (this.comparator().compare(curr.data, toSearch) < 0) {
                    return search(curr.right, toSearch);
                } else if (this.comparator().compare(curr.data, toSearch) > 0) {
                /* Recursive case 2: if the compare instance results in the
                 * toSearch object to be less than the curr.data then we have
                 * to recursively call the search method by passing the left child
                 * reference of the curr along with the toSearch Object.
                 */
                    return search(curr.left, toSearch);
                } else {
                    /*
                     * Base case 2: If the toSearch Object is found then
                     * return the curr.data as the search result.
                     */
                    return curr.data;
                }
                  //If no compare instance exists, use the default compareTo
                  //implementation is provided in the T class.
                } else {

                    /*
                     * Recursive case 1: If the compareTo results in the the toSearch
                     * object to be greater than the curr.data object then we have to
                     * recursively call the search method by passing the right child
                     * reference of the curr, along with the toSeach Object.
                     */
                    if (curr.data.compareTo(toSearch) < 0) {
                        return search(curr.right, toSearch);
                    } else if (curr.data.compareTo(toSearch) > 0) {
                        /*
                         * Recursive case 2: If the compareTo results in the toSearch
                         * object to be lesser than the curr.data object then we have
                         * to recursively call the search method by passing the left
                         * child reference of the curr, along with the toSearch object.
                         */
                        return search(curr.left, toSearch);

                    } else {
                        /*
                         * Base case 2: If the toSearch object is found then return the
                         * curr.data object as the search result.
                         */
                        return curr.data;
                    }
                }
    }

    @Override
    public void insert(T toInsert) {
        /* Sanity Check:
         * If the given toInsert object is null then do not
         * insert anything.
         */
        if (toInsert == null) {
            return;
        }
        /* Sanity Check:
         * If the root is null and the toInsert is not null, then insert
         * the given object to the root reference return the function call.
         */
        if (root == null) {
            root = new Node<T>(toInsert);
            return;
        }
        /*
         * If neither of the above conditions are satisfied then call the private
         * helper recursive method for insert by passing the root reference and
         * the toInsert object.
         */
        insert(root, toInsert);
    }
    /**
     * private insert helper method used to recursively implement BST insert.
     * @param curr accepts the root reference.
     * @param toInsert of generic type T to be inserted.
     *
     */
    private void insert(Node<T> curr, T toInsert) {
        /*
         * If the comparator's compare instance exists, use it.
         */
            if (this.comparator != null) {
                /*
                 * If the compare instance results in the toInsert object to be
                 * greater than than the curr.data, then we need to examine the
                 * right child.
                 */
                if (this.comparator().compare(curr.data, toInsert) < 0) {
                    /*
                     * Recursive Case 1: If the right child of the curr is not null
                     * then we haven't reached the leaf node yet, so not ready to insert.
                     * Hence we recursively call the insert method by passing the right
                     * child reference of the curr along with the toInsert object.
                     */
                    if (curr.right != null) {
                        insert(curr.right, toInsert);
                    } else {
                        /*
                         * Base case 1: If the curr.right happens to be null then we
                         * have reached the leaf node, hence ready to insert.
                         * Insert a new Node with the given to Insert object as a
                         * parameter to the right child reference of the curr and
                         * return the function call.
                         */
                        curr.right = new Node<T>(toInsert);
                        return;
                    }
                //If the compare instance results in the toInsert object to be lesser
                //than the curr.data, then we need to examine the left child reference.
                } else if (this.comparator().compare(curr.data, toInsert) > 0) {
                    /*
                     * Recursive case 2: If the left child reference of the curr is not
                     * null, then we haven't reached the leaf node yet, so not ready to
                     * insert. Hence we recursively call the insert method by passing the
                     * left child reference of the curr along with the toInsert object.
                     */
                    if (curr.left != null) {
                        insert(curr.left, toInsert);
                    } else {
                        /*
                         * Base case 2: If the left child reference of the curr happens to
                         * be null, then we have reached the leaf node, so ready to insert.
                         * Hence we Insert a new Node with the toInsert object as the parameter
                         * to the left child reference of the curr and return the function call.
                         */
                        curr.left = new Node<T>(toInsert);
                        return;
                    }
                } else {
                    /*
                     * Base case 3: If the toInsert object already exists in the BST
                     * then do not insert anything, simply return the function call,
                     * as the BST can not have any duplicates.
                     */
                    return;
                }
                //If no compare instance exists, then use the default compareTo
                //implementation provided in the T class.
                } else {
                    /*
                     * If the compareTo results in the toInsert object to be greater
                     * than the curr.data, then we need to examine the right child.
                     */
                     if (curr.data.compareTo(toInsert) < 0) {
                         /*
                          * Recursive Case 1: If the right child of the curr is not null
                          * then we haven't reached the leaf node yet, so not ready to insert.
                          * Hence we recursively call the insert method by passing the right
                          * child reference of the curr along with the toInsert object.
                          */
                        if (curr.right != null) {
                                 insert(curr.right, toInsert);
                        } else {
                            /*
                             * Base case 1: If the curr.right happens to be null then we
                             * have reached the leaf node, hence ready to insert.
                             * Insert a new Node with the given to Insert object as a
                             * parameter to the right child reference of the curr and
                             * return the function call.
                             */
                            curr.right = new Node<T>(toInsert);
                            return;
                        }
                    //If the compareTo results in the toInsert object to be lesser
                    //than the curr.data, then we need to examine the left child.
                    } else if (curr.data.compareTo(toInsert) > 0) {
                        /*
                         * Recursive case 2: If the left child reference of the curr is not
                         * null, then we haven't reached the leaf node yet, so not ready to
                         * insert. Hence we recursively call the insert method by passing the
                         * left child reference of the curr along with the toInsert object.
                         */
                        if (curr.left != null) {
                             insert(curr.left, toInsert);
                         } else {
                             /*
                              * Base case 2: If the left child reference of the curr happens to
                              * be null, then we have reached the leaf node, so ready to insert.
                              * Hence we Insert a new Node with the toInsert object as the parameter
                              * to the left child reference of the curr and return the function call.
                              */
                             curr.left = new Node<T>(toInsert);
                             return;
                         }

                    } else {
                        /*
                         * Base case 3: If the toInsert object already exists in the BST
                         * then do not insert anything, simply return the function call,
                         * as the BST can not have any duplicates.
                         */
                        return;
                    }

                }
        }

    @Override
    public Iterator<T> iterator() {
        return new BSTIterator();
    }
    /**
     *
     * private BSTIterator class that implements Iterator interface.
     *
     */
private class BSTIterator implements Iterator<T> {
    /**
     * stack instance used to implement in order Iterator.
     */
    private Stack<Node<T>> stack;
    /**
     * BSTIterator constructor to initialize the stack instance.
     */
    BSTIterator() {
        stack = new Stack<Node<T>>();
        leftMost(root);
    }
    /**
     * leftMost method helper method to traverse to the leftmost child.
     * @param temp parameter gets the root Node whose leftmost child has to be
     * found.
     */
    private void leftMost(Node<T> temp) {
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
    }
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        //when there is no next element
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Node<T> node = stack.pop();
        T result = node.data;
        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return result;
    }

}
/**
 *
 * private static class Node<T> that defines each Node of the BST.
 *
 * @param <T> Generic Type T assumes any given Type.
 */
    private static class Node<T> {
        /**
         * data instance of generic Type T.
         */
        private T data;
        /**
         * left reference referencing a Node<T>.
         */
        private Node<T> left;
        /**
         * right reference referencing a Node<T>.
         */
        private Node<T> right;
/**
 *
 * @param data1 of type T is the value contained in the parentNode.
 */
        Node(T data1) {
            this(data1, null, null);
        }
/**
 *
 * @param d data of Generic type T.
 * @param l left reference referencing a Node<T>.
 * @param r right reference referencing a Node<T>.
 */
        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

}
