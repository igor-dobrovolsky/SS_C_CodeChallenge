package cisco.java.challenge;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GNodeFactory {
    public final static int ROOT_LEVEL = 0;

    public static GNodeExt parse(String... lines) {
        Map<String, Integer> identPrefix2Level = new HashMap<>();
        Map<Integer, GNodeConcrete> currentNodesAtLevels = new HashMap<>();
        int prevLevel = ROOT_LEVEL;

        for (String line : lines) {
            Pair<String, String> identAndName = parseIdentAndName(line);

            if(identAndName == null) continue; //skipping blank line

            String ident = identAndName.getLeft();
            String name = identAndName.getRight();

            int identLevel = matchLevel(identPrefix2Level, ident);
            if (identLevel == prevLevel)
                currentNodesAtLevels.put(identLevel, new GNodeConcrete(name));
            else {
                currentNodesAtLevels.get(identLevel - 1).addChild(new GNodeConcrete(name));
            }

            prevLevel = identLevel;
        }

        return currentNodesAtLevels.getOrDefault(ROOT_LEVEL, null);
    }

    private static Pattern nonWhiteSpacePtrn = Pattern.compile("\\S");

    static int indexOf(Pattern pattern, String s) {
        Matcher matcher = pattern.matcher(s);
        return matcher.find() ? matcher.start() : -1;
    }

    private static String getIdentPrefix(String line) {
        int index = indexOf(nonWhiteSpacePtrn, line);
        return index >= 0
                ? line.substring(0, index)
                : /* blank line */ null;
    }

    private static Pair<String, String> parseIdentAndName(String line) {
        String ident = getIdentPrefix(line);
        return ident != null
                ? ImmutablePair.of(ident, line.substring(ident.length()).trim())
                : /* blank line */ null;
    }

    private static int matchLevel(Map<String, Integer> identPrefix2Level, String identPrefix) {
        identPrefix2Level.putIfAbsent(identPrefix, calcLevel(identPrefix2Level, identPrefix));

        return identPrefix2Level.get(identPrefix);
    }

    private static Integer calcLevel(Map<String, Integer> identPrefix2Level, String identPrefix) {
        int maxLessLevel = ROOT_LEVEL - 1;
        for (Map.Entry<String, Integer> kv : identPrefix2Level.entrySet()) {
            if (kv.getKey().length() == identPrefix.length())
                return kv.getValue();

            if (kv.getKey().length() < identPrefix.length())
                maxLessLevel = Math.max(maxLessLevel, kv.getValue());
        }
        return maxLessLevel + 1;
    }
}
