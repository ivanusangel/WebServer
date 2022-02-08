package org.ivan_smirnov.webserver;

import org.ivan_smirnov.webserver.service.ContentReader;
import org.ivan_smirnov.webserver.service.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.ivan_smirnov.webserver.util.Constant.DEFAULT_WEB_APP_PATH;

public class Server {
    private final int port;
    private String webAppPath;

    public Server(int port) {
        this.port = port;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port);
             ContentReader contentReader = new ContentReader(
                     webAppPath == null ? DEFAULT_WEB_APP_PATH : webAppPath)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(
                             new OutputStreamWriter(socket.getOutputStream()))
                ) {
                    RequestHandler requestHandler =
                            new RequestHandler(reader, writer, contentReader);
                    requestHandler.handle();
                } catch (Exception e) {
                    System.out.println(e.getMessage()); //log
                }
            }

        } catch (IOException e) {
            System.out.println("Server looks dead");
            System.out.println(e.getMessage());
        }
    }
}
