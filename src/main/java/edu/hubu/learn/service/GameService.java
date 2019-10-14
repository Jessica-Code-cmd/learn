package edu.hubu.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hubu.learn.dao.GameDao;
import edu.hubu.learn.entity.Game;

@Service
public class GameService {

    @Autowired
    private GameDao gameDao;

    public Game getGame(Long id) {
        return gameDao.findById(id).get();
    }
    public List<Game>getGames(){
        return gameDao.findAll();
    }
}