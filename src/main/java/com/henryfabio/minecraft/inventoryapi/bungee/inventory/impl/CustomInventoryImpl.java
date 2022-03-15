package com.henryfabio.minecraft.inventoryapi.bungee.inventory.impl;

import com.henryfabio.minecraft.inventoryapi.bungee.controller.InventoryController;
import com.henryfabio.minecraft.inventoryapi.bungee.controller.ViewerController;
import com.henryfabio.minecraft.inventoryapi.bungee.inventory.CustomInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.inventory.configuration.InventoryConfiguration;
import com.henryfabio.minecraft.inventoryapi.bungee.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.bungee.manager.InventoryManager;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import de.exceptionflug.protocolize.inventory.Inventory;
import de.exceptionflug.protocolize.inventory.InventoryModule;
import de.exceptionflug.protocolize.inventory.InventoryType;
import lombok.Data;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Data
public abstract class CustomInventoryImpl implements CustomInventory {

    private final String id;
    private final String title;
    private final InventoryType type;

    private final InventoryConfiguration configuration;

    @Override
    public final <T extends CustomInventory> T init() {
        InventoryController inventoryController = InventoryManager.getInventoryController();
        return (T) inventoryController.registerInventory(this);
    }

    @Override
    public final <T extends InventoryConfiguration> void configuration(@NotNull Consumer<T> consumer) {
        T configuration = this.getConfiguration();
        consumer.accept(configuration);
    }

    @Override
    public final void openInventory(@NotNull ProxiedPlayer player) {
        this.openInventory(player, null);
    }

    @Override
    public void updateInventory(@NotNull ProxiedPlayer player) {
        ViewerController viewerController = InventoryManager.getViewerController();
        viewerController.findViewer(player.getName()).ifPresent(viewer -> {
            if (viewer.getCustomInventory().getClass().isInstance(this)) {
                InventoryEditor editor = viewer.getEditor();
                update(viewer, editor);
                editor.updateAllItemStacks();

                //player.updateInventory();
            }
        });
    }

    @Override
    public final <T extends InventoryConfiguration> @NotNull T getConfiguration() {
        return (T) configuration;
    }

    protected final <T extends Viewer> void defaultOpenInventory(ProxiedPlayer player, Viewer viewer, Consumer<T> viewerConsumer) {
        if (viewerConsumer != null) {
            viewerConsumer.accept((T) viewer);
        }

        viewer.resetConfigurations();
        this.configureViewer(viewer);

        Inventory inventory = viewer.createInventory();
        InventoryEditor editor = viewer.getEditor();

        configureInventory(viewer, editor);
        update(viewer, editor);

        InventoryModule.sendInventory(player, inventory);

        ViewerController viewerController = InventoryManager.getViewerController();
        viewerController.registerViewer(viewer);
    }

    protected void configureViewer(@NotNull Viewer viewer) {
        // empty method
    }

    protected void configureInventory(@NotNull Viewer viewer, @NotNull InventoryEditor editor) {
        // empty method
    }

    protected void update(@NotNull Viewer viewer, @NotNull InventoryEditor editor) {
        // empty method
    }

}
