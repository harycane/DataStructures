/**
 * Andrew ID: hramasub.
 *
 * @author HaryKrishnan Ramasubramanian.
 */
public class MyArray {
    /**
     * index gives the size of the words array.
     */
    private int index;
    /**
     * s is a string array that stores the words.
     */
    private String[] s;
    /**
     * default constructor.
     * Run time Complexity is O(1).
     */
    public MyArray() {

    }
    /**
     * initArray to double the array size.
     * @param size length of the array that has to be doubled.
     */
    private void initArray(int size) {
        if (size != 0) {
        s = new String[size * 2];
        } else {
            s = new String[1];
        }

    }

    /**
     * Run time complexity O(1).
     * @param initialCapacity is the initialization size.
     */
    public MyArray(int initialCapacity) {

        s = new String[initialCapacity];
    }

    /**
     * Run time complexity O(1) (Amortized).
     * @param text is the word to be added.
     */
    public void add(String text) {
        if (text == null) {
            return;
        }
        if (text.trim().length() == 0) {
            return;
        }

        if (text.matches("[a-zA-Z]+")) {

            if (index < s.length) {
                s[index] = text;
                index = index + 1;
            } else {
                String[] temp = new String[s.length];
                for (int i = 0; i < s.length; i++) {
                    temp[i] = s[i];
                }
                initArray(s.length);
                for (int i = 0; i < temp.length; i++) {
                    s[i] = temp[i];
                }
                s[index] = text;
                index = index + 1;
            }

        }
    }

    /**
     * Run time complexity O(n).
     * @param key is the key to be searched for.
     * @return boolean to find out whether search term exists or not.
     */
    public boolean search(String key) {
        boolean flag = false;
        for (int i = 0; i < index; i++) {
            if (key == null) {
                return false;
            }
            if (s[i].equals(key)) {
                flag = true;
                break;
            }
        }
        return flag;

    }

    /**
     * Run time complexity O(1).
     * @return int size of the word array.
     */
    public int size() {

        return index;
    }

    /**
     * Run time complexity O(1).
     * @return int length or capacity of the array.
     */
    public int getCapacity() {
        return s.length;
    }

    /**
     * Run time complexity O(n).
     */
    public void display() {

        for (int i = 0; i < index; i++) {

            System.out.print(s[i] + " ");

        }
        System.out.println();
    }

    /**
     * Run time complexity O(n^2).
     */
    public void removeDups() {
        for (int i = 0; i < index; i++) {
            int j = 0;
            while (j < index) {
                if (i != j && s[i].equals(s[j])) {
                    System.arraycopy(s, 0, s, 0, j);
                    System.arraycopy(s, j + 1, s, j, index - j - 1);
                    index = index - 1;
                    j = -1;
                }
                    j = j + 1;
            }
        }
    }
}
