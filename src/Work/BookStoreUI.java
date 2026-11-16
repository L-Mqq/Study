package Work;

public class BookStoreUI {
    public static void main(String[] args) {
        BookStore bookStore  = new BookStore();

        //添加书籍
        bookStore.addBook("1","数据结构");
        bookStore.addBook("2","sql");
        bookStore.addBook("3","java");
        bookStore.addBook("4","python");

        //查找书籍
        bookStore.findBook("1");
        bookStore.findBook("5");

        //买书
        System.out.println("-------------------");
        bookStore.buyBook("2",2);
        bookStore.buyBook("4",5);
        bookStore.getTopThreeBook();

        System.out.println("-------------------------");
        bookStore.buyBook("1",9);
        bookStore.getTopThreeBook();

        System.out.println("-------------");
        bookStore.buyBook("3",88);
        bookStore.getTopThreeBook();

    }
}
