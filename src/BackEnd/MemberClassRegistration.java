
package BackEnd;


import java.time.LocalDate;


public class MemberClassRegistration {

    private String memberID;
    private String classID;
    private String status;
    LocalDate registrationDate;

    public MemberClassRegistration(String memberID, String classID, String status) {
        this.memberID = memberID;
        this.classID = classID;
        this.status = status;
        this.registrationDate = LocalDate.now();

    }
    
    public void setRegistrationDate(LocalDate L)
    {
        this.registrationDate=L;
    }
    public String getMemberID() {
        return memberID;
    }

    public String getClassID() {
        return classID;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String lineRepresentation() {
        String[] info = {this.memberID, this.classID, this.registrationDate.toString(), this.status};
        String lineString = String.join(",", info);
        return lineString;
    }

    public String getSearchKey() {
        String newID = this.memberID+this.classID ;
        return newID;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}



