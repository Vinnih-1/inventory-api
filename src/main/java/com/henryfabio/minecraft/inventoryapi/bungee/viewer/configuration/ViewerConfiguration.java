package com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration;

import de.exceptionflug.protocolize.inventory.InventoryType;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public interface ViewerConfiguration {

    String titleInventory();

    ViewerConfiguration titleInventory(String title);

    InventoryType inventoryType();

    ViewerConfiguration inventoryType(InventoryType type);

    String backInventory();

    ViewerConfiguration backInventory(String inventory);

}
