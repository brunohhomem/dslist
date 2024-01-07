package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Transactional(readOnly = true)
    public GameDTO findById(Long id){
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            Game result = optionalGame.get();
            return new GameDTO(result);
        } else {
            throw new EntityNotFoundException("Game not found with ID: " + id);
        }
    }
    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
        List<Game> result = gameRepository.findAll(); //Pega a lista de jogos
        List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList(); //Transforma a lista pro DTO configurado anteriormente
        return dto; //Retorna o dto
    }
    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> result = gameRepository.searchByList(listId);
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }
    public ResponseEntity insertGame(GameDTO data){
        Game newGame = new Game(data);
        gameRepository.save(newGame);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity updateGameById(GameDTO data){
        Optional<Game> optionalGame = gameRepository.findById(data.getId());
        if(optionalGame.isPresent()){
            Game game = optionalGame.get();
            game.setTitle(data.getTitle());
            game.setYear(data.getYear());
            game.setGenre(data.getGenre());
            game.setPlatforms(data.getPlatforms());
            game.setScore(data.getScore());
            game.setImgUrl(data.getImgUrl());
            game.setShortDescription(data.getShortDescription());
            game.setLongDescription(data.getLongDescription());

            // Salvar o objeto atualizado no repositório
            Game updatedGame = gameRepository.save(game);

            // retornar oque foi salvo
            return ResponseEntity.ok(updatedGame);
        } return ResponseEntity.notFound().build();
    }
    public ResponseEntity deleteGameById(Long id){
        gameRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
