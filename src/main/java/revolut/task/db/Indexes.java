/*
 * This file is generated by jOOQ.
 */
package revolut.task.db;


import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;

import revolut.task.db.tables.Accounts;
import revolut.task.db.tables.Transfers;


/**
 * A class modelling indexes of tables of the <code>PUBLIC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index PRIMARY_KEY_A = Indexes0.PRIMARY_KEY_A;
    public static final Index FK_TRANSFERS_ACCOUNTS_FROM_ACCOUNT_INDEX_E = Indexes0.FK_TRANSFERS_ACCOUNTS_FROM_ACCOUNT_INDEX_E;
    public static final Index FK_TRANSFERS_ACCOUNTS_TO_ACCOUNT_INDEX_E = Indexes0.FK_TRANSFERS_ACCOUNTS_TO_ACCOUNT_INDEX_E;
    public static final Index PRIMARY_KEY_E = Indexes0.PRIMARY_KEY_E;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index PRIMARY_KEY_A = Internal.createIndex("PRIMARY_KEY_A", Accounts.ACCOUNTS, new OrderField[] { Accounts.ACCOUNTS.ID }, true);
        public static Index FK_TRANSFERS_ACCOUNTS_FROM_ACCOUNT_INDEX_E = Internal.createIndex("FK_TRANSFERS_ACCOUNTS_FROM_ACCOUNT_INDEX_E", Transfers.TRANSFERS, new OrderField[] { Transfers.TRANSFERS.FROM_ACCOUNT }, false);
        public static Index FK_TRANSFERS_ACCOUNTS_TO_ACCOUNT_INDEX_E = Internal.createIndex("FK_TRANSFERS_ACCOUNTS_TO_ACCOUNT_INDEX_E", Transfers.TRANSFERS, new OrderField[] { Transfers.TRANSFERS.TO_ACCOUNT }, false);
        public static Index PRIMARY_KEY_E = Internal.createIndex("PRIMARY_KEY_E", Transfers.TRANSFERS, new OrderField[] { Transfers.TRANSFERS.ID }, true);
    }
}
