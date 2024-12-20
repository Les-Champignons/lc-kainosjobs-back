ALTER TABLE JobRoles
ADD COLUMN description VARCHAR(255),
ADD COLUMN responsibilities VARCHAR(255),
ADD COLUMN sharepointUrl VARCHAR(255),
ADD COLUMN statusId INT,
ADD COLUMN numberOfOpenPositions INT;

ALTER TABLE JobRoles
ADD FOREIGN KEY (statusId) REFERENCES Status(statusId);
