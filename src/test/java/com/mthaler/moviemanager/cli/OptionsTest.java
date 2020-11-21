package com.mthaler.moviemanager.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionsTest {

    @Test
    void parseOptions() {
        Options o0 = Options.parseOptions(new String[]{ "--username", "test"});
        assertEquals("test", o0.getUsername());
        assertEquals("", o0.getPassword());
        Options o1 = Options.parseOptions(new String[]{ "--password", "secret"});
        assertEquals("", o1.getUsername());
        assertEquals("secret", o1.getPassword());
        Options o2 = Options.parseOptions(new String[]{ "--username", "test", "--password", "secret"});
        assertEquals("test", o2.getUsername());
        assertEquals("secret", o2.getPassword());
    }
}
