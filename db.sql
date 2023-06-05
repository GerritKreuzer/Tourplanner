-- Adminer 4.8.1 PostgreSQL 15.1 (Debian 15.1-1.pgdg110+1) dump

DROP TABLE IF EXISTS "tourlogs";
DROP SEQUENCE IF EXISTS tourlogs_id_seq;
CREATE SEQUENCE tourlogs_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."tourlogs" (
    "id" integer DEFAULT nextval('tourlogs_id_seq') NOT NULL,
    "tourId" integer NOT NULL,
    "date" date NOT NULL,
    "comment" text NOT NULL,
    "difficulty" integer NOT NULL,
    "totalTime" time without time zone NOT NULL,
    "rating" integer NOT NULL,
    CONSTRAINT "tourlogs_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "tours";
DROP SEQUENCE IF EXISTS tours_id_seq;
CREATE SEQUENCE tours_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."tours" (
    "id" integer DEFAULT nextval('tours_id_seq') NOT NULL,
    "name" character(64) NOT NULL,
    "transportationType" integer NOT NULL,
    "tourDistance" integer NOT NULL,
    "estimatedTime" time without time zone NOT NULL,
    "pathToMap" character(256) NOT NULL,
    "user" character(64) NOT NULL,
    "from" character(256) NOT NULL,
    "to" character(256) NOT NULL,
    CONSTRAINT "tours_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


-- 2023-06-05 07:16:00.686335+00