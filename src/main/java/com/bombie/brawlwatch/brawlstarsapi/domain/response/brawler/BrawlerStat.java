package com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = {"genId"})
@NoArgsConstructor
@Entity
public class BrawlerStat implements GenericBrawler {

    @Data
    public static class BrawlerStatBuilder {
        private int id;
        private String name;
        private int rank;
        private int trophies;
        private int highestTrophies;
        private int power;
        private Set<StarPower> starPowers;

        public BrawlerStat build(FullPlayer owner) {
            return new BrawlerStat(this, owner);
        }
    }

    private BrawlerStat(BrawlerStatBuilder BrawlerStatBuilder, FullPlayer owner) {
        this.owner = owner;
        this.id = BrawlerStatBuilder.getId();
        this.name = BrawlerStatBuilder.getName();
        this.rank = BrawlerStatBuilder.getRank();
        this.trophies = BrawlerStatBuilder.getTrophies();
        this.highestTrophies = BrawlerStatBuilder.getHighestTrophies();
        this.power = BrawlerStatBuilder.getPower();
        this.starPowers = BrawlerStatBuilder.getStarPowers();

        this.genId = owner.getTag() + id;
    }

    @Id
    private String genId;

    @ToString.Exclude
    @ManyToOne
    private FullPlayer owner;

    private int id;

    private String name;

    private int rank;
    private int trophies;
    private int highestTrophies;
    private int power;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<StarPower> starPowers;
}
