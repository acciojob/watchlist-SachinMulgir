package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MovieService{

    @Autowired
    MovieRepository repo;

    public boolean addMovie(Movie movie) throws AlreadyExistException {
        Optional<Movie> opt = repo.getMovieByName(movie.getName());
        if(opt.isPresent()){
            throw new AlreadyExistException(movie.getName());
        }
        return repo.addMovie(movie);
    }


    public boolean addDirector(Director director) {
        Optional<Director> opt = repo.getDirectorByName(director.getName());
        if( opt.isPresent() ){
            throw new AlreadyExistException(director.getName());
        }
        return repo.addDirector(director);
    }


    public ResponseEntity addMovieDirectorPair(String movieName, String directorName) {

    }


    public ResponseEntity getMovieByName(String name) {

    }


    public ResponseEntity getDirectorByName(String name) {

    }


    public ResponseEntity getMoviesByDirectorName(Director director) {

    }


    public List<Movie> findAllMovies() throws NoDataException{
        List<Movie> opt = repo.findAllMovies();
        if(opt.size() == 0){
            throw new NoDataException();
        }
        return opt.stream().toList();
    }


    public ResponseEntity deleteDirectorByName(String name) {
        return repo.deleteDirectorByName(name);
    }


    public void deleteAllDirectors() {
        repo.deleteAllDirectors();
    }
}
