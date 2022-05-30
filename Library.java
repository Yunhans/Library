import java.util.ArrayList;
public class Library {
    public static ArrayList<Book> library = new ArrayList<>();

    public void setLibrary(){
        library.add(new Book("Harry Potter", "J.K.Rowling", "Bloomsbury Publishing plc"));
    }

    public String getLibrary(){
        String allBooks = "";
        for(Book book : library){
            allBooks += book;
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
        
    }

    public void editBook(){

    }

    public void deleteBook(){
        
    }
}
