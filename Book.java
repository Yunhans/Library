public class Book {
    private String bookname;
	private String bookauthor;
	private String bookpublisher;
	private boolean bookstatus = true;

	public Book(String bookname, String bookauthor, String bookpublisher){
		setbookname(bookname);
		setbookauthor(bookauthor);
		setbookpublisher(bookpublisher);
	}

	public void setbookname(String bookname){this.bookname = bookname;}
	public String getbookname(){return bookname;}

	public void setbookauthor(String bookauthor){this.bookauthor = bookauthor;}
	public String getbookauthor(){return bookauthor;}

	public void setbookpublisher(String bookpublisher){this.bookpublisher = bookpublisher;}
	public String getbookpublisher(){return bookpublisher;}

	public void setbookstatus(boolean bookstatus){this.bookstatus = bookstatus;}
	public boolean getbookstatus(){return bookstatus;}

    public String toString(){
		return "書名 : " + getbookname() + " 作者 : " + getbookauthor() + " 出版社 : " + getbookpublisher() + " 狀態 : " + ((getbookstatus() == true)? "已出借\n":"庫中\n");
	}
}
