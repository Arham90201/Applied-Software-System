import java.util.Random;

public class Easy implements Player {

    private Random random;

    public Easy() {
        System.out.println("RandomPlayer initialized.");
        this.random = new Random();
    }

    public Easy(ConnectFour game) {
        this.random = new Random();
    }

    public void setMove(int col) {}

    public int getType() {
        return 1;
    }

    public void go(SimpleBoard b) {
        int m = random.nextInt(7);

        while (b.cols[m] == 6) m = random.nextInt(7);

        b.Move(m);
    }

    public int makeMove(SimpleBoard board) {
        go(board);
        return board.getLastMove();
    }
}
