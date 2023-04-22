package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String, Movie> hmMovies = new HashMap<>();
    Map<String, Director> hmDirector = new HashMap<>();


    public boolean addMovie(Movie movie) {
        hmMovies.put( movie.getName(), movie);
        return true;
    }

    public boolean addDirector(Director director) {
        hmDirector.put(director.getName(), director);
        return true;
    }

    public Optional<Director> getDirectorByName(String name){
        if( hmDirector.containsKey(name) ){
            return Optional.of(hmDirector.get(name));
        }
        return Optional.empty();
    }

    public Optional<Movie> getMovieByName(String name) {
        if( hmMovies.containsKey(name) ){
            return Optional.of(hmMovies.get(name));
        }
        return Optional.empty();
    }

    public List<Movie> findAllMovies() {
        if( hmMovies.size() == 0 )return new ArrayList<>();
        return hmMovies.values().stream().toList();
    }

    public ResponseEntity deleteDirectorByName(String name) {


    }

    public void deleteAllDirectors() {

    }
}
