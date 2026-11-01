package Work;

public class NegativeAgeExcetion extends Exception{
    public NegativeAgeExcetion(String message){
        super(message);
    }


    public static double calculateAverageAge(int[] ages) throws NegativeAgeExcetion{
        double totalAges = 0;
         for (int i = 0;i<ages.length;i++){
             if (ages[i] < 0){
                 throw new NegativeAgeExcetion("年龄不能为负");
             }
              totalAges += ages[i];
         }
         return totalAges / ages.length;
    }

    public static void main(String[] args) {
        int[] test1 = {13,15,17,19,20};
        int[] test2 = {13,15,-1,19,20};
        try {
            double result =  calculateAverageAge(test2);
            System.out.println(result);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("end");
        }
    }
}
