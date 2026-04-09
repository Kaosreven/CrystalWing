package com.github.kaosreven.crystal_wing.events.init;

import com.github.kaosreven.crystal_wing.ModHelper;
import com.github.kaosreven.crystal_wing.items.CrystalWing;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;

public class ItemListener {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;
    public static Item CRYSTAL_WING;
    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        CRYSTAL_WING = new CrystalWing(NAMESPACE.id("crystal_wing")).setTranslationKey(ModHelper.NAMESPACE, "crystal_wing");
    }
}
