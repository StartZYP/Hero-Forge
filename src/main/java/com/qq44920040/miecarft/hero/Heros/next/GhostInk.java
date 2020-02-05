/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package com.qq44920040.miecarft.hero.Heros.next;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;

public class GhostInk
extends Hero {
    private static final PotionEffect SPEED_FOUR;
    private static final PotionEffect DAMAGERESISTANCE_FOUR;
    private static final PotionEffect HEAL_FOUR;
    private static final PotionEffect WEAKNESS_FOUR;
    public static final PotionEffect SLOW_TWO;
    public static final PotionEffect POISON_THREE;

    public GhostInk() {
        super(HeroType.GHOSTINK, Data.ghostInk_title);
        this.addSkill("effect", Data.ghostInk_effect_maybe, (long)Data.ghostInk_effect_wait);
    }

    public void skillEffect(Player player) {
        if (this.isUseSkill("effect", player)) {
            addPotionEffect(player, SPEED_FOUR);
            addPotionEffect(player, DAMAGERESISTANCE_FOUR);
            addPotionEffect(player, HEAL_FOUR);
            addPotionEffect(player, WEAKNESS_FOUR);
            player.sendMessage(Data.title + Data.ghostInk_effect);
        }

    }

    public static void addPotionEffect(LivingEntity le, PotionEffect pe) {
        if (le.hasPotionEffect(pe.getType())) {
            le.removePotionEffect(pe.getType());
        }

        le.addPotionEffect(pe);
    }

    static {
        SPEED_FOUR = new PotionEffect(PotionEffectType.SPEED, 100, 3);
        DAMAGERESISTANCE_FOUR = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 3);
        HEAL_FOUR = new PotionEffect(PotionEffectType.HEAL, 100, 3);
        WEAKNESS_FOUR = new PotionEffect(PotionEffectType.WEAKNESS, 100, 3);
        SLOW_TWO = new PotionEffect(PotionEffectType.SLOW, 80, 1);
        POISON_THREE = new PotionEffect(PotionEffectType.POISON, 80, 1);
    }
}

