import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;

import java.util.*;
import java.time.LocalDate; 
import java.time.temporal.ChronoUnit; //計算時間差距的

public class User {

    static ArrayList<Student> student = new ArrayList<>(){{
        add(0, new Student("testname", "test", "123"));
    }};
    static ArrayList<Teacher> teacher = new ArrayList<>();
    static ArrayList<Staff> staff = new ArrayList<>();
    static Admin admin = new Admin("管理員","admin","admin");

    static ArrayList<Book> library = new ArrayList<Book>(){{
        add(0, new Book("Harry Potter", "J.K.Rowling", "Bloomsbury", "科幻", 0));
        add(1, new Book("Percy Jackson", "Rick Riordan", "Disney", "科幻",1));
		add(2, new Book("名偵探柯南", "青山剛昌", "小學館","懸疑",2));
        add(3, new Book("C++", "c++", "c++","電腦",3));
		add(4, new Book("Java", "java", "java","電腦",4));
		add(5, new Book("C", "c", "c","電腦",5));
		add(6, new Book("沙丘", "Franklin Herbert", "大家出版","科幻",6));
        add(7, new Book("哈利波特", "J.K.Rowling", "皇冠文化", "科幻", 7));
		add(8, new Book("金田一少年之事件簿", "金成陽三郎", "東立出版社","懸疑",8));
		add(9, new Book("Java How to program", "Paul DEITEL", "Pearson","電腦",9));


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
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
		

	    ImageIcon icon=new ImageIcon("frame_welcome_icon.png");
	    ImageIcon button_login_icon=new ImageIcon("button_login.png");
	    ImageIcon button_register_icon=new ImageIcon("button_register.png");
	    
		JButton button_login = new JButton(button_login_icon); // 登入的按鈕
		JButton button_register = new JButton(button_register_icon); // 註冊的按鈕


		//frame panel setup
		frame_welcome.setSize(600, 440);
		frame_welcome.setLocationRelativeTo(null);
		frame_welcome.setResizable(false);
		frame_welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_welcome.add(panel_welcome);
		
		panel_welcome.setLayout(null);
		panel_welcome.setOpaque(false);
		//button_login setup
		panel_welcome.add(button_login);
		button_login.setBounds(396,103,154,88);

		//button_register setup
		panel_welcome.add(button_register);
		button_register.setBounds(396,209,154,88);

		//background setup
		JLabel background = new JLabel(icon);
		panel_welcome.add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		
		frame_welcome.setVisible(true);

		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_welcome.dispose();
				login_gui();}
			});
		

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
		
		JTextField text_account; // 輸入用戶帳號
		JPasswordField text_password; // 輸入用戶密碼
		JTextField text_name; // 輸入用戶名字
		
		JComboBox<String> combobox_identity = new JComboBox<>(identityarray); //選擇身分
		
		JButton button_register; //註冊按鈕
		JButton button_back; //返回按鈕
		
		ImageIcon button_confirm_icon=new ImageIcon("button_confirm.png");
		ImageIcon button_back_icon=new ImageIcon("button_back.png");
			
		
		//frame panel setup
		frame_userinfo.setSize(600,440);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);	
		ImageIcon icon=new ImageIcon("frame_register_icon.png");


		frame_userinfo.setResizable(false);
		frame_userinfo.setLocationRelativeTo(null);
		frame_userinfo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame_userinfo.add(panel_userinfo);
		
		panel_userinfo.setLayout(null);
		
		//text_name setup
		text_name = new JTextField(20); //20是長度限制
		text_name.setBounds(228, 150, 191, 25);
		panel_userinfo.add(text_name);
		
		//text_account setup
		text_account = new JTextField(20);
		text_account.setBounds(228, 191, 191, 25);
		panel_userinfo.add(text_account);
		
		//text_password setup
		text_password = new JPasswordField(20);
		text_password.setBounds(228, 232, 191, 25);
		panel_userinfo.add(text_password);
		
		//combobox_identity setup
		combobox_identity.setBounds(228, 274, 80, 25);
		panel_userinfo.add(combobox_identity);
		
		// button_login setup
		button_register = new JButton(button_confirm_icon);
		button_register.setBounds(264, 328, 70, 27);
		panel_userinfo.add(button_register);
		
		// button_back setup
		button_back = new JButton(button_back_icon);
		button_back.setBounds(17, 13, 84, 34);
		panel_userinfo.add(button_back);

		//background setup
		JLabel background = new JLabel(icon);
		panel_userinfo.add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

		frame_userinfo.setVisible(true);


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
				register(option_input, account_input, password_input, name_input);
				frame_userinfo.dispose();
			}});

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
                    identity = student.get(id).getIdentity();
                    break;
                case "老師":
                    id = teacher.size();
                    teacher.add(new Teacher(name, account, password));
                    identity = teacher.get(id).getIdentity();
                    break;
                case "職員":
                    id = staff.size();
                    staff.add(new Staff(name, account, password));
                    identity = staff.get(id).getIdentity();
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
	
		JTextField text_id; // 輸入用戶帳號
		JPasswordField text_password; // 輸入用戶密碼
	
		JComboBox<String> combobox_identity = new JComboBox<>(identityarray); //選擇身分
	
		JButton button_login; //登入按鈕
		JButton button_back; //返回按鈕
		
		ImageIcon button_confirm_icon=new ImageIcon("button_confirm.png");
		ImageIcon button_back_icon=new ImageIcon("button_back.png");
	
		//frame panel setup
		frame_userinfo.setSize(600,440);
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);	
	    ImageIcon icon=new ImageIcon("frame_login_icon.png");

		frame_userinfo.setLocationRelativeTo(null);
		frame_userinfo.setResizable(false);
		frame_userinfo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame_userinfo.add(panel_userinfo);
	
		panel_userinfo.setLayout(null);
		
		//text_id setup
		text_id = new JTextField(20); //20是長度限制
		text_id.setBounds(242,156, 191, 25);
		panel_userinfo.add(text_id);
	
		//text_password setup
		text_password = new JPasswordField(20);
		text_password.setBounds(242, 197, 191, 25);
		panel_userinfo.add(text_password);
	
		//combobox_identity setup
		combobox_identity.setBounds(242, 239, 100, 25);
		panel_userinfo.add(combobox_identity);
	
		// button_login setup
		button_login = new JButton(button_confirm_icon);
		button_login.setBounds(262, 304, 77, 35);
		panel_userinfo.add(button_login);

		//button_back setup
		button_back = new JButton(button_back_icon);
		button_back.setBounds(17, 13, 84, 34);
		panel_userinfo.add(button_back);

		//background setup
		JLabel background = new JLabel(icon);
		panel_userinfo.add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

		frame_userinfo.setVisible(true);

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
			}
		});

		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame_userinfo.dispose();
				welcome_gui();
			}
		});
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
							identity = student.get(id).getIdentity();
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
							identity = teacher.get(id).getIdentity();
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
							identity = staff.get(id).getIdentity();
							break;
						}
					}
					break;
				case "管理員":
					//account = JOptionPane.showInputDialog("帳號:");
					if(admin.getAccount().equals(account)){
						exist = true;
						identity = admin.getIdentity();
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
				}
			}else{
				JOptionPane.showMessageDialog(null, "帳號或密碼錯誤", "登入失敗", JOptionPane.ERROR_MESSAGE);
				login_gui();
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
		
		JLabel label_username;
		label_username = new JLabel();// label_username.setText(String); 更改顯示
		if(member().getRecord()==""){
			label_username.setBounds(104,103,190,25);
			label_username.setText("Hi~ "+member().getName());
			panel_menu.add(label_username);
		}else{
			label_username.setBounds(96, 52, 120, 25); //顯示用戶名(借過書位置)
			label_username.setText("Hi~ "+member().getName());
			panel_menu.add(label_username);
			
			JLabel label_favtype;
			label_favtype = new JLabel();
			label_favtype.setText("你最常借的類別: "+member().favoriteStyle());
			label_favtype.setBounds(34, 86, 300, 25); 
			panel_menu.add(label_favtype);
			
			JLabel label_recommend;
			label_recommend = new JLabel("你可能會喜歡: "+ recommend(member().favoriteStyle()));

			label_recommend.setBounds(34, 119, 300, 25); 
			panel_menu.add(label_recommend);
		}


		ImageIcon button_exit_icon=new ImageIcon("button_exit.png");
		ImageIcon button_borrow_icon=new ImageIcon("button_borrow.png");
		ImageIcon button_return_icon=new ImageIcon("button_return.png");
		ImageIcon button_searchbook_icon=new ImageIcon("button_searchbook.png");
		ImageIcon button_history_icon=new ImageIcon("button_history.png");
  		
		//frame panel setup
		frame_menu.setSize(600, 440);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);	
		ImageIcon icon=new ImageIcon("frame_menu_icon.png");

		frame_menu.setLocationRelativeTo(null);
		frame_menu.setResizable(false);
		frame_menu.setDefaultCloseOperation(JFrame. DO_NOTHING_ON_CLOSE);
		frame_menu.add(panel_menu);
		panel_menu.setLayout(null);
		
		// 借書button_borrow setup
		button_borrow = new JButton(button_borrow_icon);
		button_borrow.setBounds(360, 30, 214, 79);
		panel_menu.add(button_borrow);

		// 還書button_return setup
		button_return = new JButton(button_return_icon);
		button_return.setBounds(360, 120, 214, 79);
		panel_menu.add(button_return);

		// 查詢書籍button_searchbook setup
		button_searchbook = new JButton(button_searchbook_icon);
		button_searchbook.setBounds(360, 210, 214, 79);
		panel_menu.add(button_searchbook);

		// 查詢借閱紀錄button_history setup
		button_history = new JButton(button_history_icon);
		button_history.setBounds(360, 300, 214, 79);
		panel_menu.add(button_history);
		
		// 離開button_exit setup
		button_exit = new JButton(button_exit_icon);
		button_exit.setBounds(17, 12, 84, 34);
		panel_menu.add(button_exit);

		//background setup
		JLabel background = new JLabel(icon);
		panel_menu.add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

		frame_menu.setVisible(true);


		button_borrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				borrowBook();
				frame_menu.dispose();
			}});

		button_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				returnBook();
				frame_menu.dispose();
			}});

		button_searchbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				searchBook();
				menu_gui();
				frame_menu.dispose();
			}});

		button_history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				history();
				frame_menu.dispose();

			}});

		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				welcome_gui();
				frame_menu.dispose();
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
				}else if(!borrowID.chars().allMatch( Character::isDigit)){
					JOptionPane.showMessageDialog(null, "請輸入數字", "借書失敗", JOptionPane.ERROR_MESSAGE);
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
	
						String show = String.format("書名: %s\n借書成功,借書期限:%s天", library.get(i).getBookname(), member().borrowDay());
	
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

        record += String.format("書名: %s\t作者: %s\t出版社: %s\t分類: %s\t借閱日期: %s\t還書日期: %s\t", member().borrowed.get(choice).getBookname(), member().borrowed.get(choice).getAuthor(), member().borrowed.get(choice).getPublisher(), member().borrowed.get(choice).getStyle(),member().borrowed.get(choice).getdate1(), member().borrowed.get(choice).getdate2());
		long fee = 0;

		LocalDate date1 = LocalDate.parse(member().borrowed.get(choice).getdate1());
		LocalDate date2 = LocalDate.parse(member().borrowed.get(choice).getdate2());

		long ok = ChronoUnit.DAYS.between(date1, date2);

		if (ok>member().borrowDay()){
			fee = (ok-member().borrowDay())*10; //過期一天10塊錢
			ok = ok-member().borrowDay();
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
            String show = String.format("書名: %s\t作者: %s\t出版社: %s\t分類: %s\t借閱日期: %s\n", borrow.getBookname(), borrow.getAuthor(), borrow.getPublisher(), borrow.getStyle(), borrow.getdate1());
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

	//個人化推薦
	public static String recommend(String favoriteStyle){
		Random r = new Random();
		ArrayList<Book> style = new ArrayList<>();
		for(int i=0; i<library.size(); i++){
			if(library.get(i)!=null && library.get(i).getStyle().equals(favoriteStyle)){
				style.add(library.get(i));
			}
		}
		int index = r.nextInt(style.size());
		return style.get(index).getBookname();
	}

    //查詢書籍
    public static void searchBook() {
        //String sameBooks = "";
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
        //return sameBooks;
       
    }

    //搜尋出版社
    public static void searchbyPublisher() {
        //String sameBooks = "";
		Object[] title={"書名","作者","出版社","類別","狀態"};
		Object[][] str = new Object[library.size()][5];
		int j = 0;
        String publisher = JOptionPane.showInputDialog(null, "輸入出版社關鍵字", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(publisher == null){
            searchBook();
            //return "";
        }else if(publisher.length()<1){
			JOptionPane.showMessageDialog(null, "輸入關鍵字為空", "查詢失敗", JOptionPane.ERROR_MESSAGE);
			//return "";
		}else{
            for(Book book : library){
                if(book != null){
					String find = book.getPublisher();
					if(find.contains(publisher)){
						str[j][0]=book.getBookname();
						str[j][1]=book.getAuthor();
						str[j][2]=book.getPublisher();
						str[j][3]=book.getStyle();
						str[j][4]=book.getStatus()? "庫中" : "已出借";
						j++;
					}
                }
            }
			DefaultTableModel model = new DefaultTableModel(str, title);
			JTable table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			if(str[0][0]==null){
				JOptionPane.showMessageDialog(null, "查無此出版社", "搜尋結果", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, scrollPane, "搜尋結果", -1);
			}
            //JOptionPane.showMessageDialog(null, sameBooks==""? "查無此出版社" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            //return sameBooks;
        } 
    }

    //搜尋作者
    public static void searchbyAuthor() {
        //String sameBooks = "";
		Object[] title={"書名","作者","出版社","類別","狀態"};
		Object[][] str = new Object[library.size()][5];
		int j = 0;
        String author = JOptionPane.showInputDialog(null, "輸入作者關鍵字", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(author == null){
            searchBook();
            //return "";
        }else if(author.length()<1){
			JOptionPane.showMessageDialog(null, "輸入關鍵字為空", "查詢失敗", JOptionPane.ERROR_MESSAGE);
			//return "";
		}else{
            for(Book book : library){
                if(book != null){
					String find = book.getAuthor();
					if(find.contains(author)){
						str[j][0]=book.getBookname();
						str[j][1]=book.getAuthor();
						str[j][2]=book.getPublisher();
						str[j][3]=book.getStyle();
						str[j][4]=book.getStatus()? "庫中" : "已出借";
						j++;
					}
                }
            }
			DefaultTableModel model = new DefaultTableModel(str, title);
			JTable table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			if(str[0][0]==null){
				JOptionPane.showMessageDialog(null, "查無此作者", "搜尋結果", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, scrollPane, "搜尋結果", -1);
			}
            //JOptionPane.showMessageDialog(null, sameBooks==""? "查無此作者" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            //return sameBooks;
        }
    }

    //搜尋書名
    public static void searchbyName() {
		Object[] title={"書名","作者","出版社","類別","狀態"};
		Object[][] str = new Object[library.size()][5];
		int j = 0;
        //String sameBooks = "";
        String bookname = JOptionPane.showInputDialog(null, "輸入書名關鍵字", "查詢書籍", JOptionPane.QUESTION_MESSAGE);
        if(bookname == null){
            searchBook();
            //return "";
        }else if(bookname.length()<1){
			JOptionPane.showMessageDialog(null, "輸入關鍵字為空", "查詢失敗", JOptionPane.ERROR_MESSAGE);
			//return "";
		}
		else{
            for(Book book : library){
                if(book != null){
					String find = book.getBookname();
					if(find.contains(bookname)){
						str[j][0]=book.getBookname();
						str[j][1]=book.getAuthor();
						str[j][2]=book.getPublisher();
						str[j][3]=book.getStyle();
						str[j][4]=book.getStatus()? "庫中" : "已出借";
						j++;
					}
                }
            }
			DefaultTableModel model = new DefaultTableModel(str, title);
			JTable table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			if(str[0][0]==null){
				JOptionPane.showMessageDialog(null, "查無此書名", "搜尋結果", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, scrollPane, "搜尋結果", -1);
			}
            //JOptionPane.showMessageDialog(null, sameBooks==""? "查無此書" : sameBooks, "查詢結果", JOptionPane.INFORMATION_MESSAGE);
            //return sameBooks;
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
		
		ImageIcon button_exit_icon=new ImageIcon("button_exit.png");
		ImageIcon button_addBook_icon=new ImageIcon("button_addBook.png");
		ImageIcon button_deletebook_icon=new ImageIcon("button_deletebook.png");
		ImageIcon button_editBook_icon=new ImageIcon("button_editBook.png");
		ImageIcon button_getLibrary_icon=new ImageIcon("button_getLibrary.png");
		ImageIcon button_searchbook_admin_icon=new ImageIcon("button_searchbook_admin.png");
		   
	  	JLabel label_adminname;
	  	label_adminname = new JLabel(" ");// label_username.setText(String); 更改顯示
	  	label_adminname.setBounds(96, 52, 120, 25); //顯示用戶名
	  	panel_adminmenu.add(label_adminname);

		//frame panel setup
		frame_adminmenu.setSize(600, 440);
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);	
	    ImageIcon icon=new ImageIcon("frame_admin_menu_icon.png");

		frame_adminmenu.setLocationRelativeTo(null);
		frame_adminmenu.setResizable(false);
		frame_adminmenu.setDefaultCloseOperation(JFrame. DO_NOTHING_ON_CLOSE);
		frame_adminmenu.add(panel_adminmenu);
		
		panel_adminmenu.setLayout(null);
		
		
		// 新增書籍button_addbook setup
		button_addbook = new JButton(button_addBook_icon);
		button_addbook.setBounds(231, 68, 143, 116);
		panel_adminmenu.add(button_addbook);

		// 刪除書籍button_deletbook setup
		button_deletbook = new JButton(button_deletebook_icon);
		button_deletbook.setBounds(408, 68, 143, 116);
		panel_adminmenu.add(button_deletbook);

		// 修改書籍資料button_editbook setup
		button_editbook = new JButton(button_editBook_icon);
		button_editbook.setBounds(47, 215, 143, 116);
		panel_adminmenu.add(button_editbook);
		
		// 查詢書籍button_searchbook setup
		button_searchbook = new JButton(button_searchbook_admin_icon);
		button_searchbook.setBounds(228, 214, 143, 116);
		panel_adminmenu.add(button_searchbook);
		
		// 顯示所有書籍button_getLibrary setup
		button_getLibrary = new JButton(button_getLibrary_icon);
		button_getLibrary.setBounds(408, 215, 143, 116);
		panel_adminmenu.add(button_getLibrary);
		
		// 離開button_exit setup
		button_exit = new JButton(button_exit_icon);
		button_exit.setBounds(17, 13, 84, 34);
		panel_adminmenu.add(button_exit);

		//background setup
		JLabel background = new JLabel(icon);
		panel_adminmenu.add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

		frame_adminmenu.setVisible(true);


		button_addbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				addBook_gui();
				frame_adminmenu.dispose();
			}});

		button_deletbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				deleteBook();
				frame_adminmenu.dispose();
			}});

		button_editbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				editBook();
				frame_adminmenu.dispose();
			}});

		button_searchbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				searchBook();
  				adminMenu_gui();
				frame_adminmenu.dispose();

			}});

		button_getLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				getLibrary();
				frame_adminmenu.dispose();
			}});

		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				welcome_gui();
				frame_adminmenu.dispose();
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
				}else if(!deleteID.chars().allMatch( Character::isDigit)){
					JOptionPane.showMessageDialog(null, "請輸入數字", "刪除失敗", JOptionPane.ERROR_MESSAGE);
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
				}else if(!editID.chars().allMatch( Character::isDigit)){
					JOptionPane.showMessageDialog(null, "請輸入數字", "修改失敗", JOptionPane.ERROR_MESSAGE);
				}else{
					int index = Integer.parseInt(editID);
					bookname = library.get(index).getBookname();
					String author = library.get(index).getAuthor();
					String publisher = library.get(index).getPublisher();
					String style = library.get(index).getStyle();
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
						library.set(index, new Book(bookname, author, publisher, style, index));
					}
				}

            }
        }
        adminMenu_gui();
    }

    //新增書籍
    public static void addBook(String bookname, String author, String publisher, String style) {
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
                library.add(new Book(bookname, author, publisher, style, index));
                JOptionPane.showMessageDialog(null, "新增成功\n"+library.get(index), "新增書籍", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "此書已存在"+sameBooks, "新增書籍失敗", JOptionPane.ERROR_MESSAGE);
            }
        }
        adminMenu_gui();
    }

	//新增書籍GUI
	public static void addBook_gui(){

		String[] stylearray = {"科幻", "懸疑", "文學", "商業", "電腦"};

		JFrame frame_bookinfo = new JFrame(); // 註冊的frame
		JPanel panel_bookinfo = new JPanel(); // 註冊的panel
		
		JLabel label_bookname; //"書名"的文字標示
		JLabel label_author; //"作者"的文字標示
		JLabel label_publisher; //"出版社"的文字標示
		JLabel label_style;
		
		JTextField text_bookname; // 輸入用戶帳號
		JTextField text_author; // 輸入用戶密碼
		JTextField text_publisher; // 輸入用戶名字

		JComboBox<String> combobox_style = new JComboBox<>(stylearray); //選擇類型

				
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
		text_author = new JTextField(20);
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

		label_style = new JLabel("類別");
		label_style.setBounds(10, 110, 80, 25);
		panel_bookinfo.add(label_style);

		//combobox_identity setup
		combobox_style.setBounds(110, 110, 80, 25);
		panel_bookinfo.add(combobox_style);
		
		// button_login setup
		button_addBook = new JButton("新增書籍");
		button_addBook.setBounds(10, 140, 80, 25);
		panel_bookinfo.add(button_addBook);
		button_addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String style_input = combobox_style.getItemAt(combobox_style.getSelectedIndex());
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
				addBook(bookname_input, author_input, publisher_input, style_input);
				frame_bookinfo.dispose();
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
    public static void getLibrary() {	
		Object[] title={"ID","書名","作者","出版社","類別","狀態"};
		Object[][] str = new Object[library.size()][6];
		int j = 0;

		for(int i=0; i<library.size();i++){
			if(library.get(i)!=null){
				str[j][0]=library.get(i).getID();
				str[j][1]=library.get(i).getBookname();
				str[j][2]=library.get(i).getAuthor();
				str[j][3]=library.get(i).getPublisher();
				str[j][4]=library.get(i).getStyle();
				str[j][5]=library.get(i).getStatus()? "庫中" : "已出借";
				j++;
			}
		}

		DefaultTableModel model = new DefaultTableModel(str, title);
      	JTable table = new JTable(model);
      	JScrollPane scrollPane = new JScrollPane(table);
      	JOptionPane.showMessageDialog(null, scrollPane, "所有館藏", -1);
		adminMenu_gui();
    }
}