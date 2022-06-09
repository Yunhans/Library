package 程式碼;
public class Admin extends Member{

    public Admin(String name, String account, String password) {
        super(name, account, password);
    }

    public int getIdentity(){
        return 3;
    }

    @Override
    public int borrowDay() {
        return 0;
    }

    @Override
    public int borrowLimit() {
        return 0;
    }
    
}
