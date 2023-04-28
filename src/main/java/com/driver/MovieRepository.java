package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String, Movie> hmMovies = new HashMap<>();
    Map<String, Director> hmDirector = new HashMap<>();
    Map<String, List<String>> hmDirectorMovie = new HashMap<>();


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

    public void addDirectorMovie(String movieName, String directorName) {
        if( hmDirectorMovie.containsKey(directorName) ){
            List<String> list = hmDirectorMovie.get(directorName);
            list.add(movieName);
            hmDirectorMovie.put(directorName,list);
        }
        else{
            List<String> list = new ArrayList<>();
            list.add(movieName);
            hmDirectorMovie.put(directorName,list);
        }
    }

    public List<String> getMoviesByDirector(String director) {
        return hmDirectorMovie.get(director);
    }

    public void removeMovie(String movie){
        hmMovies.remove(movie);
    }

    public void deleteDirectorByName(String director){
        hmDirector.remove(director);
        hmDirectorMovie.remove(director);

    }

    public List<String> getAllDirectors() {
        return hmDirector.keySet().stream().toList();
    }


}
