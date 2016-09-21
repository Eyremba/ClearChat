package me.athena222.clearchat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("clearchat")) {

			ArrayList<String> list = new ArrayList<String>();

			list.add("player");
			list.add("me");
			list.add("help");
			list.add("reload");

			Collections.sort(list);
			return list;
		}
		return null;
	}
}