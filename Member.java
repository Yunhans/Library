import java.util.ArrayList;
public abstract class Member {
    private String name;
    private String account;
    private String password;

    public ArrayList<Book> borrowed = new ArrayList<Book>(); //借閱書列
	private String record="";

    public Member(String name, String account, String password){
        setName(name);
        setAccount(account);
        setPassword(password);
    }

    public ArrayList<Book> getBorrowed(){
        return borrowed;
    }

    public abstract int getIdentity();

    public String getRecord(){
        return record;
    }

    public void newRecord(String record){
        this.record += record;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAccount(String account){
        this.account = account;
    }

    public String getAccount(){
        return account;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public abstract int borrowDay();
    public abstract int borrowLimit();
        
}
