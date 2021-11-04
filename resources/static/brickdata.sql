CREATE DATABASE brickdata;

\c brickdata;

CREATE TABLE color_entity (
  color_id INTEGER NOT NULL,
  color_name VARCHAR(32),
  color_rgb VARCHAR(32),
  is_transparent BOOLEAN,
  CONSTRAINT pk_color_entity PRIMARY KEY (color_id)
);

ALTER TABLE color_entity ADD CONSTRAINT uc_color_entity_color UNIQUE (color_id);

CREATE TABLE element (
  element_id VARCHAR(32) NOT NULL,
  part_num VARCHAR(32),
  color_id INTEGER,
  color_id_ref INTEGER,
  CONSTRAINT pk_element PRIMARY KEY (element_id)
);

ALTER TABLE element ADD CONSTRAINT uc_element_element UNIQUE (element_id);

ALTER TABLE element ADD CONSTRAINT FK_ELEMENT_ON_COLOR_ID_REF FOREIGN KEY (color_id_ref) REFERENCES color_entity (color_id);

CREATE TABLE inventory (
  inventory_id INTEGER NOT NULL,
  version_number INTEGER,
  set_number VARCHAR(32),
  parent_set_number VARCHAR(32),
  CONSTRAINT pk_inventory PRIMARY KEY (inventory_id)
);

ALTER TABLE inventory ADD CONSTRAINT uc_inventory_inventory UNIQUE (inventory_id);

ALTER TABLE inventory ADD CONSTRAINT FK_INVENTORY_ON_PARENT_SET_NUMBER FOREIGN KEY (parent_set_number) REFERENCES part_set (set_number);

CREATE TABLE inventory_minifig (
  id BIGINT NOT NULL,
  minifig_number VARCHAR(32) NOT NULL,
  set_inventory_id INTEGER,
  minifig_quantity INTEGER,
  minifig_id VARCHAR(255),
  parent_inventory_id INTEGER,
  CONSTRAINT pk_inventory_minifig PRIMARY KEY (id)
);

ALTER TABLE inventory_minifig ADD CONSTRAINT FK_INVENTORY_MINIFIG_ON_MINIFIG FOREIGN KEY (minifig_id) REFERENCES minifig (minifig_id);

ALTER TABLE inventory_minifig ADD CONSTRAINT FK_INVENTORY_MINIFIG_ON_PARENT_INVENTORY FOREIGN KEY (parent_inventory_id) REFERENCES inventory_set (inventory_id);

CREATE TABLE inventory_part (
  id BIGINT NOT NULL,
  part_number VARCHAR(32),
  inventory_id INTEGER,
  color_id INTEGER,
  quantity INTEGER,
  is_spare_part BOOLEAN,
  ref_inventory_id INTEGER,
  ref_color_id INTEGER,
  ref_part_number VARCHAR(32),
  CONSTRAINT pk_inventory_part PRIMARY KEY (id)
);

ALTER TABLE inventory_part ADD CONSTRAINT FK_INVENTORY_PART_ON_REF_COLOR FOREIGN KEY (ref_color_id) REFERENCES color_entity (color_id);

ALTER TABLE inventory_part ADD CONSTRAINT FK_INVENTORY_PART_ON_REF_INVENTORY FOREIGN KEY (ref_inventory_id) REFERENCES inventory_set (inventory_id);

ALTER TABLE inventory_part ADD CONSTRAINT FK_INVENTORY_PART_ON_REF_PART_NUMBER FOREIGN KEY (ref_part_number) REFERENCES part (part_number);

CREATE TABLE inventory_set (
  inventory_id INTEGER NOT NULL,
  set_number VARCHAR(32),
  set_quantity INTEGER,
  revision_number INTEGER,
  CONSTRAINT pk_inventory_set PRIMARY KEY (inventory_id)
);

ALTER TABLE inventory_set ADD CONSTRAINT FK_INVENTORY_SET_ON_PARENT_SET_NUMBER FOREIGN KEY (parent_set_number) REFERENCES part_set (set_number);

CREATE TABLE minifig (
  minifig_id VARCHAR(255) NOT NULL,
  minifig_name VARCHAR(255),
  part_count INTEGER,
  CONSTRAINT pk_minifig PRIMARY KEY (minifig_id)
);

ALTER TABLE minifig ADD CONSTRAINT uc_minifig_minifig UNIQUE (minifig_id);

CREATE TABLE part_category (
  category_id INTEGER NOT NULL,
  category_name VARCHAR(255),
  CONSTRAINT pk_part_category PRIMARY KEY (category_id)
);

CREATE TABLE part (
  part_number VARCHAR(32) NOT NULL,
  part_name VARCHAR(255),
  part_catalog_id INTEGER,
  part_material VARCHAR(32),
  CONSTRAINT pk_part PRIMARY KEY (part_number)
);

CREATE TABLE part_relationship (
  id BIGINT NOT NULL,
  relationship_type VARCHAR(64),
  child_part_number VARCHAR(32),
  parent_part_number VARCHAR(32),
  ref_child_part_number VARCHAR(32),
  ref_parent_part_number VARCHAR(32),
  CONSTRAINT pk_part_relationship PRIMARY KEY (id)
);

ALTER TABLE part_relationship ADD CONSTRAINT FK_PART_RELATIONSHIP_ON_REF_CHILD_PART_NUMBER FOREIGN KEY (ref_child_part_number) REFERENCES part (part_number);

ALTER TABLE part_relationship ADD CONSTRAINT FK_PART_RELATIONSHIP_ON_REF_PARENT_PART_NUMBER FOREIGN KEY (ref_parent_part_number) REFERENCES part (part_number);

CREATE TABLE part_set (
  set_number VARCHAR(32) NOT NULL,
  set_name VARCHAR(128),
  release_year INTEGER,
  set_theme_id INTEGER,
  part_count INTEGER,
  set_theme INTEGER,
  CONSTRAINT pk_part_set PRIMARY KEY (set_number)
);

ALTER TABLE part_set ADD CONSTRAINT FK_PART_SET_ON_SET_THEME FOREIGN KEY (set_theme) REFERENCES theme (theme_id);

CREATE TABLE theme (
  theme_id INTEGER NOT NULL,
  theme_name VARCHAR(128),
  parent_theme_id INTEGER,
  CONSTRAINT pk_theme PRIMARY KEY (theme_id)
);

CREATE TABLE version (
    id INTEGER NOT NULL,
    version_number INTEGER NOT NULL,
    version_date DATE
);

INSERT INTO version (
    id,
    version_number,
    version_date
    )
    VALUES(
    1,
    1,
    '2021-08-25'
    );

--Version 2
CREATE SEQUENCE part_relationship_seq
    MINVALUE 1
    START 1
    CACHE 20
    NO CYCLE
    OWNED BY part_relationship.id;

CREATE SEQUENCE inventory_minifig_seq
    MINVALUE 1
    START 1
    CACHE 20
    NO CYCLE
    OWNED BY inventory_minifig.id;

CREATE SEQUENCE inventory_part_seq
    MINVALUE 1
    START 1
    CACHE 20
    NO CYCLE
    OWNED BY inventory_part.id;

INSERT INTO version (
    id,
    version_number,
    version_date
    )
    VALUES(
    2,
    2,
    '2021-11-01'
    );

--Version 3
CREATE EXTENSION pg_trgm; --Adds support for SIMILARITY() function - Trigrams
CREATE EXTENSION fuzzystrmatch; --Adds support for SOUNDEX(), LEVENSHTEIN(), METAPHONE(), and DMETAPHONE()

INSERT INTO version (
    id,
    version_number,
    version_date
    )
    VALUES(
    3,
    3,
    '2021-11-02'
    );

