
package BackEnd;


public class Member extends Person {
    private String membershipType ;
    private String status ;

    public Member(String Id,String name, String membershipType, String emailAddress, String phoneNumber, String statues) {
        super(Id,name, emailAddress, phoneNumber);
        this.membershipType = membershipType;
        this.status = statues;
    }
    @Override
    public String lineRepresentation() {
        String info = super.lineRepresentation();
        String lineString = info + ","+ this.membershipType+","+this.getEmailAddress()+","+this.getPhoneNumber()+","+this.status ;
        return lineString;
    }
    @Override
    public String getSearchKey() {
        
       
        return  super.getId() ;
    }
}

