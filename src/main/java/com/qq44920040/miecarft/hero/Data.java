//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero;

import com.google.common.collect.Maps;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.Hero.HeroType;
import com.qq44920040.miecarft.hero.Heros.next.DarkInator;
import com.qq44920040.miecarft.hero.Heros.next.GhostInk;
import com.qq44920040.miecarft.hero.Heros.next.Healer;
import com.qq44920040.miecarft.hero.Heros.next.Helper;
import com.qq44920040.miecarft.hero.Heros.next.Magician;
import com.qq44920040.miecarft.hero.Heros.next.Shooter;
import com.qq44920040.miecarft.hero.Heros.next.Tank;
import com.qq44920040.miecarft.hero.Heros.next.Warrior;
import java.io.File;
import java.util.Map;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class Data {
    private static final File data = new File("plugins/Hero/Data/");
    private static Map<String, Hero> heros = Maps.newHashMap();
    public static String server_command;
    public static String title;
    public static String Wait;
    public static String chartitle;
    public static String tank_hard;
    public static int tank_hard_maybe;
    public static int tank_hard_wait;
    public static String tank_title;
    public static String warrior_crit;
    public static String warrior_fly;
    public static int warrior_crit_maybe;
    public static int warrior_fly_maybe;
    public static int warrior_crit_wait;
    public static int warrior_fly_wait;
    public static String warrior_title;
    public static String healer_Healone;
    public static String healer_HealRange;
    public static String healer_Healone_lore;
    public static String healer_HealRange_lore;
    public static int healer_Healone_maybe;
    public static int healer_HealRange_maybe;
    public static int healer_Healone_wait;
    public static int healer_HealRange_wait;
    public static String healer_title;
    public static String darkinator_behindKill;
    public static String darkinator_vanish;
    public static int darkinator_behindKill_maybe;
    public static int darkinator_vanish_maybe;
    public static int darkinator_behindKill_wait;
    public static int darkinator_vanish_wait;
    public static String darkinator_vanish_lore;
    public static String darkinator_title;
    public static String helper_fix;
    public static String helper_nodamage;
    public static String helper_fix_lore;
    public static String helper_nodamage_lore;
    public static int helper_fix_maybe;
    public static int helper_nodamage_maybe;
    public static int helper_fix_wait;
    public static int helper_nodamage_wait;
    public static String helper_title;
    public static String magician_fire;
    public static String magician_ghost;
    public static String magician_fire_lore;
    public static String magician_ghost_lore;
    public static int magician_fire_maybe;
    public static int magician_ghost_maybe;
    public static int magician_fire_wait;
    public static int magician_ghost_wait;
    public static String magician_title;
    public static String shooter_crit;
    public static String shooter_arrows;
    public static int shooter_crit_maybe;
    public static int shooter_crit_wait;
    public static int shooter_arrows_maybe;
    public static int shooter_arrows_wait;
    public static String shooter_title;
    public static String ghostInk_title;
    public static String ghostInk_effect;
    public static String ghostInk_effect_lore;
    public static int ghostInk_effect_maybe;
    public static int ghostInk_effect_wait;

    public Data() {
    }

    public static void clear() {
        heros = Maps.newHashMap();
    }

    public static boolean addHero(String name, HeroType type) {
        switch(type) {
            case TANK:
                heros.put(name, new Tank());
                return true;
            case WARRIOR:
                heros.put(name, new Warrior());
                return true;
            case HEALER:
                heros.put(name, new Healer());
                return true;
            case SHOOTER:
                heros.put(name, new Shooter());
                return true;
            case HELPER:
                heros.put(name, new Helper());
                return true;
            case DARKINATOR:
                heros.put(name, new DarkInator());
                return true;
            case MAGICIAN:
                heros.put(name, new Magician());
                return true;
            case GHOSTINK:
                heros.put(name, new GhostInk());
                return true;
            default:
                return false;
        }
    }

    public static Hero getHero(String name) {
        if (!heros.containsKey(name)) {
            InputPlayerData(name);
            if (!heros.containsKey(name)) {
                return null;
            }
        }

        return (Hero)heros.get(name);
    }

    public static void OutputPlayerData(String name, Hero hero) {
        if (hero != null) {
            if (!data.exists()) {
                data.mkdirs();
            }

            FileConfiguration pdf;
            File pd;
            if ((pdf = getFileConfiguration(pd = new File(data, name + ".yml"))) != null) {
                pdf.set("type", hero.getType().toString());
                pdf.set("level", hero.getLevel());

                try {
                    pdf.save(pd);
                } catch (Exception var5) {
                    var5.printStackTrace();
                }

            }
        }
    }

    public static void InputPlayerData(String name) {
        File pd = new File(data, name + ".yml");
        if (pd.exists()) {
            FileConfiguration pdf = getFileConfiguration(pd);
            if (pdf != null) {
                try {
                    addHero(name, HeroType.valueOf(pdf.getString("type")));
                    getHero(name).setLevel(pdf.getInt("level"));
                } catch (Exception var4) {
                    pd.delete();
                }
            }
        }
    }

    public static void RemoveHero(String name) {
        heros.remove(name);
    }

    private static FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }
}
