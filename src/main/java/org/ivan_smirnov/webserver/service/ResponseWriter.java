package org.ivan_smirnov.webserver.service;

import org.ivan_smirnov.webserver.model.HttpStatus;
import org.ivan_smirnov.webserver.model.Response;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.ivan_smirnov.webserver.util.Constant.LINE_SEPARATOR;
import static org.ivan_smirnov.webserver.util.Constant.HTTP_HEADER;
import static org.ivan_smirnov.webserver.util.Constant.WHITE_SPACE;

public class ResponseWriter {

    public void writeResponse(BufferedWriter writer, Response response) throws IOException {
        writeHeader(writer, response.getStatus());
        String line = response.getContent().readLine();
        while (line != null) {
            writer.write(line);
            line = response.getContent().readLine();
        }
    }

    public void writeResponse(BufferedWriter writer, HttpStatus status) throws IOException {
        writeHeader(writer, status);
    }

    private void writeHeader(BufferedWriter writer, HttpStatus status) throws IOException {
//        String line = HTTP_HEADER + status.getCode() + WHITE_SPACE
//                + status.getMessage() + LINE_SEPARATOR + LINE_SEPARATOR;
//        writer.write(line);
        writer.write(HTTP_HEADER);
        writer.write(String.valueOf(status.getCode()));
        writer.write(WHITE_SPACE);
        writer.write(status.getMessage());
        writer.write(LINE_SEPARATOR);
        writer.write(LINE_SEPARATOR);
    }
}
