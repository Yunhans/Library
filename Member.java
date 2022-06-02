import java.util.ArrayList;
public abstract class Member {
    private String name;
    private String account;
    private String password;

    ArrayList<Book> borrowed = new ArrayList<Book>(); //­正在借的書
    ArrayList<Book> history = new ArrayList<Book>(); //已經還的書（用來擴充新功能 例如: 最常借的書or最喜歡的作者etc.）
    private String record=""; //已經還的書

    public Member(String name, String account, String password){
        setName(name);
        setAccount(account);
        setPassword(password);
    }

    public ArrayList<Book> getBorrowed(){
        return borrowed;
    }

    public ArrayList<Book> getHistory(){
        return history;
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

    public void setrecord(String record){
        this.record = record;
    }

    public String getrecord(){
        return record;
    }

    public abstract int borrowDay();
    public abstract int borrowLimit();
        
}
