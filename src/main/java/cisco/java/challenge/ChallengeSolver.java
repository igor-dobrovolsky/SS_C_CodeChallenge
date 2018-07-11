package cisco.java.challenge;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;

public class ChallengeSolver implements GraphWalker, GraphPathSupplier {
    @Override
    public ArrayList<ArrayList<GNode>> paths(GNode node) {
        Validate.notNull(node);
        return null;
    }

    @Override
    public ArrayList<GNode> walkGraph(GNode node) {
        Validate.notNull(node);

        ArrayList<GNode> nodes = new ArrayList<>();

        traverseNodes(nodes, node);

        return nodes;
    }

    private static void traverseNodes(ArrayList<GNode> nodes, GNode node) {
        nodes.add(node);
        for(GNode cNode : node.getChildren())
            traverseNodes(nodes, cNode);
    }
}
