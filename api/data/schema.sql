-- DROP
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;


-- TABLES
CREATE TABLE users (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	nickname VARCHAR(50),
	birth_date DATE NOT NULL,
	password VARCHAR(128) NOT NULL,
	active BOOLEAN NOT NULL,
    profile_image VARCHAR(512)
);


CREATE TABLE permission (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	name VARCHAR(100) NOT NULL,
	users_id BIGINT NOT NULL
);


CREATE TABLE friendship (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    users_id BIGINT NOT NULL,
    friend_id BIGINT NOT NULL
);


CREATE TABLE friend_request (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	users_id BIGINT NOT NULL,
	sender_id BIGINT NOT NULL
);


CREATE TABLE post (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	users_id BIGINT NOT NULL,
	post_date DATE NOT NULL,
	privacy VARCHAR(10) NOT NULL,
	description VARCHAR(1024) NOT NULL,
	post_image VARCHAR(512)
);


CREATE TABLE post_likes (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	post_id BIGINT NOT NULL,
	users_id BIGINT NOT NULL
);


CREATE TABLE post_comments (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	post_id BIGINT NOT NULL,
	users_id BIGINT NOT NULL,
	user_comment VARCHAR(1024) NOT NULL
);


-- ALTER TABLES
-- PKS
ALTER TABLE users ADD CONSTRAINT pk_users PRIMARY KEY (id);
ALTER TABLE permission ADD CONSTRAINT pk_permission PRIMARY KEY (id);
ALTER TABLE friendship ADD CONSTRAINT pk_friendship PRIMARY KEY (id);
ALTER TABLE friend_request ADD CONSTRAINT pk_friend_request PRIMARY KEY (id);
ALTER TABLE post ADD CONSTRAINT pk_post PRIMARY KEY (id);
ALTER TABLE post_likes ADD CONSTRAINT pk_post_likes PRIMARY KEY (id);
ALTER TABLE post_comments ADD CONSTRAINT pk_post_comments PRIMARY KEY (id);

-- UKS
ALTER TABLE users ADD CONSTRAINT uk_users_email UNIQUE (email);
ALTER TABLE permission ADD CONSTRAINT uk_permission UNIQUE (name, users_id);
ALTER TABLE friend_request ADD CONSTRAINT uk_friend_request UNIQUE (users_id, sender_id);

-- FKS permission
ALTER TABLE permission ADD CONSTRAINT fk_permission_users FOREIGN KEY (users_id) REFERENCES users;

-- FKS friendship
ALTER TABLE friendship ADD CONSTRAINT fk_users_id FOREIGN KEY (users_id) REFERENCES users(id);
ALTER TABLE friendship ADD CONSTRAINT fk_friend_id FOREIGN KEY (friend_id) REFERENCES users(id);

-- FKS friend_request
ALTER TABLE friend_request ADD CONSTRAINT fk_users_id FOREIGN KEY (users_id) REFERENCES users(id);
ALTER TABLE friend_request ADD CONSTRAINT fk_sender_id FOREIGN KEY (sender_id) REFERENCES users(id);

-- FKS post (and CK)
ALTER TABLE post ADD CONSTRAINT fk_users_id FOREIGN KEY (users_id) REFERENCES users(id);
ALTER TABLE post ADD CONSTRAINT ck_privacy CHECK (privacy IN ('PUBLIC', 'PRIVATE'));

-- FKS post_likes
ALTER TABLE post_likes ADD CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES post(id);
ALTER TABLE post_likes ADD CONSTRAINT fk_users_id FOREIGN KEY (users_id) REFERENCES users(id);

-- FKS post_comments
ALTER TABLE post_comments ADD CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES post(id);
ALTER TABLE post_comments ADD CONSTRAINT fk_users_id FOREIGN KEY (users_id) REFERENCES users(id);
