package com.example.unit_test.Controller;

import com.example.unit_test.service.TicTacToe;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class TicTacToeController {

    private final TicTacToe ticTacToe;

    @PostMapping
    public Result play(@RequestBody MoveDto moveDto) {
      return new Result(ticTacToe.play(moveDto.x, moveDto.y));
    }
}
