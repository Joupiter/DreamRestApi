package fr.joupi.restapi;

import be.alexandre01.dreamnetwork.api.connection.core.players.Player;
import com.google.gson.GsonBuilder;
import fr.joupi.restapi.adapter.PlayerAdapter;
import fr.joupi.restapi.utils.AbstractApplication;
import lombok.Getter;

@Getter
public class RestApplication extends AbstractApplication {

    public RestApplication() {
        super("RestApi");
        setup();
    }

    @Override
    public void setup() {

    }

    @Override
    public GsonBuilder gsonBuilder() {
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .registerTypeAdapter(Player.class, new PlayerAdapter());
    }

}
