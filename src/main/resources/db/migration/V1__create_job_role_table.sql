CREATE TABLE JobRoles (
    jobRoleId INT AUTO_INCREMENT PRIMARY KEY,
    roleName VARCHAR(55),
    location VARCHAR(55),
    capabilityId INT,
    bandId INT,
    closingDate DATE
    FOREIGN KEY (capabilityId) REFERENCES Capability(capabilityId),
    FOREIGN KEY (bandId) REFERENCES Band(bandId),
 )
