import javax.swing.JOptionPane;
public class Admin extends Member {

    Library l = new Library();

    private String identity = "admin";

    public Admin(String name, String account, String password){
        super(name, account, password);
    }

    public String getIdentity(){
        return identity;
    }
    

    public void menu(){
        String [] menuOptions = {"新增書籍", "修改書籍資訊", "刪除書籍", "查詢書籍", "顯示所有書籍", "離開"};
        int menuOption = JOptionPane.showOptionDialog(null, "選擇功能", "管理員", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menuOptions, menuOptions[0]);

        switch(menuOption){
            case 0:
                l.addBook();
                break;
            case 1:
                l.editBook();
                break;
            case 2:
                l.deleteBook();
                break;
            case 3:
                l.searchBook();
                break;
            case 4:
                l.getLibrary();
                break;
            case 5:
                login();
                break;
        }

        menu();
    }

    @Override
    public void borrowBook() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void returnBook() {
        // TODO Auto-generated method stub
        
    }

}
