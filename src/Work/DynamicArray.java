package Work;

public class DynamicArray<E>{
    private E[] array;
    private int size = 0;
    private int capacity = 10;

    //构造方法
   public DynamicArray(E element) throws Exception{
       //刚开始数组还没被创建，前面只是进行声明
     this.array = (E[]) new Object[capacity];
     array[size] = element;
     size++;

   }

   //获取数组
   public void getArray(){
       for(int i= 0;i <size; i++){
           System.out.print(array[i]);
       }
   }

   //获取元素的个数
    public int getSize(){
        return this.size;
    }

    //获取数组的容量
    public int getCapacity(){
       return this.capacity;
    }
    //获取指定元素
    public E get(int index) throws Exception{
        if (index < 0 || index >= size){
            throw new Exception("请输入正确的索引");
        }
        return array[index];
    }

    //删除指定元素
    public E remove(int index) throws Exception{
        if (index < 0 || index >= size){
            throw new Exception("请输入正确的索引");
        }
        E temp = array[index];
        for (int i =index; i < size - 1; i++){
            array[i] = array[i + 1];
        }
        //把最后一个设为 null size是从 1 开始的!
        array[size - 1] = null;
        size--;
        return temp;
    }

    //修改元素
    public void update(int index,E newElement) throws Exception{
        if (index < 0 || index >= size){
            throw new Exception("请输入0-"+ (size - 1)+ "的数字");
        }
        array[index] = newElement;
    }

    //单个添加 不是批量
    public void add(E element){
        if (size == capacity) {
            capacity = capacity * 2;
            E[] newArray = (E[])new Object[capacity];
            for (int i = 0; i < size;i++){
                newArray[i] = array[i];
            }

            this.array = newArray;
        }
        array[size++] = element;

    }

}


