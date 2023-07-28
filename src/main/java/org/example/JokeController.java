package org.example;

import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
public class JokeController {
    @Autowired
    JokeService jokeService ;
    Random random = new Random();

    @PostMapping("/createJoke")

    public String createJoke(Joke joke) {
        if (joke != null) {
            jokeService.createJoke(joke);
            return "ok";
        } else
            return "error";
    }

    @DeleteMapping("/deleteJoke/{id}")
    @ResponseBody
    public String deleteJoke(@PathVariable long id) {
        if (id > 0) {
            Joke joke = jokeService.findJokeById(id);
            if (joke != null) {
                jokeService.deleteJoke(joke);
                return joke.getId()  + " is deleted ";
            } else
                return "Couldn´t find Costumer with id " + id;
        } else
            return "No valid id";
    }

    @GetMapping("/findJokeById/{id}")
    @ResponseBody
    public Joke findJokeById(@PathVariable long id) {
        if (id > 0) {
            return jokeService.findJokeById(id);
        }
        return null;
    }
    @GetMapping("/findAllJokes")
    @ResponseBody
    public List<Joke> findAllJokes() {
        return jokeService.findAllJoke();
    }

    @GetMapping("/tellARandomJoke")
    public Joke tellARandomJoke(){
        long count = jokeService.getCount();
        long randomNumber = random.nextLong(count) + 1;

        return jokeService.findJokeById(randomNumber);
    }
    @GetMapping("/getASpecificJoke/{religious}/{political}/{racist}/{sexist}/{explicit}")
    public Joke getASpecificJoke(@PathVariable boolean religious,@PathVariable boolean political,@PathVariable boolean racist,@PathVariable boolean sexist,@PathVariable boolean explicit){
          List<Joke> jokeList = jokeService.findJokeByAttribute(religious,political,racist,sexist,explicit) ;
        int randomNumber = (int) random.nextLong(jokeList.size()) + 1;
        return jokeList.get(randomNumber) ;
    }
    @GetMapping("/getACategoryJoke/{category}")
    public Joke getACategoryJoke(@PathVariable String category){
        List<Joke> jokeList= jokeService.findJokeByCategory(category);
        int randomNumber = (int) random.nextLong(jokeList.size()) + 1;
        return jokeList.get(randomNumber) ;

    }



}
