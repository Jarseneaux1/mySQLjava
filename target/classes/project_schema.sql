DROP TABLE IF EXISTS PROJECT;
DROP TABLE IF EXISTS MATERIAL;
DROP TABLE IF EXISTS STEP;
DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS PROJECT_CATEGORY;
CREATE TABLE PROJECT(
project_id INT AUTO_INCREMENT NOT NULL,
project_name VARCHAR(128) NOT NULL,
estimated_hours DECIMAL(7, 2),
actual_hours DECIMAL(7, 2),
difficulty INT,
notes TEXT,
PRIMARY KEY project_id
);
CREATE TABLE MATERIAL(
material_id INT AUTO_INCREMENT NOT NULL,
project_id INT NOT NULL,
material_name VARCHAR(128) NOT NULL,
num_required INT,
cost DECIMAL(7, 2),
PRIMARY KEY material_id
);
CREATE TABLE STEP(
step_id INT AUTO_INCREMENT NOT NULL,
project_id INT NOT NULL,
step_text TEXT NOT NULL,
step_order INT NOT NULL,
PRIMARY KEY step_id
);
CREATE TABLE CATEGORY(
category_id INT AUTO_INCREMENT NOT NULL,
category_name VARCHAR(128) NOT NULL
);
CREATE TABLE PROJECT_CATEGORY(
project_id INT NOT NULL FOREIGN KEY REFERENCES category_id,
category_id INT NOT NULL FOREIGN KEY REFERENCES project_id
);