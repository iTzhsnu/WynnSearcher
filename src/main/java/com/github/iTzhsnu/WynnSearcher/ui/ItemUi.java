package com.github.iTzhsnu.WynnSearcher.ui;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.github.iTzhsnu.WynnSearcher.general.ItemType;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.List;

public abstract class ItemUi extends JPanel {
    protected final ItemBase item;
    protected final List<JLabel> label = new ArrayList<>();
    protected final float totalValue;
    protected final boolean isCustom;
    protected int urlSize = 0;

    public static final Map<Integer, Identifications> ITEM_IDS = new HashMap<>(87, 2);

    public static final Map<Integer, Identifications> REVERSED_ITEM_IDS = new HashMap<>(8, 2) {{
        put(0, Identifications.PERCENT_1ST_SPELL_COST);
        put(1, Identifications.PERCENT_2ND_SPELL_COST);
        put(2, Identifications.PERCENT_3RD_SPELL_COST);
        put(3, Identifications.PERCENT_4TH_SPELL_COST);

        put(4, Identifications.RAW_1ST_SPELL_COST);
        put(5, Identifications.RAW_2ND_SPELL_COST);
        put(6, Identifications.RAW_3RD_SPELL_COST);
        put(7, Identifications.RAW_4TH_SPELL_COST);
    }};

    public static final Map<Integer, Identifications> DAMAGE_IDS = new HashMap<>(6, 2) {{
        put(0, Identifications.NEUTRAL_DAMAGE);
        put(1, Identifications.EARTH_DAMAGE);
        put(2, Identifications.THUNDER_DAMAGE);
        put(3, Identifications.WATER_DAMAGE);
        put(4, Identifications.FIRE_DAMAGE);
        put(5, Identifications.AIR_DAMAGE);
    }};

    public static final Map<Integer, Identifications> DEFENSE_IDS = new HashMap<>(6, 2) {{
        put(0, Identifications.HEALTH);
        put(1, Identifications.EARTH_DEFENSE);
        put(2, Identifications.THUNDER_DEFENSE);
        put(3, Identifications.WATER_DEFENSE);
        put(4, Identifications.FIRE_DEFENSE);
        put(5, Identifications.AIR_DEFENSE);
    }};

    public static final Map<Integer, Identifications> SP_REQUESTS = new HashMap<>(5, 2) {{
        put(0, Identifications.STRENGTH_REQ);
        put(1, Identifications.DEXTERITY_REQ);
        put(2, Identifications.INTELLIGENCE_REQ);
        put(3, Identifications.DEFENSE_REQ);
        put(4, Identifications.AGILITY_REQ);
    }};

    public static final Map<Integer, Identifications> INGREDIENT_EFFECTIVENESS = new HashMap<>(6, 2) {{
        put(0, Identifications.INGREDIENT_EFFECTIVENESS_ABOVE);
        put(1, Identifications.INGREDIENT_EFFECTIVENESS_UNDER);
        put(2, Identifications.INGREDIENT_EFFECTIVENESS_LEFT);
        put(3, Identifications.INGREDIENT_EFFECTIVENESS_RIGHT);
        put(4, Identifications.INGREDIENT_EFFECTIVENESS_NOT_TOUCHING);
        put(5, Identifications.INGREDIENT_EFFECTIVENESS_TOUCHING);
    }};

    public ItemUi(ItemBase item, ItemType type, JPanel previous, JPanel above, int uiWidth, float totalValue, boolean isCustom) {
        this.item = item;
        this.totalValue = totalValue;
        this.isCustom = isCustom;

        setDisplay();

        if (previous != null) {
            Rectangle x = previous.getBounds();
            boolean down = x.x + x.width + 10 > uiWidth - 250;

            if (down) {
                Rectangle y = above.getBounds();
                setBounds(10, y.y + y.height + 10, 250, (label.size() + 1) * 16 + urlSize);
            } else {
                if (above != null) {
                    Rectangle y = above.getBounds();
                    setBounds(10 + x.x + x.width, y.y + y.height + 10, 250, (label.size() + 1) * 16 + urlSize);
                } else {
                    setBounds(10 + x.x + x.width, 10, 250, (label.size() + 1) * 16 + urlSize);
                }
            }
        } else {
            setBounds(10, 10, 250, (label.size() + 1) * 16 + urlSize);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if (!item.getIdString(Identifications.RARITY).isEmpty()) {
            if (type == ItemType.INGREDIENT) {
                try {
                    switch (item.getIdString(Identifications.RARITY)) {
                        case JsonValues.STAR_1:
                            setBackground(new Color(252, 242, 99));
                            break;
                        case JsonValues.STAR_2:
                            setBackground(new Color(255, 168, 211)); //OLD COLOR 220, 107, 154
                            break;
                        case JsonValues.STAR_3:
                            setBackground(new Color(135, 206, 250));
                            break;
                        default:
                            setBackground(new Color(230, 230, 230));
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("old item found.");
                }
            } else {
                switch (item.getIdString(Identifications.RARITY)) {
                    case JsonValues.UNIQUE:
                        setBackground(new Color(252, 242, 99));
                        break;
                    case JsonValues.RARE:
                        setBackground(new Color(255, 168, 211)); //OLD COLOR 220, 107, 154
                        break;
                    case JsonValues.LEGENDARY:
                        setBackground(new Color(135, 206, 250));
                        break;
                    case JsonValues.FABLED:
                        setBackground(new Color(220, 107, 154)); //OLD COLOR 255 81 81
                        break;
                    case JsonValues.MYTHIC:
                        setBackground(new Color(145, 93, 163));
                        break;
                    case "set": // This rarity was discontinued.
                        setBackground(new Color(121, 192, 110)); //OLD COLOR 85 255 85
                        break;
                    case JsonValues.CRAFTED:
                        setBackground(new Color(0, 175, 204));
                        break;
                    default:
                        setBackground(new Color(230, 230, 230));
                        break;
                }
            }
        }
        setVisible(true);
    }

    public abstract void setDisplay();

    public void fixesSize(String name) {
        switch (name) {
            case "Paladin's Embodiment of Undying Determination":
            case "Aspect of Overflowing Hope":
            case "Aspect of Unquenching Flames":
            case "Shadestepper's Embodiment of Unseen Execution":
            case "Aspect of Sleight-Of-Hand":
            case "Light Bender's Embodiment of Celestial Brilliance":
            case "Aspect of Undercrank":
            case "Ritualist's Embodiment of the Ancestral Avatar":
            case "Acolyte's Embodiment of Unwavering Adherence":
            case "Aspect of Motivation":
            case "Aspect of the Channeler":
            case "Aspect of Exsanguination":
                urlSize += 16;
                break;
            case "Fallen's Embodiment of Blind Fury":
            case "Battle Monk's Embodiment of Complete Synchrony":
            case "Aspect of Deafening Echoes":
            case "Trickster's Embodiment of the Ultimate Show":
            case "Arcanist's Embodiment of Total Obliteration":
            case "Aspect of Fatal Fulguration":
            case "Trapper's Embodiment of Persistence Predation":
            case "Sharpshooter's Embodiment of Laser Precision":
            case "Summoner's Embodiment of the Omnipotent Overseer":
            case "Aspect of Lashing Fire":
            case "Aspect of Stances":
                urlSize += 32;
                break;
        }
    }

    public static void setMerchant(JsonObject manual, StringBuilder sb, String itemName, String path) {
        if (manual.get(path) != null && manual.get(path).getAsJsonObject().get(itemName) != null) { //Merchant
            JsonObject merchant = manual.get(path).getAsJsonObject().get(itemName).getAsJsonObject();
            boolean isEmpty = true;
            if (merchant.get(JsonKeys.NAME.getKey()) != null) {
                String s = "Merchant: " + merchant.get(JsonKeys.NAME.getKey()).getAsString() + "<br>";
                sb.append(s);
                isEmpty = false;
            }
            if (merchant.get(JsonKeys.POS.getKey()) != null) {
                String s = "Locate: " + merchant.get(JsonKeys.POS.getKey()).getAsString() + "<br>";
                sb.append(s);
                isEmpty = false;
            }
            if (merchant.get(JsonKeys.PRICE.getKey()) != null) {
                String s = "Price: " + merchant.get(JsonKeys.PRICE.getKey()).getAsString() + "<br>";
                sb.append(s);
                isEmpty = false;
            }

            if (isEmpty) sb.append("Merchant");

            sb.append("<br>");
        }
    }

    public static void setPosOnlyType(JsonObject manual, StringBuilder sb, String itemName, String path, String description) {
        if (manual.get(path) != null && manual.get(path).getAsJsonObject().get(itemName) != null) { //Other
            String s = description + manual.get(path).getAsJsonObject().get(itemName).getAsJsonObject().get(JsonKeys.POS.getKey()).getAsString() + "<br><br>";
            sb.append(s);
        }
    }

   public static String getMin(int base) {
       if (base < 0) {
           return String.valueOf(getMinInt(base));
       } else {
           return "+" + getMinInt(base);
       }
   }

   public static String getMax(int base) {
       if (base < 0) {
           return String.valueOf(getMaxInt(base));
       } else {
           return "+" + getMaxInt(base);
       }
   }

   public String getReversedMin(int base) {
       if (base < 0) {
           return String.valueOf(getReversedMinInt(base));
       } else {
           return "+" + getReversedMinInt(base);
       }
   }

   public String getReversedMax(int base) {
       if (base < 0) {
           return String.valueOf(getReversedMaxInt(base));
       } else {
           return "+" + getReversedMaxInt(base);
       }
   }

   public static int getMinInt(int base) {
       if (base < 0) {
           return (int) Math.min(Math.round(base * 1.3d), -1);
       } else {
           return (int) Math.max(Math.round(base * 0.3d), 1);
       }
   }

   public static int getMaxInt(int base) {
        if (base < 0) {
            return (int) Math.min(Math.round(base * 0.7d), -1);
        } else {
            return (int) Math.max(Math.round(base * 1.3d), 1);
        }
    }

    public static int getReversedMinInt(int base) {
        if (base < 0) {
            return (int) Math.min(Math.round(base * 1.3d), -1);
        } else {
            return (int) Math.max(Math.round(base * 0.7d), 1);
        }
    }

    public static int getReversedMaxInt(int base) {
        if (base < 0) {
            return (int) Math.min(Math.round(base * 0.3d), -1);
        } else {
            return (int) Math.max(Math.round(base * 1.3d), 1);
        }
    }

   public static String setPlus(int i) {
        if (i < 0) {
            return "" + i;
        } else {
            return "+" + i;
        }
   }

   public static String getHowToObtainText(String itemName, JsonObject json, int lv, JsonObject manual) {
       StringBuilder sb = new StringBuilder();
       sb.append("&fThis item can be obtained by<br>");

       int p = haveManualDrop(manual, itemName);
       if (p > 0 && json.get(JsonKeys.DROP_META.getKey()) == null) { // dropMeta == null is need?
           if (p == 1) {
               //Unobtainable
               return "<html>" + TreeCheckBox.fixesText("&fThis item can't be obtained.");
           } else if (p == 13) {
               //Discontinued
               return "<html>" + TreeCheckBox.fixesText("&fThis item is Discontinued.<br>" + manual.get(JsonValues.DISCONTINUED).getAsJsonObject().get(itemName).getAsJsonObject().get(JsonKeys.POS.getKey()).getAsString());
           } else {
               if (manual.get(JsonValues.D_NORMAL) != null) { //Normal
                   for (JsonElement je : manual.get(JsonValues.D_NORMAL).getAsJsonArray()) {
                       if (je.getAsString().equals(itemName)) {
                           String s = "Hostile Mob and Any Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4) + "<br><br>";
                           sb.append(s);
                           break;
                       }
                   }
               }
               if (manual.get(JsonValues.LEGENDARY_ISLAND) != null) { //Legendary Island
                   for (JsonElement je : manual.get(JsonValues.LEGENDARY_ISLAND).getAsJsonArray()) {
                       if (je.getAsString().equals(itemName)) {
                           sb.append("Legendary Island<br><br>");
                           break;
                       }
                   }
               }
               setPosOnlyType(manual, sb, itemName, JsonValues.DUNGEON, ""); //Dungeon Drop (and Forgery Chest)
               setMerchant(manual, sb, itemName, JsonValues.MERCHANT); //Merchant
               setMerchant(manual, sb, itemName, JsonValues.DUNGEON_MERCHANT); //Dungeon Merchant
               setPosOnlyType(manual, sb, itemName, JsonValues.THE_QIRA_HIVE, "The Qira Hive: "); //The Qira Hive
               setPosOnlyType(manual, sb, itemName, JsonValues.SECRET_DISCOVERY, ""); //Secret Discovery
               setPosOnlyType(manual, sb, itemName, JsonValues.QUEST, "Quest: "); //Quest
               setPosOnlyType(manual, sb, itemName, JsonValues.RAID, "Raid Rewards: "); //Raid Rewards
               setPosOnlyType(manual, sb, itemName, JsonValues.OTHER, ""); //Other
               setPosOnlyType(manual, sb, itemName, JsonValues.WORLD_EVENT, "World Event: "); //World Event
               setPosOnlyType(manual, sb, itemName, JsonValues.LOOTRUN, ""); //Lootrun
               if (manual.get(JsonValues.SPECIFIC) != null && manual.get(JsonValues.SPECIFIC).getAsJsonObject().get(itemName) != null) { //Specific
                   for (JsonElement je : manual.get(JsonValues.SPECIFIC).getAsJsonObject().get(itemName).getAsJsonArray()) {
                       JsonObject jsp = je.getAsJsonObject();
                       if (jsp.get(JsonKeys.IS_MOBNAME.getKey()) == null || jsp.get(JsonKeys.IS_MOBNAME.getKey()).getAsBoolean())
                           sb.append("Mob Name: ");
                       sb.append(jsp.get(JsonKeys.NAME.getKey()).getAsString());
                       if (jsp.get(JsonKeys.POS.getKey()) != null) {
                           for (JsonElement je1 : jsp.get(JsonKeys.POS.getKey()).getAsJsonArray()) {
                               String s = "<br>Locate: " + je1.getAsString();
                               sb.append(s);
                           }
                       }
                       sb.append("<br><br>");
                   }
               }
               return "<html>" + TreeCheckBox.fixesText(sb.substring(0, sb.toString().length() - 4));
           }
       } else {
           if (json.get(JsonKeys.DROP_META.getKey()) != null) {
               JsonObject j = json.get(JsonKeys.DROP_META.getKey()).getAsJsonObject();
               boolean skipLocate = false;
               if (j.get(JsonKeys.NAME.getKey()) != null) sb.append(j.get(JsonKeys.NAME.getKey()).getAsString());
               if (j.get(JsonKeys.TYPE.getKey()) != null) {
                   if (j.get(JsonKeys.TYPE.getKey()).isJsonArray() && j.get(JsonKeys.EVENT.getKey()) != null) {
                       String s = " Merchant and " + j.get(JsonKeys.EVENT.getKey()).getAsString() + " Event";
                       sb.append(s);
                   } else {
                       boolean skipUnknown = false;
                       switch (j.get(JsonKeys.TYPE.getKey()).getAsString()) {
                           case JsonValues.DUNGEON_MERCHANT:
                               sb.append(" Dungeon Merchant");
                               break;
                           case JsonValues.RAID:
                               sb.append(" Raid Rewards");
                               break;
                           case JsonValues.ALTAR:
                               sb.append(" Altar");
                               break;
                           case JsonValues.DUNGEON:
                               sb.append(" Dungeon Drop");
                               break;
                           case JsonValues.MERCHANT:
                               sb.append(" Merchant");
                               break;
                           case JsonValues.LOOTRUN:
                           case JsonValues.QUEST:
                           case JsonValues.CHALLENGE:
                           case JsonValues.MINIBOSS:
                               skipUnknown = true;
                           default:
                               String s = " " + j.get(JsonKeys.TYPE.getKey()).getAsString();
                               sb.append(s);
                               if (!skipUnknown) System.out.println(itemName + " has unknown drop type: " + j.get(JsonKeys.TYPE.getKey()).getAsString());
                               break;
                       }
                   }
               }
               if (j.get(JsonKeys.COORDINATES.getKey()) != null && !skipLocate) {
                   String s = "<br>Locate: " + j.get(JsonKeys.COORDINATES.getKey()).getAsJsonArray().get(0).getAsInt() + ", " + j.get(JsonKeys.COORDINATES.getKey()).getAsJsonArray().get(1).getAsInt() + ", " + j.get(JsonKeys.COORDINATES.getKey()).getAsJsonArray().get(2).getAsInt();
                   sb.append(s);
               }
               return "<html>" + TreeCheckBox.fixesText(sb.toString());
           } else if (json.get(JsonKeys.DROP_RESTRICTION.getKey()) != null) {
               switch (json.get(JsonKeys.DROP_RESTRICTION.getKey()).getAsString()) {
                   case JsonValues.D_NORMAL: {
                       return "<html>" + TreeCheckBox.fixesText(sb + "Hostile Mob and Any Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4));
                   }
                   case JsonValues.LOOTCHEST: {
                       return "<html>" + TreeCheckBox.fixesText(sb + "Tier 3 and 4 Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4));
                   }
                   case JsonValues.NEVER: {
                       return "<html>" + TreeCheckBox.fixesText("&fUnknown");
                   }
                   case JsonValues.DUNGEON: {
                       return "<html>" + TreeCheckBox.fixesText(sb + "Dungeon Drop");
                   }
                   default: {
                       System.out.println(itemName + " has unknown drop type:" + json.get(JsonKeys.DROP_RESTRICTION.getKey()).getAsString());
                       return "<html>" + TreeCheckBox.fixesText(sb + json.get(JsonKeys.DROP_RESTRICTION.getKey()).getAsString());
                   }
               }
           }
       }
       return "";
   }

   public static int haveManualDrop(JsonObject j, String itemName) {
        if (j.get(JsonValues.D_NORMAL) != null) { //Normal
            for (JsonElement je : j.get(JsonValues.D_NORMAL).getAsJsonArray()) {
                if (je.getAsString().equals(itemName)) return 11;
            }
        }
        if (j.get(JsonValues.UNOBTAINABLE) != null) { //Unobtainable
            for (JsonElement je : j.get(JsonValues.UNOBTAINABLE).getAsJsonArray()) {
                if (je.getAsString().equals(itemName)) return 1;
            }
        }
        //Dugenon Drop and Forgery Chest
        if (j.get(JsonValues.DUNGEON) != null && j.get(JsonValues.DUNGEON).getAsJsonObject().get(itemName) != null) return 2;

        if (j.get(JsonValues.LEGENDARY_ISLAND) != null) { //Legendary Island
            for (JsonElement je : j.get(JsonValues.LEGENDARY_ISLAND).getAsJsonArray()) {
                if (je.getAsString().equals(itemName)) return 3;
            }
        }
        //Merchant
        if (j.get(JsonValues.MERCHANT) != null && j.get(JsonValues.MERCHANT).getAsJsonObject().get(itemName) != null) {
            //if (!j.get(JsonValues.MERCHANT).getAsJsonObject().get(itemName).getAsJsonObject().entrySet().isEmpty()) {
            //    return 4;
            //} else {
            //    return 14;
            //}
            return 4;
        }

        //The Qira Hive
       if (j.get(JsonValues.THE_QIRA_HIVE) != null && j.get(JsonValues.THE_QIRA_HIVE).getAsJsonObject().get(itemName) != null) return 5;

       //Secret Discovery
       if (j.get(JsonValues.SECRET_DISCOVERY) != null && j.get(JsonValues.SECRET_DISCOVERY).getAsJsonObject().get(itemName) != null) return 6;

       //Quest Rewards
       if (j.get(JsonValues.QUEST) != null && j.get(JsonValues.QUEST).getAsJsonObject().get(itemName) != null) return 7;

       //Specific
       if (j.get(JsonValues.SPECIFIC) != null && j.get(JsonValues.SPECIFIC).getAsJsonObject().get(itemName) != null) return 8;

       //Raid Rewards
       if (j.get(JsonValues.RAID) != null && j.get(JsonValues.RAID).getAsJsonObject().get(itemName) != null) return 9;

       //Other
       if (j.get(JsonValues.OTHER) != null && j.get(JsonValues.OTHER).getAsJsonObject().get(itemName) != null) return 10;

       //Dungeon Merchant
       if (j.get(JsonValues.DUNGEON_MERCHANT) != null && j.get(JsonValues.DUNGEON_MERCHANT).getAsJsonObject().get(itemName) != null) return 12;

       //Discontinued
       if (j.get(JsonValues.DISCONTINUED) != null && j.get(JsonValues.DISCONTINUED).getAsJsonObject().get(itemName) != null) return 13;

       //World Event
       if (j.get(JsonValues.WORLD_EVENT) != null && j.get(JsonValues.WORLD_EVENT).getAsJsonObject().get(itemName) != null) return 15;

       //Lootrun
       if (j.get(JsonValues.LOOTRUN) != null && j.get(JsonValues.LOOTRUN).getAsJsonObject().get(itemName) != null) return 16;

        return 0;
   }

   public static class FullMessageAction implements ActionListener {

       @Override
       public void actionPerformed(ActionEvent e) {
           if (e.getSource() instanceof JButton) {
               String s = ((JButton) e.getSource()).getToolTipText()
                       .replaceAll("<html>", "").replaceAll("<br>", "\n").replaceAll("</font>", "")
                       .replaceAll("<font color=\"#000000\">", "").replaceAll("<font color=\"#0000AA\">", "").replaceAll("<font color=\"#00AA00\">", "")
                       .replaceAll("<font color=\"#00AAAA\">", "").replaceAll("<font color=\"#AA0000\">", "").replaceAll("<font color=\"#AA00AA\">", "")
                       .replaceAll("<font color=\"#FFAA00\">", "").replaceAll("<font color=\"#AAAAAA\">", "").replaceAll("<font color=\"#555555\">", "")
                       .replaceAll("<font color=\"#5555FF\">", "").replaceAll("<font color=\"#55FF55\">", "").replaceAll("<font color=\"#55FFFF\">", "")
                       .replaceAll("<font color=\"#FF5555\">", "").replaceAll("<font color=\"#FF55FF\">", "").replaceAll("<font color=\"#FFFF55\">", "")
                       .replaceAll("<font color=\"#FFFFFF\">", "").replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("<u>", "").replaceAll("</u>", "");
               System.out.println("Clipped: " + s);

               StringSelection selection = new StringSelection(s);
               Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
           }
       }
   }

   public static class OpenURLAction implements ActionListener {

       @Override
       public void actionPerformed(ActionEvent e) {
           if (Desktop.isDesktopSupported() && e.getSource() instanceof JButton) {
               try {
                   Desktop.getDesktop().browse(URI.create(((JButton) e.getSource()).getToolTipText()));
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
       }
   }

   public static class JLabel_Custom extends JLabel {
        public JLabel_Custom(String text) {
            super(text);
        }

       @Override
       public JToolTip createToolTip() {
           JToolTip toolTip = new JToolTip().createToolTip();
           toolTip.setBackground(new Color(15, 1, 15));
           return toolTip;
       }
   }

   public static class JButton_Custom extends JButton {
        public JButton_Custom(String s) {
            super(s);
        }

       @Override
       public JToolTip createToolTip() {
           JToolTip toolTip = new JToolTip().createToolTip();
           toolTip.setBackground(new Color(15, 1, 15));
           return toolTip;
       }
   }
}
