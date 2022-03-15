package com.henryfabio.minecraft.inventoryapi.bungee.viewer;

import com.henryfabio.minecraft.inventoryapi.bungee.inventory.CustomInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration.ViewerConfiguration;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.property.ViewerPropertyMap;
import com.henryfabio.minecraft.inventoryapi.bungee.editor.InventoryEditor;
import de.exceptionflug.protocolize.inventory.Inventory;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public interface Viewer {

    String getName();

    default ProxiedPlayer getPlayer() {
        return ProxyServer.getInstance().getPlayer(this.getName());
    }

    <T extends CustomInventory> T getCustomInventory();

    <T extends ViewerConfiguration> T getConfiguration();

    ViewerPropertyMap getPropertyMap();

    InventoryEditor getEditor();

    default Inventory getInventory() {
        return this.getEditor().getInventory();
    }

    Inventory createInventory();

    void resetConfigurations();

}
