package cisco.java.challenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class GNodeFactoryTest {

    @Test
    public void emptyLinesListShouldBuildNullGNode() {
        assertTrue(GNodeFactory.parse() == null);
    }

    @Test
    public void listOfSingleLineWithBlanksShouldBuildNullGNode() {
        assertTrue(GNodeFactory.parse("   ") == null);
    }

    @Test
    public void listOfMultipleLinesWithBlanksShouldBuildNullGNode() {
        assertTrue(GNodeFactory.parse("   ", " ", "      ") == null);
    }

    @Test
    public void listOfSingleLineWithStringShouldBuildRootGNodeWithNoChildren() {
        GNodeExt node = GNodeFactory.parse("A");
        assertTrue(node != null);
        assertTrue(node.getName().equals("A"));
        assertTrue(node.isLeaf());
    }

    @Test
    public void listOfNonEmptyAndEmptyLinesShouldBuildRootGNodeWithNoChildren() {
        GNodeExt node = GNodeFactory.parse("  ",
                "A",
                "  ");
        assertTrue(node != null);
        assertTrue(node.getName().equals("A"));
        assertTrue(node.isLeaf());
    }

    @Test
    public void listOfLinesWithSingleIndentShouldBuildRootGNodeWithSingleChild() {
        GNodeExt node = GNodeFactory.parse("  ",
                "A",
                " B");
        assertTrue(node != null);
        assertTrue(node.getName().equals("A"));
        assertTrue(!node.isLeaf());
        assertTrue(node.getName().equals("A"));
        assertTrue(node.getChildren().length == 1);
        assertTrue(node.getChildren()[0].getName().equals("B"));
        assertTrue(node.getChildren()[0].getChildren().length == 0);
    }

    @Test
    public void linesOfThreeLevelIndentShouldBuildProperGraph() {
        GNode expectedNode = new GNodeConcrete(
                "A",
                new GNodeConcrete("B",
                        new GNodeConcrete("E"),
                        new GNodeConcrete("F")),
                new GNodeConcrete("C",
                        new GNodeConcrete("G"),
                        new GNodeConcrete("H"),
                        new GNodeConcrete("I")),
                new GNodeConcrete("D")
        );

        GNodeExt node = GNodeFactory.parse("  ",
                "A",
                " B",
                "  E",
                "  F",
                " C",
                "  G",
                "  H",
                "  I",
                " D");

        assertTrue(node != null);
        assertTrue(node.equals(expectedNode));
    }
}