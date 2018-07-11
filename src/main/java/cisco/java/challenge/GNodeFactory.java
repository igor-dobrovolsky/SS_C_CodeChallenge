package cisco.java.challenge;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GNodeFactory {
    public final static int ROOT_LEVEL = 0;

    public static GNodeExt build(String... lines) {
        Map<String, Integer> indentPrefix2Level = new HashMap<>();
        Map<Integer, GNodeConcrete> currentNodesAtLevels = new HashMap<>();

        for (String line : lines) {
            Pair<String, String> indentAndName = parseIdentAndName(line);

            if (indentAndName == null) continue; //skipping blank line

            String indent = indentAndName.getLeft();
            String name = indentAndName.getRight();

            int indentLevel = matchLevel(indentPrefix2Level, indent);
            GNodeConcrete node = new GNodeConcrete(name);
            currentNodesAtLevels.put(indentLevel, node);

            if (indentLevel > ROOT_LEVEL)
                currentNodesAtLevels.get(indentLevel - 1).addChild(node);
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
        String indent = getIdentPrefix(line);
        return indent != null
                ? ImmutablePair.of(indent, line.substring(indent.length()).trim())
                : /* blank line */ null;
    }

    private static int matchLevel(Map<String, Integer> indentPrefix2Level, String indentPrefix) {
        indentPrefix2Level.putIfAbsent(indentPrefix, calcLevel(indentPrefix2Level, indentPrefix));

        return indentPrefix2Level.get(indentPrefix);
    }

    private static Integer calcLevel(Map<String, Integer> indentPrefix2Level, String indentPrefix) {
        int closestLessLevel = ROOT_LEVEL - 1;
        for (Map.Entry<String, Integer> kv : indentPrefix2Level.entrySet()) {
            if (kv.getKey().length() == indentPrefix.length())
                return kv.getValue();

            if (kv.getKey().length() < indentPrefix.length())
                closestLessLevel = Math.max(closestLessLevel, kv.getValue());
        }
        return closestLessLevel + 1;
    }
}
