CREATE TABLE IF NOT EXISTS parking_machine (id int NOT NULL AUTO_INCREMENT, longitude int, latitude int, PRIMARY KEY (`id`));
CREATE TABLE IF NOT EXISTS user (id int NOT NULL AUTO_INCREMENT, email varchar(100), password varchar(100), vip BOOL, PRIMARY KEY (`id`));



INSERT INTO `parking_machine` (`id`, `longitude`, `latitude`) VALUES (1, 123, 789);
INSERT INTO `user` (`id`, `email`, `password`, `vip`) VALUES (1, 'asd@bp.pl', 'qwer', TRUE);