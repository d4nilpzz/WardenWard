package dev.d4nilpzz.wardenward.registry;

import dev.d4nilpzz.wardenward.Wardenward;
import dev.d4nilpzz.wardenward.items.WardenWardItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Wardenward.MODID);

    public static final DeferredItem<Item> WARDEN_WARD =
            ITEMS.register("warden_ward", () ->
                    new WardenWardItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC))
            );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}