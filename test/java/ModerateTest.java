import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModerateTest {
    @Test
    public void testModerateAIDefense() {
        ConnectFour game = new ConnectFour();
        game.initializeBoard();
        Moderate ai = new Moderate();

        // Set up a board state where the opponent (Player 1) has three in a row vertically in column 0
        SimpleBoard board = game.getBoard();
        board.Move(0); // Player 1
        board.Move(1); // Player 2
        board.Move(0); // Player 1
        board.Move(1); // Player 2
        board.Move(0); // Player 1

        // Moderate AI should block this move to prevent Player 1 from winning
        ai.go(board);

        // Assert that the AI placed a piece in column 0 (blocking move)
        assertEquals(2, board.loc[2][0]); // The AI's move should be placed at the topmost row available in column 0


    }

    @Test
    public void testModerateAIBlocksHorizontal() {
        ConnectFour game = new ConnectFour();
        game.initializeBoard();
        Moderate ai = new Moderate();

        SimpleBoard board = game.getBoard();
        // Simulate a situation where opponent (Player 1) is about to win horizontally
        board.Move(0); // Player 1
        board.Move(1); // Player 2
        board.Move(1); // Player 1
        board.Move(2); // Player 2
        board.Move(2); // Player 1
        board.Move(3); // Player 2
        board.Move(3); // Player 1

        // Moderate AI should block this move to prevent Player 1 from winning
        ai.go(board);

        // Assert that the AI placed a piece in column 3 (blocking move)
        assertEquals(2, board.loc[5][3]); // The AI's move should be placed at the bottommost row available in column 3
    }
}
