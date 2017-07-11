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
 * AlphaFreq Class implements a comparator to compare two
 * objects by alphabets first and then their frequencies.
 *
 */
public class AlphaFreq implements Comparator<Word> {
/**
 * compare method compares two Word objects by their alphabet first
 * and in case of tie, with the frequencies in ascending order.
 */
    @Override
    public int compare(Word o1, Word o2) {
        int alphaResult = o1.getWord().compareTo(o2.getWord());
        if (alphaResult != 0) {
            return alphaResult;
        }
        return Integer.compare(o1.getFrequency(), o2.getFrequency());
    }

}
