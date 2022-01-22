INSERT INTO public.system_user(
    user_type, id, address, blocked, city, country, email, name, password, phone_number, surname)
VALUES ('MansionOwner', 5, 'Trg 34', false, 'Paris', 'France', 'ana@gmail.com','Ana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '78555', 'Grkovic');
INSERT INTO public.mansion_owner(
    type, advertiser_registration_approved, advertiser_reason, id)
VALUES ('mansion', true, 'I am a mansion owner', 5);

INSERT INTO public.system_user(
    user_type, id, address, blocked, city, country, email, name, password, phone_number, surname)
VALUES ('Administrator', '6', 'Tolstojeva 10', false, 'Novi Sad', 'Srbija', 'admin@gmail.com','Adminka', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','069652256','mirkovic');
INSERT INTO administrator(
    id)
VALUES ('6');

INSERT INTO public.system_user(
    user_type, id, address, blocked, city, country, email, name, password, phone_number, surname)
VALUES ('BoatOwner', 100, 'St Marc 45', false, 'Genova', 'Italy', 'markijan@gmail.com', 'Mark', '$2a$10$KBYOmrL4g3ce5.wfagQnveII0Ubylu0s/1pP/zl2pagffN7uDfAKS', '09787', 'Markijani');
INSERT INTO public.boat_owner(
    type, advertiser_registration_approved, advertiser_reason, id)
VALUES ('boat', true, 'I want to advertise', 100);

INSERT INTO public.system_user(
    user_type, id, address, blocked, city, country, email, name, password, phone_number, surname)
VALUES ('CLIENT', 400, 'Trg 34', false, 'Trebinje', 'Bosnia and Herzegovina', 'littlevamp999@gmail.com','Anja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '78555', 'Laketa');
INSERT INTO public.client(
    activation_code, client_loyalty_points,client_penalty_points, id)
VALUES ('code', 0, 0, 400);

INSERT INTO public.system_user(
    user_type, id, address, blocked, city, country, email, name, password, phone_number, surname)
VALUES ('CLIENT', 401, 'Trg 34', false, 'Gacko', 'Bosnia and Herzegovina', 'mikymilane@gmail.com','Milan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '78555', 'Milovic');
INSERT INTO public.client(
    activation_code, client_loyalty_points,client_penalty_points, id)
VALUES ('code', 0, 0, 401);

INSERT INTO public.system_user(
    user_type, id, address, blocked, city, country, email, name, password, phone_number, surname)
VALUES ('CLIENT', 402, 'Trg 34', false, 'Belgrade', 'Serbia', 'anya@gmail.com','Anica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '78555', 'Obrenovic');
INSERT INTO public.client(
    activation_code, client_loyalty_points,client_penalty_points, id)
VALUES ('code', 0, 0, 402);


INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (3, 'New York Street 23a', 'Budapest', 'Hungary', 23.67, 45.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (4, '4440 Allegan Rd', 'London', 'England', 23.67, 45.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (5, '14 Loon Lake Ter Vermontville', 'New York', 'NY', 23.67, 45.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (6, '11275 Kelly Hwy', 'Vermontville', ' Michigan', 23.67, 45.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (7, '1889 Bonn Blvd', ' Bismarck', 'North Dakota', 23.67, 45.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (8, '1026 Sea Breeze', 'Portland', ' Texas', 23.67, 45.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (9, '328 W Abe ', 'St Plano', 'Illinois', 23.67, 45.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (10, 'Kila 23a', 'Herceg Novi', 'Montenegero', 23.67, 45.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (11, '1176 N Ventura Ave', 'Oak View', ' California', 44.67, 45.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (12, '601 Hawaii St', 'El Segundo, ', 'California', 44.67, 35.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (13, '230 Gum St', 'Philadelphia', 'Mississippi', 45.25, 19.85);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (14, 'St Marcus Aurelius 23/788', 'Honolulu', 'Hawaii', 21.31, -157.9);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (15, '32nd Ave SW', 'Seattle ', 'Washington', 44.67, 35.88);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (16, '6305 Balboa Ln', 'Apollo Beach ', 'Florida', 45.25, 19.85);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (17, '361 Hiawatha Way', 'Melbourne Beach', 'Florida', 21.31, -157.9);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (18, '6305 Balboa Ln', 'Apollo Beach ', 'Florida', 45.25, 19.85);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (19, '361 Hiawatha Way', 'Melbourne Beach', 'Florida', 21.31, -157.9);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (20, '6305 Balboa Ln', 'Apollo Beach ', 'Florida', 45.25, 19.85);
INSERT INTO public.address(
    id, address, city, country, latitude, longitude)
VALUES (21, '361 Hiawatha Way', 'Melbourne Beach', 'Florida', 21.31, -157.9);

INSERT INTO public.mansion(
    id, deleted,avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
VALUES (1, false, 4, 'Clermont', 250, 50, 'These two words speak volumes. A big and welcoming backyard can be the main selling feature for some visitors, especially if they have children or pets, or those who are loving warmer climates.',5, 5);
INSERT INTO public.mansion(
    id,deleted, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
VALUES (2,false, 10, 'Mils mansion', 250, 50, 'A great floor plan can overcome many other objections, including the overall size of the house. Good flow can maximize space. Many visitors want an open floor plan.',6, 5);
INSERT INTO public.mansion(
    id,deleted, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
VALUES (3,false, 5, 'Hilda', 250, 50, ' One of the greatest fears is having to pour money into a house after closing. Homes that are well maintained require less initial upkeep, so you might want to mention that yours has been "lovingly maintained."',7, 5);
INSERT INTO public.mansion(
    id, deleted,avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
VALUES (4,false, 9, 'Vile mansion', 250, 50, 'Committed To Getting The Colorful places to live and play.',8, 5);
INSERT INTO public.mansion(
    id,deleted, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
VALUES (5,false, 8, 'Sores', 250, 50, 'Happiness in every nook and corner. Rent this mansion for price on discount, now!',9, 5);
INSERT INTO public.mansion(
    id,deleted, avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
VALUES (6,false, 9, 'Hourus mansion', 250, 50, 'Happiness in every nook and corner. Rent this mansion for price on discount, now!',10, 5);
INSERT INTO public.mansion(
    id, deleted,avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
VALUES (7,false, 9, 'Katrina', 250, 50, 'Happiness in every nook and corner. Rent this mansion for price on discount, now!',11, 5);
INSERT INTO public.mansion(
    id, deleted,avg_grade, name, price_for_seven_days, price_per_day, promo_description, address_id, owner_id)
VALUES (8,false, 10, 'Cassity', 250, 50, 'The mansion was all concrete and tall glass windows that gave a view of the mountains, a chance to relax and take in the changing of the seasons from the comfort of an easy chair.',12, 5);

INSERT INTO public.boat(
    id,deleted, gps, vhfradio, avg_grade, cancellation_policy, capacity, engine_power, fishfinder, length, max_speed, name, number_of_engines, price_for_seven_days, price_per_day, price_per_hour, promo_description, radar, type, address_id, owner_id)
VALUES (3,false, true, true, 0.0, 'You will recieve 100% of total price!', 10, 200, true, 10, 120, 'Milizia', 6, 600, 80, 20, 'Promo description of malicija',true, 'Sail boat', 13, 100);
INSERT INTO public.boat(
    id,deleted, gps, vhfradio, avg_grade, cancellation_policy, capacity, engine_power, fishfinder, length, max_speed, name, number_of_engines, price_for_seven_days, price_per_day, price_per_hour, promo_description, radar, type, address_id, owner_id)
VALUES (4,false, true, true, 0.0, 'You will recieve 70% of total price!', 10, 200, true, 34, 220, 'Malizia', 6, 660, 90, 30, 'Promo description of malicija',true, 'Sail boat', 14, 100);
INSERT INTO public.boat(
    id,deleted, gps, vhfradio, avg_grade, cancellation_policy, capacity, engine_power, fishfinder, length, max_speed, name, number_of_engines, price_for_seven_days, price_per_day, price_per_hour, promo_description, radar, type, address_id, owner_id)
VALUES (5, false,true, true, 0.0, 'You will recieve 70% of total price!', 10, 200, true, 34, 220, 'Catamaran', 6, 500, 100, 15, 'This is the best boat',false, 'Yacht', 10, 100);
INSERT INTO public.boat(
    id,deleted, gps, vhfradio, avg_grade, cancellation_policy, capacity, engine_power, fishfinder, length, max_speed, name, number_of_engines, price_for_seven_days, price_per_day, price_per_hour, promo_description, radar, type, address_id, owner_id)
VALUES (6, false,false, true, 0.0, 'You will recieve 85% of total price!', 15, 200, true, 34, 220, 'BENETEAU', 6, 700, 250, 70, 'This is the best boat',true, 'Sailboat', 9, 100);
INSERT INTO public.boat(
    id,deleted, gps, vhfradio, avg_grade, cancellation_policy, capacity, engine_power, fishfinder, length, max_speed, name, number_of_engines, price_for_seven_days, price_per_day, price_per_hour, promo_description, radar, type, address_id, owner_id)
VALUES (7, false,true, true, 0.0, 'You will recieve 70% of total price!', 10, 200, true, 34, 220, 'Osmana', 6, 500, 100, 30, 'This is the best boat',false, 'Yacht', 20, 100);
INSERT INTO public.boat(
    id,deleted, gps, vhfradio, avg_grade, cancellation_policy, capacity, engine_power, fishfinder, length, max_speed, name, number_of_engines, price_for_seven_days, price_per_day, price_per_hour, promo_description, radar, type, address_id, owner_id)
VALUES (8, false,false, true, 0.0, 'You will recieve 85% of total price!', 15, 200, true, 34, 220, 'Lady', 6, 700, 250, 70, 'This is the best boat',true, 'Sailboat', 21, 100);
INSERT INTO public.boat(
    id,deleted, gps, vhfradio, avg_grade, cancellation_policy, capacity, engine_power, fishfinder, length, max_speed, name, number_of_engines, price_for_seven_days, price_per_day, price_per_hour, promo_description, radar, type, address_id, owner_id)
VALUES (1, false,true, true, 0.0, 'You will recieve 70% of total price!', 10, 200, true, 34, 220, 'Yacht Numb', 6, 500, 100, 30, 'This is the best boat',false, 'Yacht', 18, 100);
INSERT INTO public.boat(
    id,deleted, gps, vhfradio, avg_grade, cancellation_policy, capacity, engine_power, fishfinder, length, max_speed, name, number_of_engines, price_for_seven_days, price_per_day, price_per_hour, promo_description, radar, type, address_id, owner_id)
VALUES (2, false,false, true, 0.0, 'You will recieve 85% of total price!', 15, 200, true, 34, 220, 'Peace', 6, 700, 250, 70, 'This is the best boat',true, 'Sailboat', 17, 100);


INSERT INTO public.additional_service(
    id, name, price_per_day, price_per_hour, boat_id, mansion_id)
VALUES (1, 'wifi', 30, 12, 3, null );
INSERT INTO public.additional_service(
    id, name, price_per_day, price_per_hour, boat_id, mansion_id)
VALUES (2, 'captain', 50, 100, 3, null );
INSERT INTO public.additional_service(
    id, name, price_per_day, price_per_hour, boat_id, mansion_id)
VALUES (3, 'champagne', 12, 36, 4, null );
INSERT INTO public.additional_service(
    id, name, price_per_day, price_per_hour, boat_id, mansion_id)
VALUES (4, 'bathroom', 12, 36, 4, null );

INSERT INTO public.boat_available_period(
    available_period_id, end_date, start_date, boat_id)
VALUES (1, '2022-03-10 00:00:00', '2022-01-01 00:00:00', 3);
INSERT INTO public.boat_available_period(
    available_period_id, end_date, start_date, boat_id)
VALUES (2, '2022-03-28 00:00:00', '2022-03-20 00:00:00', 3);
INSERT INTO public.boat_available_period(
    available_period_id, end_date, start_date, boat_id)
VALUES (3, '2022-02-20 00:00:00', '2022-01-20 00:00:00', 4);
INSERT INTO public.boat_available_period(
    available_period_id, end_date, start_date, boat_id)
VALUES (4,'2022-01-28 00:00:00', '2022-01-20 00:00:00', 5);

INSERT INTO public.boat_available_period(
    available_period_id, end_date, start_date, boat_id)
VALUES (5, '2022-03-10 00:00:00', '2022-02-02 00:00:00', 3);
INSERT INTO public.boat_available_period(
    available_period_id, end_date, start_date, boat_id)
VALUES (6, '2022-05-28 00:00:00', '2022-05-20 00:00:00', 3);
INSERT INTO public.boat_available_period(
    available_period_id, end_date, start_date, boat_id)
VALUES (7, '2022-02-20 00:00:00', '2022-01-20 00:00:00', 1);
INSERT INTO public.boat_available_period(
    available_period_id, end_date, start_date, boat_id)
VALUES (8,'2022-01-28 00:00:00', '2022-01-20 00:00:00', 2);

INSERT INTO public.reservation(
    reservation_type, id, end_date, number_of_guests, start_date, total_price, type, system_user)
VALUES ('BOAT', 1, '2022-02-07 00:00:00', 4, '2022-01-28 00:08:00', 230, 'BOAT', 400);
INSERT INTO public.boat_reservation(
    id, boat_id, feedback_id, owner_feedback_id)
VALUES (1, 3, null, null);

INSERT INTO public.reservation(
    reservation_type, id, end_date, number_of_guests, start_date, total_price, type, system_user)
VALUES ('BOAT', 2, '2022-02-09 00:00:00', 4, '2022-02-07 00:00:00', 230, 'BOAT', 400);
INSERT INTO public.boat_reservation(
    id, boat_id, feedback_id, owner_feedback_id)
VALUES (2, 3, null, null);

INSERT INTO public.reservation(
    reservation_type, id, end_date, number_of_guests, start_date, total_price, type, system_user)
VALUES ('BOAT', 3, '2022-02-09 00:00:00', 4, '2022-02-07 00:00:00', 230, 'BOAT', 400);
INSERT INTO public.boat_reservation(
    id, boat_id, feedback_id, owner_feedback_id)
VALUES (3, 4, null, null);



INSERT INTO public.reservation(
    reservation_type, id, end_date, number_of_guests, start_date, total_price, type, system_user)
VALUES ('MANSION', 4, '2021-01-05 00:00:00', 4, '2021-01-03 00:08:00', 89, 'MANSION', 400);
INSERT INTO public.mansion_reservation(
    id, mansion_id, feedback_id, owner_feedback_id)
VALUES (4, 3, null, null);

INSERT INTO public.reservation(
    reservation_type, id, end_date, number_of_guests, start_date, total_price, type, system_user)
VALUES ('MANSION', 5,  '2021-01-11 00:00:00', 4, '2021-01-10 00:08:00', 200, 'MANSION', 400);
INSERT INTO public.mansion_reservation(
    id, mansion_id, feedback_id, owner_feedback_id)
VALUES (5, 1, null, null);

INSERT INTO public.reservation(
    reservation_type, id, end_date, number_of_guests, start_date, total_price, type, system_user)
VALUES ('MANSION', 6, '2021-04-13 00:00:00', 4, '2021-04-10 00:08:00', 22, 'MANSION', 400);
INSERT INTO public.mansion_reservation(
    id, mansion_id, feedback_id, owner_feedback_id)
VALUES (6, 2, null, null);


INSERT INTO ROLE (id ,name) VALUES (1, 'ROLE_CLIENT');
INSERT INTO ROLE (id ,name) VALUES (2, 'ROLE_BOAT_OWNER');
INSERT INTO ROLE (id ,name) VALUES (3, 'ROLE_MANSION_OWNER');
INSERT INTO ROLE (id ,name) VALUES (4, 'ROLE_UNAUTHORISED');
INSERT INTO ROLE (id ,name) VALUES (5, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (400, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (401, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (402, 1);

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (100, 2);


INSERT INTO public.subscription(
    subscription_type, id, subscriber_id)
VALUES ('BOAT_SUBSCRIPTION', 11, 400);
INSERT INTO public.boat_subscription(
    id, boat_id)
VALUES (11, 3);

INSERT INTO public.subscription(
    subscription_type, id, subscriber_id)
VALUES ('BOAT_SUBSCRIPTION', 12, 400);
INSERT INTO public.boat_subscription(
    id, boat_id)
VALUES (12, 4);

INSERT INTO public.subscription(
    subscription_type, id, subscriber_id)
VALUES ('BOAT_SUBSCRIPTION', 13, 400);
INSERT INTO public.boat_subscription(
    id, boat_id)
VALUES (13, 5);

INSERT INTO public.subscription(
    subscription_type, id, subscriber_id)
VALUES ('BOAT_SUBSCRIPTION', 14, 401);
INSERT INTO public.boat_subscription(
    id, boat_id)
VALUES (14, 3);

INSERT INTO public.subscription(
    subscription_type, id, subscriber_id)
VALUES ('BOAT_SUBSCRIPTION', 15, 401);
INSERT INTO public.boat_subscription(
    id, boat_id)
VALUES (15, 6);

INSERT INTO public.subscription(
    subscription_type, id, subscriber_id)
VALUES ('BOAT_SUBSCRIPTION', 16, 401);
INSERT INTO public.boat_subscription(
    id, boat_id)
VALUES (16, 5);

INSERT INTO public.subscription(
    subscription_type, id, subscriber_id)
VALUES ('BOAT_SUBSCRIPTION', 17, 402);
INSERT INTO public.boat_subscription(
    id, boat_id)
VALUES (17, 4);

INSERT INTO public.subscription(
    subscription_type, id, subscriber_id)
VALUES ('BOAT_SUBSCRIPTION', 18, 402);
INSERT INTO public.boat_subscription(
    id, boat_id)
VALUES (18, 6);

INSERT INTO public.discount_reservation(
	id, end_date, number_of_guests, percentage_of_discount, price_with_discount, price_without_discount, start_date, type, valid_until, optlock, system_user, status)
	VALUES (1, '2022-03-11 00:00:00', 5, 25, 300, 375, '2022-03-08 00:00:00', 'BOAT DISCOUNT', '2022-02-28 00:00:00', 0, 402, 'RESERVED');
INSERT INTO public.boat_discount_reservation(
	id, boat_id, feedback_id, owner_feedback_id)
	VALUES (1, 6, null, null);

INSERT INTO public.discount_reservation(
	id, end_date, number_of_guests, percentage_of_discount, price_with_discount, price_without_discount, start_date, type, valid_until, optlock, system_user, status)
	VALUES (2, '2022-03-18 00:00:00', 5, 25, 300, 375, '2022-03-15 00:00:00','BOAT DISCOUNT', '2022-03-11 00:00:00', 0, null, 'ACTIVE');
INSERT INTO public.boat_discount_reservation(
	id, boat_id, feedback_id, owner_feedback_id)
	VALUES (2, 5, null, null);

INSERT INTO public.discount_reservation(
	id, end_date, number_of_guests, percentage_of_discount, price_with_discount, price_without_discount, start_date, type, valid_until, optlock, system_user, status)
	VALUES (3, '2022-03-21 00:00:00', 6, 25, 300, 375, '2022-03-19 00:00:00', 'BOAT DISCOUNT', '2022-02-28 00:00:00', 0, null, 'ACTIVE');
INSERT INTO public.boat_discount_reservation(
	id, boat_id, feedback_id, owner_feedback_id)
	VALUES (3, 5, null, null);

INSERT INTO public.discount_reservation(
	id, end_date, number_of_guests, percentage_of_discount, price_with_discount, price_without_discount, start_date, type, valid_until, optlock, system_user, status)
	VALUES (4, '2022-04-05 00:00:00', 6, 25, 300, 375, '2022-04-01 00:00:00', 'BOAT DISCOUNT', '2022-02-28 00:00:00', 0, null, 'ACTIVE');
INSERT INTO public.boat_discount_reservation(
	id, boat_id, feedback_id, owner_feedback_id)
	VALUES (4, 5, null, null);

INSERT INTO public.discount_reservation(
	id, end_date, number_of_guests, percentage_of_discount, price_with_discount, price_without_discount, start_date, type, valid_until, optlock, system_user, status)
	VALUES (5, '2022-04-11 00:00:00', 6, 25, 300, 375, '2022-04-07 00:00:00', 'BOAT DISCOUNT', '2022-02-28 00:00:00', 0, 402, 'RESERVED');
INSERT INTO public.boat_discount_reservation(
	id, boat_id, feedback_id, owner_feedback_id)
	VALUES (5, 5, null, null);
	

INSERT INTO public.discount_reservation(
	id, end_date, number_of_guests, percentage_of_discount, price_with_discount, price_without_discount, start_date, type, valid_until, optlock, system_user, status)
	VALUES (6, '2022-03-21 00:00:00', 6, 25, 300, 375, '2022-03-10 00:00:00', 'MANSION DISCOUNT', '2022-02-28 00:00:00', 0, null, 'ACTIVE');
INSERT INTO public.mansion_discount_reservation(
	id, mansion_id, feedback_id, owner_feedback_id)
	VALUES (6, 5, null, null);

INSERT INTO public.discount_reservation(
	id, end_date, number_of_guests, percentage_of_discount, price_with_discount, price_without_discount, start_date, type, valid_until, optlock, system_user, status)
	VALUES (7, '2022-04-05 00:00:00', 6, 25, 300, 375, '2022-04-01 00:00:00', 'MANSION DISCOUNT', '2022-02-28 00:00:00', 0, null, 'ACTIVE');
INSERT INTO public.mansion_discount_reservation(
	id, mansion_id, feedback_id, owner_feedback_id)
	VALUES (7, 5, null, null);

INSERT INTO public.discount_reservation(
	id, end_date, number_of_guests, percentage_of_discount, price_with_discount, price_without_discount, start_date, type, valid_until, optlock, system_user, status)
	VALUES (8, '2021-04-11 00:00:00', 6, 25, 300, 375, '2021-04-07 00:00:00', 'MANSION DISCOUNT', '2021-02-28 00:00:00', 0, 400, 'RESERVED');
INSERT INTO public.mansion_discount_reservation(
	id, mansion_id, feedback_id, owner_feedback_id)
	VALUES (8, 5, null, null);
INSERT INTO public.discount_reservation(
	id, end_date, number_of_guests, percentage_of_discount, price_with_discount, price_without_discount, start_date, type, valid_until, optlock, system_user, status)
	VALUES (9, '2021-04-11 00:00:00', 6, 25, 300, 375, '2021-04-07 00:00:00', 'MANSION DISCOUNT', '2021-02-28 00:00:00', 0, 400, 'CLOSED');
INSERT INTO public.mansion_discount_reservation(
	id, mansion_id, feedback_id, owner_feedback_id)
	VALUES (9, 1, null, null);
	

UPDATE reservation SET status = 'CLOSED' WHERE end_date <= NOW();
UPDATE discount_reservation SET status = 'CLOSED' WHERE end_date <= NOW();

UPDATE client
SET client_penalty_points = 0, penalty_points_reset_date = NOW()
WHERE (SELECT EXTRACT(YEAR FROM penalty_points_reset_date)) < (SELECT EXTRACT(YEAR FROM current_date)) OR 
(SELECT EXTRACT(MONTH FROM penalty_points_reset_date)) < (SELECT EXTRACT(MONTH FROM current_date))


	

