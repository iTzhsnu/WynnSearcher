package com.github.iTzhsnu.WynnSearcher.data;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.ui.ItemUi;
import com.github.iTzhsnu.WynnSearcher.general.DataType;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Item extends ItemBase {

    public Item(JsonObject j) {
        super(j);
    }

    @Override
    public int getIdValue(Identifications id, JsonKeys sortType) {
        return getIdValue(id, id.getItemName(), id.getItemFieldPos(), sortType);
    }

    @Override
    public boolean haveIdValue(Identifications id, JsonObject howToObtain, String min, String max) {
        if (id.getItemName() != null) {
            if (id.getIDType() == DataType.INT) {
                return getIdValue(id, JsonKeys.MAX) != 0;
            } else if (id.getIDType() == DataType.STRING) {
                // Check has ID
                if (id.getItemFieldPos() != null) {
                    if (id.getItemFieldPos() == JsonKeys.NOTHING) {
                        if (j.get(id.getItemName()) == null) return false;
                    } else if (id.getItemFieldPos() == JsonKeys.OTHERS) {
                        return false;
                    } else {
                        if (j.get(id.getItemFieldPos().getKey()) != null) {
                            if (j.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName()) == null) return false;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }

                // Check match ID or Filtering
                if (id.getItemName().equals(Identifications.DROP_TYPE.getItemName())) { // If Drop Type
                    if (j.get(JsonKeys.NAME.getKey()) != null) {
                        String itemName = j.get(JsonKeys.NAME.getKey()).getAsString();
                        if (ItemUi.haveManualDrop(howToObtain, itemName) > 0 && howToObtain.get(id.getDisplayName()) != null) { // Manual Drop Type Check
                            if (howToObtain.get(id.getDisplayName()).isJsonArray()) {
                                for (JsonElement je : howToObtain.get(id.getDisplayName()).getAsJsonArray()) {
                                    if (je.getAsString().equals(itemName)) {
                                        return true;
                                    }
                                }
                            } else if (howToObtain.get(id.getDisplayName()).isJsonObject()) {
                                if (howToObtain.get(id.getDisplayName()).getAsJsonObject().get(itemName) != null) {
                                    return true;
                                }
                            }
                        }

                        if (j.get(JsonKeys.DROP_META.getKey()) != null) {
                            if (j.get(JsonKeys.DROP_META.getKey()).getAsJsonObject().get(JsonKeys.TYPE.getKey()).isJsonArray()) {
                                for (JsonElement je : j.get(JsonKeys.DROP_META.getKey()).getAsJsonObject().get(JsonKeys.TYPE.getKey()).getAsJsonArray()) {
                                    return id.getDisplayName().equals(je.getAsString());
                                }
                            } else {
                                return id.getDisplayName().equals(j.get(JsonKeys.DROP_META.getKey()).getAsJsonObject().get(JsonKeys.TYPE.getKey()).getAsString());
                            }
                        }

                        if (j.get(JsonKeys.DROP_RESTRICTION.getKey()) != null) {
                            if (id == Identifications.DROP_TYPE_UNKNOWN) {
                                if (ItemUi.haveManualDrop(howToObtain, itemName) > 0) return false;
                            }
                            return id.getDisplayName().equals(j.get(JsonKeys.DROP_RESTRICTION.getKey()).getAsString());
                        }
                    }
                } else if (id == Identifications.MAJOR_IDS) {
                    if (!min.isEmpty() || !max.isEmpty()) {
                        String s = max;
                        if (!min.isEmpty()) s = min;
                        //if (j.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().get("name").getAsString().toLowerCase().contains(s.toLowerCase())) remove = false;
                        for (Map.Entry<String, JsonElement> entry : j.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().entrySet()) {
                            if (entry.getKey().toLowerCase().contains(s.toLowerCase())) {
                                return true;
                            }
                        }
                    } else { //has No Text (Major ID)
                        return true;
                    }
                } else if (id == Identifications.ATTACK_SPEED || id == Identifications.SET) {
                    if (!min.isEmpty() || !max.isEmpty()) {
                        String s = max;
                        if (!min.isEmpty()) s = min;

                        return j.get(id.getItemName()).getAsString().toLowerCase().contains(s.toLowerCase());
                    } else { // has No Text (Attack Speed)
                        return true;
                    }
                }  else {
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
        return getIdString(id, id.getItemName(), id.getIngFieldPos());
    }

    @Override
    public boolean haveFieldPos(Identifications id) {
        return haveFieldPos(id.getItemFieldPos());
    }
}
