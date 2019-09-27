package edu.hubu.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hubu.learn.entity.Game;

public interface GameDao extends JpaRepository<Game, Long> {

}