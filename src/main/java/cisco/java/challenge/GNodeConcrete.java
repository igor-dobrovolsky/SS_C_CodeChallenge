package cisco.java.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object anObject) {
        if (super.equals(anObject)) {
            return true;
        }
        if (anObject instanceof GNodeConcrete) {
            GNodeConcrete other = (GNodeConcrete)anObject;
            return name.equals(other.name) && nodes.equals(other.nodes);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nodes);
    }
}
