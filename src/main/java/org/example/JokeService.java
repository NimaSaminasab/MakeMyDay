package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokeService {
    @Autowired
    JokeRepository jokeRepository ;

    public String createJoke(Joke joke){
        if(joke!=null){
            jokeRepository.save(joke) ;
            return "ok" ;
        }
        else
            return "error" ;
    }

    public String deleteJoke(Joke joke){
        if(joke!=null){
            jokeRepository.delete(joke);
            return "ok" ;
        }
        else
            return "error" ;
    }
    public Joke findJokeById(long id){
        if(id!=0){
            return jokeRepository.findById(id).orElse(null) ;
        }
        return null;
    }
    public List<Joke> findAllJoke(){
        return (List<Joke>) jokeRepository.findAll();
    }
    public long getCount(){
        return jokeRepository.count() ;
    }
    public List<Joke> findJokeByAttribute(boolean religious, boolean political, boolean racist, boolean sexist, boolean explicit){
      return jokeRepository.findByReligiousAndPoliticalAndRacistAndSexistAndExplicit(religious,political,racist,sexist,explicit);
    }

    public List<Joke> findJokeByCategory(String category) {

        return jokeRepository.findByCategory(category) ;
    }
}
