package com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class BrawlerReference implements GenericBrawler {
    @Id
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reference", fetch = FetchType.EAGER)
    private Set<StarPower> starPowers;
}
