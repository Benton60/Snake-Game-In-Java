import java.util.*;
class snake implements Runnable
{
    private static Boolean hasWon = false;                                   //initiating a boolean that will allow the program to break a loop later in the code
    static List<Integer[]> snakeTail = new ArrayList<Integer[]>();          //creates an Arraylist that can used to contain the snakes tail
    static List<Integer[]> boundaries = new ArrayList<Integer[]>();         //creates an Arraylist that can be used to contain the borders of the board.
    static int y = 7;                                                       //these x and y values hold the head of the snakes position
    static int x = 7;
    public static String direction = " ";                                   //this creates a variable that can be accessed by the checkmove thread and changed to move the snake a different direction
    static Boolean hasEatenApple = false;                                   //this allows the program to keep track of wether an apple has been eaten or not and if it needs to make a new one
    static int appleX = 5;                                                  //this is the apples starting x coordinate
    static int appleY = 7;                                                  //this is the apples starting y coordinate
    static char[][] gameboard =                                                                         //this is a 2d array that will be our game board
            { {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_','_'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'}
            };
    public void run ()                                                      //the class's starting function
    {
        gameboard[appleX][appleY] = '0';                                    //creates the first apple
        Integer[] start = {7,7};                                            //creates the start point for the snake
        snakeTail.add(start);                                               //adds the starting point to the tale so it will be printed later
        createBoundaries();                                                 //this calls a function that adds the boundaries to the boundaries arraylist it could be done manually but this is faster and more mantainable
        while (hasWon == false)                                             //initializes the games main loop. it will stay in this loop until you win or lose
        {
            printBoard ();                                                  //calls the printboard function
            pause();                                                        //calls the pause functipn
        }
        checkMove.hasWon = hasWon;
    }
    private static void pause(){
        try
        {
            Thread.sleep (500);                                       //this sleep is needed to keep the program at 2 frames per second
        }
        catch (InterruptedException ie)
        {
            Thread.currentThread ().interrupt ();
        }
    }
    private static void printBoard (){
        makeApple();                                                        //calls the make apple function
        switch(direction){                                                  //this switch case ckecks which direction the snake is heading
            case "d":                                                       //letter d means right
                y += 1;                                                     //moves snake head one square to the right
                makeTail();                                                 //calls the makeTail function
                break;
            case "a":                                                       //letter a means left
                y -= 1;                                                     //moves snake head one square to the left
                makeTail();                                                 //calls the maketail function
                break;
            case "w":                                                       //letter w means up
                x -= 1;                                                     //moves snake head one square up
                makeTail();                                                 //calls the maketail function
                break;
            case "s":                                                       //letter s means down
                x+= 1;                                                      //moves snake head one square down
                makeTail();                                                 //calls the maketail function
                break;
            default:
                break;
        }
        gameboard[x][y] = 'o';                                              //makes the snake head an o
        for (int i = 0; i < gameboard.length; i++)                          //these two for loops are the part that actually prints the gameboard
        {
            for (int j = 0; j < gameboard[i].length; j++)
            {
                System.out.print (gameboard[i][j]);
            }
            System.out.println ();
        }
        System.out.println("Score: "+ snakeTail.size());                    //this line prints your score
    }
    private static void makeTail(){

        for(Integer i[] : snakeTail){                                       //this iterates through your tail array to check that your heads position is not the same as any other part of you snake
            if(i[0] == x && i[1] == y){
                hasWon = true;                                              //this changes the variable that controls the games loop ending the game
                System.out.println("You Lost");                             //prints a sad message
            }
        }
        for(Integer i[] : boundaries){                                      //this iterates through all of the boundary positions to make sure you haven't ran into the wall
            if(i[0] == x && i[1] == y){
                hasWon = true;                                              //this changes the variable that controls the games loop ending the game
                System.out.println("You lost");                             //prints a sad message
            }
        }
        if(hasWon == false){                                                //if you haven't won it still needs to keep track of your new position
            Integer[] currentcoordinates = {x,y};                           //stores the snakes head position in an array
            Integer[] tailend = snakeTail.get(0);                           //gets the last position in your tail
            int xtailend = tailend[0];                                      //stores the x value in variable
            int ytailend = tailend[1];                                      //stores the y value in a variable
            gameboard[xtailend][ytailend] = ' ';                            //erases the last position of your tail on the board
            snakeTail.add(currentcoordinates);                              //adds your heads position to the tail
            if(hasEatenApple == false){                                     //checks whether you have eaten an apple
                snakeTail.remove(0);                                  //if you haven't then it deltes the end of your tail
            }
        }
        for(Integer i[] : snakeTail){                                       //this for loop iterates through the array that stores your tail and changes those squares to x's
            int xtail = i[0];
            int ytail = i[1];
            gameboard[xtail][ytail] = '+';
        }
    }
    private static void checkApple(){
        if(x == appleX && y == appleY){                                     //if the position of the apple is equal to the position of the snakes head
            hasEatenApple = true;                                           //hasEatenApple is true
        }else{
            hasEatenApple = false;                                          //otherwise it is false
        }
    }
    private static void makeApple(){
        checkApple();                                                       //calls function checkapple
        if(hasEatenApple){                                                  //if the snake has eaten the apple a new apple must be created
            Random rand = new Random();                                     //initiates a new random
            Integer[] applecoords = {0,0};                                  //creates an array that store the apples coordinates
            appleX = rand.nextInt(12)+1;                             //gets a random position
            appleY = rand.nextInt(12)+1;
            applecoords[0] = appleX;                                        //sets the applecoordinates to that random position
            applecoords[1] = appleY;
            while(snakeTail.contains(applecoords) || squareAhead()){        //the first part checks whther your tail contains the applecoordinates the second calls the square ahead function
                appleX = rand.nextInt(12)+1;                         //if the apple coordinates are contained within the snakes tail it generates new coordinates
                appleY = rand.nextInt(12)+1;
                applecoords[0] = appleX;                                    //and sets the apple coordinates to that position again
                applecoords[1] = appleY;
            }
            gameboard[appleX][appleY] = '0';                                //places a 0 on the gameboard to represent the apple
        }
    }
    private static boolean squareAhead(){
        if (direction == "w" && snakeTail.contains(new int[] {appleX - 1, appleY})){            //all of these if statements check whether the apple is placed right before the snake
            return true;
        } else if (direction == "d" && snakeTail.contains(new int[] {appleX, appleY-1})) {      //becuase if it is it will get covered by the snake when its new position is printed but not get eaten
            return true;
        } else if (direction == "s" && snakeTail.contains(new int[] {appleX+1, appleY})) {
            return true;
        } else if (direction == "a" && snakeTail.contains(new int[] {appleX, appleY+1})) {
            return true;
        }else{
            return false;
        }
    }
    private static void createBoundaries(){         //this function creates all the boundaries for the gameboard
        for(int i = 0; i <= 14; i++){
            Integer[] bound1 = {0, i};              //creates coordinates for the edges
            Integer[] bound2 = {14,i};
            Integer[] bound3 = {i, 0};
            Integer[] bound4 = {i,14};
            boundaries.add(bound1);                 //and adds them to the boundaries arraylist
            boundaries.add(bound2);
            boundaries.add(bound3);
            boundaries.add(bound4);
        }
    }
}