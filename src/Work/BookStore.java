package Work;

import java.util.*;

public class BookStore {
    private Map<String, Book> books;
    private  Map<String,Integer> sales;
    private List<Book> topThreeBook;

    public BookStore(){
        books = new HashMap<>();
        sales = new HashMap<>();
        topThreeBook = new ArrayList<>();
    }

    //书店进书 (不能直接参数列表写 Book！)
    public void addBook(String id,String name){
        books.put(id,new Book(id,name));
    }


    //查书
    public void findBook(String id){
        if (books.get(id) != null){
            System.out.println(books.get(id).getName());
        }else{
            System.out.println("此书不存在");
        }
    }


    //获取销量前三的书
    public void getTopThreeBook(){
        Iterator<Book> iterator = topThreeBook.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getName());
        }
    }


    //更新销量前三书籍 （参考答案）
    private void updateTopThreeBook(String id){
        Book book = books.get(id);

        //判断用户买的书有没有在前三 没有加进去序列去进行排序
        if (!topThreeBook.contains(book)){
            topThreeBook.add(book);
        }

        //重新进行排序 之后取前三
        topThreeBook.sort((b1,b2)->sales.get(b2.getId()) - sales.get(b1.getId()));
        //要判断会不会超过三个 ！！！！
        if (topThreeBook.size() > 3){
            topThreeBook.remove(3);
        }
    }

    //用户买书
    public void buyBook(String id,Integer buyNumber){
        if (books.get(id) == null){
            System.out.println("此书不存在");
        }else {
            //要判断原本这本书有没有被买过！！！！！
             if (sales.get(id) == null){
                 sales.put(id,buyNumber);
                 updateTopThreeBook(id);
             }else {
                 sales.put(id,sales.get(id) + buyNumber);
                 updateTopThreeBook(id);
             }
        }

    }

}
