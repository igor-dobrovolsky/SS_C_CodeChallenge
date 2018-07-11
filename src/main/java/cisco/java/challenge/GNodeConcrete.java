package cisco.java.challenge;

class GNodeConcrete implements GNodeExt {
    private String name;
    private GNode[] nodes;

    GNodeConcrete(String name, GNode ... nodes) {
        this.name = name;
        this.nodes = nodes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GNode[] getChildren() {
        return nodes;
    }
}
