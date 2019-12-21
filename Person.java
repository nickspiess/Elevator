public class Person {

    /**
     * File name: Person.java
     * @author SPIESSNA
     * description: This program creates an instance of a person to eventually be used to add to SLLists
     * and be picked up and dropped off the elevator
     * Version: 1
     * @since 11/5/19
     */

    private String name;

    /**
     * Our constructor to create a person
     * @param name - The name of the person we want to create
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * A toString method to get the name of the person we are creating
     * @return the name of the person
     */
    public String toString() {
        return name;
    }

}
