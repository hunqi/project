drop table if exists t_user;

create table t_user (

	id int(11) not null auto_increment,
	user_name varchar(40) not null,
	password varchar(255) not null,
	age int(4) not null,
	primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into t_user(id, user_name, password, age) values (1, '测试', 'sdfsdf', 26);
