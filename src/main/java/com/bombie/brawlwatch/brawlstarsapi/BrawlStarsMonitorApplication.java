package com.bombie.brawlwatch.brawlstarsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.bombie.brawlwatch.brawlstarsapi.util.BrawlStarsAPIAdapter;
import com.bombie.brawlwatch.brawlstarsapi.util.BrawlStarsAPIProxy;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.BrawlStarsAPIDatabaseInterceptor;

@SpringBootApplication
@SuppressWarnings("unused")
public class BrawlStarsMonitorApplication {

    private static final String PLAYERTAG = "#2PG9UCPU9";
    private static final String CLUBTAG = "#G908GGPC";

    @Autowired
    private BrawlStarsAPIAdapter connector;

    @Autowired
    private BeginnerPlayerMinerService miner;

    @Autowired
    private BrawlStarsAPIDatabaseInterceptor db;

    public static void main(String[] args) {
        SpringApplication.run(BrawlStarsMonitorApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            connector.findBrawlers();

            connector.findPlayerBattleLog("#VYVPLJU0");

            //miner.getALowerAndLowerIteration(3);
            playerTest(connector);
            //battleLogTest(connector);

            //playerTest(db);
            //battleLogTest(db);

            fullTest(connector);
        };
    }

    private void playerTest(BrawlStarsAPIProxy proxy) {
        proxy.getPlayer(PLAYERTAG);
        proxy.getPlayer("#Y2YQGUJC");
        proxy.getPlayer("#2UP0C8CGQ");
        proxy.getPlayer("#28JQYRL0");
    }

    private void battleLogTest(BrawlStarsAPIProxy proxy) {
        proxy.getPlayerBattleLog(PLAYERTAG);
        proxy.getPlayerBattleLog("#2UP0C8CGQ");
        proxy.getPlayerBattleLog("#28JQYRL0");
    }

    private void fullTest(BrawlStarsAPIProxy proxy) {
        proxy.getPlayer(PLAYERTAG);
        proxy.getPlayerBattleLog(PLAYERTAG);

        proxy.getClub(CLUBTAG);
        proxy.getClubMembers(CLUBTAG);

        proxy.getBrawlers();
        proxy.getBrawler(16000000);

        proxy.getPlayerRanking("global");
        proxy.getClubRanking("global");
        proxy.getBrawlerRanking("global", 16000000);
    }
}
