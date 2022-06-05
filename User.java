import javax.swing.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.time.LocalDate; 
import java.time.temporal.ChronoUnit; //計算時間差距的

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
    static int identity = -1; // -1: none, 0: student, 1: teacher, 2: staff, 3: admin

    public static void main (String [] args) {
    	welcome_gui();
    }
    
    //歡迎畫面
	public static void welcome_gui() {
		JFrame frame_welcome = new JFrame("央央圖書館"); //登入註冊的frame
		JPanel panel_welcome = new JPanel(); //登入註冊的panel
		JButton button_login = new JButton("登入"); // 登入的按鈕
		JButton button_register = new JButton("註冊"); // 註冊的按鈕


		//frame panel setup
		frame_welcome.setSize(230, 160);
		frame_welcome.setLocationRelativeTo(null);
		frame_welcome.setResizable(false);
		frame_welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_welcome.add(panel_welcome);
		
		panel_welcome.setLayout(null);
		
		frame_welcome.setVisible(true);
		
		//Container cp=frame_welcome.getContentPane();
	    //cp.setLayout(new GridLayout(2,0));
		
		//button_login setup
		button_login.setBounds(30,30,60,40);
		panel_welcome.add(button_login);
		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_welcome.dispose();
				login_gui();}
			});
		
		//button_register setup
		button_register.setBounds(120,30,60,40);
		panel_welcome.add(button_register);
		button_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_welcome.dispose();
				register_gui();
				}});
	}

    //註冊GUI
    public static void register_gui() {
		
		String[] identityarray = {"學生", "老師", "職員"};
		
		JFrame frame_userinfo = new JFrame(); // 註冊的frame
		JPanel panel_userinfo = new JPanel(); // 註冊的panel
		
		JLabel label_account; //"user"的文字標示
		JLabel label_password; //"password"的文字標示
		JLabel label_name; //"name"的文字標示
		
		JTextField text_account; // 輸入用戶帳號
		JPasswordField text_password; // 輸入用戶密碼
		JTextField text_name; // 輸入用戶名字
		
		JComboBox<String> combobox_identity = new JComboBox<>(identityarray); //選擇身分
		
		JButton button_register; //註冊按鈕
		JButton button_back; //返回按鈕
		
		frame_userinfo.setVisible(true);
		
		//frame panel setup
		frame_userinfo.setSize(320,225);
		frame_userinfo.setResizable(false);
		frame_userinfo.setLocationRelativeTo(null);
		frame_userinfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_userinfo.add(panel_userinfo);
		
		panel_userinfo.setLayout(null);
		
		//name part
		//label_name setup
		label_name = new JLabel("輸入姓名");
		label_name.setBounds(10,20,80,25);
		panel_userinfo.add(label_name);
		
		//text_name setup
		text_name = new JTextField(20); //20是長度限制
		text_name.setBounds(100, 20, 165, 25);
		panel_userinfo.add(text_name);
		
		//account part
		//label_acount setup
		label_account = new JLabel("註冊帳號");
		label_account.setBounds(10, 50, 80, 25);
		panel_userinfo.add(label_account);
		
		//text_account setup
		text_account = new JTextField(20);
		text_account.setBounds(100, 50, 165, 25);
		panel_userinfo.add(text_account);
		
		//password part
		//label_password setup
		label_password = new JLabel("設定密碼");
		label_password.setBounds(10, 80, 80, 25);
		panel_userinfo.add(label_password);
		
		//text_password setup
		text_password = new JPasswordField(20);
		text_password.setBounds(100, 80, 165, 25);
		panel_userinfo.add(text_password);
		
		//combobox_identity setup
		combobox_identity.setBounds(10, 110, 80, 25);
		panel_userinfo.add(combobox_identity);
		
		// button_login setup
		button_register = new JButton("註冊");
		button_register.setBounds(10, 140, 80, 25);
		panel_userinfo.add(button_register);
		button_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String option_input = combobox_identity.getItemAt(combobox_identity.getSelectedIndex());
				String account_input;
				String password_input;
				String name_input;
				if(text_account.getText().length()<1 || text_password.getText().length()<1 || text_name.getText().length()<1 ){
					account_input = null;
					password_input = null;
					name_input = null;
				}else{
					account_input = text_account.getText();
					password_input = text_password.getText();
					name_input = text_name.getText();
				}
				frame_userinfo.dispose();
				register(option_input, account_input, password_input, name_input);
			}});

		// button_back setup
		button_back = new JButton("返回");
		button_back.setBounds(110, 140, 80, 25);
		panel_userinfo.add(button_back);
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_userinfo.dispose();
				welcome_gui();
			}});
	}
    
    //註冊
    public static void register(String option_input, String account_input, String password_input, String name_input){
        String options = option_input;
        //int option = JOptionPane.showOptionDialog(null, "選擇身份", "註冊", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        String name = name_input;
        String account = account_input;
        String password = password_input;
        if(name == null || account == null || password == null){
            JOptionPane.showMessageDialog(null, "有資料為空", "註冊失敗", JOptionPane.ERROR_MESSAGE);
            register_gui();
        }else{
            switch(options){
                case "學生":
                    id = student.size();
                    student.add(new Student(name, account, password));
                    identity = 0;
                    break;
                case "老師":
                    id = teacher.size();
                    teacher.add(new Teacher(name, account, password));
                    identity = 1;
                    break;
                case "職員":
                    id = staff.size();
                    staff.add(new Staff(name, account, password));
                    identity = 2;
                    break;
                default :
                    welcome_gui();
                    break;
            }
			menu_gui();
        }
    }
    
    //登入GUI
	public static void login_gui() {
		String[] identityarray = {"學生", "老師", "職員", "管理員"};
	
		JFrame frame_userinfo = new JFrame(); // 登入的frame
		JPanel panel_userinfo = new JPanel(); // 登入的panel
	
		JLabel label_id; //"user"的文字標示
		JLabel label_password; //"password"的文字標示
	
		JTextField text_id; // 輸入用戶帳號
		JPasswordField text_password; // 輸入用戶密碼
	
		JComboBox<String> combobox_identity = new JComboBox<>(identityarray); //選擇身分
	
		JButton button_login; //登入按鈕
		JButton button_back; //返回按鈕
	
		JLabel option_text;//讀入身分
		JLabel account_text;//讀入帳號
	
		frame_userinfo.setVisible(true);
	
		//option_text text
		option_text = new JLabel("");
		option_text.setBounds(30, 110, 300, 25);
		panel_userinfo.add(option_text);
	
		//account_text text
		account_text = new JLabel("");
		account_text.setBounds(30, 110, 300, 25);
		panel_userinfo.add(account_text);
	
		//frame panel setup
		frame_userinfo.setSize(320,200);
		frame_userinfo.setLocationRelativeTo(null);
		frame_userinfo.setResizable(false);
		frame_userinfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_userinfo.add(panel_userinfo);
	
		panel_userinfo.setLayout(null);
	
		//id part
		//label_id setup
		label_id = new JLabel("帳號");
		label_id.setBounds(10,20,80,25);
		panel_userinfo.add(label_id);
		
		//text_id setup
		text_id = new JTextField(20); //20是長度限制
		text_id.setBounds(100, 20, 165, 25);
		panel_userinfo.add(text_id);
	
		//password part
		//label_password setup
		label_password = new JLabel("密碼");
		label_password.setBounds(10, 50, 80, 25);
		panel_userinfo.add(label_password);
	
		//text_password setup
		text_password = new JPasswordField(20);
		text_password.setBounds(100, 50, 165, 25);
		panel_userinfo.add(text_password);
	
		//combobox_identity setup
		combobox_identity.setBounds(10, 80, 80, 25);
		panel_userinfo.add(combobox_identity);
	
		// button_login setup
		button_login = new JButton("log in");
		button_login.setBounds(10, 110, 80, 25);
		panel_userinfo.add(button_login);
		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String option = combobox_identity.getItemAt(combobox_identity.getSelectedIndex());;
				String account;
				String password;
				if( text_id.getText().length()<1 || text_password.getText().length()<1){
					account = null;
					password = null;
				}else{
					account = text_id.getText();
					password = text_password.getText();
				}
				login(option, account, password);
				frame_userinfo.dispose();
			}});

		// button_back setup
		button_back = new JButton("返回");
		button_back.setBounds(110, 110, 80, 25);
		panel_userinfo.add(button_back);
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_userinfo.dispose();
				welcome_gui();
			}});
	}
    
    //登入
    public static void login(String option_input, String account_input, String password_input){
       
    	boolean exist = false;
        //String options [] = {"學生", "老師", "職員", "管理員"};
        String option = option_input;// = JOptionPane.showOptionDialog(null, "選擇身份", "登入", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        String account= account_input;
        String password = password_input;
		if( account == null || password == null){
            JOptionPane.showMessageDialog(null, "有資料為空", "登入失敗", JOptionPane.ERROR_MESSAGE);
            login_gui();
        }else{
			switch(option){
				case "學生":
					//account = JOptionPane.showInputDialog("帳號:");
					for(int i=0; i<student.size(); i++){
						if(student.get(i).getAccount().equals(account)){
							exist = true;
							id = i;
							identity = 0;
							break;
						}
					}
					break;
				case "老師":
					//account = JOptionPane.showInputDialog("帳號:");
					for(int i=0; i<teacher.size(); i++){
						if(teacher.get(i).getAccount().equals(account)){
							exist = true;
							id = i;
							identity = 1;
							break;
						}
					}
					break;
				case "職員":
					//account = JOptionPane.showInputDialog("帳號:");
					for(int i=0; i<staff.size(); i++){
						if(staff.get(i).getAccount().equals(account)){
							exist = true;
							id = i;
							identity = 2;
							break;
						}
					}
					break;
				case "管理員":
					//account = JOptionPane.showInputDialog("帳號:");
					if(admin.getAccount().equals(account)){
						exist = true;
						identity = 3;
					}
					break;
				default:
					welcome_gui();
					break;
			}
			if(exist==true){
				  if(password.equals(member().getPassword())){
					if(member()==admin){
						adminMenu_gui();
					}else{
						menu_gui();
					}
				}else{
					JOptionPane.showMessageDialog(null, "密碼錯誤", "登入失敗", JOptionPane.ERROR_MESSAGE);
					login_gui();
				}
			}else{
				JOptionPane.showMessageDialog(null, "此帳號不存在,前往註冊畫面", "登入失敗", JOptionPane.ERROR_MESSAGE);
				register_gui();
			}

		}
        
    }

    //提取使用者身份
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
    
    //主畫面
  	public static void menu_gui() {
  		JFrame frame_menu = new JFrame(); // 主畫面的frame
  		JPanel panel_menu = new JPanel(); // 主畫面的panel
  		
  		JButton button_borrow;
  		JButton button_return;
  		JButton button_searchbook;
  		JButton button_history;
  		JButton button_exit;
  		
  		JLabel test;//測試
  		
  		frame_menu.setVisible(true);
  		
  		//test text
  		test = new JLabel("");
  		test.setBounds(30, 200, 300, 25);
  		panel_menu.add(test);
  		
  		//frame panel setup
  		frame_menu.setSize(420, 350);
  		frame_menu.setLocationRelativeTo(null);
  		frame_menu.setResizable(false);
  		frame_menu.setDefaultCloseOperation(JFrame. DO_NOTHING_ON_CLOSE);
  		frame_menu.add(panel_menu);
  		
  		panel_menu.setLayout(null);
  		
  		// 借書button_borrow setup
  		button_borrow = new JButton("借書");
  		button_borrow.setBounds(30, 30, 150, 60);
  		panel_menu.add(button_borrow);
  		button_borrow.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent actionEvent) {
  				//message(1,"borrow()"+identityword);
  				frame_menu.dispose();
  				borrowBook();
  			}});
  		
  		// 還書button_return setup
  		button_return = new JButton("還書");
  		button_return.setBounds(210, 30, 150, 60);
  		panel_menu.add(button_return);
  		button_return.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent actionEvent) {
  				//message(1,"return()"+identityword);
  				frame_menu.dispose();
  				returnBook();
  			}});
  		
  		// 查詢書籍button_searchbook setup
  		button_searchbook = new JButton("查詢書籍");
  		button_searchbook.setBounds(30, 120, 150, 60);
  		panel_menu.add(button_searchbook);
  		button_searchbook.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent actionEvent) {
  				//message(1,"searchbook()"+identityword);
  				frame_menu.dispose();
  				searchBook();
  				menu_gui();
  			}});
  		
  		// 查詢借閱紀錄button_history setup
  		button_history = new JButton("查詢借閱紀錄");
  		button_history.setBounds(210, 120, 150, 60);
  		panel_menu.add(button_history);
  		button_history.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent actionEvent) {
  				//message(1,"history()"+identityword);
  				frame_menu.dispose();
  				history();
  			}});
  		
  		// 離開button_exit setup
  		button_exit = new JButton("離開");
  		button_exit.setBounds(120, 210, 150, 60);
  		panel_menu.add(button_exit);
  		button_exit.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent actionEvent) {
  				frame_menu.dispose();
  				welcome_gui();
  			}});
  	}

    //借書
	public static void borrowBook() {
		String option[]={"繼續借書","回總畫面"};
        int decision = 0;
        
		do{
            String date =  String.format("%d-%02d-%02d", LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
			String sameBooks = "";
			String bookname = JOptionPane.showInputDialog(null, "輸入書名關鍵字", "查詢書籍", JOptionPane.QUESTION_MESSAGE);

            if(bookname==null){
				break;
			}else if(bookname.length()<1){
				JOptionPane.showMessageDialog(null, "有資料為空", "查詢失敗", JOptionPane.ERROR_MESSAGE);
                break;
            }else{
				for(int i=0; i<library.size(); i++){
					if(library.get(i) != null){
						String find = library.get(i).getBookname();
						if(find.contains(bookname)){
							sameBooks += library.get(i);
						}
					}
				}
			}
            if( sameBooks == ""){
				JOptionPane.showMessageDialog(null, "查無此書", "查詢結果", JOptionPane.ERROR_MESSAGE);
				decision = 1;
			}else{
				String borrowID = JOptionPane.showInputDialog(null, "搜尋結果如下:\n"+sameBooks+"\n輸入要借閱的書的ID:", "借閱書籍", JOptionPane.QUESTION_MESSAGE);
				if(borrowID==null){
					break;
				}else if(borrowID.length()<1){
					JOptionPane.showMessageDialog(null, "有資料為空", "借書失敗", JOptionPane.ERROR_MESSAGE);
					break;
				}else{
					int i = Integer.parseInt(borrowID);
					if(member().borrowed.size() == member().borrowLimit()){
						String show = String.format("你已借%d本書,不能再借了", member().borrowLimit());
						JOptionPane.showMessageDialog(null, show);
						decision = 1;
					}
					else if(library.get(i).getStatus()==true){ //沒有被借走
						library.get(i).setStatus(false);
						member().borrowed.add(library.get(i)); //加到借閱書列中
	
						library.get(i).setdate1(JOptionPane.showInputDialog(null, "借閱日期?\n(請照yyyy-mm-dd格式輸入)", date));
	
						String show = String.format("書名: %s借書成功,借書期限:%s天", library.get(i).getBookname(), member().borrowDay());
	
						decision = JOptionPane.showOptionDialog(null, show,"訊息",1,1,null,option,option[0]);
					}
					else{  //被借走了
						decision = JOptionPane.showOptionDialog(null, "書已被借走","訊息",1,1,null,option,option[0]);
					}
				}
				
			}

		}while (decision == 0 && member().borrowed.size()<member().borrowLimit());
		menu_gui();
	}

	//還書
	public static void returnBook() {
        int i = 0;
		String record = "";
		String [] text = new String[10];
        String date =  String.format("%d-%02d-%02d", LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
		for(i = 0 ; i< member().borrowed.size();i++){
			if(text[i]==null){
				text[i]= member().borrowed.get(i).getBookname();}
        }
        String [] options = new String [i];
        if(text[0]==null){
            JOptionPane.showMessageDialog(null, "你沒有正在借的書喔", "訊息", JOptionPane.ERROR_MESSAGE);
            menu_gui();
        }
        for(int j = 0; j<options.length; j++){
            options[j] = text[j];
        }
		int choice = JOptionPane.showOptionDialog(null,"選擇要還的書：","訊息",1,1,null,options,options[0]);

		member().borrowed.get(choice).setdate2(JOptionPane.showInputDialog(null, "還書日期?\n(請照yyyy-mm-dd格式輸入)", date));

        record += String.format("書名: %s\t作者: %s\t出版社: %s\t借閱日期: %s\t還書日期: %s\t", member().borrowed.get(choice).getBookname(), member().borrowed.get(choice).getAuthor(), member().borrowed.get(choice).getPublisher(), member().borrowed.get(choice).getdate1(), member().borrowed.get(choice).getdate2());
		long fee = 0;

		LocalDate date1 = LocalDate.parse(member().borrowed.get(choice).getdate1());
		LocalDate date2 = LocalDate.parse(member().borrowed.get(choice).getdate2());

		long ok = ChronoUnit.DAYS.between(date1, date2);

		if (ok>member().borrowDay()){
			fee = (ok-member().borrowDay())*10; //過期一天10塊錢
		}

		if(fee==0){
			record +="\n";
		}else{
            record += String.format("過期: %d天\t罰款: %d元\n", ok, fee);
		}

		member().borrowed.get(choice).setStatus(true);
        member().history.add(member().borrowed.get(choice)); //加入紀錄
		member().borrowed.remove(choice);
        member().newRecord(record); 
        
		JOptionPane.showMessageDialog(null,"還書成功"+ ((fee==0)?"":"過期 : " + ok + "天 罰款 : " + fee + " 元"));
		menu_gui();
	}

    //借書紀錄
	public static void history() {
        String borrowing="";

        for(Book borrow : member().borrowed){
            String show = String.format("書名: %s\t作者: %s\t出版社: %s\t借閱日期: %s\n", borrow.getBookname(), borrow.getAuthor(), borrow.getPublisher(), borrow.getdate1());
            borrowing += show;
        }
        if(member().getRecord()==""&&borrowing==""){
            JOptionPane.showMessageDialog(null, "無借閱紀錄", "訊息", JOptionPane.ERROR_MESSAGE);
        }else if(borrowing==""){
            JOptionPane.showMessageDialog(null,member().getRecord());
        }else{
            JOptionPane.showMessageDialog(null,member().getRecord()+"借閱中/未歸還書籍:\n"+borrowing);

        }
		menu_gui();
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
        String publisher = JOptionPane.showInputDialog(null, "輸入出版社關鍵字", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(publisher == null){
            searchBook();
            return "";
        }else if(publisher.length()<1){
			JOptionPane.showMessageDialog(null, "輸入關鍵字為空", "查詢失敗", JOptionPane.ERROR_MESSAGE);
			return "";
		}else{
            for(Book book : library){
                if(book != null){
					String find = book.getPublisher();
					if(find.contains(publisher)){
						sameBooks += book;
					}
                }
            }
            JOptionPane.showMessageDialog(null, sameBooks==""? "查無此出版社" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            return sameBooks;
        } 
    }

    //搜尋作者
    public static String searchbyAuthor() {
        String sameBooks = "";
        String author = JOptionPane.showInputDialog(null, "輸入作者關鍵字", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(author == null){
            searchBook();
            return "";
        }else if(author.length()<1){
			JOptionPane.showMessageDialog(null, "輸入關鍵字為空", "查詢失敗", JOptionPane.ERROR_MESSAGE);
			return "";
		}else{
            for(Book book : library){
                if(book != null){
					String find = book.getAuthor();
					if(find.contains(author)){
						sameBooks += book;
					}
                }
            }
            JOptionPane.showMessageDialog(null, sameBooks==""? "查無此作者" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            return sameBooks;
        }
    }

    //搜尋書名
    public static String searchbyName() {
        String sameBooks = "";
        String bookname = JOptionPane.showInputDialog(null, "輸入書名關鍵字", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookname == null){
            searchBook();
            return "";
        }else if(bookname.length()<1){
			JOptionPane.showMessageDialog(null, "輸入關鍵字為空", "查詢失敗", JOptionPane.ERROR_MESSAGE);
			return "";
		}
		else{
            for(Book book : library){
                if(book != null){
					String find = book.getBookname();
					if(find.contains(bookname)){
						sameBooks += book;
					}
                }
            }
            JOptionPane.showMessageDialog(null, sameBooks==""? "查無此書" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            return sameBooks;
        }
    }
    
    //管理員主畫面
    public static void adminMenu_gui() {
		JFrame frame_adminmenu = new JFrame(); 
		JPanel panel_adminmenu = new JPanel();
		
		JButton button_addbook;
		JButton button_deletbook;
		JButton button_editbook;
		JButton button_searchbook;
		JButton button_getLibrary;
		JButton button_exit;

		//frame panel setup
		frame_adminmenu.setSize(420, 350);
		frame_adminmenu.setLocationRelativeTo(null);
		frame_adminmenu.setResizable(false);
		frame_adminmenu.setDefaultCloseOperation(JFrame. DO_NOTHING_ON_CLOSE);
		frame_adminmenu.add(panel_adminmenu);
		
		panel_adminmenu.setLayout(null);
		
		frame_adminmenu.setVisible(true);
		
		// 新增書籍button_addbook setup
		button_addbook = new JButton("新增書籍");
		button_addbook.setBounds(30, 30, 150, 60);
		panel_adminmenu.add(button_addbook);
		button_addbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_adminmenu.dispose();
				addBook_gui();
			}});
		
		// 刪除書籍button_deletbook setup
		button_deletbook = new JButton("刪除書籍");
		button_deletbook.setBounds(210, 30, 150, 60);
		panel_adminmenu.add(button_deletbook);
		button_deletbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_adminmenu.dispose();
				deleteBook();
			}});

		// 修改書籍資料button_editbook setup
		button_editbook = new JButton("修改書籍資料");
		button_editbook.setBounds(30, 120, 150, 60);
		panel_adminmenu.add(button_editbook);
		button_editbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_adminmenu.dispose();
				editBook();
			}});
		
		// 查詢書籍button_searchbook setup
		button_searchbook = new JButton("查詢書籍");
		button_searchbook.setBounds(210, 120, 150, 60);
		panel_adminmenu.add(button_searchbook);
		button_searchbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_adminmenu.dispose();
				searchBook();
  				adminMenu_gui();
			}});
		
		// 顯示所有書籍button_getLibrary setup
		button_getLibrary = new JButton("顯示所有書籍");
		button_getLibrary.setBounds(30, 210, 150, 60);
		panel_adminmenu.add(button_getLibrary);
		button_getLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_adminmenu.dispose();
				getLibrary();
			}});
		
		// 離開button_exit setup
		button_exit = new JButton("離開主選單");
		button_exit.setBounds(210, 210, 150, 60);
		panel_adminmenu.add(button_exit);
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_adminmenu.dispose();
				frame_adminmenu.dispose();
				welcome_gui();
			}});
	}

    //刪除書籍
    public static void deleteBook() {
        String sameBooks = "";
        String bookname = JOptionPane.showInputDialog(null, "輸入書名關鍵字", "刪除書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookname == null){ //按 x or cancel
			//不做任何事,回到adminmenu
        }else if(bookname.length()<1){
			JOptionPane.showMessageDialog(null, "輸入關鍵字為空", "刪除失敗", JOptionPane.ERROR_MESSAGE);
		}else{
            for(Book book : library){
                if(book != null){
					String find = book.getBookname();
					if(find.contains(bookname)){
						sameBooks += book;
					}
                }
            }
            if(sameBooks==""){
                JOptionPane.showMessageDialog(null, "查無此書", "刪除書籍",JOptionPane.ERROR_MESSAGE);
            }else{
				String deleteID = JOptionPane.showInputDialog(null, "搜尋結果如下:\n"+sameBooks+"\n輸入要刪除的書的ID:", "刪除書籍", JOptionPane.QUESTION_MESSAGE);
				if(deleteID==null){

				}else if(deleteID.length()<1){
					JOptionPane.showMessageDialog(null, "輸入ID為空", "刪除失敗", JOptionPane.ERROR_MESSAGE);
				}else{
					int index = Integer.parseInt(deleteID);
					int option = JOptionPane.showConfirmDialog(null, "確定要刪除以下書籍嗎?\n"+library.get(index));
					if(option == 0){
						library.set(index, null);
						JOptionPane.showMessageDialog(null, "刪除成功");
					}
				}
            }
        }
        adminMenu_gui();
    }

    //修改書籍資訊
    public static void editBook() {
        String sameBooks = "";
        String bookname = JOptionPane.showInputDialog(null, "輸入書名關鍵字", "修改書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookname == null){

        }else if(bookname.length()<1){
			JOptionPane.showMessageDialog(null, "輸入關鍵字為空", "修改失敗", JOptionPane.ERROR_MESSAGE);
		}else{
            for(Book book : library){
                if(book != null){
					String find = book.getBookname();
					if(find.contains(bookname)){
						sameBooks += book;
					}
                }
            }
            if(sameBooks==""){
                JOptionPane.showMessageDialog(null, "查無此書", "修改書籍",JOptionPane.ERROR_MESSAGE);
            }else{
				String editID = JOptionPane.showInputDialog(null, "搜尋結果如下:\n"+sameBooks+"\n輸入要修改的書的ID:", "修改書籍", JOptionPane.QUESTION_MESSAGE);
				if(editID==null){

				}else if(editID.length()<1){
					JOptionPane.showMessageDialog(null, "輸入ID為空", "修改失敗", JOptionPane.ERROR_MESSAGE);
				}else{
					int index = Integer.parseInt(editID);
					bookname = library.get(index).getBookname();
					String author = library.get(index).getAuthor();
					String publisher = library.get(index).getPublisher();
					int option = JOptionPane.showConfirmDialog( null, "確定要修改以下書籍嗎?\n"+library.get(index), "修改書籍", JOptionPane.YES_NO_OPTION);
					if(option == 0){
						String [] searchOptions = {"書名", "作者", "出版商"};
						int searchOption = JOptionPane.showOptionDialog(null, "選擇修改", "修改書籍", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, searchOptions, searchOptions[0]);
						String editBook;
						switch(searchOption){
							case 0:
								editBook = JOptionPane.showInputDialog(null, "原書名: "+library.get(index).getBookname()+"\n修改為: ");
								if(editBook==null){

								}else if(editBook.length()<1){
									JOptionPane.showMessageDialog(null, "輸入為空", "修改失敗", JOptionPane.ERROR_MESSAGE);
								}else{
									bookname = editBook;
									JOptionPane.showMessageDialog(null, "修改成功");
								}
								break;
							case 1:
								editBook = JOptionPane.showInputDialog(null, "原作者: "+library.get(index).getAuthor()+"\n修改為: ");
								if(editBook==null){

								}else if(editBook.length()<1){
									JOptionPane.showMessageDialog(null, "輸入為空", "修改失敗", JOptionPane.ERROR_MESSAGE);
								}else{
									author = editBook;
									JOptionPane.showMessageDialog(null, "修改成功");
								}
								break;
							case 2:
								editBook = JOptionPane.showInputDialog(null, "原出版社: "+library.get(index).getPublisher()+"\n修改為: ");
								if(editBook==null){

								}else if(editBook.length()<1){
									JOptionPane.showMessageDialog(null, "輸入為空", "修改失敗", JOptionPane.ERROR_MESSAGE);
								}else{
									publisher = editBook;
									JOptionPane.showMessageDialog(null, "修改成功");
								}
								break;
						}
						library.set(index, new Book(bookname, author, publisher, index));
					}
				}

            }
        }
        adminMenu_gui();
    }

    //新增書籍
    public static void addBook(String bookname, String author, String publisher) {
        String sameBooks = "";
        int index = 0;
		if(bookname == null || author == null || publisher == null){
            JOptionPane.showMessageDialog(null, "有資料為空", "新增書籍失敗", JOptionPane.ERROR_MESSAGE);
        }else{
            for( int i=0; i<library.size(); i++){
                if( library.get(i) != null && bookname.equals( library.get(i).getBookname() ) ){
                    sameBooks += library.get(i).getBookname();
                }
            }
            if(sameBooks == ""){
                index = library.size();
                library.add(new Book(bookname, author, publisher, index));
                JOptionPane.showMessageDialog(null, "新增成功\n"+library.get(index), "新增書籍", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "此書已存在"+sameBooks, "新增書籍失敗", JOptionPane.ERROR_MESSAGE);
            }
        }
        adminMenu_gui();
    }

	//新增書籍GUI
	public static void addBook_gui(){
	
		JFrame frame_bookinfo = new JFrame(); // 註冊的frame
		JPanel panel_bookinfo = new JPanel(); // 註冊的panel
		
		JLabel label_bookname; //"書名"的文字標示
		JLabel label_author; //"作者"的文字標示
		JLabel label_publisher; //"出版社"的文字標示
		
		JTextField text_bookname; // 輸入用戶帳號
		JTextField text_author; // 輸入用戶密碼
		JTextField text_publisher; // 輸入用戶名字
				
		JButton button_addBook; //註冊按鈕
		JButton button_back; //返回按鈕
		
		frame_bookinfo.setVisible(true);
		
		//frame panel setup
		frame_bookinfo.setSize(320,225);
		frame_bookinfo.setResizable(false);
		frame_bookinfo.setLocationRelativeTo(null);
		frame_bookinfo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame_bookinfo.add(panel_bookinfo);
		
		panel_bookinfo.setLayout(null);
		
		//bookname part
		//label_bookname setup
		label_bookname = new JLabel("書名");
		label_bookname.setBounds(10, 20, 80, 25);
		panel_bookinfo.add(label_bookname);
		
		//text_bookname setup
		text_bookname = new JTextField(20);
		text_bookname.setBounds(100, 20, 165, 25);
		panel_bookinfo.add(text_bookname);
		
		//author part
		//label_author setup
		label_author = new JLabel("作者");
		label_author.setBounds(10, 50, 80, 25);
		panel_bookinfo.add(label_author);
		
		//text_author setup
		text_author = new JPasswordField(20);
		text_author.setBounds(100, 50, 165, 25);
		panel_bookinfo.add(text_author);

		//publisher part
		//label_publisher setup
		label_publisher = new JLabel("出版社");
		label_publisher.setBounds(10,80,80,25);
		panel_bookinfo.add(label_publisher);
		
		//text_publisher setup
		text_publisher = new JTextField(20); //20是長度限制
		text_publisher.setBounds(100, 80, 165, 25);
		panel_bookinfo.add(text_publisher);
		
		// button_login setup
		button_addBook = new JButton("新增書籍");
		button_addBook.setBounds(10, 140, 80, 25);
		panel_bookinfo.add(button_addBook);
		button_addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String bookname_input;
				String author_input;
				String publisher_input;
				if(text_bookname.getText().length()<1 || text_author.getText().length()<1 || text_publisher.getText().length()<1 ){
					bookname_input = null;
					author_input = null;
					publisher_input = null;
				}else{
					bookname_input = text_bookname.getText();
					author_input = text_author.getText();
					publisher_input = text_publisher.getText();
				}
				frame_bookinfo.dispose();
				addBook(bookname_input, author_input, publisher_input);
			}});

		// button_back setup
		button_back = new JButton("返回");
		button_back.setBounds(110, 140, 80, 25);
		panel_bookinfo.add(button_back);
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_bookinfo.dispose();
				adminMenu_gui();
			}});
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
        adminMenu_gui();
        return allBooks;
    }
}