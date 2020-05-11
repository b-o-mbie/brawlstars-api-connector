package com.bombie.brawlwatch.brawlstarsapi.util.deserializer;

import java.io.IOException;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FullPlayerDeserializer extends StdDeserializer<FullPlayer> implements ResolvableDeserializer {
    private static final long serialVersionUID = -1230522650563785803L;

    private final JsonDeserializer<FullPlayer> defaultDeserializer;

    public FullPlayerDeserializer(JsonDeserializer<FullPlayer> defaultDeserializer) {
        super(FullPlayer.class);
        this.defaultDeserializer = defaultDeserializer;
    }

    @Override
    public FullPlayer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        FullPlayer fullPlayer = defaultDeserializer.deserialize(p, ctxt);
        return fullPlayer;
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        ((ResolvableDeserializer) defaultDeserializer).resolve(ctxt);
    }
}
