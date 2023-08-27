package fr.joupi.restapi.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import lombok.Getter;

@Getter
public abstract class AbstractApplication {

    private final Javalin javalin;
    private final String name;

    private final Gson gson;

    protected AbstractApplication(String name) {
        this.javalin = Javalin.create();
        this.name = name;
        this.gson = gsonBuilder().create();
    }

    public abstract void setup();

    public abstract GsonBuilder gsonBuilder();

    public void connect() {
        System.out.println("[" + getName() + "] started on http://localhost:7070/");
        new Thread(() -> getJavalin().start(7070)).start();
        ClassUtils.registerControllers(this);
    }

    public void disconnect() {
        System.out.println("[" + getName() + "] shutdown");
        getJavalin().stop();
    }

}
