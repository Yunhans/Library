package 程式碼;
public class Student extends Member{
    
    public Student(String name, String account, String password){
        super(name, account, password);
    }

    public int getIdentity(){
        return 0;
    }

    @Override
    public int borrowLimit() {
        return 5;
    }

    @Override
    public int borrowDay() {
        return 7;
    }
    
}
