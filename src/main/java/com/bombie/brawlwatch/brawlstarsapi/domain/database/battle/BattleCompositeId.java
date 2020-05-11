package com.bombie.brawlwatch.brawlstarsapi.domain.database.battle;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BattleCompositeId implements Serializable {
    private static final long serialVersionUID = 6542813869768333167L;
    private LocalDateTime timestamp;
    private String playerListIdentifier;
}
