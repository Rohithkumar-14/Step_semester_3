
package oop.week11.assignment;

import java.util.Queue;
import java.util.LinkedList;

public class P5_FireDrillExits {

    public static int[][] exitDistance(char[][] plan) {
        int rows = plan.length;
        int cols = plan[0].length;

        int[][] distance = new int[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distance[i][j] = -1;

                if (plan[i][j] == 'E') {
                    distance[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cell = queue.remove();

            int r = cell[0];
            int c = cell[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < rows &&
                    nc >= 0 && nc < cols &&
                    plan[nr][nc] == '.' &&
                    distance[nr][nc] == -1) {

                    distance[nr][nc] = distance[r][c] + 1;
                    queue.add(new int[]{nr, nc});
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
