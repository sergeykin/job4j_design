create database userRoles;

create table role(
    RoleID serial primary key ,
    Name varchar(255)
);

create table "user"(
                      UserID serial primary key ,
                      FIO    varchar(255),
                      BirthDay date,
                      Heigh double precision,
                      RoleID int references role(RoleID)
);

create table rules(
    RulesID serial primary key ,
    Name  varchar(255)
);

create table role_rules(
    Role_RulesID serial primary key ,
    RoleID int references role(RoleID),
    RulesID int references rules(RulesID)
);

create table state(
    StateID serial primary key ,
    Name varchar(255)
);

create table category(
  CategoryID serial primary key ,
  Name varchar(255)
);

create table item(
    ItemID serial primary key ,
    UserID int references "user"(UserID),
    CategoryID int references category(CategoryID),
    StateID int references state(StateID),
    Name varchar(255)
);

create table "comments"(
    CommentID serial primary key ,
    ItemID int references item(ItemID),
    Name varchar(255)
);

create table attachs(
    AttachID serial primary key ,
    ItemID int references item(ItemID),
    Name varchar(255)
);

insert into role(Name) values('role1');
insert into state(Name) values('state1');
insert into rules(Name) values('rules1');
insert into role_rules(RoleID, RulesID) values(1, 1);
insert into "user"(FIO, BirthDay, Heigh, RoleID)values('userFIO', '20000101',134.12, 1);
insert into category(Name) values('category1');
insert into item(UserID, CategoryID, StateID, Name) values(1, 1, 1, 'itemName1');
insert into "comments"(ItemID, Name) values(1, 'commentsName1');
insert into attachs(ItemID, Name) values(1, 'attachsName1');
