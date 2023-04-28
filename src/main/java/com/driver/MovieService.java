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
        Optional<Movie> opt = this.repo.getMovieByName(movie.getName());
        if(opt.isPresent()){
            throw new AlreadyExistException(movie.getName());
        }
        return repo.addMovie(movie);
    }

    public boolean addDirector(Director director) throws AlreadyExistException {
        Optional<Director> opt = this.repo.getDirectorByName(director.getName());
        if( opt.isPresent() ){
            throw new AlreadyExistException(director.getName());
        }
        return repo.addDirector(director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) throws NoDataException {
        Optional<Movie> opt1 = this.repo.getMovieByName(movieName);
        Optional<Director> opt2 = repo.getDirectorByName(directorName);
        if( opt1.isEmpty() || opt2.isEmpty() ){
            throw new NoDataException();
        }
        repo.addDirectorMovie(movieName, directorName);
    }

    public Optional<Movie> getMovieByName(String name) {
        Optional<Movie> opt = this.repo.getMovieByName(name);
        if( opt.isEmpty() ){
            throw new NoDataException();
        }
        return opt;
    }

    public Optional<Director> getDirectorByName(String name) {
        Optional<Director> opt = this.repo.getDirectorByName(name);
        if( opt.isEmpty() ){
            throw new NoDataException();
        }
        return opt;
    }

    public List<String> getMoviesByDirectorName(String director) {
        return this.repo.getMoviesByDirector(director);
    }


    public List<Movie> findAllMovies() throws NoDataException{
        List<Movie> opt = repo.findAllMovies();
        if(opt.size() == 0){
            throw new NoDataException();
        }
        return opt.stream().toList();
    }

    public void deleteDirectorByName(String director) {
        List<String> movies = repo.getMoviesByDirector(director);
        for( String movie : movies ){
            repo.removeMovie(movie);
        }

    }

    public void deleteAllDirectors() {
        List<String> directors = repo.getAllDirectors();
        for( String director : directors ){
            deleteDirectorByName(director);
        }
    }
}
