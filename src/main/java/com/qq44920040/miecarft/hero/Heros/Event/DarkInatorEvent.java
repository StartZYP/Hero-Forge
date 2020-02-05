//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero.Heros.Event;

import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.DarkInator;
import com.qq44920040.miecarft.hero.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DarkInatorEvent implements Listener {
    public DarkInatorEvent() {
    }

    @EventHandler
    public void PlayerRightClick(PlayerInteractEvent event) {
        if (event.hasItem() && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            Player player = event.getPlayer();
            Hero hero = Data.getHero(player.getName());
            if (!(hero instanceof DarkInator) || !Util.ItemHasLore(event.getItem(), Data.darkinator_vanish_lore)) {
                return;
            }

            DarkInator di = (DarkInator)hero;
            di.Skill_vanish(player);
        }

    }

    @EventHandler
    public void PlayerDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player)event.getDamager();
            Hero hero = Data.getHero(player.getName());
            if (hero != null && hero instanceof DarkInator) {
                DarkInator di = (DarkInator)hero;
                di.Skill_behindKill(event);
            }
        }
    }
}
