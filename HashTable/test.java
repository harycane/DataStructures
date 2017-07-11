
public class test {
    
    
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;

        // TODO implement constructor and methods
        /**
         * Constructs a new DataItem of the table.
         * @param k key of DataItem
         */
        DataItem(String value , int k) {
            this.value = value;
            this.frequency = k;
        }
    }

    public static void main(String args[]) {
        
        DataItem[] input = new DataItem[4];
        for (int i = 0; i < input.length; i++) {
        System.out.println(input[i]);
        }
        String input1 = "cats";
        int result = 0;
        for (int i = input1.length() - 1; i >= 0; i--) {
            int r = (int)input1.charAt(i) - 96; System.out.println (r);

            result = result * 27 + r;
        }
    }
}
