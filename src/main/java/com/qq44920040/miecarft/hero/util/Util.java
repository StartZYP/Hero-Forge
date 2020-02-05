//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero.util;

import java.util.Iterator;
import java.util.UUID;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

public class Util {
    public Util() {
    }

    public static UUID PlayerLaunchProjectile(Player player, Class<? extends Projectile> c) {
        Projectile ball = player.launchProjectile(c, player.getLocation().getDirection().multiply(5));
        return ball.getUniqueId();
    }

    public static boolean ItemHasLore(ItemStack is, String label) {
        if (is != null && is.getItemMeta() != null && is.getItemMeta().hasLore()) {
            Iterator var2 = is.getItemMeta().getLore().iterator();

            while(var2.hasNext()) {
                String lore = (String)var2.next();
                if (label.equals(lore)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void particleCreate(Location Loc, double Radii, Effect Type) {
        for(double i = 1.5D; i <= Radii; i += 2.0D) {
            for(double o = 90.0D; o <= 270.0D; ++o) {
                double x = i * Math.cos(o / 180.0D * 3.141592653589793D) * -Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.sin((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + i * Math.sin(o / 180.0D * 3.141592653589793D) * -Math.sin((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D);
                double y = 0.8D + i * Math.cos(o / 180.0D * 3.141592653589793D) * Math.sin((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D);
                double z = i * (Math.cos(o / 180.0D * 3.141592653589793D) * Math.cos((double)((90.0F - Loc.clone().getPitch() + 90.0F) / 180.0F) * 3.141592653589793D) * Math.cos((double)(Loc.clone().getYaw() / 180.0F) * 3.141592653589793D) + Math.sin(o / 180.0D * 3.141592653589793D) * Math.cos((double)((Loc.clone().getYaw() - 90.0F) / 180.0F) * 3.141592653589793D));
                Location Location = Loc.clone().add(x, y, z);
                Loc.clone().getWorld().playEffect(Location, Type, 0);
            }
        }

    }

    public static boolean isEntityInIt(Location entityLct, Location start, Location end) {
        boolean x = start.getX() >= end.getX() ? entityLct.getX() >= end.getX() && entityLct.getX() <= start.getX() : entityLct.getX() >= start.getX() && entityLct.getX() <= end.getX();
        boolean y = start.getY() >= end.getY() ? entityLct.getY() >= end.getY() && entityLct.getY() <= start.getY() : entityLct.getY() >= start.getY() && entityLct.getY() <= end.getY();
        boolean z = start.getZ() >= end.getZ() ? entityLct.getZ() >= end.getZ() && entityLct.getZ() <= start.getZ() : entityLct.getZ() >= start.getZ() && entityLct.getZ() <= end.getZ();
        return x && y && z;
    }

    public static boolean isEntityRange(Location lct, Location lct1, int r) {
        double cy = Math.abs(lct.getY() - lct1.getY());
        double xy = Math.abs(lct.getX() - lct1.getX());
        double zy = Math.abs(lct.getZ() - lct1.getZ());
        return cy <= (double)r && xy <= (double)r && zy <= (double)r;
    }
}
