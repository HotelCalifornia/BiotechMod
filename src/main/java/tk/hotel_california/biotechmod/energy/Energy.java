package tk.hotel_california.biotechmod.energy;
//girl do you know Java?
//because your method body is sexy!

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.StatCollector;

import java.util.Locale;
//normally I'd spell 'localized' with an s, but for the sake of consistency, I'll conform to forge

public class Energy {
    /**
     * Unique identifier for the energy.
     */
    protected final String energyName;
    /**
     * The integer ID of the energy.
     * For now, this is dangerous but necessary.
     * In the future, I will create my own energy registry.
     */
    protected final int energyID;
    /**
     * Unlocalised name of the energy.
     */
    protected String unlocalizedName;
    /**
     * How much light is emitted from blocks carrying the energy (containers, wires, etc.)
     * Default value is 3.
     */
    protected int luminosity = 3;
    /**
     * Temperature of the energy.
     * Default temperature is 0 Fahrenheit as electricity doesn't have a temperature.
     * Increase if you would like blocks carrying the energy (containers, wires, etc.) to heat up, otherwise arbitrary.
     */
    protected int temperature = 0;
    /**
     * The rarity of the energy.
     * Used primarily in tool tips.
     */
    protected EnumRarity rarity = EnumRarity.common;

    public Energy(String energyName, int energyID) {
        this.energyName = energyName.toLowerCase(Locale.ENGLISH);
        this.energyID = energyID;
        this.unlocalizedName = energyName;
    }
    public Energy setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }
    public Energy setLuminosity(int luminosity) {
        this.luminosity = luminosity;
        return this;
    }
    public Energy setTemperature(int temperature) {
        this.temperature = temperature;
        return this;
    }
    public Energy setRarity(EnumRarity rarity) {
        this.rarity = rarity;
        return this;
    }
    public final String getName() {
        return this.energyName;
    }
    public final int getID() {
        return this.energyID;
    }
    public String getLocalizedName() {
        String s = this.getUnlocalizedName();
        return s == null ? "" : StatCollector.translateToLocal(s);
    }
    public String getUnlocalizedName() {
        return "energy." + this.unlocalizedName;
    }
    public final int getLuminosity()
    {
        return this.luminosity;
    }
    public final int getTemperature()
    {
        return this.temperature;
    }
    public EnumRarity getRarity()
    {
        return rarity;
    }



}
