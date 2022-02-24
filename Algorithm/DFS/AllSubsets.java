package Algorithm.DFS;

import java.util.*;

public class AllSubsets {
    public List<String> subSets(String set) {
        if (set == null) {
            return new ArrayList<String>();
        }
        List<String> result = new ArrayList<>();
        if (set.length() == 0) {
            result.add("");
            return result;
        }
        StringBuilder bd = new StringBuilder();
        Set<Character> skipped = new HashSet<>();
        subSets(set, skipped, 0, result, bd);
        return result;
    }

    private void subSets(String str, Set<Character> skipped, int index, List<String> result, StringBuilder bd) {
        if (index == str.length()) {
            result.add(new String(bd));
            return;
        }

        if (!skipped.contains(str.charAt(index))) {
            bd.append(str.charAt(index));
            subSets(str, skipped, index + 1, result, bd);
            bd.deleteCharAt(bd.length() - 1);
            skipped.add(str.charAt(index));
            subSets(str, skipped, index + 1, result, bd);
            skipped.remove(str.charAt(index));
        } else {
            subSets(str, skipped, index + 1, result, bd);
        }
    }
}
