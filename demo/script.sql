create database onlineshopdb;
use onlineshopdb;
create table admin
(
    id         bigint auto_increment
        constraint `PRIMARY`
        primary key,
    admin_name varchar(255) null,
    email      varchar(255) null,
    password   varchar(255) null
);

create table customer
(
    id              bigint auto_increment
        constraint `PRIMARY`
        primary key,
    account_balance bigint       not null,
    address         varchar(255) null,
    customer_name   varchar(255) null,
    email           varchar(255) null,
    password        varchar(255) null
);

create table order_details
(
    order_id     bigint auto_increment
        constraint `PRIMARY`
        primary key,
    product_id   int          not null,
    product_name varchar(255) null,
    unit_cost    double       not null,
    quantity     bigint       not null,
    total        double       not null
);

create table order_item
(
    order_id      bigint auto_increment
        constraint `PRIMARY`
        primary key,
    customer_name varchar(255) null,
    date_created  datetime     null,
    date_shipped  datetime     null,
    shipping_id   bigint       not null,
    user_id       int          not null
);

create table shipping_info
(
    shipping_id     bigint auto_increment
        constraint `PRIMARY`
        primary key,
    shipping_cost   bigint       not null,
    shipping_info   varchar(255) null,
    shipping_region varchar(255) null
);

create table shopping_cart
(
    cart_id    bigint auto_increment
        constraint `PRIMARY`
        primary key,
    date_added datetime null,
    product_id int      not null,
    quantity   int      not null
);


