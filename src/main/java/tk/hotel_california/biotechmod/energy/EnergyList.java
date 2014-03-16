package tk.hotel_california.biotechmod.energy;
//girl do you know Java?
//because your method body is sexy!

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

import java.util.HashMap;
//a perverted version of a registry
public class EnergyList {
    static int maxID = 0;
    static HashMap<String, Energy> energies = Maps.newHashMap();
    static BiMap<String, Integer> energyIDs = HashBiMap.create();
    public static boolean registerEnergy(Energy energy) {
        if (energyIDs.containsKey(energy.getName())) {
            return false;
        }
        energies.put(energy.getName(), energy);
        energyIDs.put(energy.getName(), maxID++);
        return true;
    }
    public static boolean isEnergyRegistered(Energy energy) {
        return energyIDs.containsKey(energy.getName());
    }
    public static Energy getEnergy(String energyName) {
        return energies.get(energyName);
    }
    public static Energy getEnergy(int energyID) {
        return energies.get(getEnergyName(energyID));
    }
    public static String getEnergyName(int energyID) {
        return energyIDs.inverse().get(energyID);
    }
    public static String getEnergyName(EnergyStack stack) {
        return getEnergyName(stack.energyID);
    }
    public static int getEnergyID(String energyName) {
        return energyIDs.get(energyName);
    }
    public static EnergyStack getEnergyStack(String energyName, int amount) {
        if(!energyIDs.containsKey(energyName)) {
            return null;
        }
        return new EnergyStack(getEnergyID((energyName)), amount);
    }
    public static int getMaxID() {
        return maxID;
    }
}
