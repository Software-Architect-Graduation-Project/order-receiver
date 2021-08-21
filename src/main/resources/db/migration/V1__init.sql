CREATE TABLE public."order_requested" (
    id serial NOT NULL,
    client_id varchar NOT NULL,
    created_on timestamp(0) NOT NULL DEFAULT now(),
    payment_plan varchar NOT NULL,
    products varchar NOT null,
    PRIMARY KEY (id)
);