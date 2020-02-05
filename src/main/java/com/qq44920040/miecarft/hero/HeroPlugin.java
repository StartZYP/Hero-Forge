//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero;

import com.qq44920040.miecarft.hero.Commands.HeroCommand;
import com.qq44920040.miecarft.hero.Heros.Event.DarkInatorEvent;
import com.qq44920040.miecarft.hero.Heros.Event.GhostInkEvent;
import com.qq44920040.miecarft.hero.Heros.Event.HeroEvent;
import com.qq44920040.miecarft.hero.Heros.Event.ProjectileEvent;
import com.qq44920040.miecarft.hero.Heros.Event.ShooterEvent;
import com.qq44920040.miecarft.hero.Heros.Event.TankEvent;
import com.qq44920040.miecarft.hero.Heros.Event.WarriorEvent;
import java.io.File;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HeroPlugin extends JavaPlugin {
    private File config = new File(this.getDataFolder(), "config.yml");
    public static HeroPlugin plugin;

    public HeroPlugin() {
    }

    public void onEnable() {
        this.Config();
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new HeroEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DarkInatorEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ShooterEvent(), this);
        Bukkit.getPluginManager().registerEvents(new TankEvent(), this);
        Bukkit.getPluginManager().registerEvents(new WarriorEvent(), this);
        Bukkit.getPluginManager().registerEvents(new GhostInkEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ProjectileEvent(), this);
        this.getServer().getPluginCommand("hp").setExecutor(new HeroCommand());
    }

    public void onDisable() {
        Iterator var1 = Bukkit.getWorlds().iterator();

        while(var1.hasNext()) {
            World world = (World)var1.next();
            Iterator var3 = world.getPlayers().iterator();

            while(var3.hasNext()) {
                Player player = (Player)var3.next();
                Data.OutputPlayerData(player.getName(), Data.getHero(player.getName()));
            }
        }

    }

    public void Config() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }

        if (!this.config.exists()) {
            this.saveDefaultConfig();
        }

        Data.title = this.getConfig().getString("title").replace('&', '§');
        Data.tank_hard = this.getConfig().getString("tank.skills.hard.message").replace('&', '§');
        Data.tank_hard_maybe = this.getConfig().getInt("tank.skills.hard.maybe");
        Data.tank_hard_wait = this.getConfig().getInt("tank.skills.hard.wait");
        Data.tank_title = this.getConfig().getString("tank.title").replace('&', '§');
        Data.warrior_crit = this.getConfig().getString("warrior.skills.crit.message").replace('&', '§');
        Data.warrior_fly = this.getConfig().getString("warrior.skills.fly.message").replace('&', '§');
        Data.warrior_crit_maybe = this.getConfig().getInt("warrior.skills.crit.maybe");
        Data.warrior_fly_maybe = this.getConfig().getInt("warrior.skills.fly.maybe");
        Data.warrior_crit_wait = this.getConfig().getInt("warrior.skills.crit.wait");
        Data.warrior_fly_wait = this.getConfig().getInt("warrior.skills.fly.wait");
        Data.warrior_title = this.getConfig().getString("warrior.title").replace('&', '§');
        Data.healer_Healone = this.getConfig().getString("healer.skills.healone.message").replace('&', '§');
        Data.healer_HealRange = this.getConfig().getString("healer.skills.healRange.message").replace('&', '§');
        Data.healer_Healone_maybe = this.getConfig().getInt("healer.skills.healone.maybe");
        Data.healer_HealRange_maybe = this.getConfig().getInt("healer.skills.healRange.maybe");
        Data.healer_Healone_wait = this.getConfig().getInt("healer.skills.healone.wait");
        Data.healer_HealRange_wait = this.getConfig().getInt("healer.skills.healRange.wait");
        Data.healer_Healone_lore = this.getConfig().getString("healer.skills.healone.lore").replace('&', '§');
        Data.healer_HealRange_lore = this.getConfig().getString("healer.skills.healRange.lore").replace('&', '§');
        Data.healer_title = this.getConfig().getString("healer.title").replace('&', '§');
        Data.darkinator_behindKill = this.getConfig().getString("darkinator.skills.behindKill.message").replace('&', '§');
        Data.darkinator_vanish = this.getConfig().getString("darkinator.skills.vanish.message").replace('&', '§');
        Data.darkinator_behindKill_maybe = this.getConfig().getInt("darkinator.skills.behindKill.maybe");
        Data.darkinator_vanish_maybe = this.getConfig().getInt("darkinator.skills.vanish.maybe");
        Data.darkinator_behindKill_wait = this.getConfig().getInt("darkinator.skills.behindKill.wait");
        Data.darkinator_vanish_wait = this.getConfig().getInt("darkinator.skills.vanish.wait");
        Data.darkinator_vanish_lore = this.getConfig().getString("darkinator.skills.vanish.lore").replace('&', '§');
        Data.darkinator_title = this.getConfig().getString("darkinator.title").replace('&', '§');
        Data.helper_fix = this.getConfig().getString("helper.skills.fix.message").replace('&', '§');
        Data.helper_nodamage = this.getConfig().getString("helper.skills.nodamage.message").replace('&', '§');
        Data.helper_fix_lore = this.getConfig().getString("helper.skills.fix.lore").replace('&', '§');
        Data.helper_nodamage_lore = this.getConfig().getString("helper.skills.nodamage.lore").replace('&', '§');
        Data.helper_fix_maybe = this.getConfig().getInt("helper.skills.fix.maybe");
        Data.helper_nodamage_maybe = this.getConfig().getInt("helper.skills.nodamage.maybe");
        Data.helper_fix_wait = this.getConfig().getInt("helper.skills.fix.wait");
        Data.helper_nodamage_wait = this.getConfig().getInt("helper.skills.nodamage.wait");
        Data.helper_title = this.getConfig().getString("helper.title").replace('&', '§');
        Data.magician_fire = this.getConfig().getString("magician.skills.fire.message").replace('&', '§');
        Data.magician_ghost = this.getConfig().getString("magician.skills.ghost.message").replace('&', '§');
        Data.magician_fire_lore = this.getConfig().getString("magician.skills.fire.lore").replace('&', '§');
        Data.magician_ghost_lore = this.getConfig().getString("magician.skills.ghost.lore").replace('&', '§');
        Data.magician_fire_maybe = this.getConfig().getInt("magician.skills.fire.maybe");
        Data.magician_ghost_maybe = this.getConfig().getInt("magician.skills.ghost.maybe");
        Data.magician_fire_wait = this.getConfig().getInt("magician.skills.fire.wait");
        Data.magician_ghost_wait = this.getConfig().getInt("magician.skills.ghost.wait");
        Data.magician_title = this.getConfig().getString("magician.title").replace('&', '§');
        Data.shooter_crit = this.getConfig().getString("shooter.skills.crit.message").replace('&', '§');
        Data.shooter_arrows = this.getConfig().getString("shooter.skills.arrows.message").replace('&', '§');
        Data.shooter_crit_maybe = this.getConfig().getInt("shooter.skills.crit.maybe");
        Data.shooter_arrows_maybe = this.getConfig().getInt("shooter.skills.arrows.maybe");
        Data.shooter_crit_wait = this.getConfig().getInt("shooter.skills.crit.wait");
        Data.shooter_arrows_wait = this.getConfig().getInt("shooter.skills.arrows.wait");
        Data.shooter_title = this.getConfig().getString("shooter.title").replace('&', '§');
        Data.ghostInk_effect = this.getConfig().getString("ghostInk.skills.effect.message").replace('&', '§');
        Data.ghostInk_effect_lore = this.getConfig().getString("ghostInk.skills.effect.lore").replace('&', '§');
        Data.ghostInk_effect_maybe = this.getConfig().getInt("ghostInk.skills.effect.maybe");
        Data.ghostInk_effect_wait = this.getConfig().getInt("ghostInk.skills.effect.wait");
        Data.ghostInk_title = this.getConfig().getString("ghostInk.title").replace('&', '§');
        Data.server_command = this.getConfig().getString("server_command").replace('&', '§');
        Data.Wait = this.getConfig().getString("wait").replace('&', '§');
        Data.chartitle = this.getConfig().getString("chartitle").replace('&', '§');
        this.onDisable();
        Data.clear();
        Iterator var1 = Bukkit.getWorlds().iterator();

        while(var1.hasNext()) {
            World world = (World)var1.next();
            Iterator var3 = world.getPlayers().iterator();

            while(var3.hasNext()) {
                Player player = (Player)var3.next();
                Data.InputPlayerData(player.getName());
            }
        }

    }

    public static HeroPlugin getPlugin() {
        return plugin;
    }
}
