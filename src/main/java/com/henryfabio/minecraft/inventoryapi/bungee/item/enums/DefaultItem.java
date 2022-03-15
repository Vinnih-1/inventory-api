package com.henryfabio.minecraft.inventoryapi.bungee.item.enums;

import com.henryfabio.minecraft.inventoryapi.bungee.controller.InventoryController;
import com.henryfabio.minecraft.inventoryapi.bungee.manager.InventoryManager;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.impl.paged.PagedViewer;
import com.henryfabio.minecraft.inventoryapi.bungee.item.InventoryItem;
import de.exceptionflug.protocolize.inventory.InventoryModule;
import de.exceptionflug.protocolize.items.ItemStack;
import de.exceptionflug.protocolize.items.ItemType;
import lombok.AllArgsConstructor;
import lombok.Setter;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@AllArgsConstructor
public enum DefaultItem {

    BACK(viewer -> {
        ItemStack itemStack = new ItemStack(ItemType.ARROW);
        itemStack.setDisplayName("§aVoltar");

        return InventoryItem.of(itemStack)
            .defaultCallback(event -> {
                String backInventory = viewer.getConfiguration().backInventory();
                if (backInventory == null) return;

                InventoryController inventoryController = InventoryManager.getInventoryController();
                inventoryController.findInventory(backInventory)
                    .ifPresent(inventory -> inventory.openInventory(viewer.getPlayer()));
            });
    }),
    CLOSE(viewer -> {
        ItemStack itemStack = new ItemStack(ItemType.BARRIER);
        itemStack.setDisplayName("§cFechar");

        return InventoryItem.of(itemStack)
            .defaultCallback(event -> {
                ProxiedPlayer player = event.getViewer().getPlayer();
                InventoryModule.closeAllInventories(player);
            });
    }),
    EMPTY(viewer -> {
        ItemStack itemStack = new ItemStack(ItemType.COBWEB);
        itemStack.setDisplayName("§cVazio");

        return InventoryItem.of(itemStack);
    }),
    NEXT_PAGE(viewer -> {
        if (!(viewer instanceof PagedViewer))
            throw new UnsupportedOperationException("viewer isn't from paged inventory");

        PagedViewer pagedViewer = (PagedViewer) viewer;

        ItemStack itemStack = new ItemStack(ItemType.GREEN_STAINED_GLASS_PANE);
        itemStack.setDisplayName("§aPróxima Página: " + (pagedViewer.getCurrentPage() + 1));

        return InventoryItem.of(itemStack)
            .defaultCallback(event -> pagedViewer.nextPage());
    }),
    PREVIOUS_PAGE(viewer -> {
        if (!(viewer instanceof PagedViewer))
            throw new UnsupportedOperationException("viewer isn't from paged inventory");

        PagedViewer pagedViewer = (PagedViewer) viewer;

        ItemStack itemStack = new ItemStack(ItemType.GREEN_STAINED_GLASS_PANE);
        itemStack.setDisplayName("§aPágina Anterior: " + (pagedViewer.getCurrentPage() - 1));

        return InventoryItem.of(itemStack)
            .defaultCallback(event -> pagedViewer.previousPage());
    }),
    NULL_ITEM(viewer -> {
        ItemStack itemStack = new ItemStack(ItemType.BLACK_STAINED_GLASS_PANE);
        itemStack.setDisplayName("§8Vazio");

        return InventoryItem.of(itemStack);
    }),
    NULL_PAGE(viewer -> {
        ItemStack itemStack = new ItemStack(ItemType.RED_STAINED_GLASS_PANE);
        itemStack.setDisplayName(new TextComponent("§cVazio"));

        return InventoryItem.of(itemStack);
    });

    @Setter private DefaultItemSupplier itemSupplier;

    public InventoryItem toInventoryItem(Viewer viewer) {
        return this.itemSupplier.get(viewer);
    }

    public InventoryItem toInventoryItem() {
        return this.itemSupplier.get(null);
    }

    @FunctionalInterface
    private interface DefaultItemSupplier {

        InventoryItem get(Viewer viewer);

    }

}
