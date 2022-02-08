package Algorithm.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MQueenNxN {
    public static void main(String[] args) {
        MQueenNxN result = new MQueenNxN();
        List<List<Integer>> resultList = result.mQueenNxN(8, 8);
        for (List<Integer> elem : resultList) {
            System.out.println(elem.toString());
        }
    }

    public List<List<Integer>> mQueenNxN(int m, int n) {
        if (m <= 0 || m > n) {
            return new ArrayList<>();
        }
        Integer[] curRes = new Integer[n];
        List<List<Integer>> result = new ArrayList<>();
        mQueenNxN(0, m, curRes, result);
        return result;
    }

    private void mQueenNxN(int index, int queenToLocate, Integer[] curRes, List<List<Integer>> result) {
        if (index == curRes.length) {
            if (queenToLocate == 0) {
                result.add(new ArrayList<>(Arrays.asList(curRes)));
            }
            return;
        }
        curRes[index] = null;
        mQueenNxN(index + 1, queenToLocate, curRes, result);
        for (int i = 0; i < curRes.length; i++) {
            curRes[index] = i;
            if (isValid(index, curRes)) {
                mQueenNxN(index + 1, queenToLocate - 1, curRes, result);
            }
        }
    }

    private boolean isValid(int index, Integer[] curRes) {
        if (index == 0) {
            return true;
        }
        for (int i = 0; i < index; i++) {
            if (curRes[i] == null) {
                continue;
            }
            if (curRes[i] == curRes[index]) {
                return false;
            }
            if (curRes[i] + (index - i) == curRes[index] || curRes[i] - (index - i) == curRes[index]) {
                return false;
            }
        }
        return true;
    }
}
