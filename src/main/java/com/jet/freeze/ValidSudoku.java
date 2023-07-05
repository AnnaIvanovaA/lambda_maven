package com.jet.freeze;

import java.util.Arrays;
import java.util.HashSet;

public class ValidSudoku {

    public static void main(String[] args) {
        ValidSudoku validSudoku = new ValidSudoku();
        char[][] board = new char[][]{
                {'2', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        validSudoku.isValidSudoku(board);
    }

    public boolean isValidSudoku(char[][] board) {
        char[] section = new char[9];
        int counter = 0;

        for (int i = 0; i < 9; i++) {
            char[] row = board[i];
            if (!isSectionValid(row)) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                section[j] = board[j][i];
            }
            if (!isSectionValid(section)) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    int currRow = row + (i * 3);
                    int currCol = col;
                    section[counter] = board[currRow][currCol];
                    counter++;
                }
                isSectionValid(section);
                counter = 0;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    int currRow = row + (i * 3);
                    int currCol = col;
                    section[counter] = board[currCol][currRow];
                    counter++;
                }
                isSectionValid(section);
                counter = 0;
            }
        }

        return true;
    }

    public boolean isSectionValid(char[] section) {
        HashSet<Character> validation = new HashSet<>(
                Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));

        for (char character : section) {
            if ('.' == character) {
                continue;
            }
            if (!validation.contains(character)) {
                return false;
            }
            validation.remove(character);
        }

        return true;
    }
}
