--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-02-15 23:42:12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 172 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1945 (class 0 OID 0)
-- Dependencies: 172
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 861174)
-- Name: names; Type: TABLE; Schema: public; Owner: names; Tablespace: 
--

CREATE TABLE names (
    name_id integer NOT NULL,
    name text
);


ALTER TABLE public.names OWNER TO names;

--
-- TOC entry 170 (class 1259 OID 861172)
-- Name: names_name_id_seq; Type: SEQUENCE; Schema: public; Owner: names
--

CREATE SEQUENCE names_name_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.names_name_id_seq OWNER TO names;

--
-- TOC entry 1946 (class 0 OID 0)
-- Dependencies: 170
-- Name: names_name_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: names
--

ALTER SEQUENCE names_name_id_seq OWNED BY names.name_id;


--
-- TOC entry 1824 (class 2604 OID 861177)
-- Name: name_id; Type: DEFAULT; Schema: public; Owner: names
--

ALTER TABLE ONLY names ALTER COLUMN name_id SET DEFAULT nextval('names_name_id_seq'::regclass);


--
-- TOC entry 1937 (class 0 OID 861174)
-- Dependencies: 171
-- Data for Name: names; Type: TABLE DATA; Schema: public; Owner: names
--

COPY names (name_id, name) FROM stdin;
\.


--
-- TOC entry 1947 (class 0 OID 0)
-- Dependencies: 170
-- Name: names_name_id_seq; Type: SEQUENCE SET; Schema: public; Owner: names
--

SELECT pg_catalog.setval('names_name_id_seq', 8, true);


--
-- TOC entry 1826 (class 2606 OID 861184)
-- Name: names_name_key; Type: CONSTRAINT; Schema: public; Owner: names; Tablespace: 
--

ALTER TABLE ONLY names
    ADD CONSTRAINT names_name_key UNIQUE (name);


--
-- TOC entry 1828 (class 2606 OID 861182)
-- Name: names_pkey; Type: CONSTRAINT; Schema: public; Owner: names; Tablespace: 
--

ALTER TABLE ONLY names
    ADD CONSTRAINT names_pkey PRIMARY KEY (name_id);


--
-- TOC entry 1944 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: names
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM names;
GRANT ALL ON SCHEMA public TO names;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-02-15 23:42:13

--
-- PostgreSQL database dump complete
--

