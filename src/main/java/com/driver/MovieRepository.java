package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String, Movie> hmMovies = new HashMap<>();
    Map<String, Director> hmDirector = new HashMap<>();
    Map<String, List<String>> hmDirectorMovie = new HashMap<>();


    //Crud : CREATE

    public boolean addMovie(Movie movie) {
        hmMovies.put( movie.getName(), movie);
        return true;
    }
    public boolean addDirector(Director director) {
        hmDirector.put(director.getName(), director);
        return true;
    }


    //cRud : READ

    public Optional<Movie> getMovieByName(String name) {
        if( hmMovies.containsKey(name) ){
            return Optional.of(hmMovies.get(name));
        }
        return Optional.empty();
    }
    public Optional<Director> getDirectorByName(String name){
        if( hmDirector.containsKey(name) ){
            return Optional.of(hmDirector.get(name));
        }
        return Optional.empty();
    }
    public List<String> findAllMovies() {
        if( hmMovies.size() == 0 )return new ArrayList<>();
        return hmMovies.keySet().stream().toList();
    }
    public List<String> getMoviesByDirector(String director) {
        return hmDirectorMovie.get(director);
    }

    public List<String> getAllDirectors() {
        return hmDirector.keySet().stream().toList();
    }


    //crUd : UPDATE:
    public boolean addDirectorMoviePair(String movieName, String directorName) {
        List<String> list = new ArrayList<>();
        if( hmDirectorMovie.containsKey(directorName) ){
            list = hmDirectorMovie.get(directorName);
            if( list.contains(movieName) ){
                return false;
            }
            list.add(movieName);
        }
        else{
            list.add(movieName);
        }
        hmDirectorMovie.put(directorName,list);
        return true;
    }


    // cruD : DELETE

    public void removeMovie(String movie){
        hmMovies.remove(movie);
    }

    public void deleteDirectorByName(String director){
        hmDirector.remove(director);
        hmDirectorMovie.remove(director);

    }


}
