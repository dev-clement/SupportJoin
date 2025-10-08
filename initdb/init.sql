-- Use the database created by Docker
USE supportjoin;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Projects table
CREATE TABLE IF NOT EXISTS projects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255)
);

-- Many-to-many link table: users <-> projects
CREATE TABLE IF NOT EXISTS m2m_user_project (
    user_id BIGINT,
    project_id BIGINT,
    role VARCHAR(50),
    assigned_date DATE,
    PRIMARY KEY (user_id, project_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

-- Assets table
CREATE TABLE IF NOT EXISTS asset (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    link VARCHAR(512) NOT NULL,
    name VARCHAR(255)
);

-- Many-to-many link table: projects <-> assets
CREATE TABLE IF NOT EXISTS m2m_project_asset (
    project_id BIGINT,
    asset_id BIGINT,
    metadata VARCHAR(255),
    added_date DATE,
    PRIMARY KEY (project_id, asset_id),
    FOREIGN KEY (project_id) REFERENCES projects(id),
    FOREIGN KEY (asset_id) REFERENCES asset(id)
);

-- Sample data
INSERT INTO users (name, email, password) VALUES
  ('Alice', 'alice@example.com', 'alicepassword'),
  ('Bob', 'bob@example.com', 'bobpassword');

INSERT INTO projects (title, description) VALUES
  ('AI Platform', 'Machine Learning Project'),
  ('Web Dashboard', 'Frontend React Dashboard');

INSERT INTO m2m_user_project (user_id, project_id, role, assigned_date) VALUES
  (1, 1, 'Lead', CURDATE()),
  (1, 2, 'Contributor', CURDATE()),
  (2, 2, 'Lead', CURDATE());
