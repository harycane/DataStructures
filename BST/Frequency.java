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
/**
 *
 * Frequency class implements comparator to compare two Word Objects
 * by their frequencies in descending order.
 *
 */
public class Frequency implements Comparator<Word> {
/**
 * compare method compares two Word Objects by comparing their frequencies
 * in descending order.
 */
    @Override
    public int compare(Word o1, Word o2) {
        return Integer.compare(o2.getFrequency(), o1.getFrequency());
    }

}
