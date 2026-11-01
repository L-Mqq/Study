package Work;

public class FirstWork {
    public static void main(String[] args) {
        //Jay 19   99  99.5
        //Amy 20   88  90
        //Alen 18  89.5  98
        int totalStudents = 3;
        int totalSubjects = 2;

        //基本数据
        String[] names = {"Jay","Amy","Alen"};
        int[] ages = {19,20,18};
        double[] chineseScores = {99,88,89.5};
        double[] mathScores = {99.5,90,98};

        double[] totalScores = new double[totalStudents];
        double[] avrScores = new double[totalStudents];

        //计算总成绩平均成绩
        for (int i = 0;i< totalStudents;i++){
            totalScores[i] = chineseScores[i] + mathScores[i];
            avrScores[i] =  totalScores[i] / totalSubjects;
        }

        String[] grades = new String[totalStudents];

        //划分等级
        for (int j = 0;j<totalStudents;j++){
            if (avrScores[j] >= 90){
                grades[j] = "A";
            }else if(avrScores[j] >= 80){
                grades[j] = "B";
            }else if(avrScores[j] >= 70){
                grades[j] = "C";
            } else if (avrScores[j] >= 60) {
                grades[j] = "D";
            }else{
                grades[j] = "F";
            }
        }

        //输出信息
        for (int k = 0;k<totalStudents;k++){
            System.out.println("姓名：" + names[k]);
            System.out.println("年龄：" + ages[k]);
            System.out.println("语文成绩：" + chineseScores[k]);
            System.out.println("数学成绩：" + mathScores[k]);
            System.out.println("总成绩：" + totalScores[k]);
            System.out.println("平均成绩：" + avrScores[k]);
            System.out.println("评价等级：" + grades[k]);
        }

        //应用 把Alen语文成绩改为60
        int chineseSize = chineseScores.length;
        int index = 2;
        if (index >= 0 && index < chineseSize){
            chineseScores[index] = 60;
            totalScores[index] = chineseScores[index] + mathScores[index];
            avrScores[index] = totalScores[index] / totalSubjects;
            if (avrScores[index] >= 90){
                grades[index] = "A";
            }else if(avrScores[index] >= 80){
                grades[index] = "B";
            }else if(avrScores[index] >= 70){
                grades[index] = "C";
            } else if (avrScores[index] >= 60) {
                grades[index] = "D";
            }else{
                grades[index] = "F";
            }
            System.out.println("更新后：");
            System.out.println("姓名：" + names[index]);
            System.out.println("年龄：" + ages[index]);
            System.out.println("语文成绩：" + chineseScores[index]);
            System.out.println("数学成绩：" + mathScores[index]);
            System.out.println("总成绩：" + totalScores[index]);
            System.out.println("平均成绩：" + avrScores[index]);
            System.out.println("评价等级：" + grades[index]);

           // System.out.printf("输入有效");
        }else{
            System.out.println("输入有效数字");
        }


    }
}
