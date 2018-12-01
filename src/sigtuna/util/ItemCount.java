package sigtuna.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sigtuna.main.Main;

public class ItemCount {
	
	Main plugin = Main.plugin;
	
	public static List<String> moduleList = new ArrayList<String>();
	public static List<String> weaponList = new ArrayList<String>();
	public static List<String> unknownList = new ArrayList<String>();
	
	public ItemCount() {
		
		Set<String> set = plugin.ic.getKeys(false);
		
		String[] array = new String[set.size()];
		
		set.toArray(array);
		
		for(int i = 0; i < array.length; i++) {
			
			String type = plugin.ic.getString(array[i] + ".type");
			
			System.out.println(type);
			
			if(type == null) {
				
				unknownList.add(array[i]);
				continue;
				
			}
			
			if(type.equals("module")) {
				
				moduleList.add(array[i]);
				
			}else if(type.equals("weapon")){
				
				weaponList.add(array[i]);
				
			}
			
		}
		
	}
	

}
