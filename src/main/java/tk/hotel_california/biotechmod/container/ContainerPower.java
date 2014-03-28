package tk.hotel_california.biotechmod.container;
//girl do you know Java?
//because your method body is sexy!


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import tk.hotel_california.biotechmod.item.ItemBattery;
import tk.hotel_california.biotechmod.item.ItemEnergyContainer;
import tk.hotel_california.biotechmod.tileentity.TileEntityEnergyContainer;

public class ContainerPower extends Container {
    TileEntityEnergyContainer te;
    int energyContained = 0;
    final int maxEnergyContained;
    Slot slot1;
    Slot slot2;
    public ContainerPower(InventoryPlayer inventoryPlayer, TileEntityEnergyContainer te, int energyCapacity) {
        this.te = te;
        this.maxEnergyContained = energyCapacity;
        IInventory inv = new TileEntityEnergyContainer(energyCapacity);
        this.slot1 = addSlotToContainer(new Slot(inv, 0, 40, 36));
        this.slot2 = addSlotToContainer(new Slot(inv, 1, 120, 36));
        bindPlayerInventory(inventoryPlayer);
    }
    @Override
    public void detectAndSendChanges() {
        if(slot1.getStack() != null && slot2.getStack() != null) {

            if(slot1.getStack().getItem() instanceof ItemEnergyContainer) {
                ItemEnergyContainer e = (ItemBattery)slot1.getStack().getItem();
                ItemStack container = slot1.getStack();
                if(this.energyContained + e.getCapacity(container) <= this.maxEnergyContained) {
                    //fills this container and drains from the container in the slot
                    this.energyContained += e.drain(container, e.getCapacity(container), true);
                }
                else {
                    this.energyContained += e.drain(container, this.energyContained - e.getCapacity(container), true);
                }
            }

            if(slot2.getStack().getItem() instanceof ItemEnergyContainer) {
                ItemEnergyContainer e = (ItemBattery)slot2.getStack().getItem();
                ItemStack container = slot2.getStack();
                if(this.energyContained != 0) {
                    //drains this container and fills the container in the slot
                    this.energyContained -= e.fill(container, e.getEnergy(container), true);
                }
            }
        }
        super.detectAndSendChanges();
    }
    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);

    }
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return te.isUseableByPlayer(player);
    }
    protected void bindPlayerInventory(InventoryPlayer inv) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for(int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inv, i, 8 + i * 18, 142));
        }
    }
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot)inventorySlots.get(slot);
        if(slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            if(slot < 9) {
                if(!this.mergeItemStack(stackInSlot, 0, 35, true)) {
                    return null;
                }
            }
            else if(!this.mergeItemStack(stackInSlot, 0, 9, false)) {
                return null;
            }
            if(stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            }
            else {
                slotObject.onSlotChanged();
            }
            if(stackInSlot.stackSize == stack.stackSize) {
                return null;
            }
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return stack;
    }

}
