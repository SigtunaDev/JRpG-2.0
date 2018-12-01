package sigtuna.util;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import sigtuna.main.Main;

public class ItemMaker {

	static Main plugin = Main.plugin;

	public static boolean create(String ID) {

		try {

			plugin.ic.set(String.format("%s.name", ID), StringManager.INFO_ITEM_INIT_NAME);
			plugin.ic.set(String.format("%s.material", ID), "STONE");
			plugin.ic.set(String.format("%s.lore", ID), Arrays.asList(StringManager.INFO_ITEM_INIT_LORE));

			// 物品類別預設none
			plugin.ic.set(String.format("%s.info.class", ID), "none");
			plugin.ic.set(String.format("%s.info.skill", ID), "none");
			plugin.ic.set(String.format("%s.uuid", ID), UUID.randomUUID().toString());
			plugin.ic.set(String.format("%s.version", ID), 0.1);
			
			plugin.reload();

			return true;

		} catch (Exception e) {

			return false;

		}

	}

	public static void remove(String ID) {

		plugin.ic.set(String.format("%s", ID), null);
		
		plugin.reload();

	}

	public static List<String> loreMaker(List<String> lore, String ID) {

		ItemLore il = new ItemLore(ID);

		return il.getLore(lore);

	}

}
