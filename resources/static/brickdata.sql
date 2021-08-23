CREATE DATABASE brickdata;

\c brickdata;

CREATE TABLE color IF NOT EXISTS(
    colorId INT PRIMARY KEY,
    colorName VARCHAR(32),
    colorRGB VARCHAR(16),
    isTrans BOOLEAN
);

CREATE TABLE element IF NOT EXISTS(
    elementId INT NOT PRIMARY KEY,
    partNum VARCHAR(32),
    colorId INT
);

CREATE TABLE inventory_part IF NOT EXISTS(
    partNumber VARCHAR(32) PRIMARY KEY,
    inventoryId INT,
    colorId INT,
    quantity INT,
    isSpare BOOLEAN
);

CREATE TABLE inventory_set IF NOT EXISTS(
    inventoryId INT PRIMARY KEY,
    setNumber VARCHAR(32),
    quantity INT,
    revision INT
);

CREATE TABLE minifig IF NOT EXISTS(
    minifigId VARCHAR(32) PRIMARY KEY,
    name VARCHAR(128),
    numParts INT
);

CREATE TABLE part IF NOT EXISTS(
    partNumber VARCHAR(32) PRIMARY KEY,
    name VARCHAR(128),
    partCatalogId INT,
    partMaterial VARCHAR(128)
);

CREATE TABLE part_category IF NOT EXISTS(
    partId INT PRIMARY KEY,
    categoryName VARCHAR(128)
);

CREATE TABLE part_relationship IF NOT EXISTS(
    id INT PRIMARY KEY,
    relationshipType VARCHAR(128),
    childPartNumber VARCHAR(32),
    parentPartNumber VARCHAR(32)
);

CREATE TABLE part_set IF NOT EXISTS(
    setNumber VARCHAR(32) PRIMARY KEY,
    name VARCHAR(128),
    setYear INT,
    themeId INT,
    numParts INT
);

CREATE TABLE theme IF NOT EXISTS(
    themeId INT NOT NULL,
    themeName TEXT,
    parentId INT,

);