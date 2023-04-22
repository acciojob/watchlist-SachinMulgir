package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;


    //POST methods:
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie ){
        try{
            boolean added = this.movieService.addMovie(movie);
            return new ResponseEntity("Movie Successfully Added", HttpStatus.CREATED);
        }catch (AlreadyExistException ex) {
            return new ResponseEntity("Movie with the name " + movie.getName() + " already exist.", HttpStatus.valueOf(400));
        }

    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        try{
            boolean added = this.movieService.addDirector(director);
            return new ResponseEntity("Director Successfully Added", HttpStatus.CREATED);
        }catch (AlreadyExistException ex) {
            return new ResponseEntity("Movie with the name " + director.getName() + " already exist.", HttpStatus.valueOf(400));
        }
    }


    //PUT methods:
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName){
        return this.movieService.addMovieDirectorPair(movieName, directorName);
    }


    //GET methods:
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        return this.movieService.getMovieByName(name);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
        return this.movieService.getDirectorByName(name);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable Director director){
        return this.movieService.getMoviesByDirectorName(director);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        try{
            List<Movie> list = this.movieService.findAllMovies();
            return new ResponseEntity(list, HttpStatus.valueOf(200));
        }catch (NoDataException ex){
            return new ResponseEntity("No data found", HttpStatus.valueOf(404));
        }
    }

    //DELETE methods:
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String name){
        return this.movieService.deleteDirectorByName(name);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        this.movieService.deleteAllDirectors();
    }



}
