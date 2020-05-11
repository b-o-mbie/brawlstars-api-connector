package com.bombie.brawlwatch.brawlstarsapi.domain.response.club;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
@Entity
public class FullClub extends ClubReference {

    private String description;
    private String type;
    private int requiredTrophies;
    private int trophies;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<ClubMemberPlayer> members;
}
