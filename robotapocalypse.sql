-- Database: robotapocalypse

--DROP DATABASE IF EXISTS robotapocalypse; -- DO NOT DO THIS IN PRODUCTION

CREATE TABLE "flag" (
	"id"			text PRIMARY KEY,	
	"no_of_reports"	int,
	"status"		boolean 
);

CREATE TABLE "location" (
	"id"			text PRIMARY KEY,	
	"longitude"		numeric,
	"latitude"		numeric
);

CREATE TABLE "inventory" (
    "id"            text PRIMARY KEY,
    "water"         boolean DEFAULT false,
    "food"          boolean DEFAULT false,
    "medication"    boolean DEFAULT false,
    "ammunition"    boolean DEFAULT false
);


CREATE TYPE gender AS ENUM ('Male', 'Female');

CREATE TABLE "survivor" (
	"id"			text PRIMARY KEY,	
	"name"			text,
	"age"   		int,
	"gender" 		gender,
  "location_id" text REFERENCES "location"("id") ON DELETE CASCADE ON UPDATE CASCADE,
  "flag_id"     text REFERENCES "flag"("id") ON DELETE CASCADE ON UPDATE CASCADE,
  "inventory_id"		text REFERENCES "inventory"("id") ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TYPE category_option AS ENUM ('Land', 'Flying');

CREATE TABLE "robot" (
	"model"				      text PRIMARY KEY,
	"serial_number"		  text UNIQUE,
	"manufactured_date"	timestamp with time zone,
	"category_option"		category_option
);

-- Populate the "location" table
INSERT INTO "location" ("id","longitude", "latitude") 
VALUES
  ('L1', 28.0473, -26.2041), 
  ('L2', 18.4233, -33.9180), 
  ('L3', 31.0416, -29.8587), 
  ('L4', 27.9228, -26.1015), 
  ('L5', 28.9784, -26.1296), 
  ('L6', 18.5502, -33.9289);
 

-- Populate the "flag" table
INSERT INTO "flag" ("id","no_of_reports", "status")
VALUES
  ('F1', 3, true),
  ('F2', 0, false),
  ('F3', 1, false),
  ('F4', 3, true),
  ('F5', 2, false),
  ('F6', 0, false),
  ('F7', 3, true),
  ('F8', 2, false),
  ('F9', 1, false),
  ('F10', 3, true);

-- Populate the "inventory" table
INSERT INTO "inventory" ("id", "water", "food", "medication", "ammunition") 
VALUES
  ('I1', true, false, true, true),
  ('I2', false, true, false, true),
  ('I3', true, true, false, false),
  ('I4', false, false, true, true),
  ('I5', true, false, false, true),
  ('I6', false, true, true, false),
  ('I7', true, true, true, true),
  ('I8', false, false, false, true),
  ('I9', true, true, true, true),
  ('I10', false, true, false, true);

-- Populate the "survivor" table 
INSERT INTO "survivor" ("id", "name", "age", "gender", "location_id", "flag_id", "inventory_id") 
VALUES
  ('surv_1', 'Thato Mnisi', 16, 'Male', 'L2' , 'F1', 'I1'),
  ('surv_2', 'Zoe Khoza', 25, 'Female', 'L5', 'F2', 'I2'),
  ('surv_3', 'Thembi Nkambule', 35, 'Female', 'L5', 'F3', 'I3'),
  ('surv_4', 'Reitumetse Maseko', 80, 'Female', 'L1', 'F4', 'I4'),
  ('surv_5', 'Katekani Khoza', 37, 'Female', 'L6', 'F5', 'I5'),
  ('surv_6', 'Ruth Hlongwane', 31, 'Female', 'L3', 'F6', 'I6'),
  ('surv_7', 'Curthwell Mulivha', 40, 'Male', 'L2', 'F7', 'I7'),
  ('surv_8', 'Ntombi Skosana', 50, 'Female', 'L4', 'F8', 'I8'),
  ('surv_9', 'Sam Phiri', 7, 'Male', 'L1', 'F9', 'I9'),
  ('surv_10', 'Linda Jobe', 21, 'Female','L4', 'F10', 'I10');


