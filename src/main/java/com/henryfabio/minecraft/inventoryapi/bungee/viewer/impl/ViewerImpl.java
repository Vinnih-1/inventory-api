package com.henryfabio.minecraft.inventoryapi.bungee.viewer.impl;

import com.henryfabio.minecraft.inventoryapi.bungee.inventory.CustomInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration.ViewerConfiguration;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.property.ViewerPropertyMap;
import com.henryfabio.minecraft.inventoryapi.bungee.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.bungee.editor.impl.InventoryEditorImpl;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import de.exceptionflug.protocolize.inventory.Inventory;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Data
public abstract class ViewerImpl implements Viewer {

    private final String name;
    private final CustomInventory customInventory;

    private final ViewerConfiguration configuration;
    private final ViewerPropertyMap propertyMap = new ViewerPropertyMap();

    @Setter(AccessLevel.PRIVATE) protected InventoryEditor editor;

    @Override
    public Inventory createInventory() {
        Inventory inventory = new Inventory(configuration.inventoryType(),
                new TextComponent(ChatColor.translateAlternateColorCodes('&', configuration.titleInventory())));

        this.editor = new InventoryEditorImpl(inventory);
        return inventory;
    }

    @Override
    public void resetConfigurations() {
        this.configuration.titleInventory(this.customInventory.getTitle());
        this.configuration.inventoryType(this.customInventory.getType());
        this.configuration.backInventory(null);
    }

}
