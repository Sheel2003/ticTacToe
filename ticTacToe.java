package ticTacToe;

import java.util.Scanner;

public class Game {

	static Scanner scn = new Scanner(System.in);
	static String[][] board = new String[3][3];
	static String po = "X", pt = "O";
	static String name1 = "", name2 = "";
	static boolean winner = false;
	static int score1 = 0, score2 = 0;
	
	public static void main(String[] args) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				board[i][j] = " ";
			}
		}
	
		System.out.println("Enter Player one's name: ");
		name1 = scn.nextLine();
		System.out.println("Enter Player two's name: ");
		name2 = scn.nextLine();
		
		System.out.println("Welcome " + name1 + " and " + name2 + " to the Tic Tac Toe game!\n");
		
		System.out.println(name1 + ", please select your symbol");
		po = scn.nextLine();
		if(po.equals("X")) {
			pt = "O";
			System.out.println(name1 + ", your symbol is " + po);
			System.out.println(name2 + ", your symbol is " + pt+"\n");
		} else if(po.equals("O")) {
			pt = "X";
			System.out.println(name1 + ", your symbol is " + po);
			System.out.println(name2 + ", your symbol is " + pt+"\n");
		}
		
		int num = 0;
		
		while(num >= 0) {
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[0].length; j++) {
					board[i][j] = " ";
				}
			}
			printBoard();
			winner = false;
			while(winner == false && isFull() == false) {
				System.out.println("Its " + name1 + "'s turn!\n");
				playerOne();
				winner = isWinner(po);
				if(isWinner(po)) {
					System.out.println("Congrats " + name1 + ", YOU ARE THE WINNER!\n");
					score1+=2;
					System.out.println("Your score is " + score1 + "\n");
					break;
				} 
				if(isFull()) {
					break;
				}
				System.out.println("Its " + name2 + "'s turn!\n");
				playerTwo();
				winner = isWinner(pt);
				if(isWinner(pt)) {
					System.out.println("Congrats " + name2 + ", YOU ARE THE WINNER!\n");
					score2+=2;
					System.out.println("Your score is " + score2 + "\n");
					break;
				}
				if(isFull()) {
					break;
				}
			}
			if(isWinner(po) == false && isWinner(pt) == false) {
				System.out.println("ITS A DRAW!\n");
				score1++;
				score2++;
				System.out.println("You both get one point\n");
			}
			System.out.println("Would you like to play again?\n" +
					   "0. YES\n-1. NO\n");
			num = scn.nextInt();
		}
		if(score1 > score2) {
			System.out.println(name1 + ", you have won the most of the games!");
		} else {
			System.out.println(name1 + ", you have won the most of the games!");
		}
	}
	
	
	public static void printBoard() {
		System.out.println("     0     1     2   ");
		System.out.println("  |-----|-----|-----|");
		System.out.println("0 |  " + board[0][0] + "  |  " + board[0][1] + "  |  " + board[0][2] + "  |");
		System.out.println("  |-----|-----|-----|");
		System.out.println("1 |  " + board[1][0] + "  |  " + board[1][1] + "  |  " + board[1][2] + "  |");
		System.out.println("  |-----|-----|-----|");
		System.out.println("2 |  " + board[2][0] + "  |  " + board[2][1] + "  |  " + board[2][2] + "  |");
		System.out.println("  |-----|-----|-----|");
	}
	
	
	public static void playerOne() {
		System.out.println(name1+", please enter the coordinate where you would like to drop your symbol");
		int x = scn.nextInt();
		int y = scn.nextInt();
		if((x < board.length && y < board[0].length)) {
			board[x][y] = po;
			printBoard();
		} else {
			System.out.println("\nPlease select points inside the board");
			x = scn.nextInt();
			y = scn.nextInt();
			board[x][y] = po;
			printBoard();
		}
	}
	
	
	public static void playerTwo() {
		System.out.println(name2+", please enter the coordinate where you would like to drop your symbol");
		int x = scn.nextInt();
		int y = scn.nextInt();
		if((x < board.length && y < board[0].length)) {
			board[x][y] = pt;
			printBoard();
		} else {
			System.out.println("\nPlease select points inside the board");
			x = scn.nextInt();
			y = scn.nextInt();
			board[x][y] = po;
			printBoard();
		}
	}
	
	
	public static boolean isWinner(String sym) {
		int i, j;
		for (i = 0; i < board.length && !winner; i++) {
		      for (j = 0; j < board[0].length; j++) {
		        if (board[i][j] != sym)
		          break;
		      }
		      if (j == board[0].length)
		        winner = true;
		    }

		    // Check win by a column
		    for (j = 0; j < board[0].length && !winner; j++) {
		      for (i = 0; i < board.length; i++) {
		        if (board[i][j] != sym)
		          break;
		      }
		      if (i == board.length)
		        winner = true;
		    }

		    // Check win by a diagonal (1)
		    if (!winner) {
		      for (i = 0; i < board.length; i++) {
		        if (board[i][i] != sym)
		          break;
		      }
		      if (i == board.length)
		        winner = true;
		    }

		    // Check win by a diagonal (2)
		    if (!winner) {
		      for (i = 0; i < board.length; i++) {
		        if (board[i][board.length - 1 - i] != sym)
		          break;
		      }
		      if (i == board.length)
		        winner = true;
		    }

		    // Finally return win
		    return winner;
	}
	
	public static boolean isFull() {
		int c = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(!(board[i][j].equals(" "))) {
					c++;
				}
			}
		}
		if(c == 9) {
			return true;
		}
		return false;
	}
	
	public static int tossTime() {
		int random = (int)((Math.random()*2)+1);
		return random;
	}
}

