public abstract class Member {
    private String name;
    private String account;
    private String password;

    public Member(String name, String account, String password){
        setName(name);
        setAccount(account);
        setPassword(password);
    }

    public void login(){
        
    }

    public void menu(){
        
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

    public abstract void borrowBook();
    public abstract void returnBook();
    
}
