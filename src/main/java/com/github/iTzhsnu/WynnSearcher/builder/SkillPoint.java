package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SkillPoint {
    private final SkillPointPanel strength;
    private final SkillPointPanel dexterity;
    private final SkillPointPanel intelligence;
    private final SkillPointPanel defense;
    private final SkillPointPanel agility;
    private final JLabel spText = new JLabel("Using Skill Point: 0");

    public SkillPoint(JPanel p) {
        this.strength = new SkillPointPanel("Strength", 182, 175, p);
        this.dexterity = new SkillPointPanel("Dexterity", 312, 175, p);
        this.intelligence = new SkillPointPanel("Intelligence", 442, 175, p);
        this.defense = new SkillPointPanel("Defense", 572, 175, p);
        this.agility = new SkillPointPanel("Agility", 702, 175, p);
        spText.setBounds(10, 210, 200, 20);
        p.add(spText);
    }

    public void setSkillPoint(ItemJsons items) {
        int[] strI = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //Strength Skill Point Bonus
        int[] dexI = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //Dexterity Skill Point Bonus
        int[] intI = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //Intelligence Skill Point Bonus
        int[] defI = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //Defense Skill Point Bonus
        int[] agiI = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //Agility Skill Point Bonus
        int[] strR = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Strength Req
        int[] dexR = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Dexterity Req
        int[] intR = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Intelligence Req
        int[] defR = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Defense Req
        int[] agiR = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; //Agility Req
        int[] maxSPReq = new int[] {0, 0, 0, 0, 0}; //Max Skill Point Req
        int[] originalSP = new int[] {0, 0, 0, 0, 0}; //Original Skill Point
        int[] totalSP = new int[] {0, 0, 0, 0, 0}; //Total (Non Crafted and Weapon) Skill Point
        int[] manualSP = new int[] {0, 0, 0, 0, 0}; //Manual (Assign) Skill Point

        //Set Weapon Tomes and Guild Tome Skill Point
        if (items.getWeaponTomes().size() > 0) {
            for (JsonObject j : items.getWeaponTomes()) {

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
                    originalSP[0] += j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt();
                    totalSP[0] += j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt();
                }

                //Defense
                if (j.get(Identifications.DEFENSE.getItemName()) != null) {
                    originalSP[0] += j.get(Identifications.DEFENSE.getItemName()).getAsInt();
                    totalSP[0] += j.get(Identifications.DEFENSE.getItemName()).getAsInt();
                }

                //Agility
                if (j.get(Identifications.AGILITY.getItemName()) != null) {
                    originalSP[0] += j.get(Identifications.AGILITY.getItemName()).getAsInt();
                    totalSP[0] += j.get(Identifications.AGILITY.getItemName()).getAsInt();
                }
            }
        }
        if (items.getGuildTome() != null) {
            JsonObject j = items.getGuildTome();

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
                originalSP[0] += j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt();
                totalSP[0] += j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt();
            }

            //Defense
            if (j.get(Identifications.DEFENSE.getItemName()) != null) {
                originalSP[0] += j.get(Identifications.DEFENSE.getItemName()).getAsInt();
                totalSP[0] += j.get(Identifications.DEFENSE.getItemName()).getAsInt();
            }

            //Agility
            if (j.get(Identifications.AGILITY.getItemName()) != null) {
                originalSP[0] += j.get(Identifications.AGILITY.getItemName()).getAsInt();
                totalSP[0] += j.get(Identifications.AGILITY.getItemName()).getAsInt();
            }
        }

        //Get Armor and Accessory Skill Point
        if (items.getJsonObjectList().size() > 0) {
            for (int i = 0; items.getJsonObjectList().size() > i; ++i) {
                JsonObject j = items.getJsonObjectList().get(i);

                //Skill Point Req
                if (j.get(Identifications.STRENGTH_REQ.getItemName()) != null && j.get(Identifications.STRENGTH_REQ.getItemName()).getAsInt() != 0) {
                    strR[i] = j.get(Identifications.STRENGTH_REQ.getItemName()).getAsInt();
                    if (strR[i] > maxSPReq[0]) maxSPReq[0] = strR[i];
                }
                if (j.get(Identifications.DEXTERITY_REQ.getItemName()) != null && j.get(Identifications.DEXTERITY_REQ.getItemName()).getAsInt() != 0) {
                    dexR[i] = j.get(Identifications.DEXTERITY_REQ.getItemName()).getAsInt();
                    if (dexR[i] > maxSPReq[1]) maxSPReq[1] = dexR[i];
                }
                if (j.get(Identifications.INTELLIGENCE_REQ.getItemName()) != null && j.get(Identifications.INTELLIGENCE_REQ.getItemName()).getAsInt() != 0) {
                    intR[i] = j.get(Identifications.INTELLIGENCE_REQ.getItemName()).getAsInt();
                    if (intR[i] > maxSPReq[2]) maxSPReq[2] = intR[i];
                }
                if (j.get(Identifications.DEFENSE_REQ.getItemName()) != null && j.get(Identifications.DEFENSE_REQ.getItemName()).getAsInt() != 0) {
                    defR[i] = j.get(Identifications.DEFENSE_REQ.getItemName()).getAsInt();
                    if (defR[i] > maxSPReq[3]) maxSPReq[3] = defR[i];
                }
                if (j.get(Identifications.AGILITY_REQ.getItemName()) != null && j.get(Identifications.AGILITY_REQ.getItemName()).getAsInt() != 0) {
                    agiR[i] = j.get(Identifications.AGILITY_REQ.getItemName()).getAsInt();
                    if (agiR[i] > maxSPReq[4]) maxSPReq[4] = agiR[i];
                }

                //Skill Point Bonus
                if (j.get(Identifications.STRENGTH.getItemName()) != null && j.get(Identifications.STRENGTH.getItemName()).getAsInt() != 0) {
                    if (j.get("tier") != null && j.get("tier").getAsString().equals("Crafted")) {
                        strI[i + 8] +=j.get(Identifications.STRENGTH.getItemName()).getAsInt();
                    } else {
                        strI[i] +=j.get(Identifications.STRENGTH.getItemName()).getAsInt();
                    }
                }
                if (j.get(Identifications.DEXTERITY.getItemName()) != null && j.get(Identifications.DEXTERITY.getItemName()).getAsInt() != 0) {
                    if (j.get("tier") != null && j.get("tier").getAsString().equals("Crafted")) {
                        dexI[i + 8] +=j.get(Identifications.DEXTERITY.getItemName()).getAsInt();
                    } else {
                        dexI[i] +=j.get(Identifications.DEXTERITY.getItemName()).getAsInt();
                    }
                }
                if (j.get(Identifications.INTELLIGENCE.getItemName()) != null && j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt() != 0) {
                    if (j.get("tier") != null && j.get("tier").getAsString().equals("Crafted")) {
                        intI[i + 8] +=j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt();
                    } else {
                        intI[i] +=j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt();
                    }
                }
                if (j.get(Identifications.DEFENSE.getItemName()) != null && j.get(Identifications.DEFENSE.getItemName()).getAsInt() != 0) {
                    if (j.get("tier") != null && j.get("tier").getAsString().equals("Crafted")) {
                        defI[i + 8] +=j.get(Identifications.DEFENSE.getItemName()).getAsInt();
                    } else {
                        defI[i] +=j.get(Identifications.DEFENSE.getItemName()).getAsInt();
                    }
                }
                if (j.get(Identifications.AGILITY.getItemName()) != null && j.get(Identifications.AGILITY.getItemName()).getAsInt() != 0) {
                    if (j.get("tier") != null && j.get("tier").getAsString().equals("Crafted")) {
                        agiI[i + 8] +=j.get(Identifications.AGILITY.getItemName()).getAsInt();
                    } else {
                        agiI[i] +=j.get(Identifications.AGILITY.getItemName()).getAsInt();
                    }
                }
            }
        }

        //Get Weapon Skill Point
        if (items.getWeapon() != null) {
            JsonObject j = items.getWeapon();

            //Skill Point Req
            if (j.get(Identifications.STRENGTH_REQ.getItemName()) != null && j.get(Identifications.STRENGTH_REQ.getItemName()).getAsInt() != 0) strR[8] = j.get(Identifications.STRENGTH_REQ.getItemName()).getAsInt();
            if (j.get(Identifications.DEXTERITY_REQ.getItemName()) != null && j.get(Identifications.DEXTERITY_REQ.getItemName()).getAsInt() != 0) dexR[8] = j.get(Identifications.DEXTERITY_REQ.getItemName()).getAsInt();
            if (j.get(Identifications.INTELLIGENCE_REQ.getItemName()) != null && j.get(Identifications.INTELLIGENCE_REQ.getItemName()).getAsInt() != 0) intR[8] = j.get(Identifications.INTELLIGENCE_REQ.getItemName()).getAsInt();
            if (j.get(Identifications.DEFENSE_REQ.getItemName()) != null && j.get(Identifications.DEFENSE_REQ.getItemName()).getAsInt() != 0) defR[8] = j.get(Identifications.DEFENSE_REQ.getItemName()).getAsInt();
            if (j.get(Identifications.AGILITY_REQ.getItemName()) != null && j.get(Identifications.AGILITY_REQ.getItemName()).getAsInt() != 0) agiR[8] = j.get(Identifications.AGILITY_REQ.getItemName()).getAsInt();

            //Skill Point Bonus
            if (j.get(Identifications.STRENGTH.getItemName()) != null && j.get(Identifications.STRENGTH.getItemName()).getAsInt() != 0) strI[16] += j.get(Identifications.STRENGTH.getItemName()).getAsInt();
            if (j.get(Identifications.DEXTERITY.getItemName()) != null && j.get(Identifications.DEXTERITY.getItemName()).getAsInt() != 0) dexI[16] += j.get(Identifications.DEXTERITY.getItemName()).getAsInt();
            if (j.get(Identifications.INTELLIGENCE.getItemName()) != null && j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt() != 0) intI[16] += j.get(Identifications.INTELLIGENCE.getItemName()).getAsInt();
            if (j.get(Identifications.DEFENSE.getItemName()) != null && j.get(Identifications.DEFENSE.getItemName()).getAsInt() != 0) defI[16] += j.get(Identifications.DEFENSE.getItemName()).getAsInt();
            if (j.get(Identifications.AGILITY.getItemName()) != null && j.get(Identifications.AGILITY.getItemName()).getAsInt() != 0) agiI[16] += j.get(Identifications.AGILITY.getItemName()).getAsInt();
        }

        //Set Not Crafted and Have SP Bonus Armor and Accessory
        if (items.getJsonObjectList().size() > 0) {
            List<Integer> strChecked = new ArrayList<>();
            List<Integer> dexChecked = new ArrayList<>();
            List<Integer> intChecked = new ArrayList<>();
            List<Integer> defChecked = new ArrayList<>();
            List<Integer> agiChecked = new ArrayList<>();
            for (int i = 0; items.getJsonObjectList().size() > i; ++i) {
                if (items.getJsonObjectList().get(i).get("tier").isJsonNull() || !items.getJsonObjectList().get(i).get("tier").getAsString().equals("Crafted")) {
                    int[] pos = new int[] {9, 9, 9, 9, 9};
                    int[] min = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
                    for (int n = 0; items.getJsonObjectList().size() > n; ++n) {
                        if (items.getJsonObjectList().get(n).get("tier").isJsonNull() || !items.getJsonObjectList().get(n).get("tier").getAsString().equals("Crafted")) {
                            //Strength Req Sort
                            if (strI[n] != 0 && min[0] > strR[n] && !strChecked.contains(n)) {
                                pos[0] = n;
                                min[0] = strR[n];
                            }

                            //Dexterity Req Sort
                            if (dexI[n] != 0 && min[1] > dexR[n] && !dexChecked.contains(n)) {
                                pos[1] = n;
                                min[1] = dexR[n];
                            }

                            //Intelligence Req Sort
                            if (intI[n] != 0 && min[2] > intR[n] && !intChecked.contains(n)) {
                                pos[2] = n;
                                min[2] = intR[n];
                            }

                            //Defense Req Sort
                            if (defI[n] != 0 && min[3] > defR[n] && !defChecked.contains(n)) {
                                pos[3] = n;
                                min[3] = defR[n];
                            }

                            //Agility Req Sort
                            if (agiI[n] != 0 && min[4] > agiR[n] && !agiChecked.contains(n)) {
                                pos[4] = n;
                                min[4] = agiR[n];
                            }
                        }
                    }

                    //Strength
                    if (9 > pos[0]) {
                        strChecked.add(pos[0]); //Add Strength Checked Pos
                        if (strR[pos[0]] <= 0 || strR[pos[0]] <= totalSP[0]) { //Req = 0 or Total >= Req
                            originalSP[0] += strI[pos[0]];
                            totalSP[0] += strI[pos[0]];
                        } else if (strR[pos[0]] > totalSP[0]) { //Req > Total
                            int reqPlusBonus = strR[pos[0]] + strI[pos[0]];
                            manualSP[0] += strR[pos[0]] - totalSP[0];
                            originalSP[0] = reqPlusBonus;
                            totalSP[0] = reqPlusBonus;
                        }
                    }

                    //Dexterity
                    if (9 > pos[1]) {
                        dexChecked.add(pos[1]);
                        if (dexR[pos[1]] <= 0 || dexR[pos[1]] <= totalSP[1]) { //Req = 0 or Total >= Req
                            originalSP[1] += dexI[pos[1]];
                            totalSP[1] += dexI[pos[1]];
                        } else if (dexR[pos[1]] > totalSP[1]) { //Req > Total
                            int reqPlusBonus = dexR[pos[1]] + dexI[pos[1]];
                            manualSP[1] += dexR[pos[1]] - totalSP[1];
                            originalSP[1] = reqPlusBonus;
                            totalSP[1] = reqPlusBonus;
                        }
                    }

                    //Intelligence
                    if (9 > pos[2]) {
                        intChecked.add(pos[2]);
                        if (intR[pos[2]] <= 0 || intR[pos[2]] <= totalSP[2]) { //Req = 0 or Total >= Req
                            originalSP[2] += intI[pos[2]];
                            totalSP[2] += intI[pos[2]];
                        } else if (intR[pos[2]] > totalSP[2]) { //Req > Total
                            int reqPlusBonus = intR[pos[2]] + intI[pos[2]];
                            manualSP[2] += intR[pos[2]] - totalSP[2];
                            originalSP[2] = reqPlusBonus;
                            totalSP[2] = reqPlusBonus;
                        }
                    }

                    //Defense
                    if (9 > pos[3]) {
                        defChecked.add(pos[3]);
                        if (defR[pos[3]] <= 0 || defR[pos[3]] <= totalSP[3]) { //Req = 0 or Total >= Req
                            originalSP[3] += defI[pos[3]];
                            totalSP[3] += defI[pos[3]];
                        } else if (defR[pos[3]] > totalSP[3]) { //Req > Total
                            int reqPlusBonus = defR[pos[3]] + defI[pos[3]];
                            manualSP[3] += defR[pos[3]] - totalSP[3];
                            originalSP[3] = reqPlusBonus;
                            totalSP[3] = reqPlusBonus;
                        }
                    }

                    //Agility
                    if (9 > pos[4]) {
                        agiChecked.add(pos[4]);
                        if (agiR[pos[4]] <= 0 || agiR[pos[4]] <= totalSP[4]) { //Req = 0 or Total >= Req
                            originalSP[4] += agiI[pos[4]];
                            totalSP[4] += agiI[pos[4]];
                        } else if (agiR[pos[4]] > totalSP[4]) { //Req > Total
                            int reqPlusBonus = agiR[pos[4]] + agiI[pos[4]];
                            manualSP[4] += agiR[pos[4]] - totalSP[4];
                            originalSP[4] = reqPlusBonus;
                            totalSP[4] = reqPlusBonus;
                        }
                    }
                }
            }

            //Not Crafted and Not Have SP Bonus Armor and Accessory
            for (int i = 0; items.getJsonObjectList().size() > i; ++i) {
                if (items.getJsonObjectList().get(i).get("tier").isJsonNull() || !items.getJsonObjectList().get(i).get("tier").getAsString().equals("Crafted")) {
                    if (strR[i] > totalSP[0] && strR[i] != 0) { //Strength Req
                        manualSP[0] += strR[i] - totalSP[0]; //Manual Assign + (Req - Total)
                        originalSP[0] += strR[i] - totalSP[0]; //Original + (Req - Total)
                        totalSP[0] = strR[i]; //Total (No Crafted Item and Weapon Total Skill Point) = Req
                    }
                    if (dexR[i] > totalSP[1] && dexR[i] != 0) { //Dexterity Req
                        manualSP[1] += dexR[i] - totalSP[1];
                        originalSP[1] += dexR[i] - totalSP[1];
                        totalSP[1] = dexR[i];
                    }
                    if (intR[i] > totalSP[2] && intR[i] != 0) { //Intelligence Req
                        manualSP[2] += intR[i] - totalSP[2];
                        originalSP[2] += intR[i] - totalSP[2];
                        totalSP[2] = intR[i];
                    }
                    if (defR[i] > totalSP[3] && defR[i] != 0) { //Defense Req
                        manualSP[3] += defR[i] - totalSP[3];
                        originalSP[3] += defR[i] - totalSP[3];
                        totalSP[3] = defR[i];
                    }
                    if (agiR[i] > totalSP[4] && agiR[i] != 0) { //Agility Req
                        manualSP[4] += agiR[i] - totalSP[4];
                        originalSP[4] += agiR[i] - totalSP[4];
                        totalSP[4] = agiR[i];
                    }
                }
            }

            //Crafted Armor and Accessory Skill Point Req and Bonus
            for (int i = 0; items.getJsonObjectList().size() > i; ++i) {
                if (items.getJsonObjectList().get(i).get("tier") != null && items.getJsonObjectList().get(i).get("tier").getAsString().equals("Crafted")) {
                    if (strR[i] != 0 || dexR[i] != 0 || intR[i] != 0 || defR[i] != 0 || agiR[i] != 0 || strI[i + 8] != 0 || dexI[i + 8] != 0 || intI[i + 8] != 0 || defI[i + 8] != 0 || agiI[i + 8] != 0) {
                        if (strR[i] > totalSP[0] && strR[i] != 0) { //Strength Req
                            manualSP[0] += strR[i] - totalSP[0]; //Manual Assign + (Req - Total)
                            originalSP[0] += strR[i] - totalSP[0]; //Original + (Req - Total + Bonus)
                            totalSP[0] = strR[i]; //Total (No Crafted Item and Weapon Total Skill Point) = Req
                        }
                        if (dexR[i] > totalSP[1] && dexR[i] != 0) { //Dexterity Req
                            manualSP[1] += dexR[i] - totalSP[1];
                            originalSP[1] += dexR[i] - totalSP[1];
                            totalSP[1] = dexR[i];
                        }
                        if (intR[i] > totalSP[2] && intR[i] != 0) { //Intelligence Req
                            manualSP[2] += intR[i] - totalSP[2];
                            originalSP[2] += intR[i] - totalSP[2];
                            totalSP[2] = intR[i];
                        }
                        if (defR[i] > totalSP[3] && defR[i] != 0) { //Defense Req
                            manualSP[3] += defR[i] - totalSP[3];
                            originalSP[3] += defR[i] - totalSP[3];
                            totalSP[3] = defR[i];
                        }
                        if (agiR[i] > totalSP[4] && agiR[i] != 0) { //Agility Req
                            manualSP[4] += agiR[i] - totalSP[4];
                            originalSP[4] += agiR[i] - totalSP[4];
                            totalSP[4] = agiR[i];
                        }

                        if (strI[i] != 0) originalSP[0] += strI[i + 8]; //Strength Bonus
                        if (dexI[i] != 0) originalSP[1] += dexI[i + 8]; //Dexterity Bonus
                        if (intI[i] != 0) originalSP[2] += intI[i + 8]; //Intelligence Bonus
                        if (defI[i] != 0) originalSP[3] += defI[i + 8]; //Defense Bonus
                        if (agiI[i] != 0) originalSP[4] += agiI[i + 8]; //Agility Bonus
                    }
                }
            }
        }

        //Set Weapon Skill Point
        if (items.getWeapon() != null) {
            if (strR[8] != 0 || dexR[8] != 0 || intR[8] != 0 || defR[8] != 0 || agiR[8] != 0 || strI[16] != 0 || dexI[16] != 0 || intI[16] != 0 || defI[16] != 0 || agiI[16] != 0) {
                if (strR[8] > totalSP[0] && strR[8] != 0) { //Strength Req
                    manualSP[0] += strR[8] - totalSP[0]; //Manual Assign + Strength Req - Total (No Crafted Item and Weapon SP Bonus Skill Point)
                    originalSP[0] += strR[8] - totalSP[0]; //Original + Req - Total (No Crafted Item and Weapon SP Bonus Skill Point)
                    totalSP[0] = strR[8]; //Total (No Crafted Item and Weapon SP Bonus Skill Point) = Req
                }
                if (dexR[8] > totalSP[1] && dexR[8] != 0) { //Dexterity Req
                    manualSP[1] += dexR[8] - totalSP[1];
                    originalSP[1] += dexR[8] - totalSP[1];
                    totalSP[1] = dexR[8];
                }
                if (intR[8] > totalSP[2] && intR[8] != 0) { //Intelligence Req
                    manualSP[2] += intR[8] - totalSP[2];
                    originalSP[2] += intR[8] - totalSP[2];
                    totalSP[2] = intR[8];
                }
                if (defR[8] > totalSP[3] && defR[8] != 0) { //Defense Req
                    manualSP[3] += defR[8] - totalSP[3];
                    originalSP[3] += defR[8] - totalSP[3];
                    totalSP[3] = defR[8];
                }
                if (agiR[8] > totalSP[4] && agiR[8] != 0) { //Agility Req
                    manualSP[4] += agiR[8] - totalSP[4];
                    originalSP[4] += agiR[8] - totalSP[4];
                    totalSP[4] = agiR[8];
                }
                if (strI[16] != 0) originalSP[0] += strI[16]; //Strength Bonus
                if (dexI[16] != 0) originalSP[1] += dexI[16]; //Dexterity Bonus
                if (intI[16] != 0) originalSP[2] += intI[16]; //Intelligence Bonus
                if (defI[16] != 0) originalSP[3] += defI[16]; //Defense Bonus
                if (agiI[16] != 0) originalSP[4] += agiI[16]; //Agility Bonus
            }
        }

        strength.setValue(originalSP[0], manualSP[0], totalSP[0], strI);
        dexterity.setValue(originalSP[1], manualSP[1], totalSP[1], dexI);
        intelligence.setValue(originalSP[2], manualSP[2], totalSP[2], intI);
        defense.setValue(originalSP[3], manualSP[3], totalSP[3], defI);
        agility.setValue(originalSP[4], manualSP[4], totalSP[4], agiI);
        spText.setText("Using Skill Point: " + (manualSP[0] + manualSP[1] + manualSP[2] + manualSP[3] + manualSP[4]));
    }

    public void updateSkillPoint() {
        strength.updateValue();
        dexterity.updateValue();
        intelligence.updateValue();
        defense.updateValue();
        agility.updateValue();
        spText.setText("Using Skill Point: " + (strength.getTotalManual() + dexterity.getTotalManual() + intelligence.getTotalManual() + defense.getTotalManual() + agility.getTotalManual()));
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

    public enum SkillPointType {
        STRENGTH,
        DEXTERITY,
        INTELLIGENCE,
        DEFENSE,
        AGILITY
    }

    public static class SkillPointPanel {
        private final JTextField textField = new JTextField("0");
        private final JLabel name = new JLabel();
        private final JLabel boost = new JLabel();
        private final JLabel total = new JLabel("(0)");
        private final JLabel original = new JLabel("Original: 0");
        private final JLabel manual = new JLabel("Manual: 0");
        private final JLabel items = new JLabel("Items: 0");
        private int manualBase = 0;

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
            this.name.setText(name);
            setValue(0, 0, 0, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});

            this.name.setAlignmentX(Component.CENTER_ALIGNMENT);
            textField.setAlignmentX(Component.CENTER_ALIGNMENT);
            total.setAlignmentX(Component.CENTER_ALIGNMENT);
            boost.setAlignmentX(Component.CENTER_ALIGNMENT);
            original.setAlignmentX(Component.CENTER_ALIGNMENT);
            manual.setAlignmentX(Component.CENTER_ALIGNMENT);
            items.setAlignmentX(Component.CENTER_ALIGNMENT);

            pane.add(this.name);
            pane.add(textField);
            pane.add(total);
            pane.add(boost);
            pane.add(original);
            pane.add(manual);
            pane.add(items);

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
            switch (name.getText()) {
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
            if (name.getText().equals("Intelligence")) {
                int sp = getSPValue();
                if (sp > 0 && sp < 150) {
                    return STR_AND_DEX[sp];
                } else {
                    return STR_AND_DEX[150];
                }
            }
            return 1F;
        }

        public void setValue(int originalSP, int manualSP, int totalSP, int[] itemsSP) {
            total.setText("(" + totalSP + ")");
            original.setText("Original: " + originalSP);
            manual.setText("Manual: " + manualSP);
            items.setText("Items: " + (itemsSP[0] + itemsSP[1] + itemsSP[2] + itemsSP[3] + itemsSP[4] + itemsSP[5] + itemsSP[6] + itemsSP[7]) + " (" + (itemsSP[0] + itemsSP[1] + itemsSP[2] + itemsSP[3] + itemsSP[4] + itemsSP[5] + itemsSP[6] + itemsSP[7] + itemsSP[8] + itemsSP[9] + itemsSP[10] + itemsSP[11] + itemsSP[12] + itemsSP[13] + itemsSP[14] + itemsSP[15] + itemsSP[16]) + ")");
            textField.setText(String.valueOf(originalSP));
            manualBase = manualSP;
            updateValue();
        }

        public void updateValue() {
            switch (name.getText()) {
                case "Strength":
                    boost.setText("Damage: +" + (Math.round((getSPBoost() - 1) * 1000F) / 10F) + "%");
                    break;
                case "Dexterity":
                    boost.setText("Crit: " + (Math.round((getSPBoost() - 1) * 1000F) / 10F) + "%");
                    break;
                case "Intelligence":
                    boost.setText("SP Cost: -" + (Math.round(getSPBoost() * 10000F) / 100F) + "%");
                    break;
                case "Defense":
                    boost.setText("Resist: +" + (Math.round((getSPBoost() - 1) * 10000F) / 100F) + "%");
                    break;
                case "Agility":
                    boost.setText("Dodge: " + (Math.round((getSPBoost() - 1) * 10000F) / 100F) + "%");
                    break;
            }
            total.setText("(" + (getSPValue() - getOriginal() + getTotal()) + ")");
            manual.setText("Manual: " + getTotalManual());
        }

        public int getOriginal() {
            return Integer.parseInt(original.getText().replace("Original: ", ""));
        }

        public int getTotal() {
            return Integer.parseInt(total.getText().replace("(", "").replace(")", ""));
        }

        public int getTotalManual() {
            return getSPValue() - getOriginal() + manualBase;
        }
    }
}
