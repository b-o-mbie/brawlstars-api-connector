package com.bombie.brawlwatch.brawlstarsapi.spring;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.battle.Event;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerStat;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.StarPower;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.util.deserializer.BattleLogDeserializer;
import com.bombie.brawlwatch.brawlstarsapi.util.deserializer.BrawlerStatDeserializer;
import com.bombie.brawlwatch.brawlstarsapi.util.deserializer.EventDeserializer;
import com.bombie.brawlwatch.brawlstarsapi.util.deserializer.FullPlayerDeserializer;
import com.bombie.brawlwatch.brawlstarsapi.util.deserializer.StarPowerDeserializer;
import com.bombie.brawlwatch.brawlstarsapi.util.jsonencoders.BattleLogEncoder;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class JacksonCustomDeserializerConfiguration {

    @PersistenceContext
    private EntityManager em;

    private final class EventDeserializerModifier extends BeanDeserializerModifier {
        @SuppressWarnings("unchecked")
        @Override
        public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> defaultDeser) {
            if (beanDesc.getBeanClass() == Event.class) {
                return new EventDeserializer((JsonDeserializer<Event>) defaultDeser);
            }
            if (beanDesc.getBeanClass() == FullPlayer.class) {
                return new FullPlayerDeserializer((JsonDeserializer<FullPlayer>) defaultDeser);
            }
            if (beanDesc.getBeanClass() == BrawlerStat.class) {
                return new BrawlerStatDeserializer((JsonDeserializer<BrawlerStat>) defaultDeser);
            }
            if (beanDesc.getBeanClass() == StarPower.class) {
                return new StarPowerDeserializer((JsonDeserializer<StarPower>) defaultDeser);
            }
            if (beanDesc.getBeanClass() == Battle.class) {
                return new BattleLogDeserializer(battleLogEncoder);
            }
            return defaultDeser;
        }
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private void addDeserializer() {
        SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new EventDeserializerModifier());
        objectMapper.registerModule(module);
    }

    @Autowired
    BattleLogEncoder battleLogEncoder;
}
