package com.bombie.brawlwatch.brawlstarsapi.domain.database.battle;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BattlePlayerCompositeId implements Serializable {
    private static final long serialVersionUID = 5074004890377432373L;
    private LocalDateTime timestamp;
    private String playerListIdentifier;
    private String tag;

}
