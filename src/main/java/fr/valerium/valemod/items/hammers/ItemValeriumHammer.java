package fr.valerium.valemod.items.hammers;

public class ItemValeriumHammer extends ItemHammerBase {
    public ItemValeriumHammer(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("valerium_hammer"); // Nom de l'item
        this.setMaxStackSize(1); // Vous pouvez ajuster la pile maximale si nécessaire
		setTextureName("valerium:hammer/valerium_hammer");
    }

}
