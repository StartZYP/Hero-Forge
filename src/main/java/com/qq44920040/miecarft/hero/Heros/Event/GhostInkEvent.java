//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero.Heros.Event;

import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.GhostInk;
import com.qq44920040.miecarft.hero.util.Util;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GhostInkEvent implements Listener {
    public GhostInkEvent() {
    }

    @EventHandler
    public void PlayerRightClick(PlayerInteractEvent event) {
        Player player;
        Hero hero;
        if (event.hasItem() && (hero = Data.getHero((player = event.getPlayer()).getName())) instanceof GhostInk) {
            GhostInk m = (GhostInk)hero;
            if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && Util.ItemHasLore(player.getItemInHand(), Data.ghostInk_effect_lore)) {
                m.skillEffect(player);
            }
        }

    }

    @EventHandler(
            priority = EventPriority.LOW
    )
    public void PlayerDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player)event.getDamager();
            Hero hero = Data.getHero(player.getName());
            if (hero instanceof GhostInk && event.getEntity() instanceof LivingEntity) {
                LivingEntity le = (LivingEntity)event.getEntity();
                GhostInk.addPotionEffect(le, GhostInk.SLOW_TWO);
                GhostInk.addPotionEffect(le, GhostInk.POISON_THREE);
            }

        }
    }
}
