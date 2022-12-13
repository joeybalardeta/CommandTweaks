package com.core.commandtweaks.vanillaplus.singleplayersleep;

import com.core.commandtweaks.CommandTweaks;
import com.core.commandtweaks.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SinglePlayerSleep {
    private final int MIN_SLEEPING = 1;

    private int numSleeping = 0;

    private boolean numSleepingChanged = false;

    private int minSleepTicks = 0;

    private int taskID = -1;

    public int getMinSleeping() {
        return this.MIN_SLEEPING;
    }

    public int getNumSleeping() {
        return this.numSleeping;
    }

    public void setNumSleeping(int numSleeping) {
        if (this.numSleeping != numSleeping && numSleeping != 0) {
            this.numSleepingChanged = true;
        }

        this.numSleeping = numSleeping;
    }

    public boolean getNumSleepingChanged() {
        return this.numSleepingChanged;
    }

    public void setNumSleepingChanged(boolean changed) {
        this.numSleepingChanged = changed;
    }

    public int getMinSleepTicks() {
        return this.minSleepTicks;
    }

    public void setMinSleepTicks(int ticks) {
        this.minSleepTicks = ticks;
    }

    public int getTaskID() {
        return this.taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void countSleeping() {
        int sleeping = 0;

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online.isSleeping()) {
                sleeping++;
            }
        }

        this.setNumSleeping(sleeping);

        if (sleeping >= this.MIN_SLEEPING) {
            this.minSleepTicks++;
        }
        else {
            this.minSleepTicks = 0;
        }
    }

    public void init() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CommandTweaks.getInstance(), new Runnable() {
            @Override
            public void run(){
                CommandTweaks.singlePlayerSleep.countSleeping();

                if (CommandTweaks.singlePlayerSleep.getNumSleepingChanged()) {
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        // Utils.sendMessage(online, CommandTweaks.singlePlayerSleep.getNumSleeping() + "/" + Bukkit.getOnlinePlayers().size() + " players are sleeping.");
                    }

                    CommandTweaks.singlePlayerSleep.setNumSleepingChanged(false);
                }

                if (CommandTweaks.singlePlayerSleep.getMinSleepTicks() >= 40) {
                    Player sleeping = null;

                    for (Player online : Bukkit.getOnlinePlayers()) {
                        if (online.isSleeping()) {
                            sleeping = online;
                        }
                    }


                    CommandTweaks.singlePlayerSleep.fastForward(sleeping);
                }

            }
        }, 0, 1L);
    }


    public void fastForward(Player player) {
        if (this.getTaskID() != -1) {
            return;
        }

        int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(CommandTweaks.getInstance(), new Runnable() {
            @Override
            public void run(){


                player.getWorld().setTime(player.getWorld().getFullTime() + 1000L);

                if (player.getWorld().getFullTime() % 24000 > 1000) {
                    Bukkit.getScheduler().cancelTask(CommandTweaks.singlePlayerSleep.getTaskID());
                    CommandTweaks.singlePlayerSleep.setTaskID(-1);
                }


            }
        }, 0, 10L);

        this.setTaskID(taskID);
    }
}
