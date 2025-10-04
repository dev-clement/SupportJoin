DROP DATABASE IF EXISTS supportjoin;
CREATE OR REPLACE DATABASE supportjoin;
USE supportjoin;

CREATE TABLE user (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	fk_user_project INT
) ENGINE=InnoDB;

CREATE TABLE project (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(300),
	fk_project_user INT,
	fk_asset_project INT
) ENGINE=InnoDB;

CREATE TABLE m2m_user_project (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	fk_user_id INT NOT NULL,
	fk_project_id INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE m2m_assets_projects (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	fk_project_id INT NOT NULL,
	fk_link_id INT NOT NULL
) ENGINE=INNODB;

CREATE TABLE link (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	link VARCHAR(250)
);

ALTER TABLE m2m_user_project
	ADD FOREIGN KEY (fk_user_id) REFERENCES user(id);

ALTER TABLE m2m_user_project
	ADD FOREIGN KEY (fk_project_id) REFERENCES project(id);

ALTER TABLE user
	ADD FOREIGN KEY (fk_user_project) REFERENCES project(id);
	
ALTER TABLE m2m_assets_projects
	ADD FOREIGN KEY (fk_project_id) REFERENCES project(id);

ALTER TABLE m2m_assets_projects
	ADD FOREIGN KEY (fk_link_id) REFERENCES link(id)