package sigtuna.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import sigtuna.util.ConfigData;
import sigtuna.util.ItemCount;
import sigtuna.util.ItemData;
import sigtuna.util.StringManager;

public class Main extends JavaPlugin {
	
	//宣告plugin為類別Main的靜態變數
	public static Main plugin;
	
	//資料檔
	public File item,config,lang,player_config,uuid_search;
	public FileConfiguration ic,cc,lc,pc,us;
	
	public void onEnable() {
		
		plugin = this;
		
		new EventLoader();
		new CommandLoader();
		
		item = new File(this.getDataFolder() + "/item.yml");
		ic = YamlConfiguration.loadConfiguration(item);
		
		config = new File(this.getDataFolder() + "/config.yml");
		cc = YamlConfiguration.loadConfiguration(config);
		
		lang = new File(this.getDataFolder() + "/lang.yml");
		lc = YamlConfiguration.loadConfiguration(lang);
		
		player_config = new File(this.getDataFolder() + "/player.yml");
		pc = YamlConfiguration.loadConfiguration(player_config);
		
		uuid_search = new File(this.getDataFolder() + "/uuid_search.yml");
		us = YamlConfiguration.loadConfiguration(uuid_search);
		
		this.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "插件開發人員：Sigtuna");
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Discord: Believe#4619");
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Facebook: Han Xuan");
		
		if(plugin.cc.getString("version") == null) {
			ConfigData.init();
		}
		
		if(StringManager.INFO_TEXT_SUCCESS == null) {
			System.out.println("語言檔不存在，新增中...");
			StringManager.init();
			lc = YamlConfiguration.loadConfiguration(lang);
			System.out.println(String.format("測試：%s", StringManager.getTagMessage("INFO_TEXT_SUCCESS")));
		}
		
		this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + String.format("模組類-物品數量： %d 個", ItemCount.moduleList.size()));
		this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + String.format("武器類-物品數量： %d 個", ItemCount.weaponList.size()));
		this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + String.format("未知類-物品數量： %d 個", ItemCount.unknownList.size()));
		
		uuid_search();
		reload();
		
	}
	
	public void reload() {
		
		try {
			
			uuid_search();
			
			cc.save(config);
			ic.save(item);
			lc.save(lang);
			pc.save(player_config);
			us.save(uuid_search);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void uuid_search() {
		
		Set<String> set = plugin.ic.getConfigurationSection("").getKeys(false);
		
		List<String> list = new ArrayList<String>();
		
		list.addAll(set);
		
		us.set("uuid", "");
		
		for(int i = 0; i < list.size(); i++) {
			
			ItemData item = new ItemData(list.get(i));
			
			plugin.us.set("uuid." + item.getStringUniqueID(list.get(i)), list.get(i));
			
		}
		
	}

}
