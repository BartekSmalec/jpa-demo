insert into course(id, name, last_updated_date, created_date, is_deleted)
values (10001, 'JPA in 50 Steps', current_timestamp, current_timestamp, false);
insert into course(id, name, last_updated_date, created_date, is_deleted)
values (10002, 'Spring in 50 Steps', current_timestamp, current_timestamp, false);
insert into course(id, name, last_updated_date, created_date, is_deleted)
values (10003, 'Spring Boot in 50 Steps', current_timestamp, current_timestamp, false);
insert into course(id, name, last_updated_date, created_date, is_deleted)
values (10004, 'Dummy', current_timestamp, current_timestamp, false);
insert into course(id, name, last_updated_date, created_date, is_deleted)
values (10005, 'Dummy', current_timestamp, current_timestamp, false);
insert into course(id, name, last_updated_date, created_date, is_deleted)
values (10007, 'Dummy', current_timestamp, current_timestamp, false);
insert into course(id, name, last_updated_date, created_date, is_deleted)
values (10009, 'Dummy', current_timestamp, current_timestamp, false);

insert into passport(id, number)
values (40001, 'E123456');
insert into passport(id, number)
values (40002, 'N123456');
insert into passport(id, number)
values (40003, 'L123560');

insert into student(id, name, passport_id)
values (20001, 'Ranga', 40001);
insert into student(id, name, passport_id)
values (20002, 'Adam', 40002);
insert into student(id, name, passport_id)
values (20003, 'Jane', 40003);

insert into review(id, rating, desc, course_id)
values (50001, '5', 'Great course', 10001);
insert into review(id, rating, desc, course_id)
values (50002, '4', 'Wonderful Course', 10001);
insert into review(id, rating, desc, course_id)
values (50003, '5', 'Awesome Course', 10003);

insert into student_course(student_id, course_id)
values (20001, 10001);
insert into student_course(student_id, course_id)
values (20001, 10001);
values (20002, 10001);
insert into student_course(student_id, course_id)
values (20003, 10003);
