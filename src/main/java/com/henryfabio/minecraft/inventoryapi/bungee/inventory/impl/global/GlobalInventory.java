package com.henryfabio.minecraft.inventoryapi.bungee.inventory.impl.global;

import com.henryfabio.minecraft.inventoryapi.bungee.inventory.configuration.impl.InventoryConfigurationImpl;
import com.henryfabio.minecraft.inventoryapi.bungee.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.bungee.editor.impl.InventoryEditorImpl;
import com.henryfabio.minecraft.inventoryapi.bungee.inventory.impl.CustomInventoryImpl;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.impl.global.GlobalViewer;
import de.exceptionflug.protocolize.inventory.Inventory;
import de.exceptionflug.protocolize.inventory.InventoryType;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Getter
public abstract class GlobalInventory extends CustomInventoryImpl {

    private InventoryEditor inventoryEditor;

    public GlobalInventory(String id, String title, InventoryType type) {
        super(id, title, type, new InventoryConfigurationImpl.Global());
    }

    @Override
    public final <T extends Viewer> void openInventory(@NotNull ProxiedPlayer player, Consumer<T> viewerConsumer) {
        createInventoryEditor();

        Viewer viewer = new GlobalViewer(player.getName(), this);
        defaultOpenInventory(player, viewer, viewerConsumer);
    }

    public final void updateInventory() {
        if (this.inventoryEditor == null) return;

        this.update(this.inventoryEditor);
        //this.inventoryEditor.updateAllItemStacks();
    }

    @Override
    public final void updateInventory(@NotNull ProxiedPlayer player) {
        this.updateInventory();
    }

    @Override
    protected final void configureViewer(@NotNull Viewer viewer) {
        //
    }

    protected void configureInventory(@NotNull InventoryEditor editor) {
        // empty method
    }

    @Override
    protected final void configureInventory(@NotNull Viewer viewer, @NotNull InventoryEditor editor) {
        //
    }

    protected void update(@NotNull InventoryEditor editor) {
        // empty method
    }

    @Override
    protected final void update(@NotNull Viewer viewer, @NotNull InventoryEditor editor) {
        this.update(this.inventoryEditor);
    }

    private void createInventoryEditor() {
        if (this.inventoryEditor != null) return;

        Inventory inventory = new Inventory(this.getType(),
                new TextComponent(ChatColor.translateAlternateColorCodes('&', this.getTitle())));

        this.inventoryEditor = new InventoryEditorImpl(inventory);

        this.configureInventory(this.inventoryEditor);
        this.update(this.inventoryEditor);
    }

}
