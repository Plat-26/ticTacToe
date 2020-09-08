package tictactoe;
import java.util.*;

public class TicTacToe {
    static Scanner scanner = new Scanner(System.in);
    static char[][] board = new char[4][4];
	static GameState gs;
    
   /* static void getInput() {
        System.out.print("Enter cells:");
        String format = scanner.nextLine();
        int k = 0;
        for (int i = 3; i > 0; i--) {
            for (int j = 1; j < 4; j++) {
                board[j][i] = format.charAt(k);
                k++;
            }
        }
    } */
    
    static void printArray() {
            System.out.println("---------");
            System.out.print("| ");
            System.out.println(board[1][3] + " " + board[2][3] + " " + board[3][3] + " " + "|");
            System.out.print("| ");
            System.out.println(board[1][2] + " " + board[2][2] + " " + board[3][2] + " " + "|");
            System.out.print("| ");
            System.out.println(board[1][1] + " " + board[2][1] + " " + board[3][1] + " " + "|");
            System.out.println("---------");
    }
    
    static void checkPlay() {
        boolean check = true; int a; int b;
        do {
            System.out.print("Enter the coordinates:");
            String[] cord = scanner.nextLine().split(" ");
            if (cord[0].length() >  1 || cord[1].length() >  1) {
                System.out.println("You should enter numbers!");
                continue;
            }
            else {
                a = Integer.parseInt(cord[0]);
                b  = Integer.parseInt(cord[1]);
            }
            if (a > 3 || a < 1 || b > 3 || b < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (board[a][b] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            else {
                board[a][b] = 'X';
                printArray();
                check = false;
                break;
            }
                 
        } 
        while (check);
    } 
    
    public static void main(String[] args) {

        getInput();
        printArray();
        checkPlay();
		gs = analyzeField();
		System.out.println(gs);
        
        
      char[][] array = new char[3][3];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = format.charAt(k);
                k++;
            }
        } 
    
       char tic = 'X';
        char tac = 'O';
        char toe = '_';
        
        int equal = 0;
        int emptyCells = 0;
        int oWins = 0;
        int xWins = 0;
        int nX = 0;
        int nO = 0;
        int sameDiag = 0;
        int inCol = 0;
        int xTotal = 0;
        int oTotal = 0;
        int diff;
        
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                
                //check for empty cells
                if (array[i][j] == '_') {
                    emptyCells++;
                }
                //check for X in a row   
                if (array[i][j] == 'X')  {
                    nX++;
                    xTotal++;
                }
                //check for O in a row, coloumn and diagonals
                if (array[i][j] == 'O') {
                    nO++;
                    oTotal++;
                }
                //if only three in a row
                if (nX == 3) {
                    xWins++;
                }
                //if only os in a row
                if (nO == 3) {
                    oWins++;
                }
            }
            //reset the row counters
            nO = 0;
            nX = 0;  
        }
        
        //move through coloumns
        for  (int j = 0; j < 3; j++) {
            for (int i = 0; i < 2; i++) {
                
                if (array[i][j] == array[i + 1][j]) {
                    inCol++;
                    
                }   
                if (inCol == 2 && array[i][j] == 'X') {
                    xWins++;
                    break;
                }
                if (inCol == 2 && array[i][j] == 'O') {
                    oWins++;
                    break;
                }     
            }
            inCol = 0;       
        }
        
        //move through the diagonal forward
        for (int i = 0, j= 0; i < 2 && j < 2; i++, j++) {
            
            if (array[i][j] == array[i + 1][j + 1]) {
                sameDiag++;
            }
            if (sameDiag == 2 && array[i][j] == 'X') {
                    xWins++;
                    break;
            }
            if (sameDiag == 2 && array[i][j] == 'O') {
                    oWins++;
                    break;
            }
        }
        sameDiag = 0;
        
        //move through diagonal backwards
        for (int i = 0, j = 2; i < 2 && j < 3; i++, j--) {
            if (array[i][j] == array[i + 1][j - 1]) {
                sameDiag++;
            }
            if (sameDiag == 2 && array[i][j] == 'X') {
                    xWins++;
                    break;
            }
            if (sameDiag == 2 && array[i][j] == 'O') {
                    oWins++;
                    break;
            }
        }
        
        //analyse the field
        
        public static GameState analyzeField() {
            diff = Math.abs(xTotal - oTotal); //get input difference
        
        while (emptyCells == 0) { 
            if (xWins > 0 && oWins > 0 ) {
                return GameState.IMPOSSIBLE;
            }
            if (xWins == 0 && oWins == 0) {
                return GameState.DRAW;
            }
            break;  
        }
         
        while (emptyCells > 0) { 
            if (xWins == 0 && oWins == 0 && diff < 2) {
                return GameState.NOT_FINISHED;
                //break;
            }
            if (xWins == 0 && oWins == 0 && diff > 1) {
                return GameState.IMPOSSIBLE;
                //break;
            } 
            if (xWins > 0 && oWins > 0 ) {
                return GameState.IMPOSSIBLE;
                //break;
            }
            break;
        }
        
        if (oWins < 1) {
            if (xWins == 1) { 
                return GameState.X_WINS;
            }    
        }
        if (xWins < 1) {
            if (oWins == 1) { 
               return GameState.O_WINS;
            } 
        }
        if (xWins > 1 && oWins > 1) {
            return GameState.IMPOSSIBLE;
            //break;
        }
        }
        
        
        enum GameState {
            NOT_FINISHED("Game not finished"),
            DRAW("Draw"),
            X_WINS("X wins"),
            O_WINS("O wins"),
            IMPOSSIBLE("Impossible");
            
            String state;
            
            GameState(String state) {
                this.state = state;
            }
        } 
           
    }
}

