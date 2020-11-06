--1. Создать таблицы и заполнить их начальными данными
CREATE TABLE departments (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(255)
);

CREATE TABLE employees (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255),
                           department_id INT REFERENCES departments(id)
);

insert into departments(name) values ('department1');
insert into departments(name) values ('department2');
insert into departments(name) values ('department3');

insert into employees(name, department_id) values ('User1', 1);
insert into employees(name, department_id) values ('User2', 1);
insert into employees(name, department_id) values ('User3', 1);

insert into employees(name, department_id) values ('User4', 2);
insert into employees(name, department_id) values ('User5', 2);

insert into employees(name, department_id) values ('User6', 3);

insert into employees(name, department_id) values ('User7', null);
insert into employees(name, department_id) values ('User8', null);
insert into employees(name, department_id) values ('User9', null);


--2. Выполнить запросы с left, rigth, full, cross соединениями
select *
from employees e
         left join  departments d on d.id = e.department_id;

select *
from departments d
         right join employees e on d.id = e.department_id;

select *
from departments d
         full join employees e on d.id = e.department_id;

select *
from departments d
         cross join employees e;

--3. Используя left join найти работников, которые не относятся ни к одну из департаментов
select *
from employees e
         left join  departments d on d.id = e.department_id
where d.id isnull ;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select d.name as department, e.name as employee
from employees e
         left join  departments d on d.id = e.department_id;

select d.name as department, e.name as employee
from departments d
         right join employees e on d.id = e.department_id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens(
                      id serial primary key ,
                      name varchar(255),
                      gender char(1)
);

insert into teens(name, gender) values ('teen1', 'm');
insert into teens(name, gender) values ('teen2', 'm');
insert into teens(name, gender) values ('teen3', 'm');
insert into teens(name, gender) values ('teen4', 'w');
insert into teens(name, gender) values ('teen5', 'w');

select *
from teens t1
         cross join teens t
where t1.gender != t.gender;
