//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero.Heros.Event;

import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.Healer;
import com.qq44920040.miecarft.hero.Heros.next.Helper;
import com.qq44920040.miecarft.hero.Heros.next.Magician;
import com.qq44920040.miecarft.hero.module.entity.SpecialProjectile;
import com.qq44920040.miecarft.hero.module.entity.SpecialProjectile.SpecialProjectileType;
import com.qq44920040.miecarft.hero.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ProjectileEvent implements Listener {
    private Map<UUID, SpecialProjectile> balls = new HashMap();
    private static final PotionEffect HELP_SLOWEST;
    private static final PotionEffect HELP_SLOW;
    private static final PotionEffect HELP_NODAMAGE;
    private static final PotionEffect BLIND;

    public ProjectileEvent() {
    }

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        boolean isLeftClick = false;
        if (!action.equals(Action.PHYSICAL)) {
            if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
                isLeftClick = true;
            }

            Hero hero;
            if ((hero = Data.getHero(player.getName())) != null) {
                switch(hero.getType()) {
                    case HEALER:
                        Healer healer = (Healer)hero;
                        if (isLeftClick && Util.ItemHasLore(item, Data.healer_Healone_lore)) {
                            this.balls.put(healer.Skill_Healone(player), new SpecialProjectile(SpecialProjectileType.HEALER_SKILL_ONE));
                        } else if (!isLeftClick && Util.ItemHasLore(item, Data.healer_HealRange_lore)) {
                            healer.Skill_HealRange(player);
                        }
                        break;
                    case HELPER:
                        Helper helper = (Helper)hero;
                        if (isLeftClick && Util.ItemHasLore(item, Data.helper_fix_lore)) {
                            this.balls.put(helper.Skill_fix(player), new SpecialProjectile(SpecialProjectileType.HELPER_SKILL_FIX));
                        } else if (!isLeftClick && Util.ItemHasLore(item, Data.helper_nodamage_lore)) {
                            this.balls.put(helper.Skill_nodamage(player), new SpecialProjectile(SpecialProjectileType.HELPER_SKILL_NODAMAGE));
                        }
                        break;
                    case MAGICIAN:
                        Magician m = (Magician)hero;
                        if (isLeftClick && item != null && item.hasItemMeta() && item.getType() != Material.AIR) {
                            this.balls.put(m.Skill_fire(player), new SpecialProjectile(SpecialProjectileType.MAGICIAN_SKILL_FIRE));
                        } else if (!isLeftClick && item != null && item.hasItemMeta() && item.getType() != Material.AIR) {
                            this.balls.put(m.Skill_ghost(player), new SpecialProjectile(SpecialProjectileType.MAGICIAN_SKILL_GHOST));
                        }
                }
            }
        }

    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void ProjectileDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Projectile && this.balls.containsKey(event.getDamager().getUniqueId())) {
            Entity damagerEntity = event.getDamager();
            SpecialProjectile sball = (SpecialProjectile)this.balls.get(damagerEntity.getUniqueId());
            event.setDamage(0.0D);
            LivingEntity entity;
            switch(sball.getType()) {
                case HEALER_SKILL_ONE:
                    if (event.getEntity() instanceof Player) {
                        Damageable d = (Damageable)event.getEntity();
                        d.setHealth(d.getMaxHealth());
                    }
                    break;
                case HELPER_SKILL_FIX:
                    if (event.getEntity() instanceof LivingEntity) {
                        entity = (LivingEntity)event.getEntity();
                        if (entity.hasPotionEffect(PotionEffectType.SLOW)) {
                            entity.removePotionEffect(PotionEffectType.SLOW);
                        }

                        entity.addPotionEffect(HELP_SLOWEST);
                    }
                    break;
                case HELPER_SKILL_NODAMAGE:
                    if (event.getEntity() instanceof LivingEntity) {
                        entity = (LivingEntity)event.getEntity();
                        if (entity.hasPotionEffect(PotionEffectType.SLOW)) {
                            entity.removePotionEffect(PotionEffectType.SLOW);
                        }

                        if (entity.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
                            entity.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        }

                        entity.addPotionEffect(HELP_SLOW);
                        entity.addPotionEffect(HELP_NODAMAGE);
                    }
                case MAGICIAN_SKILL_FIRE:
                    if (event.getEntity() instanceof LivingEntity) {
                        entity = (LivingEntity)event.getEntity();
                        entity.setFireTicks(20);
                        event.setDamage(entity.getMaxHealth() * 0.2D);
                    }
                case MAGICIAN_SKILL_GHOST:
                    if (event.getEntity() instanceof LivingEntity) {
                        entity = (LivingEntity)event.getEntity();
                        if (!entity.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                            entity.addPotionEffect(BLIND);
                        }

                        event.setDamage(entity.getMaxHealth() * 0.25D);
                    }
            }
        }

    }

    static {
        HELP_SLOWEST = new PotionEffect(PotionEffectType.SLOW, 100, 20);
        HELP_SLOW = new PotionEffect(PotionEffectType.SLOW, 100, 4);
        HELP_NODAMAGE = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4);
        BLIND = new PotionEffect(PotionEffectType.BLINDNESS, 160, 0);
    }
}
