package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemUITemplate extends JPanel {
    private final JsonObject json;
    private final List<JLabel> label = new ArrayList<>();
    private final float totalValue;
    private final boolean isCustom;

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

        if (json.get("strengthPoints") != null && json.get("strengthPoints").getAsInt() != 0) {
            label.add(new JLabel("Strength: " + setPlus(json.get("strengthPoints").getAsInt())));
        }

        if (json.get("dexterityPoints") != null && json.get("dexterityPoints").getAsInt() != 0) {
            label.add(new JLabel("Dexterity: " + setPlus(json.get("dexterityPoints").getAsInt())));
        }

        if (json.get("intelligencePoints") != null && json.get("intelligencePoints").getAsInt() != 0) {
            label.add(new JLabel("Intelligence: " + setPlus(json.get("intelligencePoints").getAsInt())));
        }

        if (json.get("defensePoints") != null && json.get("defensePoints").getAsInt() != 0) {
            label.add(new JLabel("Defense: " + setPlus(json.get("defensePoints").getAsInt())));
        }

        if (json.get("agilityPoints") != null && json.get("agilityPoints").getAsInt() != 0) {
            label.add(new JLabel("Agility: " + setPlus(json.get("agilityPoints").getAsInt())));
        }

        //TODO USE MAP
        if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
            if (json.get("healthBonus") != null && json.get("healthBonus").getAsInt() != 0) {
                label.add(new JLabel("Health Bonus: " + setPlus(json.get("healthBonus").getAsInt())));
            }
            if (json.get("healthRegenRaw") != null && json.get("healthRegenRaw").getAsInt() != 0) {
                label.add(new JLabel("Health Regen Raw: " + setPlus(json.get("healthRegenRaw").getAsInt())));
            }
            if (json.get("healthRegen") != null && json.get("healthRegen").getAsInt() != 0) {
                label.add(new JLabel("Health Regen: " + setPlus(json.get("healthRegen").getAsInt()) + "%"));
            }
            if (json.get("lifeSteal") != null && json.get("lifeSteal").getAsInt() != 0) {
                label.add(new JLabel("Life Steal: " + setPlus(json.get("lifeSteal").getAsInt()) + "/3s"));
            }
            if (json.get("manaRegen") != null && json.get("manaRegen").getAsInt() != 0) {
                label.add(new JLabel("Mana Regen: " + setPlus(json.get("manaRegen").getAsInt()) + "/5s"));
            }
            if (json.get("manaSteal") != null && json.get("manaSteal").getAsInt() != 0) {
                label.add(new JLabel("Mana Steal: " + setPlus(json.get("manaSteal").getAsInt()) + "/3s"));
            }

            if (json.get("bonusEarthDefense") != null && json.get("bonusEarthDefense").getAsInt() != 0) {
                label.add(new JLabel("Earth Defense: " + setPlus(json.get("bonusEarthDefense").getAsInt()) + "%"));
            }
            if (json.get("bonusThunderDefense") != null && json.get("bonusThunderDefense").getAsInt() != 0) {
                label.add(new JLabel("Thunder Defense: " + setPlus(json.get("bonusThunderDefense").getAsInt()) + "%"));
            }
            if (json.get("bonusWaterDefense") != null && json.get("bonusWaterDefense").getAsInt() != 0) {
                label.add(new JLabel("Water Defense: " + setPlus(json.get("bonusWaterDefense").getAsInt()) + "%"));
            }
            if (json.get("bonusFireDefense") != null && json.get("bonusFireDefense").getAsInt() != 0) {
                label.add(new JLabel("Fire Defense: " + setPlus(json.get("bonusFireDefense").getAsInt()) + "%"));
            }
            if (json.get("bonusAirDefense") != null && json.get("bonusAirDefense").getAsInt() != 0) {
                label.add(new JLabel("Air Defense: " + setPlus(json.get("bonusAirDefense").getAsInt()) + "%"));
            }

            if (json.get("earthDamageBonus") != null && json.get("earthDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Earth Damage: " + setPlus(json.get("earthDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("thunderDamageBonus") != null && json.get("thunderDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Thunder Damage: " + setPlus(json.get("thunderDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("waterDamageBonus") != null && json.get("waterDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Water Damage: " + setPlus(json.get("waterDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("fireDamageBonus") != null && json.get("fireDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Fire Damage: " + setPlus(json.get("fireDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("airDamageBonus") != null && json.get("airDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Air Damage: " + setPlus(json.get("airDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("spellDamageBonusRaw") != null && json.get("spellDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Spell Damage: " + setPlus(json.get("spellDamageBonusRaw").getAsInt())));
            }
            if (json.get("mainAttackDamageBonusRaw") != null && json.get("mainAttackDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Melee Damage: " + setPlus(json.get("mainAttackDamageBonusRaw").getAsInt())));
            }
            if (json.get("spellDamageBonus") != null && json.get("spellDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Spell Damage: " + setPlus(json.get("spellDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("mainAttackDamageBonus") != null && json.get("mainAttackDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Melee Damage: " + setPlus(json.get("mainAttackDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("poison") != null && json.get("poison").getAsInt() != 0) {
                label.add(new JLabel("Poison: " + setPlus(json.get("poison").getAsInt()) + "/3s"));
            }
            if (json.get("attackSpeedBonus") != null && json.get("attackSpeedBonus").getAsInt() != 0) {
                label.add(new JLabel("Attack Speed Bonus: " + setPlus(json.get("attackSpeedBonus").getAsInt()) + "tier"));
            }

            if (json.get("speed") != null && json.get("speed").getAsInt() != 0) {
                label.add(new JLabel("Walk Speed: " + setPlus(json.get("speed").getAsInt()) + "%"));
            }
            if (json.get("sprint") != null && json.get("sprint").getAsInt() != 0) {
                label.add(new JLabel("Sprint: " + setPlus(json.get("sprint").getAsInt()) + "%"));
            }
            if (json.get("sprintRegen") != null && json.get("sprintRegen").getAsInt() != 0) {
                label.add(new JLabel("Sprint Regen: " + setPlus(json.get("sprintRegen").getAsInt()) + "%"));
            }
            if (json.get("jumpHeight") != null && json.get("jumpHeight").getAsInt() != 0) {
                label.add(new JLabel("Jump Height: " + setPlus(json.get("jumpHeight").getAsInt())));
            }

            if (json.get("thorns") != null && json.get("thorns").getAsInt() != 0) {
                label.add(new JLabel("Thorns: " + setPlus(json.get("thorns").getAsInt()) + "%"));
            }
            if (json.get("reflection") != null && json.get("reflection").getAsInt() != 0) {
                label.add(new JLabel("Reflection: " + setPlus(json.get("reflection").getAsInt()) + "%"));
            }
            if (json.get("exploding") != null && json.get("exploding").getAsInt() != 0) {
                label.add(new JLabel("Exploding: " + setPlus(json.get("exploding").getAsInt()) + "%"));
            }
            if (json.get("emeraldStealing") != null && json.get("emeraldStealing").getAsInt() != 0) {
                label.add(new JLabel("Stealing: " + setPlus(json.get("emeraldStealing").getAsInt()) + "%"));
            }
            if (json.get("xpBonus") != null && json.get("xpBonus").getAsInt() != 0) {
                label.add(new JLabel("Combat XP Bonus: " + setPlus(json.get("xpBonus").getAsInt()) + "%"));
            }
            if (json.get("gatherXpBonus") != null && json.get("gatherXpBonus").getAsInt() != 0) {
                label.add(new JLabel("Gather XP Bonus: " + setPlus(json.get("gatherXpBonus").getAsInt()) + "%"));
            }
            if (json.get("gatherSpeed") != null && json.get("gatherSpeed").getAsInt() != 0) {
                label.add(new JLabel("Gather Speed Bonus: " + setPlus(json.get("gatherSpeed").getAsInt()) + "%"));
            }
            if (json.get("lootBonus") != null && json.get("lootBonus").getAsInt() != 0) {
                label.add(new JLabel("Loot Bonus: " + setPlus(json.get("lootBonus").getAsInt()) + "%"));
            }
            if (json.get("lootQuality") != null && json.get("lootQuality").getAsInt() != 0) {
                label.add(new JLabel("Loot Quality: " + setPlus(json.get("lootQuality").getAsInt()) + "%"));
            }
            if (json.get("soulPoints") != null && json.get("soulPoints").getAsInt() != 0) {
                label.add(new JLabel("Soul Point Regen: " + setPlus(json.get("soulPoints").getAsInt()) + "%"));
            }

            if (json.get("spellCostRaw1") != null && json.get("spellCostRaw1").getAsInt() != 0) {
                label.add(new JLabel("1st Spell Cost Raw: " + setPlus(json.get("spellCostRaw1").getAsInt())));
            }
            if (json.get("spellCostRaw2") != null && json.get("spellCostRaw2").getAsInt() != 0) {
                label.add(new JLabel("2nd Spell Cost Raw: " + setPlus(json.get("spellCostRaw2").getAsInt())));
            }
            if (json.get("spellCostRaw3") != null && json.get("spellCostRaw3").getAsInt() != 0) {
                label.add(new JLabel("3rd Spell Cost Raw: " + setPlus(json.get("spellCostRaw3").getAsInt())));
            }
            if (json.get("spellCostRaw4") != null && json.get("spellCostRaw4").getAsInt() != 0) {
                label.add(new JLabel("4th Spell Cost Raw: " + setPlus(json.get("spellCostRaw4").getAsInt())));
            }
            if (json.get("spellCostPct1") != null && json.get("spellCostPct1").getAsInt() != 0) {
                label.add(new JLabel("1st Spell Cost: " + setPlus(json.get("spellCostPct1").getAsInt()) + "%"));
            }
            if (json.get("spellCostPct2") != null && json.get("spellCostPct2").getAsInt() != 0) {
                label.add(new JLabel("2nd Spell Cost: " + setPlus(json.get("spellCostPct2").getAsInt()) + "%"));
            }
            if (json.get("spellCostPct3") != null && json.get("spellCostPct3").getAsInt() != 0) {
                label.add(new JLabel("3rd Spell Cost: " + setPlus(json.get("spellCostPct3").getAsInt()) + "%"));
            }
            if (json.get("spellCostPct4") != null && json.get("spellCostPct4").getAsInt() != 0) {
                label.add(new JLabel("4th Spell Cost: " + setPlus(json.get("spellCostPct4").getAsInt()) + "%"));
            }

            if (json.get("mainAttackNeutralDamageBonus") != null && json.get("mainAttackNeutralDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Neutral Melee Damage: " + setPlus(json.get("mainAttackNeutralDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("mainAttackEarthDamageBonus") != null && json.get("mainAttackEarthDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Earth Melee Damage: " + setPlus(json.get("mainAttackEarthDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("mainAttackThunderDamageBonus") != null && json.get("mainAttackThunderDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Thunder Melee Damage: " + setPlus(json.get("mainAttackThunderDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("mainAttackWaterDamageBonus") != null && json.get("mainAttackWaterDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Water Melee Damage: " + setPlus(json.get("mainAttackWaterDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("mainAttackFireDamageBonus") != null && json.get("mainAttackFireDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Fire Melee Damage: " + setPlus(json.get("mainAttackFireDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("mainAttackAirDamageBonus") != null && json.get("mainAttackAirDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Air Melee Damage: " + setPlus(json.get("mainAttackAirDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("mainAttackElementalDamageBonus") != null && json.get("mainAttackElementalDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Elemental Melee Damage: " + setPlus(json.get("mainAttackElementalDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("mainAttackNeutralDamageBonusRaw") != null && json.get("mainAttackNeutralDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Neutral Melee Damage: " + setPlus(json.get("mainAttackNeutralDamageBonusRaw").getAsInt())));
            }
            if (json.get("mainAttackEarthDamageBonusRaw") != null && json.get("mainAttackEarthDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Earth Melee Damage: " + setPlus(json.get("mainAttackEarthDamageBonusRaw").getAsInt())));
            }
            if (json.get("mainAttackThunderDamageBonusRaw") != null && json.get("mainAttackThunderDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Thunder Melee Damage: " + setPlus(json.get("mainAttackThunderDamageBonusRaw").getAsInt())));
            }
            if (json.get("mainAttackWaterDamageBonusRaw") != null && json.get("mainAttackWaterDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Water Melee Damage: " + setPlus(json.get("mainAttackWaterDamageBonusRaw").getAsInt())));
            }
            if (json.get("mainAttackFireDamageBonusRaw") != null && json.get("mainAttackFireDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Fire Melee Damage: " + setPlus(json.get("mainAttackFireDamageBonusRaw").getAsInt())));
            }
            if (json.get("mainAttackAirDamageBonusRaw") != null && json.get("mainAttackAirDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Air Melee Damage: " + setPlus(json.get("mainAttackAirDamageBonusRaw").getAsInt())));
            }
            if (json.get("mainAttackElementalDamageBonusRaw") != null && json.get("mainAttackElementalDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Elemental Melee Damage: " + setPlus(json.get("mainAttackElementalDamageBonusRaw").getAsInt())));
            }
            if (json.get("spellNeutralDamageBonus") != null && json.get("spellNeutralDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Neutral Spell Damage: " + setPlus(json.get("spellNeutralDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("spellEarthDamageBonus") != null && json.get("spellEarthDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Earth Spell Damage: " + setPlus(json.get("spellEarthDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("spellThunderDamageBonus") != null && json.get("spellThunderDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Thunder Spell Damage: " + setPlus(json.get("spellThunderDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("spellWaterDamageBonus") != null && json.get("spellWaterDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Water Spell Damage: " + setPlus(json.get("spellWaterDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("spellFireDamageBonus") != null && json.get("spellFireDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Fire Spell Damage: " + setPlus(json.get("spellFireDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("spellAirDamageBonus") != null && json.get("spellAirDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Air Spell Damage: " + setPlus(json.get("spellAirDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("spellElementalDamageBonus") != null && json.get("spellElementalDamageBonus").getAsInt() != 0) {
                label.add(new JLabel("Elemental Spell Damage: " + setPlus(json.get("spellElementalDamageBonus").getAsInt()) + "%"));
            }
            if (json.get("spellNeutralDamageBonusRaw") != null && json.get("spellNeutralDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Neutral Spell Damage: " + setPlus(json.get("spellNeutralDamageBonusRaw").getAsInt())));
            }
            if (json.get("spellEarthDamageBonusRaw") != null && json.get("spellEarthDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Earth Spell Damage: " + setPlus(json.get("spellEarthDamageBonusRaw").getAsInt())));
            }
            if (json.get("spellThunderDamageBonusRaw") != null && json.get("spellThunderDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Thunder Spell Damage: " + setPlus(json.get("spellThunderDamageBonusRaw").getAsInt())));
            }
            if (json.get("spellWaterDamageBonusRaw") != null && json.get("spellWaterDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Water Spell Damage: " + setPlus(json.get("spellWaterDamageBonusRaw").getAsInt())));
            }
            if (json.get("spellFireDamageBonusRaw") != null && json.get("spellFireDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Fire Spell Damage: " + setPlus(json.get("spellFireDamageBonusRaw").getAsInt())));
            }
            if (json.get("spellAirDamageBonusRaw") != null && json.get("spellAirDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Air Spell Damage: " + setPlus(json.get("spellAirDamageBonusRaw").getAsInt())));
            }
            if (json.get("spellElementalDamageBonusRaw") != null && json.get("spellElementalDamageBonusRaw").getAsInt() != 0) {
                label.add(new JLabel("Raw Elemental Spell Damage: " + setPlus(json.get("spellElementalDamageBonusRaw").getAsInt())));
            }
        } else {
            if (json.get("healthBonus") != null && json.get("healthBonus").getAsInt() != 0) {
                int base = json.get("healthBonus").getAsInt();
                label.add(new JLabel(getMin(base) + " Health Bonus " + getMax(base)));
            }
            if (json.get("healthRegenRaw") != null && json.get("healthRegenRaw").getAsInt() != 0) {
                int base = json.get("healthRegenRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Health Regen Raw " + getMax(base)));
            }
            if (json.get("healthRegen") != null && json.get("healthRegen").getAsInt() != 0) {
                int base = json.get("healthRegen").getAsInt();
                label.add(new JLabel(getMin(base) + "% Health Regen " + getMax(base) + "%"));
            }
            if (json.get("lifeSteal") != null && json.get("lifeSteal").getAsInt() != 0) {
                int base = json.get("lifeSteal").getAsInt();
                label.add(new JLabel(getMin(base) + "/3s Life Steal " + getMax(base) + "/3s"));
            }
            if (json.get("manaRegen") != null && json.get("manaRegen").getAsInt() != 0) {
                int base = json.get("manaRegen").getAsInt();
                label.add(new JLabel(getMin(base) + "/5s Mana Regen " + getMax(base) + "/5s"));
            }
            if (json.get("manaSteal") != null && json.get("manaSteal").getAsInt() != 0) {
                int base = json.get("manaSteal").getAsInt();
                label.add(new JLabel(getMin(base) + "/3s Mana Steal " + getMax(base) + "/3s"));
            }

            if (json.get("bonusEarthDefense") != null && json.get("bonusEarthDefense").getAsInt() != 0) {
                int base = json.get("bonusEarthDefense").getAsInt();
                label.add(new JLabel(getMin(base) + "% Earth Defense " + getMax(base) + "%"));
            }
            if (json.get("bonusThunderDefense") != null && json.get("bonusThunderDefense").getAsInt() != 0) {
                int base = json.get("bonusThunderDefense").getAsInt();
                label.add(new JLabel(getMin(base) + "% Thunder Defense " + getMax(base) + "%"));
            }
            if (json.get("bonusWaterDefense") != null && json.get("bonusWaterDefense").getAsInt() != 0) {
                int base = json.get("bonusWaterDefense").getAsInt();
                label.add(new JLabel(getMin(base) + "% Water Defense " + getMax(base) + "%"));
            }
            if (json.get("bonusFireDefense") != null && json.get("bonusFireDefense").getAsInt() != 0) {
                int base = json.get("bonusFireDefense").getAsInt();
                label.add(new JLabel(getMin(base) + "% Fire Defense " + getMax(base) + "%"));
            }
            if (json.get("bonusAirDefense") != null && json.get("bonusAirDefense").getAsInt() != 0) {
                int base = json.get("bonusAirDefense").getAsInt();
                label.add(new JLabel(getMin(base) + "% Air Defense " + getMax(base) + "%"));
            }

            if (json.get("earthDamageBonus") != null && json.get("earthDamageBonus").getAsInt() != 0) {
                int base = json.get("earthDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Earth Damage " + getMax(base) + "%"));
            }
            if (json.get("thunderDamageBonus") != null && json.get("thunderDamageBonus").getAsInt() != 0) {
                int base = json.get("thunderDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Thunder Damage " + getMax(base) + "%"));
            }
            if (json.get("waterDamageBonus") != null && json.get("waterDamageBonus").getAsInt() != 0) {
                int base = json.get("waterDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Water Damage " + getMax(base) + "%"));
            }
            if (json.get("fireDamageBonus") != null && json.get("fireDamageBonus").getAsInt() != 0) {
                int base = json.get("fireDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Fire Damage " + getMax(base) + "%"));
            }
            if (json.get("airDamageBonus") != null && json.get("airDamageBonus").getAsInt() != 0) {
                int base = json.get("airDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Air Damage " + getMax(base) + "%"));
            }
            if (json.get("spellDamageBonusRaw") != null && json.get("spellDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("spellDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Raw Spell Damage " + getMax(base)));
            }
            if (json.get("mainAttackDamageBonusRaw") != null && json.get("mainAttackDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("mainAttackDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Raw Melee Damage " + getMax(base)));
            }
            if (json.get("spellDamageBonus") != null && json.get("spellDamageBonus").getAsInt() != 0) {
                int base = json.get("spellDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Spell Damage " + getMax(base) + "%"));
            }
            if (json.get("mainAttackDamageBonus") != null && json.get("mainAttackDamageBonus").getAsInt() != 0) {
                int base = json.get("mainAttackDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Melee Damage " + getMax(base) + "%"));
            }
            if (json.get("poison") != null && json.get("poison").getAsInt() != 0) {
                int base = json.get("poison").getAsInt();
                label.add(new JLabel(getMin(base) + "/3s Poison " + getMax(base) + "/3s"));
            }
            if (json.get("attackSpeedBonus") != null && json.get("attackSpeedBonus").getAsInt() != 0) {
                int base = json.get("attackSpeedBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "tier Attack Speed Bonus " + getMax(base) + "tier"));
            }

            if (json.get("speed") != null && json.get("speed").getAsInt() != 0) {
                int base = json.get("speed").getAsInt();
                label.add(new JLabel(getMin(base) + "% Walk Speed " + getMax(base) + "%"));
            }
            if (json.get("sprint") != null && json.get("sprint").getAsInt() != 0) {
                int base = json.get("sprint").getAsInt();
                label.add(new JLabel(getMin(base) + "% Sprint " + getMax(base) + "%"));
            }
            if (json.get("sprintRegen") != null && json.get("sprintRegen").getAsInt() != 0) {
                int base = json.get("sprintRegen").getAsInt();
                label.add(new JLabel(getMin(base) + "% Sprint Regen " + getMax(base) + "%"));
            }
            if (json.get("jumpHeight") != null && json.get("jumpHeight").getAsInt() != 0) {
                int base = json.get("jumpHeight").getAsInt();
                label.add(new JLabel(getMin(base) + " Jump Height " + getMax(base)));
            }

            if (json.get("thorns") != null && json.get("thorns").getAsInt() != 0) {
                int base = json.get("thorns").getAsInt();
                label.add(new JLabel(getMin(base) + "% Thorns " + getMax(base) + "%"));
            }
            if (json.get("reflection") != null && json.get("reflection").getAsInt() != 0) {
                int base = json.get("reflection").getAsInt();
                label.add(new JLabel(getMin(base) + "% Reflection " + getMax(base) + "%"));
            }
            if (json.get("exploding") != null && json.get("exploding").getAsInt() != 0) {
                int base = json.get("exploding").getAsInt();
                label.add(new JLabel(getMin(base) + "% Exploding " + getMax(base) + "%"));
            }
            if (json.get("emeraldStealing") != null && json.get("emeraldStealing").getAsInt() != 0) {
                int base = json.get("emeraldStealing").getAsInt();
                label.add(new JLabel(getMin(base) + "% Stealing " + getMax(base) + "%"));
            }
            if (json.get("xpBonus") != null && json.get("xpBonus").getAsInt() != 0) {
                int base = json.get("xpBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Combat XP Bonus " + getMax(base) + "%"));
            }
            if (json.get("gatherXpBonus") != null && json.get("gatherXpBonus").getAsInt() != 0) {
                int base = json.get("gatherXpBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Gather XP Bonus " + getMax(base) + "%"));
            }
            if (json.get("gatherSpeed") != null && json.get("gatherSpeed").getAsInt() != 0) {
                int base = json.get("gatherSpeed").getAsInt();
                label.add(new JLabel(getMin(base) + "% Gather Speed " + getMax(base) + "%"));
            }
            if (json.get("lootBonus") != null && json.get("lootBonus").getAsInt() != 0) {
                int base = json.get("lootBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Loot Bonus " + getMax(base) + "%"));
            }
            if (json.get("lootQuality") != null && json.get("lootQuality").getAsInt() != 0) {
                int base = json.get("lootQuality").getAsInt();
                label.add(new JLabel(getMin(base) + "% Loot Quality " + getMax(base) + "%"));
            }
            if (json.get("soulPoints") != null && json.get("soulPoints").getAsInt() != 0) {
                int base = json.get("soulPoints").getAsInt();
                label.add(new JLabel(getMin(base) + "% Soul Point Regen " + getMax(base) + "%"));
            }

            if (json.get("spellCostRaw1") != null && json.get("spellCostRaw1").getAsInt() != 0) {
                int base = json.get("spellCostRaw1").getAsInt();
                label.add(new JLabel(getReversedMax(base) + " 1st Spell Cost Raw " + getReversedMin(base)));
            }
            if (json.get("spellCostRaw2") != null && json.get("spellCostRaw2").getAsInt() != 0) {
                int base = json.get("spellCostRaw2").getAsInt();
                label.add(new JLabel(getReversedMax(base) + " 2nd Spell Cost Raw " + getReversedMin(base)));
            }
            if (json.get("spellCostRaw3") != null && json.get("spellCostRaw3").getAsInt() != 0) {
                int base = json.get("spellCostRaw3").getAsInt();
                label.add(new JLabel(getReversedMax(base) + " 3rd Spell Cost Raw " + getReversedMin(base)));
            }
            if (json.get("spellCostRaw4") != null && json.get("spellCostRaw4").getAsInt() != 0) {
                int base = json.get("spellCostRaw4").getAsInt();
                label.add(new JLabel(getReversedMax(base) + " 4th Spell Cost Raw " + getReversedMin(base)));
            }
            if (json.get("spellCostPct1") != null && json.get("spellCostPct1").getAsInt() != 0) {
                int base = json.get("spellCostPct1").getAsInt();
                label.add(new JLabel(getReversedMax(base) + "% 1st Spell Cost " + getReversedMin(base) + "%"));
            }
            if (json.get("spellCostPct2") != null && json.get("spellCostPct2").getAsInt() != 0) {
                int base = json.get("spellCostPct2").getAsInt();
                label.add(new JLabel(getReversedMax(base) + "% 2nd Spell Cost " + getReversedMin(base) + "%"));
            }
            if (json.get("spellCostPct3") != null && json.get("spellCostPct3").getAsInt() != 0) {
                int base = json.get("spellCostPct3").getAsInt();
                label.add(new JLabel(getReversedMax(base) + "% 3rd Spell Cost " + getReversedMin(base) + "%"));
            }
            if (json.get("spellCostPct4") != null && json.get("spellCostPct4").getAsInt() != 0) {
                int base = json.get("spellCostPct4").getAsInt();
                label.add(new JLabel(getReversedMax(base) + "% 4th Spell Cost " + getReversedMin(base) + "%"));
            }

            if (json.get("mainAttackNeutralDamageBonus") != null && json.get("mainAttackNeutralDamageBonus").getAsInt() != 0) {
                int base = json.get("mainAttackNeutralDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Neutral Melee Damage " + getMax(base) + "%"));
            }
            if (json.get("mainAttackEarthDamageBonus") != null && json.get("mainAttackEarthDamageBonus").getAsInt() != 0) {
                int base = json.get("mainAttackEarthDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Earth Melee Damage " + getMax(base) + "%"));
            }
            if (json.get("mainAttackThunderDamageBonus") != null && json.get("mainAttackThunderDamageBonus").getAsInt() != 0) {
                int base = json.get("mainAttackThunderDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Thunder Melee Damage " + getMax(base) + "%"));
            }
            if (json.get("mainAttackWaterDamageBonus") != null && json.get("mainAttackWaterDamageBonus").getAsInt() != 0) {
                int base = json.get("mainAttackWaterDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Water Melee Damage " + getMax(base) + "%"));
            }
            if (json.get("mainAttackFireDamageBonus") != null && json.get("mainAttackFireDamageBonus").getAsInt() != 0) {
                int base = json.get("mainAttackFireDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Fire Melee Damage " + getMax(base) + "%"));
            }
            if (json.get("mainAttackAirDamageBonus") != null && json.get("mainAttackAirDamageBonus").getAsInt() != 0) {
                int base = json.get("mainAttackAirDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Air Melee Damage " + getMax(base) + "%"));
            }
            if (json.get("mainAttackElementalDamageBonus") != null && json.get("mainAttackElementalDamageBonus").getAsInt() != 0) {
                int base = json.get("mainAttackElementalDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Elemental Melee Damage " + getMax(base) + "%"));
            }
            if (json.get("mainAttackNeutralDamageBonusRaw") != null && json.get("mainAttackNeutralDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("mainAttackNeutralDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Raw Neutral Melee Damage " + getMax(base)));
            }
            if (json.get("mainAttackEarthDamageBonusRaw") != null && json.get("mainAttackEarthDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("mainAttackEarthDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Raw Earth Melee Damage " + getMax(base)));
            }
            if (json.get("mainAttackThunderDamageBonusRaw") != null && json.get("mainAttackThunderDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("mainAttackThunderDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Raw Thunder Melee Damage " + getMax(base)));
            }
            if (json.get("mainAttackWaterDamageBonusRaw") != null && json.get("mainAttackWaterDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("mainAttackWaterDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Raw Water Melee Damage " + getMax(base)));
            }
            if (json.get("mainAttackFireDamageBonusRaw") != null && json.get("mainAttackFireDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("mainAttackFireDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Raw Fire Melee Damage " + getMax(base)));
            }
            if (json.get("mainAttackAirDamageBonusRaw") != null && json.get("mainAttackAirDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("mainAttackAirDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Raw Air Melee Damage " + getMax(base)));
            }
            if (json.get("mainAttackElementalDamageBonusRaw") != null && json.get("mainAttackElementalDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("mainAttackElementalDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Raw Elemental Melee Damage " + getMax(base)));
            }
            if (json.get("spellNeutralDamageBonus") != null && json.get("spellNeutralDamageBonus").getAsInt() != 0) {
                int base = json.get("spellNeutralDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Neutral Spell Damage " + getMax(base) + "%"));
            }
            if (json.get("spellEarthDamageBonus") != null && json.get("spellEarthDamageBonus").getAsInt() != 0) {
                int base = json.get("spellEarthDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Earth Spell Damage " + getMax(base) + "%"));
            }
            if (json.get("spellThunderDamageBonus") != null && json.get("spellThunderDamageBonus").getAsInt() != 0) {
                int base = json.get("spellThunderDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Thunder Spell Damage " + getMax(base) + "%"));
            }
            if (json.get("spellWaterDamageBonus") != null && json.get("spellWaterDamageBonus").getAsInt() != 0) {
                int base = json.get("spellWaterDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Water Spell Damage " + getMax(base) + "%"));
            }
            if (json.get("spellFireDamageBonus") != null && json.get("spellFireDamageBonus").getAsInt() != 0) {
                int base = json.get("spellFireDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Fire Spell Damage " + getMax(base) + "%"));
            }
            if (json.get("spellAirDamageBonus") != null && json.get("spellAirDamageBonus").getAsInt() != 0) {
                int base = json.get("spellAirDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Air Spell Damage " + getMax(base) + "%"));
            }
            if (json.get("spellElementalDamageBonus") != null && json.get("spellElementalDamageBonus").getAsInt() != 0) {
                int base = json.get("spellElementalDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + "% Elemental Spell Damage " + getMax(base) + "%"));
            }
            if (json.get("spellNeutralDamageBonusRaw") != null && json.get("spellNeutralDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("spellNeutralDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Raw Neutral Spell Damage " + getMax(base)));
            }
            if (json.get("spellEarthDamageBonusRaw") != null && json.get("spellEarthDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("spellEarthDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Earth Spell Damage " + getMax(base)));
            }
            if (json.get("spellThunderDamageBonusRaw") != null && json.get("spellThunderDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("spellThunderDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Thunder Spell Damage " + getMax(base)));
            }
            if (json.get("spellWaterDamageBonus") != null && json.get("spellWaterDamageBonus").getAsInt() != 0) {
                int base = json.get("spellWaterDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + " Water Spell Damage " + getMax(base)));
            }
            if (json.get("spellFireDamageBonusRaw") != null && json.get("spellFireDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("spellFireDamageBonus").getAsInt();
                label.add(new JLabel(getMin(base) + " Fire Spell Damage " + getMax(base)));
            }
            if (json.get("spellAirDamageBonusRaw") != null && json.get("spellAirDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("spellAirDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Air Spell Damage " + getMax(base)));
            }
            if (json.get("spellElementalDamageBonusRaw") != null && json.get("spellElementalDamageBonusRaw").getAsInt() != 0) {
                int base = json.get("spellElementalDamageBonusRaw").getAsInt();
                label.add(new JLabel(getMin(base) + " Elemental Spell Damage " + getMax(base)));
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
            if (j.get("STRENGTHPOINTS") != null && j.get("STRENGTHPOINTS").getAsJsonObject() != null) {
                JsonObject jo = j.get("STRENGTHPOINTS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + " Strength " + setPlus(jo.get("maximum").getAsInt())));
                run = true;
            }
            if (j.get("DEXTERITYPOINTS") != null && j.get("DEXTERITYPOINTS").getAsJsonObject() != null) {
                JsonObject jo = j.get("DEXTERITYPOINTS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + " Dexterity " + setPlus(jo.get("maximum").getAsInt())));
                run = true;
            }
            if (j.get("INTELLIGENCEPOINTS") != null && j.get("INTELLIGENCEPOINTS").getAsJsonObject() != null) {
                JsonObject jo = j.get("INTELLIGENCEPOINTS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + " Intelligence " + setPlus(jo.get("maximum").getAsInt())));
                run = true;
            }
            if (j.get("DEFENSEPOINTS") != null && j.get("DEFENSEPOINTS").getAsJsonObject() != null) {
                JsonObject jo = j.get("DEFENSEPOINTS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + " Defense " + setPlus(jo.get("maximum").getAsInt())));
                run = true;
            }
            if (j.get("AGILITYPOINTS") != null && j.get("AGILITYPOINTS").getAsJsonObject() != null) {
                JsonObject jo = j.get("AGILITYPOINTS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + " Agility " + setPlus(jo.get("maximum").getAsInt())));
                run = true;
            }

            if (j.get("HEALTHBONUS") != null && j.get("HEALTHBONUS").getAsJsonObject() != null) {
                JsonObject jo = j.get("HEALTHBONUS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + " Health Bonus " + setPlus(jo.get("maximum").getAsInt())));
                run = true;
            }
            if (j.get("HEALTHREGENRAW") != null && j.get("HEALTHREGENRAW").getAsJsonObject() != null) {
                JsonObject jo = j.get("HEALTHREGENRAW").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + " Health Regen Raw " + setPlus(jo.get("maximum").getAsInt())));
                run = true;
            }
            if (j.get("HEALTHREGEN") != null && j.get("HEALTHREGEN").getAsJsonObject() != null) {
                JsonObject jo = j.get("HEALTHREGEN").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Health Regen " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("LIFESTEAL") != null && j.get("LIFESTEAL").getAsJsonObject() != null) {
                JsonObject jo = j.get("LIFESTEAL").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "/3s Life Steal " + setPlus(jo.get("maximum").getAsInt()) + "/3s"));
                run = true;
            }
            if (j.get("MANAREGEN") != null && j.get("MANAREGEN").getAsJsonObject() != null) {
                JsonObject jo = j.get("MANAREGEN").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "/5s Mana Regen " + setPlus(jo.get("maximum").getAsInt()) + "/5s"));
                run = true;
            }
            if (j.get("MANASTEAL") != null && j.get("MANASTEAL").getAsJsonObject() != null) {
                JsonObject jo = j.get("MANASTEAL").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "/3s Mana Steal " + setPlus(jo.get("maximum").getAsInt()) + "/3s"));
                run = true;
            }
            if (j.get("EARTHDEFENSE") != null && j.get("EARTHDEFENSE").getAsJsonObject() != null) {
                JsonObject jo = j.get("EARTHDEFENSE").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Earth Defense " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("THUNDERDEFENSE") != null && j.get("THUNDERDEFENSE").getAsJsonObject() != null) {
                JsonObject jo = j.get("THUNDERDEFENSE").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Thunder Defense " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("WATERDEFENSE") != null && j.get("WATERDEFENSE").getAsJsonObject() != null) {
                JsonObject jo = j.get("WATERDEFENSE").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Water Defense " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("FIREDEFENSE") != null && j.get("FIREDEFENSE").getAsJsonObject() != null) {
                JsonObject jo = j.get("FIREDEFENSE").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Fire Defense " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("AIRDEFENSE") != null && j.get("AIRDEFENSE").getAsJsonObject() != null) {
                JsonObject jo = j.get("AIRDEFENSE").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Air Defense " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("EARTHDAMAGEBONUS") != null && j.get("EARTHDAMAGEBONUS").getAsJsonObject() != null) {
                JsonObject jo = j.get("EARTHDAMAGEBONUS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Earth Damage " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("THUNDERDAMAGEBONUS") != null && j.get("THUNDERDAMAGEBONUS").getAsJsonObject() != null) {
                JsonObject jo = j.get("THUNDERDAMAGEBONUS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Thunder Damage " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("WATERDAMAGEBONUS") != null && j.get("WATERDAMAGEBONUS").getAsJsonObject() != null) {
                JsonObject jo = j.get("WATERDAMAGEBONUS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Water Damage " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("FIREDAMAGEBONUS") != null && j.get("FIREDAMAGEBONUS").getAsJsonObject() != null) {
                JsonObject jo = j.get("FIREDAMAGEBONUS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Fire Damage " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("AIRDAMAGEBONUS") != null && j.get("AIRDAMAGEBONUS").getAsJsonObject() != null) {
                JsonObject jo = j.get("AIRDAMAGEBONUS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Air Damage " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("SPELLDAMAGERAW") != null && j.get("SPELLDAMAGERAW").getAsJsonObject() != null) {
                JsonObject jo = j.get("SPELLDAMAGERAW").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + " Raw Spell Damage " + setPlus(jo.get("maximum").getAsInt())));
                run = true;
            }
            if (j.get("DAMAGEBONUSRAW") != null && j.get("DAMAGEBONUSRAW").getAsJsonObject() != null) {
                JsonObject jo = j.get("DAMAGEBONUSRAW").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + " Raw Melee Damage " + setPlus(jo.get("maximum").getAsInt())));
                run = true;
            }
            if (j.get("SPELLDAMAGE") != null && j.get("SPELLDAMAGE").getAsJsonObject() != null) {
                JsonObject jo = j.get("SPELLDAMAGE").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Spell Damage " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("DAMAGEBONUS") != null && j.get("DAMAGEBONUS").getAsJsonObject() != null) {
                JsonObject jo = j.get("DAMAGEBONUS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Melee Damage " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("POISON") != null && j.get("POISON").getAsJsonObject() != null) {
                JsonObject jo = j.get("POISON").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "/3s Poison " + setPlus(jo.get("maximum").getAsInt()) + "/3s"));
                run = true;
            }
            if (j.get("ATTACKSPEED") != null && j.get("ATTACKSPEED").getAsJsonObject() != null) {
                JsonObject jo = j.get("ATTACKSPEED").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "tier Attack Speed " + setPlus(jo.get("maximum").getAsInt()) + "tier"));
                run = true;
            }
            if (j.get("SPEED") != null && j.get("SPEED").getAsJsonObject() != null) {
                JsonObject jo = j.get("SPEED").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Walk Speed " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("STAMINA") != null && j.get("STAMINA").getAsJsonObject() != null) {
                JsonObject jo = j.get("STAMINA").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Sprint " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("STAMINA_REGEN") != null && j.get("STAMINA_REGEN").getAsJsonObject() != null) {
                JsonObject jo = j.get("STAMINA_REGEN").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Sprint Regen " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("JUMP_HEIGHT") != null && j.get("JUMP_HEIGHT").getAsJsonObject() != null) {
                JsonObject jo = j.get("JUMP_HEIGHT").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + " Jump Height " + setPlus(jo.get("maximum").getAsInt())));
                run = true;
            }
            if (j.get("THORNS") != null && j.get("THORNS").getAsJsonObject() != null) {
                JsonObject jo = j.get("THORNS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Thorns " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("REFLECTION") != null && j.get("REFLECTION").getAsJsonObject() != null) {
                JsonObject jo = j.get("REFLECTION").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Reflection " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("EXPLODING") != null && j.get("EXPLODING").getAsJsonObject() != null) {
                JsonObject jo = j.get("EXPLODING").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Exploding " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("EMERALDSTEALING") != null && j.get("EMERALDSTEALING").getAsJsonObject() != null) {
                JsonObject jo = j.get("EMERALDSTEALING").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Stealing " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("XPBONUS") != null && j.get("XPBONUS").getAsJsonObject() != null) {
                JsonObject jo = j.get("XPBONUS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Combat XP Bonus " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("GATHER_XP_BONUS") != null && j.get("GATHER_XP_BONUS").getAsJsonObject() != null) {
                JsonObject jo = j.get("GATHER_XP_BONUS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Gather XP Bonus " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("GATHER_SPEED") != null && j.get("GATHER_SPEED").getAsJsonObject() != null) {
                JsonObject jo = j.get("GATHER_SPEED").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Gather Speed " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("LOOTBONUS") != null && j.get("LOOTBONUS").getAsJsonObject() != null) {
                JsonObject jo = j.get("LOOTBONUS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Loot Bonus " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("LOOT_QUALITY") != null && j.get("LOOT_QUALITY").getAsJsonObject() != null) {
                JsonObject jo = j.get("LOOT_QUALITY").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Loot Quality " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("SOULPOINTS") != null && j.get("SOULPOINTS").getAsJsonObject() != null) {
                JsonObject jo = j.get("SOULPOINTS").getAsJsonObject();
                label.add(new JLabel(setPlus(jo.get("minimum").getAsInt()) + "% Soul Point Regen " + setPlus(jo.get("maximum").getAsInt()) + "%"));
                run = true;
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
