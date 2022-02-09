package org.ivan_smirnov.webserver;

import org.junit.Test;

public class Starter {

    @Test
    public void runServer() {
        Server server = new Server(3000);
        server.start();
    }
}
