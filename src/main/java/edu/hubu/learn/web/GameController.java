package edu.hubu.learn.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Game;
import edu.hubu.learn.service.GameService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/game")
public class GameController {

   

    @Autowired
    private GameService gameService;

    @RequestMapping("/{id}")
    public ModelAndView game(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Game game = gameService.getGame(id);
        mav.addObject("game", game);
        mav.setViewName("game");
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        gameService.deleteGame(id);
        ModelAndView mav = new ModelAndView("redirect:/game/list");
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

    @RequestMapping("/add")
    public ModelAndView addGame() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("game_add");
        return mav;
    }

    @RequestMapping("/do_add")
    public ModelAndView doAddGame(Game game) {
        game.setAvatar("");
        gameService.addGame(game);
        ModelAndView mav = new ModelAndView("redirect:/game/list");
        return mav;
    }

    @RequestMapping("/modify/{id}")
    public ModelAndView modifyGame(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("game", gameService.getGame(id));
        mav.setViewName("game_modify");
        return mav;
    }

    @RequestMapping("/do_modify")
    public ModelAndView doModifyGame(Game game) {
        game.setAvatar("");
        gameService.modifyGame(game);
        ModelAndView mav = new ModelAndView("redirect:/game/list");
        return mav;
    }

    @RequestMapping("/search")
    public ModelAndView searchGame() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("game_search");
        return mav;
    }

    @RequestMapping("/do_search")
    public ModelAndView doSearchGame(HttpServletRequest httpRequest) {
        ModelAndView mav = new ModelAndView();
        String keyword = httpRequest.getParameter("keyword");
        List<Game> games = gameService.searchGames(keyword);
        mav.addObject("games", games);
        mav.setViewName("games");
        return mav;
    }

    @RequestMapping("/add_avatar/{id}")
    public ModelAndView addGameAvatar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("game", gameService.getGame(id));
        mav.setViewName("game_add_avatar");
        return mav;
    }

    @RequestMapping("/do_add_avatar/{id}")
    public ModelAndView doAddGameAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable Long id) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = ResourceUtils.getURL("classpath:").getPath() + "../../../resources/main/static/";
            File dest = new File(filePath + fileName);
            log.info(dest.getAbsolutePath());
            file.transferTo(dest);
            Game game = gameService.getGame(id);
            game.setAvatar(fileName);
            gameService.modifyGame(game);
        } catch (Exception e) {
            log.error("upload avatar error", e.getMessage());
        }
        return new ModelAndView("redirect:/game/list");
    }
}