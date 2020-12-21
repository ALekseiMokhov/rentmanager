insert into rent_point
(id,coordinate,point_name,type)
values
(1,ST_GeomFromText('POINT(1 1)'),'Main','CENTER'),
(2,ST_GeomFromText('POINT(134 585)'),'Square point','SECOND_LINE'),
(3,ST_GeomFromText('POINT(538 14)'),'Airport','AIRPORT'),
(4,ST_GeomFromText('POINT(47 902)'),'West end','SUBURBIA')     ;

insert into address
(id,city,street,building_number)
values
(1,'Orel','Lenina',2)  ,
(2,'Orel','Schepnaya',34)  ,
(3,'Livny','Centralnaya',7)  ,
(4,'Orel','Naugorskaya',71)  ;
