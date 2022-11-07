package com.github.iTzhsnu.WynnSearcher.builder;

import javax.swing.*;
import java.awt.*;

public class SkillPoint {
    private final SkillPointPanel strength;
    private final SkillPointPanel dexterity;
    private final SkillPointPanel intelligence;
    private final SkillPointPanel defense;
    private final SkillPointPanel agility;

    public SkillPoint(JPanel p) {
        this.strength = new SkillPointPanel("Strength", 212, 120, p);
        this.dexterity = new SkillPointPanel("Dexterity", 342, 120, p);
        this.intelligence = new SkillPointPanel("Intelligence", 472, 120, p);
        this.defense = new SkillPointPanel("Defense", 602, 120, p);
        this.agility = new SkillPointPanel("Agility", 732, 120, p);
    }

    public void setSkillPoint(int originalStr, int manualStr, int originalDex, int manualDex, int originalInt, int manualInt, int originalDef, int manualDef, int originalAgi, int manualAgi) {
        strength.setValue(originalStr, manualStr);
        dexterity.setValue(originalDex, manualDex);
        intelligence.setValue(originalInt, manualInt);
        defense.setValue(originalDef, manualDef);
        agility.setValue(originalAgi, manualAgi);
    }

    public void updateSkillPoint() {
        strength.setValue();
        dexterity.setValue();
        intelligence.setValue();
        defense.setValue();
        agility.setValue();
    }

    private static class SkillPointPanel {
        private final JTextField textField = new JTextField("0");
        private final JLabel name = new JLabel();
        private final JLabel boost = new JLabel();
        private final JLabel original = new JLabel("Original: 0");
        private final JLabel manual = new JLabel("Manual: 0");

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
            pane.setBounds(x, y, 120, 85);
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
            this.name.setText(name);
            setValue(0, 0);

            this.name.setAlignmentX(Component.CENTER_ALIGNMENT);
            textField.setAlignmentX(Component.CENTER_ALIGNMENT);
            original.setAlignmentX(Component.CENTER_ALIGNMENT);
            manual.setAlignmentX(Component.CENTER_ALIGNMENT);
            boost.setAlignmentX(Component.CENTER_ALIGNMENT);

            pane.add(this.name);
            pane.add(textField);
            pane.add(original);
            pane.add(manual);
            pane.add(boost);

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
                    } else if (sp > 150) {
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
                    } else if (sp > 150) {
                        value = DEFENSE[150];
                    }
                    break;
                case "Agility":
                    if (sp > 0 && sp < 150) {
                        value = AGILITY[sp];
                    } else if (sp > 150) {
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

        public void setValue(int originalSP, int manualSP) {
            original.setText("Original: " + originalSP);
            manual.setText("Manual: " + manualSP);
            setValue();
        }

        public void setValue() {
            switch (name.getText()) {
                case "Strength":
                    boost.setText("Damage: +" + (Math.round((getSPBoost() - 1) * 1000F) / 10F) + "%");
                    break;
                case "Dexterity":
                    boost.setText("Crit: +" + (Math.round((getSPBoost() - 1) * 1000F) / 10F) + "%");
                    break;
                case "Intelligence":
                    boost.setText("SP Cost: -" + (Math.round(getSPBoost() * 10000F) / 100F) + "%");
                    break;
                case "Defense":
                    boost.setText("Resist: +" + (Math.round((getSPBoost() - 1) * 10000F) / 100F) + "%");
                    break;
                case "Agility":
                    boost.setText("Dodge: +" + (Math.round((getSPBoost() - 1) * 10000F) / 100F) + "%");
                    break;
            }
        }
    }
}
