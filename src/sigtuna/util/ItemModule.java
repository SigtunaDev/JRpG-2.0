package sigtuna.util;

import java.util.ArrayList;
import java.util.List;

import sigtuna.main.Main;

public class ItemModule {
	
	Main plugin = Main.plugin;
	
	public void setListType(String ID,String info) {
		
		plugin.ic.set(ID + ".module.list_type",info);
		
		plugin.reload();
		
	}
	
	public void addBanList(String ID,String ban_module) {
		
		List<String> list = plugin.ic.getStringList(ID + ".module.list");
		
		if(list == null) {
			
			list = new ArrayList<String>();
			
			list.add(ban_module);
			
		}else {
			
			list.add(ban_module);
			
		}
		
		plugin.ic.set(ID + ".module.list", list);
		
		plugin.reload();
		
	}
	
	public void removeBanList(String ID,String ban_module) {
		
		List<String> list = plugin.ic.getStringList(ID + ".module.list");
		
		if(list == null) {
			
			list = new ArrayList<String>();
			
			list.remove(ban_module);
			
		}else {
			
			list.remove(ban_module);
			
		}
		
		plugin.ic.set(ID + ".module.list", list);
		
		plugin.reload();
		
	}
	
	public boolean isExistInList(String ID,String ban_module) {
		
		List<String> list = plugin.ic.getStringList(ID + ".module.list");
		
		if(list == null) return false;
		
		if(!list.contains(ban_module)) return false;
		
		return true;
		
	}
	
	public String getBanType(String ID) {
		
		String str = plugin.ic.getString(ID + ".module.list_type");
		
		return str;
		
	}
	
	public List<String> getBanList(String ID,Boolean bool){
		
		List<String> banList = plugin.ic.getStringList(ID + ".module.ban.list");
		List<String> list = ItemCount.moduleList;
		
		if(getBanType(ID).equals("allow")) {
			
			list = banList;
			
		}else if(getBanType(ID).equals("deny")) {
			
			for(int i = 0; i < banList.size(); i++) {
				
				list.remove(banList.get(i));
				
			}
			
		}
		
		return list;
		
	}

}
