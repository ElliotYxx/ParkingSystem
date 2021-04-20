/** 用户表 **/
drop table if exists parkinglot.user;
create table parkinglot.user
(
    id bigint auto_increment comment '用户id'
        primary key,
    username varchar(50) not null comment '用户名',
    password varchar(255) not null comment '用户密码',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    status int default 0 not null comment '用户状态 默认为正常',
    role_id int default 1 not null comment '用户对应的角色id 默认为USER'
)default charset utf8 comment '用户信息表';
insert into parkinglot.user(username, password, role_id) values('admin', 'admin', 2);

/** 停车临时记录表 **/
drop table if exists parkinglot.parking_temp;
create table parkinglot.parking_temp
(
    id bigint auto_increment comment '车辆id'
        primary key,
    plate_number varchar(20) not null comment '车牌号码',
    color varchar(10) not null comment '车牌颜色',
    create_time datetime default CURRENT_TIMESTAMP comment '进入停车场的时间',
    file_url varchar(255) not null comment '车辆图片url'
)default charset utf8 comment '停车临时记录表';

/** 停车记录表 **/
drop table if exists parkinglot.parking_record;
create table parkinglot.parking_record
(
    id bigint auto_increment comment '车辆id'
        primary key,
    plate_number varchar(20) not null comment '车牌号码',
    color varchar(10) not null comment '车牌颜色',
    create_time datetime not null comment '进入停车场的时间',
    left_time datetime default CURRENT_TIMESTAMP comment '离开停车场的时间',
    cost decimal(18, 2) not null comment '本次停车收取的费用',
    file_url varchar(255) not null comment '车辆图片url'
)default charset utf8 comment '停车记录表';

