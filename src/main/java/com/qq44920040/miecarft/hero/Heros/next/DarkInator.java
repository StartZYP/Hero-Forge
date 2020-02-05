/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageModifier
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package com.qq44920040.miecarft.hero.Heros.next;

import com.qq44920040.miecarft.hero.util.Util;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;

import java.util.Iterator;

public class DarkInator
extends Hero {
    private static final PotionEffect VANISH_EFFECT;
    private static final PotionEffect JUMP_EFFECT;
    private static final PotionEffect SPEED_EFFECT;

    public DarkInator() {
        super(HeroType.DARKINATOR, Data.darkinator_title);
        this.addSkill("behindKill", Data.darkinator_behindKill_maybe, (long)Data.darkinator_behindKill_wait);
        this.addSkill("vanish", Data.darkinator_vanish_maybe, (long)Data.darkinator_vanish_wait);
    }

    public void Skill_vanish(Player player) {
        if (this.isUseSkill("vanish", player)) {
            Location lct = player.getLocation();
            int r = 5;
            double minx = lct.getX() - (double)r;
            double minz = lct.getZ() - (double)r;
            double miny = lct.getY() - (double)r;
            double maxx = lct.getX() + (double)r;
            double maxz = lct.getZ() + (double)r;
            double maxy = lct.getY() + (double)r;
            Iterator var16 = lct.getWorld().getPlayers().iterator();

            while(var16.hasNext()) {
                Player p2 = (Player)var16.next();
                Location lct2 = p2.getLocation();
                if (!p2.hasPotionEffect(PotionEffectType.INVISIBILITY) && lct2.getX() >= minx && lct2.getX() <= maxx && lct2.getZ() >= minz && lct2.getZ() <= maxz && lct2.getY() >= miny && lct2.getY() <= maxy) {
                    p2.addPotionEffect(VANISH_EFFECT);
                }
            }

            Util.particleCreate(player.getLocation(), 4.0D, Effect.valueOf("WITCH_MAGIC"));
            player.sendMessage(Data.title + Data.darkinator_vanish);
        }

    }

    public void Skill_behindKill(EntityDamageByEntityEvent event) {
        Player player = (Player)event.getDamager();
        if (this.isUseSkill("behindKill", player) && player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            player.addPotionEffect(JUMP_EFFECT);
            player.addPotionEffect(SPEED_EFFECT);
            event.setDamage(event.getDamage(EntityDamageEvent.DamageModifier.BASE) * 5.0D);
            Util.particleCreate(player.getLocation(), 4.0D, Effect.valueOf("CRIT"));
            player.sendMessage(Data.darkinator_behindKill);
        }

    }

    static {
        VANISH_EFFECT = new PotionEffect(PotionEffectType.INVISIBILITY, 200, 1);
        JUMP_EFFECT = new PotionEffect(PotionEffectType.JUMP, 2147483647, 2);
        SPEED_EFFECT = new PotionEffect(PotionEffectType.SPEED, 2147483647, 3);
    }
}

