package com.henryfabio.minecraft.inventoryapi.bungee.inventory;

import com.henryfabio.minecraft.inventoryapi.bungee.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.bungee.inventory.impl.simple.SimpleInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.item.InventoryItem;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration.ViewerConfiguration;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.impl.simple.SimpleViewer;
import com.henryfabio.minecraft.inventoryapi.bungee.Main;
import de.exceptionflug.protocolize.api.ClickType;
import de.exceptionflug.protocolize.inventory.InventoryType;
import de.exceptionflug.protocolize.items.ItemStack;
import de.exceptionflug.protocolize.items.ItemType;
import lombok.val;
import net.md_5.bungee.api.chat.TextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public final class TestSimpleInventory extends SimpleInventory {

    public TestSimpleInventory() {
        super(
                "test.inventory.simple", // Identificador do inventário (deve ser único)
                "&8SimpleInventory", // Título padrão do inventário
                InventoryType.GENERIC_9X3 // Tamanho do inventário
        );

        // Método para configurar características do inventário (não é obrigatória a configuração)
        configuration(configuration -> {
            configuration.secondUpdate(1); // Definir o tempo de atualização do inventário (não configure isso caso não queira que ele atualize automaticamente)
        });
    }

    /**
     * Método utilizado para configurar o visualizador, podendo definir
     * unicamente o título do inventário e tamanho.
     * <p>
     * Não é obrigatória a implementação desde método, apenas caso queira uma configuração
     * exclusiva para cada visualizador.
     *
     * @param viewer visualizador do inventário
     */
    @Override
    protected void configureViewer(@NotNull SimpleViewer viewer) {
        ViewerConfiguration configuration = viewer.getConfiguration();
        configuration.titleInventory("&8Seu nome: " + viewer.getName());
    }

    /**
     * Método utilizado para configurar os itens do inventário.
     *
     * @param viewer visualizador do inventário
     * @param editor editor do inventário
     */
    @Override
    protected void configureInventory(@NotNull Viewer viewer, @NotNull InventoryEditor editor) {
        val viewerPlayer = viewer.getPlayer();

        // Item utilizado para conseguir definir callbacks para o item
        InventoryItem inventoryItem = InventoryItem.of(
                new ItemStack(ItemType.ANVIL)
        ).callback(ClickType.RIGHT_CLICK, event -> { // Callback chamado quando o jogador clicar com o botão direito no item
            val player = event.getPlayer();
            player.sendMessage(new TextComponent("§eVocê clicou com o botão direito!"));
        }).defaultCallback(event -> { // Callback chamado quando o jogador interagir com qualquer botão com esse item
            val player = event.getPlayer();
            Main.getInstance().pagedInventory.openInventory(player);
        }).updateCallback(itemStack -> {
            Random random = new Random();
            itemStack.setDisplayName("§e" + random.nextInt(1000));
        });

        editor.setItem(13, inventoryItem);
    }

    /**
     * Método utilizado para definir ou atualizar itens durante a atualização do inventário.
     * Esse método é chamado automaticamente se o valor InventoryConfiguration#secondUpdate() for diferente de 0.
     *
     * @param viewer visualizador do inventário
     * @param editor editor do inventário
     */
    @Override
    protected void update(@NotNull Viewer viewer, @NotNull InventoryEditor editor) {
        Random random = new Random();
        editor.setItem(random.nextInt(9), InventoryItem.of(new ItemStack(ItemType.EMERALD_BLOCK)));
    }

}
