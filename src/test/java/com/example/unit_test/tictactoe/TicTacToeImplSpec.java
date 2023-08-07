package com.example.unit_test.tictactoe;

import com.example.unit_test.model.Step;
import com.example.unit_test.repository.StepRepository;
import com.example.unit_test.service.TicTacToe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicTacToeSpec {
    @Mock
    private StepRepository stepRepository;
    private TicTacToe ticTacToe;

    @BeforeEach
    public  void before() {
        ticTacToe = new TicTacToe(stepRepository);
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException() {

        assertThatThrownBy(() -> ticTacToe.play(5, 2))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("X is outside board");

    }


    @Test
    public void whenYOutsideBoardThenRuntimeException() {

        assertThatThrownBy(() -> ticTacToe.play(2, 5))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Y is outside board");

    }

    @Test
    public void whenOccupiedThenRuntimeException() {
        //Arrages

        Step step = new Step();

        step.setXCord(0);
        step.setYCord(0);
        step.setPlayer('X');

        List steps = List.of(step);

        when(stepRepository.findAll()).thenReturn(steps);



        //Assert
        assertThatThrownBy(() -> ticTacToe.play(1, 2))  // player y
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Box is occupied");

    }


    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {

        assertThat(ticTacToe.nextPlayer()).isEqualTo('X');

    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO() {

        //Arrange
        Step step = new Step();
        step.setXCord(0);
        step.setYCord(0);
        step.setPlayer('X');

        List steps = List.of(step);

        when(stepRepository.findAll()).thenReturn(steps);

        //Act and assert
        assertThat(ticTacToe.nextPlayer()).isEqualTo('O');

    }

    @Test
    public void whenPlayThenNoWinner() {
        String actual = ticTacToe.play(1, 1);
        assertThat(actual).isEqualTo("No winner");
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {

        //Arrange
        Step x1 = new Step(1L,0,0,'X');
        Step y1 = new Step(2L,0,1,'Y');
        Step x2 = new Step(3L,1,0,'X');
        Step y2 = new Step(4L,1,1,'Y');
        when(stepRepository.findAll()).thenReturn(List.of(x1,y1,x2,y2));

        //Act and assert

        assertThat(ticTacToe.play(3, 1))//x
                .isEqualTo("X is the winner");
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {

        //Arrange
        Step x1 = new Step(1L,1,0,'X');
        Step y1 = new Step(2L,0,0,'O');
        Step x2 = new Step(3L,2,0,'X');
        Step y2 = new Step(4L,0,1,'O');
        Step x3 = new Step(5L,1,1,'X');
        when(stepRepository.findAll()).thenReturn(List.of(x1,y1,x2,y2,x3));

        //Act and assert

        assertThat(ticTacToe.play(1, 3))//x
                .isEqualTo("O is the winner");
    }

    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner() {

        //Arrange
        Step x1 = new Step(1L,0,0,'X');
        Step y1 = new Step(2L,0,1,'O');
        Step x2 = new Step(3L,1,1,'X');
        Step y2 = new Step(4L,0,2,'O');

        when(stepRepository.findAll()).thenReturn(List.of(x1,y1,x2,y2));

        //Act and assert

        assertThat(ticTacToe.play(3, 3))//x
                .isEqualTo("X is the winner");
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {

        //Arrange
        Step x1 = new Step(1L,0,2,'X');
        Step y1 = new Step(2L,0,0,'O');
        Step x2 = new Step(3L,1,1,'X');
        Step y2 = new Step(4L,0,1,'O');

        when(stepRepository.findAll()).thenReturn(List.of(x1,y1,x2,y2));

        //Act and assert

        assertThat(ticTacToe.play(3, 1))//x
                .isEqualTo("X is the winner");
    }


    @Test
    public void whenAllBoxesAreFilledThenDraw() {

        //Arrange
        Step x1 = new Step(1L,0,1,'X');
        Step y1 = new Step(2L,0,2,'O');
        Step x2 = new Step(3L,0,1,'X');
        Step y2 = new Step(4L,1,0,'O');
        Step x3 = new Step(5L,1,2,'X');
        Step y3 = new Step(6L,1,1,'O');
        Step x4 = new Step(7L,2,1,'X');
        Step y4 = new Step(8L,2,0,'O');

        when(stepRepository.findAll()).thenReturn(List.of(x1,y1,x2,y2,x3,y3,x4,y4));

        //Act and assert

        assertThat(ticTacToe.play(3, 3))//x
                .isEqualTo("No winner");
    }
}
