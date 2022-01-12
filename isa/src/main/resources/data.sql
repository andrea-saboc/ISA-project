	INSERT INTO public.system_user(
	user_type, id, address, blocked, city, country, email,  name, password, phone_number, surname)
	VALUES ('BoatOwner', 2, 'Trg 34', false, 'Paris', 'France', 'ana@gmail.com', 'Ana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '78555', 'Grkovic');
INSERT INTO public.mansion_owner(
	type, advertiser_registration_approved, advertiser_reason, id)
	VALUES ('mansion', true, 'I am a mansion owner', 2);
	
	
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (3, 'Jovana Jovanovica 23a', 'Novi Sade', 'Srbija', 23.67, 45.88);
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (4, 'Marka Markovica 23a', 'London', 'Engleska', 23.67, 45.88);
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (5, 'Jovana Markovica 23a', 'Beograd', 'Srbija', 23.67, 45.88);
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (6, 'Hercega 23a', 'Herceg Novi', 'Montenegero', 23.67, 45.88);
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (7, 'Hercega 23a', 'Herceg Novi', 'Montenegero', 23.67, 45.88);	
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (8, 'Ena 23a', 'Herceg Novi', 'Negero', 23.67, 45.88);
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (9, 'Mila 23a', 'Herceg Novi', 'Montenegero', 23.67, 45.88);	
INSERT INTO public.address(
	id, address, city, country, latitude, longitude)
	VALUES (10, 'Kila 23a', 'Herceg Novi', 'Montenegero', 23.67, 45.88);

	
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
INSERT INTO public.boat(
    id, gps, vhfradio, avg_grade, cancellation_policy, capacity, engine_power, fishfinder, length, max_speed, name, number_of_engines, price_for_seven_days, price_per_day, price_per_hour, promo_description, radar, type, address_id, owner_id)
    VALUES (5, true, true, 10.0, 'You will recieve 70% of total price!', 10, 200, true, 34, 220, 'Catamaran', 6, 500, 100, 30, 'This is the best boat',false, 'Yacht', 10, 100);
    INSERT INTO public.boat(
id, gps, vhfradio, avg_grade, cancellation_policy, capacity, engine_power, fishfinder, length, max_speed, name, number_of_engines, price_for_seven_days, price_per_day, price_per_hour, promo_description, radar, type, address_id, owner_id)
    VALUES (6, false, true, 10.0, 'You will recieve 85% of total price!', 15, 200, true, 34, 220, 'BENETEAU', 6, 700, 250, 70, 'This is the best boat',true, 'Sailboat', 9, 100);