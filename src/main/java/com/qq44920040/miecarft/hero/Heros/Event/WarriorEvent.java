/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 */
package com.qq44920040.miecarft.hero.Heros.Event;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.Warrior;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WarriorEvent
implements Listener {
    private static final PotionEffect JUMP_EFFECT;
    private static final PotionEffect SPEED_EFFECT;

    public WarriorEvent() {
    }

    @EventHandler
    public void PlayerDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && !event.isCancelled()) {
            Player player = (Player)event.getDamager();
            Hero hero = Data.getHero(player.getName());
            if (hero instanceof Warrior) {
                Warrior warrior = (Warrior)hero;
                warrior.Skill_crit(event);
                warrior.Skill_fly(event);
                player.addPotionEffect(JUMP_EFFECT);
                player.addPotionEffect(SPEED_EFFECT);
            }

        }
    }

    static {
        JUMP_EFFECT = new PotionEffect(PotionEffectType.JUMP, 2147483647, 2);
        SPEED_EFFECT = new PotionEffect(PotionEffectType.SPEED, 2147483647, 2);
    }
}

