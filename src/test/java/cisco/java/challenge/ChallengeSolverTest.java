package cisco.java.challenge;

import org.junit.Test;

import java.util.*;
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

    @Test
    public void resolvingPathsOfChallengeShouldResultInCorrectPathsSet() {
        HashSet<List<String>> expectedPaths = new HashSet<>(
                Arrays.asList(
                        Arrays.asList("A", "B", "E"),
                        Arrays.asList("A", "B", "F"),
                        Arrays.asList("A", "C", "G"),
                        Arrays.asList("A", "C", "H"),
                        Arrays.asList("A", "C", "I"),
                        Arrays.asList("A", "D")
                ));
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

        ArrayList<ArrayList<GNode>> paths = new ChallengeSolver().paths(rootNode);

        assertEquals(paths.size(), expectedPaths.size()); //make sure list and set are of the same size before comparing list as set
        Set<List<String>> actualSet = paths.stream()
                .map(
                        p -> p.stream()
                                .map(n -> n.getName())
                                .collect(Collectors.toList()))
                .collect(Collectors.toSet());

        assertEquals(actualSet, expectedPaths);
    }
}