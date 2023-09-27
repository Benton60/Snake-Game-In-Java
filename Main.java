import java.util.*;
class Main
{
    public static void main (String[] args)
    {
        Thread checkMoves = new Thread (new checkMove ());
        checkMoves.start ();
        Thread snakes = new Thread (new snake ());
        snakes.start ();
    }
}