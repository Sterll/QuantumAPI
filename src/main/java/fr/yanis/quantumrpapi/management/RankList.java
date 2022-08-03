package fr.yanis.quantumrpapi.management;

public enum RankList {

    Joueur(0, ""),
    Moderateur(1, "§2Modérateur"),
    Buildeur(2, "§1Buildeur"),
    Modelisateur(3, "§3Modélisateur"),
    Developpeur(4, "§bDéveloppeur"),
    Administrateur(5, "§cAdmin");


    private String displayname;
    private int id;

     RankList(int id, String displayname){
        this.id = id;
        this.displayname = displayname;
    }

    public String getDisplayname() {
        return displayname;
    }
    public int getId() {
        return id;
    }

    public static RankList getRankById(int id){

        for(RankList rankList : RankList.values()){
            if(rankList.id == id){
                return rankList;
            }
        }

        return null;
    }

    public static RankList getRankByDisplayName(String displayname){

        for(RankList rankList : RankList.values()){
            if(rankList.displayname.equalsIgnoreCase(displayname)){
                return rankList;
            }
        }

        return null;
    }

}
