package com.mthaler.moviemanager.hibernate.mapping;

import com.mthaler.moviemanager.cli.Options;
import com.mthaler.moviemanager.model.Actor;
import com.mthaler.moviemanager.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {

    private final SessionFactory sessionFactory;

    public MovieManager(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate_mapping.cfg.xml") // configures settings from hibernate_mapping.cfg.xml
                .applySetting("hibernate.connection.username", options.getUsername())
                .applySetting("hibernate.connection.password", options.getPassword())
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    private void persistMovie() {
        Movie movie0 = new Movie();
        movie0.setId(1);
        movie0.setDirector("Steven Speilberg");
        movie0.setTitle("Jaws");
        movie0.setSynopsis("A tale of a white shark!");

        Movie movie1 = new Movie();
        movie1.setId(2);
        movie1.setDirector("Tony Scott");
        movie1.setTitle("Top Gun");
        movie1.setSynopsis("Maverick is a hot pilot. When he encounters "
                + "a pair of MiGs over the Persian Gulf,"
                + " his wingman is clearly outflown and freaks. "
                + "On almost no fuel, Maverick is able to talk "
                + "him back down to the Carrier..");

        Actor a0 = new Actor();
        a0.setId(1);
        a0.setName("Tom Cruise");
        a0.setGender("male");

        Actor a1 = new Actor();
        a1.setId(2);
        a1.setName("Kelly McGillis");
        a1.setGender("female");

        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(a0);
        actors.add(a1);

        movie1.setActors(actors);

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(movie0);
        session.save(movie1);
        session.getTransaction().commit();
    }

    private void findMovie(int i) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Movie movie = session.load(Movie.class, i);
        System.out.println("Movie:" + movie);
        session.getTransaction().commit();

    }

    private void findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Movie> movies = session.createQuery("from Movie").list();
        session.getTransaction().commit();
        System.out.println("All Movies:" + movies);
    }


    public static void main(String[] args) throws Exception {
        MovieManager movieManager = new MovieManager(args);
        //movieManager.persistMovie();
        movieManager.findAll();
    }
}
