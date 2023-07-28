package org.example;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JokeRepository extends CrudRepository<Joke,Long> {
    long count();
    List<Joke> findByReligiousAndPoliticalAndRacistAndSexistAndExplicit(boolean religious, boolean political, boolean racist, boolean sexist, boolean explicit);

    List<Joke> findByCategory(String category);
}
