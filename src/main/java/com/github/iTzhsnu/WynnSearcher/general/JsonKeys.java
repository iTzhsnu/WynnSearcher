package com.github.iTzhsnu.WynnSearcher.general;

public enum JsonKeys {
    NOTHING(""),
    NAME("name"),
    DISPLAY_NAME("displayName"),
    INTERNAL_NAME("internalName"),
    REQUIREMENTS("requirements"),
    BASE("base"),
    IDENTIFICATION("identifications"),

    // Ingredient
    ITEMONLYIDS("itemOnlyIDs"),
    CONSUMABLEONLYIDS("consumableOnlyIDs"),
    INGREDIENTPOSITIONMODIFIERS("ingredientPositionModifiers"),
    SKILLS("skills"), // Ingredient can use this skill (ex. armouring, tailoring and etc...)

    // Type
    TYPE("type"), // Ex. Weapon, Tome and etc...
    SUBTYPE("subType"), // Ex. Helmet, Armouring, Charm and etc...
    IDENTIFIED("identified"), // Item haven't id range

    // ID
    RAW("raw"), // ex. ID raw
    MIN("min"), // ex. ID min
    MAX("max"), // ex. ID max

    // Drop Types
    DROP_RESTRICTION("dropRestriction"), // Equipment drop type
    DROP_META("dropMeta"), // Equipment drop type
    DROPPED_BY("droppedBy"), // Ingredient drop type
    EVENT("event"), // Drop Type: Event
    COORDINATES("coordinates"), // Equipment dropMeta and tree coordinates
    COORDS("coords"), // Ingredient droppedBy coordinate

    // Tools
    GATHERING_SPEED("gatheringSpeed"), // Tools Gathering Speed

    // Set Bonuses
    SETS("sets"), // Equipment have Set
    BONUSES("bonuses"), // Set Bonuses
    MINOR("minor"), // Set Bonuses IDs
    MAJOR("major"), // Set Bonuses Major ID?

    // Ability Tree
    X("x"), // Position X
    Y("y"), // Position Y
    META("meta"), // Ability tree meta data
    ICON("icon"), // Ability tree icon in meta data
    PAGES("pages"), // Ability tree page
    DESCRIPTION("description"), // Ability Tree description


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
