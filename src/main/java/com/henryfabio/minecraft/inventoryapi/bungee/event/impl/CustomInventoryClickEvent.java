package com.henryfabio.minecraft.inventoryapi.bungee.event.impl;

import com.henryfabio.minecraft.inventoryapi.bungee.event.CustomInventoryEvent;
import com.henryfabio.minecraft.inventoryapi.bungee.inventory.CustomInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import de.exceptionflug.protocolize.api.ClickType;
import de.exceptionflug.protocolize.inventory.Inventory;
import de.exceptionflug.protocolize.inventory.event.InventoryClickEvent;
import de.exceptionflug.protocolize.items.ItemStack;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Cancellable;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Getter
public final class CustomInventoryClickEvent extends CustomInventoryEvent implements Cancellable {

    private final InventoryClickEvent primaryEvent;

    private final Inventory clickedInventory;
    private final ItemStack itemStack;
    private final ClickType clickType;
    private final int slot;

    public CustomInventoryClickEvent(Viewer viewer, InventoryClickEvent primaryEvent) {
        super(viewer);
        this.primaryEvent = primaryEvent;
        this.clickedInventory = primaryEvent.getInventory();
        this.itemStack = primaryEvent.getClickedItem();
        this.clickType = primaryEvent.getClickType();
        this.slot = primaryEvent.getSlot();
    }

    public void updateItemStack() {
        InventoryEditor inventoryEditor = this.getViewer().getEditor();
        //inventoryEditor.updateItemStack(slot);
    }

    public void updateInventory() {
        CustomInventory customInventory = this.getCustomInventory();
        //customInventory.updateInventory(this.getPlayer());
    }

    @Override
    public boolean isCancelled() {
        return this.primaryEvent.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.primaryEvent.setCancelled(cancel);
    }

}
