package tk.hotel_california.biotechmod.energy;

import net.minecraft.item.ItemStack;

public interface IEnergyContainer {
    /**
     *
     * @param container
     *          ItemStack that is the container
     * @return EnergyStack of the energy in the container, or null if empty
     */
    EnergyStack getEnergy(ItemStack container);

    /**
     *
     * @param container
     *          ItemStack that is the container
     * @return Capacity of the energy container
     */
    int getCapacity(ItemStack container);

    /**
     *
     * @param container
     *          ItemStack that is the container
     * @param resource
     *          EnergyStack attempting to fill the container
     * @param dofill
     *          If false, the fill will only be simulated
     * @return Amount of energy that was filled into the container
     */
    int fill(ItemStack container, EnergyStack resource, boolean dofill);

    /**
     *
     * @param container
     *          ItemStack that is the container
     * @param maxDrain
     *          Maximum amount of fluid that can be removed from the container
     * @param doDrain
     *          If false, the drain will only be simulated
     * @return Amount of energy that was drained form the container
     */
    EnergyStack drain(ItemStack container, int maxDrain, boolean doDrain);
}
