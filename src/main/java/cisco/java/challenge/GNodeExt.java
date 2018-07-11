package cisco.java.challenge;

public interface GNodeExt extends GNode {
    default boolean isLeaf() {
        return getChildren().length == 0;
    }

    default boolean hasNoChildren() {
        return isLeaf();
    }
}
