package com.car.rental.application.aspects.validation;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;
import com.car.rental.application.shared.Request;
import com.car.rental.domain.exceptions.InvalidRequestException;
import com.car.rental.domain.exceptions.models.InvalidRequestExceptionModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
public class RequestValidatorAspect<TRequest extends Request<TResponse>, TResponse> {

    private final List<Validator<TRequest>> validators;

    public RequestValidatorAspect(List<Validator<TRequest>> validators) {
        this.validators = validators;
    }

    @Before(value = "execution(* com.car.rental.application.shared.RequestHandler.handle(..)) && args(request)")
    public void beforeRequestHandler(JoinPoint joinPoint, TRequest request) {
        Object[] args = joinPoint.getArgs();

        Optional<Validator<TRequest>> validator = getValidatorForRequest(request);
        if (validator.isPresent()) {
            ValidationResult validationResult = validator.get().validate(request);
            if (!validationResult.isValid()) {
                List<InvalidRequestExceptionModel> errors = validationResult.getErrors().stream()
                        .map(error -> new InvalidRequestExceptionModel(error.getField(), error.getMessage()))
                        .toList();

                throw new InvalidRequestException(errors);
            }
        }
    }

    private Optional<Validator<TRequest>> getValidatorForRequest(TRequest request) {
        return validators.stream().filter(x-> isValidatorForType(x, request)).findFirst();
    }

    private <T> boolean isValidatorForType(Validator<T> validator, T type) {
        Type[] argumentTypes = ((java.lang.reflect.ParameterizedType) validator.getClass().getGenericSuperclass()).getActualTypeArguments();
        return argumentTypes.length > 0 && argumentTypes[0].equals(type.getClass());
    }
}
