package com.github.kaosreven.crystal_wing.events.init;

import com.github.kaosreven.crystal_wing.ModHelper;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ItemStack;
import paulevs.bhcreative.api.CreativeTab;
import paulevs.bhcreative.api.SimpleTab;
import paulevs.bhcreative.registry.TabRegistryEvent;

public class CreativeListener {
    public static CreativeTab tabCrystalWing;

    @EventListener
    public void onTabInit(TabRegistryEvent event){
        tabCrystalWing = new SimpleTab(ModHelper.NAMESPACE.id("crystal_wing"), ItemListener.CRYSTAL_WING);
        event.register(tabCrystalWing);
        tabCrystalWing.addItem(new ItemStack(ItemListener.CRYSTAL_WING, 1));
    }
}