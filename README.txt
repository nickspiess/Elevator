ElevatorAction:

1. File List : All must be in same directory
- ElevatorAction.java
- Person.java
- Processor.java
- List.java
- Node.java
- Queue.java
- QueueList.java
- SLList.java
- StackList.java

2. In order to run this code, you must navigate to the directory that these files
exist in and compile the java programs using "javac *.java".  Then, you input:
 "java ElevatorActions <name_file> <command_file>"

 Where name_file is the file that contains the names and floor locations where people will be added to in the
 building.
 Where the command_file is the file that contains the commands that are input for the elevator.

 3. I decided to make a processor class due to some input from a friend saying that it was easier to access all
 your queues, stacks and lists by doing it in a non-static class. This made it easier to access all the queues, lists
 and stacks without having to transfer them from method to method.

 4. Overall, I didn't think this project was very difficult (especially compared to BioInfo).  I only had slight troubles
 for a bit with making loops that iterated through queues and dequeued individuals, because after every time looping
 through, the size would actually decrease and you end up not grabbing everyone that you're supposed to grab.  Other than
 that, it was a relatively easier project.