package edu.hubu.learn.service;

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
}