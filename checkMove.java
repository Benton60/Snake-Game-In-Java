import java.util.*;
class checkMove implements Runnable {
    public static String move = "";

    public void run()
    {
        while(true){
            Scanner scan = new Scanner(System.in);
            move = scan.next();
            snake.direction = move;
        }
    }
}