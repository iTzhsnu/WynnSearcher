package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.SearchUI;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SkillPoint {
    private final SkillPointPanel strength;
    private final SkillPointPanel dexterity;
    private final SkillPointPanel intelligence;
    private final SkillPointPanel defense;
    private final SkillPointPanel agility;
    private final JLabel spText = new JLabel("Using Skill Point: 0");
    private List<Integer> equipOrder = null;

    public SkillPoint(JPanel p) {
        int y = 340;
        this.strength = new SkillPointPanel("Strength", 182, y, p);
        this.dexterity = new SkillPointPanel("Dexterity", 312, y, p);
        this.intelligence = new SkillPointPanel("Intelligence", 442, y, p);
        this.defense = new SkillPointPanel("Defense", 572, y, p);
        this.agility = new SkillPointPanel("Agility", 702, y, p);
        spText.setBounds(10, 375, 200, 20);
        p.add(spText);
    }

    public void setSkillPoint(ItemJsons items, List<SetBonus> setBonuses) {
        int[] strI = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Strength Skill Point Bonus
        int[] dexI = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Dexterity Skill Point Bonus
        int[] intI = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Intelligence Skill Point Bonus
        int[] defI = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Defense Skill Point Bonus
        int[] agiI = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Agility Skill Point Bonus
        int[] strR = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Strength Req
        int[] dexR = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Dexterity Req
        int[] intR = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Intelligence Req
        int[] defR = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Defense Req
        int[] agiR = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Agility Req
        int[] maxSPReq = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}; //Max Skill Point Req (Strength, Dexterity...)
        int[] originalSP = new int[] {0, 0, 0, 0, 0}; //Original Skill Point
        int[] totalSP = new int[] {0, 0, 0, 0, 0}; //Total (Non Crafted and Weapon) Skill Point
        int[] assignSP = new int[] {0, 0, 0, 0, 0}; //Assign Skill Point

        boolean[] isCrafted = checkCrafted(items.getEquipments());

        List<SetBonus> haveSPBSets = getHaveSPSetBonus(setBonuses, originalSP, totalSP);
        Set<Integer> isNull = new HashSet<>();
        List<Integer> crafted = new ArrayList<>();
        List<Integer> checked = new ArrayList<>();
        int size = items.getJsonObjectList().size();

        //Add Guild Tome SPB
        if (items.getGuildTome() != null) {
            getTomeSkillPoints(items.getGuildTome(), originalSP, totalSP);
        }

        //Set Req and Bonus
        for (int i = 0; 8 >= i; ++i) {
            JsonObject j = items.getEquipments().get(i);
            if (j != null) {
                setSPReq(j, i, strR, dexR, intR, defR, agiR, maxSPReq);

                getSkillPoints(j, Identifications.STRENGTH, strI, i, totalSP, originalSP, i != 8);
                getSkillPoints(j, Identifications.DEXTERITY, dexI, i, totalSP, originalSP, i != 8);
                getSkillPoints(j, Identifications.INTELLIGENCE, intI, i, totalSP, originalSP, i != 8);
                getSkillPoints(j, Identifications.DEFENSE, defI, i, totalSP, originalSP, i != 8);
                getSkillPoints(j, Identifications.AGILITY, agiI, i, totalSP, originalSP, i != 8);

                if (strR[i] == 0 && dexR[i] == 0 && intR[i] == 0 && defR[i] == 0 && agiR[i] == 0 && !isCrafted[i] && i != 8) {
                    addSPB(i, strI, dexI, intI, defI, agiI, originalSP, totalSP);
                    checked.add(i);
                    checkSet(j, haveSPBSets, originalSP, totalSP);
                }
            } else {
                isNull.add(i);
            }
        }

        //Search can equip
        boolean canEquip = true;
        while (canEquip) {
            canEquip = searchEquip(items.getEquipments(), haveSPBSets, strR, dexR, intR, defR, agiR, strI, dexI, intI, defI, agiI, originalSP, totalSP, checked, crafted, isNull, size);
        }

        //Search Minimum Request
        boolean searchMinReq = true;
        while (searchMinReq) {
            searchMinReq = searchMinReq(items.getEquipments(), haveSPBSets, maxSPReq, strR, dexR, intR, defR, agiR, strI, dexI, intI, defI, agiI, originalSP, totalSP, assignSP, checked, crafted, isNull, size);
        }

        //Set Weapon
        if (items.getWeapon() != null) {
            applySPReq(8, strR, dexR, intR, defR, agiR, originalSP, totalSP, assignSP);

            originalSP[0] += strI[8];
            originalSP[1] += dexI[8];
            originalSP[2] += intI[8];
            originalSP[3] += defI[8];
            originalSP[4] += agiI[8];
        }

        equipOrder = checked;
        strength.setValue(originalSP[0], assignSP[0], totalSP[0]);
        dexterity.setValue(originalSP[1], assignSP[1], totalSP[1]);
        intelligence.setValue(originalSP[2], assignSP[2], totalSP[2]);
        defense.setValue(originalSP[3], assignSP[3], totalSP[3]);
        agility.setValue(originalSP[4], assignSP[4], totalSP[4]);
        spText.setText("Using Skill Point: " + (assignSP[0] + assignSP[1] + assignSP[2] + assignSP[3] + assignSP[4]));
    }

    public void updateSkillPoint() {
        strength.updateValue();
        dexterity.updateValue();
        intelligence.updateValue();
        defense.updateValue();
        agility.updateValue();
        spText.setText("Using Skill Point: " + (strength.getTotalAssign() + dexterity.getTotalAssign() + intelligence.getTotalAssign() + defense.getTotalAssign() + agility.getTotalAssign()));
    }

    public List<Integer> getEquipOrder() {
        return equipOrder;
    }

    public SkillPointPanel getSkillPoint(SkillPointType spType) {
        switch (spType) {
            case STRENGTH:
                return strength;
            case DEXTERITY:
                return dexterity;
            case INTELLIGENCE:
                return intelligence;
            case DEFENSE:
                return defense;
            case AGILITY:
                return agility;
        }
        return null;
    }

    public SkillPointPanel getSkillPoint(int i) {
        switch (i) {
            case 1:
                return dexterity;
            case 2:
                return intelligence;
            case 3:
                return defense;
            case 4:
                return agility;
            default:
                return strength;
        }
    }

    public int getSkillPointInt(int i) {
        switch (i) {
            case 1:
                return dexterity.getSPValue();
            case 2:
                return intelligence.getSPValue();
            case 3:
                return defense.getSPValue();
            case 4:
                return agility.getSPValue();
            default:
                return strength.getSPValue();
        }
    }

    private static void getTomeSkillPoints(JsonObject json, int[] originalSP, int[] totalSP) {
        if (json.get(Identifications.STRENGTH_REQ.getItemFieldPos()) != null) {
            JsonObject j = json.get(Identifications.STRENGTH_REQ.getItemFieldPos()).getAsJsonObject();

            //Strength
            if (j.get(Identifications.STRENGTH.getItemName()) != null) {
                originalSP[0] += j.get(Identifications.STRENGTH.getItemName()).getAsInt();
                totalSP[0] += j.get(Identifications.STRENGTH.getItemName()).getAsInt();
            }

            //Dexterity
            if (j.get(Identifications.DEXTERITY.getItemName()) != null) {
                originalSP[1] += j.get(Identifications.DEXTERITY.getItemName()).getAsInt();
                totalSP[1] += j.get(Identifications.DEXTERITY.getItemName()).getAsInt();
            }

            //Intelligence
            if (j.get(Identifications.INTELLIGENCE.getItemName()) != null) {
                originalSP[2] += j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt();
                totalSP[2] += j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt();
            }

            //Defense
            if (j.get(Identifications.DEFENSE.getItemName()) != null) {
                originalSP[3] += j.get(Identifications.DEFENSE.getItemName()).getAsInt();
                totalSP[3] += j.get(Identifications.DEFENSE.getItemName()).getAsInt();
            }

            //Agility
            if (j.get(Identifications.AGILITY.getItemName()) != null) {
                originalSP[4] += j.get(Identifications.AGILITY.getItemName()).getAsInt();
                totalSP[4] += j.get(Identifications.AGILITY.getItemName()).getAsInt();
            }
        }
    }

    private static void getSkillPoints(JsonObject json, Identifications id, int[] sps, int i, int[] total, int[] original, boolean addTotal) {
        int sp = 0;
        if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
            JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
            if (!j.isJsonObject()) {
                sp = j.getAsInt();
            } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
                String minOrMax = "max";
                if (j.getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                sp = SearchUI.getBaseID(j.getAsJsonObject().get(minOrMax).getAsInt());
            } else if (id.isItemVariable() || json.get(Identifications.RARITY.getItemName()).getAsString().equals("crafted")) {
                sp = j.getAsJsonObject().get("max").getAsInt();
            }
        }

        if (json.get(Identifications.RARITY.getItemName()) != null && json.get(Identifications.RARITY.getItemName()).getAsString().equals("crafted")) {
            sps[i] += sp;
        } else {
            if (sp < 0) { //SP Bonus Minus
                int pos = 0; //Default: Strength
                switch (id) {
                    case DEXTERITY:
                        pos = 1;
                        break;
                    case INTELLIGENCE:
                        pos = 2;
                        break;
                    case DEFENSE:
                        pos = 3;
                        break;
                    case AGILITY:
                        pos = 4;
                        break;
                }
                if (addTotal) total[pos] += sp;
                original[pos] += sp;
            } else { //SP Bonus Plus
                sps[i] += sp;
            }
        }
    }

    private static List<SetBonus> getHaveSPSetBonus(List<SetBonus> setBonuses, int[] originalSP, int[] totalSP) {
        List<SetBonus> haveSPBSets = new ArrayList<>();

        //Set Bonus
        for (SetBonus setBonus : setBonuses) {
            int[] ids = setBonus.getId_Numbers(true);
            int[] sps = new int[] {ids[ID_Display.ID_INT.get(Identifications.STRENGTH)], ids[ID_Display.ID_INT.get(Identifications.DEXTERITY)], ids[ID_Display.ID_INT.get(Identifications.INTELLIGENCE)], ids[ID_Display.ID_INT.get(Identifications.DEFENSE)], ids[ID_Display.ID_INT.get(Identifications.AGILITY)]};
            //Have SP Bonus
            int[] ids_In_Weapon = setBonus.getId_Numbers(false);
            int[] sps_In_Weapon = new int[] {ids_In_Weapon[ID_Display.ID_INT.get(Identifications.STRENGTH)], ids_In_Weapon[ID_Display.ID_INT.get(Identifications.DEXTERITY)], ids_In_Weapon[ID_Display.ID_INT.get(Identifications.INTELLIGENCE)], ids_In_Weapon[ID_Display.ID_INT.get(Identifications.DEFENSE)], ids_In_Weapon[ID_Display.ID_INT.get(Identifications.AGILITY)]};
            if (sps_In_Weapon[0] > 0 || sps_In_Weapon[1] > 0 || sps_In_Weapon[2] > 0 || sps_In_Weapon[3] > 0 || sps_In_Weapon[4] > 0) haveSPBSets.add(setBonus);

            //Set Minus SP Bonus
            if (sps[0] < 0) { //Strength
                originalSP[0] += sps[0];
                totalSP[0] += sps[0];
            }
            if (sps[1] < 0) { //Dexterity
                originalSP[1] += sps[1];
                totalSP[1] += sps[1];
            }
            if (sps[2] < 0) { //Intelligence
                originalSP[2] += sps[2];
                totalSP[2] += sps[2];
            }
            if (sps[3] < 0) { //Defense
                originalSP[3] += sps[3];
                totalSP[3] += sps[3];
            }
            if (sps[4] < 0) { //Agility
                originalSP[4] += sps[4];
                totalSP[4] += sps[4];
            }
        }

        return haveSPBSets;
    }

    private static void setSPReq(JsonObject json, int pos, int[] strR, int[] dexR, int[] intR, int[] defR, int[] agiR, int[] maxSPReq) {
        if (json.get(Identifications.STRENGTH_REQ.getItemFieldPos()) != null) {
            JsonObject j = json.get(Identifications.STRENGTH_REQ.getItemFieldPos()).getAsJsonObject();

            //Strength Req
            if (j.get(Identifications.STRENGTH_REQ.getItemName()) != null && j.get(Identifications.STRENGTH_REQ.getItemName()).getAsInt() != 0) {
                strR[pos] = j.get(Identifications.STRENGTH_REQ.getItemName()).getAsInt();
                if (strR[pos] > maxSPReq[0]) maxSPReq[0] = strR[pos]; //Strength Req > Max SP Req
            }

            //Dexterity Req
            if (j.get(Identifications.DEXTERITY_REQ.getItemName()) != null && j.get(Identifications.DEXTERITY_REQ.getItemName()).getAsInt() != 0) {
                dexR[pos] = j.get(Identifications.DEXTERITY_REQ.getItemName()).getAsInt();
                if (dexR[pos] > maxSPReq[1]) maxSPReq[1] = dexR[pos];
            }

            //Intelligence Req
            if (j.get(Identifications.INTELLIGENCE_REQ.getItemName()) != null && j.get(Identifications.INTELLIGENCE_REQ.getItemName()).getAsInt() != 0) {
                intR[pos] = j.get(Identifications.INTELLIGENCE_REQ.getItemName()).getAsInt();
                if (intR[pos] > maxSPReq[2]) maxSPReq[2] = intR[pos];
            }

            //Defense Req
            if (j.get(Identifications.DEFENSE_REQ.getItemName()) != null && j.get(Identifications.DEFENSE_REQ.getItemName()).getAsInt() != 0) {
                defR[pos] = j.get(Identifications.DEFENSE_REQ.getItemName()).getAsInt();
                if (defR[pos] > maxSPReq[3]) maxSPReq[3] = defR[pos];
            }

            //Agility Req
            if (j.get(Identifications.AGILITY_REQ.getItemName()) != null && j.get(Identifications.AGILITY_REQ.getItemName()).getAsInt() != 0) {
                agiR[pos] = j.get(Identifications.AGILITY_REQ.getItemName()).getAsInt();
                if (agiR[pos] > maxSPReq[4]) maxSPReq[4] = agiR[pos];
            }
        }
    }

    private static boolean[] checkCrafted(List<JsonObject> json) {
        boolean[] isCrafted = new boolean[] {false, false, false, false, false, false, false, false};

        for (int i = 0; 8 > i; ++i) {
            JsonObject j = json.get(i);
            if (j != null && j.get(Identifications.RARITY.getItemName()) != null && j.get(Identifications.RARITY.getItemName()).getAsString().equals("crafted")) {
                isCrafted[i] = true;
            }
        }

        return isCrafted;
    }

    private static void checkSet(JsonObject j, List<SetBonus> setBonuses, int[] originalSP, int[] totalSP) {
        if (setBonuses.size() > 0) {
            for (SetBonus set : setBonuses) {
                if (set.getEquippedItems().contains(j.get("name").getAsString())) {
                    set.addEquipped();

                    int[] ids_before = set.getId_Numbers(set.getEquippedSize() - 1);
                    int[] sps_before = new int[] {ids_before[ID_Display.ID_INT.get(Identifications.STRENGTH)], ids_before[ID_Display.ID_INT.get(Identifications.DEXTERITY)], ids_before[ID_Display.ID_INT.get(Identifications.INTELLIGENCE)], ids_before[ID_Display.ID_INT.get(Identifications.DEFENSE)], ids_before[ID_Display.ID_INT.get(Identifications.AGILITY)]};

                    int[] ids_after = set.getId_Numbers(set.getEquippedSize());
                    int[] sps_after = new int[] {ids_after[ID_Display.ID_INT.get(Identifications.STRENGTH)], ids_after[ID_Display.ID_INT.get(Identifications.DEXTERITY)], ids_after[ID_Display.ID_INT.get(Identifications.INTELLIGENCE)], ids_after[ID_Display.ID_INT.get(Identifications.DEFENSE)], ids_after[ID_Display.ID_INT.get(Identifications.AGILITY)]};

                    //Strength
                    if (sps_after[0] > 0) {
                        int sp = sps_after[0] - sps_before[0];
                        originalSP[0] += sp;
                        totalSP[0] += sp;
                    }

                    //Dexterity
                    if (sps_after[1] > 0) {
                        int sp = sps_after[1] - sps_before[1];
                        originalSP[1] += sp;
                        totalSP[1] += sp;
                    }

                    //Intelligence
                    if (sps_after[2] > 0) {
                        int sp = sps_after[2] - sps_before[2];
                        originalSP[2] += sp;
                        totalSP[2] += sp;
                    }

                    //Defense
                    if (sps_after[3] > 0) {
                        int sp = sps_after[3] - sps_before[3];
                        originalSP[3] += sp;
                        totalSP[3] += sp;
                    }

                    //Agility
                    if (sps_after[4] > 0) {
                        int sp = sps_after[4] - sps_before[4];
                        originalSP[4] += sp;
                        totalSP[4] += sp;
                    }
                }
            }
        }
    }

    private static void addSPB(int i, int[] strI, int[] dexI, int[] intI, int[] defI, int[] agiI, int[] originalSP, int[] totalSP) {
        //Strength
        originalSP[0] += strI[i];
        totalSP[0] += strI[i];

        //Dexterity
        originalSP[1] += dexI[i];
        totalSP[1] += dexI[i];

        //Intelligence
        originalSP[2] += intI[i];
        totalSP[2] += intI[i];

        //Defense
        originalSP[3] += defI[i];
        totalSP[3] += defI[i];

        //Agility
        originalSP[4] += agiI[i];
        totalSP[4] += agiI[i];
    }

    private static boolean searchEquip(List<JsonObject> items, List<SetBonus> setBonuses, int[] strR, int[] dexR, int[] intR, int[] defR, int[] agiR, int[] strI, int[] dexI, int[] intI, int[] defI, int[] agiI, int[] originalSP, int[] totalSP, List<Integer> checked, List<Integer> crafted, Set<Integer> isNull, int size) {
        if (checked.size() == size) return false;

        for (int i = 0; 8 > i; ++i) {
            if (checked.contains(i) || crafted.contains(i) || isNull.contains(i)) continue;

            if ((totalSP[0] > strR[i] || strR[i] == 0) && (totalSP[1] > dexR[i] || dexR[i] == 0) && (totalSP[2] > intR[i] || intR[i] == 0) && (totalSP[3] > defR[i] || defR[i] == 0) && (totalSP[4] > agiR[i] || agiR[i] == 0)) {
                addSPB(i, strI, dexI, intI, defI, agiI, originalSP, totalSP);
                checked.add(i);
                checkSet(items.get(i), setBonuses, originalSP, totalSP);

                return true;
            }
        }
        return false;
    }

    private static boolean searchMinReq(List<JsonObject> items, List<SetBonus> setBonuses, int[] maxR, int[] strR, int[] dexR, int[] intR, int[] defR, int[] agiR, int[] strI, int[] dexI, int[] intI, int[] defI, int[] agiI, int[] originalSP, int[] totalSP, int[] assignSP, List<Integer> checked, List<Integer> crafted, Set<Integer> isNull, int size) {
        if (checked.size() == size) return false;

        int[] minRequests = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int minItemID = -1;

        for (int i = 0; 8 > i; ++i) {
            if (checked.contains(i) || crafted.contains(i) || isNull.contains(i)) continue; //(strI[i] == 0 && dexI[i] == 0 && intI[i] == 0 && defI[i] == 0 && agiI[i] == 0)
            int[] maxRequests = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

            sortSPReq(i, strR, maxRequests);
            sortSPReq(i, dexR, maxRequests);
            sortSPReq(i, intR, maxRequests);
            sortSPReq(i, defR, maxRequests);
            sortSPReq(i, agiR, maxRequests);

            if (minRequests[0] > maxRequests[0] && minRequests[1] > maxRequests[1] && minRequests[2] > maxRequests[2] && minRequests[3] > maxRequests[3] && minRequests[4] > maxRequests[4]) {
                minRequests = maxRequests;
                minItemID = i;
            } else if (minRequests[4] > maxRequests[4] && compareESPB(minItemID, i, maxR, strR, dexR, intR, defR, agiR, strI, dexI, intI, defI, agiI, totalSP)) {
                minRequests = maxRequests;
                minItemID = i;
            }
        }

        if (minItemID != -1) {
            applySPReq(minItemID, strR, dexR, intR, defR, agiR, originalSP, totalSP, assignSP);

            addSPB(minItemID, strI, dexI, intI, defI, agiI, originalSP, totalSP);
            checked.add(minItemID);
            checkSet(items.get(minItemID), setBonuses, originalSP, totalSP);

            boolean canEquip = true;
            while (canEquip) {
                canEquip = searchEquip(items, setBonuses, strR, dexR, intR, defR, agiR, strI, dexI, intI, defI, agiI, originalSP, totalSP, checked, crafted, isNull, size);
            }

            return true;
        }

        return false;
    }

    private static void applySPReq(int i, int[] strR, int[] dexR, int[] intR, int[] defR, int[] agiR, int[] originalSP, int[] totalSP, int[] assignSP) {
        //Strength Req
        if (strR[i] != 0 && strR[i] > totalSP[0]) {
            assignSP[0] += strR[i] - totalSP[0];
            originalSP[0] = strR[i];
            totalSP[0] = strR[i];
        }

        //Dexterity Req
        if (dexR[i] != 0 && dexR[i] > totalSP[1]) {
            assignSP[1] += dexR[i] - totalSP[1];
            originalSP[1] = dexR[i];
            totalSP[1] = dexR[i];
        }

        //Intelligence Req
        if (intR[i] != 0 && intR[i] > totalSP[2]) {
            assignSP[2] += intR[i] - totalSP[2];
            originalSP[2] = intR[i];
            totalSP[2] = intR[i];
        }

        //Defense Req
        if (defR[i] != 0 && defR[i] > totalSP[3]) {
            assignSP[3] += defR[i] - totalSP[3];
            originalSP[3] = defR[i];
            totalSP[3] = defR[i];
        }

        //Agility Req
        if (agiR[i] != 0 && agiR[i] > totalSP[4]) {
            assignSP[4] += agiR[i] - totalSP[4];
            originalSP[4] = agiR[i];
            totalSP[4] = agiR[i];
        }
    }

    private static void sortSPReq(int i, int[] spR, int[] maxRequests) {
        if (spR[i] > 0) {
            if (spR[i] > maxRequests[0]) {
                maxRequests[4] = maxRequests[3];
                maxRequests[3] = maxRequests[2];
                maxRequests[2] = maxRequests[1];
                maxRequests[1] = maxRequests[0];
                maxRequests[0] = spR[i];
                if (maxRequests[1] == Integer.MIN_VALUE) maxRequests[1] = spR[i];
                if (maxRequests[2] == Integer.MIN_VALUE) maxRequests[2] = spR[i];
                if (maxRequests[3] == Integer.MIN_VALUE) maxRequests[3] = spR[i];
                if (maxRequests[4] == Integer.MIN_VALUE) maxRequests[4] = spR[i];
            } else if (spR[i] > maxRequests[1]) {
                maxRequests[4] = maxRequests[3];
                maxRequests[3] = maxRequests[2];
                maxRequests[2] = maxRequests[1];
                maxRequests[1] = spR[i];

                if (maxRequests[2] > maxRequests[1]) {
                    maxRequests[2] = maxRequests[1];
                    maxRequests[3] = maxRequests[1];
                    maxRequests[4] = maxRequests[1];
                }
            } else if (spR[i] > maxRequests[2]) {
                maxRequests[4] = maxRequests[3];
                maxRequests[3] = maxRequests[2];
                maxRequests[2] = spR[i];

                if (maxRequests[3] > maxRequests[2]) {
                    maxRequests[3] = maxRequests[2];
                    maxRequests[4] = maxRequests[2];
                }
            } else if (spR[i] > maxRequests[3]) {
                maxRequests[4] = maxRequests[3];
                maxRequests[3] = spR[i];

                if (maxRequests[4] > maxRequests[3]) {
                    maxRequests[4] = maxRequests[3];
                }
            } else if (spR[i] > maxRequests[4]) {
                maxRequests[4] = spR[i];
            }
        }
    }

    private static boolean compareESPB(int before, int after, int[] maxR, int[] strR, int[] dexR, int[] intR, int[] defR, int[] agiR, int[] strI, int[] dexI, int[] intI, int[] defI, int[] agiI, int[] totalSP) {
        return calcESPB(before, after, maxR, strR, dexR, intR, defR, agiR, strI, dexI, intI, defI, agiI, totalSP) > calcESPB(after, before, maxR, strR, dexR, intR, defR, agiR, strI, dexI, intI, defI, agiI, totalSP);
    }

    private static int calcESPB(int before, int after, int[] maxR, int[] strR, int[] dexR, int[] intR, int[] defR, int[] agiR, int[] strI, int[] dexI, int[] intI, int[] defI, int[] agiI, int[] totalSP) {
        int[] sps = new int[] {strR[after], dexR[after], intR[after], defR[after], agiR[after]};
        //Equip After
        if (totalSP[0] > strR[after]) sps[0] = totalSP[0];
        if (totalSP[1] > dexR[after]) sps[1] = totalSP[1];
        if (totalSP[2] > intR[after]) sps[2] = totalSP[2];
        if (totalSP[3] > defR[after]) sps[3] = totalSP[3];
        if (totalSP[4] > agiR[after]) sps[4] = totalSP[4];

        int[] compareTotal = new int[] {sps[0] + strI[after], sps[1] + dexI[after], sps[2] + intI[after], sps[3] + defI[after], sps[4] + agiI[after]};

        int[] rSPR = new int[] {maxR[0] - sps[0], maxR[1] - sps[1], maxR[2] - sps[2], maxR[3] - sps[3], maxR[4] - sps[4]};
        int eSPB = 0;
        if (rSPR[0] > 0 && strI[after] > 0) eSPB += Math.min(rSPR[0], strI[after]);
        if (rSPR[1] > 0 && dexI[after] > 0) eSPB += Math.min(rSPR[1], dexI[after]);
        if (rSPR[2] > 0 && intI[after] > 0) eSPB += Math.min(rSPR[2], intI[after]);
        if (rSPR[3] > 0 && defI[after] > 0) eSPB += Math.min(rSPR[3], defI[after]);
        if (rSPR[4] > 0 && agiI[after] > 0) eSPB += Math.min(rSPR[4], agiI[after]);

        //Equip Before
        if (strR[before] > compareTotal[0]) compareTotal[0] = strR[before];
        if (dexR[before] > compareTotal[1]) compareTotal[1] = dexR[before];
        if (intR[before] > compareTotal[2]) compareTotal[2] = intR[before];
        if (defR[before] > compareTotal[3]) compareTotal[3] = defR[before];
        if (agiR[before] > compareTotal[4]) compareTotal[4] = agiR[before];

        int[] rSPR_B = new int[] {compareTotal[0] - sps[0], compareTotal[1] - sps[1], compareTotal[2] - sps[2], compareTotal[3] - sps[3], compareTotal[4] - sps[4]};
        if (rSPR_B[0] > 0 && strI[before] > 0) eSPB += Math.min(rSPR_B[0], strI[before]);
        if (rSPR_B[1] > 0 && dexI[before] > 0) eSPB += Math.min(rSPR_B[1], dexI[before]);
        if (rSPR_B[2] > 0 && intI[before] > 0) eSPB += Math.min(rSPR_B[2], intI[before]);
        if (rSPR_B[3] > 0 && defI[before] > 0) eSPB += Math.min(rSPR_B[3], defI[before]);
        if (rSPR_B[4] > 0 && agiI[before] > 0) eSPB += Math.min(rSPR_B[4], agiI[before]);

        return eSPB;
    }

    public enum SkillPointType {
        STRENGTH,
        DEXTERITY,
        INTELLIGENCE,
        DEFENSE,
        AGILITY
    }

    public static class SkillPointPanel {
        private final JTextField textField = new JTextField("0");
        private final JLabel name_label = new JLabel();
        private final JLabel boost_label = new JLabel();
        private final JLabel total_label = new JLabel("(0)");
        private final JLabel original_label = new JLabel("Original: 0");
        private final JLabel assign_label = new JLabel("Assign: 0");
        private int assignBase = 0;
        private int totalBase = 0;
        private int original = 0;

        private static final float[] STR_AND_DEX = new float[] {
                1F, 1.01F, 1.02F, 1.029F, 1.039F, 1.049F, 1.058F, 1.067F, 1.077F, 1.086F, //0 ~ 9
                1.095F, 1.104F, 1.113F, 1.122F, 1.131F, 1.139F, 1.148F, 1.157F, 1.165F, 1.173F, //10 ~ 19
                1.182F, 1.19F, 1.198F, 1.206F, 1.214F, 1.222F, 1.23F, 1.238F, 1.246F, 1.253F, //20 ~ 29
                1.261F, 1.268F, 1.276F, 1.283F, 1.29F, 1.298F, 1.305F, 1.312F, 1.319F, 1.326F, //30 ~ 39
                1.333F, 1.34F, 1.346F, 1.353F, 1.36F, 1.366F, 1.373F, 1.379F, 1.386F, 1.392F, //40 ~ 49
                1.399F, 1.405F, 1.411F, 1.417F, 1.423F, 1.429F, 1.435F, 1.441F, 1.447F, 1.453F, //50 ~ 59
                1.458F, 1.464F, 1.47F, 1.475F, 1.481F, 1.486F, 1.492F, 1.497F, 1.503F, 1.508F, //60 ~ 69
                1.513F, 1.518F, 1.523F, 1.528F, 1.534F, 1.539F, 1.543F, 1.548F, 1.553F, 1.558F, //70 ~ 79
                1.563F, 1.568F, 1.572F, 1.577F, 1.581F, 1.586F, 1.591F, 1.595F, 1.599F, 1.604F, //80 ~ 89
                1.608F, 1.613F, 1.617F, 1.621F, 1.625F, 1.629F, 1.633F, 1.638F, 1.642F, 1.646F, //90 ~ 99
                1.65F, 1.654F, 1.657F, 1.661F, 1.665F, 1.669F, 1.673F, 1.676F, 1.68F, 1.684F, //100 ~ 109
                1.687F, 1.691F, 1.694F, 1.698F, 1.701F, 1.705F, 1.708F, 1.712F, 1.715F, 1.718F, //110 ~ 119
                1.722F, 1.725F, 1.728F, 1.731F, 1.735F, 1.738F, 1.741F, 1.744F, 1.747F, 1.75F, //120 ~ 129
                1.753F, 1.756F, 1.759F, 1.762F, 1.765F, 1.768F, 1.771F, 1.773F, 1.776F, 1.779F, //130 ~ 139
                1.782F, 1.784F, 1.787F, 1.79F, 1.792F, 1.795F, 1.798F, 1.8F, 1.803F, 1.805F, 1.808F //140 ~ 150
        };
        private static final float[] INTELLIGENCE = new float[] {
                0F, 0.0062F, 0.0124F, 0.018F, 0.0241F, 0.0303F, 0.0359F, 0.0415F, 0.0477F, 0.0532F, //0 ~ 9
                0.0588F, 0.0644F, 0.0699F, 0.0755F, 0.0811F, 0.086F, 0.0916F, 0.0972F, 0.1021F, 0.1071F, //10 ~ 19
                0.1127F, 0.1176F, 0.1226F, 0.1275F, 0.1325F, 0.1374F, 0.1424F, 0.1473F, 0.1523F, 0.1566F, //20 ~ 29
                0.1616F, 0.1659F, 0.1708F, 0.1752F, 0.1795F, 0.1845F, 0.1888F, 0.1931F, 0.1975F, 0.2018F, //30 ~ 39
                0.2061F, 0.2105F, 0.2142F, 0.2185F, 0.2228F, 0.2266F, 0.2309F, 0.2346F, 0.2389F, 0.2426F, //40 ~ 49
                0.247F, 0.2507F, 0.2544F, 0.2581F, 0.2618F, 0.2656F, 0.2693F, 0.273F, 0.2767F, 0.2804F, //50 ~ 59
                0.2835F, 0.2872F, 0.2909F, 0.294F, 0.2977F, 0.3008F, 0.3045F, 0.3076F, 0.3114F, 0.3145F, //60 ~ 69
                0.3175F, 0.3206F, 0.3237F, 0.3268F, 0.3305F, 0.3336F, 0.3361F, 0.3392F, 0.3423F, 0.3454F, //70 ~ 79
                0.3485F, 0.3516F, 0.3541F, 0.3572F, 0.3596F, 0.3627F, 0.3658F, 0.3683F, 0.3708F, 0.3739F, //80 ~ 89
                0.3764F, 0.3794F, 0.3819F, 0.3844F, 0.3869F, 0.3894F, 0.3918F, 0.3949F, 0.3974F, 0.3999F, //90 ~ 99
                0.4023F, 0.4048F, 0.4067F, 0.4092F, 0.4116F, 0.4141F, 0.4166F, 0.4184F, 0.4209F, 0.4234F, //100 ~ 109
                0.4253F, 0.4277F, 0.4296F, 0.4321F, 0.4339F, 0.4364F, 0.4383F, 0.4407F, 0.4426F, 0.4444F, //110 ~ 119
                0.4469F, 0.4488F, 0.4506F, 0.4525F, 0.455F, 0.4568F, 0.4587F, 0.4605F, 0.4624F, 0.4642F, //120 ~ 129
                0.4661F, 0.468F, 0.4698F, 0.4717F, 0.4735F, 0.4754F, 0.4772F, 0.4785F, 0.4803F, 0.4822F, //130 ~ 139
                0.4841F, 0.4853F, 0.4872F, 0.489F, 0.4902F, 0.4921F, 0.494F, 0.4952F, 0.4971F, 0.4983F, 0.5F //140 ~ 150
        };
        private static final float[] DEFENSE = new float[] {
                1F, 1.009F, 1.017F, 1.025F, 1.034F, 1.042F, 1.05F, 1.058F, 1.067F, 1.074F, //0 ~ 9
                1.082F, 1.09F, 1.098F, 1.106F, 1.113F, 1.12F, 1.128F, 1.136F, 1.143F, 1.15F, //10 ~ 19
                1.158F, 1.165F, 1.171F, 1.178F, 1.185F, 1.192F, 1.199F, 1.206F, 1.213F, 1.219F, //20 ~ 29
                1.226F, 1.232F, 1.239F, 1.245F, 1.251F, 1.258F, 1.264F, 1.27F, 1.276F, 1.282F, //30 ~ 39
                1.288F, 1.294F, 1.30F, 1.306F, 1.312F, 1.317F, 1.323F, 1.328F, 1.334F, 1.339F, //40 ~ 49
                1.346F, 1.351F, 1.356F, 1.361F, 1.366F, 1.372F, 1.377F, 1.382F, 1.387F, 1.392F, //50 ~ 59
                1.397F, 1.402F, 1.407F, 1.411F, 1.417F, 1.421F, 1.426F, 1.43F, 1.436F, 1.44F, //60 ~ 69
                1.444F, 1.449F, 1.453F, 1.457F, 1.462F, 1.467F, 1.47F, 1.475F, 1.479F, 1.483F, //70 ~ 79
                1.488F, 1.492F, 1.495F, 1.5F, 1.503F, 1.507F, 1.512F, 1.515F, 1.519F, 1.523F, //80 ~ 89
                1.527F, 1.531F, 1.534F, 1.538F, 1.541F, 1.545F, 1.548F, 1.553F, 1.556F, 1.559F, //90 ~ 99
                1.563F, 1.566F, 1.569F, 1.572F, 1.576F, 1.579F, 1.583F, 1.585F, 1.589F, 1.592F, //100 ~ 109
                1.595F, 1.598F, 1.601F, 1.604F, 1.607F, 1.611F, 1.613F, 1.617F, 1.619F, 1.622F, //110 ~ 119
                1.625F, 1.628F, 1.63F, 1.633F, 1.637F, 1.639F, 1.642F, 1.644F, 1.647F, 1.65F, //120 ~ 129
                1.652F, 1.655F, 1.657F, 1.66F, 1.662F, 1.665F, 1.668F, 1.669F, 1.672F, 1.675F, //130 ~ 139
                1.677F, 1.679F, 1.682F, 1.684F, 1.686F, 1.688F, 1.691F, 1.693F, 1.695F, 1.697F, 1.7F //140 ~ 150
        };
        private static final float[] AGILITY = new float[] {
                1F, 1.01F, 1.019F, 1.028F, 1.037F, 1.047F, 1.055F, 1.064F, 1.073F, 1.082F, //0 ~ 9
                1.09F, 1.099F, 1.107F, 1.116F, 1.125F, 1.132F, 1.141F, 1.149F, 1.157F, 1.164F, //10 ~ 19
                1.173F, 1.181F, 1.188F, 1.196F, 1.203F, 1.211F, 1.219F, 1.226F, 1.234F, 1.24F, //20 ~ 29
                1.248F, 1.255F, 1.262F, 1.269F, 1.276F, 1.283F, 1.29F, 1.297F, 1.303F, 1.31F, //30 ~ 39
                1.317F, 1.323F, 1.329F, 1.336F, 1.342F, 1.348F, 1.355F, 1.36F, 1.367F, 1.373F, //40 ~ 49
                1.379F, 1.385F, 1.391F, 1.396F, 1.402F, 1.408F, 1.413F, 1.419F, 1.425F, 1.431F, //50 ~ 59
                1.435F, 1.441F, 1.447F, 1.451F, 1.457F, 1.462F, 1.468F, 1.472F, 1.478F, 1.483F, //60 ~ 69
                1.488F, 1.492F, 1.497F, 1.502F, 1.508F, 1.512F, 1.516F, 1.521F, 1.526F, 1.53F, //70 ~ 79
                1.535F, 1.54F, 1.544F, 1.548F, 1.552F, 1.557F, 1.562F, 1.566F, 1.569F, 1.574F, //80 ~ 89
                1.578F, 1.583F, 1.586F, 1.59F, 1.594F, 1.598F, 1.602F, 1.606F, 1.61F, 1.614F, //90 ~ 99
                1.618F, 1.622F, 1.624F, 1.628F, 1.632F, 1.636F, 1.64F, 1.643F, 1.646F, 1.65F, //100 ~ 109
                1.653F, 1.657F, 1.66F, 1.663F, 1.666F, 1.67F, 1.673F, 1.677F, 1.68F, 1.682F, //110 ~ 119
                1.686F, 1.689F, 1.692F, 1.695F, 1.669F, 1.701F, 1.704F, 1.707F, 1.71F, 1.713F, //120 ~ 129
                1.716F, 1.719F, 1.721F, 1.724F, 1.727F, 1.73F, 1.733F, 1.735F, 1.738F, 1.74F, //130 ~ 139
                1.743F, 1.745F, 1.748F, 1.751F, 1.753F, 1.756F, 1.758F, 1.76F, 1.763F, 1.765F, 1.768F //140 ~ 150
        };

        public SkillPointPanel(String name, int x, int y, JPanel p) {
            JPanel pane = new JPanel();
            pane.setBounds(x, y, 120, 100);
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
            name_label.setText(name);
            setValue(0, 0, 0);

            name_label.setAlignmentX(Component.CENTER_ALIGNMENT);
            textField.setAlignmentX(Component.CENTER_ALIGNMENT);
            total_label.setAlignmentX(Component.CENTER_ALIGNMENT);
            boost_label.setAlignmentX(Component.CENTER_ALIGNMENT);
            original_label.setAlignmentX(Component.CENTER_ALIGNMENT);
            assign_label.setAlignmentX(Component.CENTER_ALIGNMENT);

            pane.add(name_label);
            pane.add(textField);
            pane.add(total_label);
            pane.add(boost_label);
            pane.add(original_label);
            pane.add(assign_label);

            p.add(pane);
        }

        public int getSPValue() {
            int value = 0;
            if (textField.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
                value = Integer.parseInt(textField.getText());
            }
            return value;
        }

        public float getSPBoost() {
            float value = 1F;
            int sp = getSPValue();
            switch (name_label.getText()) {
                case "Strength":
                case "Dexterity":
                    if (sp > 0 && sp < 150) {
                        value = STR_AND_DEX[sp];
                    } else if (sp >= 150) {
                        value = STR_AND_DEX[150];
                    }
                    break;
                case "Intelligence":
                    if (sp < 1) {
                        value = INTELLIGENCE[0];
                    } else if (sp < 150) {
                        value = INTELLIGENCE[sp];
                    } else {
                        value = INTELLIGENCE[150];
                    }
                    break;
                case "Defense":
                    if (sp > 0 && sp < 150) {
                        value = DEFENSE[sp];
                    } else if (sp >= 150) {
                        value = DEFENSE[150];
                    }
                    break;
                case "Agility":
                    if (sp > 0 && sp < 150) {
                        value = AGILITY[sp];
                    } else if (sp >= 150) {
                        value = AGILITY[150];
                    }
                    break;
            }
            return value;
        }

        public float getIntDamageBonus() {
            if (name_label.getText().equals("Intelligence")) {
                int sp = getSPValue();
                if (sp > 0 && sp < 150) {
                    return STR_AND_DEX[sp];
                } else {
                    return STR_AND_DEX[150];
                }
            }
            return 1F;
        }

        public void setValue(int originalSP, int assignSP, int totalSP) {
            total_label.setText("(" + totalSP + ")");
            original_label.setText("Original: " + originalSP);
            assign_label.setText("Assign: " + assignSP);
            textField.setText(String.valueOf(originalSP));
            assignBase = assignSP;
            totalBase = totalSP;
            original = originalSP;
            updateValue();
        }

        public void updateValue() {
            switch (name_label.getText()) {
                case "Strength":
                    boost_label.setText("Damage: +" + (Math.round((getSPBoost() - 1) * 1000F) / 10F) + "%");
                    break;
                case "Dexterity":
                    boost_label.setText("Crit: " + (Math.round((getSPBoost() - 1) * 1000F) / 10F) + "%");
                    break;
                case "Intelligence":
                    boost_label.setText("SP Cost: -" + (Math.round(getSPBoost() * 10000F) / 100F) + "%");
                    break;
                case "Defense":
                    boost_label.setText("Resist: +" + (Math.round((getSPBoost() - 1) * 10000F) / 100F) + "%");
                    break;
                case "Agility":
                    boost_label.setText("Dodge: " + (Math.round((getSPBoost() - 1) * 10000F) / 100F) + "%");
                    break;
            }
            total_label.setText("(" + (getSPValue() - original + totalBase) + ")");
            assign_label.setText("Assign: " + getTotalAssign());
        }

        public int getTotalAssign() {
            return getSPValue() - original + assignBase;
        }

        public void setTextValue(int value) {
            textField.setText(String.valueOf(value));
        }
    }
}
