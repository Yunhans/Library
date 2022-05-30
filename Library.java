import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Library {
    public static ArrayList<Book> library = new ArrayList<>(){{
        add(0, new Book("Harry Potter", "J.K.Rowling", "Bloomsbury Publishing plc", 0));
        add(1, new Book("Percy Jackson", "Rick Riordan", "Disney Hyperion", 1));
    }};
    private String bookname;
	private String bookauthor;
	private String bookpublisher;
    private String sameBooks = null;

    public void getLibrary(){
        String allBooks = "";
        for(Book book : library){
            allBooks += book.toString();
        }
        JOptionPane.showMessageDialog(null, allBooks);
    }

    public void searchBook(){
        sameBooks = "";
        String [] searchOptions = {"書名", "作者", "出版商"};
        int searchOption = JOptionPane.showOptionDialog(null, "選擇查詢", "查詢書籍", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, searchOptions, searchOptions[0]);

        switch(searchOption){
            case 0:
                searchbyName();
                break;
            case 1:
                searchbyAuthor();
                break;
            case 2:
                searchbyPublisher();
                break;
        }
    }

    public void searchbyName(){
        bookname = JOptionPane.showInputDialog(null, "輸入書名");
        for(Book book : library){
            if(bookname.equals(book.getbookname())){
                sameBooks += book;
            }
        }
        JOptionPane.showMessageDialog(null, sameBooks==""? "查無此書" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
    } 

    public void searchbyAuthor(){
        bookauthor = JOptionPane.showInputDialog(null, "輸入作者");
        for(Book book : library){
            if(bookauthor.equals(book.getbookauthor())){
                sameBooks += book;
            }
        }
        JOptionPane.showMessageDialog(null, sameBooks==""? "查無此作者" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
    } 

    public void searchbyPublisher(){
        bookpublisher = JOptionPane.showInputDialog(null, "輸入出版社");
        for(Book book : library){
            if(bookpublisher.equals(book.getbookpublisher())){
                sameBooks += book;
            }
        }
        JOptionPane.showMessageDialog(null, sameBooks==""? "查無此出版社" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
    }

    public void addBook(){
        sameBooks = "";
        bookname = JOptionPane.showInputDialog(null, "輸入書名");
        bookauthor = JOptionPane.showInputDialog(null, "輸入作者");
        bookpublisher = JOptionPane.showInputDialog(null, "輸入出版社");
        for(Book book : library){
            if(bookname.equals(book.getbookname())){
                sameBooks += book;
            }
        }
        if(sameBooks == ""){
            for(int i=0; i<library.size(); i++){
                if(library.get(i)==null){
                    library.add(i, new Book(bookname, bookauthor, bookpublisher,i));
                    JOptionPane.showMessageDialog(null, "新增成功\n");
                    break;
                }else{
                    library.add(library.size(), new Book(bookname, bookauthor, bookpublisher,library.size()));
                    JOptionPane.showMessageDialog(null, "新增成功\n");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "此書已存在");
        }
    }

    public void editBook(){

    }

    public void deleteBook(){
        
    }
}
