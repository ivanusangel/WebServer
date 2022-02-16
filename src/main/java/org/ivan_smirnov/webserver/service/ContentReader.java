package org.ivan_smirnov.webserver.service;

import java.io.*;

public class ContentReader implements Closeable {
    private final String webAppPath;
    private BufferedReader reader;

    public ContentReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public BufferedReader readContent (String uri) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(webAppPath + uri));
        return reader;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
