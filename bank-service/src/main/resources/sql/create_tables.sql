create table fin_autotest_result(
    id      int(20) unsigned auto_increment comment '主键' ,
    pack_order_id       varchar(20) not null ,
    student_id          varchar(20) not null ,
    project_ver         varchar(20) not null ,
    result              varchar(10) not null ,
    create_time         timestamp default current_timestamp not null ,
    update_time         timestamp default current_timestamp on update current_timestamp,
    primary key (id)
)ENGINE=INNODB DEFAULT CHAR SET=UTF8 comment '自动化测试结果表';
