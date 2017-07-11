/**
 * 08722 Data Structures for Application Programmers.
 * Homework Assignment 2
 * Solve Josephus problem with different data structures
 * and different algorithms and compare running times
 *
 * Andrew ID: hramasub .
 * @author HaryKrishnan Ramasubramanian .
 * I will use the first method "ArrayDeque as a Deque" implementation since it takes the lowest time among the three
 * implementations for the same input size, as the input/no: of people grows substantially large in
 * comparison to the rotations (size is very large & size > rotations). This is because ArrayDeque does not
 * have to perform node allocations (as in the case of LinkedList as a Deque) nor does it have to perform
 * shift of elements (as in the case of LinkedList as a List, removing by index) and thus has a faster runtime.
 *
 * However, when rotations are very large & rotation >= size, I will choose the third implementation,
 * "LinkedList as a List" since it's algorithm is less likely to be dominated by the value of rotations as
 * compared to the first two methods which loop over the rotations as well.
 */
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Josephus game implemented using ArrayDeque, LinkedList as a deque and LinkedList as a List.
 *
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     * Run Time Complexity : O(n) .
     */
    public int playWithAD(int size, int rotation) { //change return type to int
        // TODO your implementation here
        if (rotation <  1) {
            throw new RuntimeException("RuntimeException");
        }
        if (size < 1) {
            throw new RuntimeException("RuntimeException");
        }
       Deque<Integer> adq = new ArrayDeque<Integer>(size);

        for (int i = 1; i <= size; i++) {
            adq.offerLast(i);
        }
        while (size > 1) {
        for (int i = 1; i < rotation; i++) {

            adq.offerLast(adq.pollFirst());

        }

        adq.pollFirst();
        size = size - 1;
        }
        return adq.peekFirst();

}

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) { //change return type to int
        // TODO your implementation here
        if (rotation <  1) {
            throw new RuntimeException("RuntimeException");

        }
        if (size < 1) {
            throw new RuntimeException("RuntimeException");
        }
        Deque<Integer> ll = new LinkedList<Integer>();

        for (int i = 1; i <= size; i++) {
            ll.offerLast(i);
        }
        while (size > 1) {
            for (int i = 1; i < rotation; i++) {

                ll.offerLast(ll.pollFirst());

            }

            ll.pollFirst();
            size = size - 1;
            }
            return ll.peekFirst();

    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) { //change return type to int
        // TODO your implementation here
        if (rotation <  1) {
            throw new RuntimeException("RuntimeException");

        }
        if (size < 1) {
            throw new RuntimeException("RuntimeException");
        }
        List<Integer> ll = new LinkedList<Integer>();

        for (int i = 1; i <= size; i++) {
            ll.add(i);
        }

        //while (ll.size() != 1) {
        int f = 0;
        while (ll.size() != 1) {
            f = (f + rotation - 1) % ll.size();
           ll.remove(f);
           if (f >= ll.size()) {
               f = 0;
           }
        }
       // }
       return ll.get(0);



    }

}
