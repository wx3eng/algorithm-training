package Algorithm.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidPermutationOfParentheses {
    public List<String> validParentheses(int n) {
        char[] array = new char[2 * n];
        for (int i = 0; i < n; i++) {
            array[i] = '(';
            array[i + n] = ')';
        }
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        validParentheses(array, 0, 0, 0, builder, result);
        return result;
    }

    private void validParentheses(char[] array, int left, int right, int index, StringBuilder builder, List<String> result) {
        if (index == array.length) {
            result.add(builder.toString());
        }
        boolean triedLeft = false;
        boolean triedRight = false;
        for (int i = index; i < array.length; i++) {
            if (array[i] == '(' && triedLeft == false) {
                swap(array, index, i);
                builder.append('(');
                validParentheses(array, left + 1, right, index + 1, builder, result);
                swap(array, index, i);
                builder.deleteCharAt(builder.length() - 1);
                triedLeft = true;
            } else if (array[i] == ')' && right < left && triedRight == false) {
                swap(array, index, i);
                builder.append(')');
                validParentheses(array, left, right + 1, index + 1, builder, result);
                swap(array, index, i);
                builder.deleteCharAt(builder.length() - 1);
                triedRight = true;
            }
        }
    }

    private void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        ValidPermutationOfParentheses result = new ValidPermutationOfParentheses();
        System.out.println(Arrays.toString(result.validParentheses(3).toArray()));
    }
}
