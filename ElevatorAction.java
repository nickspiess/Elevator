import java.io.*;
import java.util.*;

public class ElevatorAction {

    /**
     * File name: ElevatorAction.java
     * @author SPIESSNA
     * description: This is the main driver for the ElevatorAction program.  This will grab your command line
     * inputs, will read the files in the directory, iterate through the name file and will call on the processor
     * to create the instances of people and add them to lists of each floor.  It will iterate through the command
     * file and call on the instance of our processor, passing the needed arguments to pickup and drop off
     * people and inspect the elevator as well
     * Version: 3
     * @since 11/5/19
     */

    public static void main(String[] args) throws FileNotFoundException {
        Processor processor = new Processor();
        String peopleFile = args[0];
        String commandFile = args[1];
        linePass(peopleFile, processor, "People");
        linePass(commandFile, processor, "Command");
    }


    /**
     * A method to read the first file line by line and pass that line to a method that will
     * create instances of each person and add them to a SLList of people
     * @param peopleFile - The file name of people file
     * @param processor - The processor to manipulate the data
     * @param type - The type of file reader we want the call
     * @throws FileNotFoundException
     */
    public static void linePass(String peopleFile, Processor processor, String type) throws FileNotFoundException {
        Scanner inputFile = new Scanner(new File(peopleFile));
        while (inputFile.hasNextLine()) { // While file contains other lines
            // if there is inputFile hasNext token, continue while it does
            if (inputFile.hasNext()) {
                while (inputFile.hasNext()) { // While line contains tokens
                    String line = inputFile.nextLine(); // Grab line
                    if (type.equals("People")) {
                        peopleCreator(line, processor); // Pass line to command checker
                    }
                    else {
                        commandInstructions(line, processor);
                    }
                }
            }
            // If there is no next token, end.
            else {
                break;
            }
        }
    }


    /**
     * For each line in the people file, we will create an instance of that person and
     * add it to the SLList of people
     * @param line - the line of the person
     * @param processor - The processor to manipulate the data
     */
    public static void peopleCreator(String line, Processor processor) {
        // Create a scanner to read the line
        Scanner lineReader = new Scanner(line);
        String name;
        int floor;
        //While there are still tokens
        while (lineReader.hasNext()) {
            if (lineReader.hasNext()) {
                name = lineReader.next();
                if (lineReader.hasNextInt()) {
                    floor = lineReader.nextInt();
                    processor.addToFloor(name, floor);
                }
                else {
                    return;
                }
            }
            else {
                return;
            }
        }
    }


    /**
     * For each line in the people file, we will create an instance of that person and
     * add it to the SLList of people
     * @param line - the line of the person
     * @param processor - The processor to manipulate the data
     */
    public static void commandInstructions(String line, Processor processor) {
        // Create floor int
        int floor;
        // Create name String
        String name;
        // Create a scanner to read the line
        Scanner lineReader = new Scanner(line);
        // Grab command, convert to uppercase
        String command = lineReader.next();
        command = command.toUpperCase();
        // If command is wait, grab necessary tokens
        if (command.equals("WAIT")) {
            // Check for tokens existing
            if (lineReader.hasNextInt()) {
                floor = lineReader.nextInt();
                if (lineReader.hasNext()) {
                    name = lineReader.next();
                    processor.wait(name, floor);
                }
                else {
                    return;
                }
            }
            else {
                return;
            }
        }
        // if command is Pick up, grab necessary tokens
        if (command.equals("PICK_UP")) {
            // If block to verify there are the correct tokens
            if (lineReader.hasNextInt()) {
                floor = lineReader.nextInt();
                processor.pickUp(floor);
            }
            else {
                return;
            }
        }
        if (command.equals("DROP_OFF")) {
            // If blocks to verify there are tokens available
            if (lineReader.hasNextInt()) {
                floor = lineReader.nextInt();
                if (lineReader.hasNextInt()) {
                    int dropOffFloor = lineReader.nextInt();
                    processor.dropOff(floor, dropOffFloor);
                }
                else {
                    return;
                }
            }
            else {
                return;
            }
        }
        if (command.equals("INSPECTION")) {
            processor.inspection();
        }
    }
}
