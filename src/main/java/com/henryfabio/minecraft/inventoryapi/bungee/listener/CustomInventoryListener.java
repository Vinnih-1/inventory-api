package com.henryfabio.minecraft.inventoryapi.bungee.listener;

import com.henryfabio.minecraft.inventoryapi.bungee.controller.ViewerController;
import com.henryfabio.minecraft.inventoryapi.bungee.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.bungee.event.impl.CustomInventoryClickEvent;
import com.henryfabio.minecraft.inventoryapi.bungee.event.impl.CustomInventoryCloseEvent;
import com.henryfabio.minecraft.inventoryapi.bungee.item.callback.ItemCallback;
import com.henryfabio.minecraft.inventoryapi.bungee.manager.InventoryManager;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import de.exceptionflug.protocolize.inventory.InventoryType;
import de.exceptionflug.protocolize.inventory.event.InventoryClickEvent;
import de.exceptionflug.protocolize.inventory.event.InventoryCloseEvent;
import lombok.val;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


import java.util.function.Consumer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public final class CustomInventoryListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        val player = event.getPlayer();
        ViewerController viewerController = InventoryManager.getViewerController();

        Viewer viewer = viewerController.unregisterViewer(player.getName());
        if (viewer != null) {
            CustomInventoryCloseEvent closeEvent = new CustomInventoryCloseEvent(viewer, event);
            ProxyServer.getInstance().getPluginManager().callEvent(closeEvent);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        val clickedInventory = event.getInventory();
        if (clickedInventory == null) return;

        val player = event.getPlayer();

        ViewerController viewerController = InventoryManager.getViewerController();
        viewerController.findViewer(player.getName()).ifPresent(viewer -> {
            event.setCancelled(true);

            CustomInventoryClickEvent clickEvent = new CustomInventoryClickEvent(viewer, event);
            ProxyServer.getInstance().getPluginManager().callEvent(clickEvent);

            if (clickedInventory.getType().equals(InventoryType.PLAYER)) return;

            InventoryEditor editor = viewer.getEditor();
            ItemCallback itemCallback = editor.getItemCallback(event.getSlot());
            if (itemCallback == null) return;

            Consumer<CustomInventoryClickEvent> callback = itemCallback.getClickCallback(event.getClickType());
            if (callback != null) {
                callback.accept(clickEvent);
            }
        });
    }

}
