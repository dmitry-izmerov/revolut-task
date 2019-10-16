INSERT INTO accounts(name, currency, balance) values
    ('rub account 1', 'RUB', 1000),
    ('rub account 2', 'RUB', 1000),
    ('rub account 3', 'RUB', 1000),
    ('rub account 4', 'RUB', 0),
    ('rub account 5', 'RUB', 1000),
    ('rub account 6', 'RUB', 0);

INSERT INTO transfers(id, from_account, to_account, currency, amount, date)
    values (1, 1, 2, 'RUB', 100, now());
