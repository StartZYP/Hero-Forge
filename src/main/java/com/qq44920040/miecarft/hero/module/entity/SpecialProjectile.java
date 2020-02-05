//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qq44920040.miecarft.hero.module.entity;

public class SpecialProjectile {
    private SpecialProjectile.SpecialProjectileType type;

    public SpecialProjectile(SpecialProjectile.SpecialProjectileType type) {
        this.type = type;
    }

    public void setType(SpecialProjectile.SpecialProjectileType type) {
        this.type = type;
    }

    public SpecialProjectile.SpecialProjectileType getType() {
        return this.type;
    }

    public static enum SpecialProjectileType {
        HEALER_SKILL_ONE,
        HELPER_SKILL_FIX,
        HELPER_SKILL_NODAMAGE,
        MAGICIAN_SKILL_FIRE,
        MAGICIAN_SKILL_GHOST;

        private SpecialProjectileType() {
        }
    }
}
