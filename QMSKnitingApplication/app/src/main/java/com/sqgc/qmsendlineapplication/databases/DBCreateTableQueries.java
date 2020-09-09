/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/19/20 3:56 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/19/20 1:50 PM
 *
 */

package com.sqgc.qmsendlineapplication.databases;

public class DBCreateTableQueries {
    //create tables
    public static String createSilhouetteGridsTable = "CREATE TABLE IF NOT EXISTS \"SilhouetteGrids\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL UNIQUE,\n" +
            "\t\"GridNo\"\tINTEGER NOT NULL,\n" +
            "\t\"DefectPositionId\"\tINTEGER NOT NULL,\n" +
            "\t\"FrontOrBack\"\tINTEGER NOT NULL,\n" +
            "\t\"DefectPositionName\"\tNUMERIC NOT NULL,\n" +
            "\t\"StyleId\"\tINTEGER NOT NULL,\n" +
            "\t\"StyleName\"\tTEXT NOT NULL,\n" +
            "\t\"SilhouetteId\"\tINTEGER NOT NULL,\n" +
            "\t\"SilhouetteName\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id\")\n" +
            ");";

    public static String createImageListTable = "CREATE TABLE IF NOT EXISTS \"StyleImage\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL,\n" +
            "\t\"StyleID\"\tINTEGER NOT NULL,\n" +
            "\t\"FrontImgPath\"\tTEXT NOT NULL,\n" +
            "\t\"BackImagePath\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"ID\" AUTOINCREMENT)" +
            ");";

    public static String createDefectListTable = "CREATE TABLE IF NOT EXISTS \"DefectListbyPositions\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL,\n" +
            "\t\"GridNo\"\tINTEGER NOT NULL,\n" +
            "\t\"DefectPositionId\"\tINTEGER NOT NULL,\n" +
            "\t\"FrontorBack\"\tINTEGER NOT NULL,\n" +
            "\t\"DefectPositionName\"\tTEXT NOT NULL,\n" +
            "\t\"DefectId\"\tINTEGER NOT NULL,\n" +
            "\t\"DefectName\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ");";

    public static String createQCGarmentsDefectInfoTable = "CREATE TABLE IF NOT EXISTS \"QCGarmentsDefectInfo\" (\n" +
            "\t\"ID\"\tINTEGER NOT NULL UNIQUE,\n" +
            "\t\"Date\"\tTEXT NOT NULL,\n" +
            "\t\"Time\"\tTEXT NOT NULL,\n" +
            "\t\"DefectId\"\tTEXT NOT NULL,\n" +
            "\t\"DefectName\"\tTEXT NOT NULL,\n" +
            "\t\"DefectPositionId\"\tTEXT NOT NULL,\n" +
            "\t\"DefectPositionName\"\tTEXT NOT NULL,\n" +
            "\t\"GarmentsNo\"\tTEXT NOT NULL,\n" +
            "\t\"DefectQuantity\"\tTEXT NOT NULL,\n" +
            "\t\"ProductionUnitId\"\tTEXT NOT NULL,\n" +
            "\t\"QmsMasterKey\"\tTEXT NOT NULL,\n" +
            "\t\"LotNo\"\tINTEGER NOT NULL,\n" +
            "\t\"isSynced\"\tINTEGER NOT NULL DEFAULT 0,\n" +
            "\tPRIMARY KEY(\"ID\" AUTOINCREMENT)\n" +
            ");";


}
