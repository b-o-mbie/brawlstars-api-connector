package com.bombie.brawlwatch.brawlstarsapi.domain.database.battle;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.battle.Event;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@IdClass(BattleCompositeId.class)
public class Battle {

    @Id
    @Column(name = "id_timestamp")
    private LocalDateTime timestamp;

    @Id
    @Column(name = "id_playerListIdentifier")
    private String playerListIdentifier;

    @ManyToOne(fetch = FetchType.EAGER)
    private Event event;

    private String mode;
    private String type;
    private int duration;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String starPlayerTag;

    @Transient
    private BattlePlayer starPlayer;
    @Transient
    private List<BattlePlayer> players;

    public void setStarPlayer(BattlePlayer starPlayer) {
        this.starPlayer = starPlayer;
        this.starPlayerTag = starPlayer.getTag();
    }

    public void initStarPlayer() {
        if (starPlayerTag != null) {
            starPlayer = players.stream()
                    .filter(p -> p.getTag().equals(starPlayerTag))
                    .findAny().get();
        }
    }
}
