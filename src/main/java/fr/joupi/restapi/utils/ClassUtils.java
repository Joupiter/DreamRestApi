package fr.joupi.restapi.utils;

import lombok.experimental.UtilityClass;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;

@UtilityClass
public class ClassUtils {

    public void registerControllers(AbstractApplication application) {
        new Reflections(application.getClass().getPackage().getName() + ".controller").getSubTypesOf(IController.class)
                .forEach(clazz -> {
                    try {
                        IController controller = clazz.getDeclaredConstructor(application.getClass()).newInstance(application);
                        controller.registerRoutes();
                        System.out.println("[" + application.getName() + "] register routes of " + clazz.getSimpleName());
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                });
    }

    /*public void registerAdapter(AbstractApplication application) {
        new Reflections(application.getClass().getPackage().getName() + ".adapter").getSubTypesOf(IAdapter.class)
                .forEach(clazz -> {
                    try {
                        //ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
                        //Class<?> type = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                        ParameterizedType genericSuperclass = (ParameterizedType) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
                        IAdapter<?> adapter = clazz.getDeclaredConstructor(genericSuperclass.getClass()).newInstance(genericSuperclass);

                        application.getGson().newBuilder().registerTypeAdapter(adapter.getType(), adapter);
                        System.out.println("[" + application.getName() + "] register adapter of " + clazz.getSimpleName());
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                });
    }*/


}
