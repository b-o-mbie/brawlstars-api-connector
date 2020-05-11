package com.bombie.brawlwatch.brawlstarsapi.util.interceptors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Battle;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.brawler.BrawlerReference;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.FullClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.club.RankingClub;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.exception.BrawlStarsAPIException;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.ClubMemberPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.FullPlayer;
import com.bombie.brawlwatch.brawlstarsapi.domain.response.player.RankingPlayer;
import com.bombie.brawlwatch.brawlstarsapi.util.BrawlStarsAPIProxy;

@Service
public class InterceptableBrawlStarsAPI implements BrawlStarsAPIProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger("OUTPUT");
    private static final int METHOD_NAME_THREAD_STACKSTRACE_LEVEL = 3;

    private List<BrawlStarsAPICallBiInterceptor> interceptors = new ArrayList<>();
    private List<Consumer<BrawlStarsAPIException>> brawlStarsAPIExceptionObservers = new ArrayList<>();

    public void addInterceptors(List<BrawlStarsAPICallBiInterceptor> interceptors) {
        this.interceptors.addAll(interceptors);
    }

    public void addObservers(List<Consumer<BrawlStarsAPIException>> brawlStarsAPIExceptionObservers) {
        this.brawlStarsAPIExceptionObservers.addAll(brawlStarsAPIExceptionObservers);
    }

    @Override
    public FullPlayer getPlayer(String playerTag) throws BrawlStarsAPIException {
        return interceptAPIMethod(playerTag);
    }

    @Override
    public List<Battle> getPlayerBattleLog(String playerTag) throws BrawlStarsAPIException {
        return interceptAPIMethod(playerTag);
    }

    @Override
    public FullClub getClub(String clubTag) throws BrawlStarsAPIException {
        return interceptAPIMethod(clubTag);

    }

    @Override
    public List<ClubMemberPlayer> getClubMembers(String clubTag) throws BrawlStarsAPIException {
        return interceptAPIMethod(clubTag);

    }

    @Override
    public List<RankingPlayer> getPlayerRanking(String country) throws BrawlStarsAPIException {
        return interceptAPIMethod(country);

    }

    @Override
    public List<RankingClub> getClubRanking(String country) throws BrawlStarsAPIException {
        return interceptAPIMethod(country);

    }

    @Override
    public List<RankingPlayer> getBrawlerRanking(String country, int brawlerId) throws BrawlStarsAPIException {
        return interceptAPIMethod(country, brawlerId);

    }

    @Override
    public List<BrawlerReference> getBrawlers() throws BrawlStarsAPIException {
        return interceptAPIMethod();

    }

    @Override
    public BrawlerReference getBrawler(int brawlerId) throws BrawlStarsAPIException {
        return interceptAPIMethod(brawlerId);
    }

    private int numofcalls = 0;
    private int numofapicalls = 0;

    private <V> V interceptAPIMethod(Object... args) {
        LOGGER.warn("NUMBER OF ALL API CALLS: " + ++numofcalls);
        V value = null;
        String getMethodName = getMethodName();
        BrawlStarsAPIException lastException = null;

        Set<BrawlStarsAPICallBiInterceptor> failedToAnswerInterceptors = new HashSet<>();

        for (Iterator<BrawlStarsAPICallBiInterceptor> iterator = interceptors.iterator(); value == null && iterator.hasNext();) {
            BrawlStarsAPICallBiInterceptor interceptor = iterator.next();
            try {
                if (interceptor.getClass() == BrawlStarsAPIProxy.class) {
                    LOGGER.warn("  NUMBER OF REAL API CALLS: " + ++numofapicalls);
                }
                value = makeAPICallInLayer(interceptor, getMethodName, args);
            } catch (BrawlStarsAPIException e) {
                lastException = e;
                brawlStarsAPIExceptionObservers.forEach(observer -> observer.accept(e));
            }
            if (value == null) {
                failedToAnswerInterceptors.add(interceptor);
            }
        }

        if (value == null && lastException != null) {
            throw lastException;
        } else {
            callProperConsumerInterceptors(value, getMethodName, failedToAnswerInterceptors, args);
            return value;
        }
    }

    private <V> void callProperConsumerInterceptors(V value, String getMethodName,
            Set<BrawlStarsAPICallBiInterceptor> failedToAnswerInterceptors,
            Object... args) {
        String setMethodName = getMethodName.replaceFirst("get", "set");

        failedToAnswerInterceptors.forEach(interceptor -> {
            List<Object> valueAndArgs = new ArrayList<>();
            valueAndArgs.add(value);
            valueAndArgs.addAll(Arrays.asList(args));
            makeAPICallInLayer(interceptor, setMethodName, valueAndArgs.toArray());
        });
    }

    private String getMethodName() {
        return Thread.currentThread()
                .getStackTrace()[METHOD_NAME_THREAD_STACKSTRACE_LEVEL]
                        .getMethodName();
    }

    @SuppressWarnings({"unchecked"})
    private <V> V makeAPICallInLayer(BrawlStarsAPIProxy layer, String methodName, Object... args) {
        V value = null;
        try {
            value = (V) findMethodInLayer(methodName, layer).invoke(layer, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException e) {
            if (isExceptionOriginatedInBrawlStarsAPI(e)) {
                throw (BrawlStarsAPIException) e.getCause();
            }
        }
        return value;
    }

    private Method findMethodInLayer(String methodName, BrawlStarsAPIProxy layer) throws NoSuchMethodException {
        return Arrays.stream(layer.getClass().getMethods())
                .filter(m -> m.getName().equals(methodName))
                .findAny()
                .orElseThrow(() -> new NoSuchMethodException());
    }

    private boolean isExceptionOriginatedInBrawlStarsAPI(Exception e) {
        return BrawlStarsAPIException.class.equals(e.getCause().getClass());
    }
}
