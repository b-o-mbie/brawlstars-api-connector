package com.bombie.brawlwatch.brawlstarsapi.domain.database.battle;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.springframework.lang.Nullable;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.battle.BattleLogBrawlerData;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.PlayerReference;

import lombok.Data;

@Data
@Entity
@IdClass(BattlePlayerCompositeId.class)
public class BattlePlayer implements PlayerReference {

    @Id
    @Column(name = "id_timestamp")
    private LocalDateTime timestamp;

    @Id
    @Column(name = "id_playerListIdentifier")
    private String playerListIdentifier;

    @Id
    @Column(name = "id_tag")
    private String tag;

    private String name;

    @Embedded
    private BattleLogBrawlerData brawler;

    private int teamDiscriminator; // also rank
    private int trophyChange;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Result3v3 result;
}
