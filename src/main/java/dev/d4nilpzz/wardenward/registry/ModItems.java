package dev.d4nilpzz.wardenward.registry;

import dev.d4nilpzz.wardenward.Wardenward;
import dev.d4nilpzz.wardenward.items.GenericWardenItem;
import dev.d4nilpzz.wardenward.items.WardenPulseBootsItem;
import dev.d4nilpzz.wardenward.items.WardenSonicPulseItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Wardenward.MODID);

    public static final DeferredItem<Item> WARDEN_WARD =
            ITEMS.register("warden_ward", () -> new GenericWardenItem(new Item.Properties()));

    public static final DeferredItem<Item> WARDEN_XP_NECKLACE =
            ITEMS.register("warden_xp_necklace", () -> new GenericWardenItem(new Item.Properties()));

    public static final DeferredItem<Item> WARDEN_PULSE_BOOTS =
            ITEMS.register("warden_pulse_boots", () -> new WardenPulseBootsItem(new Item.Properties()));

    public static final DeferredItem<Item> WARDEN_SONIC_PULSE =
            ITEMS.register("warden_sonic_pulse", () -> new WardenSonicPulseItem(new Item.Properties()));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}