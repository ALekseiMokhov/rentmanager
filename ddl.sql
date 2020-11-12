drop schema if exists senla;
create schema senla;
use senla;

create table user(id bigint not null auto_increment primary key, full_name varchar(255) not null unique, password varchar(255) not null, id_credit_card bigint , role varchar(64),privilege varchar(64));

create table card(id bigint not null auto_increment primary key, credit_card_number bigint not null,available_funds double, expiration_date timestamp not null, id_user bigint);
alter table card  add constraint fk_card_user_id foreign key (id_user) references user(id) on delete cascade;
alter table user add constraint fk_user_card_id foreign key(id_credit_card) references card(id) on delete cascade on update cascade;


create table vehicle(id bigint not null auto_increment primary key, model_name varchar(255), vehicle_type varchar(255), is_muscular boolean, is_childish boolean, rent_price double, fine_price double);


create table rent_point(id bigint not null auto_increment primary key, point_name varchar(255),type varchar(64), coordinate geometry);
create table address(id bigint not null auto_increment primary key, city varchar(64) not null, street varchar(255) not null, building_number int not null);
alter table address add constraint fk_rent_point_adress foreign key (id	) references rent_point (id) on delete cascade on update cascade;

create table message(id bigint not null auto_increment primary key, text varchar(1024) not null,id_user bigint);
alter table message add constraint fk_message_user_id foreign key (id_user) references user(id);

create table subscription (id bigint not null auto_increment primary key,  price double not null, date_ordered timestamp not null, 
date_beginning timestamp not null, date_expired timestamp not null ,id_user bigint);

create table user_subscription(id_user bigint, id_subscription bigint );
alter table user_subscription add constraint fk_user_id foreign key(id_user) references user(id);
alter table user_subscription add constraint fk_subscription_id foreign key(id_subscription) references user(id);

create table `order`(id bigint not null auto_increment primary key, creation_time timestamp not null, finish_time timestamp,blocked_funds double not null, total_price double not null, 
					has_valid_abonement boolean not null, order_status varchar(64) not null, id_user bigint, id_vehicle bigint, id_rent_point bigint);
alter table `order` add constraint fk_order_user foreign key(id_user) references user(id);
alter table `order` add constraint fk_order_vehicle foreign key(id_vehicle) references vehicle(id);
alter table `order` add constraint fk_order_rent_point foreign key(id_rent_point) references rent_point(id);


