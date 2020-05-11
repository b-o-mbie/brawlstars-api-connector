package com.bombie.brawlwatch.brawlstarsapi.domain.response.player;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ClubMemberPlayer implements PlayerReference {
    private String tag;
    private String name;
    private String nameColor;
    private String role;
    private int trophies;

}
