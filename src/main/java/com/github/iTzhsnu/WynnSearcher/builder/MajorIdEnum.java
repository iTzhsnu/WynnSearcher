package com.github.iTzhsnu.WynnSearcher.builder;

import java.util.HashMap;
import java.util.Map;

public enum MajorIdEnum {
    EMPTY,

    SELF_SUFFICIENT,
    ONEIRO,
    IGNITION_WHIRLWIND,
    OLD_SPARK,
    DAUNTLESS_INFERNO,
    DEATH_SENTENCE,
    FINAL_GAMBIT,
    STRINGS_OF_FATE,
    BLINDING_LIGHTS,
    SLOW_BOIL,
    DISASSOCIATE,
    FLAIN_REMNANTS,
    SOLAR_WIND,
    MANIC_EDGE,
    FURIOUS_EFFIGY,
    LIGHTNING_CRASH,
    STAGNANT_AIR,
    CHAOS_REACH,
    SEEKING_MODULE,
    INCISION_ELEGY,
    DIVINE_RIGHT,
    GUNK_SHOT,
    FISSURE,
    NAPALM,
    REGICIDE,
    UNSTABLE_REACTION,
    ENLIGHTENMENT,
    NET_SHOT,
    WAVEBREAK,
    COMBO_STOMP,
    ELVEN_CURRENT,
    GENTLE_GLOW,
    LIT_FUSE,
    HEROINES_BLESSING,
    LIVING_MUSEUM,
    LIGHTWEIGHT,
    RIVER_OF_SOULS,
    HEARTBEAT,
    STARCROSSED,
    FLURRY_OF_BLOWS,
    ONESELF_UNTO_ALL,
    JUGGLE,
    CHERRY_BOMBS,
    GRUESOME_KNOTS,
    BATTLEFIELD_BRILLIANCE,
    VITRIOL,
    VEDETTE,
    FIXATE,
    TWISTING_THREADS,
    LIFESTREAM,
    SORCERY,
    HURRICANES_EYE,
    PERFECT_RECALL,
    HELLFIRE,
    EFFLORESCENCE,
    SOUL_EATER,
    GREED,
    HEAT_SHIMMER,
    METEOR_CRASH,
    RALLY,
    ORBITAL_CHAIN,
    FORESTS_BLESSING,
    SCARCE_COMFORT,
    DIVINE_HONOR,
    DEAD_WEIGHT,
    FALLOUT,
    FAUSTIAN_GAMBIT,
    FREERUNNER,
    WORMHOLE,
    INVERSION,
    FISSION,
    ERGOSPHERE,
    TRANSCENDENCE,
    EARTHEN_SPLINTER,
    TOTEMIC_FUSE,
    OUTBREAK,
    PIONEERS_ECHO,
    ROVING_ASSASSIN,
    TORMENT_LITURGY,
    THREE_BODIES,
    TIDAL_DRIFT,
    PUNISHMENT,
    ALTRUISM,
    SPACE_GLIDE,
    GRAND_INFLUENCE,
    GUARDIAN,
    REALITY_REVISION,
    GEOCENTRISM,
    SAVIOURS_SACRIFICE,
    COSMIC_CAPTURE,
    SUBLIMATION,
    TROLL_BARGE,
    PARAGON,
    PEACEFUL_EFFIGY,
    ENTROPY,
    LUSTRATE,
    WINDSURF,
    LAST_RESORT,
    EVERSHOT,
    LOCKDOWN,
    FRENETIC_SPIRIT,
    GRAVITY_WELL,
    DISPLACE,
    TACKLE,
    PLAGUE,
    PERILOUS_FLARE,
    FLASHFREEZE,
    METHODICAL,
    TEMBLOR,
    FAN_THE_HAMMER,
    OVERWHELM,
    BURNT_SHADOWS,
    SUPERCONDUCTOR,
    INTANGIBLE,
    ORBWALK,
    DESPERATE_MEASURES,
    LUNGE,
    POUNCE,
    ICE_STORM,
    EXPLOSIVE_IMPACT,
    ROCK_SHIELD,
    PIROUETTE,
    MAGNET,
    EXPUNGE,
    ALTER_EGO,
    PHOENIX_BORN,
    HAWKEYE,
    RECKLESS_ABANDON,
    ESCAPE_ROUTE,
    TAUNT,
    RUSTED_ICHOR,
    MANGLE,
    BRACHIATE,
    FRIGHT_OR_FLIGHT,
    CANNULATE,
    SUN_EATER,
    SNOWY_STEPS,
    FESTIVE_SPIRIT,
    FLIGHT_BURST,
    CLAMBER,
    MOB_MENTALITY,
    BOUND_BY_BLOOD,
    LASSO,
    SPLIT_SECOND,
    CULICIDAE_CREED,
    SPECTRAL_MEMORY,
    FIND_THYSELF,
    MADNESS,
    COAGULATE,
    INSOLUBLE,
    POWER_FIST

    ;

    public static final Map<String, MajorIdEnum> GET_MAJOR_IDS = new HashMap<>() {{
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
