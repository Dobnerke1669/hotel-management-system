package ro.fortech.bigproject.services;

import ro.fortech.bigproject.services.validators.exceptions.BlankException;

public class GenericService<T> {

    public void checkIfBlank(T t) throws BlankException
    {
        for (java.lang.reflect.Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(t);
                if (value == null || value.toString().isBlank()) {
                    throw new BlankException(field.getName() + " is blank");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
