package me.wilhelm.boardgames.other;

import me.wilhelm.boardgames.Boardgames;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class Timer {
    static Boardgames plugin;
    public Timer(Boardgames plugin) {
        Timer.plugin = plugin;
    }

    HashMap<String, Integer> timers = new HashMap<>();
    HashMap<String, Integer> bukkitTasks = new HashMap<>();

    public void setTimer(int lengthInSeconds, String timerID) {
        if (!timers.containsKey(timerID)) {
            timers.put(timerID, lengthInSeconds);
        }
    }

    public String getFormattedDuration(String timerID) {
        if (timers.containsKey(timerID)) {
            int minutes;
            int seconds;

            minutes = timers.get(timerID) / 60;
            seconds = timers.get(timerID) % 60;
            if (timers.get(timerID) >= 60) {
                if (seconds < 10) {
                    return minutes + ":0" + seconds;
                }
                return minutes + ":" + seconds;
            }
            if (timers.get(timerID) < 10) {
                return "0:0" + seconds;
            }
            return "0:" + seconds;
        }
        return "";
    }

    public int getUnformattedDuration(String timerID) {
        if (timers.containsKey(timerID)) {
            return timers.get(timerID);
        }
        return 0;
    }

    public void startTimer(String timerID) {
        if (!bukkitTasks.containsKey(timerID)) {
            BukkitTask timer = Bukkit.getScheduler().runTaskTimer(Timer.plugin, () -> {
                timers.put(timerID, timers.get(timerID) - 1);
                if (timers.get(timerID) == 0) {
                    pauseTimer(timerID);
                }
            }, 0L, 20L);
            bukkitTasks.put(timerID, timer.getTaskId());
        }
    }

    public void pauseTimer(String timerID) {
        if (bukkitTasks.containsKey(timerID)) {
            Bukkit.getScheduler().cancelTask(bukkitTasks.get(timerID));
        }
    }

    public void stopTimer(String timerID) {
        if (bukkitTasks.containsKey(timerID)) {
            Bukkit.getScheduler().cancelTask(bukkitTasks.get(timerID));
            bukkitTasks.remove(timerID);
        }
        timers.remove(timerID);
    }


    public static void delayTask(long ticks, Scheduler scheduler) {
        Bukkit.getScheduler().runTaskLater(plugin, scheduler::task, ticks);
    }
}
