package fr.yanis.quantumrpapi.timers;

import fr.yanis.quantumrpapi.ApiMain;
import org.bukkit.scheduler.BukkitRunnable;

public class DBSave extends BukkitRunnable {

    private int timer = 1800;
    private ApiMain main;

    public DBSave(ApiMain main){
        this.main = main;
    }

    @Override
    public void run() {

        if(timer == 0){
            main.getDbUtils().saveAll();
            timer = 3600;
        }

        timer--;
    }
}
