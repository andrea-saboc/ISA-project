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
	VALUES (5, 'Marka Markovica 23a', 'London', 'Engleska', 23.67, 45.88);
INSERT INTO public.mansion(
	id, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
	VALUES (1, 9, 'Vila Viktorija', 250, 50, 'Najbolji promo',5, 2);
	INSERT INTO public.additional_service(
	available_period_id, name, price_per_day, price_per_hour, boat_id, mansion_id)
	VALUES (123, 'Internet', 23, 0, null, 1);
	INSERT INTO public.additional_service(
	available_period_id, name, price_per_day, price_per_hour, boat_id, mansion_id)
	VALUES (123, 'TV', 45, 0, null, 2);
	INSERT INTO public.additional_service(
	id, name, price_per_day, price_per_hour, boat_id, mansion_id)
	VALUES (7, 'housekeeper', 12, 30, null, 1);
