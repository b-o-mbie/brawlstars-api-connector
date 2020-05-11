package com.bombie.brawlwatch.brawlstarsapi;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.util.BrawlStarsAPIAdapter;

@Service
public class BeginnerPlayerMinerService {

    private static final Logger LOGGER = LoggerFactory.getLogger("OUTPUT");
    private static final Set<FullClub> OPTIMAL_CLUBS = new ConcurrentSkipListSet<>();

    @Autowired
    BrawlStarsAPIAdapter api;

    public void getALowerAndLowerIteration(int n) {
        Collection<String> tags = api.getPlayerRanking("global").stream()
                .map(p -> p.getTag())
                .collect(Collectors.toList());

        for (int i = 0; i < n; i++) {
            LOGGER.warn("ITERATION NUMBER " + i + " players: " + tags.size());
            new PlayerAndClubDataLoader(tags).start();
            tags = getNeighbours(tags);
        }

        LOGGER.warn("CRAWLING STOPPED.");
    }

    public Set<String> getNeighbours(Collection<String> tags) {
        System.out.println(tags.size());
        return tags.parallelStream()
                .flatMap(tag -> api.getPlayerBattleLog(tag).stream())
                .flatMap(battle -> battle.getPlayers().stream())
                .map(player -> player.getTag())
                .collect(Collectors.toSet());
    }

    class PlayerAndClubDataLoader extends Thread {

        private Collection<String> playerTags;

        public PlayerAndClubDataLoader(Collection<String> playerTags) {
            super();
            this.playerTags = playerTags;
        }

        @Override
        public void run() {
            playerTags.parallelStream()
                    .forEach(playerTag -> api.findPlayer(playerTag)
                            .map(p -> p.getClub().getTag())
                            .map(clubTag -> api.getClub(clubTag))
                            .filter(club -> club.getRequiredTrophies() <= 16000 && club.getDescription().contains("dc"))
                            .ifPresent(club -> {
                                if (OPTIMAL_CLUBS.add(club)) {
                                    LOGGER.warn("FOUND OPTIMAL CLUB: " + club);
                                }
                            }));
        }
    }
}
