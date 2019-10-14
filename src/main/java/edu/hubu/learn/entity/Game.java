package edu.hubu.learn.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "game")
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    @Column(name="avatar_url")
    private String avatar;
}