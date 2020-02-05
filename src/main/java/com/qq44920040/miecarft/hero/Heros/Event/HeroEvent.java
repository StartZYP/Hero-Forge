//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero.Heros.Event;

import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HeroEvent implements Listener {
    public HeroEvent() {
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Data.getHero(event.getPlayer().getName());
    }

    @EventHandler
    public void PlayerKickEvent(PlayerKickEvent event) {
        Player player = event.getPlayer();
        Data.OutputPlayerData(player.getName(), Data.getHero(player.getName()));
        Data.RemoveHero(player.getName());
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Data.OutputPlayerData(player.getName(), Data.getHero(player.getName()));
        Data.RemoveHero(player.getName());
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (Data.getHero(player.getName()) == null) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Data.server_command.replace("%player%", player.getName()));
        }

    }

    @EventHandler
    public void PlayerMessage(AsyncPlayerChatEvent event) {
        Hero hero = Data.getHero(event.getPlayer().getName());
        if (hero != null) {
            event.setFormat(Data.chartitle.replace("%title%", hero.getTitle()) + event.getFormat());
        }

    }
}
