package cisco.java.challenge;

import java.util.ArrayList;

public interface GraphPathSupplier {
    ArrayList<ArrayList<GNode>> paths(GNode node);
}
