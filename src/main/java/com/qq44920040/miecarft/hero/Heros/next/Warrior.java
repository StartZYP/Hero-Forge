/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.util.Vector
 */
package com.qq44920040.miecarft.hero.Heros.next;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.qq44920040.miecarft.hero.util.Util;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;

public class Warrior
extends Hero {
    public Warrior() {
        super(HeroType.WARRIOR, Data.warrior_title);
        this.addSkill("crit", Data.warrior_crit_maybe, (long)Data.warrior_crit_wait);
        this.addSkill("fly", Data.warrior_fly_maybe, (long)Data.warrior_fly_wait);
    }

    public void Skill_crit(EntityDamageByEntityEvent event) {
        Player player = (Player)event.getDamager();
        if (this.isUseSkill("crit", player)) {
            event.setDamage(event.getFinalDamage() * 2.0D);
            Util.particleCreate(player.getLocation(), 7.0D, Effect.valueOf("CRIT"));
            player.sendMessage(Data.title + Data.warrior_crit);
        }

    }

    public void Skill_fly(EntityDamageByEntityEvent event) {
        Player player = (Player)event.getDamager();
        if (this.isUseSkill("fly", player)) {
            UUID uuid = player.getUniqueId();
            Location lct = event.getEntity().getLocation();
            int length = (int)(Math.random() * 3.0D + 3.0D);
            Iterator var6 = lct.getWorld().getEntities().iterator();
            Util.particleCreate(player.getLocation(), 7.0D, Effect.valueOf("EXPLOSION"));

            while(var6.hasNext()) {
                Entity entity = (Entity)var6.next();
                Location elct = entity.getLocation();
                double cy = Math.abs(elct.getY() - lct.getY());
                double xy = Math.abs(elct.getX() - lct.getX());
                double zy = Math.abs(elct.getZ() - lct.getZ());
                if (entity.getUniqueId() != uuid && cy < 4.0D && xy <= 4.0D && zy <= 4.0D) {
                    entity.setVelocity(entity.getVelocity().setY(0.3D).multiply(length));
                }
            }

            ((Player)event.getDamager()).sendMessage(Data.title + Data.warrior_fly);
        }

    }
}

