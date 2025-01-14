CREATE TABLE Applications (
	email varchar(64),
    jobRoleName varchar(55),
    etag varchar(255),
    status varchar(12),
    PRIMARY KEY (email, jobRoleName)
);