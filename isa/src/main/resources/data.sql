INSERT INTO public.system_user(
	user_type, id, address, blocked, city, country, email, last_password_reset_date, name, password, phone_number, surname)
	VALUES ('Administrator', '1', 'Tolstojeva 10', false, 'Novi Sad', 'Srbija', 'admin@gmail.com', null, 'Adminka', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','069652256','mirkovic');
INSERT INTO administrator(
	id)
	VALUES ('1');