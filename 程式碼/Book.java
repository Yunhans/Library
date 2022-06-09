package 程式碼;
public class Book {

	private String bookName;
	private String author;
	private String publisher;
    private String style;
	private boolean status = true;
	private int id;
	private String date1; //借書日期
	private String date2; //還書日期

	public Book(String bookName, String author, String publisher, String style, int id){
		setBookname(bookName);
		setAuthor(author);
		setPublisher(publisher);
        setStyle(style);
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

    public void setStyle(String style){
        this.style = style;
    }
    public String getStyle(){
        return style;
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

	public void setdate1(String date1){
        this.date1 = date1;
    }
	public String getdate1(){
        return date1;
    }

	public void setdate2(String date2){
        this.date2 = date2;
    }
	public String getdate2(){
        return date2;
    }

	public String toString(){
        return String.format("ID: %04d\t書名: %s\t作者: %s\t出版社: %s\t分類: %s\t狀態: "+(getStatus()==true? "可借閱\n" : "已出借\n"), getID(), getBookname(), getAuthor(), getPublisher(), getStyle());
	}
    
}