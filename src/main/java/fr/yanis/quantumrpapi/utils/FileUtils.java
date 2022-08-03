package fr.yanis.quantumrpapi.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    //===================================
    // Fichier - Sauvegarde
    //===================================

    public void saveFile(File file, FileConfiguration config) {
        try {
            config.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
