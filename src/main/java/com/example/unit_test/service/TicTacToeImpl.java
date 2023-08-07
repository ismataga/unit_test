package com.example.unit_test.service;

import com.example.unit_test.model.Step;
import com.example.unit_test.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class TicTacToe {
    private final StepRepository stepRepository;
    private static final int SIZE = 3;


    public String play(int x, int y) {

        checkAxis(x, 'X');
        checkAxis(y, 'Y');
        Character[][] board = loadSteps();
        char lastPlayer = nextPlayer();
        setBox(board, x, y, lastPlayer);

        if (isWin(board, x, y, lastPlayer)) {
            return lastPlayer + " is the winner";
        } else if (isDraw(board))
            return "The result is draw";
//        saveToDb(lastPlayer,x,y);
        return "No winner";
    }

    private Character[][] loadSteps() {
        Character[][] board = {
                {'\0', '\0', '\0'},
                {'\0', '\0', '\0'},
                {'\0', '\0', '\0'}};

        stepRepository.findAll()
                .stream()
                .forEach(step ->
                        board[step.getXCord()][step.getYCord()] = step.getPlayer());

        printBoard(board);
        return board;
    }

    private boolean isDraw(Character[][] board) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == '\0')
                    return false;
            }

        }
        return true;
    }

    private boolean isWin(Character[][] board, int x, int y, char lastPlayer) {
        int playerTotal = lastPlayer * 3;
        char horizontal, vertical, diagonal1, diagonal2;
        horizontal = vertical = diagonal1 = diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][y - 1];
            vertical += board[x - 1][i];
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];

        }
        return horizontal == playerTotal
                || vertical == playerTotal
                || diagonal1 == playerTotal
                || diagonal2 == playerTotal;
    }


    public Character nextPlayer() {
        var steps = stepRepository.findAll();
        if (steps.size() == 0) {
            return 'X';
        }

        var lastPlayer = steps.get(steps.size() - 1).getPlayer();
        return lastPlayer == 'X' ? 'O' : 'X';
    }

    private void checkAxis(int axis, char player) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException(player + " is outside board");
        }

    }

    private void setBox(Character[][] board, int x, int y, char player) {
        if (board[x - 1][y - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[x - 1][y - 1] = player;
            saveMove(x, y, player);
        }
    }

    private void saveToDb(Character player, int x, int y) {

        Step step = new Step();
        step.setPlayer(player);
        step.setXCord(x);
        step.setYCord(y);
        stepRepository.save(step);
    }

    private void saveMove(int x, int y, char player) {
        Step step = new Step();

        step.setPlayer(player);
        step.setXCord(x);
        step.setYCord(y);

        stepRepository.save(step);
    }

    private void printBoard(Character[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println("");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '\0')
                    System.out.println("- ");
                System.out.println(board[i][j] + " ");

            }

        }
    }
}
