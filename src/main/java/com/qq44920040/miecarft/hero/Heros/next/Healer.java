//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero.Heros.next;

import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.util.Util;
import java.util.Iterator;
import java.util.UUID;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Healer extends Hero {
    private static final PotionEffect HEALER_DAMAGE_RESISTANCE;
    private static final PotionEffect HEALER_HEAL;

    public Healer() {
        super(HeroType.HEALER, Data.healer_title);
        this.addSkill("Healone", Data.healer_Healone_maybe, (long)Data.healer_Healone_wait);
        this.addSkill("HealRange", Data.healer_HealRange_maybe, (long)Data.healer_HealRange_wait);
    }

    public void Skill_HealRange(Player player) {
        if (this.isUseSkill("HealRange", player)) {
            Location lct = player.getLocation();
            player.setHealth(0.0D + player.getMaxHealth());
            int r = 6;
            double minx = lct.getX() - (double)r;
            double minz = lct.getZ() - (double)r;
            double miny = lct.getY() - (double)r;
            double maxx = lct.getX() + (double)r;
            double maxz = lct.getZ() + (double)r;
            double maxy = lct.getY() + (double)r;
            Iterator var16 = lct.getWorld().getPlayers().iterator();
            Util.particleCreate(player.getLocation(), 4.0D, Effect.valueOf("POTION_BREAK"));

            while(var16.hasNext()) {
                Player p2 = (Player)var16.next();
                System.out.println(p2.getName());
                Location lct2 = p2.getLocation();
                if (lct2.getX() >= minx && lct2.getX() <= maxx && lct2.getZ() >= minz && lct2.getZ() <= maxz && lct2.getY() >= miny && lct2.getY() <= maxy) {
                    p2.addPotionEffect(HEALER_DAMAGE_RESISTANCE);
                }
            }

            player.sendMessage(Data.title + Data.healer_HealRange);
        }

    }

    public UUID Skill_Healone(Player healer) {
        if (this.isUseSkill("Healone", healer)) {
            Location lct = healer.getLocation();
            int r = 6;
            double minx = lct.getX() - (double)r;
            double minz = lct.getZ() - (double)r;
            double miny = lct.getY() - (double)r;
            double maxx = lct.getX() + (double)r;
            double maxz = lct.getZ() + (double)r;
            double maxy = lct.getY() + (double)r;
            Iterator var16 = lct.getWorld().getPlayers().iterator();
            Util.particleCreate(healer.getLocation(), 4.0D, Effect.valueOf("POTION_BREAK"));

            while(var16.hasNext()) {
                Player p2 = (Player)var16.next();
                Location lct2 = p2.getLocation();
                System.out.println(p2.getName());
                if (lct2.getX() >= minx && lct2.getX() <= maxx && lct2.getZ() >= minz && lct2.getZ() <= maxz && lct2.getY() >= miny && lct2.getY() <= maxy) {
                    p2.addPotionEffect(HEALER_HEAL);
                }
            }

            healer.sendMessage(Data.title + Data.healer_Healone);
            return healer.getUniqueId();
        } else {
            return null;
        }
    }

    static {
        HEALER_DAMAGE_RESISTANCE = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 5);
        HEALER_HEAL = new PotionEffect(PotionEffectType.HEAL, 60, 15);
    }
}
