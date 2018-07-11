package cisco.java.challenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChallengeSolverTest {

    @Test(expected = NullPointerException.class)
    public void walkingGraphOfNullNodeShouldResultInException() {
        new ChallengeSolver().walkGraph(null);
    }

    @Test(expected = NullPointerException.class)
    public void resolvingPathsForNullNodeShouldResultInException() {
        new ChallengeSolver().paths(null);
    }
}