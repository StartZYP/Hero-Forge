//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero.Commands;

import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.HeroPlugin;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.Hero.HeroType;
import com.qq44920040.miecarft.hero.Heros.next.Shooter;
import com.qq44920040.miecarft.hero.Heros.next.Warrior;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HeroCommand implements CommandExecutor {
    private static final PotionEffect JUMP_EFFECT;
    private static final PotionEffect SPEED_EFFECT;
    private static final PotionEffect NIGHT_VISION_EFFECT;

    public HeroCommand() {
    }

    public boolean onCommand(CommandSender sender, Command com, String label, String[] args) {
        if (label.equalsIgnoreCase("hp")) {
            if (args.length < 1) {
                return Help(sender);
            } else if (args[0].equalsIgnoreCase("sl") && sender instanceof Player && args.length > 1 && sender.hasPermission("hero.commands.sl")) {
                Player player = (Player)sender;
                Iterator var6 = player.getActivePotionEffects().iterator();

                while(var6.hasNext()) {
                    PotionEffect potionEffect = (PotionEffect)var6.next();
                    player.removePotionEffect(potionEffect.getType());
                }

                boolean b = select(player, args);
                if (Data.getHero(player.getName()) instanceof Shooter) {
                    player.sendMessage("Shooter属性载入");
                    player.addPotionEffect(JUMP_EFFECT);
                    player.addPotionEffect(SPEED_EFFECT);
                    player.addPotionEffect(NIGHT_VISION_EFFECT);
                } else if (Data.getHero(player.getName()) instanceof Warrior) {
                    player.sendMessage("Warrior属性载入");
                    player.addPotionEffect(JUMP_EFFECT);
                    player.addPotionEffect(SPEED_EFFECT);
                }

                return b;
            } else if (args[0].equalsIgnoreCase("look") && sender instanceof Player && sender.hasPermission("hero.commands.look")) {
                return look((Player)sender, args);
            } else if (args[0].equalsIgnoreCase("change") && sender.isOp() && args.length > 2) {
                return change(sender, args);
            } else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("hero.commands.reload")) {
                return reload(sender);
            } else {
                return args[0].equalsIgnoreCase("itemLook") && sender.hasPermission("hero.commands.itemlook") && sender instanceof Player ? itemLook((Player)sender) : Help(sender);
            }
        } else {
            return false;
        }
    }

    private static boolean itemLook(Player player) {
        ItemStack item = player.getItemInHand();
        if (item != null && item.getItemMeta() != null && item.getItemMeta().hasLore()) {
            Iterator var2 = item.getItemMeta().getLore().iterator();

            while(var2.hasNext()) {
                String lore = (String)var2.next();
                player.sendMessage(lore.replace('§', '&').replace(" ", "[空]"));
            }

            return true;
        } else {
            player.sendMessage("§c物品不存在或者不存在Lore! ");
            return true;
        }
    }

    private static boolean look(Player player, String[] args) {
        Hero hero = Data.getHero(player.getName());
        player.sendMessage("§e您的职业: §c" + hero.getType().toString());
        return true;
    }

    private static boolean reload(CommandSender sender) {
        HeroPlugin hp = HeroPlugin.getPlugin();
        hp.reloadConfig();
        hp.Config();
        sender.sendMessage("§e重载插件成功!");
        return true;
    }

    private static boolean change(CommandSender sender, String[] args) {
        try {
            Iterator var2 = Bukkit.getWorlds().iterator();

            while(var2.hasNext()) {
                World world = (World)var2.next();
                Iterator var10 = world.getPlayers().iterator();

                while(var10.hasNext()) {
                    Player player = (Player)var10.next();
                    if (player.getName().equals(args[1])) {
                        Iterator var12 = player.getActivePotionEffects().iterator();

                        while(var12.hasNext()) {
                            PotionEffect potionEffect = (PotionEffect)var12.next();
                            player.removePotionEffect(potionEffect.getType());
                        }

                        Data.addHero(player.getName(), HeroType.valueOf(args[2].toUpperCase()));
                        if (Data.getHero(player.getName()) instanceof Shooter) {
                            player.sendMessage("Shooter属性载入");
                            player.addPotionEffect(JUMP_EFFECT);
                            player.addPotionEffect(SPEED_EFFECT);
                            player.addPotionEffect(NIGHT_VISION_EFFECT);
                        } else if (Data.getHero(player.getName()) instanceof Warrior) {
                            player.sendMessage("Warrior属性载入");
                            player.addPotionEffect(JUMP_EFFECT);
                            player.addPotionEffect(SPEED_EFFECT);
                        }
                    }
                }
            }

            sender.sendMessage("§c成功为玩家更换职业!");
        } catch (Exception var8) {
            sender.sendMessage("§c职业列表: ");
            HeroType[] var3 = HeroType.values();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                HeroType t = var3[var5];
                sender.sendMessage("§c" + t.toString());
            }
        }

        return true;
    }

    private static boolean select(Player player, String[] args) {
        if (Data.getHero(player.getName()) != null) {
            player.sendMessage("§c无法重复选择职业");
            return true;
        } else {
            try {
                if (Data.addHero(player.getName(), HeroType.valueOf(args[1].toUpperCase()))) {
                    player.sendMessage("§c恭喜您成功选择职业!");
                }
            } catch (Exception var7) {
                player.sendMessage("§c职业列表: ");
                HeroType[] var3 = HeroType.values();
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    HeroType t = var3[var5];
                    player.sendMessage("§c" + t.toString());
                }
            }

            return true;
        }
    }

    private static boolean Help(CommandSender sender) {
        sender.sendMessage("§c/hp sl [职业名] §e//选择职业");
        sender.sendMessage("§c/hp look §e//查看自己的职业");
        if (sender.isOp()) {
            sender.sendMessage("§c/hp itemLook §e//查看手中物品的Lore");
            sender.sendMessage("§c/hp change [玩家名] [职业名] §e//更换玩家的职业");
            sender.sendMessage("§c/hp reload §e//重载配置文件");
        }

        return true;
    }

    static {
        JUMP_EFFECT = new PotionEffect(PotionEffectType.JUMP, 2147483647, 1);
        SPEED_EFFECT = new PotionEffect(PotionEffectType.SPEED, 2147483647, 2);
        NIGHT_VISION_EFFECT = new PotionEffect(PotionEffectType.NIGHT_VISION, 2147483647, 1);
    }
}
