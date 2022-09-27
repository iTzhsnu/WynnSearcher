package com.github.iTzhsnu.WynnSearcher;

public enum Identifications implements IIdentifications {
    LEVEL("level", "level", false, false),
    STRENGTH_REQ("strength", "strengthRequirement", false, false),
    DEXTERITY_REQ("dexterity", "dexterityRequirement", false, false),
    INTELLIGENCE_REQ("intelligence", "intelligenceRequirement", false, false),
    DEFENSE_REQ("defense", "defenceRequirement", false, false),
    AGILITY_REQ("agility", "agilityRequirement", false, false),
    QUEST_REQ("quest", null, false, false),

    HEALTH("health", null, false, false),
    HEALTH_BONUS("healthBonus", "HEALTHBONUS", false, false),
    RAW_HEALTH_REGEN("healthRegenRaw", "HEALTHREGENRAW", false, false),
    HEALTH_REGEN_PERCENT("", "", true, false),
    LIFE_STEAL("", "", false, false),

    STRENGTH("", "", false, false),
    DEXTERITY("", "", false, false),
    INTELLIGENCE("", "", false, false),
    DEFENSE("", "", false, false),
    AGILITY("", "", false, false),

    EARTH_DEFENSE("", null, false, false),
    THUNDER_DEFENSE("", null, false, false),
    WATER_DEFENSE("", null, false, false),
    FIRE_DEFENSE("", null, false, false),
    AIR_DEFENSE("", null, false, false),

    EARTH_DEFENSE_PERCENT("", "", true, false),
    THUNDER_DEFENSE_PERCENT("", "", true, false),
    WATER_DEFENSE_PERCENT("", "", true, false),
    FIRE_DEFENSE_PERCENT("", "", true, false),
    AIR_DEFENSE_PERCENT("", "", true, false),

    NEUTRAL_DAMAGE("", null, false, false),
    EARTH_DAMAGE("", null, false, false),
    THUNDER_DAMAGE("", null, false, false),
    WATER_DAMAGE("", null, false, false),
    FIRE_DAMAGE("", null, false, false),
    AIR_DAMAGE("", null, false, false),

    EARTH_DAMAGE_PERCENT("", "", true, false),
    THUNDER_DAMAGE_PERCENT("", "", true, false),
    WATER_DAMAGE_PERCENT("", "", true, false),
    FIRE_DAMAGE_PERCENT("", "", true, false),
    AIR_DAMAGE_PERCENT("", "", true, false),

    RAW_SPELL_DAMAGE("", "", false, false),
    RAW_MELEE_DAMAGE("", "", false, false),
    SPELL_DAMAGE_PERCENT("", "", true, false),
    MELEE_DAMAGE_PERCENT("", "", true, false),
    POISON("", "", false, false),

    ATTACK_SPEED("", null, false, false),
    ATTACK_SPEED_BONUS("", "", false, false),

    MANA_REGEN("", "", false, false),
    MANA_STEAL("", "", false, false),

    WALK_SPEED("", "", true, false),
    SPRINT_BONUS("", "", true, false),
    SPRINT_REGEN("", "", true, false),
    JUMP_HEIGHT("", "", false, false),

    THORNS("", "", true, false),
    REFLECTION("", "", true, false),
    EXPLODING("", "", true, false),
    STEALING("", "", true, false),
    COMBAT_XP_BONUS("", "", true, false),
    GATHERING_XP_BONUS("", "", true, false),
    GATHERING_SPEED_BONUS("", "", true, false),
    LOOT_BONUS("", "", true, false),
    LOOT_QUALITY("", "", true, false),
    SOUL_POINT_REGEN("", "", true, false),
    POWDER_SLOTS("", null, true, false),

    RAW_1ST_SPELL_COST("", null, false, false),
    RAW_2ND_SPELL_COST("", null, false, false),
    RAW_3RD_SPELL_COST("", null, false, false),
    RAW_4TH_SPELL_COST("", null, false, false),

    PERCENT_1ST_SPELL_COST("", null, true, false),
    PERCENT_2ND_SPELL_COST("", null, true, false),
    PERCENT_3RD_SPELL_COST("", null, true, false),
    PERCENT_4TH_SPELL_COST("", null, true, false),

    RAW_EARTH_SPELL_DAMAGE(null, null, false, false),
    RAW_THUNDER_SPELL_DAMAGE("", null, false, false),
    RAW_WATER_SPELL_DAMAGE("", null, false, false),
    RAW_FIRE_SPELL_DAMAGE("", null, false, false),
    RAW_AIR_SPELL_DAMAGE("", null, false, false),
    RAW_ELEMENTAL_SPELL_DAMAGE("", null, false, false),

    DURABILITY(null, "", false, false),
    DURATION(null, "", false, false),
    INGREDIENT_EFFECTIVENESS(null, "", false, false),
    INGREDIENT_EFFECTIVENESS_ABOVE(null, "", false, false),
    INGREDIENT_EFFECTIVENESS_UNDER(null, "", false, false),
    INGREDIENT_EFFECTIVENESS_RIGHT(null, "", false, false),
    INGREDIENT_EFFECTIVENESS_LEFT(null, "", false, false),
    INGREDIENT_EFFECTIVENESS_TOUCHING(null, "", false, false),
    INGREDIENT_EFFECTIVENESS_NOT_TOUCHING(null, "", false, false),

    SUM_TOTAL_HEALTH("", "", false, true),
    SUM_RAW_DEFENSES("", null, false, true),
    SUM_RAW_RAINBOW_DEFENSES("", null, false, true),
    SUM_DEFENSES_PERCENT("", "", true, true),
    SUM_RAINBOW_DEFENSES_PERCENT("", "", true, true),
    SUM_SKILL_POINT_BONUS("", "", false, true),
    SUM_RAINBOW_SKILL_POINT_BONUS("", "", false, true),

    SUM_BASE_DPS("", null, false, true),
    SUM_NEUTRAL_DPS("", null, false, true),
    SUM_EARTH_DPS("", null, false, true),
    SUM_WATER_DPS("", null, false, true),
    SUM_FIRE_DPS("", null, false, true),
    SUM_AIR_DPS("", null, false, true),

    SUM_RAW_DAMAGES("", null, false, true),
    SUM_RAW_RAINBOW_DAMAGES("", null, false, true),
    SUM_DAMAGES_PERCENT("", "", true, true),
    SUM_RAINBOW_DAMAGES_PERCENT("", "", true, true),

    SUM_TOTAL_MELEE_DAMAGE("", null, false, true),
    SUM_TOTAL_NEUTRAL_MELEE_DAMAGE("", null, false, true),
    SUM_TOTAL_EARTH_DAMAGE("", null, false, true),
    SUM_TOTAL_THUNDER_DAMAGE("", null, false, true),
    SUM_TOTAL_WATER_DAMAGE("", null, false, true),
    SUM_TOTAL_FIRE_DAMAGE("", null, false, true),
    SUM_TOTAL_AIR_DAMAGE("", null, false, true),

    SUM_TOTAL_DPS("", null, false, true),
    SUM_TOTAL_MELEE_DPS("", null, false, true),
    SUM_TOTAL_SPELL_DPS("", null, false, true),
    SUM_TOTAL_NEUTRAL_MELEE_DPS("", null, false, true),
    SUM_TOTAL_NEUTRAL_SPELL_DPS("", null, false, true),
    SUM_TOTAL_EARTH_DPS("", null, false, true),
    SUM_TOTAL_THUNDER_DPS("", null, false, true),
    SUM_TOTAL_WATER_DPS("", null, false, true),
    SUM_TOTAL_FIRE_DPS("", null, false, true),
    SUM_TOTAL_AIR_DPS("", null, false, true),

    SUM_RAW_SPELL_COSTS("", null, false, true),
    SUM_SPELL_COSTS_PERCENT("", null, true, true)
    ;

    private final String itemName;
    private final String ingName;
    private final boolean percent;
    private final boolean notID;

    Identifications(String itemName, String ingName, boolean percent, boolean notID) {
        this.itemName = itemName;
        this.ingName = ingName;
        this.percent = percent;
        this.notID = notID;
    }


    @Override
    public String getItemName() {
        return this.itemName;
    }

    @Override
    public String getIngName() {
        return this.ingName;
    }

    @Override
    public boolean isPercent() {
        return this.percent;
    }

    @Override
    public boolean isNotID() {
        return this.notID;
    }
}
