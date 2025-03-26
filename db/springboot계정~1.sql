create sequence seq_board nocache;

create table boardtest (
    boardno number primary key,
    title varchar2(100),
    writer varchar2(20),
    content varchar2(500)
);

insert into boardtest values(seq_board.nextval,'제목1','더조은','내용입니다1');
insert into boardtest values(seq_board.nextval,'제목2','김길동','내용입니다2');

commit;