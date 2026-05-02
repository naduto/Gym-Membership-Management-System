package BackEnd;

import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;
import constants.FileName;
public class AdminRole {

    private TrainerDatabase database;

    public AdminRole() throws IOException {
        database = new TrainerDatabase(FileName.TRAINER_FILENAME);
    }

    public void addTrainer(String trainerId, String name, String email, String specialty, String phoneNumber) {
        if (database.contains(trainerId)) {
            JOptionPane.showMessageDialog(null, "Trainer with Id = " + trainerId + " alredy exists!!");

            return ;
        }
        Trainer newtrainer = new Trainer(trainerId, name, email, specialty, phoneNumber);
        database.insertRecord(newtrainer);
        JOptionPane.showMessageDialog(null, "Trainer with Id = "+trainerId+" successfully added !!!");

    }

    public ArrayList<Trainer> getListOfTrainers() {
        return database.returnAllRecords();
    }

    public void removeTrainer(String key) {
        if (!database.contains(key)) {
            JOptionPane.showMessageDialog(null,"Trainer with Id = " + key + " not found!!!");
            return;
        }
        database.deleteRecord(key);
        JOptionPane.showMessageDialog(null,"Trainer with Id = "+key+" Successfully removed !!!");
    }

    public void logout() {
        database.saveToFile();
        
       
    }

}
