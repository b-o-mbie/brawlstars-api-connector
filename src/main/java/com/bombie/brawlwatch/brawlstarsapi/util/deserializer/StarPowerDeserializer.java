package com.bombie.brawlwatch.brawlstarsapi.util.deserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.StarPower;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class StarPowerDeserializer extends StdDeserializer<StarPower> implements ResolvableDeserializer {
    private static final long serialVersionUID = 6006420451434190084L;

    private final JsonDeserializer<StarPower> defaultDeserializer;

    public StarPowerDeserializer(JsonDeserializer<StarPower> defaultDeserializer) {
        super(StarPower.class);
        this.defaultDeserializer = defaultDeserializer;
    }

    private Map<Integer, StarPower> loadedStarPowers = new HashMap<>();

    @Override
    public StarPower deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        StarPower starPower = defaultDeserializer.deserialize(p, ctxt);

        JsonStreamContext parent = p.getParsingContext().getParent();
        BrawlerReference brawlerReference = (BrawlerReference) parent.getCurrentValue();
        starPower.setReference(brawlerReference);

        if (loadedStarPowers.containsKey(starPower.getId())) {
            starPower = loadedStarPowers.get(starPower.getId());
        } else {
            loadedStarPowers.put(starPower.getId(), starPower);
        }
        return starPower;
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        ((ResolvableDeserializer) defaultDeserializer).resolve(ctxt);
    }

}
