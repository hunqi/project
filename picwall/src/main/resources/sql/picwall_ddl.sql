create table if not exists t_user
(
    id BIGINT auto_increment,
    name varchar(30) unique not null,
    password varchar(100) not null,
    cdate timestamp default CURRENT_TIMESTAMP,
    edate timestamp default CURRENT_TIMESTAMP,
    creator varchar(20) default 'system',
    editor varchar(20) default 'system',
    PRIMARY KEY (id)
);

create table if not exists t_folder
(
    id BIGINT auto_increment,
    name varchar(30) not null,
    id_user BIGINT,
    cdate timestamp default CURRENT_TIMESTAMP,
    edate timestamp default CURRENT_TIMESTAMP,
    creator varchar(20) default 'system',
    editor varchar(20) default 'system',
    PRIMARY KEY (id),
    FOREIGN KEY (id_user) references t_user(id)
);

create table if not exists t_pic
(
    id BIGINT auto_increment,
    name varchar(50) not null,
    data blob not null,
    id_folder BIGINT,
    cdate timestamp default CURRENT_TIMESTAMP,
    edate timestamp default CURRENT_TIMESTAMP,
    creator varchar(20) default 'system',
    editor varchar(20) default 'system',
    PRIMARY KEY(id),
    FOREIGN KEY(id_folder) references t_folder(id)
);