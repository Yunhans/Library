public class Teacher extends Member{

    public Teacher(String name, String account, String password){
		super(name, account, password);
	}

    public int getIdentity(){
        return 1;
    }

    @Override
    public int borrowLimit() {
        return 10;
    }

    @Override
    public int borrowDay() {
        return 21;
    }
    
}
