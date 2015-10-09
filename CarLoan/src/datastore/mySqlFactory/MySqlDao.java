package datastore.mySqlFactory;

import datastore.mySqlDB.SqlQueryFactory;

/**
 * Classe che contiene gli attributi in comuni dei DAO MYSQL.
 * */
public class MySqlDao {

    /**
     * Factory delle query sql.
     * */
    protected SqlQueryFactory sqlFac;

    /**
     * Costruttore della classe.
     * */
    public MySqlDao() {
        sqlFac = SqlQueryFactory.getInstance();
    }

}
