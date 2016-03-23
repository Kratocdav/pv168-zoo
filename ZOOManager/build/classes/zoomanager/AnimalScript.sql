/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  David
 * Created: 22.3.2016
 */

CREATE TABLE "ANIMAL" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "ANIMALNAME" VARCHAR(255),
    "ANIMALCLASS" VARCHAR(10),
    "AGE" INTEGER,
    "GENDER" VARCHAR(10)
);