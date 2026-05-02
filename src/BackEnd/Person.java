
package BackEnd;

import java.io.IOException;

public abstract class Person {
    private String name ;
    private String  emailAddress ;
    private String id ;
    private String phoneNumber ;

    public Person(String Id,String name, String emailAddress, String phoneNumber) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.id=Id;
    }
    public String getId()
    {
        return this.id;
    }
    
    public String lineRepresentation ()
    {
        String[] info = {this.id ,this.name  };
        String lineString = String.join(",", info );   
        return lineString;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    
    public abstract String  getSearchKey () ;
       
             
    
}

