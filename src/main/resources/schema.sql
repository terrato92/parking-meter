CREATE TABLE IF NOT EXISTS parking_machine (id int NOT NULL AUTO_INCREMENT, longitude int, latitude int, PRIMARY KEY (`id`));
CREATE TABLE IF NOT EXISTS user (id int NOT NULL AUTO_INCREMENT, email varchar(100), password varchar(100), vip BOOL, PRIMARY KEY (`id`));
CREATE TABLE IF NOT EXISTS ticket (id int NOT NULL AUTO_INCREMENT, start_date TIMESTAMP, endDate TIMESTAMP, plate varchar(100), user_id INT, PRIMARY KEY (`id`));



INSERT INTO `parking_machine` (`id`, `longitude`, `latitude`) VALUES (1, 123, 789);
INSERT INTO `user` (`id`, `email`, `password`, `vip`) VALUES (1, 'asd@bp.pl', 'qwer', FALSE);
INSERT INTO `ticket` (`id`, `start_date`, `plate`, `user_id`) VALUES (1, CURRENT_TIMESTAMP, 'lwwl34r4', 1);