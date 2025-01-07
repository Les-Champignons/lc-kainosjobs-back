CREATE TABLE `User` (
    `email` VARCHAR(64) NOT NULL,
    `password` VARCHAR(64) NOT NULL,
    `roleId` TINYINT NOT NULL DEFAULT '0',
    PRIMARY KEY (`email`),
    FOREIGN KEY (`roleId`) REFERENCES `Role`(`roleId`)
);