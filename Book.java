public class Book {
	private String bookname;
	private String bookauthor;
	private String bookpublisher;
	private boolean bookstatus = true;
	private int bookID;

	public Book(String bookname, String bookauthor, String bookpublisher, int bookID){
		setbookname(bookname);
		setbookauthor(bookauthor);
		setbookpublisher(bookpublisher);
		setbookID(bookID);
	}

	public void setbookname(String bookname){this.bookname = bookname;}
	public String getbookname(){return bookname;}

	public void setbookauthor(String bookauthor){this.bookauthor = bookauthor;}
	public String getbookauthor(){return bookauthor;}

	public void setbookpublisher(String bookpublisher){this.bookpublisher = bookpublisher;}
	public String getbookpublisher(){return bookpublisher;}

	public void setbookstatus(boolean bookstatus){this.bookstatus = bookstatus;}
	public boolean getbookstatus(){return bookstatus;}

	public void setbookID(int bookID){this.bookID = bookID;}
	public int getbookID(){return bookID;}

	public String toString(){
        return String.format("ID : %03d\t書名 : %s\t作者 : %s\t出版社 : %s\t"+(getbookstatus()==true? "已出借\n" : "庫中"), getbookID(), getbookname(), getbookauthor(), getbookpublisher());
	}
}
