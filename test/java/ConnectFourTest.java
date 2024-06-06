import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectFourTest {

    @Test
    public void testReadChoiceDefault() throws IOException {
        String input = "\n";
        BufferedReader br = new BufferedReader(new StringReader(input));
        int choice = ConnectFour.readChoice(3, br);
        assertEquals(3, choice);
    }

    @Test
    public void testReadChoiceValidInput() throws IOException {
        String input = "2\n";
        BufferedReader br = new BufferedReader(new StringReader(input));
        int choice = ConnectFour.readChoice(3, br);
        assertEquals(2, choice);
    }

    @Test
    public void testReadChoiceInvalidInput() throws IOException {
        String input = "invalid\n";
        BufferedReader br = new BufferedReader(new StringReader(input));
        int choice = ConnectFour.readChoice(3, br);
        assertEquals(3, choice);
    }



    @Test
    public void testMakeMoveInvalidColumn() {
        ConnectFour game = new ConnectFour();
        game.initializeBoard();
        assertFalse(game.makeMove(7, 'X'), Boolean.parseBoolean("Move should be unsuccessful for an invalid column."));
    }

    @Test
    public void testMakeMoveFullColumn() {
        ConnectFour game = new ConnectFour();
        game.initializeBoard();
        for (int i = 0; i < 6; i++) {
            game.makeMove(0, 'X');
        }
        assertFalse(game.makeMove(0, 'O'), Boolean.parseBoolean("Move should be unsuccessful for a full column."));
    }



    @Test
    void testHorizontalWin() {
        SimpleBoard board = new SimpleBoard();

        // Manually simulate the moves for a horizontal win for Player 1
        board.Move(0); // Player 1
        board.Move(0); // Player 2
        board.Move(1); // Player 1
        board.Move(1); // Player 2
        board.Move(2); // Player 1
        board.Move(2); // Player 2
        board.Move(3); // Player 1 - this should create a horizontal win

        // Check if player 1 wins
        assertEquals(1, board.winner(), "Player 1 should win with a horizontal alignment.");
    }

    @Test
    void testVerticalWin() {
        SimpleBoard board = new SimpleBoard();

        // Manually simulate the moves for a vertical win for Player 1
        board.Move(0); // Player 1
        board.Move(1); // Player 2
        board.Move(0); // Player 1
        board.Move(1); // Player 2
        board.Move(0); // Player 1
        board.Move(1); // Player 2
        board.Move(0); // Player 1 - this should create a vertical win

        // Check if player 1 wins
        assertEquals(1, board.winner(), "Player 1 should win with a vertical alignment.");
    }

    @Test
    void testDiagonalWin() {
        SimpleBoard board = new SimpleBoard();

        board.Move(0);
        board.Move(1);
        board.Move(1);
        board.Move(2);
        board.Move(2);
        board.Move(3);
        board.Move(2);
        board.Move(3);
        board.Move(3);
        board.Move(4);
        board.Move(3);

        // Check if player 1 wins
        assertEquals(1, board.winner(), "Player 1 should win with a diagonal alignment.");
    }

    @Test
    void testDrawGame() {
        SimpleBoard board = new SimpleBoard();
        Hard player1 = new Hard();
        Hard player2 = new Hard();

        // Fill the board in such a way to result in a draw
        int[][] moves = {
                {0, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6},
                {0, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6},
                {0, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6},
                {0, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6},
                {0, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6},
                {0, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}
        };

        for (int[] move : moves) {
            board.Move(move[0]);
            board.Move(move[1]);
        }

        // Check if the game is a draw
        assertEquals(0, board.winner, "The game should be a draw.");
    }
}

