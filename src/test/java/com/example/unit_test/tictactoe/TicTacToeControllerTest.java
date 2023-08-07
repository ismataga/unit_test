package com.example.unit_test.tictactoe;

import com.example.unit_test.Controller.MoveDto;
import com.example.unit_test.Controller.TicTacToeController;
import com.example.unit_test.service.TicTacToe;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(TicTacToeController.class)
public class TicTacToeControllerTest {

    @MockBean
    private TicTacToe game;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenPlayThenNoWinner() throws Exception {

        MoveDto moveDto = new MoveDto(1, 1);
        when(game.play(1, 1)).thenReturn("No winner");

        //Act
        mockMvc.perform(
                post("/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(moveDto))
        ).andExpect(status().isOk());

    }
}
