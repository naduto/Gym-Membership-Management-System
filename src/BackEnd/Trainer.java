package BackEnd;


public class Trainer extends Person {

    private String speciality;

    public Trainer(String ID,String name, String emailAddress, String speciality, String phonenumber) {
        super(ID,name, emailAddress, phonenumber);
        this.speciality = speciality;
    }

    @Override
    public String lineRepresentation() {
        String info = super.lineRepresentation();
        String lineString = info + "," + this.getEmailAddress() + "," + this.speciality + "," + this.getPhoneNumber();
        return lineString;
    }

    @Override
    public String getSearchKey() {
        
        return super.getId();

    }
}

