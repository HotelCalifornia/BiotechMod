package tk.hotel_california.biotechmod.item;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.item.ItemStack;
import tk.hotel_california.biotechmod.energy.EnergyStack;

public class ItemBattery extends ItemEnergyContainer {
    public final int itemTier;
    public ItemBattery(int tier) {
        this.itemTier = tier;
    }
    public int getCapacity(ItemBattery battery) {
        switch(battery.itemTier) {
            case 0 : return 100;
            case 1 : return 1000;
            case 2 : return 10000;
            default: return 0;
        }
    }
}
