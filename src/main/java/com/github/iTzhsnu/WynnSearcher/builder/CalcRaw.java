package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.data.DamageType;

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
        check(totalMin + neutralMin, earthMin, DamageType.EARTH, neutral + elem, false);
        check(totalMin + neutralMin, thunderMin, DamageType.THUNDER, neutral + elem, false);
        check(totalMin + neutralMin, waterMin, DamageType.WATER, neutral + elem, false);
        check(totalMin + neutralMin, fireMin, DamageType.FIRE, neutral + elem, false);
        check(totalMin + neutralMin, airMin, DamageType.AIR, neutral + elem, false);

        //Max
        check(totalMax + neutralMax, earthMax, DamageType.EARTH, neutral + elem, true);
        check(totalMax + neutralMax, thunderMax, DamageType.THUNDER, neutral + elem, true);
        check(totalMax + neutralMax, waterMax, DamageType.WATER, neutral + elem, true);
        check(totalMax + neutralMax, fireMax, DamageType.FIRE, neutral + elem, true);
        check(totalMax + neutralMax, airMax, DamageType.AIR, neutral + elem, true);

        if (elem != 0) {
            if (totalMin == 0) {
                notUsedElemMin += percent[0];
            } else {
                rawDamagesMin[DamageType.EARTH.getId()] += calc(elem, DamageType.EARTH, false, false) * percent[0];
                rawDamagesMin[DamageType.THUNDER.getId()] += calc(elem, DamageType.THUNDER, false, false) * percent[0];
                rawDamagesMin[DamageType.WATER.getId()] += calc(elem, DamageType.WATER, false, false) * percent[0];
                rawDamagesMin[DamageType.FIRE.getId()] += calc(elem, DamageType.FIRE, false, false) * percent[0];
                rawDamagesMin[DamageType.AIR.getId()] += calc(elem, DamageType.AIR, false, false) * percent[0];
            }

            if (totalMax == 0) {
                notUsedElemMax += percent[0];
            } else {
                rawDamagesMax[DamageType.EARTH.getId()] += calc(elem, DamageType.EARTH, true, false) * percent[0];
                rawDamagesMax[DamageType.THUNDER.getId()] += calc(elem, DamageType.THUNDER, true, false) * percent[0];
                rawDamagesMax[DamageType.WATER.getId()] += calc(elem, DamageType.WATER, true, false) * percent[0];
                rawDamagesMax[DamageType.FIRE.getId()] += calc(elem, DamageType.FIRE, true, false) * percent[0];
                rawDamagesMax[DamageType.AIR.getId()] += calc(elem, DamageType.AIR, true, false) * percent[0];
            }

            for (int i = 1; 6 > i; ++i) {
                calcNotUsed(elem, notUsedElemMin, usedElemMin, haveElemMin, haveMastery[i], DamageType.getDamageType(i), false);
                calcNotUsed(elem, notUsedElemMax, usedElemMax, haveElemMax, haveMastery[i], DamageType.getDamageType(i), true);
            }
        }

        if (neutral != 0) {
            if (neutralMin + totalMin == 0) {
                notUsedTotalMin += percent[0];
            } else {
                rawDamagesMin[DamageType.NEUTRAL.getId()] += calc(elem, DamageType.NEUTRAL, false, true) * percent[0];
                rawDamagesMin[DamageType.EARTH.getId()] += calc(elem, DamageType.EARTH, false, true) * percent[0];
                rawDamagesMin[DamageType.THUNDER.getId()] += calc(elem, DamageType.THUNDER, false, true) * percent[0];
                rawDamagesMin[DamageType.WATER.getId()] += calc(elem, DamageType.WATER, false, true) * percent[0];
                rawDamagesMin[DamageType.FIRE.getId()] += calc(elem, DamageType.FIRE, false, true) * percent[0];
                rawDamagesMin[DamageType.AIR.getId()] += calc(elem, DamageType.AIR, false, true) * percent[0];
            }

            if (neutralMax + totalMax == 0) {
                notUsedTotalMax += percent[0];
            } else {
                rawDamagesMax[DamageType.NEUTRAL.getId()] += calc(elem, DamageType.NEUTRAL, true, true) * percent[0];
                rawDamagesMax[DamageType.EARTH.getId()] += calc(elem, DamageType.EARTH, true, true) * percent[0];
                rawDamagesMax[DamageType.THUNDER.getId()] += calc(elem, DamageType.THUNDER, true, true) * percent[0];
                rawDamagesMax[DamageType.WATER.getId()] += calc(elem, DamageType.WATER, true, true) * percent[0];
                rawDamagesMax[DamageType.FIRE.getId()] += calc(elem, DamageType.FIRE, true, true) * percent[0];
                rawDamagesMax[DamageType.AIR.getId()] += calc(elem, DamageType.AIR, true, true) * percent[0];
            }

            for (int i = 1; 6 > i; ++i) {
                calcNotUsed(neutral, notUsedTotalMin, usedTotalMin, haveDamageMin, haveMastery[i], DamageType.getDamageType(i), false);
                calcNotUsed(neutral, notUsedTotalMax, usedTotalMax, haveDamageMax, haveMastery[i], DamageType.getDamageType(i), true);
            }
        }
    }

    public void check(float totalDamage, float damage, DamageType type, int raw, boolean isMax) {
        if (damage != 0 || haveMastery[type.getId()] || (totalDamage != 0 && percent[type.getId()] != 0)) {
            if (isMax) {
                rawDamagesMax[type.getId()] += raw * percent[type.getId()];
                usedTotalMax = percent[type.getId()];
                usedElemMax = percent[type.getId()];
            } else {
                rawDamagesMin[type.getId()] += raw * percent[type.getId()];
                usedTotalMin = percent[type.getId()];
                usedElemMin = percent[type.getId()];
            }
        } else if (percent[type.getId()] != 0) {
            if (isMax) {
                notUsedTotalMax += percent[type.getId()];
                notUsedElemMax += percent[type.getId()];
            } else {
                notUsedTotalMin += percent[type.getId()];
                notUsedElemMin += percent[type.getId()];
            }
        }
    }

    public void calcNotUsed(int raw, float notUsedRaw, float usedTotal, boolean haveDamage, boolean haveMastery, DamageType type, boolean isMax) {
        if (notUsedRaw != 0) {
            if (!haveDamage) {
                float totalPercent = percent[1] + percent[2] + percent[3] + percent[4] + percent[5];

                if (isMax) {
                    rawDamagesMax[type.getId()] += raw * notUsedRaw * percent[type.getId()] / totalPercent;
                } else {
                    rawDamagesMin[type.getId()] += raw * notUsedRaw * percent[type.getId()] / totalPercent;
                }
            } else if (percent[type.getId()] != 0 && haveMastery) {
                if (isMax) {
                    rawDamagesMax[type.getId()] += raw * notUsedRaw * percent[type.getId()] / usedTotal;
                } else {
                    rawDamagesMin[type.getId()] += raw * notUsedRaw * percent[type.getId()] / usedTotal;
                }
            }
        }
    }

    public float calc(int raw, DamageType elem, boolean max, boolean isNotElem) {
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
}
