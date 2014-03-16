package tk.hotel_california.biotechmod.energy;
//girl do you know Java?
//because your method body is sexy!

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.item.ItemStack;
import tk.hotel_california.biotechmod.item.Items;

import java.util.Map;
import java.util.Set;

public abstract class EnergyContainerList {
    private static class ContainerKey {
        ItemStack container;
        EnergyStack energy;
        private ContainerKey(ItemStack container) {
            this.container = container;
        }
        private ContainerKey(ItemStack container, EnergyStack energy) {
            this(container);
            this.energy = energy;
        }
        @Override
        public int hashCode() {
            int code = 1;
            code = 31*code + container.hashCode();
            code = 31*code + container.getItemDamage();
            if(energy != null) {
                code = 31*code + energy.energyID;
            }
            return code;
        }
    }
    private static Map<ContainerKey, EnergyContainerData> containerEnergyMap = Maps.newHashMap();
    private static Map<ContainerKey, EnergyContainerData> filledContainerMap = Maps.newHashMap();
    private static Set<ContainerKey> emptyContainers = Sets.newHashSet();

    public static final int BATTERY_CHARGE = 1000;
    public static final ItemStack EMPTY_BATTERY = new ItemStack(Items.declareItems.bioticBatteryItem);
    public static final ItemStack NULL_EMPTYCONTAINER = new ItemStack(Items.declareItems.bioticBatteryItem);

    private EnergyContainerList() {}

    public static boolean registerEnergyContainer(EnergyStack stack, ItemStack filledContainer, ItemStack emptyContainer) {
        return registerEnergyContainer(new EnergyContainerData(stack, filledContainer, emptyContainer));
    }
    public static boolean registerEnergyContainer(Energy energy, ItemStack filledContainer, ItemStack emptyContainer) {
        if(!EnergyList.isEnergyRegistered(energy)) {
            EnergyList.registerEnergy(energy);
        }
        return registerEnergyContainer(new EnergyStack(energy, BATTERY_CHARGE), filledContainer, emptyContainer);
    }
    public static boolean registerEnergyContainer(EnergyStack stack, ItemStack filledContainer) {
        return registerEnergyContainer(new EnergyContainerData(stack, filledContainer, null, true));
    }
    public static boolean registerEnergyContainer(Energy energy, ItemStack filledContainer) {
        if(!EnergyList.isEnergyRegistered(energy)) {
            EnergyList.registerEnergy(energy);
        }
        return registerEnergyContainer(new EnergyStack(energy, BATTERY_CHARGE), filledContainer);
    }
    public static boolean registerEnergyContainer(EnergyContainerData data) {
        if(isFilledContainer(data.filledContainer)) {
            return false;
        }
        containerEnergyMap.put(new ContainerKey(data.filledContainer), data);
        if(data.emptyContainer != null && data.emptyContainer != NULL_EMPTYCONTAINER) {
            filledContainerMap.put(new ContainerKey(data.emptyContainer, data.energy), data);
            emptyContainers.add(new ContainerKey(data.emptyContainer));
        }
        return true;
    }
    public static EnergyStack getEnergyForFilledItem(ItemStack container) {
        if(container == null) {
            return null;
        }
        EnergyContainerData data = containerEnergyMap.get(new ContainerKey(container));
        return data == null ? null : data.energy.copy();
    }
    public static ItemStack fillEnergyContainer(EnergyStack energy, ItemStack container) {
        if(container == null || energy == null) {
            return null;
        }
        EnergyContainerData data = filledContainerMap.get(new ContainerKey(container, energy));
        if(data != null && energy.amount >= data.energy.amount) {
            return data.filledContainer.copy();
        }
        return null;
    }
    public static boolean containsEnergy(ItemStack container, EnergyStack energy) {
        if(container == null || energy == null) {
            return false;
        }
        EnergyContainerData data = filledContainerMap.get(new ContainerKey(container, energy));
        return data == null ? false : data.energy.isEnergyEqual(energy);
    }
    public static boolean isBattery(ItemStack container) {
        if(container == null) {
            return false;
        }
        if(container.isItemEqual(EMPTY_BATTERY)) {
            return true;
        }
        EnergyContainerData data = containerEnergyMap.get(new ContainerKey(container));
        return data != null && data.emptyContainer.isItemEqual(EMPTY_BATTERY);
    }
    public static boolean isContainer(ItemStack container) {
        return isEmptyContainer(container) || isFilledContainer(container);
    }
    public static boolean isEmptyContainer(ItemStack container) {
        return container != null && getEnergyForFilledItem(container) != null;
    }
    public static boolean isFilledContainer(ItemStack container) {
        return container != null && getEnergyForFilledItem(container) != null;
    }
    public static EnergyContainerData[] getRegisteredEnergyContainerData() {
        return containerEnergyMap.values().toArray(new EnergyContainerData[containerEnergyMap.size()]);
    }
    public static class EnergyContainerData {
        public final EnergyStack energy;
        public final ItemStack filledContainer;
        public final ItemStack emptyContainer;
        public EnergyContainerData(EnergyStack stack, ItemStack filledContainer, ItemStack emptyContainer) {
            this(stack, filledContainer, emptyContainer, false);
        }
        public EnergyContainerData(EnergyStack stack, ItemStack filledContainer, ItemStack emptyContainer, boolean nullEmpty) {
            this.energy = stack;
            this.filledContainer = filledContainer;
            this.emptyContainer = emptyContainer;
            if(stack == null || filledContainer == null || emptyContainer == null && nullEmpty) {
                throw new RuntimeException("Invalid EnergyContainerData - a parameter was null.");
            }
        }
        public EnergyContainerData copy() {
            return new EnergyContainerData(energy, filledContainer, emptyContainer, true);
        }
    }
}
