import java.io.*;

public class ConnectFour {
    private Player p1;
    private Player p2;
    private SimpleBoard board;

    /** Creates a new instance of ConnectFour */
    public ConnectFour() {
        p1 = new HumanPlayer();
        p2 = new HumanPlayer();
        board = new SimpleBoard();
    }

    // Initialize players based on game mode and difficulty
    public void initializePlayers(int gameMode, int difficulty) {
        if (gameMode == 1) {
            p1 = new HumanPlayer();
            p2 = new HumanPlayer();
        } else if (gameMode == 2 || gameMode == 3) {
            Player temp = null;
            if (difficulty == 1) temp = new Easy();
            if (difficulty == 2) temp = new Moderate();
            if (difficulty == 3) temp = new Hard();
            if (gameMode == 2) { p1 = new HumanPlayer(); p2 = temp; }
            if (gameMode == 3) { p1 = temp; p2 = new HumanPlayer(); }
        }
        p1.setMove(-2);
        p2.setMove(-2);
    }

    // Start the game loop
    public void startGame() {
        System.out.println();
        while (!board.over()) {
            System.out.println(board);
            System.out.print("Player " + board.next());
            System.out.print(" next move:");

            if (board.next() == 1) p1.go(board);
            else p2.go(board);
        }
        System.out.println(board);
    }

    // Helper method to read user choice with a default value
    public static int readChoice(int defaultChoice, BufferedReader br) throws IOException {
        String s = br.readLine();
        if (s.isEmpty()) {
            return defaultChoice;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defaultChoice;
        }
    }

    // Main method for running the game
    public static void main(String[] args) throws IOException {
        ConnectFour game = new ConnectFour();

        System.out.println("Welcome to Connect Four");
        System.out.println("Please choose from the following options:");
        System.out.println("    1. Human vs Human");
        System.out.println("    2. Human vs Computer (Human goes first)");
        System.out.println("    3. Computer vs Human (Computer goes first)");
        System.out.print("Choice[3]:");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int gameMode = readChoice(3, br);

        int difficulty = 0;
        if (gameMode == 2 || gameMode == 3) {
            System.out.println("Please choose the level of the computer:");
            System.out.println("    1. Easy");
            System.out.println("    2. Moderate");
            System.out.println("    3. Hard");
            System.out.print("Choice[1]:");
            difficulty = readChoice(1, br);
        }

        game.initializePlayers(gameMode, difficulty);
        game.startGame();
    }

    // Accessor method for the board
    public SimpleBoard getBoard() {
        return board;
    }

    // Unused methods
    public void initializeBoard() {
        board.clear();
    }

    public String makeMove(int i, char x) {
        return "";
    }
}
