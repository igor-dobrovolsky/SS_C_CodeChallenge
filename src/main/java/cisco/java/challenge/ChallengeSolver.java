package cisco.java.challenge;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;

public class ChallengeSolver implements GraphWalker, GraphPathSupplier {
    @Override
    public ArrayList<ArrayList<GNode>> paths(GNode node) {
        Validate.notNull(node);

        ArrayList<ArrayList<GNode>> paths = new ArrayList<>();

        traversePaths(paths, node);

        return paths;
    }

    private void traversePaths(ArrayList<ArrayList<GNode>> paths, GNode node) {
        traversePaths(paths, node, new ArrayList<>());
    }

    private void traversePaths(ArrayList<ArrayList<GNode>> paths, GNode node,
                               ArrayList<GNode> cPath) {

        cPath = (ArrayList<GNode>)cPath.clone();
        cPath.add(node);

        if(node.getChildren().length == 0) { //leaf
            paths.add(cPath);
        }
        else {
            for (GNode cNode : node.getChildren()) {
                traversePaths(paths, cNode, cPath);
            }
        }
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
