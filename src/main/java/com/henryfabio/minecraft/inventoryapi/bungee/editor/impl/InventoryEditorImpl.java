package com.henryfabio.minecraft.inventoryapi.bungee.editor.impl;

import com.google.common.base.Preconditions;
import com.henryfabio.minecraft.inventoryapi.bungee.item.callback.update.ItemUpdateCallback;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration.border.Border;
import com.henryfabio.minecraft.inventoryapi.bungee.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.bungee.item.InventoryItem;
import com.henryfabio.minecraft.inventoryapi.bungee.item.callback.ItemCallback;

import de.exceptionflug.protocolize.inventory.Inventory;
import de.exceptionflug.protocolize.items.ItemStack;
import de.exceptionflug.protocolize.items.ItemType;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Data
public final class InventoryEditorImpl implements InventoryEditor {

    private final Inventory inventory;
    private final Map<Integer, ItemCallback> inventoryCallbackMap = new LinkedHashMap<>();

    @Override
    public void setItem(int slot, InventoryItem inventoryItem) {
        if (inventoryItem == null) setEmptyItem(slot);
        else {
            this.inventory.setItem(slot, inventoryItem.getItemStack());
            this.inventoryCallbackMap.put(slot, inventoryItem.getItemCallback());
        }
    }

    @Override
    public void fillPage(List<InventoryItem> inventoryItems, Border border) {
        int bottomLimit = (inventory.getType().getTypicalSize(47) / 9) - border.getBottom();

        int width = border.getLeft();
        int height = border.getTop();
        for (InventoryItem item : inventoryItems) {
            int itemSlot = inventory.getType().getTypicalSize(47) > 9 ? width + 9 * height : width;
            setItem(itemSlot, item);

            if (++width == (9 - border.getRight())) {
                width = border.getLeft();
                if (++height >= bottomLimit) break;
            }
        }
    }

    @Override
    public void fillCenter(InventoryItem inventoryItem, Border border) {
        List<InventoryItem> inventoryItems = new LinkedList<>();
        for (int i = 0; i < inventory.getType().getTypicalSize(47); i++) inventoryItems.add(inventoryItem);
        this.fillPage(inventoryItems, border);
    }

    @Override
    public void fillColumn(int column, InventoryItem inventoryItem) {
        Preconditions.checkState(
                column >= 0 && column <= 9,
                "The column must be greater than or equal to 0 or less than or equal to 9"
        );

        int columnIndex = column;
        for (int i = 0; i < this.getInventoryRows(); i++) {
            setItem(columnIndex, inventoryItem);
            columnIndex += 9;
        }
    }

    @Override
    public void setEmptyItem(int slot) {
        this.inventory.setItem(slot, new ItemStack(ItemType.AIR));
        this.inventoryCallbackMap.remove(slot);
    }

    @Override
    public void updateItemStack(int slot) {
        ItemCallback itemCallback = getItemCallback(slot);
        if (itemCallback == null) return;

        updateItemStack(slot, itemCallback);
    }

    @Override
    public void updateAllItemStacks() {
        for (Map.Entry<Integer, ItemCallback> entry : inventoryCallbackMap.entrySet()) {
            updateItemStack(entry.getKey(), entry.getValue());
        }
    }

    private void updateItemStack(int slot, ItemCallback itemCallback) {
        ItemUpdateCallback updateCallback = itemCallback.getUpdateCallback();
        if (updateCallback == null) return;

        ItemStack itemStack = getItemStack(slot);
        updateCallback.accept(itemStack);

        this.inventory.setItem(slot, itemStack);
    }

    @Override
    public ItemStack getItemStack(int slot) {
        return this.inventory.getItem(slot);
    }

    @Override
    public ItemCallback getItemCallback(int slot) {
        return this.inventoryCallbackMap.get(slot);
    }

    private int getInventoryRows() {
        return this.inventory.getType().getTypicalSize(47) / 9;
    }
}
