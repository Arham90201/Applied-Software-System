
import java.io.*;
public class ConnectFour {
   private static Player  p1;
   private static Player  p2;
    
    /** Creates a new instance of ConnectFour */
    public ConnectFour() {
        p1 = new HumanPlayer();
        p2 = new HumanPlayer();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Connect Four");
        System.out.println("Please choose from the following options:");
        System.out.println("    1. Human vs Human");
        System.out.println("    2. Human vs Computer (Human goes first)");
        System.out.println("    3. Computer vs Human (Computer goes first)");
        System.out.print("Choice[3]:");
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader br = new BufferedReader( isr );
        String s = null;
        try {
           s = br.readLine();
        }
        catch ( IOException ioe ) {
        }
        int n = Integer.parseInt(s);
        
        if (n == 1) {
            p1 = new HumanPlayer();
            p2 = new HumanPlayer();
        }
        
        else if((n == 2) || (n == 3)) {
            System.out.println("Please choose the level of the computer:");
            System.out.println("    1. Easy");
            System.out.println("    2. Moderate");
            System.out.println("    3. Hard");
            System.out.print("Choice[1]:");
            isr = new InputStreamReader( System.in );
            br = new BufferedReader( isr );
            s = null;
            try {
                s = br.readLine();
            }
            catch ( IOException ioe ) {
            }
            int nn = Integer.parseInt(s);
            
            Player temp= null;            
            
            if (nn==1) temp= new Easy();
            if (nn==2) temp= new Moderate();
            if (nn==3) temp= new Hard();
            
            if (n==2) {p1 = new HumanPlayer();p2 = temp;}
            if (n==3) {p1 = temp;p2 = new HumanPlayer();}                
        }
        
        
        SimpleBoard BoardA = new SimpleBoard();
        
        p1.setMove(-2);
        p2.setMove(-2);
        
        System.out.println();
        while (!BoardA.over() ){
           
           System.out.println(BoardA);
           System.out.print("Player "+BoardA.next());
           System.out.print(" next move:");
           
           if (BoardA.next() == 1) p1.go(BoardA);
           else p2.go(BoardA);
                       
        }
        System.out.println(BoardA);
    }
    
}

