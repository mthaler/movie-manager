package com.mthaler.moviemanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MovieTest {

    @Test
    void id() {
        Movie m = new Movie();
        m.setId(42);
        assertEquals(42, m.getId());
    }

    @Test
    void title() {
        Movie m = new Movie();
        m.setTitle("test");
        assertEquals("test", m.getTitle());
    }
}
