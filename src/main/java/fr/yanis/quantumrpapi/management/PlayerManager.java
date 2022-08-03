package fr.yanis.quantumrpapi.management;

import fr.yanis.quantumrpapi.ApiMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private ApiMain main;
    private UUID uuid, uuidofmaried;
    private String playername, gang, entreprise, sexe, firstname, secondname;
    private JobList job;
    private RankList rank;
    private int age, taille;
    private float money;
    public static HashMap<Player, PlayerManager> Players = new HashMap<>();

    public PlayerManager(ApiMain main, UUID uuid, String playername, String firstname, String secondname, JobList job, RankList rank, float money, UUID uuidofmaried, String gang, String entreprise, int taille, String sexe, int age){
        this.uuid = uuid;
        this.uuidofmaried = uuidofmaried;
        this.playername = playername;
        this.firstname = firstname;
        this.secondname = secondname;
        this.entreprise = entreprise;
        this.gang = gang;
        this.job = job;
        this.money = money;
        this.sexe = sexe;
        this.taille = taille;
        this.age = age;
        this.main = main;
        this.rank = rank;

        Players.put(Bukkit.getPlayer(uuid), this);
    }

    public static boolean existPlayer(Player player){
        return Players.containsKey(player);
    }

    public static PlayerManager getPlayerInSkript(String playername){ return Players.get(Bukkit.getPlayer(playername)); }
    public static PlayerManager getPlayer (Player p) { return Players.get(p); }

    public String getPlayerName(){
        return playername;
    }
    public int getAge(){ return age; }
    public String getFirstName(){ return  firstname; }
    public String getSecondName(){
        return secondname;
    }
    public String getGang(){ return gang; }
    public String getEntreprise(){ return entreprise; }
    public String getSexe(){ return sexe; }
    public UUID getUuidOfMaried(){ return uuidofmaried; }
    public JobList getJob(){
        return job;
    }
    public RankList getRank(){
        return rank;
    }
    public float getMoney(){ return money; }
    public int getTaille(){ return taille; }

    public void setAge(int age){ this.age = age; }
    public void setFirstName(String firstName){ this.firstname = firstName; }
    public void setSecondName(String secondName){ this.secondname = secondName; }
    public void setSexe(String sexe){ this.sexe = sexe; }
    public void setTaille(int taille){ this.taille = taille; }
    public void setGang(String gang){ this.gang = gang; }
    public void setEntreprise(String entreprise){ this.entreprise = entreprise; }
    public void setUuidOfMaried(UUID uuidofmaried){ this.uuidofmaried = uuidofmaried; }
    public void setPlayerName(String playername) {
        this.playername = playername;
    }
    public void setJob(JobList job) {
        this.job = job;
    }
    public void setMoney(float money) { this.money = money; }
    public void addMoney(float money) { this.money = this.money + money; }
    public void removeMoney(float money) { this.money = this.money - money; }
    public void setRank(RankList rank) { this.rank = rank; }

}