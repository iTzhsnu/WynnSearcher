package com.github.iTzhsnu.WynnSearcher.general;

public enum JsonKeys {
    NOTHING(""),
    NAME("name"),
    DISPLAY_NAME("displayName"),
    INTERNAL_NAME("internalName"),
    REQUIREMENTS("requirements"),
    BASE("base"),
    IDENTIFICATION("identifications"),
    ITEMONLYIDS("itemOnlyIDs"),
    CONSUMABLEONLYIDS("consumableOnlyIDs"),
    INGREDIENTPOSITIONMODIFIERS("ingredientPositionModifiers"),
    SKILLS("skills"),
    TYPE("type"),
    SUBTYPE("subType"),
    IDENTIFIED("identified"),
    RAW("raw"),
    MIN("min"),
    MAX("max"),
    DROP_RESTRICTION("dropRestriction"),
    DROP_META("dropMeta"),
    DROPPED_BY("droppedBy"),
    EVENT("event"),
    COORDINATES("coordinates"), // official api
    COORDS("coords"), // official api ingredient use this
    GATHERING_SPEED("gatheringSpeed"),

    // used by custom drop type
    IS_MOBNAME("ismobname"),
    POS("pos"),
    PRICE("price"),

    OTHERS("") // Used for detection
    ;

    private final String key;

    JsonKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
