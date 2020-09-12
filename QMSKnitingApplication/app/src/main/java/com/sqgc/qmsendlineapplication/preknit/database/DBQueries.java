/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 7/23/20 11:34 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/23/20 8:36 AM
 *
 */

package com.sqgc.qmsendlineapplication.preknit.database;

public class DBQueries {

    public static String allStyleCategory = "SELECT * FROM STYLECATEGORY;";

    public static String insertStyleCategory = "INSERT INTO \"STYLECATEGORY\" (\"ID\",\"NAME\") VALUES (1,'Cardigan'),\n" +
            " (2,'Pullover'),\n" +
            " (3,'Jacket'),\n" +
            " (4,'Dress'),\n" +
            " (5,'Skirt'),\n" +
            " (6,'Bottoms'),\n" +
            " (7,'Brief'),\n" +
            " (8,'Bra');";

    /*Style Sub Categories*/
    public static String allStyleSubCategory = "SELECT * FROM STYLESUBCATEGORY;";
    public static String allStyleSubCategoryWhereCat = "SELECT STYLESUBCATEGORY.ID, STYLESUBCATEGORY.NAME FROM STYLECATEGORY JOIN STYLESUBCATEGORY ON STYLECATEGORY.ID = STYLESUBCATEGORY.STYLECATEGORY\n" +
            "WHERE STYLECATEGORY.NAME = '";
    public static String allStyleSubCategoryWhereLineAndCatID = "SELECT DISTINCT StyleSubCategory.ID, StyleSubCategory.Name FROM StyleInBuyers \n" +
            "\t\t\tJOIN BUYER on StyleInBuyers.BuyerID = BUYER.ID\n" +
            "\t\t\tJOIN StyleSubCategory on StyleInBuyers.StyleSubCatID = StyleSubCategory.ID\n" +
            "\t\t\tJOIN Line on StyleInBuyers.LineID = Line.ID \n" +
            "\t\t\tJOIN StyleCategory on StyleInBuyers.StyleCateID = StyleCategory.ID WHERE ";


    public static String insertDefect = "INSERT INTO \"DEFECT\" (\"ID\",\"NAME\") VALUES (1,'Un even knitting tension'),\n" +
            " (2,'Thin & thick yarn'),\n" +
            " (3,'Un even dyeing'),\n" +
            " (4,'High Frequency knot'),\n" +
            " (5,'Dirty & oil mark'),\n" +
            " (6,'Wrong ply'),\n" +
            " (7,'Single ply'),\n" +
            " (8,'Drop Stitch'),\n" +
            " (9,'Broken Yarn'),\n" +
            " (10,'Nylon plating'),\n" +
            " (11,'Foreign fiber'),\n" +
            " (12,'Shading between panel to panel'),\n" +
            " (13,'Broken Needle'),\n" +
            " (14,'Needle line'),\n" +
            " (15,'Pulling yarn'),\n" +
            " (16,'Edge broken'),\n" +
            " (17,'Side up down'),\n" +
            " (18,'Cut hole'),\n" +
            " (19,'Rib up down'),\n" +
            " (20,'Open Seam'),\n" +
            " (21,'Uneven linking'),\n" +
            " (22,'Poor triming'),\n" +
            " (23,'Unhidding'),\n" +
            " (24,'Broken Stitch'),\n" +
            " (25,'Skipped/split stitich'),\n" +
            " (26,'Cut damage'),\n" +
            " (27,'Uneven yarn twist'),\n" +
            " (28,'Needle mark'),\n" +
            " (29,'Barring line'),\n" +
            " (30,'Sleeve up down'),\n" +
            " (31,'Puckered/wavy seam'),\n" +
            " (32,'Poor neck shape'),\n" +
            " (33,'Placket up down'),\n" +
            " (34,'Un even stripe match'),\n" +
            " (35,'Shiny marks'),\n" +
            " (36,'Bottom up down'),\n" +
            " (37,'Size & label mistake'),\n" +
            " (38,'Uncut thread'),\n" +
            " (39,'Loose thread'),\n" +
            " (40,'Yarn contamination'),\n" +
            " (41,'Wrong mending'),\n" +
            " (42,'Loose button'),\n" +
            " (43,'Button up down'),\n" +
            " (44,'Un even leg shape'),\n" +
            " (45,'Fabric fault'),\n" +
            " (46,'Stain mark'),\n" +
            " (47,'Colour shading'),\n" +
            " (48,'Wrong SPI'),\n" +
            " (49,'Missing bow'),\n" +
            " (50,'In secure bot attaching'),\n" +
            " (51,'Missing bar tack'),\n" +
            " (52,'Pointy side seam'),\n" +
            " (53,'Un even gusset shape'),\n" +
            " (54,'Un cut thread end'),\n" +
            " (55,'Un even neck shape'),\n" +
            " (56,'uneven neck length'),\n" +
            " (57,'Colour shading cups'),\n" +
            " (58,'Mirror unmatching'),\n" +
            " (59,'Un even underarm shapes'),\n" +
            " (60,'Uneven underarm length'),\n" +
            " (61,'Tight on cup dressing'),\n" +
            " (62,'Wire play +'),\n" +
            " (63,'Wire play -'),\n" +
            " (64,'Visible wire casing'),\n" +
            " (65,'Bar tack gap uneven'),\n" +
            " (66,'Slanted bar tack'),\n" +
            " (67,'Wire casing fray edges'),\n" +
            " (68,'Uneven code on wire casing stitch'),\n" +
            " (69,'Cracking stitches'),\n" +
            " (70,'Cut stitches'),\n" +
            " (71,'Un even seam allowances'),\n" +
            " (72,'Raw edges'),\n" +
            " (73,'Slant apex'),\n" +
            " (74,'Un even CF curve'),\n" +
            " (75,'High/Low'),\n" +
            " (76,'Puckering'),\n" +
            " (77,'Looseness'),\n" +
            " (78,'CF top width +'),\n" +
            " (79,'Un even CF'),\n" +
            " (80,'Flipping CF bottom'),\n" +
            " (81,'Waviness'),\n" +
            " (83,'Un even wing shapes'),\n" +
            " (84,'Un even wing length'),\n" +
            " (85,'Shirring un balance'),\n" +
            " (86,'Un even cup height'),\n" +
            " (87,'Uneven strap length'),\n" +
            " (88,'Wrong side strap'),\n" +
            " (89,'Un even raw edge'),\n" +
            " (90,'Twisted strap'),\n" +
            " (91,'Pointy bar tack'),\n" +
            " (92,'Incorrect hook & eye angle attaching'),\n" +
            " (93,'Wing seam unbalance'),\n" +
            " (94,'Incorrect strap angle'),\n" +
            " (95,'Grinng'),\n" +
            " (96,'Un even shirring'),\n" +
            " (97,'Gap uneven'),\n" +
            " (98,'Sharp bar tack edges'),\n" +
            " (99,'High/low  @side seam'),\n" +
            " (100,'Un even shirring  on leg hem'),\n" +
            " (101,'un even shirring  on waist'),\n" +
            " (102,'Open seam @ leg'),\n" +
            " (103,'Open seam @ waist'),\n" +
            " (104,'Cut stitches @ leg'),\n" +
            " (105,'Cut stitches @ waist'),\n" +
            " (106,'Cracking stitches @ waist'),\n" +
            " (107,'Cracking stitches @ leg'),\n" +
            " (108,'Un cut Thread end @ waist'),\n" +
            " (109,'Un cut Thread end @ leg'),\n" +
            " (110,'Uneven raw edge @ waist'),\n" +
            " (111,'Uneven raw edge @ leg'),\n" +
            " (112,'High /Low @ leg'),\n" +
            " (113,'Gap uneven @ both side'),\n" +
            " (114,'Waviness on leg'),\n" +
            " (115,'Waviness on waist'),\n" +
            " (116,'Un even shirring @ gusset'),\n" +
            " (117,'Cracking stitches @ guest leg'),\n" +
            " (118,'cracking stitches @ front gusset'),\n" +
            " (119,'cracking stitches @ back gusset'),\n" +
            " (120,'Open seam @ back gusset'),\n" +
            " (121,'Open seam @ front gusset'),\n" +
            " (122,'Sharp bar tack edges @side seam'),\n" +
            " (123,'High/Low @ leg elastic joint'),\n" +
            " (124,'High/Low @ waist elastic joint'),\n" +
            " (125,'Waviness @side seam'),\n" +
            " (126,'High /Low at waist elastic joint');";


    public static String defectPosByIDsql = "SELECT DEFECTPOSITION.NAME FROM DEFECTPOSITION WHERE ID = ";
    public static String insertDefectPosition = "INSERT INTO \"DEFECTPOSITION\" (\"ID\",\"NAME\") VALUES (2,'Front Left'),\n" +
            " (3,'Front Right '),\n" +
            " (4,'Middle Front'),\n" +
            " (5,'Front Rib'),\n" +
            " (6,'Front Rib to Body Seam'),\n" +
            " (7,'Front Rib Hem'),\n" +
            " (8,'Front Rib Right Seam '),\n" +
            " (9,'Front Rib Left Seam'),\n" +
            " (10,'Front Left Side Seam'),\n" +
            " (11,'Front Right Side Seam'),\n" +
            " (12,'Body to Sleeve Join right'),\n" +
            " (13,'Body to Sleeve Join Left'),\n" +
            " (14,'Collar'),\n" +
            " (15,'Right Shoulder'),\n" +
            " (16,'Left Shoulder'),\n" +
            " (17,'Cuff Right'),\n" +
            " (18,'Cuff Left'),\n" +
            " (19,'Sleeve In Seam right'),\n" +
            " (20,'Sleeve In seam Left'),\n" +
            " (21,'Right Sleve'),\n" +
            " (22,'Left Sleeve'),\n" +
            " (23,'Neck'),\n" +
            " (24,'Underarm'),\n" +
            " (25,'Around cup'),\n" +
            " (26,'Cup seam'),\n" +
            " (27,'Apex'),\n" +
            " (28,'Top wing'),\n" +
            " (29,'Bottom wing'),\n" +
            " (30,'Front Strap'),\n" +
            " (31,'Back Strap'),\n" +
            " (32,'Hook & Eye'),\n" +
            " (33,'CF'),\n" +
            " (34,'Front'),\n" +
            " (35,'Back'),\n" +
            " (36,'Gusset'),\n" +
            " (37,'Side Seam');";


    /*COLORS*/
    public static String allColor = "SELECT * FROM COLOR;";
    public static String allColorWhere = "SELECT COLOR.id, COLOR.ColorName FROM ColorsInPO \n" +
            "\tJOIN PO ON ColorsInPO.PO_ID = PO.ID\n" +
            "\tJOIN Color ON ColorsInPO.Color_ID = Color.ID\n" +
            "\tJOIN Line ON ColorsInPO.Line_ID = Line.ID\n" +
            "WHERE PO.PoNo = \"";


    public static String allSizes = "SELECT * FROM SIZE;";
    public static String insertSize = "INSERT INTO \"SIZE\" (\"id\",\"SizeName\") VALUES (1,'0');";


    public static String allDefectUsingPosition = "SELECT DEFECT.ID, DEFECT.NAME FROM STYLE_SUBCAT_DEFECTS\n" +
            "JOIN DEFECT ON STYLE_SUBCAT_DEFECTS.DEFECT_ID = DEFECT.ID\n" +
            "JOIN DEFECTPOSITION ON STYLE_SUBCAT_DEFECTS.DEFECT_POSITION_ID = DEFECTPOSITION.ID\n" +
            "JOIN STYLESUBCATEGORY ON STYLE_SUBCAT_DEFECTS.SLTLE_SUBCAT_ID = STYLESUBCATEGORY.ID\n" +
            "JOIN STYLECATEGORY ON STYLECATEGORY.ID = STYLESUBCATEGORY.STYLECATEGORY\n" +
            "WHERE STYLE_SUBCAT_DEFECTS.DEFECT_POSITION_ID = ";


    public static String allProductionUnit = "SELECT * FROM ProductionUnit;";
    public static String insertProductionUnit = "INSERT INTO \"ProductionUnit\" (\"ID\",\"Name\") VALUES" +
            " (1,'Newton');";


    public static String allLines = "SELECT * FROM LINE;";
    public static String insertLines = "INSERT INTO \"LINE\" (\"ID\",\"Name\") VALUES "
            + "(1,'KQC Table-1'),"
            + "(2,'KQC Table-2'),"
            + "(3,'KQC Table-3'),"
            + "(4,'KQC Table-4'),"
            + "(5,'KQC Table-5'),"
            + "(6,'KQC Table-6'),"
            + "(7,'KQC Table-7'),"
            + "(8,'KQC Table-8'),"
            + "(9,'KQC Table-9'),"
            + "(10,'KQC Table-10'),"
            + "(11,'KQC Table-11'),"
            + "(12,'KQC Table-12'),"
            + "(13,'KQC Table-13'),"
            + "(14,'KQC Table-14'),"
            + "(15,'KQC Table-15'),"
            + "(16,'KQC Table-16'),"
            + "(17,'KQC Table-17'),"
            + "(18,'KQC Table-18'),"
            + "(19,'KQC Table-19'),"
            + "(20,'KQC Table-20'),"
            + "(21,'KQC Table-21'),"
            + "(22,'KQC Table-22'),"
            + "(23,'KQC Table-23'),"
            + "(24,'KQC Table-24'),"
            + "(25,'KQC Table-25'),"
            + "(26,'KQC Table-26'),"
            + "(27,'KQC Table-27'),"
            + "(28,'KQC Table-28'),"
            + "(29,'KQC Table-29'),"
            + "(30,'KQC Table-30'),"
            + "(31,'KQC Table-31'),"
            + "(32,'KQC Table-32'),"
            + "(33,'KQC Table-33'),"
            + "(34,'KQC Table-34'),"
            + "(35,'KQC Table-35'),"
            + "(36,'KQC Table-36'),"
            + "(37,'KQC Table-37'),"
            + "(38,'KQC Table-38'),"
            + "(39,'KQC Table-39'),"
            + "(40,'KQC Table-40'),"
            + "(41,'KQC Table-41'),"
            + "(42,'KQC Table-42'),"
            + "(43,'KQC Table-43'),"
            + "(44,'KQC Table-44'),"
            + "(45,'KQC Table-45'),"
            + "(46,'KQC Table-46'),"
            + "(47,'KQC Table-47'),"
            + "(48,'KQC Table-48'),"
            + "(49,'KQC Table-49'),"
            + "(50,'KQC Table-50'),"
            + "(51,'KQC Table-51'),"
            + "(52,'KQC Table-52'),"
            + "(53,'KQC Table-53'),"
            + "(54,'KQC Table-54'),"
            + "(55,'KQC Table-55'),"
            + "(56,'KQC Table-56'),"
            + "(57,'KQC Table-57'),"
            + "(58,'KQC Table-58'),"
            + "(59,'KQC Table-59'),"
            + "(60,'KQC Table-60'),"
            + "(61,'KQC Table-61'),"
            + "(62,'KQC Table-62'),"
            + "(63,'KQC Table-63'),"
            + "(64,'KQC Table-64'),"
            + "(65,'KQC Table-65'),"
            + "(66,'KQC Table-66'),"
            + "(67,'KQC Table-67'),"
            + "(68,'KQC Table-68'),"
            + "(69,'KQC Table-69'),"
            + "(70,'KQC Table-70'),"
            + "(71,'KQC Table-71'),"
            + "(72,'KQC Table-72'),"
            + "(73,'KQC Table-73'),"
            + "(74,'KQC Table-74'),"
            + "(75,'KQC Table-75'),"
            + "(76,'KQC Table-76');";


    public static String allModules = "SELECT * FROM MODULE;";
    public static String insertModules = "INSERT INTO \"MODULE\" (\"ID\",\"Name\") VALUES" +
            " (1,'Knitting');";


    /*BUYERS */
    public static String allBuyers = "SELECT * FROM BUYER;";
    public static String allBuyersWhereFloorNo = "SELECT DISTINCT BUYER.ID, BUYER.Name FROM StyleInBuyers \n" +
            "JOIN BUYER on StyleInBuyers.BuyerID = BUYER.ID\n" +
            "JOIN StyleSubCategory on StyleInBuyers.StyleSubCatID = StyleSubCategory.ID\n" +
            "JOIN Line on StyleInBuyers.LineID = Line.ID \n" +
            "JOIN StyleCategory on StyleInBuyers.StyleCateID = StyleCategory.ID\n" +
            "WHERE Line.name = '";


    public static String possBySubCatID = "SELECT PO.ID, PO.PoNo, PO.StyleSubCatID, StyleSubCategory.name, StyleSubCategory.StyleCategory FROM POsInStyle\n" +
            "\tJOIN PO ON POsInStyle.PO_ID = PO.ID\n" +
            "\tJOIN StyleSubCategory ON POsInStyle.StyleSubCatID = StyleSubCategory.ID\n" +
            "\tJOIN Line ON POsInStyle.Line_ID = Line.ID WHERE \n" +
            "\tPOsInStyle.StyleSubCatID = ";


    public static String styleMapDataByID = "SELECT * FROM STYLE_MAP WHERE STYLE_MAP.StyleSubCatID =";
    public static String insertStyleMapData = "INSERT INTO \"STYLE_MAP\" (\"ID\",\"Pixelno\",\"DefectPosIDFront\",\"DefectPosIDBack\",\"StyleSubCatID\") VALUES (1,1,0,0,1),\n" +
            " (2,2,0,0,1),\n" +
            " (3,3,0,0,1),\n" +
            " (4,4,15,15,1),\n" +
            " (5,5,15,15,1),\n" +
            " (6,6,23,23,1),\n" +
            " (7,7,23,23,1),\n" +
            " (8,8,23,23,1),\n" +
            " (9,9,16,16,1),\n" +
            " (10,10,0,0,1),\n" +
            " (11,11,0,0,1),\n" +
            " (12,12,0,0,1),\n" +
            " (13,13,0,0,1),\n" +
            " (14,14,0,0,1),\n" +
            " (15,15,0,0,1),\n" +
            " (16,16,0,0,1),\n" +
            " (17,17,0,0,1),\n" +
            " (18,18,0,0,1),\n" +
            " (19,19,15,15,1),\n" +
            " (20,20,15,15,1),\n" +
            " (21,21,14,14,1),\n" +
            " (22,22,14,14,1),\n" +
            " (23,23,14,14,1),\n" +
            " (24,24,16,16,1),\n" +
            " (25,25,16,16,1),\n" +
            " (26,26,0,0,1),\n" +
            " (27,27,0,0,1),\n" +
            " (28,28,0,0,1),\n" +
            " (29,29,0,0,1),\n" +
            " (30,30,0,0,1),\n" +
            " (31,31,0,0,1),\n" +
            " (32,32,0,0,1),\n" +
            " (33,33,21,21,1),\n" +
            " (34,34,21,21,1),\n" +
            " (35,35,12,12,1),\n" +
            " (36,36,3,3,1),\n" +
            " (37,37,4,3,1),\n" +
            " (38,38,2,2,1),\n" +
            " (39,39,2,2,1),\n" +
            " (40,40,13,13,1),\n" +
            " (41,41,22,22,1),\n" +
            " (42,42,0,0,1),\n" +
            " (43,43,0,0,1),\n" +
            " (44,44,0,0,1),\n" +
            " (45,45,0,0,1),\n" +
            " (46,46,0,0,1),\n" +
            " (47,47,21,21,1),\n" +
            " (48,48,21,21,1),\n" +
            " (49,49,21,21,1),\n" +
            " (50,50,12,12,1),\n" +
            " (51,51,3,3,1),\n" +
            " (52,52,4,3,1),\n" +
            " (53,53,2,2,1),\n" +
            " (54,54,2,2,1),\n" +
            " (55,55,22,22,1),\n" +
            " (56,56,22,22,1),\n" +
            " (57,57,0,0,1),\n" +
            " (58,58,0,0,1),\n" +
            " (59,59,0,0,1),\n" +
            " (60,60,0,0,1),\n" +
            " (61,61,0,0,1),\n" +
            " (62,62,21,21,1),\n" +
            " (63,63,21,21,1),\n" +
            " (64,64,19,19,1),\n" +
            " (65,65,12,12,1),\n" +
            " (66,66,3,3,1),\n" +
            " (67,67,4,3,1),\n" +
            " (68,68,2,2,1),\n" +
            " (69,69,2,2,1),\n" +
            " (70,70,13,13,1),\n" +
            " (71,71,22,22,1),\n" +
            " (72,72,22,22,1),\n" +
            " (73,73,0,0,1),\n" +
            " (74,74,0,0,1),\n" +
            " (75,75,0,0,1),\n" +
            " (76,76,21,21,1),\n" +
            " (77,77,21,21,1),\n" +
            " (78,78,21,21,1),\n" +
            " (79,79,19,19,1),\n" +
            " (80,80,3,3,1),\n" +
            " (81,81,3,3,1),\n" +
            " (82,82,4,3,1),\n" +
            " (83,83,2,2,1),\n" +
            " (84,84,2,2,1),\n" +
            " (85,85,13,13,1),\n" +
            " (86,86,20,20,1),\n" +
            " (87,87,22,22,1),\n" +
            " (88,88,0,0,1),\n" +
            " (89,89,0,0,1),\n" +
            " (90,90,0,0,1),\n" +
            " (91,91,21,21,1),\n" +
            " (92,92,21,21,1),\n" +
            " (93,93,21,21,1),\n" +
            " (94,94,11,11,1),\n" +
            " (95,95,3,3,1),\n" +
            " (96,96,3,3,1),\n" +
            " (97,97,4,3,1),\n" +
            " (98,98,2,2,1),\n" +
            " (99,99,2,2,1),\n" +
            " (100,100,10,10,1),\n" +
            " (101,101,20,20,1),\n" +
            " (102,102,22,22,1),\n" +
            " (103,103,22,22,1),\n" +
            " (104,104,0,0,1),\n" +
            " (105,105,21,21,1),\n" +
            " (106,106,21,21,1),\n" +
            " (107,107,21,21,1),\n" +
            " (108,108,19,19,1),\n" +
            " (109,109,11,11,1),\n" +
            " (110,110,10,10,1),\n" +
            " (111,111,3,3,1),\n" +
            " (112,112,4,3,1),\n" +
            " (113,113,2,2,1),\n" +
            " (114,114,2,2,1),\n" +
            " (115,115,10,10,1),\n" +
            " (116,116,20,20,1),\n" +
            " (117,117,22,22,1),\n" +
            " (118,118,22,22,1),\n" +
            " (119,119,0,0,1),\n" +
            " (120,120,21,21,1),\n" +
            " (121,121,21,21,1),\n" +
            " (122,122,19,19,1),\n" +
            " (123,123,0,0,1),\n" +
            " (124,124,11,11,1),\n" +
            " (125,125,3,3,1),\n" +
            " (126,126,3,3,1),\n" +
            " (127,127,4,3,1),\n" +
            " (128,128,2,2,1),\n" +
            " (129,129,2,2,1),\n" +
            " (130,130,10,10,1),\n" +
            " (131,131,20,20,1),\n" +
            " (132,132,20,20,1),\n" +
            " (133,133,22,22,1),\n" +
            " (134,134,0,0,1),\n" +
            " (135,135,21,21,1),\n" +
            " (136,136,21,21,1),\n" +
            " (137,137,19,19,1),\n" +
            " (138,138,0,0,1),\n" +
            " (139,139,11,11,1),\n" +
            " (140,140,3,3,1),\n" +
            " (141,141,3,3,1),\n" +
            " (142,142,4,3,1),\n" +
            " (143,143,2,2,1),\n" +
            " (144,144,2,2,1),\n" +
            " (145,145,10,10,1),\n" +
            " (146,146,0,0,1),\n" +
            " (147,147,20,20,1),\n" +
            " (148,148,22,22,1),\n" +
            " (149,149,22,22,1),\n" +
            " (150,150,17,17,1),\n" +
            " (151,151,17,17,1),\n" +
            " (152,152,0,0,1),\n" +
            " (153,153,0,0,1),\n" +
            " (154,154,11,11,1),\n" +
            " (155,155,3,3,1),\n" +
            " (156,156,3,3,1),\n" +
            " (157,157,4,3,1),\n" +
            " (158,158,2,2,1),\n" +
            " (159,159,2,2,1),\n" +
            " (160,160,10,10,1),\n" +
            " (161,161,0,0,1),\n" +
            " (162,162,20,20,1),\n" +
            " (163,163,18,18,1),\n" +
            " (164,164,18,18,1),\n" +
            " (165,165,17,17,1),\n" +
            " (166,166,17,17,1),\n" +
            " (167,167,0,0,1),\n" +
            " (168,168,0,0,1),\n" +
            " (169,169,11,11,1),\n" +
            " (170,170,8,8,1),\n" +
            " (171,171,7,7,1),\n" +
            " (172,172,7,7,1),\n" +
            " (173,173,7,7,1),\n" +
            " (174,174,7,7,1),\n" +
            " (175,175,6,6,1),\n" +
            " (176,176,0,0,1),\n" +
            " (177,177,0,0,1),\n" +
            " (178,178,18,18,1),\n" +
            " (179,179,18,18,1),\n" +
            " (180,180,0,0,1),\n" +
            " (181,181,0,0,1),\n" +
            " (182,182,0,0,1),\n" +
            " (183,183,0,0,1),\n" +
            " (184,184,8,8,1),\n" +
            " (185,185,8,8,1),\n" +
            " (186,186,5,5,1),\n" +
            " (187,187,5,5,1),\n" +
            " (188,188,5,5,1),\n" +
            " (189,189,5,5,1),\n" +
            " (190,190,9,9,1),\n" +
            " (191,191,0,0,1),\n" +
            " (192,192,0,0,1),\n" +
            " (193,193,0,0,1),\n" +
            " (194,194,0,0,1),\n" +
            " (195,0,0,0,1),\n" +
            " (196,1,0,0,27),\n" +
            " (197,2,0,0,27),\n" +
            " (198,3,0,0,27),\n" +
            " (199,4,0,0,27),\n" +
            " (200,5,0,0,27),\n" +
            " (201,6,0,0,27),\n" +
            " (202,7,0,0,27),\n" +
            " (203,8,0,0,27),\n" +
            " (204,9,0,0,27),\n" +
            " (205,10,0,0,27),\n" +
            " (206,11,0,0,27),\n" +
            " (207,12,0,0,27),\n" +
            " (208,13,0,0,27),\n" +
            " (209,14,0,0,27),\n" +
            " (210,15,0,0,27),\n" +
            " (211,16,0,0,27),\n" +
            " (212,17,0,37,27),\n" +
            " (213,18,0,35,27),\n" +
            " (214,19,0,35,27),\n" +
            " (215,20,0,35,27),\n" +
            " (216,21,0,35,27),\n" +
            " (217,22,0,35,27),\n" +
            " (218,23,0,35,27),\n" +
            " (219,24,0,35,27),\n" +
            " (220,25,0,35,27),\n" +
            " (221,26,0,35,27),\n" +
            " (222,27,0,37,27),\n" +
            " (223,28,0,0,27),\n" +
            " (224,29,0,0,27),\n" +
            " (225,30,0,0,27),\n" +
            " (226,31,37,37,27),\n" +
            " (227,32,37,37,27),\n" +
            " (228,33,0,35,27),\n" +
            " (229,34,0,35,27),\n" +
            " (230,35,0,35,27),\n" +
            " (231,36,0,35,27),\n" +
            " (232,37,0,35,27),\n" +
            " (233,38,0,35,27),\n" +
            " (234,39,0,35,27),\n" +
            " (235,40,0,35,27),\n" +
            " (236,41,0,35,27),\n" +
            " (237,42,37,37,27),\n" +
            " (238,43,37,37,27),\n" +
            " (239,44,0,0,27),\n" +
            " (240,45,0,0,27),\n" +
            " (241,46,37,37,27),\n" +
            " (242,47,37,37,27),\n" +
            " (243,48,34,35,27),\n" +
            " (244,49,34,35,27),\n" +
            " (245,50,0,35,27),\n" +
            " (246,51,0,35,27),\n" +
            " (247,52,0,35,27),\n" +
            " (248,53,0,35,27),\n" +
            " (249,54,0,35,27),\n" +
            " (250,55,34,35,27),\n" +
            " (251,56,34,35,27),\n" +
            " (252,57,37,37,27),\n" +
            " (253,58,37,37,27),\n" +
            " (254,59,0,0,27),\n" +
            " (255,60,0,0,27),\n" +
            " (256,61,37,37,27),\n" +
            " (257,62,37,37,27),\n" +
            " (258,63,34,35,27),\n" +
            " (259,64,34,35,27),\n" +
            " (260,65,34,35,27),\n" +
            " (261,66,34,35,27),\n" +
            " (262,67,34,35,27),\n" +
            " (263,68,34,35,27),\n" +
            " (264,69,34,35,27),\n" +
            " (265,70,34,35,27),\n" +
            " (266,71,34,35,27),\n" +
            " (267,72,37,37,27),\n" +
            " (268,73,37,37,27),\n" +
            " (269,74,0,37,27),\n" +
            " (270,75,37,0,27),\n" +
            " (271,76,37,37,27),\n" +
            " (272,77,37,37,27),\n" +
            " (273,78,34,35,27),\n" +
            " (274,79,34,35,27),\n" +
            " (275,80,34,35,27),\n" +
            " (276,81,34,35,27),\n" +
            " (277,82,34,35,27),\n" +
            " (278,83,34,35,27),\n" +
            " (279,84,34,35,27),\n" +
            " (280,85,34,35,27),\n" +
            " (281,86,34,35,27),\n" +
            " (282,87,37,37,27),\n" +
            " (283,88,37,37,27),\n" +
            " (284,89,37,37,27),\n" +
            " (285,90,0,0,27),\n" +
            " (286,91,37,0,27),\n" +
            " (287,92,37,0,27),\n" +
            " (288,93,34,35,27),\n" +
            " (289,94,34,35,27),\n" +
            " (290,95,34,35,27),\n" +
            " (291,96,34,35,27),\n" +
            " (292,97,34,35,27),\n" +
            " (293,98,34,35,27),\n" +
            " (294,99,34,35,27),\n" +
            " (295,100,34,35,27),\n" +
            " (296,101,34,35,27),\n" +
            " (297,102,37,0,27),\n" +
            " (298,103,37,0,27),\n" +
            " (299,104,0,0,27),\n" +
            " (300,105,0,0,27),\n" +
            " (301,106,0,0,27),\n" +
            " (302,107,0,0,27),\n" +
            " (303,108,0,0,27),\n" +
            " (304,109,34,35,27),\n" +
            " (305,110,34,35,27),\n" +
            " (306,111,34,35,27),\n" +
            " (307,112,34,35,27),\n" +
            " (308,113,34,35,27),\n" +
            " (309,114,34,35,27),\n" +
            " (310,115,34,35,27),\n" +
            " (311,116,0,0,27),\n" +
            " (312,117,0,0,27),\n" +
            " (313,118,0,0,27),\n" +
            " (314,119,0,0,27),\n" +
            " (315,120,0,0,27),\n" +
            " (316,121,0,0,27),\n" +
            " (317,122,0,0,27),\n" +
            " (318,123,0,0,27),\n" +
            " (319,124,0,0,27),\n" +
            " (320,125,34,35,27),\n" +
            " (321,126,34,35,27),\n" +
            " (322,127,34,35,27),\n" +
            " (323,128,34,35,27),\n" +
            " (324,129,34,35,27),\n" +
            " (325,130,0,0,27),\n" +
            " (326,131,0,0,27),\n" +
            " (327,132,0,0,27),\n" +
            " (328,133,0,0,27),\n" +
            " (329,134,0,0,27),\n" +
            " (330,135,0,0,27),\n" +
            " (331,136,0,0,27),\n" +
            " (332,137,0,0,27),\n" +
            " (333,138,0,0,27),\n" +
            " (334,139,0,0,27),\n" +
            " (335,140,36,36,27),\n" +
            " (336,141,36,36,27),\n" +
            " (337,142,36,36,27),\n" +
            " (338,143,36,36,27),\n" +
            " (339,144,36,36,27),\n" +
            " (340,145,0,0,27),\n" +
            " (341,146,0,0,27),\n" +
            " (342,147,0,0,27),\n" +
            " (343,148,0,0,27),\n" +
            " (344,149,0,0,27),\n" +
            " (345,150,0,0,27),\n" +
            " (346,151,0,0,27),\n" +
            " (347,152,0,0,27),\n" +
            " (348,153,0,0,27),\n" +
            " (349,154,0,0,27),\n" +
            " (350,155,36,36,27),\n" +
            " (351,156,36,36,27),\n" +
            " (352,157,36,36,27),\n" +
            " (353,158,36,36,27),\n" +
            " (354,159,36,36,27),\n" +
            " (355,160,0,0,27),\n" +
            " (356,161,0,0,27),\n" +
            " (357,162,0,0,27),\n" +
            " (358,163,0,0,27),\n" +
            " (359,164,0,0,27),\n" +
            " (360,165,0,0,27),\n" +
            " (361,166,0,0,27),\n" +
            " (362,167,0,0,27),\n" +
            " (363,168,0,0,27),\n" +
            " (364,169,0,0,27),\n" +
            " (365,170,0,0,27),\n" +
            " (366,171,36,36,27),\n" +
            " (367,172,36,36,27),\n" +
            " (368,173,36,36,27),\n" +
            " (369,174,0,0,27),\n" +
            " (370,175,0,0,27),\n" +
            " (371,176,0,0,27),\n" +
            " (372,177,0,0,27),\n" +
            " (373,178,0,0,27),\n" +
            " (374,179,0,0,27),\n" +
            " (375,180,0,0,27),\n" +
            " (376,181,0,0,27),\n" +
            " (377,182,0,0,27),\n" +
            " (378,183,0,0,27),\n" +
            " (379,184,0,0,27),\n" +
            " (380,185,0,0,27),\n" +
            " (381,186,0,0,27),\n" +
            " (382,187,0,0,27),\n" +
            " (383,188,0,0,27),\n" +
            " (384,189,0,0,27),\n" +
            " (385,190,0,0,27),\n" +
            " (386,191,0,0,27),\n" +
            " (387,192,0,0,27),\n" +
            " (388,193,0,0,27),\n" +
            " (389,194,0,0,27),\n" +
            " (390,0,0,0,27);";

    /*
* OperatorID
MachineID
*
* */

    public static String selectAllQCdata = "Select * FROM QCDataTable;";
    public static String selectAllDefectListData = "SELECT Unit, Line, Time, Date, BatchQty, BuyerName, StyleCategory, StyleSubCategory, GarmentsNo, GarmentPos, IFNULL(DEFECT.NAME, 'na') as NAME  , IFNULL(DefectCount, 0) as DefectCount, Color, DefectPos, Size, QCGarmentsDefect.LotNo, QCGarmentsDefect.PO, SMV, QCGarmentsDefect.OperatorID, QCGarmentsDefect.MachineID\n" +
            "FROM QCGarmentsDefect \n" +
            "LEFT JOIN QCGarmentDefectCounts ON QCGarmentsDefect.ID = QCGarmentDefectCounts.QCGarmentDefectID \n" +
            "LEFT JOIN DEFECT on QCGarmentDefectCounts.DefectID = DEFECT.ID \n" +
            "WHERE Date = '";

    public static String selectAllDefectListDataByGarmentsNo = "SELECT Unit, Line, Time, Date, BatchQty, BuyerName, StyleCategory, StyleSubCategory, GarmentsNo, GarmentPos, DEFECT.NAME , DefectCount, Color, DefectPos, Size, QCGarmentsDefect.LotNo, QCGarmentsDefect.PO, SMV, QCGarmentsDefect.OperatorID, QCGarmentsDefect.MachineID\n" +
            "FROM QCGarmentsDefect \n" +
            "JOIN QCGarmentDefectCounts on QCGarmentsDefect.ID = QCGarmentDefectCounts.QCGarmentDefectID\n" +
            "JOIN DEFECT on QCGarmentDefectCounts.DefectID = DEFECT.ID\n" +
            "WHERE GarmentsNo = ";

    public static String selectDefectIDByName = "SELECT ID FROM \"DEFECT\" WHERE DEFECT.NAME = ";


    public static String totalGarmentsEntryInADay = "SELECT DISTINCT LotNo, GarmentsNo\n" +
            "FROM QCGarmentsDefect  \n" +
            "LEFT JOIN QCGarmentDefectCounts ON QCGarmentsDefect.ID = QCGarmentDefectCounts.QCGarmentDefectID \n" +
            "LEFT JOIN DEFECT on QCGarmentDefectCounts.DefectID = DEFECT.ID \n" +
            "WHERE Date = '";


    public static String selectAllDefectListDataForSending = "SELECT Unit, Line, Time, Date, BatchQty, BuyerName, StyleCategory, StyleSubCategory, GarmentsNo, GarmentPos, IFNULL(DEFECT.NAME, 'na') as NAME  , IFNULL(DefectCount, 0) as DefectCount, Color, DefectPos, Size, QCGarmentsDefect.LotNo, QCGarmentsDefect.PO, SMV, QCGarmentsDefect.ID, QCGarmentsDefect.OperatorID, QCGarmentsDefect.MachineID \n" +
            "FROM QCGarmentsDefect \n" +
            "LEFT JOIN QCGarmentDefectCounts ON QCGarmentsDefect.ID = QCGarmentDefectCounts.QCGarmentDefectID \n" +
            "LEFT JOIN DEFECT on QCGarmentDefectCounts.DefectID = DEFECT.ID \n" +
            "WHERE Date = '";

    public static String selectAllDefectListDataByGarmentsNoForDeleting = "SELECT Unit, Line, Time, Date, BatchQty, BuyerName, StyleCategory, StyleSubCategory, GarmentsNo, GarmentPos, IFNULL(DEFECT.NAME, 'na') as NAME  , IFNULL(DefectCount, 0) as DefectCount, Color, DefectPos, Size, QCGarmentsDefect.LotNo, QCGarmentsDefect.PO, SMV, QCGarmentsDefect.ID, QCGarmentsDefect.OperatorID, QCGarmentsDefect.MachineID \n" +
            "FROM QCGarmentsDefect \n" +
            "LEFT JOIN QCGarmentDefectCounts on QCGarmentsDefect.ID = QCGarmentDefectCounts.QCGarmentDefectID\n" +
            "LEFT JOIN DEFECT on QCGarmentDefectCounts.DefectID = DEFECT.ID\n" +
            "WHERE GarmentsNo = ";


}
