package com.car.rental.application.shared;

public interface RequestHandler<TRequest extends Request<TResponse>, TResponse> {
    TResponse handle(TRequest request);
}
