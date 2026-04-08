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
    GATHERING_SPEED("gatheringSpeed"),

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
