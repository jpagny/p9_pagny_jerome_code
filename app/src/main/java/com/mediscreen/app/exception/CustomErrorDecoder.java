package com.mediscreen.app.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoker, Response response) {

        switch (response.status()) {
            case 400:
                return new PatientBadRequestException("Bad request");
            case 500:
                return new PatientInternalServerError("Internal Server Error");

            default:
                return defaultErrorDecoder.decode(invoker, response);
        }

    }

}
