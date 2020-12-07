

    drop table if exists address;


    drop table if exists card;


    drop table if exists hibernate_sequence;


    drop table if exists message;


    drop table if exists `order`;


    drop table if exists rent_point;


    drop table if exists subscription;


    drop table if exists user;


    drop table if exists vehicle;


    drop table if exists vehicle_booked_dates;


    create table address(
       id bigint not null,
        building_number integer not null,
        city varchar(32) not null,
        street varchar(32) not null,
        primary key (id)
    ) engine=InnoDB;


    create table card (
       id bigint not null auto_increment,
        available_funds double precision,
        blocked_funds double precision,
        credit_card_number bigint not null,
        expiration_date DATE,
        valid_date DATE,
        full_name varchar(255),
        primary key (id)
    ) engine=InnoDB ;


    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB  ;


    insert into hibernate_sequence values ( 1 );


    create table message (
       id bigint not null auto_increment,
        date_time datetime,
        text longtext not null,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB  ;


    create table `order` (
       id bigint not null auto_increment,
        blocked_funds double precision not null,
        creation_time datetime not null,
        card_number bigint not null,
        finish_time datetime,
        has_valid_subscription bit not null,
        start_time datetime not null,
        order_status varchar(255) not null,
        total_price double precision,
        id_user bigint,
        id_vehicle bigint,
        primary key (id)
    ) engine=InnoDB ;


    create table rent_point (
       id bigint not null auto_increment,
        coordinate GEOMETRY not null,
        point_name varchar(255) not null,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB ;


    create table subscription (
       id bigint not null auto_increment,
        date_expired date not null,
        isExpired bit not null,
        date_order date not null,
        price double precision not null,
        date_beginning date not null,
        primary key (id)
    ) engine=InnoDB;


    create table user (
       id bigint not null auto_increment,
        email varchar(255),
        has_valid_sub boolean default false,
        password varchar(255) not null,
        phone_number bigint,
        privilege varchar(255),
        role varchar(255),
        full_name varchar(35) not null,
        subscription_id bigint,
        primary key (id)
    ) engine=InnoDB;


    create table vehicle (
       id bigint not null,
        fine_price double precision not null,
        is_childish bit not null,
        is_human_powered bit not null,
        model_name varchar(64) not null,
        rent_price double precision not null,
        type varchar(255) not null,
        rent_point_id bigint,
        primary key (id)
    ) engine=InnoDB;


    create table vehicle_booked_dates (
       id bigint not null,
        booked_dates date
    ) engine=InnoDB;


    alter table card
       add constraint UK_credit_card_number unique (credit_card_number);


    alter table user
       add constraint UK_full_name unique (full_name);


    alter table user
       add constraint UK_phone_number unique (phone_number);


    alter table address
       add constraint address_rent_point
       foreign key (id)
       references rent_point (id);


    alter table card
       add constraint card_user
       foreign key (full_name)
       references user (full_name);


    alter table message
       add constraint message_user
       foreign key (user_id)
       references user (id) ;


    alter table `order`
       add constraint order_user
       foreign key (id_user)
       references user (id) ;


    alter table `order`
       add constraint order_vehicle
       foreign key (id_vehicle)
       references vehicle (id);


    alter table user
       add constraint user_subscription
       foreign key (subscription_id)
       references subscription (id) ;


    alter table vehicle
       add constraint vehicle_rent_point
       foreign key (rent_point_id)
       references rent_point (id) ;


    alter table vehicle_booked_dates
       add constraint booked_dates_vehicle
       foreign key (id)
       references vehicle (id) ;
