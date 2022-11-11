package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import com.github.iTzhsnu.WynnSearcher.Identifications;

public enum SkillEnum {
    //ANY
    PROFICIENCY_1(new TreeEffect(Identifications.MELEE_DAMAGE_PERCENT, 5), null),
    REDUCE_10_1ST_SP_COST(new TreeEffect(Identifications.RAW_1ST_SPELL_COST, -10), null),
    REDUCE_10_3RD_SP_COST(new TreeEffect(Identifications.RAW_3RD_SPELL_COST, -10), null),
    REDUCE_5_1ST_SP_COST(new TreeEffect(Identifications.RAW_1ST_SPELL_COST, -5), null),
    REDUCE_5_2ND_SP_COST(new TreeEffect(Identifications.RAW_2ND_SPELL_COST, -5), null),
    REDUCE_5_3RD_SP_COST(new TreeEffect(Identifications.RAW_3RD_SPELL_COST, -5), null),
    REDUCE_5_4TH_SP_COST(new TreeEffect(Identifications.RAW_4TH_SPELL_COST, -5), null),
    EARTH_DAMAGE(new TreeEffect(Identifications.EARTH_DAMAGE, "2-4", Identifications.EARTH_DAMAGE_PERCENT, 20), null),
    THUNDER_DAMAGE(new TreeEffect(Identifications.THUNDER_DAMAGE, "1-8", Identifications.THUNDER_DAMAGE_PERCENT, 10), null),
    WATER_DAMAGE(new TreeEffect(Identifications.WATER_DAMAGE, "2-4", Identifications.WATER_DAMAGE_PERCENT, 15), null),
    FIRE_DAMAGE(new TreeEffect(Identifications.FIRE_DAMAGE, "3-5", Identifications.FIRE_DAMAGE_PERCENT, 15), null),
    AIR_DAMAGE(new TreeEffect(Identifications.AIR_DAMAGE, "3-4", Identifications.AIR_DAMAGE_PERCENT, 15), null),

    BASH(null, SpellEnum.BASH),
    DOUBLE_BASH(null, SpellEnum.DOUBLE_BASH),

    ARROW_BOMB(null, SpellEnum.ARROW_BOMB),
    HEART_SHATTER(null, SpellEnum.HEART_SHATTER),
    ;

    private final TreeEffect effect;
    private final SpellEnum spell;

    SkillEnum(TreeEffect effect, SpellEnum spell) {
        this.effect = effect;
        this.spell = spell;
    }

    public TreeEffect getEffect() {
        return effect;
    }

    public SpellEnum getSpell() {
        return spell;
    }
}
