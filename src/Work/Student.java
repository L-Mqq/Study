package Work;

public class Student {
    String name;
    int age;
    double chineseScore;
    double mathScore;
    double totalScore;
    double avrScore;
    String grade;

    int  totalSubjet = 2;
    static int totalStudent = 3;

    //构造方法
    public Student(String name,int age,double chineseScore,double mathScore){
        this.name = name;
        this.age = age;
        this.chineseScore = chineseScore;
        this.mathScore = mathScore;
    }

    //计算总成绩
    public void setTotalScore(){
        this.totalScore = chineseScore + mathScore;
    }

    //计算平均成绩
    public void setAvrScore(){
        this.avrScore = totalScore / totalSubjet;
    }


    //计算等级
    public void setGrade(){
        if (avrScore >= 90){
            this.grade = "A";
        } else if (avrScore >= 80) {
            this.grade = "B";
        } else if (avrScore >= 70) {
            this.grade = "C";
        } else if (avrScore >= 60) {
            this.grade = "D";
        }else {
            this.grade = "F";
        }
    }

    //输出信息
    public void getInfo(){
        System.out.println("姓名：" + name + "年龄：" + age + "语文成绩：" + chineseScore + "数学成绩：" + mathScore + "总成绩：" + totalScore + "平均成绩：" + avrScore + "成绩等级：" + grade);
    }

    public static void main(String[] args) {
        Student[] students = new Student[totalStudent];
        students[0] = new Student("Jay", 18, 88, 99);
        students[1] = new Student("Amy", 20, 88.5, 90);
        students[2] = new Student("Judy", 18, 77, 98.5);

        for (int i = 0;i < totalStudent ; i++){
            students[i].setTotalScore();
            students[i].setAvrScore();
            students[i].setGrade();
            students[i].getInfo();
        }

        //修改Amy的数学为67
        int index = 1;
         if (index >= 0 && index <totalStudent){
             students[index].mathScore = 67;
             students[index].setTotalScore();
             students[index].setAvrScore();
             students[index].setGrade();
             students[index].getInfo();
         }else {
             System.out.println("输入有效数字");
         }

    }
}
