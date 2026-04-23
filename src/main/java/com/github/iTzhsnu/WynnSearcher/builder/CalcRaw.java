package com.github.iTzhsnu.WynnSearcher.builder;

public class CalcRaw {
    public final float neutralMin;
    public final float earthMin;
    public final float thunderMin;
    public final float waterMin;
    public final float fireMin;
    public final float airMin;

    public final float neutralMax;
    public final float earthMax;
    public final float thunderMax;
    public final float waterMax;
    public final float fireMax;
    public final float airMax;

    private final float[] percent;
    private final boolean[] haveMastery;

    public final float[] rawDamagesMin = new float[] {0, 0, 0, 0, 0, 0};
    public final float[] rawDamagesMax = new float[] {0, 0, 0, 0, 0, 0};

    private float notUsedTotalMin = 0;
    private float notUsedTotalMax = 0;
    private float notUsedElemMin = 0;
    private float notUsedElemMax = 0;

    private float usedTotalMin = 0;
    private float usedTotalMax = 0;
    private float usedElemMin = 0;
    private float usedElemMax = 0;

    public CalcRaw(float neutralMin, float earthMin, float thunderMin, float waterMin, float fireMin, float airMin
            , float neutralMax, float earthMax, float thunderMax, float waterMax, float fireMax, float airMax
            , float[] percent, boolean[] haveMastery) {
        this.neutralMin = neutralMin;
        this.earthMin = earthMin;
        this.thunderMin = thunderMin;
        this.waterMin = waterMin;
        this.fireMin = fireMin;
        this.airMin = airMin;

        this.neutralMax = neutralMax;
        this.earthMax = earthMax;
        this.thunderMax = thunderMax;
        this.waterMax = waterMax;
        this.fireMax = fireMax;
        this.airMax = airMax;

        this.percent = percent;
        this.haveMastery = haveMastery;
    }

    public void calc(int neutral, int elem) {
        if (neutral == 0 && elem == 0) return;

        float totalMin = earthMin + thunderMin + waterMin + fireMin + airMin;
        float totalMax = earthMax + thunderMax + waterMax + fireMax + airMax;
        notUsedTotalMin = 0;
        notUsedTotalMax = 0;
        notUsedElemMin = 0;
        notUsedElemMax = 0;

        usedTotalMin = 0;
        usedTotalMax = 0;
        usedElemMin = 0;
        usedElemMax = 0;

        boolean haveElemMin = totalMin != 0;
        boolean haveElemMax = totalMax != 0;
        boolean haveDamageMin = totalMin + neutralMin != 0;
        boolean haveDamageMax = totalMax + neutralMax != 0;
        for (int i = 1; 6 > i; ++i) {
            if (haveMastery[i] && percent[i] != 0) {
                haveElemMin = true;
                haveElemMax = true;
                haveDamageMin = true;
                haveDamageMax = true;
                break;
            }
        }

        //Min
        check(totalMin + neutralMin, earthMin, DamageDisplay.Damage_Type.EARTH, neutral + elem, false);
        check(totalMin + neutralMin, thunderMin, DamageDisplay.Damage_Type.THUNDER, neutral + elem, false);
        check(totalMin + neutralMin, waterMin, DamageDisplay.Damage_Type.WATER, neutral + elem, false);
        check(totalMin + neutralMin, fireMin, DamageDisplay.Damage_Type.FIRE, neutral + elem, false);
        check(totalMin + neutralMin, airMin, DamageDisplay.Damage_Type.AIR, neutral + elem, false);

        //Max
        check(totalMax + neutralMax, earthMax, DamageDisplay.Damage_Type.EARTH, neutral + elem, true);
        check(totalMax + neutralMax, thunderMax, DamageDisplay.Damage_Type.THUNDER, neutral + elem, true);
        check(totalMax + neutralMax, waterMax, DamageDisplay.Damage_Type.WATER, neutral + elem, true);
        check(totalMax + neutralMax, fireMax, DamageDisplay.Damage_Type.FIRE, neutral + elem, true);
        check(totalMax + neutralMax, airMax, DamageDisplay.Damage_Type.AIR, neutral + elem, true);

        if (elem != 0) {
            if (totalMin == 0) {
                notUsedElemMin += percent[0];
            } else {
                rawDamagesMin[DamageDisplay.Damage_Type.EARTH.id] += calc(elem, DamageDisplay.Damage_Type.EARTH, false, false) * percent[0];
                rawDamagesMin[DamageDisplay.Damage_Type.THUNDER.id] += calc(elem, DamageDisplay.Damage_Type.THUNDER, false, false) * percent[0];
                rawDamagesMin[DamageDisplay.Damage_Type.WATER.id] += calc(elem, DamageDisplay.Damage_Type.WATER, false, false) * percent[0];
                rawDamagesMin[DamageDisplay.Damage_Type.FIRE.id] += calc(elem, DamageDisplay.Damage_Type.FIRE, false, false) * percent[0];
                rawDamagesMin[DamageDisplay.Damage_Type.AIR.id] += calc(elem, DamageDisplay.Damage_Type.AIR, false, false) * percent[0];
            }

            if (totalMax == 0) {
                notUsedElemMax += percent[0];
            } else {
                rawDamagesMax[DamageDisplay.Damage_Type.EARTH.id] += calc(elem, DamageDisplay.Damage_Type.EARTH, true, false) * percent[0];
                rawDamagesMax[DamageDisplay.Damage_Type.THUNDER.id] += calc(elem, DamageDisplay.Damage_Type.THUNDER, true, false) * percent[0];
                rawDamagesMax[DamageDisplay.Damage_Type.WATER.id] += calc(elem, DamageDisplay.Damage_Type.WATER, true, false) * percent[0];
                rawDamagesMax[DamageDisplay.Damage_Type.FIRE.id] += calc(elem, DamageDisplay.Damage_Type.FIRE, true, false) * percent[0];
                rawDamagesMax[DamageDisplay.Damage_Type.AIR.id] += calc(elem, DamageDisplay.Damage_Type.AIR, true, false) * percent[0];
            }

            for (int i = 1; 6 > i; ++i) {
                calcNotUsed(elem, notUsedElemMin, usedElemMin, haveElemMin, haveMastery[i], getDamageType(i), false);
                calcNotUsed(elem, notUsedElemMax, usedElemMax, haveElemMax, haveMastery[i],getDamageType(i), true);
            }
        }

        if (neutral != 0) {
            if (neutralMin + totalMin == 0) {
                notUsedTotalMin += percent[0];
            } else {
                rawDamagesMin[DamageDisplay.Damage_Type.NEUTRAL.id] += calc(elem, DamageDisplay.Damage_Type.NEUTRAL, false, true) * percent[0];
                rawDamagesMin[DamageDisplay.Damage_Type.EARTH.id] += calc(elem, DamageDisplay.Damage_Type.EARTH, false, true) * percent[0];
                rawDamagesMin[DamageDisplay.Damage_Type.THUNDER.id] += calc(elem, DamageDisplay.Damage_Type.THUNDER, false, true) * percent[0];
                rawDamagesMin[DamageDisplay.Damage_Type.WATER.id] += calc(elem, DamageDisplay.Damage_Type.WATER, false, true) * percent[0];
                rawDamagesMin[DamageDisplay.Damage_Type.FIRE.id] += calc(elem, DamageDisplay.Damage_Type.FIRE, false, true) * percent[0];
                rawDamagesMin[DamageDisplay.Damage_Type.AIR.id] += calc(elem, DamageDisplay.Damage_Type.AIR, false, true) * percent[0];
            }

            if (neutralMax + totalMax == 0) {
                notUsedTotalMax += percent[0];
            } else {
                rawDamagesMax[DamageDisplay.Damage_Type.NEUTRAL.id] += calc(elem, DamageDisplay.Damage_Type.NEUTRAL, true, true) * percent[0];
                rawDamagesMax[DamageDisplay.Damage_Type.EARTH.id] += calc(elem, DamageDisplay.Damage_Type.EARTH, true, true) * percent[0];
                rawDamagesMax[DamageDisplay.Damage_Type.THUNDER.id] += calc(elem, DamageDisplay.Damage_Type.THUNDER, true, true) * percent[0];
                rawDamagesMax[DamageDisplay.Damage_Type.WATER.id] += calc(elem, DamageDisplay.Damage_Type.WATER, true, true) * percent[0];
                rawDamagesMax[DamageDisplay.Damage_Type.FIRE.id] += calc(elem, DamageDisplay.Damage_Type.FIRE, true, true) * percent[0];
                rawDamagesMax[DamageDisplay.Damage_Type.AIR.id] += calc(elem, DamageDisplay.Damage_Type.AIR, true, true) * percent[0];
            }

            for (int i = 1; 6 > i; ++i) {
                calcNotUsed(neutral, notUsedTotalMin, usedTotalMin, haveDamageMin, haveMastery[i], getDamageType(i), false);
                calcNotUsed(neutral, notUsedTotalMax, usedTotalMax, haveDamageMax, haveMastery[i],getDamageType(i), true);
            }
        }
    }

    public void check(float totalDamage, float damage, DamageDisplay.Damage_Type type, int raw, boolean isMax) {
        if (damage != 0 || haveMastery[type.id] || (totalDamage != 0 && percent[type.id] != 0)) {
            if (isMax) {
                rawDamagesMax[type.id] += raw * percent[type.id];
                usedTotalMax = percent[type.id];
                usedElemMax = percent[type.id];
            } else {
                rawDamagesMin[type.id] += raw * percent[type.id];
                usedTotalMin = percent[type.id];
                usedElemMin = percent[type.id];
            }
        } else if (percent[type.id] != 0) {
            if (isMax) {
                notUsedTotalMax += percent[type.id];
                notUsedElemMax += percent[type.id];
            } else {
                notUsedTotalMin += percent[type.id];
                notUsedElemMin += percent[type.id];
            }
        }
    }

    public void calcNotUsed(int raw, float notUsedRaw, float usedTotal, boolean haveDamage, boolean haveMastery, DamageDisplay.Damage_Type type, boolean isMax) {
        if (notUsedRaw != 0) {
            if (!haveDamage) {
                float totalPercent = percent[1] + percent[2] + percent[3] + percent[4] + percent[5];

                if (isMax) {
                    rawDamagesMax[type.id] += raw * notUsedRaw * percent[type.id] / totalPercent;
                } else {
                    rawDamagesMin[type.id] += raw * notUsedRaw * percent[type.id] / totalPercent;
                }
            } else if (percent[type.id] != 0 && haveMastery) {
                if (isMax) {
                    rawDamagesMax[type.id] += raw * notUsedRaw * percent[type.id] / usedTotal;
                } else {
                    rawDamagesMin[type.id] += raw * notUsedRaw * percent[type.id] / usedTotal;
                }
            }
        }
    }

    public float calc(int raw, DamageDisplay.Damage_Type elem, boolean max, boolean isNotElem) {
        if (raw == 0) return 0;

        float totalMin = earthMin + thunderMin + waterMin + fireMin + airMin;
        float totalMax = earthMax + thunderMax + waterMax + fireMax + airMax;

        if (isNotElem) {
            totalMin += neutralMin;
            totalMax += neutralMax;
        }

        float elemMinORMax;
        if (max) {
            switch (elem) {
                case EARTH: elemMinORMax = earthMax;
                    break;
                case THUNDER: elemMinORMax = thunderMax;
                    break;
                case WATER: elemMinORMax = waterMax;
                    break;
                case FIRE: elemMinORMax = fireMax;
                    break;
                case AIR: elemMinORMax = airMax;
                    break;
                default: elemMinORMax = neutralMax;
                    break;
            }

            if (elemMinORMax != 0) {
                return raw * (elemMinORMax / totalMax);
            }
        } else {
            switch (elem) {
                case EARTH: elemMinORMax = earthMin;
                    break;
                case THUNDER: elemMinORMax = thunderMin;
                    break;
                case WATER: elemMinORMax = waterMin;
                    break;
                case FIRE: elemMinORMax = fireMin;
                    break;
                case AIR: elemMinORMax = airMin;
                    break;
                default: elemMinORMax = neutralMin;
                    break;
            }

            if (elemMinORMax != 0) {
                return raw * (elemMinORMax / totalMin);
            }
        }

        return 0;
    }

    public DamageDisplay.Damage_Type getDamageType(int i) {
        switch (i) {
            case 1:
                return DamageDisplay.Damage_Type.EARTH;
            case 2:
                return DamageDisplay.Damage_Type.THUNDER;
            case 3:
                return DamageDisplay.Damage_Type.WATER;
            case 4:
                return DamageDisplay.Damage_Type.FIRE;
            case 5:
                return DamageDisplay.Damage_Type.AIR;
            default:
                return DamageDisplay.Damage_Type.NEUTRAL;
        }
    }
}
