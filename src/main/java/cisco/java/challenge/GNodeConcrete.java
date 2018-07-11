package cisco.java.challenge;

import java.util.ArrayList;
import java.util.Arrays;

class GNodeConcrete implements GNodeExt {
    private String name;
    private ArrayList<GNode> nodes;

    GNodeConcrete(String name, GNode ... nodes) {
        this.name = name;
        this.nodes = new ArrayList(Arrays.asList(nodes));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GNode[] getChildren() {
        return nodes.toArray(new GNode[0]);
    }

    public void addChild(GNodeConcrete node) {
        nodes.add(node);
    }
}
