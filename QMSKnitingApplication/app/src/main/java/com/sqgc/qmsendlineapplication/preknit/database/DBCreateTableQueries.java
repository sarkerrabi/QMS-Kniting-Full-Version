/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 7/20/20 5:47 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/20/20 5:45 PM
 *
 */

package com.sqgc.qmsendlineapplication.preknit.database;

public class DBCreateTableQueries {
    //Create Tables
    public static String createStyleCategory = "CREATE TABLE IF NOT EXISTS STYLECATEGORY ( ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL UNIQUE);";
    public static String createStyleSubCategory = "CREATE TABLE IF NOT EXISTS \"STYLESUBCATEGORY\" (" +
            " \"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            " \"NAME\" TEXT NOT NULL,\n" +
            " \"STYLECATEGORY\" INTEGER NOT NULL" +
            ");";

    public static String createDefect = "CREATE TABLE IF NOT EXISTS \"DEFECT\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"NAME\"\tTEXT NOT NULL UNIQUE\n" +
            ");";

    public static String createDefectPosition = "CREATE TABLE IF NOT EXISTS \"DEFECTPOSITION\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"NAME\"\tTEXT NOT NULL UNIQUE\n" +
            ");";

    public static String createStyleSubCatDefect = "CREATE TABLE IF NOT EXISTS \"STYLE_SUBCAT_DEFECTS\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"SLTLE_SUBCAT_ID\"\tINTEGER NOT NULL,\n" +
            "\t\"DEFECT_POSITION_ID\"\tINTEGER NOT NULL,\n" +
            "\t\"DEFECT_ID\"\tINTEGER NOT NULL\n" +
            ");";

    public static String createColorTable = "CREATE TABLE IF NOT EXISTS \"COLOR\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"ColorName\"\tTEXT NOT NULL UNIQUE\n" +
            ");";

    public static String createSizeTable = "CREATE TABLE IF NOT EXISTS \"SIZE\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"SizeName\"\tTEXT NOT NULL UNIQUE\n" +
            ");";

    public static String createQCGarmentDefectCounts = "CREATE TABLE \"QCGarmentDefectCounts\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"QCGarmentDefectID\"\tINTEGER NOT NULL,\n" +
            "\t\"DefectID\"\tINTEGER NOT NULL,\n" +
            "\t\"DefectCount\"\tINTEGER NOT NULL\n" +
            ");";

    public static String createQCGarmentsDefect = "CREATE TABLE \"QCGarmentsDefect\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"Unit\"\tTEXT NOT NULL,\n" +
            "\t\"Line\"\tTEXT NOT NULL,\n" +
            "\t\"Date\"\tTEXT NOT NULL,\n" +
            "\t\"Time\"\tTEXT NOT NULL,\n" +
            "\t\"BuyerName\"\tTEXT NOT NULL,\n" +
            "\t\"BatchQty\"\tTEXT NOT NULL,\n" +
            "\t\"StyleCategory\"\tTEXT NOT NULL,\n" +
            "\t\"StyleSubCategory\"\tTEXT NOT NULL,\n" +
            "\t\"GarmentsNo\"\tTEXT NOT NULL,\n" +
            "\t\"GarmentPos\"\tTEXT NOT NULL,\n" +
            "\t\"Color\"\tTEXT NOT NULL,\n" +
            "\t\"DefectPos\"\tTEXT NOT NULL,\n" +
            "\t\"Size\"\tTEXT NOT NULL,\n" +
            "\t\"LotNo\"\tINTEGER NOT NULL,\n" +
            "\t\"PO\"\tTEXT NOT NULL,\n" +
            "\t\"SMV\"\tTEXT NOT NULL,\n" +
            "\t\"isSynced\"\tINTEGER NOT NULL DEFAULT 0,\n" +
            "\t\"OperatorID\"\tTEXT NOT NULL,\n" +
            "\t\"MachineID\"\tTEXT NOT NULL,\n" +
            "\t\"UserID\"\tTEXT NOT NULL,\n" +
            "\t\"ServerLotNo\"\tTEXT NOT NULL\n" +
            ")";


    public static String createProductionUnitTable = "CREATE TABLE IF NOT EXISTS \"ProductionUnit\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"Name\"\tTEXT NOT NULL UNIQUE\n" +
            ");";
    public static String createLineTable = "CREATE TABLE IF NOT EXISTS \"LINE\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"Name\"\tTEXT NOT NULL UNIQUE\n" +
            ");";
    public static String createModuleTable = "CREATE TABLE IF NOT EXISTS \"MODULE\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"Name\"\tTEXT NOT NULL UNIQUE\n" +
            ");";

    public static String createBuyerTable = "CREATE TABLE IF NOT EXISTS \"BUYER\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"Name\"\tINTEGER UNIQUE\n" +
            ");";

    public static String createPOTable = "CREATE TABLE IF NOT EXISTS \"PO\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"PoNo\"\tTEXT NOT NULL UNIQUE,\n" +
            "\t\"StyleSubCatID\"\tINTEGER NOT NULL,\n" +
            "\t\"ColorID\"\tINTEGER\n" +
            ");";

    public static String createStyleMapTable = "CREATE TABLE IF NOT EXISTS \"STYLE_MAP\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"Pixelno\"\tINTEGER NOT NULL,\n" +
            "\t\"DefectPosIDFront\"\tINTEGER NOT NULL DEFAULT 0,\n" +
            "\t\"DefectPosIDBack\"\tINTEGER NOT NULL DEFAULT 0,\n" +
            "\t\"StyleSubCatID\"\tINTEGER NOT NULL\n" +
            ");";

    public static String createQCGarmentDataTable = "CREATE TABLE IF NOT EXISTS \"QCDataTable\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"Unit\"\tTEXT NOT NULL,\n" +
            "\t\"Line\"\tTEXT NOT NULL,\n" +
            "\t\"Time\"\tTEXT NOT NULL,\n" +
            "\t\"Date\"\tTEXT NOT NULL,\n" +
            "\t\"BatchQty\"\tTEXT NOT NULL,\n" +
            "\t\"BuyerName\"\tTEXT NOT NULL,\n" +
            "\t\"StyleCategory\"\tTEXT NOT NULL,\n" +
            "\t\"StyleSubCategory\"\tTEXT NOT NULL,\n" +
            "\t\"GarmentsNo\"\tTEXT NOT NULL,\n" +
            "\t\"GarmentsPos\"\tTEXT NOT NULL,\n" +
            "\t\"DefectName\"\tTEXT NOT NULL,\n" +
            "\t\"DefectCount\"\tTEXT NOT NULL,\n" +
            "\t\"Color\"\tTEXT NOT NULL,\n" +
            "\t\"DefectPos\"\tTEXT NOT NULL,\n" +
            "\t\"Size\"\tTEXT NOT NULL\n" +
            ");";

    public static String createStyleInLine = "CREATE TABLE IF NOT EXISTS \"StyleInBuyers\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"StyleSubCatID\"\tINTEGER NOT NULL,\n" +
            "\t\"BuyerID\"\tINTEGER NOT NULL,\n" +
            "\t\"LineID\"\tINTEGER NOT NULL,\n" +
            "\t\"StyleCateID\"\tINTEGER NOT NULL\n" +
            ");";


    public static String createPOInStyle = "CREATE TABLE IF NOT EXISTS \"POsInStyle\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"StyleSubCatID\"\tINTEGER NOT NULL,\n" +
            "\t\"PO_ID\"\tINTEGER NOT NULL,\n" +
            "\t\"Line_ID\"\tINTEGER NOT NULL\n" +
            ");";


    public static String createColorsInPO = "CREATE TABLE IF NOT EXISTS \"ColorsInPO\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t\"PO_ID\"\tINTEGER NOT NULL,\n" +
            "\t\"Color_ID\"\tINTEGER NOT NULL,\n" +
            "\t\"Line_ID\"\tINTEGER NOT NULL\n" +
            ");";


}
