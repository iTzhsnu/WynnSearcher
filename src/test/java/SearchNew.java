import com.github.iTzhsnu.WynnSearcher.GetAPI;
import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchNew {
    private static final List<JsonObject> equips = new ArrayList<>();
    private static final List<JsonObject> ingredients = new ArrayList<>();
    private static final List<JsonObject> others = new ArrayList<>();

    public static void main(String[] args) {
        new GetAPI().loadArchiveV3API(equips, ingredients, others, new JLabel());

        searchUnknownIDs(equips);
    }

    private static void searchUnknownIDs(List<JsonObject> items) {
        List<String> ids_base = new ArrayList<>();
        List<String> ids_req = new ArrayList<>();
        List<String> ids_id = new ArrayList<>();

        for (Identifications id : Identifications.values()) {
            if (id.getItemFieldPos() != null) {
                switch (id.getItemFieldPos()) {
                    case "base" -> ids_base.add(id.getItemName());
                    case "requirements" -> ids_req.add(id.getItemName());
                    case "identifications" -> ids_id.add(id.getItemName());
                }
            }
        }

        List<String> unknownBase = new ArrayList<>();
        List<String> unknownReq = new ArrayList<>();
        List<String> unknownIDs = new ArrayList<>();
        List<String> majorIDs = new ArrayList<>();
        StringBuilder majorID_SB = new StringBuilder();

        for (JsonObject j : items) {
            if (j.get("base") != null) {
                for (Map.Entry<String, JsonElement> entry : j.get("base").getAsJsonObject().entrySet()) {
                    if (!ids_base.contains(entry.getKey()) && !unknownBase.contains(entry.getKey())) {
                        unknownBase.add(entry.getKey());
                    }
                }
            }

            if (j.get("requirements") != null) {
                for (Map.Entry<String, JsonElement> entry : j.get("requirements").getAsJsonObject().entrySet()) {
                    if (!ids_req.contains(entry.getKey()) && !unknownReq.contains(entry.getKey())) {
                        unknownReq.add(entry.getKey());
                    }
                }
            }

            if (j.get("identifications") != null) {
                for (Map.Entry<String, JsonElement> entry : j.get("identifications").getAsJsonObject().entrySet()) {
                    if (!ids_id.contains(entry.getKey()) && !unknownIDs.contains(entry.getKey())) {
                        unknownIDs.add(entry.getKey());
                    }
                }
            }

            if (j.get("majorIds") != null) {
                for (Map.Entry<String, JsonElement> entry : j.get("majorIds").getAsJsonObject().entrySet()) {
                    if (!majorIDs.contains(entry.getKey())) {
                        majorIDs.add(entry.getKey());

                        String s = "\"" + entry.getKey() + "\": \"" + entry.getValue().getAsString() + "\",\n";
                        majorID_SB.append(s);
                    }
                }
            }
        }

        for (String s : unknownBase) {
            System.out.println("Unknown Base ID: " + s);
        }
        for (String s : unknownReq) {
            System.out.println("Unknown Req ID: " + s);
        }
        for (String s : unknownIDs) {
            System.out.println("Unknown ID: " + s);
        }
        if (majorID_SB.length() > 2) System.out.println("{\n" + majorID_SB.substring(0, majorID_SB.length() - 2) + "\n}");
    }

    private static void searchUnknownIDs_ING() {
        List<String> ids_itemOnly = new ArrayList<>();
        List<String> ids_consumeOnly = new ArrayList<>();
        List<String> ids_id = new ArrayList<>();

        for (Identifications id : Identifications.values()) {
            if (id.getIngFieldPos() != null) {
                switch (id.getIngFieldPos()) {
                    case "itemOnlyIDs" -> ids_itemOnly.add(id.getIngName());
                    case "consumableOnlyIDs" -> ids_consumeOnly.add(id.getIngName());
                    case "identifications" -> ids_id.add(id.getIngName());
                }
            }
        }

        List<String> unknownItemOnly = new ArrayList<>();
        List<String> unknownConsumeOnly = new ArrayList<>();
        List<String> unknownIDs = new ArrayList<>();

        for (JsonObject j : ingredients) {
            if (j.get("itemOnlyIDs") != null) {
                for (Map.Entry<String, JsonElement> entry : j.get("itemOnlyIDs").getAsJsonObject().entrySet()) {
                    if (!ids_itemOnly.contains(entry.getKey()) && !unknownItemOnly.contains(entry.getKey())) {
                        unknownItemOnly.add(entry.getKey());
                    }
                }
            }

            if (j.get("consumableOnlyIDs") != null) {
                for (Map.Entry<String, JsonElement> entry : j.get("consumableOnlyIDs").getAsJsonObject().entrySet()) {
                    if (!ids_consumeOnly.contains(entry.getKey()) && !unknownConsumeOnly.contains(entry.getKey())) {
                        unknownConsumeOnly.add(entry.getKey());
                    }
                }
            }

            if (j.get("identifications") != null) {
                for (Map.Entry<String, JsonElement> entry : j.get("identifications").getAsJsonObject().entrySet()) {
                    if (!ids_id.contains(entry.getKey()) && !unknownIDs.contains(entry.getKey())) {
                        unknownIDs.add(entry.getKey());
                    }
                }
            }
        }

        for (String s : unknownItemOnly) {
            System.out.println("Unknown Item Only ID: " + s);
        }
        for (String s : unknownConsumeOnly) {
            System.out.println("Unknown Consume Only: " + s);
        }
        for (String s : unknownIDs) {
            System.out.println("Unknown ID: " + s);
        }
    }

}
