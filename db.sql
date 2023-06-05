-- Adminer 4.8.1 PostgreSQL 15.1 (Debian 15.1-1.pgdg110+1) dump

DROP TABLE IF EXISTS "tourlogs";
DROP SEQUENCE IF EXISTS tourlogs_id_seq;
CREATE SEQUENCE tourlogs_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."tourlogs" (
    "id" integer DEFAULT nextval('tourlogs_id_seq') NOT NULL,
    "tour_id" integer NOT NULL,
    "date" date NOT NULL,
    "comment" text NOT NULL,
    "difficulty" integer NOT NULL,
    "duration" time without time zone NOT NULL,
    "rating" integer NOT NULL,
    CONSTRAINT "tourlogs_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "tours";
DROP SEQUENCE IF EXISTS tours_id_seq;
CREATE SEQUENCE tours_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."tours" (
    "id" integer DEFAULT nextval('tours_id_seq') NOT NULL,
    "name" character(64) NOT NULL,
    "transportation_type" integer NOT NULL,
    "distance" integer NOT NULL,
    "estimated_time" time without time zone NOT NULL,
    "path_to_map" character(256) NOT NULL,
    "user" character(64) NOT NULL,
    "from" character(256) NOT NULL,
    "to" character(256) NOT NULL,
    "description" text NOT NULL,
    CONSTRAINT "tours_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


-- 2023-06-05 16:53:28.812522+00
