package org.ivan_smirnov.webserver.service;

import org.ivan_smirnov.webserver.exception.BadRequestException;
import org.ivan_smirnov.webserver.model.HttpMethod;
import org.ivan_smirnov.webserver.model.HttpStatus;
import org.ivan_smirnov.webserver.model.Request;
import org.ivan_smirnov.webserver.model.Response;

import java.io.*;

public class RequestHandler {
    public static final RequestParser REQUEST_PARSER = new RequestParser();
    public static final ResponseWriter RESPONSE_WRITER = new ResponseWriter();
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final ContentReader contentReader;

    public RequestHandler(BufferedReader reader, BufferedWriter writer, ContentReader contentReader) {
        this.reader = reader;
        this.writer = writer;
        this.contentReader = contentReader;
    }

    public void handle() throws IOException {
        try {
            Request request = REQUEST_PARSER.parseRequest(reader);
            if (!HttpMethod.GET.equals(request.getHttpMethod())) {
                throw new BadRequestException();
            }
            BufferedReader content = contentReader.readContent(request.getUri());

            Response response = new Response(content, HttpStatus.OK);
            RESPONSE_WRITER.writeResponse(writer, response);
            writer.flush();
            contentReader.close();
        } catch (BadRequestException e) {
            System.out.println(e.getMessage()); //log
            RESPONSE_WRITER.writeResponse(writer, HttpStatus.BAD_REQUEST);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage()); //log
            RESPONSE_WRITER.writeResponse(writer, HttpStatus.NOT_FOUND);
        }
    }
}
