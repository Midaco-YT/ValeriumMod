package fr.valerium.valemod.client.gui;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.valerium.valemod.client.gui.containers.ContainerDisenchantingTable;
import fr.valerium.valemod.tiles.TileEntityDisenchantingTable;
import fr.valerium.valemod.utils.GuiSlotEnchants;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiDisenchantingTable extends GuiContainer {

	private static final ResourceLocation GUI_BACKGROUND = new ResourceLocation(
			"valerium:textures/gui/disenchanting_table.png");
	private final TileEntityDisenchantingTable tileEntity;
	private List<EnchantmentData> enchantments;
	private int selectedEnchantment;
	private EnchantmentSlot enchantmentSlot;
	private int top = 10;
	private int bottom = 256;
	private int slotHeight = 15;

	public GuiDisenchantingTable(InventoryPlayer playerInventory, TileEntityDisenchantingTable tileEntity) {
		super(new ContainerDisenchantingTable(playerInventory, tileEntity));
		this.tileEntity = tileEntity;
	}

	@Override
	public void initGui() {
		super.initGui();
		buttonList.clear();
		ItemStack enchantedItem = tileEntity.getStackInSlot(0);
		enchantments = new ArrayList<EnchantmentData>();
		if (enchantedItem != null && enchantedItem.getItem() != Items.book) {
			NBTTagList enchantmentsTag = enchantedItem.getEnchantmentTagList();
			if (enchantmentsTag != null) {
				for (int i = 0; i < enchantmentsTag.tagCount(); i++) {
					NBTTagCompound enchantmentTag = enchantmentsTag.getCompoundTagAt(i);
					int enchantId = enchantmentTag.getShort("id");
					int enchantLvl = enchantmentTag.getShort("lvl");
					Enchantment enchant = Enchantment.enchantmentsList[enchantId];
					enchantments.add(new EnchantmentData(enchant, enchantLvl));
				}
				enchantmentSlot = new EnchantmentSlot(width, height, top, bottom, slotHeight);
				enchantmentSlot.registerScrollButtons(7, 8);
			}
		} else {
			enchantmentSlot = null;
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		if (enchantmentSlot != null) {
			enchantmentSlot.drawScreen(mouseX, mouseY, partialTicks);
			
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		selectedEnchantment = button.id;
		Enchantment enchant = enchantments.get(selectedEnchantment).enchantmentobj;
		tileEntity.transferEnchantments(enchant);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(GUI_BACKGROUND);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x - 9, y, 0, 0, xSize + 10, ySize + 10);
	}

	private class EnchantmentSlot extends GuiSlotEnchants {
		private int top;
		private int bottom;
		private int slotHeight;
		

		public EnchantmentSlot(int width, int height, int top, int bottom, int slotHeight) {
			super(mc, width, height, top, bottom, slotHeight);
			this.top = top;
			this.bottom = bottom;
			this.slotHeight = slotHeight;
		}

		@Override
		protected int getSize() {
			return enchantments.size();
		}
		
		@Override
		protected int getContentHeight() {
			return enchantments.size() * 65;
			
		}
		
		 public int func_148135_f()
		    {
		        return this.getContentHeight() - (this.bottom - this.top - 4);
		    }

		@Override
		protected void elementClicked(int index, boolean isDoubleClick, int mouseX, int mouseY) {
			selectedEnchantment = index;
			Enchantment enchant = enchantments.get(selectedEnchantment).enchantmentobj;
			tileEntity.transferEnchantments(enchant);
		}

		@Override
		protected boolean isSelected(int index) {
			return index == selectedEnchantment;
		}


		@Override
		protected void drawSlot(int entryID, int x, int y, int p_148126_4_,
				Tessellator p_148126_5_, int p_148126_6_, int p_148126_7_) {
			
			EnchantmentData enchantment = enchantments.get(entryID);
			String name = I18n.format(enchantment.enchantmentobj.getName());
			fontRendererObj.drawString(name, x + 31, y + 2, 0xFFFFFF);

			
		}
		
	    @Override
	    protected void drawContainerBackground(Tessellator tessellator) {
	        // No background
	    }
	    
	    @Override
		protected void drawBackground() {

		}
		
	}
}
