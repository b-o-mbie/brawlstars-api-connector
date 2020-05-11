package com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.exception.BrawlStarsAPIException;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.BrawlStarsAPICallBiInterceptor;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories.BattlePlayerRepository;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories.BattleRepository;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories.BrawlerReferenceRepository;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories.EventRepository;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories.FullClubRepository;
import com.bombie.brawlwatch.brawlstarsapi.util.interceptors.backup.repositories.FullPlayerRepository;

@Service
public class BrawlStarsAPIDatabaseInterceptor implements BrawlStarsAPICallBiInterceptor {

    @Autowired
    private FullPlayerRepository playerRepo;

    @Autowired
    private BattleRepository battleRepo;

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private BattlePlayerRepository battlePlayerRepo;

    @Autowired
    private FullClubRepository fullClubRepo;

    @Autowired
    private BrawlerReferenceRepository brawlerReferenceRepo;

    @Override
    public void setPlayer(FullPlayer value, String playerTag) {
        FullClub clubRef = fullClubRepo.findById(value.getClub().getTag()).orElse(new FullClub());
        clubRef.setTag(value.getClub().getTag());
        clubRef.setName(value.getClub().getName());
        playerRepo.save(value);
    }

    @Override
    public void setPlayerBattleLog(List<Battle> value, String playerTag) {
        value.forEach(this::saveBattle);
    }

    private void saveBattle(Battle battle) {
        if (!eventRepo.existsById(battle.getEvent().getId())) {
            eventRepo.save(battle.getEvent());
        }
        battle.getPlayers().forEach(battlePlayerRepo::save);
        battleRepo.save(battle);
    }

    @Override
    public void setClub(FullClub value, String clubTag) {
        fullClubRepo.save(value);
    }

    @Override
    public void setClubMembers(List<ClubMemberPlayer> value, String clubTag) {
        FullClub club = fullClubRepo.findById(clubTag).orElse(new FullClub());
        Set<ClubMemberPlayer> members = value.stream().collect(Collectors.toSet());
        club.setTag(clubTag);
        club.setMembers(members);
        club.setTrophies(members.stream()
                .map(member -> member.getTrophies())
                .reduce(0, Math::addExact));
        fullClubRepo.save(club);
    }

    @Override
    public void setPlayerRanking(List<RankingPlayer> value, String country) {
    }

    @Override
    public void setClubRanking(List<RankingClub> value, String country) {
    }

    @Override
    public void setBrawlerRanking(List<RankingPlayer> value, String country, int brawlerId) {
    }

    @Override
    public void setBrawlers(List<BrawlerReference> value) {
        brawlerReferenceRepo.saveAll(value);
    }

    @Override
    public void setBrawler(BrawlerReference value, int brawlerId) {
        brawlerReferenceRepo.save(value);
    }

    @Override
    public FullPlayer getPlayer(String playerTag) throws BrawlStarsAPIException {
        return playerRepo.findById(playerTag).get();
    }

    @Override
    public List<Battle> getPlayerBattleLog(String playerTag) throws BrawlStarsAPIException {
        return battlePlayerRepo.findFirst25ByTagOrderByTimestampDesc(playerTag).stream()
                .map(p -> battleRepo.findByTimestampAndPlayerListIdentifier(p.getTimestamp(), p.getPlayerListIdentifier()).orElse(null))
                .filter(battle -> battle != null)
                .map(b -> {
                    b.setPlayers(battlePlayerRepo.findAllByTimestampAndPlayerListIdentifier(b.getTimestamp(), b.getPlayerListIdentifier()));
                    b.initStarPlayer();
                    return b;
                })
                .collect(Collectors.toList());
    }

    @Override
    public FullClub getClub(String clubTag) throws BrawlStarsAPIException {
        return fullClubRepo.findById(clubTag).get();
    }

    @Override
    public List<ClubMemberPlayer> getClubMembers(String clubTag) throws BrawlStarsAPIException {
        return fullClubRepo.findById(clubTag)
                .map(club -> club.getMembers())
                .map(memberSet -> new ArrayList<>(memberSet))
                .orElse(new ArrayList<>());
    }

    @Override
    public List<RankingPlayer> getPlayerRanking(String country) throws BrawlStarsAPIException {
        return null;
    }

    @Override
    public List<RankingClub> getClubRanking(String country) throws BrawlStarsAPIException {
        return null;
    }

    @Override
    public List<RankingPlayer> getBrawlerRanking(String country, int brawlerId) throws BrawlStarsAPIException {
        return null;
    }

    @Override
    public List<BrawlerReference> getBrawlers() throws BrawlStarsAPIException {
        return brawlerReferenceRepo.findAll();
    }

    @Override
    public BrawlerReference getBrawler(int brawlerId) throws BrawlStarsAPIException {
        return brawlerReferenceRepo.findById(brawlerId).get();
    }

}
