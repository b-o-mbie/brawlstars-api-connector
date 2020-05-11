package com.bombie.brawlwatch.brawlstarsapi.domain.response.player;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerStat;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.ClubReference;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "tag")
@Entity
public class FullPlayer implements PlayerReference {
    @Id
    private String tag;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private ClubReference club;
    private String nameColor;

    private int trophies;
    private int expLevel;
    private int expPoints;

    @JsonAlias("3vs3Victories")
    private String threeVsThreeVictories;
    private boolean isQualifiedFromChampionshipChallenge;
    private int highestTrophies;
    private int powerPlayPoints;
    private int highestPowerPlayPoints;
    private int soloVictories;
    private int duoVictories;
    private int bestRoboRumbleTime;
    private int bestTimeAsBigBrawler;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<BrawlerStat> brawlers;
}
