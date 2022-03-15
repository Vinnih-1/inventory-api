package com.henryfabio.minecraft.inventoryapi.bungee.event;

import com.henryfabio.minecraft.inventoryapi.bungee.inventory.CustomInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import de.exceptionflug.protocolize.inventory.Inventory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class CustomInventoryEvent extends Event {

    private final CustomInventory customInventory;
    private final Inventory inventory;

    private final ProxiedPlayer player;
    private final Viewer viewer;

    public CustomInventoryEvent(Viewer viewer) {
        this.viewer = viewer;
        this.customInventory = viewer.getCustomInventory();
        this.inventory = viewer.getInventory();
        this.player = viewer.getPlayer();
    }
}
