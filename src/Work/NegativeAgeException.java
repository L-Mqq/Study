package Work;

public class NegativeAgeException extends Exception{
    public NegativeAgeException(String message){
        super(message);
    }


    public static double calculateAverageAge(int[] ages) throws NegativeAgeException {
        //对ages进行校验
        if (ages == null || ages.length == 0){
            throw new NegativeAgeException("数组不能为空");
        }
        double totalAges = 0;
         for (int i = 0;i<ages.length;i++){
             if (ages[i] < 0){
                 throw new NegativeAgeException("年龄不能为负");
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
