package com.github.kaosreven.crystal_wing.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class CrystalWing extends TemplateItem {
    private boolean playNotes;
    private int start;
    public CrystalWing(Identifier id) {
        super(id);
        this.maxCount = 1;
        this.setMaxDamage(7);
    }

    private ItemStack useCrystalWing(ItemStack itemstack, World world, PlayerEntity entityplayer) {
        int x2 = (int)entityplayer.x, y2, z2 = (int)entityplayer.z;
        Vec3i cc;
        boolean playEffects;
        if(entityplayer.dimensionId == -1) {
            for(y2 = 30; world.getBlockId(x2, y2, z2) != Block.LAVA.id; ++x2) {}
            cc = new Vec3i(x2,y2,z2);
            entityplayer.sendMessage("§4BURN!!!");
            playEffects = false;
        } else {
            cc = entityplayer.getSpawnPos();
            if (cc == null) {
                cc = world.getSpawnPos();
            }
            for(y2 = cc.y; world.getBlockId(cc.x, y2, cc.z) > 0 || world.getBlockId(cc.x, y2 + 1, cc.z) > 0; ++y2) {}
            entityplayer.sendMessage("Magical winds brought you home");
            playEffects = true;
        }
        Random random = new Random();
        entityplayer.setPosition(cc.x+0.5, y2 + entityplayer.height, cc.z+0.5);
        entityplayer.yaw = random.nextFloat() * 360.0F;
        entityplayer.pitch = 0.0F;
        if(playEffects) {
            playEffects(entityplayer, world);
        }
        itemstack.damage(1, null);
        return itemstack;
    }

    public void playAtPitch(int i, World world, PlayerEntity entityplayer) {
        float f = (float)Math.pow(2.0D, (double)(i - 12) / 12.0D);
        world.playSound(entityplayer, "note.pling", 3.0F, f);
    }

    public void playEffects(PlayerEntity entityplayer, World world) {
        world.addParticle("explode", entityplayer.x, entityplayer.y+0.2, entityplayer.z, 0.0, 0.0, 0.0 );
        world.addParticle("explode", entityplayer.x, entityplayer.y+0.3, entityplayer.z, 0.0, 0.0, 0.0 );
        playNotes = true;
        start = entityplayer.age;
        playAtPitch(8, world, entityplayer);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        if(playNotes) {
            PlayerEntity entityplayer = world.getClosestPlayer(entity, 64.0D);
            int ptime = entityplayer.age - start;
            switch(ptime) {
                case 1:
                    playAtPitch(8, world, entityplayer);
                    break;
                case 3:
                    playAtPitch(12, world, entityplayer);
                    break;
                case 5:
                    playAtPitch(15, world, entityplayer);
                    break;
                case 7:
                    playAtPitch(19, world, entityplayer);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer) {
        return world.isRemote ? itemstack : useCrystalWing(itemstack, world, entityplayer);
    }
}
