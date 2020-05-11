package com.bombie.brawlwatch.brawlstarsapi.util.deserializer;

import java.io.IOException;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerStat;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerStat.BrawlerStatBuilder;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class BrawlerStatDeserializer extends StdDeserializer<BrawlerStat> implements ResolvableDeserializer {
    private static final long serialVersionUID = -1230522650563785803L;

    private final JsonDeserializer<BrawlerStat> defaultDeserializer;

    public BrawlerStatDeserializer(JsonDeserializer<BrawlerStat> defaultDeserializer) {
        super(BrawlerStat.class);
        this.defaultDeserializer = defaultDeserializer;
    }

    @Override
    public BrawlerStat deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        BrawlerStatBuilder brawlStatBuilder = new ObjectMapper().readValue(p, BrawlerStatBuilder.class);
        JsonStreamContext parent = p.getParsingContext().getParent();
        FullPlayer owner = (FullPlayer) parent.getCurrentValue();
        return brawlStatBuilder.build(owner);
    }

    @Override
    public void resolve(DeserializationContext ctxt) throws JsonMappingException {
        ((ResolvableDeserializer) defaultDeserializer).resolve(ctxt);
    }

}
