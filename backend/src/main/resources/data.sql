INSERT INTO personal_info (id, name, surname, username,  email, gender, authority, enabled)
SELECT 0, 'Lýdia', 'Gábrišová', 'lydia.gabrisova', 'lydia.gabrisova@fri.uniza.sk', 'FEMALE', 'TEACHER', true
    WHERE NOT EXISTS (
    SELECT 1 FROM personal_info WHERE username = 'lydia.gabrisova'
);

insert into personal_info (id,name,surname,username,  email,  gender, authority, enabled)
values (3,'Miloš','Lukáčik','milos.lukacik', 'lukacik.milos@fri.uniza.sk', 'MALE','EMPLOYEE', true);

insert into personal_info (id,name,surname,username, email, gender, authority, enabled)
values (1,'Anna','Kamenčáková','kamencakova1', 'kamencakova1@stud.uniza.sk','FEMALE','STUDENT', true);

insert into personal_info (id,name,surname,username,  email,  gender, authority, enabled)
values (2,'Zbyňo','Kamenčák','kamencak2', 'kamencak2@stud.uniza.sk', 'MALE','STUDENT', true);

insert into student (id, points, absents, person_id) values (0, 0,0,1);
insert into student (id, points, absents, person_id) values (1, 0,0,2);

insert into employee (id, person_id) values (0, 0);
insert into employee (id, person_id) values (1, 3);

insert into system_settings(id,allowed_absents, employee_id, uploaded_file)
values (0,2,0,false);



