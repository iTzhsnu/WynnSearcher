package com.github.iTzhsnu.WynnSearcher.data;

import com.github.iTzhsnu.WynnSearcher.ApiDataManager;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.google.gson.JsonObject;

public class DataUtils {

    private static ItemBase itemDataCache = null;
    public static ItemBase getItemDataFromName(String name) {
        if (itemDataCache != null && itemDataCache.getName().equalsIgnoreCase(name)) {
            return itemDataCache;
        } else {
            ApiDataManager api = ApiDataManager.getManager();
            for (ItemBase i : api.wynnItems) {
                if (!i.getName().isEmpty() && i.getName().equalsIgnoreCase(name)) {
                    itemDataCache = i;
                    return i;
                }
            }
        }
        return null;
    }

    // int[] { neutral, earth, thunder, water, fire, air }
    public static void setPowderOnNonCraft(int[] damages, String powderText, JsonKeys sortType) {
        int neutral = damages[0];

        ApiDataManager api = ApiDataManager.getManager();
        int size = (powderText.length() >> 1) << 1;
        for (int i = 0; size > i; i += 2) {
            String text = powderText.substring(i, i + 2);
            if (api.powderData.get(text) != null) {
                JsonObject powder = api.powderData.get(text).getAsJsonObject();
                int pos = powder.get(JsonKeys.POS.getKey()).getAsInt();
                int conv = powder.get("convert").getAsInt();

                int converted = (int) Math.floor(Math.min(neutral, damages[0] * conv * 0.01F));

                neutral -= converted;

                damages[pos] += converted + powder.get(sortType.getKey()).getAsInt();
            }
        }

        damages[0] = neutral;
    }

    // damage = { Neutral, Earth, Thunder, Water, Fire, Air }
    // percent, raw, damageTypePercent, damageTypeRaw = { Neutral, Earth, Thunder, Water, Fire, Damages, Elem Damage }
    // sp = { Strength, Dexterity, Intelligence, Defense, Agility }
    // ratio = { Damage, Earth, Thunder, Water Fire, Air }
    public static float[] calcTotalDamage(int[] damage, int[] percent, int[] raw, int[] damageTypePercent, int[] damageTypeRaw, int[] sp, float attackSpeed, int[] ratio) {
        float[] damageF = new float[6];




        return damageF;
    }

}
