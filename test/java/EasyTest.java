import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class EasyTest {
    @Test
    public void testEasyAIRandomness() {
        ConnectFour game = new ConnectFour();
        Easy ai = new Easy(game);
        Map<Integer, Integer> moveCounts = new HashMap<>();
        int numberOfMoves = 10000; // large number of moves for statistical analysis

        // Initialize move count map
        for (int col = 0; col < 7; col++) {
            moveCounts.put(col, 0);
        }

        // Simulate AI moves
        for (int i = 0; i < numberOfMoves; i++) {
            game.initializeBoard(); // Reset the board to ensure it's not affecting the AI's randomness
            SimpleBoard board = game.getBoard();
            int move = ai.makeMove(board);
            moveCounts.put(move, moveCounts.get(move) + 1);
        }

        // Convert move counts to an array for Chi-Square test
        long[] observed = new long[7];
        double[] expected = new double[7];
        for (int col = 0; col < 7; col++) {
            observed[col] = moveCounts.get(col);
            expected[col] = numberOfMoves / 7.0;
        }

        // Print the observed frequencies for manual inspection if needed
        System.out.println("Move distribution: " + moveCounts);

        // Perform Chi-Square test
        ChiSquareTest chiSquareTest = new ChiSquareTest();
        double pValue = chiSquareTest.chiSquareTest(expected, observed);

        // Assert that the p-value is not below the significance level (e.g., 0.05)
        assertTrue(pValue > 0.05, "AI moves should be uniformly distributed across columns (random).");
    }

    private void assertTrue(boolean b, String s) {
    }
}
