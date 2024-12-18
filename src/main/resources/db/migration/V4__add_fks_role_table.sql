ALTER TABLE JobRoles
ADD FOREIGN KEY (capabilityId) REFERENCES Capability(capabilityId),
ADD FOREIGN KEY (bandId) REFERENCES Band(bandId);

