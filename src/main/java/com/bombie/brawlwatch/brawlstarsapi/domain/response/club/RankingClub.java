package com.bombie.brawlwatch.brawlstarsapi.domain.response.club;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RankingClub extends ClubReference {
    private int throphies;
    private int rank;
    private int memberCount;
}
