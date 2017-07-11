/**
 * 08722 Data Structures for Application Programmers.
 * Lab 3 Simple Sorting and Stability
 *
 * Insertion Sort Implementation
 *
 * Andrew ID: hramasub .
 * @author HaryKrishnan Ramasubramanian .
 */
public class InsertionSortApp {

    /**
     * test insertion sort and its stability.
     * @param args arguments
     */
    public static void main(String[] args) {
        Employee[] list = new Employee[10];

        // employee data : first name, last name, zip
        list[0] = new Employee("Patty", "Evans", 15213);
        list[1] = new Employee("Doc", "Smith", 15214);
        list[2] = new Employee("Lorraine", "Smith", 15216);
        list[3] = new Employee("Paul", "Smith", 15216);
        list[4] = new Employee("Tom", "Yee", 15216);
        list[5] = new Employee("Sato", "Hashimoto", 15218);
        list[6] = new Employee("Henry", "Stimson", 15215);
        list[7] = new Employee("Jose", "Vela", 15211);
        list[8] = new Employee("Minh", "Vela", 15211);
        list[9] = new Employee("Lucinda", "Craswell", 15210);

        System.out.println("Before Insertion Sorting: ");
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("");

        insertionSort(list, "last");

        System.out.println("After Insertion Sorting by last name: ");
        for (Employee e : list) {
            System.out.println(e);
        }
        System.out.println("");

        insertionSort(list, "zip");

        System.out.println("After Insertion Sorting by zip code: ");
        for (Employee e : list) {
            System.out.println(e);
        }

    }

    /**
     * Sorts employees either by last name or zip using Insertion Sort.
     * @param list list of employee objects
     * @param key key param value should be either "last" or "zip"
     */
    public static void insertionSort(Employee[] list, String key) {
        // TODO implement insertion sort here
        if(key.equals("last")) {
            for ( int j = 1; j < list.length; j++) {
                Employee tempEmp = list[j];
                String tempLastName = list[j].getLastName();
                int i = j;
                while( i > 0 && list[i-1].getLastName().compareToIgnoreCase(tempLastName) > 0) {
                    list[i] = list[i-1];
                    i = i-1;
                }
                if(j != i) {
                    list[i] = tempEmp;
                }
            }
        }
        
  
        if (key.equals("zip")) {
            for ( int j = 1; j < list.length; j++) {
                Employee tempEmp = list[j];
                int tempZip = list[j].getZipCode();
                int i = j;
                while( i > 0 && list[i-1].getZipCode() > tempZip) {
                    list[i] = list[i-1];
                    i = i-1;
                }
                if(j != i) {
                    list[i] = tempEmp;
                    //System.arraycopy(list, i, list, i + 1, j - i);
                }
            }
    }

}
}
