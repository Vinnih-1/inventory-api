package com.henryfabio.minecraft.inventoryapi.bungee.inventory;

import com.henryfabio.minecraft.inventoryapi.bungee.inventory.configuration.InventoryConfiguration;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import de.exceptionflug.protocolize.inventory.InventoryType;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public interface CustomInventory {

    @NotNull
    String getId();

    @NotNull
    String getTitle();

    InventoryType getType();

    @NotNull <T extends InventoryConfiguration> T getConfiguration();

    <T extends InventoryConfiguration> void configuration(@NotNull Consumer<T> consumer);

    <T extends CustomInventory> T init();

    <T extends Viewer> void openInventory(@NotNull ProxiedPlayer player, @Nullable Consumer<T> viewerConsumer);

    void openInventory(@NotNull ProxiedPlayer player);

    void updateInventory(@NotNull ProxiedPlayer player);

}
