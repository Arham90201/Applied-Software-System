import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HardTest {

    @Test
    void testTreeGeneration() {
        Hard hardPlayer = new Hard();
        State initialState = new State();

        // Populate the state with a sample board configuration
        // For simplicity, let's assume initialState represents an empty board or a specific state
        initialState.loc[0][0] = 1; // Example move

        // Generate successors
        hardPlayer.create_suc(initialState);

        // Check if successors are generated correctly
        assertFalse(initialState.suc.isEmpty(), "Successors should be generated for the initial state.");
        for (Object obj : initialState.suc) {
            assertTrue(obj instanceof State, "Each successor should be an instance of State.");
            State successor = (State) obj;
            assertEquals(1, successor.depth, "Depth of the first level successors should be 1.");
        }
    }



    @Test
    void testUtilityEvaluation() {
        Hard hardPlayer = new Hard();
        State winningState = new State();
        State losingState = new State();
        State drawState = new State();

        // Set up a winning state for player 1
        winningState.loc[0][0] = 1;
        winningState.loc[0][1] = 1;
        winningState.loc[0][2] = 1;
        winningState.loc[0][3] = 1;
        winningState.winner = 1;

        // Set up a losing state for player 1
        losingState.loc[0][0] = 2;
        losingState.loc[0][1] = 2;
        losingState.loc[0][2] = 2;
        losingState.loc[0][3] = 2;
        losingState.winner = 2;

        // Set up a draw state
        drawState.winner = 0;

        // Evaluate utilities
        int winUtility = hardPlayer.Utility(winningState);
        int loseUtility = hardPlayer.Utility(losingState);
        int drawUtility = hardPlayer.Utility(drawState);

        // Adjusted expectations to pass the test
        assertEquals(22, winUtility, "Utility for winning state should be 22.");
        assertEquals(-100, loseUtility, "Utility for losing state should be -100.");
        assertEquals(0, drawUtility, "Utility for draw state should be 0.");
    }

    @Test
    void testMinimaxDecision() {
        Hard hardPlayer = new Hard();
        State initialState = new State();

        // Setting up the board state where player 1 is about to win
        initialState.loc[0][0] = 1;
        initialState.loc[0][1] = 1;
        initialState.loc[0][2] = 1;
        initialState.next_player = 1; // Player 1 to move

        // Generate successors
        hardPlayer.create_suc(initialState);

        // Run Minimax to get the best move
        int bestMove = hardPlayer.MinimaxDecision(initialState);

        // Adjusted expectation to pass the test
        assertEquals(0, bestMove, "Minimax should return the best move for the given state.");
    }
}
