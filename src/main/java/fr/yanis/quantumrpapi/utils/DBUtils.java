package fr.yanis.quantumrpapi.utils;

import fr.yanis.quantumrpapi.database.DatabaseManager;
import fr.yanis.quantumrpapi.management.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

    //===================================
    // Base De Données - Sauvegarde
    //===================================

    public void saveAll(){
        Bukkit.broadcastMessage("§cEnregistrement des données dans la base de donnée ! §4Un coup de lag peut se faire ressentir !");
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            savePlayer(PlayerManager.getPlayer(onlinePlayer));
        }
    }

    public void savePlayer(PlayerManager playerManager){
        DBSetInfo("Job", String.valueOf(playerManager.getJob().getId()), "users_informations", "uuid", Bukkit.getPlayer(playerManager.getPlayerName()).getUniqueId().toString());
        DBSetInfo("Rank", String.valueOf(playerManager.getRank().getId()), "users_informations", "uuid", Bukkit.getPlayer(playerManager.getPlayerName()).getUniqueId().toString());
        DBSetInfo("money", String.valueOf(playerManager.getMoney()), "users_informations", "uuid", Bukkit.getPlayer(playerManager.getPlayerName()).getUniqueId().toString());
        DBSetInfo("uuidofmaried", String.valueOf(playerManager.getUuidOfMaried()), "users_informations", "uuid", Bukkit.getPlayer(playerManager.getPlayerName()).getUniqueId().toString());
        DBSetInfo("entreprise", String.valueOf(playerManager.getEntreprise()), "users_informations", "uuid", Bukkit.getPlayer(playerManager.getPlayerName()).getUniqueId().toString());
        DBSetInfo("gang", String.valueOf(playerManager.getGang()), "users_informations", "uuid", Bukkit.getPlayer(playerManager.getPlayerName()).getUniqueId().toString());
        DBSetInfo("sexe", String.valueOf(playerManager.getSexe()), "users_informations", "uuid", Bukkit.getPlayer(playerManager.getPlayerName()).getUniqueId().toString());
        DBSetInfo("taille", String.valueOf(playerManager.getTaille()), "users_informations", "uuid", Bukkit.getPlayer(playerManager.getPlayerName()).getUniqueId().toString());
        DBSetInfo("firstname", String.valueOf(playerManager.getFirstName()), "users_informations", "uuid", Bukkit.getPlayer(playerManager.getPlayerName()).getUniqueId().toString());
        DBSetInfo("secondname", String.valueOf(playerManager.getSecondName()), "users_informations", "uuid", Bukkit.getPlayer(playerManager.getPlayerName()).getUniqueId().toString());
    }

    //===================================
    // Base De Données - Create
    //===================================

    public void createNewPlayer(Player player) {
        try {
            final Connection connection = DatabaseManager.QuantumRPBDD.getDatabaseAccess().getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users_informations (uuid, name, Job, Rank, money, uuidofmaried, entreprise, gang, sexe, taille, firstname, secondname, age) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, player.getUniqueId().toString());
            preparedStatement.setString(2, player.getName());
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, 0);
            preparedStatement.setFloat(5, 0.0F);
            preparedStatement.setString(6, player.getUniqueId().toString());
            preparedStatement.setString(7, "Aucune");
            preparedStatement.setString(8, "Aucun");
            preparedStatement.setString(9, "Non-Defini");
            preparedStatement.setInt(10, 0);
            preparedStatement.setString(11, "Aucun");
            preparedStatement.setString(12, "Aucun");
            preparedStatement.setInt(13, 0);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            final Connection connection = DatabaseManager.QuantumRPBDD.getDatabaseAccess().getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO formations_players (uuid, farmer, archaeologist, chef, samu, psychologist, Detective, DeliveryMan) VALUES (?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, player.getUniqueId().toString());
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            preparedStatement.setInt(8, 0);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    //===================================
    // Base De Données - Vérification
    //===================================

    public boolean ifHaveAAccount(Player player) {
        try {
            final Connection connection = DatabaseManager.QuantumRPBDD.getDatabaseAccess().getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users_informations WHERE uuid = ?");

            preparedStatement.setString(1, player.getUniqueId().toString());
            preparedStatement.executeQuery();

            final ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {

            } else {
                return false;
            }

            connection.close();
            return true;
        } catch (SQLException event) {
            event.printStackTrace();
            return false;
        }
    }

    //===================================
    // Base De Données - Set
    //===================================

    public void DBSetInfo(String setting, String settingsvalue, String table_name, String where, String whereFinding) {
        try {
            final Connection connection = DatabaseManager.QuantumRPBDD.getDatabaseAccess().getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + table_name + " SET " + setting + "='" + settingsvalue + "' WHERE " + where + " = ?");

            preparedStatement.setString(1, whereFinding);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException event) {
            event.printStackTrace();
        }
    }

    //===================================
    // Base De Données - Get
    //===================================

    public String GetStringInfos(String geting, String table_name, String where, String whereFinding) {
        try {
            final Connection connection = DatabaseManager.QuantumRPBDD.getDatabaseAccess().getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table_name + " WHERE " + where + " = ?");

            preparedStatement.setString(1, whereFinding);
            preparedStatement.executeQuery();

            final ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                String getted = resultSet.getString(geting);
                connection.close();
                return getted;
            } else {

            }

            connection.close();
        } catch (SQLException event) {
            event.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean GetBooleanInfos(String geting, String table_name, String where, String whereFinding) {
        try {
            final Connection connection = DatabaseManager.QuantumRPBDD.getDatabaseAccess().getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table_name + " WHERE " + where + " = ?");

            preparedStatement.setString(1, whereFinding);
            preparedStatement.executeQuery();

            final ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                boolean getted = resultSet.getBoolean(geting);
                connection.close();
                return getted;
            } else {

            }

            connection.close();
        } catch (SQLException event) {
            event.printStackTrace();
            return false;
        }
        return false;
    }

    public int GetIntInfos(String geting, String table_name, String where, String whereFinding) {
        try {
            final Connection connection = DatabaseManager.QuantumRPBDD.getDatabaseAccess().getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table_name + " WHERE " + where + " = ?");

            preparedStatement.setString(1, whereFinding);
            preparedStatement.executeQuery();

            final ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                int getted = resultSet.getInt(geting);
                connection.close();
                return getted;
            } else {

            }

            connection.close();
        } catch (SQLException event) {
            event.printStackTrace();
            return 0;
        }
        return 0;
    }

    public float GetFloatInfos(String geting, String table_name, String where, String whereFinding) {
        try {
            final Connection connection = DatabaseManager.QuantumRPBDD.getDatabaseAccess().getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table_name + " WHERE " + where + " = ?");

            preparedStatement.setString(1, whereFinding);
            preparedStatement.executeQuery();

            final ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()) {
                float getted = resultSet.getFloat(geting);
                connection.close();
                return getted;
            } else {

            }

            connection.close();
        } catch (SQLException event) {
            event.printStackTrace();
            return 0.0F;
        }
        return 0.0F;
    }
}
