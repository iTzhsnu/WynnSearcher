package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.List;

public class ItemUITemplate extends JPanel {
    private final JsonObject json;
    private final List<JLabel> label = new ArrayList<>();
    private final float totalValue;
    private final boolean isCustom;

    public static final Map<Integer, Identifications> ITEM_IDS = new HashMap<Integer, Identifications>() {{
        put(0, Identifications.STRENGTH);
        put(1, Identifications.DEXTERITY);
        put(2, Identifications.INTELLIGENCE);
        put(3, Identifications.DEFENSE);
        put(4, Identifications.AGILITY);

        put(5, Identifications.HEALTH_BONUS);
        put(6, Identifications.RAW_HEALTH_REGEN);
        put(7, Identifications.HEALTH_REGEN_PERCENT);
        put(8, Identifications.LIFE_STEAL);
        put(9, Identifications.MANA_STEAL);
        put(10, Identifications.MANA_REGEN);

        put(11, Identifications.EARTH_DEFENSE_PERCENT);
        put(12, Identifications.THUNDER_DEFENSE_PERCENT);
        put(13, Identifications.WATER_DEFENSE_PERCENT);
        put(14, Identifications.FIRE_DEFENSE_PERCENT);
        put(15, Identifications.AIR_DEFENSE_PERCENT);

        put(16, Identifications.SPELL_DAMAGE_PERCENT);
        put(17, Identifications.MELEE_DAMAGE_PERCENT);
        put(18, Identifications.EARTH_DAMAGE_PERCENT);
        put(19, Identifications.THUNDER_DAMAGE_PERCENT);
        put(20, Identifications.WATER_DAMAGE_PERCENT);
        put(21, Identifications.FIRE_DAMAGE_PERCENT);
        put(22, Identifications.AIR_DAMAGE_PERCENT);
        put(23, Identifications.ELEMENTAL_DAMAGE_PERCENT);

        put(24, Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT);
        put(25, Identifications.EARTH_SPELL_DAMAGE_PERCENT);
        put(26, Identifications.THUNDER_SPELL_DAMAGE_PERCENT);
        put(27, Identifications.WATER_SPELL_DAMAGE_PERCENT);
        put(28, Identifications.FIRE_SPELL_DAMAGE_PERCENT);
        put(29, Identifications.AIR_SPELL_DAMAGE_PERCENT);
        put(30, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT);

        put(31, Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT);
        put(32, Identifications.EARTH_MELEE_DAMAGE_PERCENT);
        put(33, Identifications.THUNDER_MELEE_DAMAGE_PERCENT);
        put(34, Identifications.WATER_MELEE_DAMAGE_PERCENT);
        put(35, Identifications.FIRE_MELEE_DAMAGE_PERCENT);
        put(36, Identifications.AIR_MELEE_DAMAGE_PERCENT);
        put(37, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT);

        put(38, Identifications.RAW_ELEMENTAL_DAMAGE);
        put(39, Identifications.RAW_SPELL_DAMAGE);
        put(40, Identifications.RAW_MELEE_DAMAGE);

        put(41, Identifications.RAW_NEUTRAL_SPELL_DAMAGE);
        put(42, Identifications.RAW_EARTH_SPELL_DAMAGE);
        put(43, Identifications.RAW_THUNDER_SPELL_DAMAGE);
        put(44, Identifications.RAW_WATER_SPELL_DAMAGE);
        put(45, Identifications.RAW_FIRE_SPELL_DAMAGE);
        put(46, Identifications.RAW_AIR_SPELL_DAMAGE);
        put(47, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE);

        put(48, Identifications.RAW_NEUTRAL_MELEE_DAMAGE);
        put(49, Identifications.RAW_EARTH_MELEE_DAMAGE);
        put(50, Identifications.RAW_THUNDER_MELEE_DAMAGE);
        put(51, Identifications.RAW_WATER_MELEE_DAMAGE);
        put(52, Identifications.RAW_FIRE_MELEE_DAMAGE);
        put(53, Identifications.RAW_AIR_MELEE_DAMAGE);
        put(54, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE);

        put(55, Identifications.POISON);
        put(56, Identifications.ATTACK_SPEED_BONUS);

        put(57, Identifications.WALK_SPEED);
        put(58, Identifications.SPRINT_BONUS);
        put(59, Identifications.SPRINT_REGEN);
        put(60, Identifications.JUMP_HEIGHT);

        put(61, Identifications.THORNS);
        put(62, Identifications.REFLECTION);
        put(63, Identifications.EXPLODING);
        put(64, Identifications.STEALING);
        put(65, Identifications.COMBAT_XP_BONUS);
        put(66, Identifications.GATHERING_XP_BONUS);
        put(67, Identifications.GATHERING_SPEED_BONUS);
        put(68, Identifications.LOOT_BONUS);
        put(69, Identifications.LOOT_QUALITY);
        put(70, Identifications.SOUL_POINT_REGEN);
    }};

    public static final Map<Integer, Identifications> REVERSED_ITEM_IDS = new HashMap<Integer, Identifications>() {{
       put(0, Identifications.PERCENT_1ST_SPELL_COST);
       put(1, Identifications.PERCENT_2ND_SPELL_COST);
       put(2, Identifications.PERCENT_3RD_SPELL_COST);
       put(3, Identifications.PERCENT_4TH_SPELL_COST);

       put(4, Identifications.RAW_1ST_SPELL_COST);
       put(5, Identifications.RAW_2ND_SPELL_COST);
       put(6, Identifications.RAW_3RD_SPELL_COST);
       put(7, Identifications.RAW_4TH_SPELL_COST);
    }};

    public ItemUITemplate(JsonObject json, boolean ing, JPanel previous, JPanel above, int uiWidth, float totalValue, boolean isCustom) {
        this.json = json;
        this.totalValue = totalValue;
        this.isCustom = isCustom;
        int urlSize = 0;

        if (ing) {
            setIngDisplay();
            if (!isCustom) urlSize = 32;
        } else {
            setItemDisplay();
            if (!isCustom) urlSize = 56;
        }

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
        if (!ing && json.get("tier") != null) {
            switch (json.get("tier").getAsString()) {
                case "Unique":
                    setBackground(new Color(252, 242, 99));
                    break;
                case "Rare":
                    setBackground(new Color(255, 168, 211)); //OLD COLOR 220, 107, 154
                    break;
                case "Legendary":
                    setBackground(new Color(135, 206, 250));
                    break;
                case "Fabled":
                    setBackground(new Color(220, 107, 154)); //OLD COLOR 255 81 81
                    break;
                case "Mythic":
                    setBackground(new Color(145, 93, 163));
                    break;
                case "Set":
                    setBackground(new Color(121, 192, 110)); //OLD COLOR 85 255 85
                    break;
                default:
                    setBackground(new Color(230, 230, 230));
                    break;
            }
        } else if (ing && json.get("tier") != null) {
            switch (json.get("tier").getAsInt()) {
                case 1:
                    setBackground(new Color(252, 242, 99));
                    break;
                case 2:
                    setBackground(new Color(255, 168, 211)); //OLD COLOR 220, 107, 154
                    break;
                case 3:
                    setBackground(new Color(135, 206, 250));
                    break;
                default:
                    setBackground(new Color(230, 230, 230));
                    break;
            }
        }
        setVisible(true);
    }

    public void setItemDisplay() {
        JButton dataButton = new JButton("Open Wynndata");
        dataButton.setBorderPainted(false);
        dataButton.setOpaque(false);
        dataButton.setBackground(Color.WHITE);
        dataButton.setForeground(Color.BLUE);
        dataButton.addActionListener(new OpenURLAction());

        JButton builderButton = new JButton("Open Wynnbuilder");
        builderButton.setBorderPainted(false);
        builderButton.setOpaque(false);
        builderButton.setBackground(Color.WHITE);
        builderButton.setForeground(Color.BLUE);
        builderButton.addActionListener(new OpenURLAction());

        if (json.get("displayName") == null) {
            label.add(new JLabel(json.get("name").getAsString()));
            String name = json.get("name").getAsString().replaceAll(" ", "%20");
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + name);
            builderButton.setToolTipText("https://hppeng-wynn.github.io/item/#" + name);
        } else {
            label.add(new JLabel(json.get("displayName").getAsString()));
            String name = json.get("displayName").getAsString().replaceAll(" ", "%20");
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + name);
            builderButton.setToolTipText("https://hppeng-wynn.github.io/item/#" + name);
        }

        if (json.get("type") != null) {
            label.add(new JLabel("Type: " + json.get("type").getAsString()));
        } else if (json.get("accessoryType") != null) {
            label.add(new JLabel("Type: " + json.get("accessoryType").getAsString()));
        }

        if (json.get("attackSpeed") != null) {
            label.add(new JLabel("Attack Speed: " + json.get("attackSpeed").getAsString()));
        }

        label.add(new JLabel(" "));

        if (json.get("health") != null && json.get("health").getAsInt() != 0) {
            label.add(new JLabel("Health: " + setPlus(json.get("health").getAsInt())));
        }

        if (json.get("earthDefense") != null && json.get("earthDefense").getAsInt() != 0) {
            label.add(new JLabel("Earth Defense: " + setPlus(json.get("earthDefense").getAsInt())));
        }

        if (json.get("thunderDefense") != null && json.get("thunderDefense").getAsInt() != 0) {
            label.add(new JLabel("Thunder Defense: " + setPlus(json.get("thunderDefense").getAsInt())));
        }

        if (json.get("waterDefense") != null && json.get("waterDefense").getAsInt() != 0) {
            label.add(new JLabel("Water Defense: " + setPlus(json.get("waterDefense").getAsInt())));
        }

        if (json.get("fireDefense") != null && json.get("fireDefense").getAsInt() != 0) {
            label.add(new JLabel("Fire Defense: " + setPlus(json.get("fireDefense").getAsInt())));
        }

        if (json.get("airDefense") != null && json.get("airDefense").getAsInt() != 0) {
            label.add(new JLabel("Air Defense: " + setPlus(json.get("airDefense").getAsInt())));
        }

        if (json.get("damage") != null && !Objects.equals(json.get("damage").getAsString(), "0-0")) {
            label.add(new JLabel("Neutral Damage: " + json.get("damage").getAsString()));
        }

        if (json.get("earthDamage") != null && !Objects.equals(json.get("earthDamage").getAsString(), "0-0")) {
            label.add(new JLabel("Earth Damage: " + json.get("earthDamage").getAsString()));
        }

        if (json.get("thunderDamage") != null && !Objects.equals(json.get("thunderDamage").getAsString(), "0-0")) {
            label.add(new JLabel("Thunder Damage: " + json.get("thunderDamage").getAsString()));
        }

        if (json.get("waterDamage") != null && !Objects.equals(json.get("waterDamage").getAsString(), "0-0")) {
            label.add(new JLabel("Water Damage: " + json.get("waterDamage").getAsString()));
        }

        if (json.get("fireDamage") != null && !Objects.equals(json.get("fireDamage").getAsString(), "0-0")) {
            label.add(new JLabel("Fire Damage: " + json.get("fireDamage").getAsString()));
        }

        if (json.get("airDamage") != null && !Objects.equals(json.get("airDamage").getAsString(), "0-0")) {
            label.add(new JLabel("Air Damage: " + json.get("airDamage").getAsString()));
        }

        label.add(new JLabel(" "));

        if (json.get("level") != null) {
            label.add(new JLabel("Combat Lv. Min: " + json.get("level").getAsInt()));
        }

        if (json.get("strength") != null && json.get("strength").getAsInt() != 0) {
            label.add(new JLabel("Strength Req: " + json.get("strength").getAsInt()));
        }

        if (json.get("dexterity") != null && json.get("dexterity").getAsInt() != 0) {
            label.add(new JLabel("Dexterity Req: " + json.get("dexterity").getAsInt()));
        }

        if (json.get("intelligence") != null && json.get("intelligence").getAsInt() != 0) {
            label.add(new JLabel("Intelligence Req: " + json.get("intelligence").getAsInt()));
        }

        if (json.get("defense") != null && json.get("defense").getAsInt() != 0) {
            label.add(new JLabel("Defense Req: " + json.get("defense").getAsInt()));
        }

        if (json.get("agility") != null && json.get("agility").getAsInt() != 0) {
            label.add(new JLabel("Agility Req: " + json.get("agility").getAsInt()));
        }

        if (json.get("quest") != null && !json.get("quest").isJsonNull()) {
            label.add(new JLabel("Quest Req: " + json.get("quest").getAsString()));
        }

        label.add(new JLabel(" "));

        for (int i = 0; 70 >= i; ++i) {
            Identifications id = ITEM_IDS.get(i);
            if (json.get(id.getItemName()) != null && json.get(id.getItemName()).getAsInt() != 0) {
                if (!id.isItemVariable() || json.get("identified") != null && json.get("identified").getAsBoolean()) {
                    label.add(new JLabel(id.getDisplayName() + " " + setPlus(json.get(id.getItemName()).getAsInt()) + id.getDisplaySp()));
                } else {
                    label.add(new JLabel(getMin(json.get(id.getItemName()).getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + getMax(json.get(id.getItemName()).getAsInt()) + id.getDisplaySp()));
                }
            }
        }

        for (int i = 0; 7 >= i; ++i) {
            Identifications id = REVERSED_ITEM_IDS.get(i);
            if (json.get(id.getItemName()) != null && json.get(id.getItemName()).getAsInt() != 0) {
                if (!id.isItemVariable() || json.get("identified") != null && json.get("identified").getAsBoolean()) {
                    label.add(new JLabel(id.getDisplayName() + " " + setPlus(json.get(id.getItemName()).getAsInt()) + id.getDisplaySp()));
                } else {
                    label.add(new JLabel(getReversedMin(json.get(id.getItemName()).getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + getReversedMax(json.get(id.getItemName()).getAsInt()) + id.getDisplaySp()));
                }
            }
        }

        label.add(new JLabel(" "));
        if (json.get("sockets") != null && json.get("sockets").getAsInt() != 0) {
            label.add(new JLabel("Powder Slots: " + json.get("sockets").getAsInt()));
        }
        if (json.get("majorIds") != null && json.get("majorIds").getAsJsonArray() != null) {
            label.add(new JLabel("Major ID: " + json.get("majorIds").getAsJsonArray().get(0).getAsString()));
        }
        if (json.get("tier") != null) {
            label.add(new JLabel("Rarity: " + json.get("tier").getAsString()));
        }
        if (json.get("restrictions") != null && !json.get("restrictions").isJsonNull()) {
            label.add(new JLabel(json.get("restrictions").getAsString()));
        }
        if (!isCustom) {
            label.add(new JLabel(" "));
            label.add(new JLabel("External Links"));
        }

        for (JLabel jLabel : label) {
            add(jLabel);
        }

        if (!isCustom) {
            add(dataButton);
            add(builderButton);

            JLabel sortValue = new JLabel("Sort Value: " + totalValue);
            sortValue.setForeground(Color.DARK_GRAY);
            add(sortValue);
        }
    }

    public void setIngDisplay() {
        JButton dataButton = new JButton("Wynndata Link");
        dataButton.setBorderPainted(false);
        dataButton.setOpaque(false);
        dataButton.setBackground(Color.WHITE);
        dataButton.setForeground(Color.BLUE);
        dataButton.addActionListener(new OpenURLAction());

        if (json.get("displayName") == null) {
            label.add(new JLabel(json.get("name").getAsString()));
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + json.get("name").getAsString().replaceAll(" ", "%20"));
        } else {
            label.add(new JLabel(json.get("displayName").getAsString()));
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + json.get("displayName").getAsString().replaceAll(" ", "%20"));
        }

        if (json.get("tier") != null) {
            label.add(new JLabel("Star: " + json.get("tier").getAsInt()));
        }

        if (json.get("level") != null) {
            label.add(new JLabel("Lv. Min: " + json.get("level").getAsInt()));
        }

        label.add(new JLabel(" "));

        if (json.get("itemOnlyIDs") != null) {
            JsonObject j = json.getAsJsonObject("itemOnlyIDs").getAsJsonObject();
            boolean run = false;
            if (j.get("durabilityModifier") != null && j.get("durabilityModifier").getAsInt() != 0) {
                label.add(new JLabel("Durability: " + setPlus(j.get("durabilityModifier").getAsInt())));
                run = true;
            }
            if (j.get("strengthRequirement") != null && j.get("strengthRequirement").getAsInt() != 0) {
                label.add(new JLabel("Strength Req: " + setPlus(j.get("strengthRequirement").getAsInt())));
                run = true;
            }
            if (j.get("dexterityRequirement") != null && j.get("dexterityRequirement").getAsInt() != 0) {
                label.add(new JLabel("Dexterity Req: " + setPlus(j.get("dexterityRequirement").getAsInt())));
                run = true;
            }
            if (j.get("intelligenceRequirement") != null && j.get("intelligenceRequirement").getAsInt() != 0) {
                label.add(new JLabel("Intelligence Req: " + setPlus(j.get("intelligenceRequirement").getAsInt())));
                run = true;
            }
            if (j.get("defenceRequirement") != null && j.get("defenceRequirement").getAsInt() != 0) {
                label.add(new JLabel("Defense Req: " + setPlus(j.get("defenceRequirement").getAsInt())));
                run = true;
            }
            if (j.get("agilityRequirement") != null && j.get("agilityRequirement").getAsInt() != 0) {
                label.add(new JLabel("Agility Req: " + setPlus(j.get("agilityRequirement").getAsInt())));
                run = true;
            }
            if (run) label.add(new JLabel(" "));
        }

        if (json.get("consumableOnlyIDs") != null) {
            JsonObject j = json.get("consumableOnlyIDs").getAsJsonObject();
            boolean run = false;
            if (j.get("duration") != null && j.get("duration").getAsInt() != 0) {
                label.add(new JLabel("Duration: " + setPlus(j.get("duration").getAsInt())));
                run = true;
            }
            if (j.get("charges") != null && j.get("charges").getAsInt() != 0) {
                label.add(new JLabel("Charges: " + setPlus(j.get("charges").getAsInt())));
                run = true;
            }
            if (run) label.add(new JLabel(" "));
        }

        if (json.get("ingredientPositionModifiers") != null) {
            JsonObject j = json.get("ingredientPositionModifiers").getAsJsonObject();
            boolean run = false;
            if (j.get("above") != null && j.get("above").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Above): " + setPlus(j.get("above").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("under") != null && j.get("under").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Under): " + setPlus(j.get("under").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("right") != null && j.get("right").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Right): " + setPlus(j.get("right").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("left") != null && j.get("left").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Left): " + setPlus(j.get("left").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("touching") != null && j.get("touching").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Touch): " + setPlus(j.get("touching").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("notTouching") != null && j.get("notTouching").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Not Touch): " + setPlus(j.get("notTouching").getAsInt()) + "%"));
                run = true;
            }
            if (run) label.add(new JLabel(" "));
        }

        //TODO USE MAP
        if (json.get("identifications") != null) {
            JsonObject j = json.get("identifications").getAsJsonObject();
            boolean run = false;

            for (int i = 0; 70 >= i; ++i) {
                Identifications id = ITEM_IDS.get(i);
                if (id.getIngName() != null && id.getIngFieldPos().equals("identifications") && j.get(id.getIngName()) != null && j.get(id.getIngName()).getAsJsonObject() != null) {
                    JsonObject jo = j.get(id.getIngName()).getAsJsonObject();
                    label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(jo.get("maximum").getAsInt()) + id.getDisplaySp()));
                    run = true;
                }
            }

            for (int i = 0; 7 >= i; ++i) {
                Identifications id = REVERSED_ITEM_IDS.get(i);
                if (id.getIngName() != null && id.getIngFieldPos().equals("identifications") && j.get(id.getIngName()) != null && j.get(id.getIngName()).getAsJsonObject() != null) {
                    JsonObject jo = j.get(id.getIngName()).getAsJsonObject();
                    label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(jo.get("maximum").getAsInt()) + id.getDisplaySp()));
                    run = true;
                }
            }

            if (run) label.add(new JLabel(" "));
        }

        if (json.get("skills") != null) {
            label.add(new JLabel("Can Use:"));
            for (int i = 0; json.get("skills").getAsJsonArray().size() > i; ++i) {
                label.add(new JLabel(json.get("skills").getAsJsonArray().get(i).getAsString()));
            }
            label.add(new JLabel(" "));
        }

        label.add(new JLabel("External Links"));

        for (JLabel jLabel : label) {
            add(jLabel);
        }

        add(dataButton);

        JLabel sortValue = new JLabel("Sort Value: " + totalValue);
        sortValue.setForeground(Color.DARK_GRAY);
        add(sortValue);
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
}
