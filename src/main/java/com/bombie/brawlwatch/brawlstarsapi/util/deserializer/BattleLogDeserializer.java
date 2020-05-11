package com.bombie.brawlwatch.brawlstarsapi.util.deserializer;

import java.io.IOException;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.battle.BattleLogData;
import com.bombie.brawlwatch.brawlstarsapi.util.jsonencoders.BattleLogEncoder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class BattleLogDeserializer extends StdDeserializer<Battle> {
    private static final long serialVersionUID = -1230522650563785803L;

    public BattleLogDeserializer(BattleLogEncoder encoder) {
        super(Battle.class);
        this.encoder = encoder;
    }

    private BattleLogEncoder encoder;

    @Override
    public Battle deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        BattleLogData battleLogData = new ObjectMapper().readValue(p, BattleLogData.class);
        Battle battle = encoder.buildBattle(battleLogData);
        return battle;
    }
}
