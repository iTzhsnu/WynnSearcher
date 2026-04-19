package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.general.DataKeys;
import com.github.iTzhsnu.WynnSearcher.general.DataType;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;

public enum Identifications {
    EMPTY(null, null, null, "", DataType.NOTHING, null, null, null, false),

    LEVEL("Lv. Min","level", "level", "", DataType.INT, null, JsonKeys.REQUIREMENTS, JsonKeys.REQUIREMENTS, false),
    STRENGTH_REQ("Strength Req", "strength", "strengthRequirement", "", DataType.INT, null, JsonKeys.REQUIREMENTS, JsonKeys.ITEMONLYIDS, false),
    DEXTERITY_REQ("Dexterity Req", "dexterity", "dexterityRequirement", "", DataType.INT, null, JsonKeys.REQUIREMENTS, JsonKeys.ITEMONLYIDS, false),
    INTELLIGENCE_REQ("Intelligence Req", "intelligence", "intelligenceRequirement", "", DataType.INT, null, JsonKeys.REQUIREMENTS, JsonKeys.ITEMONLYIDS, false),
    DEFENSE_REQ("Defense Req", "defence", "defenceRequirement", "", DataType.INT, null, JsonKeys.REQUIREMENTS, JsonKeys.ITEMONLYIDS, false),
    AGILITY_REQ("Agility Req", "agility", "agilityRequirement", "", DataType.INT, null, JsonKeys.REQUIREMENTS, JsonKeys.ITEMONLYIDS, false),
    QUEST_REQ("Quest Req", "quest", null, "", DataType.STRING, null, JsonKeys.REQUIREMENTS, null, false),
    UNTRADABLE("Untradable", "restrictions", null, "", DataType.STRING, null, JsonKeys.NOTHING, null, false),
    RARITY("Rarity", "tier", "tier", "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),

    HEALTH("Health", "baseHealth", null, "", DataType.INT, null, JsonKeys.BASE, null, false),
    HEALTH_BONUS("Health Bonus", "rawHealth", "rawHealth", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_HEALTH_REGEN("Raw Health Regen", "healthRegenRaw", "healthRegenRaw", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    HEALTH_REGEN_PERCENT("Health Regen", "healthRegen", "healthRegen", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    LIFE_STEAL("Life Steal", "lifeSteal", "lifeSteal", "/3s", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    STRENGTH("Strength", "rawStrength", "rawStrength", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, false),
    DEXTERITY("Dexterity", "rawDexterity", "rawDexterity", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, false),
    INTELLIGENCE("Intelligence", "rawIntelligence", "rawIntelligence", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, false),
    DEFENSE("Defense", "rawDefence", "rawDefence", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, false),
    AGILITY("Agility", "rawAgility", "rawAgility", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, false),

    EARTH_DEFENSE("Earth Defense", "baseEarthDefence", null, "", DataType.INT, null, JsonKeys.BASE, null, false),
    THUNDER_DEFENSE("Thunder Defense", "baseThunderDefence", null, "", DataType.INT, null, JsonKeys.BASE, null, false),
    WATER_DEFENSE("Water Defense", "baseWaterDefence", null, "", DataType.INT, null, JsonKeys.BASE, null, false),
    FIRE_DEFENSE("Fire Defense", "baseFireDefence", null, "", DataType.INT, null, JsonKeys.BASE, null, false),
    AIR_DEFENSE("Air Defense", "baseAirDefence", null, "", DataType.INT, null, JsonKeys.BASE, null, false),

    EARTH_DEFENSE_PERCENT("Earth Defense", "earthDefence", "earthDefence", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    THUNDER_DEFENSE_PERCENT("Thunder Defense", "thunderDefence", "thunderDefence", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    WATER_DEFENSE_PERCENT("Water Defense", "waterDefence", "waterDefence", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    FIRE_DEFENSE_PERCENT("Fire Defense", "fireDefence", "fireDefence", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    AIR_DEFENSE_PERCENT("Air Defense", "airDefence", "airDefence", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    ELEMENTAL_DEFENSE_PERCENT("Elemental Defense", "elementalDefence", "elementalDefence", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    NEUTRAL_DAMAGE("Neutral Damage", "baseDamage", null, "", DataType.INT, null, JsonKeys.BASE, null, true),
    EARTH_DAMAGE("Earth Damage", "baseEarthDamage", null, "", DataType.INT, null, JsonKeys.BASE, null, true),
    THUNDER_DAMAGE("Thunder Damage", "baseThunderDamage", null, "", DataType.INT, null, JsonKeys.BASE, null, true),
    WATER_DAMAGE("Water Damage", "baseWaterDamage", null, "", DataType.INT, null, JsonKeys.BASE, null, true),
    FIRE_DAMAGE("Fire Damage", "baseFireDamage", null, "", DataType.INT, null, JsonKeys.BASE, null, true),
    AIR_DAMAGE("Air Damage", "baseAirDamage", null, "", DataType.INT, null, JsonKeys.BASE, null, true),

    DAMAGE_PERCENT("Damage", "damage", "baseDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true), // Ingredient Warning: Blueshift Beacon say "baseDamage"
    NEUTRAL_DAMAGE_PERCENT("Neutral Damage", "neutralDamage", "neutralDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    EARTH_DAMAGE_PERCENT("Earth Damage", "earthDamage", "earthDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    THUNDER_DAMAGE_PERCENT("Thunder Damage", "thunderDamage", "thunderDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    WATER_DAMAGE_PERCENT("Water Damage", "waterDamage", "waterDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    FIRE_DAMAGE_PERCENT("Fire Damage", "fireDamage", "fireDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    AIR_DAMAGE_PERCENT("Air Damage", "airDamage", "airDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    ELEMENTAL_DAMAGE_PERCENT("Elemental Damage", "elementalDamage", "elementalDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    RAW_SPELL_DAMAGE("Raw Spell Damage", "rawSpellDamage", "rawSpellDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_MELEE_DAMAGE("Raw Melee Damage", "rawMainAttackDamage", "rawMainAttackDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    SPELL_DAMAGE_PERCENT("Spell Damage", "spellDamage", "spellDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    MELEE_DAMAGE_PERCENT("Melee Damage", "mainAttackDamage", "mainAttackDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    POISON("Poison", "poison", "poison", "/3s", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    ATTACK_SPEED("Attack Speed", "attackSpeed", null, "", DataType.STRING, null, JsonKeys.NOTHING, null, false),
    ATTACK_SPEED_BONUS("Attack Speed Bonus", "rawAttackSpeed", "rawAttackSpeed", "tier", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    MAX_MANA("Max Mana", "rawMaxMana", "rawMaxMana", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    MANA_REGEN("Mana Regen", "manaRegen", "manaRegen", "/5s", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    MANA_STEAL("Mana Steal", "manaSteal", "manaSteal", "/3s", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    WALK_SPEED("Walk Speed", "walkSpeed", "walkSpeed", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    SPRINT_BONUS("Sprint Bonus", "sprint", "sprint", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    SPRINT_REGEN("Sprint Regen", "sprintRegen", "sprintRegen", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    JUMP_HEIGHT("Jump Height", "jumpHeight", "jumpHeight", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    THORNS("Thorns", "thorns", "thorns", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    REFLECTION("Reflection", "reflection", "reflection", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    EXPLODING("Exploding", "exploding", "exploding", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    CRITICAL_DAMAGE_PERCENT("Critical Damage", "criticalDamageBonus", "criticalDamageBonus", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    STEALING("Stealing", "stealing", "stealing", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    COMBAT_XP_BONUS("Combat XP Bonus", "combatExperience", "combatExperience", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    GATHERING_XP_BONUS("Gathering XP Bonus", "gatheringExperience", "gatherXpBonus", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    GATHERING_SPEED_BONUS("Gathering Speed Bonus", "gatherSpeed", "gatherSpeed", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    LOOT_BONUS("Loot Bonus", "lootBonus", "lootBonus", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    LOOT_QUALITY("Loot Quality", "lootQuality", "lootQuality", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    SOUL_POINT_REGEN("Soul Point Regen", "soulPointRegen", "soulPointRegen", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    POWDER_SLOTS("Powder Slots", "powderSlots", null, "", DataType.INT, null, JsonKeys.NOTHING, null, false),
    MAJOR_IDS("Major ID", "majorIds", null, "", DataType.STRING, null, JsonKeys.NOTHING, null, false),

    RAW_1ST_SPELL_COST("Raw 1st Spell Cost", "raw1stSpellCost", "raw1stSpellCost", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_2ND_SPELL_COST("Raw 2nd Spell Cost", "raw2ndSpellCost", "raw2ndSpellCost", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_3RD_SPELL_COST("Raw 3rd Spell Cost", "raw3rdSpellCost", "raw3rdSpellCost", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_4TH_SPELL_COST("Raw 4th Spell Cost", "raw4thSpellCost", "raw4thSpellCost", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    PERCENT_1ST_SPELL_COST("1st Spell Cost", "1stSpellCost", "1stSpellCost", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    PERCENT_2ND_SPELL_COST("2nd Spell Cost", "2ndSpellCost", "2ndSpellCost", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    PERCENT_3RD_SPELL_COST("3rd Spell Cost", "3rdSpellCost", "3rdSpellCost", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    PERCENT_4TH_SPELL_COST("4th Spell Cost", "4thSpellCost", "4thSpellCost", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    RAW_DAMAGE("Raw Damage", "rawDamage", "rawDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_NEUTRAL_DAMAGE("Raw Neutral Damage", "rawNeutralDamage", "rawNeutralDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_EARTH_DAMAGE("Raw Earth Damage", "rawEarthDamage", "rawEarthDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_THUNDER_DAMAGE("Raw Thunder Damage", "rawThunderDamage", "rawThunderDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_WATER_DAMAGE("Raw Water Damage", "rawWaterDamage", "rawWaterDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_FIRE_DAMAGE("Raw Fire Damage", "rawFireDamage", "rawFireDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_AIR_DAMAGE("Raw Air Damage", "rawAirDamage", "rawAirDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_ELEMENTAL_DAMAGE("Raw Elemental Damage", "rawElementalDamage", "rawElementalDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    NEUTRAL_MELEE_DAMAGE_PERCENT("Neutral Melee Damage", "neutralMainAttackDamage", "neutralMainAttackDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    EARTH_MELEE_DAMAGE_PERCENT("Earth Melee Damage", "earthMainAttackDamage", "earthMainAttackDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    THUNDER_MELEE_DAMAGE_PERCENT("Thunder Melee Damage", "thunderMainAttackDamage", "thunderMainAttackDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    WATER_MELEE_DAMAGE_PERCENT("Water Melee Damage", "waterMainAttackDamage", "waterMainAttackDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    FIRE_MELEE_DAMAGE_PERCENT("Fire Melee Damage", "fireMainAttackDamage", "fireMainAttackDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    AIR_MELEE_DAMAGE_PERCENT("Air Melee Damage", "airMainAttackDamage", "airMainAttackDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    ELEMENTAL_MELEE_DAMAGE_PERCENT("Elem. Melee Damage", "elementalMainAttackDamage", "elementalMainAttackDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    RAW_NEUTRAL_MELEE_DAMAGE("Raw Neutral Melee Damage", "rawNeutralMainAttackDamage", "rawNeutralMainAttackDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_EARTH_MELEE_DAMAGE("Raw Earth Melee Damage", "rawEarthMainAttackDamage", "rawEarthMainAttackDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_THUNDER_MELEE_DAMAGE("Raw Thunder Melee Damage", "rawThunderMainAttackDamage", "rawThunderMainAttackDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_WATER_MELEE_DAMAGE("Raw Water Melee Damage", "rawWaterMainAttackDamage", "rawWaterMainAttackDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_FIRE_MELEE_DAMAGE("Raw Fire Melee Damage", "rawFireMainAttackDamage", "rawFireMainAttackDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_AIR_MELEE_DAMAGE("Raw Air Melee Damage", "rawAirMainAttackDamage", "rawAirMainAttackDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_ELEMENTAL_MELEE_DAMAGE("Raw Elem. Melee Damage", "rawElementalMainAttackDamage", "rawElementalMainAttackDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    NEUTRAL_SPELL_DAMAGE_PERCENT("Neutral Spell Damage", "neutralSpellDamage", "neutralSpellDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    EARTH_SPELL_DAMAGE_PERCENT("Earth Spell Damage", "earthSpellDamage", "earthSpellDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    THUNDER_SPELL_DAMAGE_PERCENT("Thunder Spell Damage", "thunderSpellDamage", "thunderSpellDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    WATER_SPELL_DAMAGE_PERCENT("Water Spell Damage", "waterSpellDamage", "waterSpellDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    FIRE_SPELL_DAMAGE_PERCENT("Fire Spell Damage", "fireSpellDamage", "fireSpellDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    AIR_SPELL_DAMAGE_PERCENT("Air Spell Damage", "airSpellDamage", "airSpellDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    ELEMENTAL_SPELL_DAMAGE_PERCENT("Elem. Spell Damage", "elementalSpellDamage", "elementalSpellDamage", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    RAW_NEUTRAL_SPELL_DAMAGE("Raw Neutral Spell Damage", "rawNeutralSpellDamage", "rawNeutralSpellDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_EARTH_SPELL_DAMAGE("Raw Earth Spell Damage", "rawEarthSpellDamage", "rawEarthSpellDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_THUNDER_SPELL_DAMAGE("Raw Thunder Spell Damage", "rawThunderSpellDamage", "rawThunderSpellDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_WATER_SPELL_DAMAGE("Raw Water Spell Damage", "rawWaterSpellDamage", "rawWaterSpellDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_FIRE_SPELL_DAMAGE("Raw Fire Spell Damage", "rawFireSpellDamage", "rawFireSpellDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_AIR_SPELL_DAMAGE("Raw Air Spell Damage", "rawAirSpellDamage", "rawAirSpellDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    RAW_ELEMENTAL_SPELL_DAMAGE("Raw Elem. Spell Damage", "rawElementalSpellDamage", "rawElementalSpellDamage", "", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    MELEE_RANGE_PERCENT("Melee Range", "mainAttackRange", "mainAttackRange", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    KNOCKBACK("Knockback", "knockback", "knockback", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    HEALING_EFFICIENCY("Healing Efficiency", "healingEfficiency", "healingEfficiency", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    WEAKEN_ENEMY("Weaken Enemy", "weakenEnemy", "weakenEnemy", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),
    SLOW_ENEMY("Slow Enemy", "slowEnemy", "slowEnemy", "%", DataType.INT, null, JsonKeys.IDENTIFICATION, JsonKeys.IDENTIFICATION, true),

    DAMAGE_FROM_MOBS("Damage From Mobs", "damageFromMobs", null, "%", DataType.INT, null, JsonKeys.BASE, null, false),
    LEVELED_LOOT_BONUS("Leveled Loot Bonus", "leveledLootBonus", null, "%", DataType.INT, null, JsonKeys.BASE, null, false),
    LEVELED_XP_BONUS("Leveled XP Bonus", "leveledXpBonus", null, "%", DataType.INT, null, JsonKeys.BASE, null, false),

    DURABILITY("Durability", null, "durabilityModifier", "", DataType.INT, null, null, JsonKeys.ITEMONLYIDS, false),
    DURATION("Duration", null, "duration", "", DataType.INT, null, null, JsonKeys.CONSUMABLEONLYIDS, false),
    CHARGES("Charges", null, "charges", "", DataType.INT, null, null, JsonKeys.CONSUMABLEONLYIDS, false),
    INGREDIENT_EFFECTIVENESS_ABOVE("Ingredient Effectiveness (Above)", null, "above", "%", DataType.INT, null, null, JsonKeys.INGREDIENTPOSITIONMODIFIERS, false),
    INGREDIENT_EFFECTIVENESS_UNDER("Ingredient Effectiveness (Under)", null, "under", "%", DataType.INT, null, null, JsonKeys.INGREDIENTPOSITIONMODIFIERS, false),
    INGREDIENT_EFFECTIVENESS_RIGHT("Ingredient Effectiveness (Right)", null, "right", "%", DataType.INT, null, null, JsonKeys.INGREDIENTPOSITIONMODIFIERS, false),
    INGREDIENT_EFFECTIVENESS_LEFT("Ingredient Effectiveness (Left)", null, "left", "%", DataType.INT, null, null, JsonKeys.INGREDIENTPOSITIONMODIFIERS, false),
    INGREDIENT_EFFECTIVENESS_TOUCHING("Ingredient Effectiveness (Touch)", null, "touching", "%", DataType.INT, null, null, JsonKeys.INGREDIENTPOSITIONMODIFIERS, false),
    INGREDIENT_EFFECTIVENESS_NOT_TOUCHING("Ingredient Effectiveness (Not Touch)", null, "notTouching", "%", DataType.INT, null, null, JsonKeys.INGREDIENTPOSITIONMODIFIERS, false),
    INGREDIENT_EFFECTIVENESS("", null, "ingEffective", "", DataType.SUM, SumEnum.INGREDIENT_EFFECTIVENESS, null, JsonKeys.OTHERS, false),

    DROP_TYPE("Drop Type", JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false), // This is Dummy
    DROP_TYPE_NORMAL(JsonValues.D_NORMAL, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_LOOT_CHESTS(JsonValues.LOOTCHEST, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_RAID_REWARDS(JsonValues.RAID, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_DUNGEON_DROP(JsonValues.DUNGEON, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_DUNGEON_MERCHANT(JsonValues.DUNGEON_MERCHANT, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_BOSS_ALTAR(JsonValues.ALTAR, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_DISCONTINUED(JsonValues.DISCONTINUED, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_UNKNOWN(JsonValues.NEVER, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_MERCHANT(JsonValues.MERCHANT, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_QUEST(JsonValues.QUEST, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_UNOBTAINABLE(JsonValues.UNOBTAINABLE, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_SPECIFIC_DROP(JsonValues.SPECIFIC, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_SECRET_DISCOVERY(JsonValues.SECRET_DISCOVERY, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_THE_QIRA_HIVE(JsonValues.THE_QIRA_HIVE, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_LEGENDARY_ISLAND(JsonValues.LEGENDARY_ISLAND, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_WORLD_EVENT(JsonValues.WORLD_EVENT, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_LOOTRUN(JsonValues.LOOTRUN, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_MINIBOSS(JsonValues.MINIBOSS, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_CHALLENGE(JsonValues.CHALLENGE, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),
    DROP_TYPE_OTHER(JsonValues.OTHER, JsonKeys.DROP_RESTRICTION.getKey(), JsonKeys.DROPPED_BY.getKey(), "", DataType.STRING, null, JsonKeys.NOTHING, JsonKeys.NOTHING, false),

    SET("Set", JsonKeys.SETS.getKey(), null, "", DataType.STRING, null, JsonKeys.NOTHING, null, false), // real Data Type is array

    SUM_TOTAL_HEALTH("", "total_health", "total_health", "", DataType.SUM, SumEnum.TOTAL_HEALTH, JsonKeys.OTHERS, JsonKeys.OTHERS, false),
    SUM_TOTAL_HP_REGEN("", "total_hp_regen", "total_hp_regen", "", DataType.SUM, SumEnum.TOTAL_HEALTH_REGEN, JsonKeys.OTHERS, JsonKeys.OTHERS, false),
    SUM_RAW_DEFENSES("", "raw_defenses", null, "", DataType.SUM, SumEnum.RAW_DEFENSES, JsonKeys.OTHERS, null, false),
    SUM_RAW_RAINBOW_DEFENSES("", "raw_rainbow_defense", null, "", DataType.SUM, SumEnum.RAW_RAINBOW_DEFENSES, JsonKeys.OTHERS, null, false),
    SUM_DEFENSES_PERCENT("", "defenses", "defenses", "", DataType.SUM, SumEnum.DEFENSES_PERCENT, JsonKeys.OTHERS, JsonKeys.OTHERS, false),
    SUM_RAINBOW_DEFENSES_PERCENT("", "rainbow_defense", "rainbow_defense", "", DataType.SUM, SumEnum.RAINBOW_DEFENSES_PERCENT, JsonKeys.OTHERS, JsonKeys.OTHERS, false),
    SUM_SKILL_POINT_BONUS("", "sp_bonus", "sp_bonus", "", DataType.SUM, SumEnum.SKILL_POINT_BONUS, JsonKeys.OTHERS, JsonKeys.OTHERS, false),
    SUM_RAINBOW_SKILL_POINT_BONUS("", "rainbow_sp_bonus", "rainbow_sp_bonus", "", DataType.SUM, SumEnum.RAINBOW_SKILL_POINT_BONUS, JsonKeys.OTHERS, JsonKeys.OTHERS, false),

    SUM_BASE_DPS("", "base_dps", null, "", DataType.SUM, SumEnum.BASE_DPS, JsonKeys.OTHERS, null, false),
    SUM_NEUTRAL_DPS("", "neutral_dps", null, "", DataType.SUM, SumEnum.NEUTRAL_DPS, JsonKeys.OTHERS, null, false),
    SUM_EARTH_DPS("", "earth_dps", null, "", DataType.SUM, SumEnum.EARTH_DPS, JsonKeys.OTHERS, null, false),
    SUM_THUNDER_DPS("", "thunder_dps", null, "", DataType.SUM, SumEnum.THUNDER_DPS, JsonKeys.OTHERS, null, false),
    SUM_WATER_DPS("", "water_dps", null, "", DataType.SUM, SumEnum.WATER_DPS, JsonKeys.OTHERS, null, false),
    SUM_FIRE_DPS("", "fire_dps", null, "", DataType.SUM, SumEnum.FIRE_DPS, JsonKeys.OTHERS, null, false),
    SUM_AIR_DPS("", "air_dps", null, "", DataType.SUM, SumEnum.AIR_DPS, JsonKeys.OTHERS, null, false),

    SUM_BASE_DAMAGES("", "base_damages", null, "", DataType.SUM, SumEnum.BASE_DAMAGES, JsonKeys.OTHERS, null, false),
    SUM_BASE_RAINBOW_DAMAGES("", "base_rainbow_damage", null, "", DataType.SUM, SumEnum.BASE_RAINBOW_DAMAGES, JsonKeys.OTHERS, null, false),

    SUM_RAW_DAMAGES("", "raw_damages", null, "", DataType.SUM, SumEnum.RAW_DAMAGES, JsonKeys.OTHERS, null, false),
    SUM_RAW_SPELL_DAMAGES("", "raw_spell_damages", null, "", DataType.SUM, SumEnum.RAW_SPELL_DAMAGES, JsonKeys.OTHERS, null, false),
    SUM_RAW_MELEE_DAMAGES("", "raw_melee_damages", null, "", DataType.SUM, SumEnum.RAW_MELEE_DAMAGES, JsonKeys.OTHERS, null, false),

    SUM_DAMAGES_PERCENT("", "damages", "damages", "", DataType.SUM, SumEnum.DAMAGES_PERCENT, JsonKeys.OTHERS, JsonKeys.OTHERS, false),
    SUM_RAINBOW_DAMAGES_PERCENT("", "rainbow_damage", "rainbow_damage", "", DataType.SUM, SumEnum.RAINBOW_DAMAGES_PERCENT, JsonKeys.OTHERS, JsonKeys.OTHERS, false),

    SUM_TOTAL_MELEE_DAMAGE("", "total_melee_damage", null, "", DataType.SUM, SumEnum.TOTAL_MELEE_DAMAGE, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_NEUTRAL_MELEE_DAMAGE("", "total_neutral_melee_damage", null, "", DataType.SUM, SumEnum.TOTAL_NEUTRAL_MELEE_DAMAGE, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_EARTH_MELEE_DAMAGE("", "total_earth_damage", null, "", DataType.SUM, SumEnum.TOTAL_EARTH_MELEE_DAMAGE, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_THUNDER_MELEE_DAMAGE("", "total_thunder_damage", null, "", DataType.SUM, SumEnum.TOTAL_THUNDER_MELEE_DAMAGE, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_WATER_MELEE_DAMAGE("", "total_water_damage", null, "", DataType.SUM, SumEnum.TOTAL_WATER_MELEE_DAMAGE, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_FIRE_MELEE_DAMAGE("", "total_fire_damage", null, "", DataType.SUM, SumEnum.TOTAL_FIRE_MELEE_DAMAGE, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_AIR_MELEE_DAMAGE("", "total_air_damage", null, "", DataType.SUM, SumEnum.TOTAL_AIR_MELEE_DAMAGE, JsonKeys.OTHERS, null, false),

    SUM_TOTAL_MELEE_DPS("", "total_melee_dps", null, "", DataType.SUM, SumEnum.TOTAL_MELEE_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_SPELL_DPS("", "total_spell_dps", null, "", DataType.SUM, SumEnum.TOTAL_SPELL_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_NEUTRAL_MELEE_DPS("", "total_neutral_melee_dps", null, "", DataType.SUM, SumEnum.TOTAL_NEUTRAL_MELEE_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_NEUTRAL_SPELL_DPS("", "total_neutral_spell_dps", null, "", DataType.SUM, SumEnum.TOTAL_NEUTRAL_SPELL_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_EARTH_MELEE_DPS("", "total_earth_melee_dps", null, "", DataType.SUM, SumEnum.TOTAL_EARTH_MELEE_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_EARTH_SPELL_DPS("", "total_earth_spell_dps", null, "", DataType.SUM, SumEnum.TOTAL_EARTH_SPELL_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_THUNDER_MELEE_DPS("", "total_thunder_melee_dps", null, "", DataType.SUM, SumEnum.TOTAL_THUNDER_MELEE_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_THUNDER_SPELL_DPS("", "total_thunder_spell_dps", null, "", DataType.SUM, SumEnum.TOTAL_THUNDER_SPELL_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_WATER_MELEE_DPS("", "total_water_melee_dps", null, "", DataType.SUM, SumEnum.TOTAL_WATER_MELEE_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_WATER_SPELL_DPS("", "total_water_spell_dps", null, "", DataType.SUM, SumEnum.TOTAL_WATER_SPELL_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_FIRE_MELEE_DPS("", "total_fire_melee_dps", null, "", DataType.SUM, SumEnum.TOTAL_FIRE_MELEE_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_FIRE_SPELL_DPS("", "total_fire_spell_dps", null, "", DataType.SUM, SumEnum.TOTAL_FIRE_SPELL_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_AIR_MELEE_DPS("", "total_air_melee_dps", null, "", DataType.SUM, SumEnum.TOTAL_AIR_MELEE_DPS, JsonKeys.OTHERS, null, false),
    SUM_TOTAL_AIR_SPELL_DPS("", "total_air_spell_dps", null, "", DataType.SUM, SumEnum.TOTAL_AIR_SPELL_DPS, JsonKeys.OTHERS, null, false),

    SUM_RAW_SPELL_COSTS("", "raw_spell_costs", null, "", DataType.SUM, SumEnum.RAW_SPELL_COSTS, JsonKeys.OTHERS, JsonKeys.OTHERS, false),
    SUM_SPELL_COSTS_PERCENT("", "spell_costs", null, "", DataType.SUM, SumEnum.SPELL_COSTS_PERCENT, JsonKeys.OTHERS, JsonKeys.OTHERS, false),

    SUM_MELEE_APPROPRIATE("", DataKeys.DAMAGE_APPROPRIATE, null, "", DataType.SUM, SumEnum.MELEE_APPROPRIATE, JsonKeys.OTHERS, null, false),
    SUM_SPELL_APPROPRIATE("", DataKeys.DAMAGE_APPROPRIATE, null, "", DataType.SUM, SumEnum.SPELL_APPROPRIATE, JsonKeys.OTHERS, null, false),
    ;

    private final String displayName;
    private final String itemName;
    private final String ingName;
    private final String displaySp;
    private final DataType idType;
    private final SumEnum sum;
    private final JsonKeys itemFieldPos;
    private final JsonKeys ingFieldPos;
    private final boolean itemVariable;

    Identifications(String displayName, String itemName, String ingName, String displaySp, DataType idType, SumEnum sum, JsonKeys itemFieldPos, JsonKeys ingFieldPos, boolean itemVariable) {
        this.displayName = displayName;
        this.itemName = itemName;
        this.ingName = ingName;
        this.displaySp = displaySp;
        this.idType = idType;
        this.sum = sum;
        this.itemFieldPos = itemFieldPos;
        this.ingFieldPos = ingFieldPos;
        this.itemVariable = itemVariable;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getIngName() {
        return this.ingName;
    }

    public String getDisplaySp() {
        return this.displaySp;
    }

    public DataType getIDType() {
        return this.idType;
    }

    public SumEnum getSum() {
        return this.sum;
    }

    public JsonKeys getItemFieldPos() {
        return this.itemFieldPos;
    }

    public JsonKeys getIngFieldPos() {
        return this.ingFieldPos;
    }

    public boolean isItemVariable() {
        return this.itemVariable;
    }
}
