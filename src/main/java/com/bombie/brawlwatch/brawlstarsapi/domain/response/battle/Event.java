package com.bombie.brawlwatch.brawlstarsapi.domain.response.battle;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Event {
    @Id
    private int id;
    private String mode;
    private String map;
}
