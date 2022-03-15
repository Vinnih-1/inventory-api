package com.henryfabio.minecraft.inventoryapi.bungee.item.supplier;

import com.henryfabio.minecraft.inventoryapi.bungee.item.InventoryItem;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@FunctionalInterface
public interface InventoryItemSupplier {

    InventoryItem get();

}
