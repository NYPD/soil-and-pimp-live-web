ALTER TABLE `soil_and_pimp_live`.`email_subscriptions` 
 ADD COLUMN `verification_token` VARCHAR(45) NULL AFTER `email_address`,
 ADD COLUMN `verified` TINYINT(1) NOT NULL AFTER `verification_token`,
 ADD COLUMN `created_date` DATE NULL AFTER `verified`;

UPDATE `soil_and_pimp_live`.`email_subscriptions`
   SET `verified` = 1;

UPDATE `soil_and_pimp_live`.`email_subscriptions` 
    SET `created_date` = CURTIME();

ALTER TABLE `soil_and_pimp_live`.`email_subscriptions` 
     MODIFY `created_date` DATE NOT NULL AFTER `verified`;