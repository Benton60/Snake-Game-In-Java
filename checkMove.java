import java.util.*;
class checkMove implements Runnable {                   //the implements keyword allows this thread to be ran
    public static boolean hasWon = false;               //initializes a variable that the snake thread can access and use to shut down this thread
    public void run()                                   //Runnable classes do not use main voids they use a function called run
    {
        Scanner scan = new Scanner(System.in);          //creates a scan to scan for HI
        while(!hasWon){                                 //starts a loop that will only be broken when the other thread is finished
                snake.direction = scan.next();          //pulls the variable direction from the snake class to change wich way the snake will travel
        }
    }
}