package Algorithm.Recursion;

public class Fibonacci {
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
//    TC: O(2^n)
//    SC: O(n)
//    bad method!!

    public static int fibBetter(int n) {
        int i = 0;
        int j = 1;
        if (n == 0) {
            return i;
        }
        if (n == 1) {
            return j;
        }
        int result = 0;
        for ( int k = 1; k < n; k++) {
            result = i + j;
            i = j;
            j = result;
        }
        return result;
    }
//    TC: O(n)
//    SC: O(1)
//    good method

    public static void main(String[] args) {
        System.out.println(Fibonacci.fib(7));
        System.out.println(Fibonacci.fibBetter(7));
    }
}
