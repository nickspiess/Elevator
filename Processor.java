
public class Processor {

    /**
     * File name: Processor.java
     * @author SPIESSNA
     * description: This program is a processor to process the commands that are inputted through the
     * command input file.  It creates queues for each floor for people waiting, SLLists for each person
     * that is currently on the floor and a StackList for the elevator to hold people.
     * Version: 5
     * @since 11/5/19
     */

    // Variable for max elevator space
    private final static int MAX_ELEVATOR_SPACE = 7;

    // Create the stacks for each floor
    private QueueList<Person> line0;
    private QueueList<Person> line1;
    private QueueList<Person> line2;
    private QueueList<Person> line3;

    // Create the queue for the elevator
    private StackList<Person> elevator;

    // Create SLLists for each floor
    private SLList<Person> floor0;
    private SLList<Person> floor1;
    private SLList<Person> floor2;
    private SLList<Person> floor3;

    public Processor() {
        // Initiate SLLists
        floor0 = new SLList<Person>();
        floor1 = new SLList<Person>();
        floor2 = new SLList<Person>();
        floor3 = new SLList<Person>();

        // Initiate the Stacks for each floor
        line0 = new QueueList<Person>();
        line1 = new QueueList<Person>();
        line2 = new QueueList<Person>();
        line3 = new QueueList<Person>();

        // Initiate the Elevator Queue
        elevator = new StackList<Person>();
    }

    /**
     * A method to create an instance of a person and add them to a SLList for the correct floor
     * @param name - The name of the person we are creating
     * @param floor - The floor number of the correct SLList we will add the person to
     */
    void addToFloor(String name, int floor) {
        // Create the instance of our person to add
        Person person = new Person(name);
        // Add to floor list 0
        if(floor == 0) {
            floor0.add(person);
        }
        // To to floor 1
        else if (floor == 1) {
            floor1.add(person);
        }
        // Add to floor 2
        else if (floor == 2) {
            floor2.add(person);
        }
        // Add to floor 3
        else {
            floor3.add(person);
        }
    }


    /**
     * A method to put people into a line queue for each floor, if they are on the floor, we will add them
     * to the queue to get on the elevator
     * @param name - The name of the person we want to add to the wait queue
     * @param floor - The floor we are putting them into a queue on
     */
    void wait(String name, int floor) {
        // If floor is 0, we will check if name exists on floor floor, if it does, we will add, if not we will
        // Print out an error
        if (floor == 0) {
            // If our list checker cannot find the name on the floor, we will notify the user
            if (checkList(name, floor0) == -1) {
                System.out.println(name + " is not on floor 0.");
            }
            // Otherwise, we will add the person to the line queue for the elevator based off checkList return value
            else {
                line0.enqueue(floor0.getValue(checkList(name, floor0)));
            }
        }
        // If floor is 1
        else if (floor == 1) {
            // If our list checker cannot find the name on the floor, we will notify the user
            if (checkList(name, floor1) == -1) {
                System.out.println(name + " is not on floor 1.");
            }
            // Otherwise, we will add the person to the line queue for the elevator based off checkList return value
            else {
                //System.out.println(floor1.getValue(checkList(name, floor1)));
                line1.enqueue(floor1.getValue(checkList(name, floor1)));
            }
        }
        // If floor is 2
        else if (floor == 2) {
            // If our list checker cannot find the name on the floor, we will notify the user
            if (checkList(name, floor2) == -1) {
                System.out.println(name + " is not on floor 2.");
            }
            // Otherwise, we will add the person to the line queue for the elevator based off checkList return value
            else {
                //System.out.println(floor2.getValue(checkList(name, floor2)));
                line2.enqueue(floor2.getValue(checkList(name, floor2)));
            }
        }
        // If floor is 3
        else {
            // If our list checker cannot find the name on the floor, we will notify the user
            if (checkList(name, floor3) == -1) {
                System.out.println(name + " is not on floor 3.");
            }
            // Otherwise, we will add the person to the line queue for the elevator based off checkList return value
            else {
                //System.out.println(floor3.getValue(checkList(name, floor3)));
                line3.enqueue(floor3.getValue(checkList(name, floor3)));
            }
        }
    }

    /**
     * Helper method to check if a person is on a certain floor
     * @param name - The name of the person we are checking for
     * @param floor - The floor we are seeing if the person is on
     */
    private int checkList(String name, SLList floor) {
        // Iterate through each value and check if the value equals the name, if it does, return true, if not, return false
        for (int i = 0; i <= floor.length() - 1; i++) {
            // if our value at our SLList is equal to our name, we will return the index it is at
            if (floor.getValue(i).toString().equals(name)) {
                return i;
            }
        }
        // If no return, return -1
        return -1;
    }


    /**
     * A method to pick up people from a certain floor, we will pick up until the elevator is full
     * @param floor - the floor we are picking up at
     */
    public void pickUp(int floor) {
        if (floor == 0) {
            //Find out line size to iterate through
            int lineSize = line0.size()-1;
            // Iterate through line of people waiting on floor
            for (int i = 0; i <= lineSize; i++) {
                // If we have a full elevator, stop adding people to elevator
                if (elevator.size() == MAX_ELEVATOR_SPACE) {
                    break;
                }
                // Take person off line and add them to elevator
                elevator.push(line0.dequeue());
                // Remove the person from the floors SLList
                removeSLList(floor0, elevator.peek().toString());
            }
        }
        else if (floor == 1) {
            //Find out line size
            int lineSize = line1.size()-1;
                // Iterate through line of people waiting on floor
                for (int i = 0; i <= lineSize; i++) {
                    // If we have a full elevator, stop adding people to elevator
                    if (elevator.size() == MAX_ELEVATOR_SPACE) {
                        break;
                    }
                    // Take person off line and add them to elevator
                    elevator.push(line1.dequeue());
                    // Remove the person from the floors SLList
                    removeSLList(floor1, elevator.peek().toString());
                }
        }
        else if (floor == 2) {
            //Find out line size to iterate through
            int lineSize = line2.size()-1;
            // Iterate through line of people waiting on floor
            for (int i = 0; i <= lineSize; i++) {
                // If we have a full elevator, stop adding people to elevator
                if (elevator.size() == MAX_ELEVATOR_SPACE) {
                    break;
                }
                // Take person off line and add them to elevator
                elevator.push(line2.dequeue());
                // Remove the person from the floors SLList
                removeSLList(floor2, elevator.peek().toString());
            }
        }
        else {
            //Find out line size to iterate through
            int lineSize = line3.size()-1;
            // Iterate through line of people waiting on floor
            for (int i = 0; i <= lineSize; i++) {
                System.out.println(line3.size());
                // If we have a full elevator, stop adding people to elevator
                if (elevator.size() == MAX_ELEVATOR_SPACE) {
                    break;
                }
                // Take person off line and add them to elevator
                elevator.push(line3.dequeue());
                // Remove the person from the floors SLList
                removeSLList(floor3, elevator.peek().toString());
            }
        }
    }

    /**
     * A method to drop off people in the elevator - If the numToDropOff is greater than elevator size,
     * we will empty the entire elevator to the floor
     * @param floor - the floor we are dropping off at
     * @param numToDropOff - The number of people to drop off
     */
    public void dropOff(int floor, int numToDropOff) {
        // If the number to drop off is greater than the amount of people in the elevator, we will empty the elevator
        if (numToDropOff > elevator.size()) {
            int elevatorSize = elevator.size() -1;
            // Checking for floors
            if (floor == 0) {
                // Iterate through elevator, emptying
                for (int i = 0; i <= elevatorSize; i++) {
                    // Remove from elevator top and add to SLList of members on floor
                    floor0.add(elevator.pop());
                }
                // If there are people waiting on the floor queue
                if (line0.size() > 0) {
                    int lineSize = line0.size() - 1;
                    // Iterate through our line queue, so we can add to the elevator
                    for (int i = 0; i <= lineSize; i++) {
                        // If our elevator is full, break out
                        if (elevator.size() == MAX_ELEVATOR_SPACE) {
                            break;
                        }
                        // Add the person from line0 to the elevator StackList
                        elevator.push(line0.dequeue());
                    }
                }
            }
            else if (floor == 1) {
                // Iterate through elevator, emptying
                for (int i = 0; i <= elevatorSize; i++) {
                    System.out.println(elevator.size());
                    // Remove from elevator top and add to SLList of members on floor
                    floor1.add(elevator.pop());
                }
                // Check to see if anyone is waiting to get on the elevator
                if (line1.size() > 0) {
                    int lineSize = line1.size() - 1;
                    // Iterate through our line queue, so we can add to the elevator
                    for (int i = 0; i <= lineSize; i++) {
                        // If our elevator is full, break out
                        if (elevator.size() == MAX_ELEVATOR_SPACE) {
                            break;
                        }
                        //Take person from line1 queue and add them to elevator StackList
                        elevator.push(line1.dequeue());
                    }
                }
            }
            else if (floor == 2) {
                // Iterate through elevator, emptying
                for (int i = 0; i <= elevatorSize; i++) {
                    // Remove from elevator top and add to SLList of members on floor
                    floor2.add(elevator.pop());
                }
                // Check to see if anyone is waiting to get on the elevator
                if (line2.size() > 0) {
                    int lineSize = line2.size() - 1;
                    // Iterate through our line queue, so we can add to the elevator
                    for (int i = 0; i <= lineSize; i++) {
                        // If our elevator is full, break out
                        if (elevator.size() == MAX_ELEVATOR_SPACE) {
                            break;
                        }
                        // Take the person from line2 queue and add them to the elevator StackList
                        elevator.push(line2.dequeue());
                    }
                }
            }
            else {
                // Iterate through elevator, emptying
                for (int i = 0; i <= elevatorSize; i++) {
                    // Remove from elevator top and add to SLList of members on floor
                    floor3.add(elevator.pop());
                }
                // Check to see if anyone is waiting to get on the elevator
                if (line3.size() > 0) {
                    int lineSize = line3.size() - 1;
                    // Iterate through our line queue, so we can add to the elevator
                    for (int i = 0; i <= lineSize; i++) {
                        // If our elevator is full, break out
                        if (elevator.size() == MAX_ELEVATOR_SPACE) {
                            break;
                        }
                        // Take person from line3 queue and add them to elevator StackList
                        elevator.push(line3.dequeue());
                    }
                }
            }
        }
        // Amount of people on elevator is less than amount we want to drop off
        else {
            // Checking for floors
            if (floor == 0) {
                // Iterate through elevator, emptying how many we want to drop off
                for (int i = 0; i <= numToDropOff -1; i++) {
                    // If the elevator is empty, we will break out and stop trying to take people out
                    if (elevator.size() == 0) {
                        break;
                    }
                    // Remove from elevator top and add to SLList of members on floor
                    floor0.add(elevator.pop());
                }
                // If there are people waiting on the floor queue
                if (line0.size() > 0) {
                    int lineSize = line0.size() - 1;
                    // Iterate through line, adding to elevator
                    for (int i = 0; i <= lineSize; i++) {
                        // If elevator is full, we will stop adding people from line queue
                        if (elevator.size() == MAX_ELEVATOR_SPACE) {
                            break;
                        }
                        // Add next person in line0 queue to elevator StackList
                        elevator.push(line0.dequeue());
                    }
                }
            }
            else if (floor == 1) {
                // Iterate through elevator, emptying
                for (int i = 0; i <= numToDropOff -1; i++) {
                    // If the elevator is empty, we will break out and stop trying to take people out
                    if (elevator.size() == 0) {
                        break;
                    }
                    // Remove from elevator top and add to SLList of members on floor
                    floor1.add(elevator.pop());
                }
                // Iterate through line, adding to elevator
                int lineSize = line1.size() - 1;
                for (int i = 0; i <= lineSize; i++) {
                    // If elevator is full, we will stop adding people from line queue
                    if (elevator.size() == MAX_ELEVATOR_SPACE) {
                        break;
                    }
                    // Add person from line1 queue to elevator StackList
                    elevator.push(line1.dequeue());
                }
            }
            else if (floor == 2) {
                // Iterate through elevator, emptying
                for (int i = 0; i <= numToDropOff - 1; i++) {
                    // If the elevator is empty, we will break out and stop trying to take people out
                    if (elevator.size() == 0) {
                        break;
                    }
                    // Remove from elevator top and add to SLList of members on floor
                    floor2.add(elevator.pop());
                }
                int lineSize = line2.size() - 1;
                // Iterate through line, adding to elevator
                for (int i = 0; i <= lineSize; i++) {
                    // If elevator is full, we will stop adding people from line queue
                    if (elevator.size() == MAX_ELEVATOR_SPACE) {
                        break;
                    }
                    // Add person from line2 queue to elevator StackList
                    elevator.push(line2.dequeue());
                }
            }
            else  {
                // Iterate through elevator, emptying
                for (int i = 0; i <= numToDropOff -1; i++) {
                    // If the elevator is empty, we will break out and stop trying to take people out
                    if (elevator.size() == 0) {
                        break;
                    }
                    // Remove from elevator top and add to SLList of members on floor
                    floor3.add(elevator.pop());
                }
                int lineSize = line3.size() - 1;
                // Iterate through line, adding to elevator
                for (int i = 0; i <= lineSize; i++) {
                    // If elevator is full, we will stop adding people from line queue
                    if (elevator.size() == MAX_ELEVATOR_SPACE) {
                        break;
                    }
                    // Add person from line3 queue to elevator StackList
                    elevator.push(line3.dequeue());
                }
            }
        }
    }


    /**
     * We will do an inspection of the building, which will tell us who is next to get off the elevator, how many people
     * are on the elevator and who is next to get on the elevator at each floor
     */
    void inspection() {
        System.out.println("Elevator status:");
        // Checking how many people are on elevator and informing user
        if (elevator.size() > 0) {
            System.out.println("The elevator is not empty. There are " + (MAX_ELEVATOR_SPACE - elevator.size()) + " empty spots.");
            System.out.println(elevator.peek().toString() + " will be the next person to leave the elevator.");
        }
        else {
            System.out.println("The elevator is empty. There are " + (MAX_ELEVATOR_SPACE) + " open spots.");
            System.out.println("No one is in the elevator.");
        }
        // Check if people are on floors, if there are, notify who is, if not, notify that no one is waiting in those queues.
        if (line0.size() > 0) {
            System.out.println(line0.front() + " will be the next person to get on the elevator from floor 0.");
        }
        else {
            System.out.println("There are no people that want to get on the elevator from floor 0.");
        }
        if (line1.size() > 0) {
            System.out.println(line1.front() + " will be the next person to get on the elevator from floor 1.");
        }
        else {
            System.out.println("There are no people that want to get on the elevator from floor 1.");
        }
        if (line2.size() > 0) {
            System.out.println(line2.front() + " will be the next person to get on the elevator from floor 2.");
        }
        else {
            System.out.println("There are no people that want to get on the elevator from floor 2.");
        }
        if (line3.size() > 0) {
            System.out.println(line3.front() + " will be the next person to get on the elevator from floor 3.");
        }
        else {
            System.out.println("There are no people that want to get on the elevator from floor 3.");
        }
    }


    /**
     * A helper method to remove people from the SLList of the floor they are on when they get picked
     * up by the elevator
     * @param peopleOnFloor - The SLList of the floor we are going to remove the person from
     * @param name - The name of the person we are going to remove from the SLList
     */
    private void removeSLList(SLList peopleOnFloor, String name) {
        for (int i = 0; i <= peopleOnFloor.length() - 1; i++) {
            // If our our element to string value matches the name we passed, remove that name from the SLList
            if (peopleOnFloor.getValue(i).toString().equals(name)) {
                peopleOnFloor.remove(i);
            }
        }
    }
}
