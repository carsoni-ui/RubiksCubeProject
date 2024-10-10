import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RubiksCube {
    // Colors for every face in order: R, B, O, G, Y, W
    private char[][] front = new char[3][3]; //Red
    private char[][] back = new char[3][3]; //Orange
    private char[][] left = new char[3][3]; //Green
    private char[][] right = new char[3][3]; //Blue
    private char[][] top = new char[3][3]; //Yellow
    private char[][] bottom = new char[3][3]; //White

    // Stores the move histiry for the optimized solver
    private List<String> moveHistory = new ArrayList<>();

    // Constructor to start the cube in a solved state
    public RubiksCube() {
        initializeFace(front, 'r');
        initializeFace(back, 'o');
        initializeFace(left, 'g');
        initializeFace(right, 'b');
        initializeFace(top, 'y');
        initializeFace(bottom, 'w');
    }

    //Helper function to give each face a color
    private void initializeFace(char[][] face, char color) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face[i][j] = color;
            }
        }
    }

    //Method for displaying the cube in the correct format
    public void displayCube() {
        printFace(front);
        System.out.println();
        printFace(right);
        System.out.println();
        printFace(back);
        System.out.println();
        printFace(left);
        System.out.println();
        printFace(top);
        System.out.println();
        printFace(bottom);
    }

    // Helper function to print a face in the r|r|r formatt
    private void printFace(char[][] face) {
        for (int i = 0; i < 3; i++) {
            System.out.println(face[i][0] + "|" + face[i][1] + "|" + face[i][2]);
        }
    }

    //Rotates the face clockwise
    public void rotateFace(char[][] face) {
        char temp;

        temp = face[0][0];
        face[0][0] = face[2][0];
        face[2][0] = face[2][2];
        face[2][2] = face[0][2];
        face[0][2] = temp;
    }

    // Rotates faces counter-clockwise
    public void rotateFaceCounterClockwise(char[][] face) {
        rotateFace(face);
        rotateFace(face);
        rotateFace(face);
    }

    // Tracking History for moves that were applied
    public void applyMove(String move) {
        moveHistory.add(move); //Adds move to history
        switch (move) {
            case "u":
                rotateFace(top);
                rotateTopEdgesClockwise();
                break;
            case "u'":
                rotateFaceCounterClockwise(top);
                rotateTopEdgesCounterClockwise();
                break;
            case "d":
                rotateFace(bottom);
                rotateBottomEdgesClockwise();
                break;
            case "d'":
                rotateFaceCounterClockwise(bottom);
                rotateBottomEdgesCounterClockwise();
                break;
            case "r":
                rotateFace(right);
                rotateRightEdgesClockwise();
                break;
            case "r'":
                rotateFaceCounterClockwise(right);
                rotateRightEdgesCounterClockwise();
                break;
            case "l":
                rotateFace(left);
                rotateLeftEdgesClockwise();
                break;
            case "l'":
                rotateFaceCounterClockwise(left);
                rotateLeftEdgesCounterClockwise();
                break;
            case "f":
                rotateFace(front);
                rotateFrontEdgesClockwise();
                break;
            case "f'":
                rotateFaceCounterClockwise(front);
                rotateFrontEdgesCounterClockwise();
                break;
            case "b":
                rotateFace(back);
                rotateBackEdgesClockwise();
                break;
            case "b'":
                rotateFaceCounterClockwise(back);
                rotateBackEdgesCounterClockwise();
                break;
            default:
                System.out.println("Invalid move: " + move);
                moveHistory.remove(moveHistory.size() - 1); // Remove invalid move
        }
    }

     // Rotation helper methods for top edges
     private void rotateTopEdgesClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = front[0][i];
            front[0][i] = right[0][i];
            right[0][i] = back[0][i];
            back[0][i] = left[0][i];
            left[0][i] = temp;
        }
    }

    private void rotateTopEdgesCounterClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = front[0][i];
            front[0][i] = left[0][i];
            left[0][i] = back[0][i];
            back[0][i] = right[0][i];
            right[0][i] = temp;
        }
    }

    // Rotation helper methods for bottom edges
    private void rotateBottomEdgesClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = front[2][i];
            front[2][i] = left[2][i];
            left[2][i] = back[2][i];
            back[2][i] = right[2][i];
            right[2][i] = temp;
        }
    }

    private void rotateBottomEdgesCounterClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = front[2][i];
            front[2][i] = right[2][i];
            right[2][i] = back[2][i];
            back[2][i] = left[2][i];
            left[2][i] = temp;
        }
    }

    // Rotation helper methods for right edges
    private void rotateRightEdgesClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = top[i][2];
            top[i][2] = front[i][2];
            front[i][2] = bottom[i][2];
            bottom[i][2] = back[2 - i][0];
            back[2 - i][0] = temp;
        }
    }

    private void rotateRightEdgesCounterClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = top[i][2];
            top[i][2] = back[2 - i][0];
            back[2 - i][0] = bottom[i][2];
            bottom[i][2] = front[i][2];
            front[i][2] = temp;
        }
    }

    // Rotation helper methods for left edges
    private void rotateLeftEdgesClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = top[i][0];
            top[i][0] = back[2 - i][2];
            back[2 - i][2] = bottom[i][0];
            bottom[i][0] = front[i][0];
            front[i][0] = temp;
        }
    }

    private void rotateLeftEdgesCounterClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = top[i][0];
            top[i][0] = front[i][0];
            front[i][0] = bottom[i][0];
            bottom[i][0] = back[2 - i][2];
            back[2 - i][2] = temp;
        }
    }

    // Rotation helper methods for front edges
    private void rotateFrontEdgesClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = top[2][i];
            top[2][i] = left[2 - i][2];
            left[2 - i][2] = bottom[0][2 - i];
            bottom[0][2 - i] = right[i][0];
            right[i][0] = temp;
        }
    }

    private void rotateFrontEdgesCounterClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = top[2][i];
            top[2][i] = right[i][0];
            right[i][0] = bottom[0][2 - i];
            bottom[0][2 - i] = left[2 - i][2];
            left[2 - i][2] = temp;
        }
    }

    // Rotation helper methods for back edges
    private void rotateBackEdgesClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = top[0][i];
            top[0][i] = right[i][2];
            right[i][2] = bottom[2][2 - i];
            bottom[2][2 - i] = left[2 - i][0];
            left[2 - i][0] = temp;
        }
    }

    private void rotateBackEdgesCounterClockwise() {
        for (int i = 0; i < 3; i++) {
            char temp = top[0][i];
            top[0][i] = left[2 - i][0];
            left[2 - i][0] = bottom[2][2 - i];
            bottom[2][2 - i] = right[i][2];
            right[i][2] = temp;
        }
    }

    // Randomizer that scrambles the cube with random moves
    public void randomizeCube(int numberOfMoves) {
        String[] possibleMoves = {"u", "u'", "d", "d'", "r", "r'", "l", "l'", "f", "f'", "b", "b'"};
        Random random = new Random();
        for (int i = 0; i < numberOfMoves; i++) {
            String randomMove = possibleMoves[random.nextInt(possibleMoves.length)];
            applyMove(randomMove);
        }
    }

    // Optimized Solver: Return simplified move list
    public List<String> getOptimizedSolution() {
        List<String> optimizedMoves = new ArrayList<>(moveHistory);

        boolean optimized = true;
        while (optimized) {
            optimized = false;
            for (int i = 0; i < optimizedMoves.size() - 1; i++) {
                String move1 = optimizedMoves.get(i);
                String move2 = optimizedMoves.get(i + 1);

                // Remove canceling moves (e.g., "r" followed by "r'")
                if (areInverseMoves(move1, move2)) {
                    optimizedMoves.remove(i);
                    optimizedMoves.remove(i);
                    optimized = true;
                    break;
                }

                // Combine moves (e.g., "r" and "r" becomes "r2")
                if (move1.equals(move2)) {
                    optimizedMoves.set(i, move1.charAt(0) + "2");
                    optimizedMoves.remove(i + 1);
                    optimized = true;
                    break;
                }

                // Simplify triple moves (e.g., "r", "r", "r" becomes "r'")
                if (i < optimizedMoves.size() - 2 &&
                    move1.equals(move2) && move2.equals(optimizedMoves.get(i + 2))) {
                    String inverseMove = getInverseMove(move1);
                    optimizedMoves.set(i, inverseMove);
                    optimizedMoves.remove(i + 1);
                    optimizedMoves.remove(i + 1);
                    optimized = true;
                    break;
                }
            }
        }

        return reverseMoves(optimizedMoves);
    }

    // Helper method to determine if two moves are inverses
    private boolean areInverseMoves(String move1, String move2) {
        return (move1 + "'").equals(move2) || (move2 + "'").equals(move1);
    }

    // Helper method to get the inverse of a move
    private String getInverseMove(String move) {
        if (move.endsWith("'")) {
            return move.substring(0, move.length() - 1);
        } else {
            return move + "'";
        }
    }

    // Reverse the moves to get the solving sequence
    private List<String> reverseMoves(List<String> moves) {
        List<String> reversedMoves = new ArrayList<>();
        for (int i = moves.size() - 1; i >= 0; i--) {
            String move = moves.get(i);
            reversedMoves.add(getInverseMove(move));
        }
        return reversedMoves;
    }

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube();

        if (args.length > 0) {
            // Command line mode
            for (String move : args) {
                cube.applyMove(move);
            }
            cube.displayCube();
        } else {
            // Interactive mode
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to Rubik's Cube Simulator!");
            System.out.println("Commands: u, d, r, l, f, b, u', d', r', l', f', b'");
            System.out.println("Type 'random' to scramble, 'solve' to see solution, or 'q' to quit.");

            while (true) {
                System.out.println("\nCurrent Cube State:");
                cube.displayCube();

                System.out.print("\nEnter moves (e.g., 'u r f'): ");
                String input = scanner.nextLine().trim();

                if (input.equals("q")) {
                    break;
                } else if (input.equals("random")) {
                    System.out.print("How many random moves? ");
                    int numMoves = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    cube.randomizeCube(numMoves);
                    System.out.println("Cube scrambled with " + numMoves + " random moves.");
                } else if (input.equals("solve")) {
                    List<String> solution = cube.getOptimizedSolution();
                    System.out.println("Optimized solution: " + solution);
                } else {
                    String[] moves = input.split("\\s+");
                    for (String move : moves) {
                        cube.applyMove(move);
                    }
                }
            }

            scanner.close();
        }
    }
}

