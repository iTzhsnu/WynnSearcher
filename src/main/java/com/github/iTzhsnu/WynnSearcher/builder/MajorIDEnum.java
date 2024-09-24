package com.github.iTzhsnu.WynnSearcher.builder;

import java.util.HashMap;
import java.util.Map;

public enum MajorIDEnum {
    EMPTY,

    DIVINE_HONOR,
    PERFECT_RECALL,
    GENTLE_GLOW,
    FURIOUS_EFFIGY,
    SOUL_EATER,
    MAGNET,
    STRINGS_OF_FATE,
    GREED,
    RALLY,
    MADNESS,
    PLAGUE,
    FORESTS_BLESSING, //TODO
    ALTER_EGO,
    TRANSCENDENCE,
    FISSION, //TODO
    ROVING_ASSASSIN,
    SORCERY,
    HEART_OF_THE_PACK,
    HAWKEYE,
    GUARDIAN,
    GEOCENTRISM,
    SAVIOURS_SACRIFICE,
    RECKLESS_ABANDON,
    ESCAPE_ROUTE,
    CHERRY_BOMBS,
    ENTROPY,
    FREERUNNER,
    LIGHTWEIGHT,
    TAUNT,
    PEACEFUL_EFFIGY,
    FLASHFREEZE,
    GRAVITY_WELL,
    JUGGLE,
    EXPLOSIVE_IMPACT,
    TEMBLOR,
    CAVALRYMAN,
    SNOWY_STEPS,
    FESTIVE_SPIRIT,
    OVERWHELM,

    //NEW 2.0.4
    GRUESOME_KNOTS, //TODO
    COAGULATE,
    DEAD_WEIGHT,
    EXPUNGE,
    HELLFIRE, //TODO
    LUNGE,
    WINDSURF

    ;

    public static final Map<String, MajorIDEnum> GET_MAJOR_IDS = new HashMap<String, MajorIDEnum>() {{
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
        put("Expunge", EXPUNGE);
    }};
}
