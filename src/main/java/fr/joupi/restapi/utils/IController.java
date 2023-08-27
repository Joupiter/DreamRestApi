package fr.joupi.restapi.utils;

import io.javalin.http.ExceptionHandler;
import io.javalin.http.Handler;

public interface IController {

    AbstractApplication getApplication();

    void registerRoutes();

    default void get(String route, Handler context) {
        getApplication().getJavalin().get(route, context).exception(Exception.class, handleException());
    }

    default void post(String route, Handler context) {
        getApplication().getJavalin().post(route, context).exception(Exception.class, handleException());
    }

    default void put(String route, Handler context) {
        getApplication().getJavalin().put(route, context).exception(Exception.class, handleException());
    }

    default void patch(String route, Handler context) {
        getApplication().getJavalin().post(route, context).exception(Exception.class, handleException());
    }

    default void delete(String route, Handler context) {
        getApplication().getJavalin().delete(route, context).exception(Exception.class, handleException());
    }

    default String serialize(Object object) {
        return getApplication().getGson().toJson(object);
    }

    private ExceptionHandler<Exception> handleException() {
        return (exception, ctx) -> exception.printStackTrace();
    }

}
