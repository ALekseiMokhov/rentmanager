insert into user
(id,full_name,password,email,phone_number,role,has_valid_sub,subscription_id,privilege)
values
(1,'Alexei Mokhov','$2y$10$mfxi52vx8rkErnRYafB9lO8DqquyXRVbFL7XTrz7xd58uOZjJ2bty','rentservicetest@yahoo.com','89995554342','ROLE_ADMIN',false,null,'PARTNER') ,
(2,'Ann Jane','$2y$10$mfxi52vx8rkErnRYafB9lO8DqquyXRVbFL7XTrz7xd58uOZjJ2bty','example@gmail.com','89995320049','ROLE_MANAGER',false,null,'PARTNER') ,
(3,'Matt Brown','$2y$10$9LhWA0PW7kmjXBPeZcuNMeF0TrZiMCt2y6HhXldxLW7smNbekGc8K','simple@yandex.com','81215557822','ROLE_USER',false,null,'NEWBIE') ,
(4,'Malcolm X','$2y$10$9LhWA0PW7kmjXBPeZcuNMeF0TrZiMCt2y6HhXldxLW7smNbekGc8K','not_simple@yandex.com','81215557833','ROLE_USER',false,null,'NEWBIE') ;
