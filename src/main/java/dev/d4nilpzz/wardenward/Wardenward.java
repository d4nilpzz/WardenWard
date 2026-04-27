package dev.d4nilpzz.wardenward;

import com.mojang.logging.LogUtils;
import dev.d4nilpzz.wardenward.registry.ModCreativeTabs;
import dev.d4nilpzz.wardenward.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Wardenward.MODID)
public class Wardenward {
    public static final String MODID = "wardenward";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Wardenward(IEventBus modEventBus) {
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
    }
}
