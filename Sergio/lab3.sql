CREATE DATABASE lab3 CHARACTER SET utf8 COLLATE utf8_general_ci;
USE lab3;


CREATE TABLE `people` (
  `idpeople` int NOT NULL AUTO_INCREMENT,
  `names` varchar(45) NOT NULL,
  `lastname` varchar(80) NOT NULL,
  `country` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `delegation` varchar(45) NOT NULL,
  `zipcode` varchar(5) NOT NULL,
  `outdoorNum` varchar(5) NOT NULL,
  `unitNum` varchar(3) DEFAULT NULL,
  `RFC` varchar(15) DEFAULT NULL,
  `myuser` varchar(100) NOT NULL DEFAULT 'USER()',
  PRIMARY KEY (`idpeople`),
  UNIQUE KEY `RFC_UNIQUE` (`RFC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `employees_type` (
  `id_employeesType` tinyint(1) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id_employeesType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `payment_method` (
  `idpayment_method` tinyint(1) NOT NULL AUTO_INCREMENT,
  `method` varchar(25) NOT NULL,
  PRIMARY KEY (`idpayment_method`),
  UNIQUE KEY `method_UNIQUE` (`method`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `nosocomio_type` (
  `idNosocomio_type` tinyint(1) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idNosocomio_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `area` (
  `idarea` tinyint(1) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(45) NOT NULL,
  PRIMARY KEY (`idarea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `nosocomio` (
  `idNosocomio` INT NOT NULL AUTO_INCREMENT,
  `names` VARCHAR(100) NOT NULL,
  `nosocomio_type` tinyint(1) NOT NULL,
  `partner` ENUM('SI', 'NO'),
  PRIMARY KEY (`idNosocomio`),
  KEY `fk_nosocomio_type_idx` (`nosocomio_type`),
  CONSTRAINT `fk_nosocomio_type` FOREIGN KEY (`nosocomio_type`) REFERENCES `nosocomio_type` (`idNosocomio_type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `docs` (
  `idDoc` INT NOT NULL AUTO_INCREMENT,
  `names` VARCHAR(100) NOT NULL,
  `lastnames` VARCHAR(100) NOT NULL,
  `speciality` VARCHAR(70) NOT NULL,
  `area` tinyint(1) NOT NULL, -- Use the same data type as the referenced column
  `Nosocomio` int NOT NULL, -- Use the same data type as the referenced column
  PRIMARY KEY (`idDoc`),
  KEY `fk_doc_area_idx` (`area`),
  CONSTRAINT `fk_doc_area` FOREIGN KEY (`area`) REFERENCES `area` (`idarea`) ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `fk_doc_nosocomio_idx` (`Nosocomio`),
  CONSTRAINT `fk_doc_nosocomio` FOREIGN KEY (`Nosocomio`) REFERENCES `nosocomio` (`idNosocomio`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `patients` (
  `idpatient` int NOT NULL AUTO_INCREMENT,
  `people_id` int NOT NULL,
  `birthday_date` date NOT NULL,
  `gender` varchar(12) NOT NULL,
  PRIMARY KEY (`idpatient`),
  KEY `fk_people_patients_idx` (`people_id`),
  CONSTRAINT `fk_people_patients` FOREIGN KEY (`people_id`) REFERENCES `people` (`idpeople`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `doc_patient_relation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `doc_id` INT NOT NULL,
  `patient_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_doc_patient_relation_doc_idx` (`doc_id`),
  KEY `fk_doc_patient_relation_patient_idx` (`patient_id`),
  CONSTRAINT `fk_doc_patient_relation_doc` FOREIGN KEY (`doc_id`) REFERENCES `docs` (`idDoc`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_doc_patient_relation_patient` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`idpatient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `email` (
  `idmail` int NOT NULL AUTO_INCREMENT,
  `id_people` int NOT NULL,
  `mail_type` enum('Personal','Work','Other') NOT NULL,
  `mail` varchar(100) NOT NULL,
  PRIMARY KEY (`idmail`),
  UNIQUE KEY `mail_UNIQUE` (`mail`),
  KEY `fk_mail_people_idx` (`id_people`),
  CONSTRAINT `fk_mail_people` FOREIGN KEY (`id_people`) REFERENCES `people` (`idpeople`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `employees` (
  `idemployees` int NOT NULL AUTO_INCREMENT,
  `birthday_date` date NOT NULL,
  `date_admission` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `people_id` int NOT NULL,
  `type_employees` tinyint(1) NOT NULL,
  PRIMARY KEY (`idemployees`),
  UNIQUE KEY `people_id_UNIQUE` (`people_id`),
  KEY `fk_employees_type_idx` (`type_employees`),
  CONSTRAINT `fk_employees_people` FOREIGN KEY (`people_id`) REFERENCES `people` (`idpeople`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_employees_type` FOREIGN KEY (`type_employees`) REFERENCES `employees_type` (`id_employeesType`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `logs` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `evento` text NOT NULL,
  `user` varchar(100) NOT NULL,
  `useradd` int DEFAULT '0',
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `salesAdd` int DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `phones` (
  `idphones` int NOT NULL AUTO_INCREMENT,
  `countrycode` varchar(4) NOT NULL,
  `phonenumber` varchar(12) NOT NULL,
  `phonetype` enum('Mobile','Work','Home') NOT NULL,
  PRIMARY KEY (`idphones`),
  UNIQUE KEY `phonenumber_UNIQUE` (`phonenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `phones_has_people` (
  `phones_idphones` int NOT NULL,
  `people_idpeople` int NOT NULL,
  PRIMARY KEY (`phones_idphones`,`people_idpeople`),
  KEY `fk_phones_has_people_people1_idx` (`people_idpeople`),
  CONSTRAINT `fk_phones_has_people_people1` FOREIGN KEY (`people_idpeople`) REFERENCES `people` (`idpeople`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_phones_has_people_phones1` FOREIGN KEY (`phones_idphones`) REFERENCES `phones` (`idphones`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `category_test` (
  `idcat_test` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`idcat_test`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `suppliers` (
  `idsuppliers` int NOT NULL AUTO_INCREMENT,
  `id_people` int NOT NULL,
  `companyname` varchar(45) NOT NULL,
  PRIMARY KEY (`idsuppliers`),
  KEY `pk_sup_people` (`id_people`),
  CONSTRAINT `fk_sup_people` FOREIGN KEY (`id_people`) REFERENCES `people` (`idpeople`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `suppliers_test` (
  `suppliers_idsuppliers` int NOT NULL,
  `category_test_idCategory_test` int NOT NULL,
  KEY `fk_suppliers_has_Category_test_Category_test1_idx` (`category_test_idCategory_test`),
  KEY `fk_suppliers_has_Category_test_suppliers1_idx` (`suppliers_idsuppliers`),
  CONSTRAINT `fk_sup_cat_test` FOREIGN KEY (`category_test_idCategory_test`) REFERENCES `category_test` (`idcat_test`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sup_cat_test_sup` FOREIGN KEY (`suppliers_idsuppliers`) REFERENCES `suppliers` (`idsuppliers`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `tests` (
  `idtest` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(80) DEFAULT NULL,
  `buyprice` decimal(8,2) NOT NULL,
  `saleprice` decimal(8,2) NOT NULL,
  `category` int NOT NULL,
  `supplier` int NOT NULL,
  PRIMARY KEY (`idtest`),
  KEY `fk_test_category_idx` (`category`),
  KEY `fk_test_supplier_idx` (`supplier`),
  CONSTRAINT `fk_test_category` FOREIGN KEY (`category`) REFERENCES `category_test` (`idcat_test`),
  CONSTRAINT `fk_test_supplier` FOREIGN KEY (`supplier`) REFERENCES `suppliers` (`idsuppliers`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `users` (
  `idusers` int NOT NULL AUTO_INCREMENT,
  `user` varchar(15) NOT NULL,
  `password` varchar(150) NOT NULL,
  `employee_id` int NOT NULL,
  `type_employee` tinyint(1) NOT NULL,
  PRIMARY KEY (`idusers`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  UNIQUE KEY `employee_id_UNIQUE` (`employee_id`),
  KEY `fk_users_employee_idx` (`employee_id`),
  KEY `fk_users_typeEmployee_idx` (`type_employee`),
  CONSTRAINT `fk_users_employee` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`idemployees`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_typeEmployee` FOREIGN KEY (`type_employee`) REFERENCES `employees_type` (`id_employeesType`),
  CONSTRAINT `users_chk_1` CHECK ((`type_employee` <> 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sales` (
  `id_sale` int NOT NULL AUTO_INCREMENT,
  `delivery_day` date NOT NULL,
  `date_purchase` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `patient` int NOT NULL,
  `atm` int NOT NULL,
  `payment_method` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_sale`),
  KEY `fk_Sales_patien_idx` (`patient`),
  KEY `fk_Sales_payment_method1_idx` (`payment_method`),
  KEY `fk_Sales_atm_idx` (`atm`),
  CONSTRAINT `fk_Sales_atm` FOREIGN KEY (`atm`) REFERENCES `users` (`idusers`),
  CONSTRAINT `fk_Sales_patien` FOREIGN KEY (`patient`) REFERENCES `patients` (`idpatient`),
  CONSTRAINT `fk_Sales_payment_method1` FOREIGN KEY (`payment_method`) REFERENCES `payment_method` (`idpayment_method`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `sales_detail` (
  `id_detail` int NOT NULL AUTO_INCREMENT,
  `no_sale` int NOT NULL,
  `priceSale` decimal(9,2) NOT NULL,
  `quantity` int NOT NULL,
  `test` int NOT NULL,
  PRIMARY KEY (`id_detail`),
  KEY `fk_details_test_idx` (`test`),
  KEY `fk_details_sale_idx` (`no_sale`),
  CONSTRAINT `fk_details_test` FOREIGN KEY (`test`) REFERENCES `tests` (`idtest`),
  CONSTRAINT `fk_details_sale` FOREIGN KEY (`no_sale`) REFERENCES `sales` (`id_sale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `warehouse` (
  `idwarehouse` int NOT NULL AUTO_INCREMENT,
  `test` int NOT NULL,
  `quantity` int NOT NULL,
  `date_in` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expiration` date NOT NULL,
  PRIMARY KEY (`idwarehouse`),
  KEY `fk_warehouse_test_idx` (`test`),
  CONSTRAINT `fk_warehouse_test` FOREIGN KEY (`test`) REFERENCES `tests` (`idtest`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `buys` (
  `idBuys` int NOT NULL AUTO_INCREMENT,
  `suppliers` int NOT NULL,
  `quantity` int NOT NULL,
  `payment_method` tinyint(1) NOT NULL,
  `buydate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `totalprice` decimal(8,2) NOT NULL,
  PRIMARY KEY (`idBuys`),
  KEY `Fecha_UNIQUE` (`buydate`),
  KEY `fk_Buys_supplier_idx` (`suppliers`),
  KEY `fk_Buys_paym_idx` (`payment_method`),
  CONSTRAINT `fk_Buys_paym` FOREIGN KEY (`payment_method`) REFERENCES `payment_method` (`idpayment_method`),
  CONSTRAINT `fk_Buys_supplier` FOREIGN KEY (`suppliers`) REFERENCES `suppliers` (`idsuppliers`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `buys_detail` (
  `idBuys_details` int NOT NULL AUTO_INCREMENT,
  `buynum` int NOT NULL,
  `test` int NOT NULL,
  `buyprice` decimal(8,2) NOT NULL,
  PRIMARY KEY (`idBuys_details`),
  KEY `fk_details_Buys_idx` (`buynum`),
  KEY `fk_details_testBuys_idx` (`test`),
  CONSTRAINT `fk_details_Buys` FOREIGN KEY (`buynum`) REFERENCES `buys` (`idBuys`),
  CONSTRAINT `fk_details_testBuys` FOREIGN KEY (`test`) REFERENCES `tests` (`idtest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orderTest` (
  `idorder` INT NOT NULL AUTO_INCREMENT,
  `issue_date` DATETIME,
  `test_id` INT NOT NULL, 
  `patient_id` INT NOT NULL,
  PRIMARY KEY (`idorder`),
  KEY `fk_Order_test_idx` (`test_id`),
  CONSTRAINT `fk_Order_test` FOREIGN KEY (`test_id`) REFERENCES `tests` (`idtest`) ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `fk_Order_patient_idx` (`patient_id`),
  CONSTRAINT `fk_Order_patient` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`idpatient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE externalOrderTest (
  `idExOrder` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`idExOrder`),
  KEY `fk_externalOrder_order_idx` (`order_id`),
  CONSTRAINT `fk_externalOrder_order` FOREIGN KEY (`order_id`) REFERENCES `orderTest` (`idorder`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE internalOrderTest (
  `idInOrder` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `area` TINYINT NOT NULL,
  `espacioOcupado` INT,
  PRIMARY KEY (`idInOrder`),
  KEY `fk_internalOrder_order_idx` (`order_id`),
  CONSTRAINT `fk_internalOrder_order` FOREIGN KEY (`order_id`) REFERENCES `orderTest` (`idorder`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE testResult (
  `idresult` INT NOT NULL auto_increment,
  `OrderID` INT NOT NULL,
  `result_date` DATE,
  `Results` TEXT NOT NULL,
  `observations` TEXT NOT NULL,
  PRIMARY KEY (`idresult`),
  KEY `fk_testResult_OrderID_idx` (`OrderID`),
  CONSTRAINT `fk_testResult_OrderID` FOREIGN KEY (`OrderID`) REFERENCES `orderTest` (`idorder`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DELIMITER //

CREATE TRIGGER check_internalOrderSocio
BEFORE INSERT ON `internalOrderTest`
FOR EACH ROW
BEGIN
  DECLARE nosocomio_partner ENUM('SI', 'NO');

  SELECT `partner` INTO nosocomio_partner
  FROM `nosocomio`
  WHERE `idNosocomio` = (SELECT `Nosocomio` FROM `docs` WHERE `idDoc` = NEW.`order_id`);

  IF nosocomio_partner != 'SI' THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'No se pueden crear órdenes internas si el nosocomio no es socio.';
  END IF;
END;
//

DELIMITER ;


DELIMITER //

CREATE TRIGGER check_externalOrderTest
BEFORE INSERT ON externalOrderTest
FOR EACH ROW
BEGIN
  DECLARE nosocomio_partner VARCHAR(2);

  SELECT `partner` INTO nosocomio_partner
  FROM `nosocomio`
  WHERE `idNosocomio` = (SELECT `Nosocomio` FROM `docs` WHERE `idDoc` = NEW.`order_id`);

  IF nosocomio_partner != 'NO' THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'El nosocomio es socio, por lo que la orden debe ser registrada como interna.';
  END IF;
END;
//

DELIMITER ;


