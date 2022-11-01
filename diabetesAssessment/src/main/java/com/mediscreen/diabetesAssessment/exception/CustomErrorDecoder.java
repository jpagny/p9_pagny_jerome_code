package com.mediscreen.diabetesAssessment.exception;

import com.mediscreen.diabetesAssessment.exception.code.BadRequestException;
import com.mediscreen.diabetesAssessment.exception.code.InternalServerErrorException;
import com.mediscreen.diabetesAssessment.exception.code.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoker, Response response) {

        switch (response.status()) {

            case 404:
                log.error("RESOURCE NOT FOUND");
                return new ResourceNotFoundException("Resource doesn't exist");

            default:
                return defaultErrorDecoder.decode(invoker, response);
        }

    }

}
