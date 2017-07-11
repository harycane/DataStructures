
public class TestArray {

    public static void main(String args[]) {
       String test = "123_";
       test = test.replaceAll("[*0-9]", "");
       test = test.replaceAll("_", "");
      if(!test.isEmpty())
       System.out.println(test);
      else
          System.out.println("hello");
  
        /*  String test[] = new String[6];
        test[0] = "I";
        test[1] = "I";
        test[2] ="not";
        test[3] = "ronman";
        test[4] = "super";
        test[5] = "man";
        
        for(int i = 0; i < test.length; i++) {
            for(int j = 0; j < test.length; j++) {
           if ( i!=j  &&  test[i] == test[j]) {
               adjust(test, j);
           }
            
        }
        }
        
        
        for(int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
            
        }
    }
    private static void adjust(String test[], int k) {
        int i;
        for (i = k+1 ; i < test.length; i++) {
            test[k-1] = test[k];
        }
        test[i-1] = null;
    }*/
}
}
