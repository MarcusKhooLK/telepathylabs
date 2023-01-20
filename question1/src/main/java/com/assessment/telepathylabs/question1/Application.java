package com.assessment.telepathylabs.question1;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Error, Usage: java -jar question1-0.0.1.jar <input_file>");
            System.exit(1);
        }

        try {
            Scanner reader = new Scanner(new FileInputStream(args[0]));
            List<Date> startTimes = new ArrayList<>();
            List<Date> endTimes = new ArrayList<>();
            DateFormat formatter = new SimpleDateFormat("kk:mm");
            while (reader.hasNext()) {
                String[] fromAndTo = reader.nextLine().split("-");
                if (fromAndTo.length != 2) {
                    continue;
                }
                startTimes.add(formatter.parse(fromAndTo[0]));
                endTimes.add(formatter.parse(fromAndTo[1]));
            }
            Collections.sort(startTimes);
            Collections.sort(endTimes);
            int ongoingMeetings = 1;
            int noOfMeetingRooms = 1;
            int i = 1;
            int j = 0;
            while (i < startTimes.size() && j < endTimes.size()) {
                if (startTimes.get(i).compareTo(endTimes.get(j)) < 0) {
                    ongoingMeetings++;
                    if (ongoingMeetings > noOfMeetingRooms) {
                        noOfMeetingRooms = ongoingMeetings;
                    }
                    i++;
                } else {
                    ongoingMeetings--;
                    j++;
                }
            }
            System.out.println(noOfMeetingRooms);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

}
