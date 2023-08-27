package fr.joupi.restapi;

import be.alexandre01.dreamnetwork.api.addons.Addon;
import be.alexandre01.dreamnetwork.api.addons.DreamExtension;
import lombok.Getter;

@Getter
public class RestExtension extends DreamExtension {

    private final RestApplication application;

    public RestExtension(Addon addon) {
        super(addon);
        this.application = new RestApplication();
    }

    @Override
    public void start() {
        getApplication().connect();
    }

    @Override
    public void stop() {
        getApplication().disconnect();
    }

}
