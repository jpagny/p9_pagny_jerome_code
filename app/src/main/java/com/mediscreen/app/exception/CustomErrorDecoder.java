package com.mediscreen.app.exception;

import com.mediscreen.app.exception.code.BadRequestException;
import com.mediscreen.app.exception.code.ConflictException;
import com.mediscreen.app.exception.code.InternalServerErrorException;
import com.mediscreen.app.exception.code.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoker, Response response) {

        switch (response.status()) {
            case 400:
                log.error("BAD REQUEST");
                return new BadRequestException("Bad request");

            case 404:
                log.error("RESOURCE NOT FOUND");
                return new ResourceNotFoundException("Resource doesn't exist");

            case 409:
                log.error("CONFLICT");
                return new ConflictException("Conflict with another data");

            case 500:
                log.error("INTERNAL SERVER ERROR");
                return new InternalServerErrorException("Internal Server Error");

            default:
                return defaultErrorDecoder.decode(invoker, response);
        }

    }

}
