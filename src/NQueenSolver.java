import java.util.* ;

class Solution{
    boolean[] occupiedColumn;
    boolean[] occupiedLeftDiagonal;
    boolean[] occupiedRightDiagonal;
    boolean[][] board;
    int boardSize;
    int solutionCount;
    Solution(int boardSize){
        this.boardSize = boardSize;
        occupiedColumn = new boolean[boardSize];
        occupiedLeftDiagonal = new boolean[2*boardSize-1];
        occupiedRightDiagonal = new boolean[2*boardSize-1];
        board = new boolean[boardSize][boardSize];
        solutionCount = 0;
    }

    void findSolution(int row){
        if(row == boardSize){
            solutionCount++;
            printSolution();
            return;
        }
        for(int i=0;i<boardSize;i++){
            if(!occupiedLeftDiagonal[row - i + boardSize - 1] && !occupiedRightDiagonal[row+i] && !occupiedColumn[i]){
                occupiedRightDiagonal[row+i] = true;
                occupiedLeftDiagonal[row - i + boardSize - 1] = true;
                occupiedColumn[i] = true;
                board[row][i] = true;

                findSolution(row+1);

                occupiedRightDiagonal[row+i] = false;
                occupiedLeftDiagonal[row - i + boardSize - 1] = false;
                occupiedColumn[i] = false;
                board[row][i] = false;
            }
        }
    }
    void printSolution(){
        System.out.println("\nSolution "+solutionCount+" :");
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(board[i][j])
                    System.out.print(" Q ");
                else
                    System.out.print(" . ");
            }
            System.out.println();
        }
    }
}
public class NQueenSolver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of queens:");
        int boardSize = sc.nextInt();
        Solution solution = new Solution(boardSize);
        solution.findSolution(0);
        System.out.println("\nNumber of solutions: "+solution.solutionCount);
    }
}
