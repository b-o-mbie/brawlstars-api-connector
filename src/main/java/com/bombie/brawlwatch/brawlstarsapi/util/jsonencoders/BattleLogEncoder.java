package com.bombie.brawlwatch.brawlstarsapi.util.jsonencoders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.BattlePlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Result3v3;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.battle.BattleData;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.battle.BattleLogData;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.battle.BattleLogPlayerData;
import com.bombie.brawlwatch.brawlstarsapi.util.etc.TrophyChangeCalculator;

@Service
public class BattleLogEncoder {
    private static final int _3v3_WINNER_TEAM_DISCRIMINATOR = 1;
    private static final int _3v3_DEFEAT_TEAM_DISCRIMINATOR = 4;

    @Autowired
    TrophyChangeCalculator trophyChangeCalculator;

    private static final int _3v3_TEAM_SIZE = 3;
    private static final int _3v3_TEAM_COUNT = 2;

    public Battle buildBattle(BattleLogData data) {
        Battle battle = new Battle();
        battle.setPlayerListIdentifier(generateBattleLogTag(data.getBattle()));
        battle.setTimestamp(data.getBattleTime());
        battle.setEvent(data.getEvent());
        initBattleData(data.getBattle(), battle);
        return battle;
    }

    private String generateBattleLogTag(BattleData data) {
        List<BattleLogPlayerData> players = getPlayersNoMatterWhat(data);
        return players.stream()
                .map(player -> player.getTag())
                .sorted()
                .findFirst().get();
    }

    private void initBattleData(BattleData data, Battle battle) {
        battle.setMode(data.getMode());
        battle.setType(data.getType());
        battle.setDuration(data.getDuration());
        List<BattlePlayer> players = buildBattlePlayers(data, battle);
        battle.setPlayers(players);
        if (data.getStarPlayer() != null) {
            BattlePlayer starPlayer = players.stream()
                    .map(p -> {
                        return p;
                    })
                    .filter(player -> player.getTag().equals(data.getStarPlayer().getTag()))
                    .map(p -> {
                        return p;
                    })
                    .findAny().get();
            battle.setStarPlayer(starPlayer);
        }
    }

    private List<BattleLogPlayerData> getPlayersNoMatterWhat(BattleData data) {
        List<BattleLogPlayerData> players = data.getPlayers();
        if (players == null) {
            players = data.getTeams().stream()
                    .flatMap(team -> team.stream())
                    .collect(Collectors.toList());
        }
        return players;
    }

    public List<BattlePlayer> buildBattlePlayers(BattleData data, Battle battle) {
        List<BattlePlayer> players = new ArrayList<>();

        if (isBattle3v3(data)) {
            players = getPlayersAs3v3(data);
        }

        if (isBattleDuoShowdown(data)) {
            players = getPlayersAsDuo(data);
        }

        if (isBattleSoloShowdown(data)) {
            players = getPlayersAsSolo(data);
        }

        players.forEach(player -> {
            player.setTimestamp(battle.getTimestamp());
            player.setPlayerListIdentifier(battle.getPlayerListIdentifier());
        });

        return players;
    }

    /* BUILD PLAYER AS {MODE} */

    private List<BattlePlayer> getPlayersAsSolo(BattleData data) {
        List<BattlePlayer> players;
        players = data.getPlayers().stream()
                .map(playerData -> buildPlayer(playerData, data.getPlayers().indexOf(playerData) + 1))
                .map(p -> {
                    initSoloTrophyChange(p);
                    return p;
                })
                .collect(Collectors.toList());
        return players;
    }

    private List<BattlePlayer> getPlayersAsDuo(BattleData data) {
        List<BattlePlayer> players;
        players = data.getTeams().stream()
                .flatMap(team -> team.stream()
                        .map(playerData -> buildPlayer(playerData, data.getTeams().indexOf(team) + 1)))
                .map(p -> {
                    initDuoTrophyChange(p);
                    return p;
                })
                .collect(Collectors.toList());
        return players;
    }

    private List<BattlePlayer> getPlayersAs3v3(BattleData data) {
        List<BattlePlayer> players;
        players = data.getTeams().stream()
                .flatMap(team -> team.stream()
                        .map(playerData -> buildPlayer(playerData, get3v3TeamDiscriminator(data, team))))
                .map(p -> {
                    init3v3Result(p);
                    init3v3TrophyChange(p);
                    return p;
                })
                .collect(Collectors.toList());
        return players;
    }

    private int get3v3TeamDiscriminator(BattleData data, List<BattleLogPlayerData> team) {
        int discriminator = 0;
        if (data.getResult().toLowerCase().equals("draw")) {
            discriminator = data.getTeams().indexOf(team) + 2;
        } else {
            discriminator = get3v3NonDrawTeamDiscriminator(data, team);
        }

        return discriminator;
    }

    private int get3v3NonDrawTeamDiscriminator(BattleData data, List<BattleLogPlayerData> team) {
        return team.stream()
                .map(player -> player.getTag())
                .filter(tag -> tag.equals(data.getStarPlayer().getTag()))
                .findAny().isPresent() ? _3v3_WINNER_TEAM_DISCRIMINATOR : _3v3_DEFEAT_TEAM_DISCRIMINATOR;
    }

    private BattlePlayer buildPlayer(BattleLogPlayerData data, int teamDiscriminator) {
        BattlePlayer player = new BattlePlayer();
        player.setBrawler(data.getBrawler());
        player.setTag(data.getTag());
        player.setName(data.getName());
        player.setTeamDiscriminator(teamDiscriminator);
        return player;
    }

    /* INIT PLAYER RESULT */

    private void init3v3Result(BattlePlayer player) {
        Result3v3 result = Result3v3.DRAW;
        if (player.getTeamDiscriminator() == 1) {
            result = Result3v3.VICTORY;
        } else if (player.getTeamDiscriminator() == 4) {
            result = Result3v3.DEFEAT;
        }
        player.setResult(result);
    }

    /* INIT PLAYER TROPHYCHANGE */

    private void initSoloTrophyChange(BattlePlayer player) {
        player.setTrophyChange(trophyChangeCalculator.getSolo(player.getTeamDiscriminator(), player.getBrawler().getTrophies()));
    }

    private void initDuoTrophyChange(BattlePlayer player) {
        player.setTrophyChange(trophyChangeCalculator.getDuo(player.getTeamDiscriminator(), player.getBrawler().getTrophies()));
    }

    private void init3v3TrophyChange(BattlePlayer player) {
        player.setTrophyChange(trophyChangeCalculator.get3v3(player.getResult(), player.getBrawler().getTrophies()));
    }

    /* IS BATTLE {MODE} */

    private boolean isBattleSoloShowdown(BattleData data) {
        return data.getMode() != null && data.getMode().equals("soloShowdown");
    }

    private boolean isBattleDuoShowdown(BattleData data) {
        return data.getMode() != null && data.getMode().equals("duoShowdown");
    }

    private boolean isBattle3v3(BattleData data) {
        return data.getTeams() != null
                && data.getTeams().size() == _3v3_TEAM_COUNT
                && data.getTeams().stream()
                        .allMatch(team -> team.size() == _3v3_TEAM_SIZE);
    }

}
