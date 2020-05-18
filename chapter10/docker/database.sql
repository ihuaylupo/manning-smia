CREATE DATABASE ostock_dev
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


-- Table: public.organizations

-- DROP TABLE public.organizations;

CREATE TABLE public.organizations
(
    organization_id text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default",
    contact_name text COLLATE pg_catalog."default",
    contact_email text COLLATE pg_catalog."default",
    contact_phone text COLLATE pg_catalog."default",
    CONSTRAINT organizations_pkey PRIMARY KEY (organization_id)
)

TABLESPACE pg_default;

ALTER TABLE public.organizations
    OWNER to postgres;

-- Table: public.licenses

-- DROP TABLE public.licenses;

CREATE TABLE public.licenses
(
    license_id text COLLATE pg_catalog."default" NOT NULL,
    organization_id text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    product_name text COLLATE pg_catalog."default" NOT NULL,
    license_type text COLLATE pg_catalog."default" NOT NULL,
    comment text COLLATE pg_catalog."default",
    CONSTRAINT licenses_pkey PRIMARY KEY (license_id),
    CONSTRAINT licenses_organization_id_fkey FOREIGN KEY (organization_id)
        REFERENCES public.organizations (organization_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.licenses
    OWNER to postgres;


