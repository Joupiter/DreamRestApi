package fr.joupi.restapi.adapter;

import be.alexandre01.dreamnetwork.api.connection.core.players.Player;
import com.google.gson.*;
import fr.joupi.restapi.utils.IAdapter;

import java.lang.reflect.Type;

public class PlayerAdapter implements IAdapter<Player> {

    @Override
    public JsonElement serialize(Player player, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", player.getId());
        jsonObject.addProperty("name", player.getName());
        //jsonObject.addProperty("uuid", player.getUuid().toString());
        jsonObject.addProperty("server", player.getServer().getJvmService().getFullName());

        return jsonObject;
    }

}
