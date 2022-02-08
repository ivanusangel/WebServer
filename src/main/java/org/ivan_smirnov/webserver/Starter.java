package org.ivan_smirnov.webserver;

public class Starter {
    public static void main(String[] args) {
        Server server = new Server(3000);
        server.start();
    }
}
