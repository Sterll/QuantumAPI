package fr.yanis.quantumrpapi.database;

public enum DatabaseManager {

    QuantumRPBDD(new DatabaseCredentials("65.21.131.103", "u17_7f9q2SLwWP", "7=i89LRsdr2T0eYZid=tjP+R", "s17_QuantumRP", 3306));

    private DatabaseAccess databaseAccess;
    DatabaseManager(DatabaseCredentials credentials){
        this.databaseAccess = new DatabaseAccess(credentials);
    }

    public DatabaseAccess getDatabaseAccess(){
        return databaseAccess;
    }

    public static void initAllDatabaseConnection(){
        for(DatabaseManager databaseManager : values()){
            databaseManager.databaseAccess.initPool();
        }
    }
    public static void closeAllDatabaseConnections(){
        for(DatabaseManager databaseManager : values()){
            databaseManager.databaseAccess.closePool();
        }
    }

}
