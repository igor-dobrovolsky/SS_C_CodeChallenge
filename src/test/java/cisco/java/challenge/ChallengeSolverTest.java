package cisco.java.challenge;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ChallengeSolverTest {

    @Test(expected = NullPointerException.class)
    public void walkingGraphOfNullNodeShouldResultInException() {
        new ChallengeSolver().walkGraph(null);
    }

    @Test
    public void walkingGraphOfChallengeShouldResultInCorrectNodesSet() {
        Set<String> expectedSet = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I"));
        GNode rootNode = GNodeFactory.build(
                "A",
                "  B",
                "    E",
                "    F",
                "  C",
                "    G",
                "    H",
                "    I",
                "  D");
        List<GNode> nodes = new ChallengeSolver().walkGraph(rootNode);

        assertEquals(nodes.size(), expectedSet.size()); //make sure list and set are of the same size before comparing list as set
        Set<String> actualSet = nodes.stream().map(n -> n.getName()).collect(Collectors.toSet());
        assertEquals(actualSet, expectedSet);
    }

    @Test(expected = NullPointerException.class)
    public void resolvingPathsForNullNodeShouldResultInException() {
        new ChallengeSolver().paths(null);
    }
}