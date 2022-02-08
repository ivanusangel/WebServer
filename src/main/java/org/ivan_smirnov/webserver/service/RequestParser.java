package org.ivan_smirnov.webserver.service;

import org.ivan_smirnov.webserver.exception.BadRequestException;
import org.ivan_smirnov.webserver.model.HttpMethod;
import org.ivan_smirnov.webserver.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.ivan_smirnov.webserver.util.Constant.HEADER_SEPARATOR;
import static org.ivan_smirnov.webserver.util.Constant.WHITE_SPACE;

public class RequestParser {



    public Request parseRequest(BufferedReader reader) throws BadRequestException {
        Request request = new Request();
        try {
            injectUriHttpMethod(reader, request);
            injectHeaders(reader, request);
        } catch (Exception e) {
            BadRequestException badRequestException = new BadRequestException();
            badRequestException.addSuppressed(e);
            throw badRequestException;
        }
        return request;
    }

    private void injectUriHttpMethod(BufferedReader reader, Request request) throws IOException {
        String line = reader.readLine();
        String[] array = line.split(WHITE_SPACE);
        request.setHttpMethod(HttpMethod.valueOf(array[0]));
        request.setUri(array[1]);
    }

    private void injectHeaders(BufferedReader reader, Request request) throws IOException {
        Map<String, String> headerParams = new HashMap<>();
        String line = reader.readLine();
        while (!line.isEmpty()) {
            String[] array = line.split(HEADER_SEPARATOR);
            headerParams.put(array[0], array[1]);
            line = reader.readLine();
        }
        request.setHeaders(headerParams);
    }
}
