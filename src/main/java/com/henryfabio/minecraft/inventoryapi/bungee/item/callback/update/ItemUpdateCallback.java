package com.henryfabio.minecraft.inventoryapi.bungee.item.callback.update;

import de.exceptionflug.protocolize.items.ItemStack;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@FunctionalInterface
public interface ItemUpdateCallback {

    void accept(ItemStack itemStack);

}
