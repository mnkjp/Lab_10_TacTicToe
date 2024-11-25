import java.util.Scanner;

public class TicTacToe {


    static final int ROWS = 3;
    static final int COLS = 3;
    static String[][] board = new String[ROWS][COLS];
    static boolean done = false;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String player = "X";
        int row, col;
        int moveCounter = 0;

        do {
            clearBoard();
            moveCounter = 0;
            done = false;
            player = "X";


            do {

                display();
                System.out.println("Player " + player + ", it's your turn!");
                row = SafeInput.getRangedInt(in, "Enter your row: ", 1, 3) - 1; // Get row (1-3), convert to 0-2
                col = SafeInput.getRangedInt(in, "Enter your column: ", 1, 3) - 1; // Get column (1-3), convert to 0-2

                while (!isValidMove(row, col)) {
                    System.out.println("Invalid move. Please try again.");
                    row = SafeInput.getRangedInt(in, "Enter your row: ", 1, 3) - 1; // Get row (1-3), convert to 0-2
                    col = SafeInput.getRangedInt(in, "Enter your column: ", 1, 3) - 1; // Get column (1-3), convert to 0-2
                }

                board[row][col] = player;
                moveCounter++;

                if (isWin(player)) {
                    display();
                    System.out.println(player + " wins!");
                    done = true;
                    break;
                }

                if (moveCounter == ROWS * COLS || isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    done = true;
                    break;
                }

                player = (player.equals("X")) ? "O" : "X";

            } while (!done);

            boolean playAgain = SafeInput.getYNConfirm(in, "Do you want to play again?");
            if (!playAgain) {
                System.out.println("Thanks for playing!");
                done = true;
            }

        } while (!done);
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j]);
                if (j < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println("-----");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {

        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }

        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }

        return !isRowWin("X") && !isColWin("X") && !isDiagnalWin("X") && !isRowWin("O") && !isColWin("O") && !isDiagnalWin("O");
    }
}