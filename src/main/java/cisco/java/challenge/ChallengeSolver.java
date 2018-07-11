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
        return null;
    }
}
