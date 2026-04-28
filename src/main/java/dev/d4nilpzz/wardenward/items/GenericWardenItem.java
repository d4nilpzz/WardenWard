package dev.d4nilpzz.wardenward.items;

import net.minecraft.world.item.Rarity;

public class GenericWardenItem extends BaseCuriosItem {

    public GenericWardenItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.RARE));
    }
}