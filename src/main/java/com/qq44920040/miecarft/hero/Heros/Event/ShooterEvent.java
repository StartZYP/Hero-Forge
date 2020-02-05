//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero.Heros.Event;

import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.HeroPlugin;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.Shooter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ShooterEvent implements Listener {
    public static final Map<UUID, Double> map = new HashMap();

    public ShooterEvent() {
        (new BukkitRunnable() {
            public void run() {
                Iterator var1 = Bukkit.getWorlds().iterator();

                while(var1.hasNext()) {
                    World world = (World)var1.next();
                    Iterator var3 = world.getEntities().iterator();

                    while(var3.hasNext()) {
                        Entity entity = (Entity)var3.next();
                        if (ShooterEvent.map.containsKey(entity.getUniqueId())) {
                            entity.remove();
                            ShooterEvent.map.remove(entity.getUniqueId());
                        }
                    }
                }

            }
        }).runTaskTimer(HeroPlugin.getPlugin(), 0L, 1200L);
    }

    @EventHandler
    public void PlayerPickEvent(PlayerPickupItemEvent event) {
        if (map.containsKey(event.getItem().getUniqueId())) {
            event.setCancelled(true);
            map.remove(event.getItem().getUniqueId());
            event.getItem().remove();
        }

    }

    @EventHandler(
            priority = EventPriority.LOW
    )
    public void PlayerDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (map.containsKey(event.getDamager().getUniqueId())) {
            event.setDamage(map.get(event.getDamager().getUniqueId()));
            map.remove(event.getDamager().getUniqueId());
        } else {
            Hero hero;
            Player player;
            Arrow arrow;
            if (event.getDamager() instanceof Arrow && (arrow = (Arrow)event.getDamager()).getShooter() instanceof Player && (hero = Data.getHero((player = (Player)arrow.getShooter()).getName())) != null && hero instanceof Shooter) {
                Shooter shooter = (Shooter)hero;
                event.setDamage(shooter.Skill_crit(player, event.getDamage()));
                shooter.Skill_arrows(player, event.getDamage());
            }

        }
    }
}
