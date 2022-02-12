create table event
(
    datetime datetime not null,
    id varchar(16) not null,
    constraint event_id_uindex
        unique (id)
);

alter table event
    add primary key (id);

create table message
(
    event_id varchar(16) not null,
    sender_id varchar(16) not null,
    recipient_id varchar(16) not null,
    content json not null
);

create table `option`
(
    id int(11) unsigned auto_increment
        primary key,
    type varchar(15) not null,
    name varchar(50) not null
);

create table order_item
(
    order_id int(11) unsigned not null,
    product_id int(11) unsigned not null,
    price int(11) unsigned not null,
    quantity int unsigned not null
);

create table order_item_option_mapping
(
    order_id int(11) unsigned not null,
    product_id int(11) unsigned not null,
    option_id int(11) unsigned not null,
    extra_fee int(11) unsigned not null
);

create table orders
(
    id int(11) unsigned auto_increment
        primary key,
    user_id int(11) unsigned not null,
    status varchar(15) default 'PENDING' not null,
    datetime datetime default CURRENT_TIMESTAMP not null
);

create table product
(
    id int(11) unsigned auto_increment
        primary key,
    name varchar(50) not null,
    price int not null,
    image_url varchar(255) not null
);

create table product_option_mapping
(
    product_id int(11) unsigned not null,
    option_id int(11) unsigned not null,
    extra_fee int(11) unsigned not null
);

create table user
(
    id int unsigned auto_increment
        primary key,
    facebook_id varchar(16) not null,
    user_ref varchar(50) null,
    constraint user_facebook_id_uindex
        unique (facebook_id)
);

create table user_conversation_context
(
    user_id int unsigned not null,
    facebook_id varchar(16) not null,
    conversation_context varchar(20) not null,
    constraint user_conversation_context_user_id_uindex
        unique (user_id)
);

create table user_order_context
(
    user_id int not null,
    facebook_id varchar(16) not null,
    order_context varchar(20) not null,
    catalogue_data json default (json_object()) not null,
    constraint user_order_context_user_id_uindex
        unique (user_id)
);

create index facebook_id
    on user_order_context (facebook_id);

create index facebook_id
    on user_order_context (facebook_id);

create table webhook_request
(
    id int(11) unsigned auto_increment
        primary key,
    header varchar(500) not null,
    event json not null,
    date_time datetime default CURRENT_TIMESTAMP not null
);

