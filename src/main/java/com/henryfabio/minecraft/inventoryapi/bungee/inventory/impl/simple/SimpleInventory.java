package com.henryfabio.minecraft.inventoryapi.bungee.inventory.impl.simple;

import com.henryfabio.minecraft.inventoryapi.bungee.inventory.configuration.impl.InventoryConfigurationImpl;
import com.henryfabio.minecraft.inventoryapi.bungee.inventory.impl.CustomInventoryImpl;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.impl.simple.SimpleViewer;
import de.exceptionflug.protocolize.inventory.InventoryType;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public abstract class SimpleInventory extends CustomInventoryImpl {

    public SimpleInventory(String id, String title, InventoryType type) {
        super(id, title, type, new InventoryConfigurationImpl.Simple());
    }

    @Override
    public final <T extends Viewer> void openInventory(@NotNull ProxiedPlayer player, Consumer<T> viewerConsumer) {
        Viewer viewer = new SimpleViewer(player.getName(), this);
        defaultOpenInventory(player, viewer, viewerConsumer);
    }

    @Override
    public final void updateInventory(@NotNull ProxiedPlayer player) {
        super.updateInventory(player);
    }

    protected void configureViewer(@NotNull SimpleViewer viewer) {
        // empty method
    }

    @Override
    protected final void configureViewer(@NotNull Viewer viewer) {
        this.configureViewer(((SimpleViewer) viewer));
    }

}
