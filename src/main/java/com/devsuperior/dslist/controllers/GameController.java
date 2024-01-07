package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;
    @GetMapping
    public List<GameMinDTO> findAll(){
        return gameService.findAll();
    }
    @GetMapping("/{id}")
    public GameDTO findById(@PathVariable Long id){
        return gameService.findById(id);
    }
    @PostMapping
    public ResponseEntity insertGame(@RequestBody @Valid GameDTO data){
        return gameService.insertGame(data);
    }
}
