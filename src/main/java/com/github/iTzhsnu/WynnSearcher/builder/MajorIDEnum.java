package com.github.iTzhsnu.WynnSearcher.builder;

import java.util.HashMap;
import java.util.Map;

public enum MajorIDEnum {
    EMPTY,

    DIVINE_HONOR,
    GENTLE_GLOW,
    FURIOUS_EFFIGY,
    STRINGS_OF_FATE,
    RALLY,
    FORESTS_BLESSING, //TODO
    FISSION, //TODO
    HAWKEYE,
    RECKLESS_ABANDON,
    CHERRY_BOMBS,
    JUGGLE,
    OVERWHELM
    ;

    public static Map<String, MajorIDEnum> GET_MAJOR_IDS = new HashMap<String, MajorIDEnum>() {{
        put("Divine Honor", DIVINE_HONOR);
        put("Gentle Glow", GENTLE_GLOW);
        put("Furious Effigy", FURIOUS_EFFIGY);
        put("Strings of Fate", STRINGS_OF_FATE);
        put("Rally", RALLY);
        put("Forest's Blessing", FORESTS_BLESSING);
        put("Fission", FISSION);
        put("Hawkeye", HAWKEYE);
        put("Reckless Abandon", RECKLESS_ABANDON);
        put("Cherry Bombs", CHERRY_BOMBS);
        put("Juggle", JUGGLE);
        put("Overwhelm", OVERWHELM);
    }};
}
