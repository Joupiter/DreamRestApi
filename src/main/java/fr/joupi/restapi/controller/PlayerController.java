package fr.joupi.restapi.controller;

import be.alexandre01.dreamnetwork.api.DNCoreAPI;
import be.alexandre01.dreamnetwork.api.connection.core.players.Player;
import fr.joupi.restapi.RestApplication;
import fr.joupi.restapi.utils.AbstractApplication;
import fr.joupi.restapi.utils.IController;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PlayerController implements IController {

    private final RestApplication application;

    public int getTotalPlayer() {
        return DNCoreAPI.getInstance().getServicePlayersManager().getPlayersMap().size();
    }

    public Player getPlayer(String playerName) {
        return DNCoreAPI.getInstance().getServicePlayersManager().getPlayersMap()
                .values()
                .stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElse(null);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(DNCoreAPI.getInstance().getServicePlayersManager().getPlayersMap().values());
    }

    @Override
    public AbstractApplication getApplication() {
        return this.application;
    }

    @Override
    public void registerRoutes() {
        get("/players", ctx -> ctx.json(serialize(getPlayers())));
        get("/players/total", ctx -> ctx.json(getTotalPlayer()));
        get("/players/{name}", ctx -> ctx.json(serialize(getPlayer(ctx.pathParam("{name}")))));
    }

}

