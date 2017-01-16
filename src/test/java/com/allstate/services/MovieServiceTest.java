package com.allstate.services;

import com.allstate.entities.Movie;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class MovieServiceTest {

    @Autowired
    private MovieService service;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void shouldCreateMovie() throws Exception{
        Movie bfo = new Movie();
        bfo.setTitle("The Matrix");
        Movie aftr = this.service.create(bfo);
        assertEquals(aftr.getTitle(),"The Matrix");
        assertEquals(aftr.getId(),2);

    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotCreateMovieNoTitle() throws Exception {
        Movie before = new Movie();
        Movie after = this.service.create(before);
        assertEquals(2, after.getId());
    }

    @Test
    public void shouldFindOneMovie() throws Exception{
        Movie bfo = new Movie();
        bfo.setTitle("The Matrix");
        bfo.setId(1);
        Movie aftr1 = this.service.create(bfo);
        Movie aftr = this.service.findById(aftr1.getId());
        assertEquals(aftr.getTitle(),"The Matrix");
    }

    @Test
    public void shouldShowAllMovies() throws Exception{
        Movie first = new Movie();
        first.setTitle("The Matrix");
        first.setId(1);

        Movie second = new Movie();
        second.setTitle("The Avenger");
        second.setId(2);

        Movie firstA = this.service.create(first);
        Movie secondA = this.service.create(second);

        Iterable<Movie> allMovies = this.service.findAll();
        List<Movie> movies = Lists.newArrayList(allMovies);
        assertEquals(2,movies.size());
    }

    @Test
    public void shouldFindByTitle() throws Exception{
        Movie first = new Movie();
        first.setTitle("The Matrix");
        first.setId(1);

        Movie second = new Movie();
        second.setTitle("The Avenger");
        second.setId(2);

        Movie firstA = this.service.create(first);
        Movie secondA = this.service.create(second);

        Movie aftr = this.service.findByTitle("The Avenger");
        assertEquals(aftr.getTitle(),"The Avenger");
    }

    @Test
    public void shouldDeleteById() throws Exception {
        Movie first = new Movie();
        first.setTitle("The Matrix");
        first.setId(1);

        Movie second = new Movie();
        second.setTitle("The Avenger");
        second.setId(2);

        Movie firstA = this.service.create(first);
        Movie secondA = this.service.create(second);

        this.service.deleteById(firstA.getId());
        Iterable<Movie> allMovies = this.service.findAll();
        List<Movie> movies = Lists.newArrayList(allMovies);
        assertEquals(1,movies.size());
        assertEquals(movies.get(0).getTitle(),"The Avenger");
    }

//    @Test
//    public void shouldUpdateById() throws Exception {
//        Movie first = new Movie();
//        first.setTitle("The Matrix");
//        first.setId(1);
//        Movie firstA = this.service.create(first);
//
//
//        first.setTitle("The Avenger");
//
//        this.service.updateById(firstA.getId(), first);
//        Movie aftr = this.service.findById(1);
//
//
//        assertEquals(aftr.getTitle(),"The Avenger");
//    }




}
