package fr.yanis.quantumrpapi;

import fr.yanis.quantumrpapi.management.JobList;
import fr.yanis.quantumrpapi.management.PlayerManager;
import fr.yanis.quantumrpapi.management.RankList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayersEvents implements Listener {

    private ApiMain main;

    public PlayersEvents(ApiMain main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        e.setJoinMessage("");

        // Vérifie si le joueur est enregistré dans la base de donnée
        if(!main.getDbUtils().ifHaveAAccount(player)){
            main.getDbUtils().createNewPlayer(player);
        }

        // Vérifie si le joueur est enregistré dans le cache
        if(!PlayerManager.existPlayer(player)){
            String firstName = main.getDbUtils().GetStringInfos("firstname", "users_informations", "uuid", player.getUniqueId().toString());
            String secondName = main.getDbUtils().GetStringInfos("secondname", "users_informations", "uuid", player.getUniqueId().toString());
            JobList job = JobList.getJobById(main.getDbUtils().GetIntInfos("job", "users_informations", "uuid", player.getUniqueId().toString()));
            RankList rank = RankList.getRankById(main.getDbUtils().GetIntInfos("rank", "users_informations", "uuid", player.getUniqueId().toString()));
            float money = main.getDbUtils().GetFloatInfos("money", "users_informations", "uuid", player.getUniqueId().toString());
            String uuidofmaried = main.getDbUtils().GetStringInfos("uuidofmaried", "users_informations", "uuid", player.getUniqueId().toString());
            String gang = main.getDbUtils().GetStringInfos("gang", "users_informations", "uuid", player.getUniqueId().toString());
            String entreprise = main.getDbUtils().GetStringInfos("entreprise", "users_informations", "uuid", player.getUniqueId().toString());
            int taille = main.getDbUtils().GetIntInfos("taille", "users_informations", "uuid", player.getUniqueId().toString());
            String sexe = main.getDbUtils().GetStringInfos("sexe", "users_informations", "uuid", player.getUniqueId().toString());
            int age = main.getDbUtils().GetIntInfos("age", "users_informations", "uuid", player.getUniqueId().toString());
            new PlayerManager(main, player.getUniqueId(), player.getName(), firstName, secondName, job, rank, money, UUID.fromString(uuidofmaried), gang, entreprise, taille, sexe, age);
        }

        // Vérifie si il y a eu un changement de pseudo
        if(player.getName() != main.getDbUtils().GetStringInfos("name", "users_informations", "uuid", player.getUniqueId().toString())){
            main.getDbUtils().DBSetInfo("name", player.getName(), "users_informations", "uuid", player.getUniqueId().toString());
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        String message = e.getMessage();
        PlayerManager playerManager = PlayerManager.getPlayer(player);

        e.setCancelled(true);
        if(playerManager.getRank().getId() == 0){
            Bukkit.broadcastMessage(playerManager.getJob().getDisplayname() + " §8- §9" + player.getName() + " §7» §f" + message);
        } else {
            Bukkit.broadcastMessage(playerManager.getRank().getDisplayname() + " §8- " + playerManager.getJob().getDisplayname() + " §8- " + " §9" + player.getName() + " §7» §f" + message);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage("");
        // Sauvegarde des données dans la base de donnée
        main.getDbUtils().savePlayer(PlayerManager.getPlayer(e.getPlayer()));
    }
}
