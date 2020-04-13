package de.marvinleiers.guiapi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Marvin Leiers
 * @version 1.0
 */
public class GuiAPI extends JavaPlugin implements CommandExecutor
{
    public static Plugin PLUGIN;

    public void onEnable()
    {
        setUp(this);
    }

    public static void setUp(Plugin plugin)
    {
        PLUGIN = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            ((Player) sender).openInventory(GuiInventory.createInventory("§aTest"));
        }

        return true;
    }


}
