package com.devsuperior.dslist.entities;

import com.devsuperior.dslist.dto.GameDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_game")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "game_year") //customizando o nome da coluna no banco
    private Integer year; //year palavra reservada do banco de dados

    private String genre;
    private String platforms;
    private Double score;
    private String imgUrl;

    @Column(columnDefinition = "TEXT")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String longDescription;

    public Game(GameDTO data){
        this.title = data.getTitle();
        this.year = data.getYear();
        this.genre = data.getGenre();
        this.platforms = data.getPlatforms();
        this.score = data.getScore();
        this.imgUrl = data.getImgUrl();
        this.shortDescription = data.getShortDescription();
        this.longDescription = data.getLongDescription();
    }
}
