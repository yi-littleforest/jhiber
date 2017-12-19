/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.s1.jhiber.basics;

import com.s1.jhiber.domain.Movie;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;



public class BasicMovieManager {

    private SessionFactory sessionFactory = null;

    public BasicMovieManager() {
        init5x();
       // init4x();
//        init3x();
    }
    
    private void init5x(){
        Configuration config = new Configuration().configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                config.getProperties()).build();
        
        config.addAnnotatedClass(Movie.class);
        
        
        sessionFactory = config.buildSessionFactory(serviceRegistry);
    }

    private void init4x() {
        Configuration config = new Configuration().configure();

       /* ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                config.getProperties()).buildServiceRegistry();

        sessionFactory = config.buildSessionFactory(serviceRegistry);*/

    }

    private void init3x() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    private void persistMovie() {
        Movie movie = new Movie();

        movie.setId(2);
        movie.setDirector("Steven Speilberg");
        movie.setTitle("Jaws");
        movie.setSynopsis("A tale of a white shark!");

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        session.save(movie);

        session.getTransaction().commit();
    }

    private void findMovie(int i) {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Movie movie = (Movie) session.load(Movie.class, i);

        System.out.println("Movie:" + movie);
        
        System.out.println("Movie tile:" + movie.getTitle());

        session.getTransaction().commit();

    }

    private void findAll() {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        List<Movie> movies = session.createQuery("from Movie").list();

        session.getTransaction().commit();

        System.out.println("All Movies:" + movies);

    }

    public static void main(String[] args) {
        BasicMovieManager movieManager = new BasicMovieManager();

      //  movieManager.persistMovie();

        movieManager.findMovie(1);

       // movieManager.findAll();
    }
}
