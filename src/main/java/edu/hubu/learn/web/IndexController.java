package edu.hubu.learn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Game;
import edu.hubu.learn.service.GameService;
import edu.hubu.learn.entity.User;
import edu.hubu.learn.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;


    @Autowired
    private GameService gameService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(1l);
        mav.addObject("user", user);
        mav.setViewName("user");
        return mav;
    }

        @RequestMapping("/game")
    public ModelAndView game() {
        ModelAndView mav = new ModelAndView();
        Game game = gameService.getGame(1l);
        mav.addObject("game", game);
        mav.setViewName("game");
        return mav;
    }
}