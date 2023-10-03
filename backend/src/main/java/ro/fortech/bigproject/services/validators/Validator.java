package ro.fortech.bigproject.services.validators;


public interface Validator<T> {

    void validate(T t) throws Exception;

}
