package com.bombie.brawlwatch.brawlstarsapi.util.proxy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.bombie.brawlwatch.brawlstarsapi.domain.response.exception.BrawlStarsAPIResponseHeader;

@Service
public class BrawlStarsAPIResponseHeaderDecoder {

    private static final String JSON_KEY_REGEX = "(?<=\"{key}\":\\s?\")(.*?)(?=\")";

    private static final Pattern RESPONSE_REASON = Pattern.compile(JSON_KEY_REGEX.replaceAll("\\{key\\}", "reason"));
    private static final Pattern RESPONSE_MESSAGE = Pattern.compile(JSON_KEY_REGEX.replaceAll("\\{key\\}", "message"));
    private static final Pattern RESPONSE_TYPE = Pattern.compile("(?<=^\\d* )(.*?)(?=:.*\\[\\{)");
    private static final Pattern RESPONSE_CODE = Pattern.compile("(^\\d+)");

    public BrawlStarsAPIResponseHeader decodeMessage(String header) {

        String reason = getMatch(RESPONSE_REASON.matcher(header));
        String message = getMatch(RESPONSE_MESSAGE.matcher(header));
        int code = Integer.parseInt(getMatch(RESPONSE_CODE.matcher(header)));
        String type = getMatch(RESPONSE_TYPE.matcher(header));

        return new BrawlStarsAPIResponseHeader(reason, message, code, type);
    }

    private String getMatch(Matcher matcher) {
        String match = "";
        if (matcher.find()) {
            match = matcher.group(0);
        }
        return match;
    }
}
