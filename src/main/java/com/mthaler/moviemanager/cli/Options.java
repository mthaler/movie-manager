package com.mthaler.moviemanager.cli;

import com.mthaler.moviemanager.config.Credentials;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import java.util.Properties;

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
        Options result = new Options();
        Properties properties = Credentials.load();
        if (properties != null) {
            String username = properties.getProperty("username");
            if (username != null) {
                result.username = username;
            }
            String password = properties.getProperty("password");
            if (password != null) {
                result.password = password;
            }
        }
        MyOptionsParser parser = new MyOptionsParser();
        OptionSet options = parser.parse(args);
        if (options.has(parser.username)) {
            result.username = options.valueOf(parser.username);
        }
        if (options.has(parser.password)) {
            result.password = options.valueOf(parser.password);
        }
        return result;
    }
}
