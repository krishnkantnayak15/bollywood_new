package com.allstate.services;

import com.allstate.entities.Movie;
import com.allstate.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private IMovieRepository repository;

    @Autowired
    public void setRepository(IMovieRepository repository) {
        this.repository = repository;
    }

    public Movie create(Movie m){
        return this.repository.save(m);
    }
    public Iterable<Movie> listAll(){
        return this.repository.findAll();
    }
    public Movie findById(int id){

        return this.repository.findOne(id);
    }

    public Iterable<Movie> findAll(){
        return this.repository.findAll();
    }
    public Movie findByTitle(String title){
        return this.repository.findByTitle(title);
    }

    public void deleteById(int id){
        this.repository.delete(id);
    }
//    public void updateById(int id, Movie r){
//        this.repository.update(id,r);
//    }



}
