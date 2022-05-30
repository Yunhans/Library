import java.util.ArrayList;
public class Library {
    public static ArrayList<Book> library = new ArrayList<>(){{
        add(0, new Book("Harry Potter", "J.K.Rowling", "Bloomsbury Publishing plc", 0));
    }};

    public String getLibrary(){
        String allBooks = "";
        for(Book book : library){
            allBooks += book.toString();
        }
        return allBooks;
    }

    public String serchbyName(String name){
        String sameBooks = null;
        for(Book book : library){
            if(name.equals(book.getbookname())){
                sameBooks += book;
            }
        }
        return sameBooks==null? "查無此書" : sameBooks;
    } 

    public String serchbyAuthor(String author){
        String sameBooks = null;
        for(Book book : library){
            if(author.equals(book.getbookauthor())){
                sameBooks += book;
            }
        }
        return sameBooks==null? "查無此書" : sameBooks;
    } 

    public String serchbyPublisher(String publisher){
        String sameBooks = null;
        for(Book book : library){
            if(publisher.equals(book.getbookpublisher())){
                sameBooks += book;
            }
        }
        return sameBooks==null? "查無此書" : sameBooks;
    }

    public void addBook(String bookname, String bookauthor, String bookpublisher){
        for(int i=0; i<=library.size(); i++){
            library.add(new Book(bookname, bookauthor, bookpublisher, i));
        }
    }

    public void editBook(){

    }

    public void deleteBook(){
        
    }
}
