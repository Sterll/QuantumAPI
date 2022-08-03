package fr.yanis.quantumrpapi;

import fr.yanis.quantumrpapi.database.DatabaseManager;
import fr.yanis.quantumrpapi.timers.DBSave;
import fr.yanis.quantumrpapi.utils.DBUtils;
import fr.yanis.quantumrpapi.utils.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class ApiMain extends JavaPlugin {

    private DBUtils dbUtils;
    private FileUtils fileUtils;

    @Override
    public void onEnable() {
        this.dbUtils = new DBUtils();
        this.fileUtils = new FileUtils();
        getServer().getPluginManager().registerEvents(new PlayersEvents(this), this);
        DatabaseManager.initAllDatabaseConnection();
        DBSave task = new DBSave(this);
        task.runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
        DatabaseManager.closeAllDatabaseConnections();
    }

    public DBUtils getDbUtils() {
        return dbUtils;
    }
    public FileUtils getFileUtils() {
        return fileUtils;
    }
}
