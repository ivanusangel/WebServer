package org.ivan_smirnov.webserver.model;

import java.io.BufferedReader;
import java.util.Map;

public class Response {
    BufferedReader content;
    HttpStatus status;
    Map<String, String> headers;


    public Response(BufferedReader content, HttpStatus status) {
        this(content, status, null);
    }

    public Response(BufferedReader content, HttpStatus status, Map<String, String> headers) {
        this.content = content;
        this.status = status;
        this.headers = headers;
    }

    public BufferedReader getContent() {
        return content;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
