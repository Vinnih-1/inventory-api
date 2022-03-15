package com.henryfabio.minecraft.inventoryapi.bungee.event.impl;

import com.henryfabio.minecraft.inventoryapi.bungee.event.CustomInventoryEvent;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import de.exceptionflug.protocolize.inventory.event.InventoryCloseEvent;
import lombok.Getter;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Getter
public final class CustomInventoryCloseEvent extends CustomInventoryEvent {

    private final InventoryCloseEvent primaryEvent;

    public CustomInventoryCloseEvent(Viewer viewer, InventoryCloseEvent primaryEvent) {
        super(viewer);
        this.primaryEvent = primaryEvent;
    }

}
