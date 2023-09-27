class Main
{
    public static void main (String[] args) {
        Thread snakes = new Thread(new snake());                // initializes the Main thread where the program is run
        Thread checkMoves = new Thread(new checkMove());        // initializes the smaller threads that check for moves and reports them back to the main thread
        checkMoves.start();                                     //starts the checkmoves thread
        snakes.start();                                         //starts the snake thread
    }
}