package sigtuna.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import sigtuna.util.ItemData;
import sigtuna.util.ItemMaker;
import sigtuna.util.ItemVerification;

public class Jitem implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length > 2) {

			String command = args[1];
			String ID = args[0];

			if (command.equals("name")) {

				String name = args[2];

				name(sender, ID, name);

			}else if (command.equals("lore")) {

				String control = args[2];

				if (control.equals("add")) {

					String lore = args[3];
					addLore(sender, ID, lore);

				} else if (control.equals("set")) {

					int line = Integer.parseInt(args[3]);
					String lore = args[4];

					setLore(sender, ID, line, lore);

				} else if (control.equals("print")) {

					printLore(sender, ID);

				} else if (control.equals("remove")) {

					int line = Integer.parseInt(args[3]);
					
					removeLore(sender, ID, line);
					
				}

			}else if(command.equals("material")) {
				
				String material = args[2];
				
				setMaterial(sender, ID, material);
				
			}else if(command.equals("type")) {
				
				String type = args[2];
				
				setType(sender,ID,type);
				
			} else {		
				
				sender.sendMessage(ChatColor.RED + "指令有誤，請閱讀說明後再使用。");
				sender.sendMessage(help());
				
			}

		} else if (args.length == 2) {

			String command = args[1];
			String ID = args[0];

			if (command.equals("create")) {

				create(sender, ID);

			} else if (command.equals("get")) {

				get(sender, ID);

			} else if (command.equals("uuid_search")) {

				uuid_search(sender, ID);
				
			} else if (command.equals("remove")){

				remove(sender, ID);
				
				
			} else {

				sender.sendMessage(ChatColor.RED + "指令有誤，請閱讀說明後再使用。");
				sender.sendMessage(help());

			}

		} else if (args.length == 1) {

			String command = args[0];

			if (command.equals("list")) {

				list(sender);
				
			} else {

				sender.sendMessage(ChatColor.RED + "指令有誤，請閱讀說明後再使用。");
				sender.sendMessage(help());

			}

		} else {

			sender.sendMessage(ChatColor.RED + "指令有誤，請閱讀說明後再使用。");
			sender.sendMessage(help());

		}

		return false;
	}

	public String[] help() {

		List<String> list = new ArrayList<String>();

		list.add("=======================================");
		list.add("/jitem list 獲得目前所有物品列表");
		list.add("/jitem <ID> create 創造物品");
		list.add("/jitem <ID> get 取得物品");
		list.add("/jitem <ID> uuid_search 查訊物品uuid");
		list.add("/jitem <ID> name <名稱> 更改物品的名稱");
		list.add("/jitem <ID> lore add|set|print|remove <變數>");
		list.add(" - add <lore> 新增敘述");
		list.add(" - set <line> <lore> 變更該行的敘述");
		list.add(" - print 預覽敘述");
		list.add(" - remove <line> <lore> 刪除該行的敘述");
		list.add("/jitem <ID> material <1.13_MATERIAL> 設定該物品的材質");
		list.add("備註：該物品ID必須要是1.13的ID格式");
		list.add("/jitem <ID> type <module/weapon/food/basic> 設置物品的類別");
		list.add("/jitem <ID> remove 刪除物品");
		list.add("/jitem <ID> update 物品版本更新");
		list.add("=======================================");
		
		String[] array = new String[list.size()];
		list.toArray(array);

		return array;

	}

	public void remove(CommandSender sender, String ID) {
		
		if(!sender.hasPermission("jitem.item.remove")) {
			
			sender.sendMessage("你沒有權限可以使用此指令。");
			return;
			
		}
		
		boolean exist = ItemVerification.item_exist(ID);
		
		if(exist == false) {
			
			sender.sendMessage(String.format("物品 %s 不存在。",ID));
			return;
			
		}
		
		ItemMaker.remove(ID);
		sender.sendMessage("刪除完成。");
		
	}
	
	public void create(CommandSender sender, String ID) {

		if (!sender.hasPermission("jitem.item.create")) {

			sender.sendMessage("你沒有權限可以使用此指令。");
			return;

		}
		
		boolean exist = ItemVerification.item_exist(ID);

		if (exist == false) {

			if (ItemMaker.create(ID) == true) {

				sender.sendMessage(String.format("創立物品 %s 成功", ID));

			}

		} else {

			sender.sendMessage(String.format("此物品 %s 已存在", ID));

		}

	}

	public void get(CommandSender sender, String ID) {

		if (!sender.hasPermission("jitem.item.get")) {

			sender.sendMessage("你沒有權限可以使用此指令。");
			return;

		}

		if (!(sender instanceof Player)) {

			sender.sendMessage("你必須是玩家才可以使用此指令");
			return;

		}

		ItemData data = new ItemData(ID);
		Player player = (Player) sender;

		if (!ItemVerification.item_exist(ID)) {

			sender.sendMessage(String.format("物品 %s 不存在", ID));
			return;

		}

		player.getInventory().addItem(data.getItem());
		player.sendMessage(String.format("物品 %s 已添加到你的物品欄", ID));

	}

	public void uuid_search(CommandSender sender, String ID) {

		if (!sender.hasPermission("jitem.search.uuid")) {

			sender.sendMessage("你沒有權限可以使用此指令。");
			return;

		}

		if (!ItemVerification.item_exist(ID)) {

			sender.sendMessage(String.format("物品 %s 不存在", ID));
			return;

		}

		ItemData data = new ItemData(ID);

		sender.sendMessage(String.format("uuid查詢結果：此物品uuid為 %s ", data.getStringUniqueID(ID)));

	}

	public void list(CommandSender sender) {

		Set<String> set = ItemData.getItemList();

		String[] array = new String[set.size()];

		set.toArray(array);

		sender.sendMessage("物品清單：");

		for (int i = 0; i < set.size(); i++) {

			TextComponent tc = new TextComponent(" - " + array[i]);
			
			tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,String.format("/jitem %s get",array[i])));
			tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("點擊可獲得此物品").create()));
			
			sender.spigot().sendMessage(tc);
			
		}

	}

	public void name(CommandSender sender, String ID, String name) {

		ItemData data = new ItemData(ID);

		if (!sender.hasPermission("jitem.item.name")) {

			sender.sendMessage("你沒有權限可以使用此指令。");
			return;

		}

		if (!ItemVerification.item_exist(ID)) {

			sender.sendMessage(String.format("物品 %s 不存在", ID));
			return;

		}
		
		name = translateColor(name);

		data.setName(name);

		sender.sendMessage(String.format("物品 %s 的名稱已經被更改為 %s", ID, name));

	}

	private String translateColor(String text) {

		return ChatColor.translateAlternateColorCodes('&', text);

	}

	public void addLore(CommandSender sender, String ID, String lore) {

		if (!sender.hasPermission("jitem.item.lore.add")) {

			sender.sendMessage("你沒有權限可以使用此指令。");
			return;

		}

		if (!ItemVerification.item_exist(ID)) {

			sender.sendMessage(String.format("物品 %s 不存在", ID));
			return;

		}
		
		ItemData data = new ItemData(ID);

		lore = translateColor(lore);

		data.addLore(lore);

		sender.sendMessage(String.format("物品 %s 已新增敘述 %s", ID, lore));

	}

	public void setLore(CommandSender sender, String ID, int line, String lore) {

		if (!sender.hasPermission("jitem.item.lore.set")) {

			sender.sendMessage("你沒有權限可以使用此指令。");
			return;

		}

		if (!ItemVerification.item_exist(ID)) {

			sender.sendMessage(String.format("物品 %s 不存在", ID));
			return;

		}
		
		ItemData data = new ItemData(ID);

		lore = translateColor(lore);

		int total_line = data.getLore().size();

		if (line >= total_line) {

			sender.sendMessage(String.format("此物品 %s 的行數只有 %d 行，若您想更改第 1 行，line請輸入 0 ", ID, total_line));
			return;

		}

		data.setLore(lore, line);

		sender.sendMessage(String.format("物品 %s 已更改第 %d 行的敘述 %s", ID, line, lore));

	}

	public void printLore(CommandSender sender, String ID) {

		if (!sender.hasPermission("jitem.item.lore.print")) {

			sender.sendMessage("你沒有權限可以使用此指令。");
			return;

		}

		if (!ItemVerification.item_exist(ID)) {

			sender.sendMessage(String.format("物品 %s 不存在", ID));
			return;

		}
		
		ItemData data = new ItemData(ID);

		List<String> lore = data.getLore();

		sender.sendMessage("");

		for (int i = 0; i < lore.size(); i++) {

			TextComponent tc = new TextComponent(" - " + lore.get(i));

			tc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
					String.format("/jitem %s lore set %d %s", ID, i, lore.get(i).replaceAll("§", "&"))));

			tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("點擊我來更改").create()));

			sender.spigot().sendMessage(tc);

		}

		sender.sendMessage("");

	}

	public void removeLore(CommandSender sender, String ID, int line) {

		if (!sender.hasPermission("jitem.item.lore.remove")) {

			sender.sendMessage("你沒有權限可以使用此指令。");
			return;

		}

		if (!ItemVerification.item_exist(ID)) {

			sender.sendMessage(String.format("物品 %s 不存在", ID));
			return;

		}
		
		ItemData data = new ItemData(ID);

		data.removeLore(line);

		sender.sendMessage(String.format("已刪除第 %d 行的敘述", line));

	}
	
	public void setMaterial(CommandSender sender, String ID, String material) {
		
		if (!sender.hasPermission("jitem.item.material")) {

			sender.sendMessage("你沒有權限可以使用此指令。");
			return;

		}

		if (!ItemVerification.item_exist(ID)) {

			sender.sendMessage(String.format("物品 %s 不存在", ID));
			return;

		}
		
		ItemData data = new ItemData(ID);
		
		try {
			
			data.setMaterial(material);
			
			Material mat = Material.getMaterial(material);
			
			sender.sendMessage(String.format("物品材質設置為 %s",mat.name()));
			
		}catch(Exception e) {
			
			sender.sendMessage(String.format("找不到ID為 %s 的物品",material));
 			
		}
		
	}
	
	public void setType(CommandSender sender, String ID, String type) {
		
		if (!sender.hasPermission("jitem.item.type")) {

			sender.sendMessage("你沒有權限可以使用此指令。");
			return;

		}

		if (!ItemVerification.item_exist(ID)) {

			sender.sendMessage(String.format("物品 %s 不存在", ID));
			return;

		}
		
		List<String> list = new ArrayList<String>();
		
		list.add("module");
		list.add("weapon");
		list.add("food");
		list.add("basic");
		
		if(!list.contains(type)) {
			
			sender.sendMessage(String.format("找不到類別%s",type));
			return;
			
		}
		
		ItemData data = new ItemData(ID);
		
		data.setType(type);
		
		sender.sendMessage(String.format("物品類別設置為%s",type));
		
	}
	
	public void update(CommandSender sender, String ID) {
		
		if(!sender.hasPermission("jitem.item.update")) {
			
			sender.sendMessage("你沒有權限可以使用此指令。");
			return;
			
		}
		
		boolean exist = ItemVerification.item_exist(ID);
		
		if(exist == false) {
			
			sender.sendMessage(String.format("物品 %s 不存在。",ID));
			return;
			
		}
		
		ItemData data = new ItemData(ID);
		
		data.setVersion(data.getVersion() + 0.1);
		
	}

}
