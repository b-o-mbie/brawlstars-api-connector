package com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class StarPower {
    @Id
    private int id;
    private String name;
    @ManyToOne
    @ToString.Exclude
    private BrawlerReference reference;
}
