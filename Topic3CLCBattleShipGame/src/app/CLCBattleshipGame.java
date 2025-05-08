package app;

import java.util.*;

public class CLCBattleshipGame {
    private static final int SIZE = 10;
    private static final char EMPTY = '-';
    private static final char BODY = 't'; // Body of Christ ships shown as 'T' for Temple or Truth
    private static final char PRINCIPALITY = 'P';
    private static final char HIT = 'X';
    private static final char MISS = 'O';

    private static char[][] bodyBoard = new char[SIZE][SIZE];
    private static char[][] principalityBoard = new char[SIZE][SIZE];
    private static char[][] bodyView = new char[SIZE][SIZE];
    private static Random rand = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to 'The Body of Christ vs Principalities' - Stand firm in the armor of God.");
        initBoard(bodyBoard);
        initBoard(principalityBoard);
        initBoard(bodyView);

        System.out.println("Deploy your spiritual defenses:");
        placeBodyShips();
        placePrincipalityStrongholds();

        boolean bodyTurn = true;

        while (true) {
            if (bodyTurn) {
                System.out.println("Your move (Body of Christ):");
                printBoardWithShips(bodyBoard);
                printBoard(bodyView);
                if (!bodyAttack()) {
                    bodyTurn = false;
                }
            } else {
                System.out.println("The principalities strike...");
                if (!principalityAttack()) {
                    bodyTurn = true;
                }
            }
            if (isGameOver(principalityBoard)) {
                System.out.println("Victory! The principalities have been overcome.");
                break;
            } else if (isGameOver(bodyBoard)) {
                System.out.println("You've been overwhelmed... but remember, the battle belongs to the Lord.");
                break;
            }
        }
    }

    private static void initBoard(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(board[i], EMPTY);
        }
    }

    private static void printBoard(char[][] board) {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printBoardWithShips(char[][] board) {
        System.out.println("-- Your Board (t = Body of Christ units) --");
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                char c = board[i][j];
                if (c == BODY) System.out.print("t ");
                else System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    private static void placeBodyShips() {
        System.out.println("Placing Faith Fortress (2x2 square)...");
        placeShipManual(bodyBoard, 2, 2, BODY);
        System.out.println("Placing Prayer Chain (3 diagonal)...");
        placeShipManual(bodyBoard, 3, 1, BODY);
        System.out.println("Placing Sword of Truth (3 straight)...");
        placeShipManual(bodyBoard, 3, 3, BODY);
    }

    private static void placePrincipalityStrongholds() {
        System.out.println("Deploying Fear Fortress (2x2 square)...");
        placeShipRandom(principalityBoard, 2, 2, PRINCIPALITY);
        System.out.println("Deploying Deception Cloud (3 diagonal)...");
        placeShipRandom(principalityBoard, 3, 1, PRINCIPALITY);
        System.out.println("Deploying Division Line (3 straight)...");
        placeShipRandom(principalityBoard, 3, 3, PRINCIPALITY);
    }

    private static void placeShipManual(char[][] board, int size, int type, char symbol) {
        while (true) {
            System.out.print("Enter row and column (e.g., 0 0): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (type == 1) {
                if (canPlaceDiagonal(board, row, col, size)) {
                    for (int i = 0; i < size; i++) {
                        board[row + i][col + i] = symbol;
                    }
                    break;
                }
            } else if (type == 2) {
                if (row + 1 < SIZE && col + 1 < SIZE && board[row][col] == EMPTY && board[row+1][col] == EMPTY && board[row][col+1] == EMPTY && board[row+1][col+1] == EMPTY) {
                    board[row][col] = board[row+1][col] = board[row][col+1] = board[row+1][col+1] = symbol;
                    break;
                }
            } else if (type == 3) {
                if (col + size <= SIZE && board[row][col] == EMPTY && board[row][col+1] == EMPTY && board[row][col+2] == EMPTY) {
                    for (int i = 0; i < size; i++) {
                        board[row][col+i] = symbol;
                    }
                    break;
                }
            }
            System.out.println("Invalid location. Try again.");
        }
    }

    private static void placeShipRandom(char[][] board, int size, int type, char symbol) {
        boolean placed = false;
        while (!placed) {
            int row = rand.nextInt(SIZE - size);
            int col = rand.nextInt(SIZE - size);
            if (type == 1 && canPlaceDiagonal(board, row, col, size)) {
                for (int i = 0; i < size; i++) {
                    board[row + i][col + i] = symbol;
                }
                placed = true;
            } else if (type == 2 && row + 1 < SIZE && col + 1 < SIZE &&
                board[row][col] == EMPTY && board[row+1][col] == EMPTY && board[row][col+1] == EMPTY && board[row+1][col+1] == EMPTY) {
                board[row][col] = board[row+1][col] = board[row][col+1] = board[row+1][col+1] = symbol;
                placed = true;
            } else if (type == 3 && col + size <= SIZE) {
                if (board[row][col] == EMPTY && board[row][col+1] == EMPTY && board[row][col+2] == EMPTY) {
                    for (int i = 0; i < size; i++) {
                        board[row][col+i] = symbol;
                    }
                    placed = true;
                }
            }
        }
    }

    private static boolean canPlaceDiagonal(char[][] board, int row, int col, int size) {
        if (row + size > SIZE || col + size > SIZE) return false;
        for (int i = 0; i < size; i++) {
            if (board[row + i][col + i] != EMPTY) return false;
        }
        return true;
    }

    private static boolean bodyAttack() {
        while (true) {
            System.out.print("Enter coordinates to shine light (e.g., 3 4): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || bodyView[row][col] != EMPTY) {
                System.out.println("Invalid or already tried. Choose another.");
                continue;
            }
            if (principalityBoard[row][col] == PRINCIPALITY) {
                System.out.println("Stronghold weakened!");
                bodyView[row][col] = HIT;
                principalityBoard[row][col] = HIT;
                return true;
            } else {
                System.out.println("No darkness here.");
                bodyView[row][col] = MISS;
                return false;
            }
        }
    }

    private static boolean principalityAttack() {
        while (true) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);
            if (bodyBoard[row][col] == HIT || bodyBoard[row][col] == MISS) continue;
            System.out.println("Darkness strikes at: " + row + ", " + col);
            if (bodyBoard[row][col] == BODY) {
                System.out.println("The Body is under fire!");
                bodyBoard[row][col] = HIT;
                return true;
            } else {
                System.out.println("The armor of God deflects the attack.");
                bodyBoard[row][col] = MISS;
                return false;
            }
        }
    }

    private static boolean isGameOver(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == BODY || board[i][j] == PRINCIPALITY) return false;
            }
        }
        return true;
    }
}
