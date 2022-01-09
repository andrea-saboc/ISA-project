INSERT INTO public.system_user(
	user_type, id, address, blocked, city, country, email, last_password_reset_date, name, password, phone_number, surname)
	VALUES ('Administrator', '1', 'Tolstojeva 10', false, 'Novi Sad', 'Srbija', 'admin@gmail.com', null, 'Adminka', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','069652256','mirkovic');
INSERT INTO administrator(
	id)
	VALUES ('1');
	INSERT INTO public.system_user(
	user_type, id, address, blocked, city, country, email, last_password_reset_date, name, password, phone_number, surname)
	VALUES ('BoatOwner', 2, 'Trg 34', false, 'Paris', 'France', 'ana@gmail.com',null, 'Ana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '78555', 'Grkovic');
INSERT INTO public.mansion_owner(
	type, advertiser_registration_approved, advertiser_reason, id)
	VALUES ('mansion', true, 'I am a mansion owner', 2);
	
	
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (5, 'Jovana Jovanovica 23a', 'Novi Sade', 'Srbija', 23.67, 45.88);
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (6, 'Marka Markovica 23a', 'London', 'Engleska', 23.67, 45.88);
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (7, 'Jovana Markovica 23a', 'Beograd', 'Srbija', 23.67, 45.88);
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (8, 'Hercega 23a', 'Herceg Novi', 'Montenegero', 23.67, 45.88);
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (9, 'Hercega 23a', 'Herceg Novi', 'Montenegero', 23.67, 45.88);	
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (10, 'Ena 23a', 'Herceg Novi', 'Negero', 23.67, 45.88);
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (11, 'Mila 23a', 'Herceg Novi', 'Montenegero', 23.67, 45.88);	
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (12, 'Kila 23a', 'Herceg Novi', 'Montenegero', 23.67, 45.88);

	
INSERT INTO public.mansion(
	id, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
	VALUES (1, 9, 'Vila Viktorija', 250, 50, 'Najbolji promo',5, 2);
INSERT INTO public.mansion(
	id, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
	VALUES (2, 9, 'Vila Sofija', 250, 50, 'Promo..',6, 2);
INSERT INTO public.mansion(
	id, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)	
	VALUES (3, 9, 'Vila Stojanka', 250, 50, 'Najbolji promo',7, 2);
INSERT INTO public.mansion(
	id, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
	VALUES (4, 9, 'Vila Viktorija', 250, 50, 'Najbolji promo',8, 2);
INSERT INTO public.mansion(
	id, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
	VALUES (6, 9, 'Nije vila', 250, 50, 'Najbolji promo',9, 2);
INSERT INTO public.mansion(
	id, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
	VALUES (5, 9, 'Bobanka', 250, 50, 'Najbolji promo',10, 2);
INSERT INTO public.mansion(
	id, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
	VALUES (7, 9, 'Nije vila', 250, 50, 'Najbolji promo',11, 2);
INSERT INTO public.mansion(
	id, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
	VALUES (8, 9, 'Bobanka', 250, 50, 'Najbolji promo',12, 2);
	

