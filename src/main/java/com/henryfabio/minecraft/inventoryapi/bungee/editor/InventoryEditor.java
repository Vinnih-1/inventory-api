package com.henryfabio.minecraft.inventoryapi.bungee.editor;

import com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration.border.Border;
import com.henryfabio.minecraft.inventoryapi.bungee.item.InventoryItem;
import com.henryfabio.minecraft.inventoryapi.bungee.item.callback.ItemCallback;
import de.exceptionflug.protocolize.inventory.Inventory;
import de.exceptionflug.protocolize.items.ItemStack;

import java.util.List;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public interface InventoryEditor {

    Inventory getInventory();

    void setItem(int slot, InventoryItem inventoryItem);

    void setEmptyItem(int slot);

    void fillPage(List<InventoryItem> inventoryItems, Border border);

    void fillCenter(InventoryItem inventoryItem, Border border);

    void fillColumn(int column, InventoryItem inventoryItem);

    void updateAllItemStacks();

    void updateItemStack(int slot);

    ItemStack getItemStack(int slot);

    ItemCallback getItemCallback(int slot);

}
