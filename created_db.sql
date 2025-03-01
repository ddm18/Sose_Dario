-- 1. Create the database
CREATE DATABASE "Sose_services";

-- 2. Connect to the newly created database
\c "Sose_services";

-- 3. Create the schemas
CREATE SCHEMA IF NOT EXISTS boat_service_schema;
CREATE SCHEMA IF NOT EXISTS car_service_schema;
CREATE SCHEMA IF NOT EXISTS flight_service_schema;
CREATE SCHEMA IF NOT EXISTS hotel_service_schema;
CREATE SCHEMA IF NOT EXISTS itinerary_prosumer_schema;
CREATE SCHEMA IF NOT EXISTS public;  -- normally exists by default
CREATE SCHEMA IF NOT EXISTS transportation_prosumer_schema;
