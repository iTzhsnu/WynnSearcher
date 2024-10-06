package com.github.iTzhsnu.WynnSearcher.builder;

import java.util.HashMap;
import java.util.Map;

public enum MajorIDEnum {
    EMPTY,

    TWISTING_THREADS,
    LIFESTREAM,
    FIXATE,
    INSOLUBLE,
    EFFLORESCENCE,
    PERFECT_RECALL,
    FURIOUS_EFFIGY,
    SOUL_EATER,
    STRINGS_OF_FATE,
    RALLY,
    METEOR_CRASH,
    GREED,
    FORESTS_BLESSING,
    DEAD_WEIGHT,
    DIVINE_HONOR,
    FISSION,
    FREERUNNER,
    TRANSCENDENCE,
    ROVING_ASSASSIN,
    TOTEMIC_FUSE,
    HEART_OF_THE_PACK,
    GUARDIAN,
    GEOCENTRISM,
    SAVIOURS_SACRIFICE,
    SUBLIMATION,
    FALLOUT,
    CHERRY_BOMBS,
    SEEKING_MODULE,
    ENTROPY,
    PARAGON,
    PEACEFUL_EFFIGY,
    LUSTRATE,
    WINDSURF,
    HELLFIRE,
    LAST_RESORT,
    SORCERY,
    LOCKDOWN,
    FRENETIC_SPIRIT,
    GRAVITY_WELL,
    JUGGLE,
    DISPLACE,
    TACKLE,
    PLAGUE,
    FLURRY_OF_BLOWS,
    PERILOUS_FLARE,
    FLASHFREEZE,
    EVERSHOT,
    HURRICANES_EYE,
    GRUESOME_KNOTS,
    TEMBLOR,
    HAWKEYE,
    OVERWHELM,
    NAPALM,
    POUNCE,
    GENTLE_GLOW,
    MAGNET,
    EXPUNGE,
    ALTER_EGO,
    CAVALRYMAN,
    RECKLESS_ABANDON,
    ESCAPE_ROUTE,
    LIGHTWEIGHT,
    LUNGE,
    TAUNT,
    ROCK_SHIELD,
    MANGLE,
    RUSTED_ICHOR,
    EXPLOSIVE_IMPACT,
    SNOWY_STEPS,
    FESTIVE_SPIRIT,
    FIND_THYSELF,
    MADNESS,
    COAGULATE,
    POWER_FIST

    ;

    public static final Map<String, MajorIDEnum> GET_MAJOR_IDS = new HashMap<String, MajorIDEnum>() {{
        put("Twisting Threads", TWISTING_THREADS); //TODO
        put("Fixate", FIXATE); //TODO x4/3 Winded Effect
        put("Lifestream", LIFESTREAM); //TODO
        put("Insoluble", INSOLUBLE); //TODO (Low Priority)
        put("Efflorescence", EFFLORESCENCE); //TODO
        put("Furious Effigy", FURIOUS_EFFIGY); //NEED CHECK
        put("Rally", RALLY);
        put("Meteor Crash", METEOR_CRASH); //TODO
        put("Strings of Fate", STRINGS_OF_FATE);
        put("Forest's Blessing", FORESTS_BLESSING); //NEED CHECK
        put("Divine Honor", DIVINE_HONOR);
        put("Fission", FISSION); //Low Priority
        put("Totemic Fuse", TOTEMIC_FUSE); //TODO
        put("Heart of the Pack", HEART_OF_THE_PACK); //Low Priority
        put("Sublimation", SUBLIMATION); //TODO (Low Priority)
        put("Fallout", FALLOUT); //TODO
        put("Cherry Bombs", CHERRY_BOMBS); //NEED CHECK
        put("Paragon", PARAGON); //TODO
        put("Lustrate", LUSTRATE); //TODO
        put("Hellfire", HELLFIRE); //TODO
        put("Last Resort", LAST_RESORT); //TODO
        put("Frenetic Spirit", FRENETIC_SPIRIT); //Low Priority
        put("Juggle", JUGGLE); //NEED CHECK
        put("Tackle", TACKLE); //TODO
        put("Flurry of Blows", FLURRY_OF_BLOWS); //TODO
        put("Perilous Flare", PERILOUS_FLARE); //TODO
        put("Evershot", EVERSHOT); //TODO
        put("Gruesome Knots", GRUESOME_KNOTS); //TODO
        put("Temblor", TEMBLOR); //TODO
        put("Hawkeye", HAWKEYE); //NEED CHECK
        put("Overwhelm", OVERWHELM); //NEED CHECK
        put("Napalm", NAPALM); //TODO
        put("Gentle Glow", GENTLE_GLOW); //NEED FIXES
        put("Expunge", EXPUNGE); //NEED FIXES
        put("Reckless Abandon", RECKLESS_ABANDON); //NEED CHECK
        put("Mangle", MANGLE); //TODO
        put("Rusted Ichor", RUSTED_ICHOR); //Low Priority
    }};
}
