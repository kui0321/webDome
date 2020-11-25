CREATE TABLE `users` (
`userid` int(11) NOT NULL AUTO_INCREMENT, 
`username` varchar(30) DEFAULT NULL, 
`userpwd` varchar(30) DEFAULT NULL, 
`usersex` varchar(2) DEFAULT NULL, 
`phonenumber` varchar(30) DEFAULT NULL, 
`qqnumber` varchar(20) DEFAULT NULL, PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;