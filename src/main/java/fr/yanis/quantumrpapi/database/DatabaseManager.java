package fr.yanis.quantumrpapi.database;

public enum DatabaseManager {

    QuantumRPBDD(new DatabaseCredentials("UNABLETOREAD", "UNABLETOREAD", "UNABLETOREAD", "UNABLETOREAD", 3306));

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
