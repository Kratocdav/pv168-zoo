/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  David
 * Created: 22.3.2016
 */

CREATE TABLE "CAPTIVITY" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "PAVILION" VARCHAR(255),
    "CAPACITY" INTEGER,
    "INFO" VARCHAR(255)
);