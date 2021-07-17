# Order Receiver

## Setup Postgres

docker run -d \
    --name ecommerceOrder-receiver-db \
    -p 5432:5432 \
    -e POSTGRES_PASSWORD=postgres \
    -v $(pwd)/docker/setup-db.sh:/docker-entrypoint-initdb.d/setup-db.sh \
    postgres:13.3