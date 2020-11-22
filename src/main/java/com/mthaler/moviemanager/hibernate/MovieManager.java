package com.mthaler.moviemanager.hibernate;

import com.mthaler.moviemanager.cli.Options;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MovieManager {

    public MovieManager(String[] args) throws Exception {
        Options options = Options.parseOptions(args);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .applySetting("hibernate.connection.username", options.getUsername())
                .applySetting("hibernate.connection.password", options.getPassword())
                .build();
    }

    public static void main(String[] args) throws Exception {
        MovieManager movieManager = new MovieManager(args);
    }
}
