drop table accompany;
create table accompany (
    accompany_id bigint primary key auto_increment,
    member_id bigint not null,
    displayName varchar(50) not null,
    local varchar(10) not null,
    max_num int not null,
    title varchar(100) not null,
    content varchar(500) not null,
    status boolean default true,
    create_date datetime default now() not null,
    modify_date timestamp default now() not null
);

select * from accompany;
insert into accompany values(null, 4, '이름1', '부산', 3, '제목1', '내용1', true, now(), now());
insert into accompany(member_id, displayName, local, max_num, title, content) values(4, '이름2', '제주', 5, '제목2', '내용2');
insert into accompany(member_id, displayName, local, max_num, title, content, create_date, modify_date) values(7, '이름3', '서울', 4, '제목3', '내용3', now(), now());
