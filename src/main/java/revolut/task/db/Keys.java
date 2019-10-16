/*
 * This file is generated by jOOQ.
 */
package revolut.task.db;


import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import revolut.task.db.tables.Accounts;
import revolut.task.db.tables.Transfers;
import revolut.task.db.tables.records.AccountsRecord;
import revolut.task.db.tables.records.TransfersRecord;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>PUBLIC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AccountsRecord, Long> IDENTITY_ACCOUNTS = Identities0.IDENTITY_ACCOUNTS;
    public static final Identity<TransfersRecord, Long> IDENTITY_TRANSFERS = Identities0.IDENTITY_TRANSFERS;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AccountsRecord> CONSTRAINT_A = UniqueKeys0.CONSTRAINT_A;
    public static final UniqueKey<TransfersRecord> CONSTRAINT_E = UniqueKeys0.CONSTRAINT_E;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<TransfersRecord, AccountsRecord> FK_TRANSFERS_ACCOUNTS_FROM_ACCOUNT = ForeignKeys0.FK_TRANSFERS_ACCOUNTS_FROM_ACCOUNT;
    public static final ForeignKey<TransfersRecord, AccountsRecord> FK_TRANSFERS_ACCOUNTS_TO_ACCOUNT = ForeignKeys0.FK_TRANSFERS_ACCOUNTS_TO_ACCOUNT;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<AccountsRecord, Long> IDENTITY_ACCOUNTS = Internal.createIdentity(Accounts.ACCOUNTS, Accounts.ACCOUNTS.ID);
        public static Identity<TransfersRecord, Long> IDENTITY_TRANSFERS = Internal.createIdentity(Transfers.TRANSFERS, Transfers.TRANSFERS.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AccountsRecord> CONSTRAINT_A = Internal.createUniqueKey(Accounts.ACCOUNTS, "CONSTRAINT_A", Accounts.ACCOUNTS.ID);
        public static final UniqueKey<TransfersRecord> CONSTRAINT_E = Internal.createUniqueKey(Transfers.TRANSFERS, "CONSTRAINT_E", Transfers.TRANSFERS.ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<TransfersRecord, AccountsRecord> FK_TRANSFERS_ACCOUNTS_FROM_ACCOUNT = Internal.createForeignKey(revolut.task.db.Keys.CONSTRAINT_A, Transfers.TRANSFERS, "FK_TRANSFERS_ACCOUNTS_FROM_ACCOUNT", Transfers.TRANSFERS.FROM_ACCOUNT);
        public static final ForeignKey<TransfersRecord, AccountsRecord> FK_TRANSFERS_ACCOUNTS_TO_ACCOUNT = Internal.createForeignKey(revolut.task.db.Keys.CONSTRAINT_A, Transfers.TRANSFERS, "FK_TRANSFERS_ACCOUNTS_TO_ACCOUNT", Transfers.TRANSFERS.TO_ACCOUNT);
    }
}
