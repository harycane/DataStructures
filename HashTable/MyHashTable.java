/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 4 HashTable Implementation with linear probing
 *
 * Andrew ID: hramasub.
 *
 * @author HaryKrishnan Ramasubramanian.
 */
public class MyHashTable implements MyHTInterface {

    /**
     * Constant to indicate item in a cell (index) has been deleted. With open
     * addressing, each cell can be one of the following three states. 1. empty
     * 2. occupied 3. deleted
     */
    private static final DataItem DELETED = new DataItem("#DEL#", -1);
    /**
     * Underlying array of DataItem.
     */
    private DataItem[] hashArray;
    /**
     * Default Capacity = 10.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Default Load factor = 0.5.
     */
    private static final double DEFAULT_LF_THRESHOLD = 0.5;
    /**
     * Current Load factor.
     */
    private double loadFactor;

    /**
     * Number of Collisions.
     */
    private int numberOfCollisions;

/**
 * No Argument Constructor to initialize array to default size.
 */
    public MyHashTable() {
        hashArray = new DataItem[DEFAULT_CAPACITY];
        loadFactor = (double) size() / hashArray.length;
        numberOfCollisions = 0;
    }
/**
 * Parameter constructor.
 * @param size input size to initialize the array.
 */
    public MyHashTable(int size) {
        if (size <= 0) {
            throw new RuntimeException("Initial Capacity cannot be <= 0");
        }
        hashArray = new DataItem[size];
        loadFactor = (double) size() / hashArray.length;
        numberOfCollisions = 0;

    }
    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value 2. compress into the table using
     * modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied
     * as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     *
     * @param input
     *            input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        int convertedResult = 0;
        int length = input.length();
        for (int i = 0; i <= length - 1; i++) {
            int r = (int) input.charAt(i) - 96;
            convertedResult = convertedResult * 27 + r;
            convertedResult = convertedResult % hashArray.length;
        }

        int compressedResult = convertedResult % hashArray.length;

        if (compressedResult < 0) {
            compressedResult += hashArray.length;
        }
        return compressedResult;

    }

    /**
     * Doubles array length and rehash items whenever the load factor
     * exceeds a threshold of 0.5.
     */
    private void rehash() {
        int oldLength = hashArray.length;
        int doubleLength = oldLength * 2;
        int primeLength;
        //checks isPrime if not chooses the next Prime
        while (!isPrime(doubleLength)) {
            ++doubleLength;
        }
        primeLength = doubleLength;

        System.out.print("Rehashing " + size() + " items, new size is " + primeLength + "\n");

        DataItem[] hashArrayCopy = new DataItem[hashArray.length];
        System.arraycopy(hashArray, 0, hashArrayCopy, 0, hashArray.length);

        hashArray = new DataItem[primeLength];
        loadFactor = (double) size() / hashArray.length;
        numberOfCollisions = 0;
        for (int i = 0; i < hashArrayCopy.length; i++) {
            if (hashArrayCopy[i] != null && hashArrayCopy[i].frequency != DELETED.frequency) {
              //inserts the word for as many as times as freq.
                while (hashArrayCopy[i].frequency > 0) {
                insert(hashArrayCopy[i].value);
                --hashArrayCopy[i].frequency;
                }
            }
        }



    }
    /**
     * Inserts a new String value (word).
     * Frequency of each word to be stored too.
     * @param value String value to add
     */
    @Override
    public void insert(String value) {

        if (value == null || value.trim().length() == 0) {
            return;
        }
      //matches only for valid words
        if (value.matches("[a-zA-Z]+")) {

            int freq = 1;
            int count = numberOfCollisions;
           boolean flag = contains(value);
         //To avoid over-counting collisions from consecutive elements having the same hash.
           boolean flag2 = true;
            int hashVal = hashFunc(value);

            // search for an empty slot or reusable slot, flagged as DELETED
            while ((hashArray[hashVal] != null) && (hashArray[hashVal].frequency != DELETED.frequency)) {
                if (!flag && flag2 && hashFunc(hashArray[hashVal].value) == hashFunc(value)) {
                    flag2 = false;
                    ++count;

                }
              //Existing word's frequency is incremented.
                if (hashArray[hashVal].value.equals(value)) {
                   freq = hashArray[hashVal].frequency;
                   freq = freq + 1;
                   break;
               }




                // linear probing with step size of 1
                hashVal++;
                // wrap around
                hashVal = hashVal % hashArray.length;
            }
            numberOfCollisions = count;
            DataItem newItem = new DataItem(value, freq);
            hashArray[hashVal] = newItem;
            loadFactor = (double) (size()) / hashArray.length;
          //if Load factor exceeds then rehash.
            if (loadFactor > DEFAULT_LF_THRESHOLD) {

                rehash();
            }

        } else {
            return;
        }
    }
    /**
     * Returns the size, number of items, of the table.
     * @return the number of items in the table
     */
    @Override
    public int size() {

        int count = 0;
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] != null && hashArray[i].frequency != DELETED.frequency) {
              //count no of words.
                ++count;
            }

        }
        return count;

    }
    /**
     * Displays the values of the table.
     * If an index is empty, it shows **
     * If previously existed data item got deleted, then it should show #DEL#
     */
    @Override
    public void display() {
      //StringBuilder append is more efficient than String concatenation.
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hashArray.length; i++) {
            //display in the expected format.
            if (hashArray[i] == null) {
                output.append("** ");
            } else if (hashArray[i].frequency == DELETED.frequency) {
                output.append(hashArray[i].value).append(" ");
            } else {
                output.append("[").append(hashArray[i].value).append(", ").append(hashArray[i].frequency).append("] ");
            }

        }
        System.out.print(output);
        System.out.println("");
    }
    /**
     * Returns true if value is contained in the table.
     * @param key String key value to search
     * @return true if found, false if not found.
     */
    @Override
    public boolean contains(String key) {
        if (key == null || key.trim().length() == 0) {
            return false;
        }
        int hashVal = hashFunc(key);

        while ((hashArray[hashVal] != null)) {
          //return true if equals method returns true.
            if (hashArray[hashVal].value.equals(key)) {
                return true;
            }
            // linear probing with step size of 1
            hashVal++;
            // wrap around
            hashVal = hashVal % hashArray.length;
        }

        return false;
    }
    /**
     * Returns the number of collisions in relation to insert and rehash.
     * When rehashing process happens, the number of collisions should be properly updated.
     *
     * The definition of collision is "two different keys map to the same hash value."
     * Be careful with the situation where you could overcount.
     * Try to think as if you are using separate chaining.
     * "How would you count the number of collisions?" when using separate chaining.
     * @return number of collisions
     */
    @Override
    public int numOfCollisions() {
        if (size() == 0 || loadFactor > DEFAULT_LF_THRESHOLD) {
            return 0;
        }
      //return no: of collisions.
        return numberOfCollisions;
    }
    /**
     * Returns the hash value of a String.
     * @param value value for which the hash value should be calculated
     * @return int hash value of a String
     */
    @Override
    public int hashValue(String value) {
      //avoids exposing the hashFunc implementation in the public method.
        return hashFunc(value);
    }
    /**
     * Returns the frequency of a key String.
     * @param key string value to find its frequency
     * @return frequency value if found. If not found, return 0
     */
    @Override
    public int showFrequency(String key) {
        if (key == null || key.trim().length() == 0) {
            return 0;
        }
        int hashVal = hashFunc(key);

        while ((hashArray[hashVal] != null) && (hashArray[hashVal].frequency != DELETED.frequency)) {
          //returns freq of a matched word.
            if (hashArray[hashVal].value.equals(key)) {
                return hashArray[hashVal].frequency;
            }
            // linear probing with step size of 1
            hashVal++;
            // wrap around
            hashVal = hashVal % hashArray.length;
        }
        return 0;
    }
    /**
     * Removes and returns removed value.
     * @param key String to remove
     * @return value that is removed. If not found, return null
     */
    @Override
    public String remove(String key) {
        if (key == null || key.trim().length() == 0) {
            return null;
        }
        int hashVal = hashFunc(key);
        while ((hashArray[hashVal] != null) && (hashArray[hashVal].frequency != DELETED.frequency)) {
            // found
            if (hashArray[hashVal].value.equals(key)) {
                DataItem item = hashArray[hashVal];
                // set flag for deletion
                hashArray[hashVal] = DELETED;
                return item.value;
            }
            // linear probing with step size of 1
            hashVal++;
            // wrap around
            hashVal = hashVal % hashArray.length;
        }

        return null;
    }
/**
 * isPrime method checks whether the parameter is prime.
 * @param num parameter to be determined whether prime or not.
 * @return boolean true if prime else false.
 */
    private boolean isPrime(int num) {
        boolean flag = true;
      //Factors from 2 to sqrt(n) for optimizing prime determination.
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;
        /**
         * Constructs a new DataItem of the table.
         *
         * @param k is the freq.
         * @param text is the String value.
         */
        DataItem(String text, int k) {
            this.value = text;
            this.frequency = k;
        }
    }

}
