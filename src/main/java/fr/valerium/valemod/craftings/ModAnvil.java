package fr.valerium.valemod.craftings;

import fr.valerium.valemod.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;

public class ModAnvil {
    
    @SuppressWarnings("unlikely-arg-type")
	public void anvilCraft(AnvilUpdateEvent e) {
    	e.left.equals(ModItems.legendary_pickaxe);
    	e.right.equals(ModItems.fortuneModifier);
    	e.output.equals(new ItemStack(ModItems.legendary_pickaxe));
    }
}
