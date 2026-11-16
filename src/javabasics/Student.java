package javabasics;

public class Student {
    static int totalStudent;
    String name;
    int id;

    public Student(String name,int id){
        this.name = name;
        this.id = id;
        totalStudent++;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null ||getClass() != obj.getClass() ){
            return false;
        }
        Student other = (Student)obj;
        return  this.id == other.id;
    }

    //  静态方法
    public static void print(){
        System.out.println("hello");
    }
    public static void main(String[] args) {
        Student s1 = new Student("lee",190);
        System.out.println(Student.totalStudent);

        Student s2 = new Student("jay",190);
        System.out.println(Student.totalStudent);

        print();

        System.out.println(s1.equals(s2));
    }
}
