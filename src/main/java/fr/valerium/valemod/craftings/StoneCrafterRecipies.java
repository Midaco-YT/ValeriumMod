package fr.valerium.valemod.craftings;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class StoneCrafterRecipies {
	private static final StoneCrafterRecipies instance = new StoneCrafterRecipies();

	private Map<ItemStack, Integer> smeltingAmmount = new HashMap<ItemStack, Integer>();
	
	private Map smeltingList = new HashMap<Object, Object>();

	public void add(ItemStack stack1, ItemStack stack2, ItemStack stack, int ammount) {
	    ItemStack[] stackList = { stack1, stack2 };
	    this.smeltingAmmount.put(stack, Integer.valueOf(ammount));
	    this.smeltingList.put(stackList, stack);
	  }

	public ItemStack getSmeltingResult(ItemStack[] stack) {
	    Map.Entry entry;
	    Iterator<Map.Entry> iterator = this.smeltingList.entrySet().iterator();
	    do {
	      if (!iterator.hasNext())
	        return null; 
	      entry = iterator.next();
	    } while (!isSameKey(stack, (ItemStack[])entry.getKey()));
	    return (ItemStack)entry.getValue();
	  }
	  
	  private boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2) {
	    boolean isSame = false;
	    for (int i = 0; i <= 1; i++) {
	      if (stackList[i].getItem() == stackList2[i].getItem()) {
	        isSame = true;
	      } else {
	        return false;
	      } 
	    } 
	    return isSame;
	  }

	public Map<ItemStack[], ItemStack> getSmeltingList() {
		return this.smeltingList;
	}

	public static StoneCrafterRecipies getManager() {
		return instance;
	}
	
	public int getSmeltingAmmount(ItemStack stack) {
	    return ((Integer)this.smeltingAmmount.get(stack)).intValue();
	  }
	  
	  public Map<ItemStack, Integer> getSmeltingAmmount() {
	    return this.smeltingAmmount;
	  }
}
