drop table if exists accounts;
drop table if exists transfers;

create table if not exists accounts (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    currency CHAR(3) NOT NULL,
    balance DECIMAL NOT NULL,
    CONSTRAINT balance_positive CHECK(balance >= 0)
);

create table if not exists transfers (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    from_account BIGINT NOT NULL,
    to_account BIGINT NOT NULL,
    amount DECIMAL NOT NULL,
    currency CHAR(3) NOT NULL,
    date TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_transfers_accounts_from_account FOREIGN KEY (from_account) REFERENCES accounts(id),
    CONSTRAINT fk_transfers_accounts_to_account FOREIGN KEY (to_account) REFERENCES accounts(id)
);

