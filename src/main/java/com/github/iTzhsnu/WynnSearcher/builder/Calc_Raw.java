package com.github.iTzhsnu.WynnSearcher.builder;

public class Calc_Raw {
    public final float neutral_min;
    public final float earth_min;
    public final float thunder_min;
    public final float water_min;
    public final float fire_min;
    public final float air_min;

    public final float neutral_max;
    public final float earth_max;
    public final float thunder_max;
    public final float water_max;
    public final float fire_max;
    public final float air_max;

    private final float[] percent;
    private final boolean[] have_mastery;

    public final float[] raw_damages_min = new float[] {0, 0, 0, 0, 0, 0};
    public final float[] raw_damages_max = new float[] {0, 0, 0, 0, 0, 0};

    private float notUsedTotal_min = 0;
    private float notUsedTotal_max = 0;
    private float notUsedElem_min = 0;
    private float notUsedElem_max = 0;

    private float usedTotal_min = 0;
    private float usedTotal_max = 0;
    private float usedElem_min = 0;
    private float usedElem_max = 0;

    public Calc_Raw(float neutral_min, float earth_min, float thunder_min, float water_min, float fire_min, float air_min
            , float neutral_max, float earth_max, float thunder_max, float water_max, float fire_max, float air_max
            , float[] percent, boolean[] have_mastery) {
        this.neutral_min = neutral_min;
        this.earth_min = earth_min;
        this.thunder_min = thunder_min;
        this.water_min = water_min;
        this.fire_min = fire_min;
        this.air_min = air_min;

        this.neutral_max = neutral_max;
        this.earth_max = earth_max;
        this.thunder_max = thunder_max;
        this.water_max = water_max;
        this.fire_max = fire_max;
        this.air_max = air_max;

        this.percent = percent;
        this.have_mastery = have_mastery;
    }

    public void calc(int neutral, int elem) {
        if (neutral == 0 && elem == 0) return;

        float total_min = earth_min + thunder_min + water_min + fire_min + air_min;
        float total_max = earth_max + thunder_max + water_max + fire_max + air_max;
        notUsedTotal_min = 0;
        notUsedTotal_max = 0;
        notUsedElem_min = 0;
        notUsedElem_max = 0;

        usedTotal_min = 0;
        usedTotal_max = 0;
        usedElem_min = 0;
        usedElem_max = 0;

        boolean haveElem_min = total_min != 0;
        boolean haveElem_max = total_max != 0;
        boolean haveDamage_min = total_min + neutral_min != 0;
        boolean haveDamage_max = total_max + neutral_max != 0;
        for (int i = 1; 6 > i; ++i) {
            if (have_mastery[i] && percent[i] != 0) {
                haveElem_min = true;
                haveElem_max = true;
                haveDamage_min = true;
                haveDamage_max = true;
                break;
            }
        }

        //Min
        check(total_min + neutral_min, earth_min, Damage_Display.Damage_Type.EARTH, neutral + elem, false);
        check(total_min + neutral_min, thunder_min, Damage_Display.Damage_Type.THUNDER, neutral + elem, false);
        check(total_min + neutral_min, water_min, Damage_Display.Damage_Type.WATER, neutral + elem, false);
        check(total_min + neutral_min, fire_min, Damage_Display.Damage_Type.FIRE, neutral + elem, false);
        check(total_min + neutral_min, air_min, Damage_Display.Damage_Type.AIR, neutral + elem, false);

        //Max
        check(total_max + neutral_max, earth_max, Damage_Display.Damage_Type.EARTH, neutral + elem, true);
        check(total_max + neutral_max, thunder_max, Damage_Display.Damage_Type.THUNDER, neutral + elem, true);
        check(total_max + neutral_max, water_max, Damage_Display.Damage_Type.WATER, neutral + elem, true);
        check(total_max + neutral_max, fire_max, Damage_Display.Damage_Type.FIRE, neutral + elem, true);
        check(total_max + neutral_max, air_max, Damage_Display.Damage_Type.AIR, neutral + elem, true);

        if (elem != 0) {
            if (total_min == 0) {
                notUsedElem_min += percent[0];
            } else {
                raw_damages_min[Damage_Display.Damage_Type.EARTH.id] += calc(elem, Damage_Display.Damage_Type.EARTH, false, false) * percent[0];
                raw_damages_min[Damage_Display.Damage_Type.THUNDER.id] += calc(elem, Damage_Display.Damage_Type.THUNDER, false, false) * percent[0];
                raw_damages_min[Damage_Display.Damage_Type.WATER.id] += calc(elem, Damage_Display.Damage_Type.WATER, false, false) * percent[0];
                raw_damages_min[Damage_Display.Damage_Type.FIRE.id] += calc(elem, Damage_Display.Damage_Type.FIRE, false, false) * percent[0];
                raw_damages_min[Damage_Display.Damage_Type.AIR.id] += calc(elem, Damage_Display.Damage_Type.AIR, false, false) * percent[0];
            }

            if (total_max == 0) {
                notUsedElem_max += percent[0];
            } else {
                raw_damages_max[Damage_Display.Damage_Type.EARTH.id] += calc(elem, Damage_Display.Damage_Type.EARTH, true, false) * percent[0];
                raw_damages_max[Damage_Display.Damage_Type.THUNDER.id] += calc(elem, Damage_Display.Damage_Type.THUNDER, true, false) * percent[0];
                raw_damages_max[Damage_Display.Damage_Type.WATER.id] += calc(elem, Damage_Display.Damage_Type.WATER, true, false) * percent[0];
                raw_damages_max[Damage_Display.Damage_Type.FIRE.id] += calc(elem, Damage_Display.Damage_Type.FIRE, true, false) * percent[0];
                raw_damages_max[Damage_Display.Damage_Type.AIR.id] += calc(elem, Damage_Display.Damage_Type.AIR, true, false) * percent[0];
            }

            for (int i = 1; 6 > i; ++i) {
                calcNotUsed(elem, notUsedElem_min, usedElem_min, haveElem_min, have_mastery[i], getDamageType(i), false);
                calcNotUsed(elem, notUsedElem_max, usedElem_max, haveElem_max, have_mastery[i],getDamageType(i), true);
            }
        }

        if (neutral != 0) {
            if (neutral_min + total_min == 0) {
                notUsedTotal_min += percent[0];
            } else {
                raw_damages_min[Damage_Display.Damage_Type.NEUTRAL.id] += calc(elem, Damage_Display.Damage_Type.NEUTRAL, false, true) * percent[0];
                raw_damages_min[Damage_Display.Damage_Type.EARTH.id] += calc(elem, Damage_Display.Damage_Type.EARTH, false, true) * percent[0];
                raw_damages_min[Damage_Display.Damage_Type.THUNDER.id] += calc(elem, Damage_Display.Damage_Type.THUNDER, false, true) * percent[0];
                raw_damages_min[Damage_Display.Damage_Type.WATER.id] += calc(elem, Damage_Display.Damage_Type.WATER, false, true) * percent[0];
                raw_damages_min[Damage_Display.Damage_Type.FIRE.id] += calc(elem, Damage_Display.Damage_Type.FIRE, false, true) * percent[0];
                raw_damages_min[Damage_Display.Damage_Type.AIR.id] += calc(elem, Damage_Display.Damage_Type.AIR, false, true) * percent[0];
            }

            if (neutral_max + total_max == 0) {
                notUsedTotal_max += percent[0];
            } else {
                raw_damages_max[Damage_Display.Damage_Type.NEUTRAL.id] += calc(elem, Damage_Display.Damage_Type.NEUTRAL, true, true) * percent[0];
                raw_damages_max[Damage_Display.Damage_Type.EARTH.id] += calc(elem, Damage_Display.Damage_Type.EARTH, true, true) * percent[0];
                raw_damages_max[Damage_Display.Damage_Type.THUNDER.id] += calc(elem, Damage_Display.Damage_Type.THUNDER, true, true) * percent[0];
                raw_damages_max[Damage_Display.Damage_Type.WATER.id] += calc(elem, Damage_Display.Damage_Type.WATER, true, true) * percent[0];
                raw_damages_max[Damage_Display.Damage_Type.FIRE.id] += calc(elem, Damage_Display.Damage_Type.FIRE, true, true) * percent[0];
                raw_damages_max[Damage_Display.Damage_Type.AIR.id] += calc(elem, Damage_Display.Damage_Type.AIR, true, true) * percent[0];
            }

            for (int i = 1; 6 > i; ++i) {
                calcNotUsed(neutral, notUsedTotal_min, usedTotal_min, haveDamage_min, have_mastery[i], getDamageType(i), false);
                calcNotUsed(neutral, notUsedTotal_max, usedTotal_max, haveDamage_max, have_mastery[i],getDamageType(i), true);
            }
        }
    }

    public void check(float total_damage, float damage, Damage_Display.Damage_Type type, int raw, boolean isMax) {
        if (damage != 0 || have_mastery[type.id] || (total_damage != 0 && percent[type.id] != 0)) {
            if (isMax) {
                raw_damages_max[type.id] += raw * percent[type.id];
                usedTotal_max = percent[type.id];
                usedElem_max = percent[type.id];
            } else {
                raw_damages_min[type.id] += raw * percent[type.id];
                usedTotal_min = percent[type.id];
                usedElem_min = percent[type.id];
            }
        } else if (percent[type.id] != 0) {
            if (isMax) {
                notUsedTotal_max += percent[type.id];
                notUsedElem_max += percent[type.id];
            } else {
                notUsedTotal_min += percent[type.id];
                notUsedElem_min += percent[type.id];
            }
        }
    }

    public void calcNotUsed(int raw, float notUsedRaw, float usedTotal, boolean haveDamage, boolean have_mastery, Damage_Display.Damage_Type type, boolean isMax) {
        if (notUsedRaw != 0) {
            if (!haveDamage) {
                float total_percent = percent[1] + percent[2] + percent[3] + percent[4] + percent[5];

                if (isMax) {
                    raw_damages_max[type.id] += raw * notUsedRaw * percent[type.id] /total_percent;
                } else {
                    raw_damages_min[type.id] += raw * notUsedRaw * percent[type.id] /total_percent;
                }
            } else if (percent[type.id] != 0 && have_mastery) {
                if (isMax) {
                    raw_damages_max[type.id] += raw * notUsedRaw * percent[type.id] / usedTotal;
                } else {
                    raw_damages_min[type.id] += raw * notUsedRaw * percent[type.id] / usedTotal;
                }
            }
        }
    }

    public float calc(int raw, Damage_Display.Damage_Type elem, boolean max, boolean isNotElem) {
        if (raw == 0) return 0;

        float total_min = earth_min + thunder_min + water_min + fire_min + air_min;
        float total_max = earth_max + thunder_max + water_max + fire_max + air_max;

        if (isNotElem) {
            total_min += neutral_min;
            total_max += neutral_max;
        }

        float elemMin_OR_Max;
        if (max) {
            switch (elem) {
                case EARTH: elemMin_OR_Max = earth_max;
                    break;
                case THUNDER: elemMin_OR_Max = thunder_max;
                    break;
                case WATER: elemMin_OR_Max = water_max;
                    break;
                case FIRE: elemMin_OR_Max = fire_max;
                    break;
                case AIR: elemMin_OR_Max = air_max;
                    break;
                default: elemMin_OR_Max = neutral_max;
                    break;
            }

            if (elemMin_OR_Max != 0) {
                return raw * (elemMin_OR_Max / total_max);
            }
        } else {
            switch (elem) {
                case EARTH: elemMin_OR_Max = earth_min;
                    break;
                case THUNDER: elemMin_OR_Max = thunder_min;
                    break;
                case WATER: elemMin_OR_Max = water_min;
                    break;
                case FIRE: elemMin_OR_Max = fire_min;
                    break;
                case AIR: elemMin_OR_Max = air_min;
                    break;
                default: elemMin_OR_Max = neutral_min;
                    break;
            }

            if (elemMin_OR_Max != 0) {
                return raw * (elemMin_OR_Max / total_min);
            }
        }

        return 0;
    }

    public Damage_Display.Damage_Type getDamageType(int i) {
        switch (i) {
            case 1:
                return Damage_Display.Damage_Type.EARTH;
            case 2:
                return Damage_Display.Damage_Type.THUNDER;
            case 3:
                return Damage_Display.Damage_Type.WATER;
            case 4:
                return Damage_Display.Damage_Type.FIRE;
            case 5:
                return Damage_Display.Damage_Type.AIR;
            default:
                return Damage_Display.Damage_Type.NEUTRAL;
        }
    }
}
