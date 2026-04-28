package dev.d4nilpzz.wardenward.registry;

import dev.d4nilpzz.wardenward.Wardenward;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Wardenward.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WARDEN_WARD_TAB =
            CREATIVE_MODE_TABS.register("warden_ward_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.wardenward.warden_ward_tab"))
                            .icon(() -> new ItemStack(ModItems.WARDEN_WARD.get()))
                            .displayItems((parameters, output) -> {
                                output.accept(ModItems.WARDEN_WARD.get());
                                output.accept(ModItems.WARDEN_PULSE_NECKLACE.get());
                                output.accept(ModItems.WARDEN_XP_NECKLACE.get());
                            })
                            .build()
            );

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
