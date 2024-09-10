import java.util.*;

public class FifteenPuzzle {

    static class Board {
        int[][] tiles;
        int cost;
        int emptyRow, emptyCol;

        public Board(int[][] tiles) {
            this.tiles = tiles;
            this.cost = 0;
            findEmptyTile();
        }

        private void findEmptyTile() {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (tiles[i][j] == 0) {
                        emptyRow = i;
                        emptyCol = j;
                        return;
                    }
                }
            }
        }

        public boolean isGoal(int[][] goal) {
            return Arrays.deepEquals(tiles, goal);
        }

        public ArrayList<Board> getNextMoves() {
            ArrayList<Board> nextMoves = new ArrayList<>();
            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

            for (int[] dir : directions) {
                int newRow = emptyRow + dir[0];
                int newCol = emptyCol + dir[1];
                if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4) {
                    int[][] newTiles = new int[4][4];
                    for (int i = 0; i < 4; i++) {
                        newTiles[i] = tiles[i].clone();
                    }
                    newTiles[emptyRow][emptyCol] = newTiles[newRow][newCol];
                    newTiles[newRow][newCol] = 0;
                    nextMoves.add(new Board(newTiles));
                }
            }
            return nextMoves;
        }
    }

    public static void branchAndBound(int[][] initial, int[][] goal) {
        PriorityQueue<Board> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        Board initialBoard = new Board(initial);
        pq.add(initialBoard);

        while (!pq.isEmpty()) {
            Board current = pq.poll();
            if (current.isGoal(goal)) {
                System.out.println("Solution found!");
                return;
            }
            for (Board next : current.getNextMoves()) {
                pq.add(next);
            }
        }
        System.out.println("Solution not found!");
    }

    public static void main(String[] args) {
        int[][] initial = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } };
        int[][] goal = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } };
        branchAndBound(initial, goal);
    }
}