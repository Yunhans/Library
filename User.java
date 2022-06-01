import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.time.LocalDate; //計算時間差距的
import java.time.temporal.ChronoUnit;

public class User {

    static ArrayList<Student> student = new ArrayList<>(){{
        add(0, new Student("test", "test", "123"));
    }};
    static ArrayList<Teacher> teacher = new ArrayList<>();
    static ArrayList<Staff> staff = new ArrayList<>();
    static Admin admin = new Admin("admin","admin","admin");

    static ArrayList<Book> library = new ArrayList<Book>(){{
        add(0, new Book("Harry Potter", "J.K.Rowling", "Bloomsbury", 0));
        add(1, new Book("Percy Jackson", "Rick Riordan", "Disney", 1));
        add(2, new Book("123", "123", "123",2));
    }};

    static int id=-1;
    static int identity = -1;     // -1: none, 0: student, 1: teacher, 2: staff, 3: admin

    public static void main (String [] args) {
        welcome();
    }

    //歡迎畫面
    public static void welcome(){  
        id = -1;
        identity = -1;;
        String options [] = {"登入", "註冊"};
        int option = JOptionPane.showOptionDialog(null, "選擇功能", "央央圖書館", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        switch(option){
            case 0:
                login();
                break;
            case 1:
                register();
                break;
        }
    }
    
    //註冊
    public static void register(){
        String options [] = {"學生", "老師", "職員"};
        int option = JOptionPane.showOptionDialog(null, "選擇身份", "註冊", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        String name = JOptionPane.showInputDialog("輸入姓名:");
        String account = JOptionPane.showInputDialog("註冊帳號:");
        String password = JOptionPane.showInputDialog("設定密碼:");
        if(name == null || account == null || password == null){
            JOptionPane.showMessageDialog(null, "有資料為空", "註冊失敗", JOptionPane.ERROR_MESSAGE);
            welcome();
        }else{
            switch(option){
                case 0:
                    id = student.size();
                    student.add(new Student(name, account, password));
                    identity = option;
                    break;
                case 1:
                    id = teacher.size();
                    teacher.add(new Teacher(name, account, password));
                    identity = option;
                    break;
                case 2:
                    id = staff.size();
                    staff.add(new Staff(name, account, password));
                    identity = option;
                    break;
                default :
                    welcome();
                    break;
            }
        }
        menu();
    }

    //登入
    public static void login(){
        boolean exist = false;
        String options [] = {"學生", "老師", "職員", "管理員"};
        int option = JOptionPane.showOptionDialog(null, "選擇身份", "登入", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        String account = JOptionPane.showInputDialog("帳號:");
        switch(option){
            case 0:
                for(int i=0; i<student.size(); i++){
                    if(student.get(i).getAccount().equals(account)){
                        exist = true;
                        id = i;
                        identity = 0;
                        break;
                    }
                }
                break;
            case 1:
                for(int i=0; i<teacher.size(); i++){
                    if(teacher.get(i).getAccount().equals(account)){
                        exist = true;
                        id = i;
                        identity = 1;
                        break;
                    }
                }
                break;
            case 2:
                for(int i=0; i<staff.size(); i++){
                    if(staff.get(i).getAccount().equals(account)){
                        exist = true;
                        id = i;
                        identity = 2;
                        break;
                    }
                }
                break;
            case 3:
                if(admin.getAccount().equals(account)){
                    exist = true;
                    identity = 3;
                }
                break;
        }

        if(exist==true){
            String password = JOptionPane.showInputDialog("密碼:");
            if(password.equals(member().getPassword())){
                if(member()==admin){
                    adminMenu();
                }else{
                    menu();
                }
            }
            if(identity == -1){
                JOptionPane.showMessageDialog(null, "密碼錯誤", "登入失敗", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "此帳號不存在", "登入失敗", JOptionPane.ERROR_MESSAGE);
        }
        welcome();
    }

    public static Member member(){
        switch(identity){
            case 0:
                return student.get(id);
            case 1:
                return teacher.get(id);
            case 2:
                return staff.get(id);
            case 3:
                return admin;
            default:
                return null;
        }
    }

    //使用者總介面
    public static void menu(){
        String options [] = {"借書", "還書", "查詢書籍", "借閱紀錄", "登出"};
        int option = JOptionPane.showOptionDialog(null, "選擇功能", "央央圖書館", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        switch(option){
            case 0:
                borrowBook();
                break;
            case 1:
                returnBook();
                break;
            case 2:
                searchBook();
                menu();
                break;
            case 3:
                history();
                break;
            case 4:
                welcome();
                break;
        }
    }

    //借書
	public static void borrowBook() {

		String option[]={"繼續借書","回總畫面"};
		int decision = 0;

		do{

			String sameBooks = "";
			String record="";
			String bookname = JOptionPane.showInputDialog(null, "輸入書名", "查詢書籍", JOptionPane.QUESTION_MESSAGE);

			for(int i=0; i<library.size(); i++){

				if(library.get(i) != null && bookname.equals(library.get(i).getBookname())){
				sameBooks += library.get(i);
				}

			}

			if( bookname == null || sameBooks == ""){
				JOptionPane.showMessageDialog(null, "查無此書", "查詢結果", JOptionPane.ERROR_MESSAGE);
				decision = 1;
			}

			else{
				int i = Integer.parseInt(JOptionPane.showInputDialog(null, "搜尋結果如下:\n"+sameBooks+"\n輸入要借閱的書的ID:", "借閱書籍", JOptionPane.QUESTION_MESSAGE));
    
				if(member().borrowed.size() == member().borrowLimit()){
					String show = String.format("你已借%d本書,不能再借了", member().borrowLimit());
					JOptionPane.showMessageDialog(null, show);
					decision = 1;
				}
				else if(library.get(i).getStatus()==true){ //沒有被借走
					library.get(i).setStatus(false);
					member().borrowed.add(library.get(i)); //加到借閱書列中

					record += "ID: " + library.get(i).getID() + "書名 : " + library.get(i).getBookname() + " 作者 : " + library.get(i).getAuthor() + " 出版社 : " + library.get(i).getPublisher(); 
					//將此紀錄加入借閱紀錄中

					library.get(i).setdate1(JOptionPane.showInputDialog(null, "借閱日期?(請照yyyy-mm-dd格式輸入)"));

					record += "借閱日期 : " + library.get(i).getdate1() + "\n"; //登入借閱日期

					member().newRecord(record);

					String show = String.format("%s借書成功,借書期限:%s天", record, member().borrowDay());

					decision = JOptionPane.showOptionDialog(null, show,"訊息",1,1,null,option,option[0]);
				}
				else{  //被借走了
					decision = JOptionPane.showOptionDialog(null, "書已被借走","訊息",1,1,null,option,option[0]);
				}
			}

		}while(decision == 0 && member().borrowed.size()<member().borrowLimit());
		menu();
	}

	//還書
	public static void returnBook() {
        int i = 0;
		String record = "";
		String [] text = new String[10];
		for(i = 0 ; i< member().borrowed.size();i++){
			if(text[i]==null){
				text[i]= member().borrowed.get(i).getBookname();}
        }
        String [] options = new String [i];
        if(text[0]==null){
            JOptionPane.showMessageDialog(null, "你沒有正在借的書喔", "訊息", JOptionPane.ERROR_MESSAGE);
            menu();
        }
        for(int j = 0; j<options.length; j++){
            options[j] = text[j];
        }
		int choice = JOptionPane.showOptionDialog(null,"選擇要還的書：","訊息",1,1,null,options,options[0]);


		record += "ID: " + member().borrowed.get(choice).getID() + "書名 : " + member().borrowed.get(choice).getBookname() + " 作者 : " + member().borrowed.get(choice).getAuthor() + " 出版社 : " + member().borrowed.get(choice).getPublisher();

		member().borrowed.get(choice).setdate2(JOptionPane.showInputDialog(null, "還書日期?(請照yyyy-mm-dd格式輸入)"));

		record += "還書日期 : " + member().borrowed.get(choice).getdate2(); 

		long fee = 0;

		LocalDate date1 = LocalDate.parse(member().borrowed.get(choice).getdate1());
		LocalDate date2 = LocalDate.parse(member().borrowed.get(choice).getdate2());

		long ok = ChronoUnit.DAYS.between(date1, date2);

		if (ok>member().borrowDay()){
			fee = (ok-member().borrowDay())*10; //過期一天10塊錢
		}

		if(fee==0)
		{
			record +="\n";
		}
		else
		{
			record +="過期 : " + ok + "天 罰款 : " + fee + " 元\n";
		}

		member().newRecord(record);
		member().borrowed.get(choice).setStatus(true);
		member().borrowed.remove(choice);
		JOptionPane.showMessageDialog(null,"還書成功"+ ((fee==0)?"":"過期 : " + ok + "天 罰款 : " + fee + " 元"));
		menu();
	}

	public static void history() {
		JOptionPane.showMessageDialog(null,member().getrecord());
		menu();
	}

    //查詢書籍
    public static String searchBook() {
        String sameBooks = "";
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

    //搜尋出版社
    public static String searchbyPublisher() {
        String sameBooks = "";
        String publisher = JOptionPane.showInputDialog(null, "輸入出版社", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(publisher == null){
            searchBook();
            return "";
        }else{
            for(Book book : library){
                if(book != null && publisher.equals(book.getPublisher())){
                    sameBooks += book;
                }
            }
            JOptionPane.showMessageDialog(null, sameBooks==""? "查無此出版社" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            return sameBooks;
        } 
    }

    //搜尋作者
    public static String searchbyAuthor() {
        String sameBooks = "";
        String author = JOptionPane.showInputDialog(null, "輸入作者", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(author == null){
            searchBook();
            return "";
        }else{
            for(Book book : library){
                if(book != null && author.equals(book.getAuthor())){
                    sameBooks += book;
                }
            }
            JOptionPane.showMessageDialog(null, sameBooks==""? "查無此作者" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            return sameBooks;
        }
    }

    //搜尋書名
    public static String searchbyName() {
        String sameBooks = "";
        String bookname = JOptionPane.showInputDialog(null, "輸入書名", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookname == null){
            searchBook();
            return "";
        }else{
            for(Book book : library){
                if(book != null && bookname.equals(book.getBookname())){
                    sameBooks += book;
                }
            }
            JOptionPane.showMessageDialog(null, sameBooks==""? "查無此書" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            return sameBooks;
        }
    }

    //管理員總介面
    public static void adminMenu() {
        String [] options = {"新增書籍", "修改書籍資訊", "刪除書籍", "查詢書籍", "顯示所有書籍", "離開"};
        int option = JOptionPane.showOptionDialog(null, "選擇功能", "管理員", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch(option){
            case 0:
                addBook();
                break;
            case 1:
                editBook();
                break;
            case 2:
                deleteBook();
                break;
            case 3:
                searchBook();
                adminMenu();
                break;
            case 4:
                getLibrary();
                break;
            case 5:
                welcome();
                break;
        }
        
    }

    public static void deleteBook() {
        String sameBooks = "";
        String bookname = JOptionPane.showInputDialog(null, "輸入書名", "刪除書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookname == null){
            JOptionPane.showMessageDialog(null, "取消刪除");
        }else{
            for(Book book : library){
                if(book != null && bookname.equals(book.getBookname())){
                    sameBooks += book;
                }
            }
            if(sameBooks==""){
                JOptionPane.showMessageDialog(null, "查無此書", "刪除書籍",JOptionPane.ERROR_MESSAGE);
            }else{
                int id = Integer.parseInt(JOptionPane.showInputDialog(null, "搜尋結果如下:\n"+sameBooks+"\n輸入要刪除的書的ID:", "刪除書籍", JOptionPane.QUESTION_MESSAGE));
                int option = JOptionPane.showConfirmDialog(null, "確定要刪除以下書籍嗎?\n"+library.get(id));
                if(option == 0){
                    library.set(id, null);
                    JOptionPane.showMessageDialog(null, "刪除成功");
                }
            }
        }
        adminMenu();
    }

    public static void editBook() {
        String sameBooks = "";
        String bookname = JOptionPane.showInputDialog(null, "輸入書名", "修改書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookname == null){
            JOptionPane.showMessageDialog(null, "取消修改");
        }else{
            for(Book book : library){
                if(book != null && bookname.equals(book.getBookname())){
                    sameBooks += book;
                }
            }
            if(sameBooks==""){
                JOptionPane.showMessageDialog(null, "查無此書", "修改書籍",JOptionPane.ERROR_MESSAGE);
            }else{
                int id = Integer.parseInt(JOptionPane.showInputDialog(null, "搜尋結果如下:\n"+sameBooks+"\n輸入要修改的書的ID:", "修改書籍", JOptionPane.QUESTION_MESSAGE));
                bookname = library.get(id).getBookname();
                String author = library.get(id).getAuthor();
                String publisher = library.get(id).getPublisher();
                int option = JOptionPane.showConfirmDialog( null, "確定要修改以下書籍嗎?\n"+library.get(id));
                if(option == 0){
                    String [] searchOptions = {"書名", "作者", "出版商"};
                    int searchOption = JOptionPane.showOptionDialog(null, "選擇修改", "修改書籍", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, searchOptions, searchOptions[0]);
    
                    switch(searchOption){
                        case 0:
                            bookname = JOptionPane.showInputDialog(null, "原書名: "+library.get(id).getBookname()+"\n修改為: ");
                            JOptionPane.showMessageDialog(null, "修改成功");
                            break;
                        case 1:
                            author = JOptionPane.showInputDialog(null, "原作者: "+library.get(id).getAuthor()+"\n修改為: ");
                            JOptionPane.showMessageDialog(null, "修改成功");
                            break;
                        case 2:
                            publisher = JOptionPane.showInputDialog(null, "原出版社: "+library.get(id).getPublisher()+"\n修改為: ");
                            JOptionPane.showMessageDialog(null, "修改成功");
                            break;
                    }
                    library.set(id, new Book(bookname, author, publisher, id));
                }
            }
        }
        adminMenu();
    }

    //新增書籍
    public static void addBook() {
        String sameBooks = "";
        int id = 0 ,empty = 0;
        String bookname = JOptionPane.showInputDialog(null, "輸入書名");
        String author = JOptionPane.showInputDialog(null, "輸入作者");
        String publisher = JOptionPane.showInputDialog(null, "輸入出版社");
        if(bookname == null || author == null || publisher == null){
            JOptionPane.showMessageDialog(null, "有資料為空", "新增書籍失敗", JOptionPane.ERROR_MESSAGE);
        }else{
            for( int i=0; i<library.size(); i++){
                if( library.get(i) != null && bookname.equals( library.get(i).getBookname() ) ){
                    sameBooks += library.get(i).getBookname();
                }else if(library.get(i) == null){
                    empty = i;
                }
                id++;
            }
            if(sameBooks == ""){
                if(empty != 0){
                    id = empty;
                    library.set(id, new Book(bookname, author, publisher, id));
                }else{
                    id = library.size();
                    library.add(new Book(bookname, author, publisher, id));
                }
                JOptionPane.showMessageDialog(null, "新增成功\n"+library.get(id), "新增書籍", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "此書已存在"+sameBooks, "新增書籍失敗", JOptionPane.ERROR_MESSAGE);
            }
        }
        adminMenu();
    }

    //取得所有庫存書籍
    public static String getLibrary() {
        String allBooks = "";
        for(Book book : library){
            if(book!=null){
                allBooks += book.toString();
            }
        }
        JOptionPane.showMessageDialog(null, allBooks, "所有館藏", JOptionPane.INFORMATION_MESSAGE);
        adminMenu();
        return allBooks;
    }
}