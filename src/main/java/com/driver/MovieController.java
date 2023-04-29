package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    //************************************ P O S T ******************************************//
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
            return new ResponseEntity("Director with the name " + director.getName() + " already exist.", HttpStatus.valueOf(400));
        }
    }


    //************************************ P U T ******************************************//
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName){
        try{
            this.movieService.addMovieDirectorPair(movieName, directorName);
            return new ResponseEntity("Added the pair successfully", HttpStatus.valueOf(200));
        }catch (NoDataException ex){
            return new ResponseEntity("Either of the movie or director is missing", HttpStatus.NOT_FOUND);
        }catch (AlreadyExistException ex){
            return new ResponseEntity("Pair Already Exist", HttpStatus.BAD_REQUEST);
        }
    }


    //*****************************      G E T   ****************************************//
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        try{
            Movie movie = this.movieService.getMovieByName(name);
            return new ResponseEntity(movie, HttpStatus.valueOf(200));
        }catch (NoDataException ex){
            return new ResponseEntity("Movie with name = " + name + " does not exist", HttpStatus.valueOf(404));
        }

    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
        try{
            Optional<Director> director = this.movieService.getDirectorByName(name);
            return new ResponseEntity(director, HttpStatus.valueOf(200));
        }catch (NoDataException ex){
            return new ResponseEntity("Director with name = " + name + " does not exist", HttpStatus.valueOf(404));
        }
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String director){
        try{
            List<String> movieList = this.movieService.getMoviesByDirectorName(director);
            return new ResponseEntity(movieList, HttpStatus.valueOf(200));
        }
        catch (NoDataException ex){
            return new ResponseEntity("Director = " + director + " not found", HttpStatus.valueOf(404));
        }
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        try{
            List<String> list = this.movieService.findAllMovies();
            return new ResponseEntity(list, HttpStatus.valueOf(200));
        }catch (NoDataException ex){
            return new ResponseEntity("No data found", HttpStatus.valueOf(404));
        }
    }

    //************************************ D E L E T E ******************************************//
    //DELETE methods:
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String director){
        this.movieService.deleteDirectorByName(director);
        return new ResponseEntity("Removed Director with its movies", HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        this.movieService.deleteAllDirectors();
        return new ResponseEntity("All Directors Deleted", HttpStatus.OK);
    }



}
