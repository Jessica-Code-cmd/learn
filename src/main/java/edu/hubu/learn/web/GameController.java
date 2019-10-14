package edu.hubu.learn.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Game;
import edu.hubu.learn.service.GameService;

@Controller
@RequestMapping("/game")
public class GameController {

   

    @Autowired
    private GameService gameService;

    @RequestMapping("/{id}")
    public ModelAndView student(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Game game = gameService.getGame(id);
        mav.addObject("game", game);
        mav.setViewName("game");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView game() {
        ModelAndView mav = new ModelAndView();
        List<Game> games = gameService.getGames();
        mav.addObject("games", games);
        mav.setViewName("games");
        return mav;
    }

    
}