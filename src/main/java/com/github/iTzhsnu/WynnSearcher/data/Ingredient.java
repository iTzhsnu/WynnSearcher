package com.github.iTzhsnu.WynnSearcher.data;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.ui.ItemUi;
import com.github.iTzhsnu.WynnSearcher.general.DataType;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Ingredient extends ItemBase {
    public Ingredient(JsonObject j) {
        super(j);
    }

    @Override
    public int getIdValue(Identifications id, JsonKeys sortType) {
        return getIdValue(id, id.getIngName(), id.getIngFieldPos(), sortType);
    }

    @Override
    public boolean haveIdValue(Identifications id, JsonObject howToObtain, String min, String max) {
        if (id.getIngName() != null) {
            if (id.getIDType() == DataType.INT) {
                return getIdValue(id, JsonKeys.MAX) != 0;
            } else if (id.getIDType() == DataType.STRING) {
                // Check has ID
                if (id.getIngFieldPos() != null) {
                    if (id.getIngFieldPos() == JsonKeys.NOTHING) {
                        if (j.get(id.getIngName()) == null) return false;
                    } else if (id.getIngFieldPos() == JsonKeys.OTHERS) {
                        return false;
                    } else {
                        if (j.get(id.getIngFieldPos().getKey()) != null) {
                            if (j.get(id.getIngFieldPos().getKey()).getAsJsonObject().get(id.getIngName()) == null) return false;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }

                // Check match ID or filtering
                if (id.getIngName().equals(Identifications.DROP_TYPE.getIngName())) {
                    switch (id) {
                        case DROP_TYPE_UNKNOWN: {
                            boolean isObtainable = false;
                            for (JsonElement je : j.get(JsonKeys.DROPPED_BY.getKey()).getAsJsonArray()) {
                                if (je.getAsJsonObject().get(JsonKeys.NAME.getKey()) != null && !je.getAsJsonObject().get(JsonKeys.NAME.getKey()).getAsString().equals(JsonValues.DUMMY)) {
                                    isObtainable = true;
                                    break;
                                }
                            }
                            int haveItem = ItemUi.haveManualDrop(howToObtain, j.get(JsonKeys.NAME.getKey()).getAsString());
                            if (haveItem > 0 && haveItem != 1) isObtainable = true;

                            return !isObtainable;
                        }
                        case DROP_TYPE_SPECIFIC_DROP: {
                            for (JsonElement je : j.get(JsonKeys.DROPPED_BY.getKey()).getAsJsonArray()) {
                                if (je.getAsJsonObject().get(JsonKeys.NAME.getKey()) != null && !je.getAsJsonObject().get(JsonKeys.NAME.getKey()).getAsString().equals(JsonValues.DUMMY)) {
                                    return true;
                                }
                            }
                            return (howToObtain.get(JsonValues.SPECIFIC) != null && howToObtain.get(JsonValues.SPECIFIC).getAsJsonObject().get(j.get(JsonKeys.NAME.getKey()).getAsString()) != null);
                        }
                        case DROP_TYPE_NORMAL:
                        case DROP_TYPE_UNOBTAINABLE:
                            if (howToObtain.get(id.getDisplayName()) != null) {
                                for (JsonElement je : howToObtain.get(id.getDisplayName()).getAsJsonArray()) {
                                    if (je.getAsString().equals(j.get(JsonKeys.NAME.getKey()).getAsString())) {
                                        return true;
                                    }
                                }
                            }
                            break;
                        case DROP_TYPE_MERCHANT:
                        case DROP_TYPE_QUEST:
                        case DROP_TYPE_RAID_REWARDS:
                        case DROP_TYPE_WORLD_EVENT:
                        case DROP_TYPE_LOOTRUN:
                            return (howToObtain.get(id.getDisplayName()) != null && howToObtain.get(id.getDisplayName()).getAsJsonObject().get(j.get(JsonKeys.NAME.getKey()).getAsString()) != null);
                    }
                } else {
                    return true;
                }
            } else {
                System.out.println("Warning: Can't check this ID");
            }
        }
        return false;
    }

    @Override
    public String getIdString(Identifications id) {
        return getIdString(id, id.getIngName(), id.getIngFieldPos());
    }

    @Override
    public boolean haveFieldPos(Identifications id) {
        return haveFieldPos(id.getIngFieldPos());
    }

    public JsonArray getSkills() {
        return j.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray();
    }
}
