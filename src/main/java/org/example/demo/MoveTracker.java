package org.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HuberyYan
 * @data 2024/9/23
 * @apiNote
 */
public class MoveTracker {
    private int[][] initialMap; // 初始二维数组
    private int[][] targetMap; // 目标二维数组
    private int rows;
    private int cols;

    public MoveTracker(int[][] initialMap, int[][] targetMap) {
        this.initialMap = initialMap;
        this.targetMap = targetMap;
        this.rows = initialMap.length;
        this.cols = initialMap[0].length;
    }

    // 查找0的位置
    private int[] findZeroPosition(int[][] map) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // 如果找不到0，返回null
    }

    // 移动函数
    private boolean move(int[][] map, int x, int y, char direction) {
        int newX = x;
        int newY = y;

        switch (direction) {
            case 'U':
                if (x > 0) newX--;
                break;
            case 'D':
                if (x < rows - 1) newX++;
                break;
            case 'L':
                if (y > 0) newY--;
                break;
            case 'R':
                if (y < cols - 1) newY++;
                break;
            default:
                return false;
        }

        if (newX == x && newY == y) {
            return false; // 无法移动
        }

        int temp = map[newX][newY];
        map[newX][newY] = 0;
        map[x][y] = temp;

        return true;
    }

    // 生成移动轨迹
    private List<char[]> generateMoves(int[][] initialMap, int[][] targetMap) {
        List<char[]> moves = new ArrayList<>();
        int[] zeroPosition = findZeroPosition(initialMap);

        while (!Arrays.deepEquals(initialMap, targetMap)) {
            int x = zeroPosition[0];
            int y = zeroPosition[1];

            // 尝试四个方向移动
            if (x > 0 && initialMap[x - 1][y] != 0) {
                move(initialMap, x, y, 'U');
                moves.add(new char[]{'U'});
            } else if (x < rows - 1 && initialMap[x + 1][y] != 0) {
                move(initialMap, x, y, 'D');
                moves.add(new char[]{'D'});
            } else if (y > 0 && initialMap[x][y - 1] != 0) {
                move(initialMap, x, y, 'L');
                moves.add(new char[]{'L'});
            } else if (y < cols - 1 && initialMap[x][y + 1] != 0) {
                move(initialMap, x, y, 'R');
                moves.add(new char[]{'R'});
            }

            zeroPosition = findZeroPosition(initialMap);
        }

        return moves;
    }

    // 打印移动轨迹
    public void printMoves(List<char[]> moves) {
        System.out.println("移动轨迹:");
        for (char[] move : moves) {
            System.out.println(new String(move));
        }
    }

    // 打印数组
    public void printArray(int[][] array) {
        for (int[] row : array) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] initialMap = {
                {6, 8, 4},
                {2, 3, 5},
                {0, 7, 1}
        };

        int[][] targetMap = {
                {6, 8, 4},
                {0, 3, 5},
                {2, 7, 1}
        };

        MoveTracker tracker = new MoveTracker(initialMap, targetMap);

        List<char[]> moves = tracker.generateMoves(initialMap, targetMap);
        tracker.printMoves(moves);

        //System.out.println("初始二维数组数组:");
        //tracker.printArray(initialMap);
        //
        //System.out.println("目标数组:");
        //tracker.printArray(targetMap);
    }
}
