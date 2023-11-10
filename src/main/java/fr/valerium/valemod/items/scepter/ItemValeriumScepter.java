package fr.valerium.valemod.items.scepter;

import fr.valerium.valemod.ModVale;
import fr.valerium.valemod.spells.ModSpell;
import fr.valerium.valemod.spells.Spell;
import fr.valerium.valemod.spells.SpellSelection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemValeriumScepter extends Item{

	private int level = 3;
	
	public ItemValeriumScepter() {
		this.setUnlocalizedName("valerium_scepter");
		this.setCreativeTab(ModVale.tabValerium);
		this.setTextureName("valerium:scepter/valerium_scepter");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		SpellSelection.setSelectedSpell(ModSpell.ATTRACTIO);
		Spell selectedSpell = SpellSelection.getSelectedSpell();
		if(selectedSpell != null) {
			selectedSpell.setSpellLevel(level);
			selectedSpell.executeSpell(player, level);
		}
		
		
		return itemStack;
	}
}
