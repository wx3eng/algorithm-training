package Algorithm.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class MatrixWithObstacles {
    public int shortestPath(int[][] grid, int k) {
        // preprocess
        int m = grid.length;
        int n = grid[0].length;
        int[][] obstacles = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                obstacles[i][j] = -1;
            }
        }
        obstacles[0][0] = 0;
        Queue<Element> queue = new ArrayDeque<>();
        queue.add(new Element(0, 0));
        for (int i = 0; i < m * n; i++) {
            if (queue.isEmpty()) {
                return -1;
            }
            int size = queue.size();
            while(size > 0) {
                Element cur = queue.poll();
                size--;
                int curObs = obstacles[cur.row][cur.col];
                if (cur.row == m - 1 && cur.col == n - 1) {
                    return i;
                }
                // expand up
                if (cur.row - 1 >= 0 && (curObs < k || curObs == k && grid[cur.row - 1][cur.col] != 1)) {
                    expand(grid, k, obstacles, cur.row - 1, cur.col, curObs + grid[cur.row - 1][cur.col], queue);
                }
                // expand down
                if (cur.row + 1 < m && (curObs < k || curObs == k && grid[cur.row + 1][cur.col] != 1)) {
                    expand(grid, k, obstacles, cur.row + 1, cur.col, curObs + grid[cur.row + 1][cur.col], queue);
                }
                // expand left
                if (cur.col - 1 >= 0 && (curObs < k || curObs == k && grid[cur.row][cur.col - 1] != 1)) {
                    expand(grid, k, obstacles, cur.row, cur.col - 1, curObs + grid[cur.row][cur.col - 1], queue);
                }
                // expand right
                if (cur.col + 1 < n && (curObs < k || curObs == k && grid[cur.row][cur.col + 1] != 1)) {
                    expand(grid, k, obstacles, cur.row, cur.col + 1, curObs + grid[cur.row][cur.col + 1], queue);
                }
            }
        }
        return -1;
    }

    private void expand(int[][] grid, int k, int[][] obstacles, int row, int col, int obs, Queue<Element> queue) {
        int prevObs = obstacles[row][col];
        if (prevObs == -1 || prevObs > obs) {
            queue.add(new Element(row, col));
            obstacles[row][col] = obs;
        }
    }
}

class Element {
    int row;
    int col;
    Element(int row, int col) {
        this.row = row;
        this.col = col;
    }
}