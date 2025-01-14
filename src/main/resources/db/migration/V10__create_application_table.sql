CREATE TABLE Applicants (
    applicantId INT AUTO_INCREMENT PRIMARY KEY,
	email varchar(64),
    jobRoleName varchar(55),
    etag varchar(255),
    status varchar(12) DEFAULT "In Progress"
);