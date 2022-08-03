package fr.yanis.quantumrpapi.management;

public enum JobList {

    Chômeur(1, "§bChômeur", 0),
    Eboueur(2, "§aEboueur", 1500),
    Taxi(3, "§aTaxi", 1700),
    Archeologue(4, "§6Archéologue", 3400),
    Cuisinier(5, "§7Cuisinier", 2000),
    SAMU(6, "§cSamu", 1800),
    Psychologue(7, "§dPsychologue", 2300),
    Tueur_a_gage(8, "§4Tueur A Gage", 0),
    Detective(9, "§0Detective", 1500),
    Livreur(10, "§3Livreur", 1900),
    Fermier(11, "§aFermier", 0),
    Maire(12, "§fMaire", 2000),
    AgentImmobilier(13, "§bAgent Immobilier", 2500);

    private String displayname;
    private int id, salary;

     JobList(int id, String displayname, int salary){
        this.id = id;
        this.displayname = displayname;
        this.salary = salary;
    }

    public String getDisplayname() {
        return displayname;
    }
    public int getSalary() {
        return salary;
    }
    public int getId() {
        return id;
    }

    public static JobList getJobById(int id){

        for(JobList jobList : JobList.values()){
            if(jobList.id == id){
                return jobList;
            }
        }

        return null;
    }

    public static JobList getJobByDisplayName(String displayname){

        for(JobList jobList : JobList.values()){
            if(jobList.displayname.equalsIgnoreCase(displayname)){
                return jobList;
            }
        }

        return null;
    }

}
