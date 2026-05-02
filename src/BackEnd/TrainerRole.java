package BackEnd;

import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;
import constants.FileName;
import java.time.LocalDate;

public class TrainerRole {

    private MemberDatabase memberDatabase;
    private MemberClassRegistrationDatabase registrationDatabase;
    private ClassDatabase classDatabase;
    private TrainerDatabase trainerDatabase;

    public TrainerRole() throws IOException {

        memberDatabase = new MemberDatabase(FileName.MEMBER_FILENAME);
        classDatabase = new ClassDatabase(FileName.CLASS_FILENAME);
        registrationDatabase = new MemberClassRegistrationDatabase(FileName.REGISTRATION_FILENAME);
        trainerDatabase = new TrainerDatabase(FileName.TRAINER_FILENAME);

    }

    public void addMember(String memberId, String name, String emailAddress, String membershipType, String phoneNumber, String statues) {

        if (memberDatabase.contains(memberId)) {
            JOptionPane.showMessageDialog(null, "Member with Id = " + memberId + " alredy exists!!");
            return;
        }
        Member newMember = new Member(memberId, name, membershipType, emailAddress, phoneNumber, statues);
        memberDatabase.insertRecord(newMember);
        JOptionPane.showMessageDialog(null, "Member with Id = "+memberId+" successfully added !!!");
    }

    public ArrayList<Member> getListOfMembers() {
        return memberDatabase.returnAllRecords();
    }

    public void addClass(String Classid, String className, String TrainerID, int duration, int availableseat) {
        if (classDatabase.contains(Classid)) {
            JOptionPane.showMessageDialog(null, "Class with Id = " + Classid + " alredy exists!!");
            return;
        }
        if (!this.trainerDatabase.contains(TrainerID)) {
            JOptionPane.showMessageDialog(null, "Invalid member ID!");
            return;
        }
        Class newClass = new Class(Classid, className, TrainerID, duration, availableseat);
        classDatabase.insertRecord(newClass);
        JOptionPane.showMessageDialog(null, "Class with Id = "+Classid+" with Trainer with Id = "+TrainerID+" successfully added !!!");
    }

    public ArrayList<Class> getListOfClasses() {
        return classDatabase.returnAllRecords();
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) {
       
        if (!this.classDatabase.contains(classID)) {
            JOptionPane.showMessageDialog(null, "Invalid class ID !");
            return false ;
        }
        if (!this.memberDatabase.contains(memberID)) {
            JOptionPane.showMessageDialog(null, "Invalid member ID!");
            return false ;
        }
        Class reqclass = this.classDatabase.getRecord(classID);
        if (reqclass.getAvailableSeats() <= 0) {
            JOptionPane.showMessageDialog(null, "NO Avalaible Seats");
             return false ;
        }
        if (this.registrationDatabase.contains(memberID + classID)) {
            JOptionPane.showMessageDialog(null, "Member with Id = "+memberID+" is already registered for this class with Id = "+classID+" !!!");
             return false ;
        }

        MemberClassRegistration registration = new MemberClassRegistration(memberID, classID, "Active");
        registration.setRegistrationDate(registrationDate);
        registrationDatabase.insertRecord(registration);
        reqclass.setAvailableSeats(reqclass.getAvailableSeats() - 1);
        JOptionPane.showMessageDialog(null, "Member with Id = "+memberID+" registered in Class with Id = "+classID+" successfully !!!");
        return true;
    }

    public boolean cancelRegistration(String memberID, String classID) {
        LocalDate date = LocalDate.now();
        if (!this.registrationDatabase.contains(memberID + classID)) {
            JOptionPane.showMessageDialog(null, "This Registration is NOT FOUND!");
            return false;
        }
        MemberClassRegistration cancelledRegistration = this.registrationDatabase.getRecord(memberID + classID);

        if (date.isAfter(cancelledRegistration.getRegistrationDate().plusDays(3))) {

            JOptionPane.showMessageDialog(null, "Sorry We Can't Cancel Registration as Cancellation Period Has Expired");
            return false;
        }

        Class reqClass = classDatabase.getRecord(classID);
         registrationDatabase.deleteRecord(memberID+classID);

        reqClass.setAvailableSeats(reqClass.getAvailableSeats() + 1);
        cancelledRegistration.setStatus("Cancelled");
        JOptionPane.showMessageDialog(null, "Member with Id = "+memberID+" has been unregistered from Class with Id = "+classID+" cancelled successfully !!! ");
        return true;

    }

    public ArrayList<MemberClassRegistration> getListOfRegistrations() {
        return registrationDatabase.returnAllRecords();
    }

    public void logout() {
        memberDatabase.saveToFile();
        classDatabase.saveToFile();
        registrationDatabase.saveToFile();
        System.out.println("All data saved. Logged out.");
    }
}
