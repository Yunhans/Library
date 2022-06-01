public class Book {

	private String bookName;
	private String author;
	private String publisher;
	private boolean status = true;
	private int id;
	private String date;

	public Book(String bookName, String author, String publisher, int id){
		setBookname(bookName);
		setAuthor(author);
		setPublisher(publisher);
		setID(id);
	}

	public void setBookname(String bookname){
        this.bookName = bookname;
    }
    
	public String getBookname(){
        return bookName;
    }

	public void setAuthor(String bookauthor){
        this.author = bookauthor;
    }
	public String getAuthor(){
        return author;
    }

	public void setPublisher(String publisher){
        this.publisher = publisher;
    }
	public String getPublisher(){
        return publisher;
    }

	public void setStatus(boolean status){
        this.status = status;
    }
	public boolean getStatus(){
        return status;
    }

	public void setID(int id){
        this.id = id;
    }
	public int getID(){
        return id;
    }

	public void setdate(String date){
        this.date = date;
    }
	public String getdate(){
        return date;
    }

	public String toString(){
        return String.format("ID: %04d\t書名: %s\t作者: %s\t出版社: %s\t狀態: "+(getStatus()==true? "可借閱\n" : "已出借\n"), getID(), getBookname(), getAuthor(), getPublisher());
	}
    
}
