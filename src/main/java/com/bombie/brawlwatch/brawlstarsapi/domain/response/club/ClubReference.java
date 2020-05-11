package com.bombie.brawlwatch.brawlstarsapi.domain.response.club;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "tag")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ClubReference implements Comparable<ClubReference> {
    @Id
    private String tag;
    private String name;

    @Override
    public int compareTo(ClubReference other) {
        return tag.compareTo(other.tag);
    }
}
