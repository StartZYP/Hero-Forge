/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Projectile
 *  org.bukkit.util.Vector
 */
package com.qq44920040.miecarft.hero.Heros.next;

import java.util.Map;
import java.util.UUID;

import com.qq44920040.miecarft.hero.util.Util;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Event.ShooterEvent;
import com.qq44920040.miecarft.hero.Heros.Hero;

public class Shooter
extends Hero {
    private static final PotionEffect JUMP_EFFECT;
    private static final PotionEffect SPEED_EFFECT;
    private static final PotionEffect NIGHT_VISION;

    public Shooter() {
        super(HeroType.SHOOTER, Data.shooter_title);
        this.addSkill("crit", Data.shooter_crit_maybe, (long)Data.shooter_crit_wait);
        this.addSkill("arrows", Data.shooter_arrows_maybe, (long)Data.shooter_arrows_wait);
    }

    public double Skill_crit(Player player, double damage) {
        if (this.isUseSkill("crit", player)) {
            player.sendMessage(Data.title + Data.shooter_crit);
            return damage * 2.0D;
        } else {
            return damage;
        }
    }

    public void Skill_arrows(Player player, double damage) {
        if (this.isUseSkill("arrows", player)) {
            player.addPotionEffect(JUMP_EFFECT);
            player.addPotionEffect(SPEED_EFFECT);
            player.addPotionEffect(NIGHT_VISION);
            int number = (int)(Math.random() * 10.0D) + 5;

            for(int i = 0; i < number; ++i) {
                Arrow arrow = player.launchProjectile(Arrow.class);
                Vector vector = player.getLocation().getDirection().multiply(5);
                vector.setX(vector.getX() + Math.random() * 4.0D - Math.random() * 4.0D);
                vector.setY(vector.getY() + Math.random() * 2.0D - Math.random() * 1.0D);
                vector.setZ(vector.getZ() + Math.random() * 4.0D - Math.random() * 4.0D);
                arrow.setVelocity(vector);
                ShooterEvent.map.put(arrow.getUniqueId(), damage);
            }

            Util.particleCreate(player.getLocation(), 4.0D, Effect.valueOf("INSTANT_SPELL"));
            player.sendMessage(Data.title + Data.shooter_arrows);
        }

    }

    static {
        JUMP_EFFECT = new PotionEffect(PotionEffectType.JUMP, 2147483647, 2);
        SPEED_EFFECT = new PotionEffect(PotionEffectType.SPEED, 2147483647, 3);
        NIGHT_VISION = new PotionEffect(PotionEffectType.NIGHT_VISION, 2147483647, 1);
    }
}

