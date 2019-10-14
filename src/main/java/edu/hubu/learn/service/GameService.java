package edu.hubu.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
        return gameDao.findAll(new Sort(Direction.DESC, "id"));
    }
    public List<Game> searchGames(String keyword) {
        Game game = new Game();
        game.setUsername(keyword);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", match->match.contains());
        Example<Game> example = Example.of(game, matcher);
        Sort sort = new Sort(Direction.DESC, "id");
        return gameDao.findAll(example, sort);
    }

    public Game addGame(Game game) {
        return gameDao.save(game);
    }

    public void deleteGame(Long id) {
        gameDao.deleteById(id);
    }

    public void modifyGame(Game game) {
        gameDao.save(game);
    }
}