package com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration.impl;

import com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration.border.Border;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration.ViewerConfiguration;
import de.exceptionflug.protocolize.inventory.InventoryType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Accessors(fluent = true)
@Data
public abstract class ViewerConfigurationImpl implements ViewerConfiguration {

    private String titleInventory;
    private InventoryType inventoryType;
    private String backInventory;

    @Setter @Getter
    public static class Simple extends ViewerConfigurationImpl {
        // empty implementation
    }

    @Accessors(fluent = true)
    @Setter @Getter
    public static class Paged extends ViewerConfigurationImpl {

        private int itemPageLimit;
        private Border border;

        private int nextPageSlot;
        private int previousPageSlot;
        private int emptyPageSlot;

    }

    public static class Global extends ViewerConfigurationImpl {
        // empty implementation
    }

}
