package cisco.java.challenge;


/**
 * Assume that when a GNode has no children, getChildren() returns an array of
 * size 0, and *not* null.
 *
 * Assume that all children returned by getChildren() are *not* null.
 */
public interface GNode {
    String getName();

    GNode[] getChildren();
}
