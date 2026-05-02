package BackEnd;

import java.io.*;
import java.util.*;

public class Class {

    private String trainerID;
    private String classID;
    private String className;
    private int duration;
    private int availableSeats;

    public Class(String Classid, String className, String TrainerID, int duration, int availableseat) {
        this.trainerID = TrainerID;
        this.className = className;
        this.classID = Classid;
        this.duration = duration;
        this.availableSeats = availableseat;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableseat) {
        this.availableSeats = availableseat;
    }

    public String lineRepresentation() {
        String[] info = {this.classID, this.className, this.trainerID, Integer.toString(this.duration), Integer.toString(this.availableSeats)};
        String lineString = String.join(",", info);
        return lineString;
    }

    public String getSearchKey() {
        return this.classID;
    }

}
