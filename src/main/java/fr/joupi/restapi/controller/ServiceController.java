package fr.joupi.restapi.controller;

import be.alexandre01.dreamnetwork.api.DNCoreAPI;
import be.alexandre01.dreamnetwork.api.service.IService;
import fr.joupi.restapi.RestApplication;
import fr.joupi.restapi.utils.AbstractApplication;
import fr.joupi.restapi.utils.IController;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ServiceController implements IController {

    private final RestApplication application;

    public int getTotalService() {
        AtomicInteger total = new AtomicInteger(0);
        DNCoreAPI.getInstance().getContainer().getJVMExecutors().forEach(jvm -> jvm.getServices().forEach(server -> total.addAndGet(1)));
        return total.get();
    }

    public IService getService(String bundleName, String serviceName) {
        return DNCoreAPI.getInstance().getContainer().tryToGetService(bundleName + "/" + serviceName);
    }

    public Map<String, List<String>> getServices() {
        //Map<IJVMExecutor, IService> services = new HashMap<>();
        Map<String, List<String>> services = new HashMap<>();

        DNCoreAPI.getInstance().getContainer().getJVMExecutors().forEach(ijvmExecutor -> services.put(ijvmExecutor.getName(), ijvmExecutor.getServices().stream().map(IService::getFullName).collect(Collectors.toList())));

        return services;
    }

    @Override
    public AbstractApplication getApplication() {
        return this.application;
    }

    @Override
    public void registerRoutes() {
        get("/services", ctx -> ctx.json(getServices()));
        get("/services/total", ctx -> ctx.json(getTotalService()));
        get("/services/{bundle}/{name}", ctx -> ctx.json(getService(ctx.pathParam("{bundle}"), ctx.pathParam("{name}")).toString()));
        /*get("/services/{bundle}/{name}/screen", ctx -> {
            //DNCoreAPI.getInstance().getScreenManager().getScreen(getService(ctx.pathParam("{bundle}"), ctx.pathParam("{name}")).getFullName()).getScreenStream().getScreenInReader().getReaderLines().add(s -> s);
        });*/
    }

}
