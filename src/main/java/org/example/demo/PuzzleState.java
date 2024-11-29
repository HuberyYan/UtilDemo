package org.example.demo;

import java.util.*;

/**
 * @author HuberyYan
 * @data 2024/7/10
 * @apiNote
 */
public class PuzzleState {
    // 使用一维数组表示3x3拼图状态
    int[] board;
    // 前一个状态，用于回溯路径
    PuzzleState prev;

    PuzzleState(int[] board, PuzzleState prev) {
        this.board = board.clone();
        this.prev = prev;
    }

    static List<String> moves = new ArrayList<>();

    // 检查是否达到目标状态
    boolean isGoal(int[] goal) {
        return Arrays.equals(board, goal);
    }

    // 生成所有可能的下一个状态（移动）
    List<PuzzleState> generateNextStates() {
        List<PuzzleState> nextStates = new ArrayList<>();
        int zeroIndex = findZeroIndex();
        int row = zeroIndex / 3;
        int col = zeroIndex % 3;

        // 定义上下左右的移动
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            // 检查新位置是否在拼图范围内
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                int newZeroIndex = newRow * 3 + newCol;
                int[] newBoard = board.clone();
                swap(newBoard, zeroIndex, newZeroIndex);
                nextStates.add(new PuzzleState(newBoard, this));
            }
        }

        return nextStates;
    }

    // 交换数组中两个元素的位置
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // 找到空白（0）格子的索引
    private int findZeroIndex() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                return i;
            }
        }
        return -1; // 如果没有找到空白格子，返回-1（但在这个问题中不应该发生）
    }

    // 辅助方法：打印路径
    void printPath() {
        if (prev == null) {
            // 一维数组
            //System.out.println(Arrays.toString(board));
            // 转二维数组
            //oneToTwoDimensionalArray(board);
            //System.out.println("-----------------");
            moves.add(Arrays.toString(board).replace("[", "").replace("]", "").replace(" ", ""));
            return;
        }
        prev.printPath();
        //System.out.println(Arrays.toString(board));
        //oneToTwoDimensionalArray(board);
        //System.out.println("-----------------");
        moves.add(Arrays.toString(board).replace("[", "").replace("]", "").replace(" ", ""));
    }

    // 一维数组转二维数组
    public static int[][] oneToTwoDimensionalArray(int[] oneDimensionalArray) {
        // 定义二维数组的维度
        int rows = 3;
        int columns = 3;
        // 初始化二维数组
        int[][] twoDimensionalArray = new int[rows][columns];
        // 将一维数组转换为二维数组
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                twoDimensionalArray[i][j] = oneDimensionalArray[i * columns + j];
            }
        }
        // 打印二维数组
        //for (int i = 0; i < rows; i++) {
        //    for (int j = 0; j < columns; j++) {
        //        System.out.print(twoDimensionalArray[i][j] + " ");
        //    }
        //    System.out.println();
        //}
        return twoDimensionalArray;
    }

    public static void findZeroMovement(int[][] initialState, int[][] targetState) {
        // 查找初始状态和目标状态中 0 的位置
        int initialZeroRow = -1;
        int initialZeroColumn = -1;
        int targetZeroRow = -1;
        int targetZeroColumn = -1;

        for (int i = 0; i < initialState.length; i++) {
            for (int j = 0; j < initialState[i].length; j++) {
                if (initialState[i][j] == 0) {
                    initialZeroRow = i;
                    initialZeroColumn = j;
                }
                if (targetState[i][j] == 0) {
                    targetZeroRow = i;
                    targetZeroColumn = j;
                }
            }
        }

        // 输出 0 的移动方向
        if (initialZeroRow != -1 && initialZeroColumn != -1 && targetZeroRow != -1 && targetZeroColumn != -1) {
            if (initialZeroRow < targetZeroRow) {
                System.out.print("下");
            } else if (initialZeroRow > targetZeroRow) {
                System.out.print("上");
            } else if (initialZeroColumn < targetZeroColumn) {
                System.out.print("右");
            } else if (initialZeroColumn > targetZeroColumn) {
                System.out.print("左");
            } else {
                System.out.println("0 没有移动");
            }
        } else {
            System.out.println("初始状态或目标状态中没有 0");
        }
    }

    public static void solve(int[] initialBoard, int[] goalBoard) {
        PuzzleState initialState = new PuzzleState(initialBoard, null);
        Queue<PuzzleState> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(initialState);
        visited.add(Arrays.toString(initialState.board));

        while (!queue.isEmpty()) {
            PuzzleState currentState = queue.poll();

            if (currentState.isGoal(goalBoard)) {
                currentState.printPath();
                return;
            }

            for (PuzzleState nextState : currentState.generateNextStates()) {
                String nextStateStr = Arrays.toString(nextState.board);
                if (!visited.contains(nextStateStr)) {
                    visited.add(nextStateStr);
                    queue.offer(nextState);
                }
            }
        }

        System.out.println("No solution found.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入初始九宫格顺序(例如：123607845)：");
        String next = scanner.nextLine();
        int[] initialBoard = Arrays.stream(next.split("")).mapToInt(Integer::parseInt).toArray();
//        int[] initialBoard = {1, 2, 3, 6, 0, 7, 8, 4, 5}; // 初始状态
        int[] goalBoard = {1, 2, 3, 4, 5, 6, 7, 8, 0}; // 目标状态

        solve(initialBoard, goalBoard);
        for (int i = 0; i < moves.size() - 1; i++) {
            int[][] pre = oneToTwoDimensionalArray(Arrays.stream(moves.get(i).split(",")).mapToInt(Integer::parseInt).toArray());
            int[][] cur = oneToTwoDimensionalArray(Arrays.stream(moves.get(i + 1).split(",")).mapToInt(Integer::parseInt).toArray());
            findZeroMovement(pre, cur);
            if ((i + 1) % 5 == 0) {
                System.out.println("");
                System.out.println("-----------------");
            }
        }
    }

}
