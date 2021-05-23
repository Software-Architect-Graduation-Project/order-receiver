#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE TABLE public."order_requested" (
        id serial NOT NULL,
        client_id varchar NOT NULL,
        created_on timestamp(0) NOT NULL DEFAULT now(),
        payment_plan varchar NOT NULL,
        products varchar NOT null,
        PRIMARY KEY (id)
    );
EOSQL

echo "Table created!"