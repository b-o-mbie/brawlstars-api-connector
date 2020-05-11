package com.bombie.brawlwatch.brawlstarsapi.util.deserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.battle.Event;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class EventDeserializer extends StdDeserializer<Event> implements ResolvableDeserializer {
    private static final long serialVersionUID = -1230522650563785803L;

    private final JsonDeserializer<Event> defaultDeserializer;

    public EventDeserializer(JsonDeserializer<Event> defaultDeserializer) {
        super(Event.class);
        this.defaultDeserializer = defaultDeserializer;
    }

    private Map<Integer, Event> loadedEvents = new HashMap<>();

    @Override
    public Event deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Event event = defaultDeserializer.deserialize(p, ctxt);

        if (loadedEvents.containsKey(event.getId())) {
            event = loadedEvents.get(event.getId());
        } else {
            loadedEvents.put(event.getId(), event);
        }

        return event;
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        ((ResolvableDeserializer) defaultDeserializer).resolve(ctxt);
    }

}
