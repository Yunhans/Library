import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Library {
    public static ArrayList<Book> library = new ArrayList<>(){{
        add(0, new Book("Harry Potter", "J.K.Rowling", "Bloomsbury Publishing", 0));
        add(1, new Book("Percy Jackson", "Rick Riordan", "Disney Hyperion", 1));
    }};
    private String bookname;
	private String bookauthor;
	private String bookpublisher;
    private String sameBooks = "";

    public String getLibrary(){
        String allBooks = "";
        for(Book book : library){
            if(book!=null){
                allBooks += book.toString();
            }
        }
        JOptionPane.showMessageDialog(null, allBooks, "所有館藏", JOptionPane.INFORMATION_MESSAGE);
        return allBooks;
    }

    public String searchBook(){
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
        return sameBooks;
    }

    public String searchbyName(){
        bookname = JOptionPane.showInputDialog(null, "輸入書名", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookname == null){
            searchBook();
            return "";
        }else{
            for(Book book : library){
                if(book != null && bookname.equals(book.getbookname())){
                    sameBooks += book;
                }
            }
            JOptionPane.showMessageDialog(null, sameBooks==""? "查無此書" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            return sameBooks;
        }
    } 

    public String searchbyAuthor(){
        bookauthor = JOptionPane.showInputDialog(null, "輸入作者", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookauthor == null){
            searchBook();
            return "";
        }else{
            for(Book book : library){
                if(book != null && bookauthor.equals(book.getbookauthor())){
                    sameBooks += book;
                }
            }
            JOptionPane.showMessageDialog(null, sameBooks==""? "查無此作者" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            return sameBooks;
        }
    } 

    public String searchbyPublisher(){
        bookpublisher = JOptionPane.showInputDialog(null, "輸入出版社", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookpublisher == null){
            searchBook();
            return "";
        }else{
            for(Book book : library){
                if(book != null && bookpublisher.equals(book.getbookpublisher())){
                    sameBooks += book;
                }
            }
            JOptionPane.showMessageDialog(null, sameBooks==""? "查無此出版社" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            return sameBooks;
        } 
    }

    public void addBook(){
        sameBooks = "";
        int id = 0, empty = 0;
        bookname = JOptionPane.showInputDialog(null, "輸入書名");
        bookauthor = JOptionPane.showInputDialog(null, "輸入作者");
        bookpublisher = JOptionPane.showInputDialog(null, "輸入出版社");
        if(bookname == null || bookauthor == null || bookpublisher == null){
            JOptionPane.showMessageDialog(null, "有資料為空", "新增書籍失敗", JOptionPane.ERROR_MESSAGE);
        }else{
            for(Book book : library){
                if(book != null && bookname.equals(book.getbookname())){
                    sameBooks += book;
                }else if(book == null){
                    empty = id;
                }
                id++;
            }
            if(sameBooks == ""){
                if(empty != 0){
                    id = empty;
                    library.set(id, new Book(bookname, bookauthor, bookpublisher, id));
                }else{
                    id = library.size();
                    library.add(new Book(bookname, bookauthor, bookpublisher, id));
                }
                JOptionPane.showMessageDialog(null, "新增成功\n"+library.get(id), "新增書籍", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "此書已存在"+sameBooks, "新增書籍失敗", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }

    public void deleteBook(){
        sameBooks = "";
        bookname = JOptionPane.showInputDialog(null, "輸入書名", "刪除書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookname == null){
            JOptionPane.showMessageDialog(null, "取消刪除");
        }else{
            for(Book book : library){
                if(book != null && bookname.equals(book.getbookname())){
                    sameBooks += book;
                }
            }
            if(sameBooks==""){
                JOptionPane.showMessageDialog(null, "查無此書", "刪除書籍",JOptionPane.ERROR_MESSAGE);
            }else{
                int id = Integer.parseInt(JOptionPane.showInputDialog(null, sameBooks+"\n輸入要刪除的書的ID:", "刪除書籍", JOptionPane.QUESTION_MESSAGE));
                int option = JOptionPane.showConfirmDialog(null, "確定要刪除以下書籍嗎?\n"+library.get(id));
                if(option == 0){
                    library.set(id, null);
                    JOptionPane.showMessageDialog(null, "刪除成功");
                }else{
                    deleteBook();
                }
            }
        }
        
    }

    public void editBook(){
        sameBooks = "";
        bookname = JOptionPane.showInputDialog(null, "輸入書名", "修改書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookname == null){
            JOptionPane.showMessageDialog(null, "取消修改");
        }else{
            for(Book book : library){
                if(book != null && bookname.equals(book.getbookname())){
                    sameBooks += book;
                }
            }
            if(sameBooks==""){
                JOptionPane.showMessageDialog(null, "查無此書", "修改書籍",JOptionPane.ERROR_MESSAGE);
            }else{
                int id = Integer.parseInt(JOptionPane.showInputDialog(null, sameBooks+"\n輸入要修改的書的ID:", "修改書籍", JOptionPane.QUESTION_MESSAGE));
                bookname = library.get(id).getbookname();
                bookauthor = library.get(id).getbookauthor();
                bookpublisher = library.get(id).getbookpublisher();
                int option = JOptionPane.showConfirmDialog( null, "確定要修改以下書籍嗎?\n"+library.get(id));
                if(option == 0){
                    String [] searchOptions = {"書名", "作者", "出版商"};
                    int searchOption = JOptionPane.showOptionDialog(null, "選擇修改", "修改書籍", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, searchOptions, searchOptions[0]);
    
                    switch(searchOption){
                        case 0:
                            bookname = JOptionPane.showInputDialog(null, "原書名: "+library.get(id).getbookname()+"\n修改為: ");
                            JOptionPane.showMessageDialog(null, "修改成功");
                            break;
                        case 1:
                            bookauthor = JOptionPane.showInputDialog(null, "原作者: "+library.get(id).getbookauthor()+"\n修改為: ");
                            JOptionPane.showMessageDialog(null, "修改成功");
                            break;
                        case 2:
                            bookpublisher = JOptionPane.showInputDialog(null, "原出版社: "+library.get(id).getbookpublisher()+"\n修改為: ");
                            JOptionPane.showMessageDialog(null, "修改成功");
                            break;
                    }
                    library.set(id, new Book(bookname, bookauthor, bookpublisher, id));
                }else{
                    deleteBook();
                }
                
            }
        }
    }
}
