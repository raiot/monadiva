--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.0
-- Dumped by pg_dump version 9.6.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: monadiva; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE monadiva WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';


connect monadiva

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: language_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE language_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: language; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE language (
    id bigint DEFAULT nextval('language_seq'::regclass) NOT NULL,
    value text NOT NULL
);


--
-- Name: translation_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE translation_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: translations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE translations (
    id bigint DEFAULT nextval('translation_seq'::regclass) NOT NULL,
    langid bigint NOT NULL,
    name text,
    value text,
    type text
);


--
-- Name: user_privilege_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_privilege_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_privileges; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_privileges (
    id bigint DEFAULT nextval('user_privilege_seq'::regclass) NOT NULL,
    name text NOT NULL
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE users (
    id bigint NOT NULL,
    privilege_id bigint NOT NULL,
    name text NOT NULL,
    "lastName" text NOT NULL,
    "hashedPassword" bytea NOT NULL,
    salt bytea NOT NULL,
    address text NOT NULL,
    phone text NOT NULL
);


--
-- Name: language language_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY language
    ADD CONSTRAINT language_pkey PRIMARY KEY (id);


--
-- Name: translations translations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translations
    ADD CONSTRAINT translations_pkey PRIMARY KEY (id);


--
-- Name: user_privileges user_privileges_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_privileges
    ADD CONSTRAINT user_privileges_pkey PRIMARY KEY (id);


--
-- Name: fki_translation_language_constraint; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_translation_language_constraint ON translations USING btree (langid);


--
-- Name: fki_user_privilege_privilege_fk; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_user_privilege_privilege_fk ON users USING btree (privilege_id);


--
-- Name: translations translation_language_constraint; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY translations
    ADD CONSTRAINT translation_language_constraint FOREIGN KEY (langid) REFERENCES language(id);


--
-- Name: users user_privilege_privilege_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_privilege_privilege_fk FOREIGN KEY (privilege_id) REFERENCES user_privileges(id);


--
-- PostgreSQL database dump complete
--

