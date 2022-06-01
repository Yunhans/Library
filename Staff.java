public class Staff extends Member{

    public Staff(String name, String account, String password){
        super(name, account, password);
    }

    public int getIdentity(){
        return 2;
    }

    @Override
    public int borrowDay() {
        return 14;
    }

    @Override
    public int borrowLimit() {
        return 5;
    }
    
}
