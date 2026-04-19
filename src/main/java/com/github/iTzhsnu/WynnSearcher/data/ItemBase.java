package com.github.iTzhsnu.WynnSearcher.data;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.ui.ItemUi;
import com.github.iTzhsnu.WynnSearcher.SumEnum;
import com.github.iTzhsnu.WynnSearcher.general.*;
import com.google.gson.JsonObject;

public abstract class ItemBase {
    protected final JsonObject j;

    public ItemBase(JsonObject j) {
        this.j = j;
    }

    public abstract int getIdValue(Identifications id, JsonKeys sortType);

    protected int getIdValue(Identifications id, String idName, JsonKeys fieldPos, JsonKeys sortType) {
        if (idName != null && fieldPos != null && id.getIDType() == DataType.INT) {
            if (fieldPos == JsonKeys.NOTHING) {
                if (j.get(idName) != null) return j.get(idName).getAsInt();
            } else {
                if (j.get(fieldPos.getKey()) != null && j.get(fieldPos.getKey()).getAsJsonObject().get(idName) != null) {
                    if (!j.get(fieldPos.getKey()).getAsJsonObject().get(idName).isJsonObject()) {
                        return j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsInt(); // Not a Json Object
                    } else if (j.get(JsonKeys.IDENTIFIED.getKey()) != null && j.get(JsonKeys.IDENTIFIED.getKey()).getAsBoolean() && id.isItemVariable()) {
                        if (j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(JsonKeys.RAW.getKey()) != null) {
                            return j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(JsonKeys.RAW.getKey()).getAsInt();
                            //return getBaseId(j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(JsonKeys.RAW.getKey()).getAsInt()); // this old code why used?
                        } else {
                            String minOrMax = JsonKeys.MAX.getKey();
                            if (isReversedId(id)) {
                                if (j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt() > 0) minOrMax = JsonKeys.MIN.getKey();
                            } else {
                                if (j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt() < 0) minOrMax = JsonKeys.MIN.getKey();
                            }
                            return getBaseId(j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(minOrMax).getAsInt());
                        }
                    } else if (id.isItemVariable()) { //Item ID Variable
                        if (j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(sortType.getKey()) != null) {
                            return j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(sortType.getKey()).getAsInt();
                        } else {
                            System.out.println("Warning: Variable ID Value get failed.");
                        }
                    }
                }
            }
        }
        return 0;
    }

    public boolean haveId(Identifications id, JsonObject howToObtain, String min, String max) {
        if (id.getIDType() != DataType.SUM) {
            return haveIdValue(id, howToObtain, min, max);
        } else {
            if (id == Identifications.SUM_MELEE_APPROPRIATE || id == Identifications.SUM_SPELL_APPROPRIATE) {
                return hasDamageAppropriateSumId(id.getSum(), min, max);
            }

            boolean need = false;
            boolean needAll = true;
            for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                Identifications id2 = id.getSum().getIds().get(n);
                boolean has = haveIdValue(id2, howToObtain, min, max);
                if (has) {
                    need = true;
                } else {
                    needAll = false;
                }
            }

            if (id.getSum().isNeedAll() && needAll) {
                return true;
            } else {
                return (!id.getSum().isNeedAll() && need);
            }
        }
    }

    public abstract boolean haveIdValue(Identifications id, JsonObject howToObtain, String min, String max);

    public float getTotalSumFloat(SumEnum sum, JsonKeys sortType, String min, String max) {
        if (SumEnum.MELEE_APPROPRIATE == sum || SumEnum.SPELL_APPROPRIATE == sum) {
            return getDamAppropriateSumFloat(sum, sortType, min, max);
        }

        float total = 0;
        float sum_total = 0;
        int sum_total_sub = 0;
        //Base IDs
        if (sum.getIds() != null) {
            for (int n = 0; sum.getIds().size() > n; ++n) {
                Identifications ids = sum.getIds().get(n);
                sum_total += getIdValue(ids, sortType);
            }
        }

        //Sub IDs
        if (sum.getMultiIDs() != null) {
            for (int n = 0; sum.getMultiIDs().size() > n; ++n) {
                Identifications ids = sum.getMultiIDs().get(n);
                sum_total_sub += getIdValue(ids, sortType);
            }
            if (sum_total < 0 && sum_total_sub < 0) {
                sum_total_sub *= -1;
            } else if (sum_total < 0 && sum_total_sub > 0) {
                sum_total_sub *= -1;
                if (sum_total_sub < -100) {
                    sum_total_sub = -100;
                }
            }
            sum_total = sum_total * (1F + sum_total_sub / 100F);
        }
        if (sum.getAddIDs() != null) {
            for (int n = 0; sum.getAddIDs().size() > n; ++n) {
                Identifications ids = sum.getAddIDs().get(n);
                int t = getIdValue(ids, sortType);

                if (sum.isMeleeDPS()) {
                    sum_total += t;
                } else {
                    total += t;
                }
            }
        }

        //DPS (Attack Speed)
        if (sum.isDPS()) {
            if (j.get(Identifications.ATTACK_SPEED.getItemName()) != null) {
                sum_total *= getAttackSpeed();
            }
        }
        return total + sum_total;
    }

    public boolean hasDamageAppropriateSumId(SumEnum sum, String weaponName, String powder) {
        if (weaponName != null && powder != null) {
            if (!weaponName.isEmpty() && DataUtils.getItemJsonFromName(weaponName) != null) {
                JsonObject weapon = DataUtils.getItemJsonFromName(weaponName).j;
                if (weapon != null) {
                    boolean[] has = new boolean[] { false, false, false, false, false, false };

                    // Powder Damage
                    if (!powder.isEmpty()) {
                        int size = (powder.length() >> 1) << 1;
                        POWDER_FIND: for (int i = 0; size > i; i += 2) {
                            try {
                                int tier = Integer.parseInt(powder.substring(i + 1, i + 2));
                                if (tier > 0 && 7 >= tier) {
                                    switch (powder.charAt(i)) {
                                        case DataKeys.POWDER_E -> has[1] = true;
                                        case DataKeys.POWDER_T -> has[2] = true;
                                        case DataKeys.POWDER_W -> has[3] = true;
                                        case DataKeys.POWDER_F -> has[4] = true;
                                        case DataKeys.POWDER_A -> has[5] = true;
                                        default -> {
                                            break POWDER_FIND;
                                        }
                                    }
                                } else {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                break;
                            }
                        }
                    }

                    // Weapon Damage
                    if (weapon.get(Identifications.NEUTRAL_DAMAGE.getItemFieldPos().getKey()) != null) {
                        JsonObject w = weapon.get(Identifications.NEUTRAL_DAMAGE.getItemFieldPos().getKey()).getAsJsonObject();
                        for (int i = 0; ItemUi.DAMAGE_IDS.size() > i; ++i) {
                            if (w.get(ItemUi.DAMAGE_IDS.get(i).getItemName()) != null) {
                                has[i] = true;
                            }
                        }
                    }


                    if (has[0] || has[1] || has[2] || has[3] || has[4] || has[5]) {
                        // Raw Damage
                        for (Identifications id : sum.getSumIDs().get(6).getAddIDs()) {
                            if (haveId(id, null, null, null)) return true;
                        }

                        // Raw Elemental Damage
                        if (has[1] || has[2] || has[3] || has[4] || has[5]) {
                            for (Identifications id : sum.getSumIDs().get(7).getAddIDs()) {
                                if (haveId(id, null, null, null)) return true;
                            }
                        }

                        // Earth, Thunder, Water, Fire and Air Damage Raw and %
                        for (int i = 0; 6 > i; ++i) {
                            if (has[i]) {
                                // Raw **** Damage and Raw **** Spell or Melee Damage
                                for (Identifications id : sum.getSumIDs().get(i).getAddIDs()) {
                                    if (haveId(id, null, null, null)) return true;
                                }

                                // **** Damage %, Elemental Damage %, **** Melee or Spell Damage % and Elemental Melee or Spell Damage %
                                for (Identifications id : sum.getSumIDs().get(i).getMultiIDs()) {
                                    if (haveId(id, null, null, null)) return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public float getDamAppropriateSumFloat(SumEnum sum, JsonKeys sortType, String weaponName, String powder) {
        if (weaponName != null && powder != null) {
            if (!weaponName.isEmpty()) {
                ItemBase weapon = DataUtils.getItemJsonFromName(weaponName);
                if (weapon != null) {
                    float total = 0;
                    float total_sub = 0;

                    // Set Weapon Min and Max Damage
                    int[] damages_min = new int[] { weapon.getIdValue(Identifications.NEUTRAL_DAMAGE, JsonKeys.MIN), weapon.getIdValue(Identifications.EARTH_DAMAGE, JsonKeys.MIN), weapon.getIdValue(Identifications.THUNDER_DAMAGE, JsonKeys.MIN), weapon.getIdValue(Identifications.WATER_DAMAGE, JsonKeys.MIN), weapon.getIdValue(Identifications.FIRE_DAMAGE, JsonKeys.MIN), weapon.getIdValue(Identifications.AIR_DAMAGE, JsonKeys.MIN) };
                    int[] damages_max = new int[] { weapon.getIdValue(Identifications.NEUTRAL_DAMAGE, JsonKeys.MAX), weapon.getIdValue(Identifications.EARTH_DAMAGE, JsonKeys.MAX), weapon.getIdValue(Identifications.THUNDER_DAMAGE, JsonKeys.MAX), weapon.getIdValue(Identifications.WATER_DAMAGE, JsonKeys.MAX), weapon.getIdValue(Identifications.FIRE_DAMAGE, JsonKeys.MAX), weapon.getIdValue(Identifications.AIR_DAMAGE, JsonKeys.MAX) };

                    // Set Powder Damage
                    if (!powder.isEmpty()) {
                        DataUtils.setPowderOnNonCraft(damages_min, powder, JsonKeys.MIN);
                        DataUtils.setPowderOnNonCraft(damages_max, powder, JsonKeys.MAX);
                    }

                    // Average Base Damage
                    float[] damages = new float[] { (damages_min[0] + damages_max[0]) * 0.5F, (damages_min[1] + damages_max[1]) * 0.5F, (damages_min[2] + damages_max[2]) * 0.5F, (damages_min[3] + damages_max[3]) * 0.5F, (damages_min[4] + damages_max[4]) * 0.5F, (damages_min[5] + damages_max[5]) * 0.5F };

                    // Damage %
                    for (int i = 0; 6 > i; ++i) {
                        if (damages[i] != 0) {
                            for (Identifications id : sum.getSumIDs().get(i).getMultiIDs()) {
                                total += damages[i] * weapon.getIdValue(id, sortType) * 0.01F;
                            }
                            for (Identifications id : sum.getSumIDs().get(i).getAddIDs()) {
                                total_sub += weapon.getIdValue(id, sortType);
                            }
                        }
                    }

                    if (damages[0] != 0 || damages[1] != 0 || damages[2] != 0 || damages[3] != 0 || damages[4] != 0 || damages[5] != 0) {
                        // Raw Damage and Raw Melee or Spell Damage
                        for (Identifications id : sum.getSumIDs().get(6).getAddIDs()) {
                            total_sub += weapon.getIdValue(id, sortType);
                        }

                        // Raw Elemental Damage and Raw Elemental Melee or Spell Damage
                        if (damages[1] != 0 || damages[2] != 0 || damages[3] != 0 || damages[4] != 0 || damages[5] != 0) {
                            for (Identifications id : sum.getSumIDs().get(7).getAddIDs()) {
                                total_sub += weapon.getIdValue(id, sortType);
                            }
                        }

                        if (sum.isMeleeDPS()) {
                            return total + total_sub;
                        } else if (sum.isDPS()) {
                            total *= weapon.getAttackSpeed();
                            return total + total_sub;
                        }
                    }
                }
            }
        }
        return 0;
    }

    public float getAttackSpeed() {
        String s;
        if (j.get(Identifications.ATTACK_SPEED.getItemName()) != null) {
            s = j.get(Identifications.ATTACK_SPEED.getItemName()).getAsString();
        } else {
            return 0;
        }

        return switch (s) {
            case "super_fast", JsonValues.SUPER_FAST -> 4.3F;
            case "very_fast", JsonValues.VERY_FAST -> 3.1F;
            case JsonValues.FAST -> 2.5F;
            case JsonValues.A_NORMAL -> 2.05F;
            case JsonValues.SLOW -> 1.5F;
            case "very_slow", JsonValues.VERY_SLOW -> 0.83F;
            case "super_slow", JsonValues.SUPER_SLOW -> 0.51F;
            default -> 0;
        };
    }

    public String getName() {
        if (j.get(JsonKeys.NAME.getKey()) != null) {
            return j.get(JsonKeys.NAME.getKey()).getAsString();
        } else {
            return "";
        }
    }

    public String getSubType() {
        if (j.get(JsonKeys.SUBTYPE.getKey()) != null) {
            return j.get(JsonKeys.SUBTYPE.getKey()).getAsString();
        } else {
            return "";
        }
    }

    public String getType() {
        if (j.get(JsonKeys.TYPE.getKey()) != null) {
            return j.get(JsonKeys.TYPE.getKey()).getAsString();
        } else {
            return "";
        }
    }

    public abstract String getIdString(Identifications id);

    protected String getIdString(Identifications id, String idName, JsonKeys fieldPos) {
        if (idName != null && fieldPos != null) {
            if (fieldPos == JsonKeys.NOTHING) {
                if (j.get(idName) != null) return j.get(idName).getAsString();
            } else {
                if (j.get(fieldPos.getKey()) != null && j.get(fieldPos.getKey()).getAsJsonObject().get(idName) != null) return j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsString();
            }
        }
        return "";
    }

    public abstract boolean haveFieldPos(Identifications id);

    public boolean haveFieldPos(JsonKeys fieldPos) {
        if (fieldPos != null) {
            if (fieldPos == JsonKeys.NOTHING) {
                return false;
            } else {
                return (j.get(fieldPos.getKey()) != null);
            }
        }
        return false;
    }

    public static int getBaseId(int i) {
        return Math.round(i / 1.3F);
    }

    public static boolean isReversedId(Identifications id) {
        return id == Identifications.RAW_1ST_SPELL_COST || id == Identifications.RAW_2ND_SPELL_COST || id == Identifications.RAW_3RD_SPELL_COST || id == Identifications.RAW_4TH_SPELL_COST || id == Identifications.PERCENT_1ST_SPELL_COST || id == Identifications.PERCENT_2ND_SPELL_COST || id == Identifications.PERCENT_3RD_SPELL_COST || id == Identifications.PERCENT_4TH_SPELL_COST;
    }

    @Deprecated
    public JsonObject getJson() {
        return j;
    }
}
