insert into personal_info (id,name,surname,username, personal_number, email, gender, authority, enabled)
values (0,'Ema','Mrkvičková','ema.mrkvickova', '56478', 'ema.mrkvičkova@fri.uniza.sk','FEMALE','TEACHER', true);

insert into personal_info (id,name,surname,username, personal_number, email,  gender, authority, enabled)
values (5,'Zuzana','Malá','zuzana.mala', '12345', 'zuzana.mala@fri.uniza.sk', 'FEMALE','EMPLOYEE', true);

insert into employee (id, person_id) values (225, 5);
insert into employee (id, person_id) values (222, 0);

insert into system_settings(id,allowed_absents, employee_id, class_date, number_of_days, uploaded_file)
values (0,2,222,'28.12.2024 14:15',0,false);