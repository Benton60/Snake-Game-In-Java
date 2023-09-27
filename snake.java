import java.util.*;
class snake implements Runnable
{
    public static Boolean hasWon = false;
    static List<Integer[]> snakeTail = new ArrayList<Integer[]>();
    static List<Integer[]> boundaries = new ArrayList<Integer[]>();
    static int y = 7;
    static int x = 7;
    public static String direction = " ";
    public static String moves = "";
    static Boolean hasEatenApple = false;
    static int appleX = 5;
    static int appleY = 7;
    static char[][] gameboard =
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
    public void run ()
    {
        gameboard[appleX][appleY] = '0';
        Integer[] start = {7,7};
        snakeTail.add(start);
        createBoundaries();
        while (hasWon == false)
        {
            printBoard ();
            pause();
        }
        while(true){}
    }
    public static void pause(){
        try
        {
            Thread.sleep (500);
        }
        catch (InterruptedException ie)
        {
            Thread.currentThread ().interrupt ();
        }
    }
    public static void printBoard (){
        makeApple();
        switch(direction){
            case "d":
                y += 1;
                makeTail();
                break;
            case "a":
                y -= 1;
                makeTail();
                break;
            case "w":
                x -= 1;
                makeTail();
                break;
            case "s":
                x+= 1;
                makeTail();
                break;
            default:
                break;
        }
        gameboard[x][y] = '+';
        for (int i = 0; i < gameboard.length; i++)
        {
            for (int j = 0; j < gameboard[i].length; j++)
            {
                System.out.print (gameboard[i][j]);
            }
            System.out.println ();
        }
        System.out.println("Score: "+ snakeTail.size());
    }
    public static void makeTail(){

        for(Integer i[] : snakeTail){
            if(i[0] == x && i[1] == y){
                hasWon = true;
                System.out.println("You Lost");
            }
        }
        for(Integer i[] : boundaries){
            if(i[0] == x && i[1] == y){
                hasWon = true;
                System.out.println("You lost");
            }
        }
        if(hasWon == false){
            Integer[] currentcoordinates = {x,y};
            Integer[] tailend = snakeTail.get(0);
            int xtailend = tailend[0];
            int ytailend = tailend[1];
            gameboard[xtailend][ytailend] = ' ';
            snakeTail.add(currentcoordinates);
            if(hasEatenApple == false){
                snakeTail.remove(0);
            }
        }
        for(Integer i[] : snakeTail){
            int xtail = i[0];
            int ytail = i[1];
            gameboard[xtail][ytail] = '+';
        }
    }
    public static void checkApple(){
        if(x == appleX && y == appleY){
            hasEatenApple = true;
        }else{
            hasEatenApple = false;
        }
    }
    public static void makeApple(){
        checkApple();
        if(hasEatenApple){
            Random rand = new Random();
            Integer[] applecoords = {6,7};
            appleX = rand.nextInt(12)+1;
            appleY = rand.nextInt(12)+1;
            applecoords[0] = appleX;
            applecoords[1] = appleY;
            while(snakeTail.contains(applecoords)){
                appleX = rand.nextInt(12)+1;
                appleY = rand.nextInt(12)+1;
                applecoords[0] = appleX;
                applecoords[1] = appleY;
            }
            gameboard[appleX][appleY] = '0';
        }
    }
    public static void createBoundaries(){
        for(int i = 0; i <= 14; i++){
            Integer[] bound1 = {0, i};
            Integer[] bound2 = {14,i};
            Integer[] bound3 = {i, 0};
            Integer[] bound4 = {i,14};
            boundaries.add(bound1);
            boundaries.add(bound2);
            boundaries.add(bound3);
            boundaries.add(bound4);
        }
    }
}