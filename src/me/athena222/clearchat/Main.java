package me.athena222.clearchat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public FileConfiguration config = getConfig();

	public void onEnable() {
		saveDefaultConfig();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String clearchatconsole = ChatColor.translateAlternateColorCodes('&',
				this.config.getString("clearchat-console", ChatColor.GREEN + "Chat has been cleared by"
						+ ChatColor.DARK_RED + " Console" + ChatColor.GREEN + "!"));
		String b = "";

		if (!(sender instanceof Player)) {
			for (int i = 0; i < 250; i++) {
				getServer().broadcastMessage(b);
			}
			getServer().broadcastMessage(clearchatconsole);
			return true;
		}

		Player player = (Player) sender;
		String clearchat = ChatColor.translateAlternateColorCodes('&',
				this.config.getString("clearchat",
						ChatColor.GREEN + "Chat has been cleared by" + ChatColor.DARK_RED + player.getDisplayName())
						.replace("{name}", player.getDisplayName()));
		String clearchatme = ChatColor.translateAlternateColorCodes('&',
				this.config.getString("clearchat-me", ChatColor.GREEN + "Your chat has been cleared!"));
		String clearchatreload = ChatColor.translateAlternateColorCodes('&', this.config.getString("clearchat-reload",
				ChatColor.GREEN + "Successfully reloaded ClearChat Configuration!"));
		String noperm = ChatColor.translateAlternateColorCodes('&', this.config.getString("no-permission",
				ChatColor.RED + "You do not have permission to use that command!"));

		if ((cmd.getName().equalsIgnoreCase("clearchat")) && (player.hasPermission("clearchat.use"))) {
			if (args.length == 0) {
				for (int i = 0; i < 250; i++) {
					getServer().broadcastMessage(b);
				}
				getServer().broadcastMessage(clearchat);
				return true;
			}

		} else if (!player.hasPermission("clearchat.use")) {
			player.sendMessage(noperm);
		}

		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("help")) {
				player.sendMessage(b);
				player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "              ClearChat");
				player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "    v" + getDescription().getVersion()
						+ " by Athena222 & Pr0totype2");
				player.sendMessage(b);
				player.sendMessage(ChatColor.AQUA + "/clearchat help " + ChatColor.GRAY + "Shows this page.");
				player.sendMessage(ChatColor.AQUA + "/clearchat " + ChatColor.GRAY + "Globally clears chat.");
				player.sendMessage(ChatColor.AQUA + "/clearchat me " + ChatColor.GRAY + "Clears your chat.");
				player.sendMessage(ChatColor.AQUA + "/clearchat reload " + ChatColor.GRAY
						+ "Reloads the ClearChat Configuration.");
				player.sendMessage(b);
			} else if ((args[0].equalsIgnoreCase("me")) && (player.hasPermission("clearchat.me"))) {
				for (int i = 0; i < 250; i++) {
					player.sendMessage(b);
				}
				player.sendMessage(clearchatme);
			} else if (!player.hasPermission("clearchat.me")) {
				player.sendMessage(noperm);
			} else if ((args[0].equalsIgnoreCase("reload")) && (player.hasPermission("clearchat.reload"))) {
				reloadConfig();
				player.sendMessage(clearchatreload);
			} else if (!player.hasPermission("clearchat.reload")) {
				player.sendMessage(noperm);
			}
		}
		return true;
	}
}