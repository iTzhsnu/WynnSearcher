package com.github.iTzhsnu.WynnSearcher;

public class Main {
    public static void main(String[] args) {
        setITEM_IDS();

        new SearchUI().setVisible(true);
    }

    private static void setITEM_IDS() {
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.STRENGTH);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.DEXTERITY);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.INTELLIGENCE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.DEFENSE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.AGILITY);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.HEALTH_BONUS);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_HEALTH_REGEN);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.HEALTH_REGEN_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.LIFE_STEAL);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.MANA_STEAL);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.MANA_REGEN);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.MAX_MANA);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.EARTH_DEFENSE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.THUNDER_DEFENSE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.WATER_DEFENSE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.FIRE_DEFENSE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.AIR_DEFENSE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.ELEMENTAL_DEFENSE_PERCENT);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.NEUTRAL_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.EARTH_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.THUNDER_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.WATER_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.FIRE_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.AIR_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.ELEMENTAL_DAMAGE_PERCENT);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.SPELL_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.EARTH_SPELL_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.THUNDER_SPELL_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.WATER_SPELL_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.FIRE_SPELL_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.AIR_SPELL_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.MELEE_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.EARTH_MELEE_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.THUNDER_MELEE_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.WATER_MELEE_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.FIRE_MELEE_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.AIR_MELEE_DAMAGE_PERCENT);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_NEUTRAL_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_EARTH_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_THUNDER_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_WATER_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_FIRE_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_AIR_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_ELEMENTAL_DAMAGE);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_SPELL_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_NEUTRAL_SPELL_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_EARTH_SPELL_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_THUNDER_SPELL_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_WATER_SPELL_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_FIRE_SPELL_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_AIR_SPELL_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_ELEMENTAL_SPELL_DAMAGE);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_MELEE_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_NEUTRAL_MELEE_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_EARTH_MELEE_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_THUNDER_MELEE_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_WATER_MELEE_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_FIRE_MELEE_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_AIR_MELEE_DAMAGE);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.RAW_ELEMENTAL_MELEE_DAMAGE);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.ATTACK_SPEED_BONUS);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.POISON);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.THORNS);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.REFLECTION);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.EXPLODING);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.CRITICAL_DAMAGE_PERCENT);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.WALK_SPEED);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.SPRINT_BONUS);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.SPRINT_REGEN);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.JUMP_HEIGHT);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.STEALING);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.COMBAT_XP_BONUS);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.GATHERING_XP_BONUS);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.GATHERING_SPEED_BONUS);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.LOOT_BONUS);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.LOOT_QUALITY);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.SOUL_POINT_REGEN);

        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.KNOCKBACK);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.HEALING_EFFICIENCY);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.WEAKEN_ENEMY);
        ItemUITemplate.ITEM_IDS.put(ItemUITemplate.ITEM_IDS.size(), Identifications.SLOW_ENEMY);
    }
}
