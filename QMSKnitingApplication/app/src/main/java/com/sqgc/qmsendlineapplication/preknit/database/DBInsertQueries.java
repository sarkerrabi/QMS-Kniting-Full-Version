/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 7/20/20 5:47 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/20/20 5:45 PM
 *
 */

package com.sqgc.qmsendlineapplication.preknit.database;

public class DBInsertQueries {
    /*BUYERS*/
    public static String insertBuyers = "INSERT INTO \"BUYER\" (\"ID\",\"Name\") VALUES (1,'H&M'),\n" +
            " (2,'SAINSBURYS'),\n" +
            " (3,'HEMA');";

    /*STYLES*/
    public static String insertStyleSubCategory = "INSERT INTO \"STYLESUBCATEGORY\" (\"ID\",\"NAME\",\"STYLECATEGORY\") VALUES (1,'Calista Cardigan',1),\n" +
            " (2,'Open',2),\n" +
            " (3,'Full Zipper',2),\n" +
            " (4,'Vest',2),\n" +
            " (5,'Hald Sleeve',2),\n" +
            " (7,'Round Neck',2),\n" +
            " (8,'V-Neck',2),\n" +
            " (9,'Half Sleeve',2),\n" +
            " (10,'Half Zipper',2),\n" +
            " (11,'Sleeveless',2),\n" +
            " (12,'Vest',2),\n" +
            " (13,'Collar',3),\n" +
            " (14,'Round Neck',3),\n" +
            " (15,'Button',3),\n" +
            " (16,'Open',3),\n" +
            " (17,'Full zipper',3),\n" +
            " (18,'Button',4),\n" +
            " (19,'Open',4),\n" +
            " (20,'Zipper',4),\n" +
            " (21,'Vest',4),\n" +
            " (22,'Half Sleeve',4),\n" +
            " (23,'Half',5),\n" +
            " (24,'Full',5),\n" +
            " (25,'Half',6),\n" +
            " (26,'Full',6),\n" +
            " (27,'KC.HM-MAR CLOVER BIKINI-OL',7),\n" +
            " (28,'KC.HM-GRETA MYNTA THONG-S2 B65+10-100+BC01',7),\n" +
            " (29,'LIN-0046 SS20 JULY FB SS MULTIPACK 16-3921 TCX',7),\n" +
            " (30,'LIN-0046 SS20 JULY FB SS MULTIPACK 18-3927 TCX',7),\n" +
            " (31,'LIN-0046 SS20 JULY FB SS MULTIPACK 18-4718 TCS',7),\n" +
            " (32,'LIN-0046 SS20 JULY FB SS MULTIPACK 19-4020 TCX',7),\n" +
            " (33,'LIN-0046 SS20 JULY FB SS MULTIPACK TU-00028',7),\n" +
            " (34,'HM-HILLY BRIEF-S2',7),\n" +
            " (35,'HM-HILLY CHEEKY BRIEF-S2-SOLID',7),\n" +
            " (36,'HEMA-19660155-GOOD MP3 BRIEF-OBSIDIAN BLACK-2020-1',7);";


    /*POs*/
    public static String insertPOs = "INSERT INTO \"PO\" (\"ID\",\"PoNo\",\"StyleSubCatID\",\"ColorID\") VALUES (1,'186510-1338',0,0),\n" +
            " (2,'218387-1334-SPEED ORDER',0,0),\n" +
            " (3,'2317162/TBA 1`',0,0),\n" +
            " (4,'SAMPLE PO',0,0),\n" +
            " (5,'2322957/TBA 2',0,0),\n" +
            " (6,'185984-OL',0,0),\n" +
            " (7,'214808-3710',0,0),\n" +
            " (8,'214865-3710',0,0),\n" +
            " (9,'4501123554 TBA-5',0,0),\n" +
            " (10,'TBA-6',0,0);";

    /*COLORSs*/
    public static String insertColor = "INSERT INTO \"COLOR\" (\"id\",\"ColorName\") VALUES (1,'06-204'),\n" +
            " (2,'07-196'),\n" +
            " (3,'B65 .'),\n" +
            " (4,'10-100'),\n" +
            " (5,'16-3921 Tcx'),\n" +
            " (6,'18-3927 TCX'),\n" +
            " (7,'18-4718 TCX'),\n" +
            " (8,'19-4020 TCX'),\n" +
            " (9,'TU-00028'),\n" +
            " (10,'09-090'),\n" +
            " (11,'13-308'),\n" +
            " (12,'OBSIDIAN BLACK'),\n" +
            " (13,'Zircon White');";

    //style in buyers and buyer in Lines
    public static String insertStyleInBuyers = "INSERT INTO \"StyleInBuyers\" (\"ID\",\"StyleSubCatID\",\"BuyerID\",\"LineID\",\"StyleCateID\") VALUES (1,27,1,1,7),\n" +
            " (2,28,1,1,7),\n" +
            " (3,29,2,1,7),\n" +
            " (4,30,2,1,7),\n" +
            " (5,31,2,1,7),\n" +
            " (6,32,2,1,7),\n" +
            " (7,33,2,1,7),\n" +
            " (8,34,1,2,7),\n" +
            " (9,35,1,2,7),\n" +
            " (10,36,3,2,7);";


    // POs In Styles
    public static String insertPOsInStyle = "INSERT INTO \"POsInStyle\" (\"ID\",\"StyleSubCatID\",\"PO_ID\",\"Line_ID\") VALUES (1,27,1,1),\n" +
            " (2,28,2,1),\n" +
            " (3,29,3,1),\n" +
            " (4,29,4,1),\n" +
            " (5,29,5,1),\n" +
            " (6,30,3,1),\n" +
            " (7,30,4,1),\n" +
            " (8,30,5,1),\n" +
            " (9,31,3,1),\n" +
            " (10,31,4,1),\n" +
            " (11,31,5,1),\n" +
            " (12,32,3,1),\n" +
            " (13,32,4,1),\n" +
            " (14,33,3,1),\n" +
            " (15,33,4,1),\n" +
            " (16,34,6,2),\n" +
            " (17,34,7,2),\n" +
            " (18,35,8,2),\n" +
            " (19,36,9,2),\n" +
            " (20,36,10,2);";


    // Colors In POs
    public static String insertColorsInPO = "INSERT INTO \"ColorsInPO\" (\"ID\",\"PO_ID\",\"Color_ID\",\"Line_ID\") VALUES (1,1,1,1),\n" +
            " (2,2,2,1),\n" +
            " (3,2,3,1),\n" +
            " (4,2,4,1),\n" +
            " (5,3,5,1),\n" +
            " (6,3,6,1),\n" +
            " (7,3,7,1),\n" +
            " (8,3,8,1),\n" +
            " (9,3,9,1),\n" +
            " (10,4,5,1),\n" +
            " (11,4,6,1),\n" +
            " (12,4,7,1),\n" +
            " (13,4,8,1),\n" +
            " (14,4,9,1),\n" +
            " (15,5,5,1),\n" +
            " (16,5,6,1),\n" +
            " (17,5,7,1),\n" +
            " (18,6,10,2),\n" +
            " (19,7,11,2),\n" +
            " (20,8,10,2),\n" +
            " (21,9,12,2),\n" +
            " (22,9,13,2),\n" +
            " (23,10,12,2);";


}
