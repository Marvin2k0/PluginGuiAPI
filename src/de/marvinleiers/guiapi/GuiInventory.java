package de.marvinleiers.guiapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class GuiInventory
{
    private static Plugin plugin = GuiAPI.PLUGIN;

    private Inventory content;
    private GuiInventory nextPanel;
    private boolean hasNext;

    private GuiInventory()
    {
        throw new NullPointerException("Not supported!");
    }

    /**
     * Set up the next panel.
     *
     * @param navigator Item that when you click on it, takes you to the next panel.
     * @param nextPanel The next panel.
     */
    public void setNextPanel(ItemStack navigator, GuiInventory nextPanel)
    {
        this.nextPanel = nextPanel;
    }

    /**
     *
     * @return Returns the next panel.
     */
    public GuiInventory getNextPanel()
    {
        return this.nextPanel;
    }

    /**
     *
     * @return Indicates whether this exact panel has a next panel.
     */
    public boolean hasNext()
    {
        return getNextPanel() != null;
    }

    /**
     * Creates a standard-sized inventory with custom title.
     *
     * @param title The invenory's title (can use color-codes)
     * @return The inventory
     */
    public static Inventory createInventory(String title)
    {
        return createInventory(title, 27);
    }

    /**
     * Creates a custom inventory.
     *
     * @param title The inventory's title (can use color-codes)
     * @param size  The inventory's size.
     * @return The inventory
     */
    public static Inventory createInventory(String title, int size)
    {
        if (plugin == null)
            return null;

        Inventory inventory = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', title));
        List<String> inventories;

        if (plugin.getConfig().getStringList("inventories").contains(title))
            return inventory;

        if (plugin.getConfig().isSet("inventories"))
        {
            inventories = plugin.getConfig().getStringList("inventories");

            inventories.add(title);
            plugin.getConfig().set("inventories", inventories);
        }
        else
        {
            inventories = new ArrayList<String>();
            inventories.add(title);

            plugin.getConfig().set("inventories", inventories);
        }

        plugin.saveConfig();

        return inventory;
    }
}
