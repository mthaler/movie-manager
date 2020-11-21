package com.mthaler.moviemanager.cli;

import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class Options {

    private String username = "";
    private String password = "";

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    static class MyOptionsParser extends OptionParser {
        ArgumentAcceptingOptionSpec<String> username = accepts("username", "database username").withRequiredArg();
        ArgumentAcceptingOptionSpec<String> password = accepts("password", "database password").withRequiredArg();
    }

    public static Options parseOptions(String[] args) {
        MyOptionsParser parser = new MyOptionsParser();
        OptionSet options = parser.parse(args);
        Options result = new Options();
        if (options.has(parser.username)) {
            result.username = options.valueOf(parser.username);
        }
        if (options.has(parser.password)) {
            result.password = options.valueOf(parser.password);
        }
        return result;
    }
}
