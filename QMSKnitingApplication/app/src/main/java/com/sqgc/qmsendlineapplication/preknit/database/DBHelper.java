/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 7/21/20 12:48 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/20/20 6:38 PM
 *
 */

package com.sqgc.qmsendlineapplication.preknit.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sqgc.qmsendlineapplication.models.Buyer;
import com.sqgc.qmsendlineapplication.models.Color;
import com.sqgc.qmsendlineapplication.models.Defect;
import com.sqgc.qmsendlineapplication.models.Line;
import com.sqgc.qmsendlineapplication.models.Module;
import com.sqgc.qmsendlineapplication.models.PO;
import com.sqgc.qmsendlineapplication.models.ProductionUnit;
import com.sqgc.qmsendlineapplication.models.QCDataModel;
import com.sqgc.qmsendlineapplication.models.Size;
import com.sqgc.qmsendlineapplication.models.StyleCategory;
import com.sqgc.qmsendlineapplication.models.StyleMapDataModel;
import com.sqgc.qmsendlineapplication.models.StyleSubCategory;

import java.util.ArrayList;
import java.util.List;

import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createBuyerTable;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createColorTable;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createColorsInPO;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createDefect;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createDefectPosition;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createLineTable;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createModuleTable;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createPOInStyle;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createPOTable;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createProductionUnitTable;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createQCGarmentDataTable;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createQCGarmentDefectCounts;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createQCGarmentsDefect;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createSizeTable;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createStyleCategory;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createStyleInLine;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createStyleMapTable;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createStyleSubCatDefect;
import static com.sqgc.qmsendlineapplication.preknit.database.DBCreateTableQueries.createStyleSubCategory;
import static com.sqgc.qmsendlineapplication.preknit.database.DBInsertQueries.insertBuyers;
import static com.sqgc.qmsendlineapplication.preknit.database.DBInsertQueries.insertColor;
import static com.sqgc.qmsendlineapplication.preknit.database.DBInsertQueries.insertColorsInPO;
import static com.sqgc.qmsendlineapplication.preknit.database.DBInsertQueries.insertPOs;
import static com.sqgc.qmsendlineapplication.preknit.database.DBInsertQueries.insertPOsInStyle;
import static com.sqgc.qmsendlineapplication.preknit.database.DBInsertQueries.insertStyleInBuyers;
import static com.sqgc.qmsendlineapplication.preknit.database.DBInsertQueries.insertStyleSubCategory;
import static com.sqgc.qmsendlineapplication.preknit.database.DBQueries.insertDefect;
import static com.sqgc.qmsendlineapplication.preknit.database.DBQueries.insertDefectPosition;
import static com.sqgc.qmsendlineapplication.preknit.database.DBQueries.insertLines;
import static com.sqgc.qmsendlineapplication.preknit.database.DBQueries.insertModules;
import static com.sqgc.qmsendlineapplication.preknit.database.DBQueries.insertProductionUnit;
import static com.sqgc.qmsendlineapplication.preknit.database.DBQueries.insertSize;
import static com.sqgc.qmsendlineapplication.preknit.database.DBQueries.insertStyleCategory;
import static com.sqgc.qmsendlineapplication.preknit.database.DBQueries.insertStyleMapData;
import static com.sqgc.qmsendlineapplication.preknit.database.DBSubDefectQuery.insertStyleSubCatDefect;

public class DBHelper extends SQLiteOpenHelper {
    //tables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "qms_end_knit.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table
        db.execSQL(createDefect);
        db.execSQL(createStyleCategory);
        db.execSQL(createStyleSubCategory);

        db.execSQL(createDefectPosition);
        db.execSQL(createStyleSubCatDefect);

        db.execSQL(createColorTable);
        db.execSQL(createSizeTable);

        db.execSQL(createQCGarmentsDefect);
        db.execSQL(createQCGarmentDefectCounts);

        db.execSQL(createProductionUnitTable);
        db.execSQL(createLineTable);
        db.execSQL(createModuleTable);
        db.execSQL(createBuyerTable);

        db.execSQL(createPOTable);
        db.execSQL(createStyleMapTable);
        db.execSQL(createQCGarmentDataTable);

        db.execSQL(createStyleInLine);

        db.execSQL(createPOInStyle);
        db.execSQL(createColorsInPO);


        //insert data
        //insert category
        db.execSQL(insertStyleCategory);
        //insert subCategory
        db.execSQL(insertStyleSubCategory);

        db.execSQL(insertDefect);
        db.execSQL(insertDefectPosition);
        db.execSQL(insertStyleSubCatDefect);
        db.execSQL(insertColor);
        db.execSQL(insertSize);

        db.execSQL(insertProductionUnit);
        db.execSQL(insertLines);
        db.execSQL(insertModules);


        db.execSQL(insertBuyers);
        db.execSQL(insertPOs);

        db.execSQL(insertStyleInBuyers);

        db.execSQL(insertColorsInPO);
        db.execSQL(insertPOsInStyle);


        db.execSQL(insertStyleMapData);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STYLECATEGORY");
        db.execSQL("DROP TABLE IF EXISTS STYLESUBCATEGORY");
        db.execSQL("DROP TABLE IF EXISTS STYLE_SUBCAT_DEFECTS");
        db.execSQL("DROP TABLE IF EXISTS DEFECTPOSITION");
        db.execSQL("DROP TABLE IF EXISTS DEFECT");
        db.execSQL("DROP TABLE IF EXISTS QCGarmentsDefect");
        db.execSQL("DROP TABLE IF EXISTS QCGarmentDefectCounts");

        db.execSQL("DROP TABLE IF EXISTS ProductionUnit");
        db.execSQL("DROP TABLE IF EXISTS LINE");
        db.execSQL("DROP TABLE IF EXISTS MODULE");

        db.execSQL("DROP TABLE IF EXISTS STYLE_MAP");
        db.execSQL("DROP TABLE IF EXISTS QCDataTable");
        db.execSQL("DROP TABLE IF EXISTS StyleInBuyers");

        db.execSQL("DROP TABLE IF EXISTS POsInStyle");
        db.execSQL("DROP TABLE IF EXISTS ColorsInPO");

        onCreate(db);
    }


    public List<StyleCategory> getStyleCategories() {
        List<StyleCategory> styleCategories = new ArrayList<>();

        String sql = DBQueries.allStyleCategory;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);

                styleCategories.add(new StyleCategory(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return styleCategories;
    }

    public List<StyleSubCategory> getStyleSubCategories(String stycatName, Line line, String buyerName) {
        List<StyleSubCategory> styleCategories = new ArrayList<>();

//        String sql = DBQueries.allStyleSubCategoryWhereCat + stycatName + "';";

        String sql = DBQueries.allStyleSubCategoryWhereLineAndCatID + "StyleCategory.name = '" + stycatName + "' AND BUYER.name = '" + buyerName + "' AND Line.name = '" + line.getName() + "' ;";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                //int catID = Integer.parseInt(cursor.getString(2));

                styleCategories.add(new StyleSubCategory(id, name, 0));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return styleCategories;
    }

    public int getStyleSubCatIDbyNameAndCatID(String subCatName, String catName) {
        String sql = "SELECT STYLESUBCATEGORY.ID FROM STYLECATEGORY JOIN STYLESUBCATEGORY ON STYLECATEGORY.ID = STYLESUBCATEGORY.STYLECATEGORY \n" +
                " WHERE STYLESUBCATEGORY.NAME =" + " '" + subCatName + "' AND STYLECATEGORY.name = '" + catName + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        int subcatID = 0;

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                subcatID = Integer.parseInt(cursor.getString(0));


            } while (cursor.moveToNext());
        }
        cursor.close();
        return subcatID;
    }


    public List<Color> getColors(PO po, String lineName) {
        List<Color> colors = new ArrayList<>();
        String sql = DBQueries.allColorWhere + po.getName() + "\" " + "AND Line.name = '" + lineName + "';";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                //int catID = Integer.parseInt(cursor.getString(2));

                colors.add(new Color(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return colors;
    }

    public List<Size> getSizes() {
        List<Size> sizes = new ArrayList<>();
        String sql = DBQueries.allSizes;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                //int catID = Integer.parseInt(cursor.getString(2));

                sizes.add(new Size(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return sizes;
    }

    public List<Defect> getAllDefectByPosID(int posID) {
        List<Defect> defectList = new ArrayList<>();
        String sql = DBQueries.allDefectUsingPosition + " " + posID + " ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {


                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                defectList.add(new Defect(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return defectList;

    }


    public String getPosNameByID(int posID) {
        String name = null;
        String sql = DBQueries.defectPosByIDsql + " " + posID + " ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                name = cursor.getString(0);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return name;
    }

    public int getDefectIDByName(String defectName) {
        int id = 0;
        String sql = DBQueries.selectDefectIDByName + " \"" + defectName + "\" ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                id = Integer.parseInt(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return id;
    }


    public List<ProductionUnit> getAllProductionUnit() {
        List<ProductionUnit> productionUnits = new ArrayList<>();
        String sql = DBQueries.allProductionUnit;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);

                productionUnits.add(new ProductionUnit(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return productionUnits;
    }

    public List<Line> getAllLines() {
        List<Line> lines = new ArrayList<>();
        String sql = DBQueries.allLines;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);

                lines.add(new Line(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return lines;
    }

    public List<Module> getAllModules() {
        List<Module> modules = new ArrayList<>();
        String sql = DBQueries.allModules;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);


                modules.add(new Module(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return modules;
    }

    public List<Buyer> getAllBuyer(Line line) {
        List<Buyer> buyers = new ArrayList<>();
//        String sql = DBQueries.allBuyers;
        String sql = DBQueries.allBuyersWhereFloorNo + line.getName() + "';";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                buyers.add(new Buyer(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return buyers;
    }

    public List<PO> getPOsByID(int mySubCatID, String lineName) {
        List<PO> poss = new ArrayList<>();
        String sql = DBQueries.possBySubCatID + mySubCatID + " AND Line.name = '" + lineName + "';";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);

                int subCatId = Integer.parseInt(cursor.getString(2));
                String subCatName = cursor.getString(3);
                int catId = Integer.parseInt(cursor.getString(4));


                poss.add(new PO(id, name, subCatId, subCatName, catId));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return poss;
    }

    public List<StyleMapDataModel> getAllStyleMapByID(int subCatID) {
        List<StyleMapDataModel> styleMapDataModelList = new ArrayList<>();
        String sql = DBQueries.styleMapDataByID + subCatID + "  ORDER BY \"Pixelno\" ASC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                int pixelNo = Integer.parseInt(cursor.getString(1));
                int defectPosIdFront = Integer.parseInt(cursor.getString(2));
                int defectPosIdBack = Integer.parseInt(cursor.getString(3));
                int styleSubCatId = Integer.parseInt(cursor.getString(4));

                styleMapDataModelList.add(new StyleMapDataModel(id, pixelNo, defectPosIdFront, defectPosIdBack, styleSubCatId));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return styleMapDataModelList;


    }

    public void insertGarmentsEntryIntoDB(List<QCDataModel> qcDataModelList) {

        for (QCDataModel qcDataModel : qcDataModelList) {

            long tempCount = qcDataModel.getGarmentNo() + 1;
            String sql = "INSERT INTO \"QCDataTable\" (\"Unit\",\"Line\",\"Time\",\"Date\",\"BatchQty\",\"BuyerName\",\"StyleCategory\",\"StyleSubCategory\",\"GarmentsNo\",\"GarmentsPos\",\"DefectName\",\"DefectCount\",\"Color\",\"DefectPos\",\"Size\",\"SMV\") " +
                    "VALUES ('" + qcDataModel.getUnit() + "'," +
                    "'" + qcDataModel.getLine() + "'," +
                    "'" + qcDataModel.getTime() + "'," +
                    "'" + qcDataModel.getDate() + "'," +
                    "'" + qcDataModel.getBatchQty() + "'," +
                    "'" + qcDataModel.getBuyerName() + "'," +
                    "'" + qcDataModel.getStyleCat() + "'," +
                    "\"" + qcDataModel.getStyleSubCat() + "\"," +
                    "'" + tempCount + "'," +
                    "'" + qcDataModel.getGarmentPos() + "'," +
                    "'" + qcDataModel.getDefect().getName() + "'," +
                    "'" + qcDataModel.getDefect().getDefectCount() + "'," +
                    "'" + qcDataModel.getColor() + "'," +
                    "'" + qcDataModel.getDefectPos() + "'," +
                    "'" + qcDataModel.getSize() + "'," +
                    "'" + qcDataModel.getSmv() + "'" +
                    ");";

            try {
                SQLiteDatabase db = this.getWritableDatabase();
                db.execSQL(sql);

                db.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("SQLITE_DB", "insertGarmentsEntry: " + e.getMessage());
            }
        }

    }

    public List<QCDataModel> getAllQCDataModels(String timeStamp, String mydate) {
        List<QCDataModel> qcDataModelArrayList = new ArrayList<>();
        String sql = DBQueries.selectAllDefectListData + mydate + "';";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //"ID","Unit","Time","Date","BatchQty","BuyerName","StyleCategory","StyleSubCategory","GarmentsNo","GarmentsPos","DefectName","DefectCount","Color","DefectPos","Size"
                //int id = Integer.parseInt(cursor.getString(0));
                String unit = cursor.getString(0);
                String line = cursor.getString(1);
                String time = cursor.getString(2);
                String date = cursor.getString(3);
                String batchQty = cursor.getString(4);
                String buyerName = cursor.getString(5);
                String styleCat = cursor.getString(6);
                String styleSubCat = cursor.getString(7);
                int garmentNo = Integer.parseInt(cursor.getString(8));
                String garmentPos = cursor.getString(9);
                String defectName = cursor.getString(10);
                int defectCount = Integer.parseInt(cursor.getString(11));
                String color = cursor.getString(12);
                String defectPos = cursor.getString(13);
                String size = cursor.getString(14);
                int lotNo = Integer.parseInt(cursor.getString(15));
                String po = cursor.getString(16);
                String smv = cursor.getString(17);
                String operatorID = cursor.getString(18);
                String machineID = cursor.getString(19);
                String userID = cursor.getString(20);

                Defect defect = new Defect(defectName, defectCount);

                QCDataModel qcDataModel = new QCDataModel(timeStamp, lotNo,
                        unit, line, time, date, batchQty, buyerName,
                        styleCat, styleSubCat, garmentNo, garmentPos,
                        defect, color, defectPos, size, po, smv
                );

                qcDataModel.setOperatorID(operatorID);
                qcDataModel.setMachineID(machineID);
                qcDataModel.setUserID(userID);

                qcDataModelArrayList.add(qcDataModel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return qcDataModelArrayList;


    }


    //get last row entry ID
    public int getLastAddedRowId() {
        String queryLastRowInserted = "select last_insert_rowid()";
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(queryLastRowInserted, null);
        int _idLastInsertedRow = 0;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    _idLastInsertedRow = cursor.getInt(0);
                }
            } finally {
                cursor.close();
            }
        }

        return _idLastInsertedRow;

    }

    public void insertGarmentsDefectEntry(QCDataModel qcDataModel) {


        long tempCount = qcDataModel.getGarmentNo();

        boolean isFound = isFoundDefectEntry(qcDataModel);
        if (!isFound) {

            String sql = "INSERT INTO \"QCGarmentsDefect\"(\"Unit\",\"Line\",\"Date\",\"Time\",\"BuyerName\",\"BatchQty\",\"StyleCategory\",\"StyleSubCategory\"," +
                    "\"GarmentsNo\",\"GarmentPos\",\"Color\",\"DefectPos\",\"Size\",\"LotNo\",\"PO\",\"SMV\",\"OperatorID\",\"MachineID\",\"UserID\") \n" +
                    "VALUES (" +
                    "'" + qcDataModel.getUnit() + "'," +
                    "'" + qcDataModel.getLine() + "'," +
                    "'" + qcDataModel.getDate() + "'," +
                    "'" + qcDataModel.getTime() + "'," +
                    "\"" + qcDataModel.getBuyerName() + "\"," +
                    "'" + qcDataModel.getBatchQty() + "'," +
                    "'" + qcDataModel.getStyleCat() + "'," +
                    "\"" + qcDataModel.getStyleSubCat() + "\"," +
                    "'" + tempCount + "'," +
                    "'" + qcDataModel.getGarmentPos() + "'," +
                    "'" + qcDataModel.getColor() + "'," +
                    "'" + qcDataModel.getDefectPos() + "'," +
                    "'" + qcDataModel.getSize() + "'," +
                    "'" + qcDataModel.getLotNo() + "'," +
                    "'" + qcDataModel.getPo() + "'," +
                    "'" + qcDataModel.getSmv() + "'," +
                    "'" + qcDataModel.getOperatorID() + "'," +
                    "'" + qcDataModel.getMachineID() + "'," +
                    "'" + qcDataModel.getUserID() + "'" +
                    ");";

            try {
                SQLiteDatabase db = this.getWritableDatabase();
                db.execSQL(sql);

                Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + getLastAddedRowId());


            } catch (Exception e) {
                e.printStackTrace();
                Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + e.getMessage());
            }
        }

        int qcGarmentDefectID = getQCGarmentDefectID(qcDataModel);
        insertDefectCount(qcGarmentDefectID, qcDataModel);


    }

    private boolean isFoundDefectEntry(QCDataModel qcDataModel) {
        long tempCount = qcDataModel.getGarmentNo();
        String sql = "SELECT * FROM\n" +
                "\"QCGarmentsDefect\" WHERE \n" +
                "\"Unit\" = '" + qcDataModel.getUnit() + "' AND \n" +
                "\"Line\" = '" + qcDataModel.getLine() + "' AND \n" +
                "\"BuyerName\" = '" + qcDataModel.getBuyerName() + "' AND \n" +
                "\"BatchQty\" = '" + qcDataModel.getBatchQty() + "' AND \n" +
                "\"StyleCategory\" = \"" + qcDataModel.getStyleCat() + "\" AND \"" +
                "StyleSubCategory\" =\"" + qcDataModel.getStyleSubCat() + "\" AND \n" +
                "\"GarmentsNo\" = '" + tempCount + "' AND \n" +
                " \"GarmentPos\" = '" + qcDataModel.getGarmentPos() + "' AND \n" +
                " \"Color\"  = '" + qcDataModel.getColor() + "' AND  \n" +
                " \"DefectPos\" = '" + qcDataModel.getDefectPos() + "' AND \n" +
                " \"PO\" = '" + qcDataModel.getPo() + "' AND \n" +
                " \"LotNo\" = '" + qcDataModel.getLotNo() + "' AND \n" +
                " \"Size\"= '" + qcDataModel.getSize() + "';";
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(sql, null);
            return cursor.getCount() != 0;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + e.getMessage());
        }


        return false;
    }

    private int getQCGarmentDefectID(QCDataModel qcDataModel) {
        int entryID = 0;

        long tempCount = qcDataModel.getGarmentNo();
        String sql = "SELECT * FROM\n" +
                "\"QCGarmentsDefect\" WHERE \n" +
                "\"Unit\" = '" + qcDataModel.getUnit() + "' AND \n" +
                "\"Line\" = '" + qcDataModel.getLine() + "' AND \n" +
                "\"BuyerName\" = '" + qcDataModel.getBuyerName() + "' AND \n" +
                "\"BatchQty\" = '" + qcDataModel.getBatchQty() + "' AND \n" +
                "\"StyleCategory\" = '" + qcDataModel.getStyleCat() + "' AND \"" +
                "StyleSubCategory\" =\"" + qcDataModel.getStyleSubCat() + "\" AND \n" +
                "\"GarmentsNo\" = '" + tempCount + "' AND \n" +
                " \"GarmentPos\" = '" + qcDataModel.getGarmentPos() + "' AND \n" +
                " \"Color\"  = '" + qcDataModel.getColor() + "' AND  \n" +
                " \"DefectPos\" = '" + qcDataModel.getDefectPos() + "' AND \n" +
                " \"PO\" = '" + qcDataModel.getPo() + "' AND \n" +
                " \"LotNo\" = '" + qcDataModel.getLotNo() + "' AND \n" +
                " \"Size\"= '" + qcDataModel.getSize() + "';";
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {
                    entryID = Integer.parseInt(cursor.getString(0));
                }
            }
            cursor.close();

            return entryID;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "getDefectEntryID: " + e.getMessage());
        }

        return entryID;


    }

    private void insertDefectCount(int qcGarmentDefectID, QCDataModel qcDataModel) {

        if (!isFoundDefectCount(qcGarmentDefectID, qcDataModel)) {
            String sql = "INSERT INTO \"QCGarmentDefectCounts\"(\"QCGarmentDefectID\",\"DefectID\",\"DefectCount\") \n" +
                    "VALUES (" + qcGarmentDefectID + "," + qcDataModel.getDefect().getId() + "," + qcDataModel.getDefect().getDefectCount() + ");";

            try {
                SQLiteDatabase db = this.getWritableDatabase();
                db.execSQL(sql);
                Log.d("SQLITE_DB", "insertDefectCount: " + getLastAddedRowId());

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("SQLITE_DB", "insertDefectCount: " + e.getMessage());
            }
        } else {

            int id = getQCGarmentDefectCountID(qcGarmentDefectID, qcDataModel);

            String updateSql = "UPDATE \"QCGarmentDefectCounts\" SET \"DefectCount\"=" + qcDataModel.getDefect().getDefectCount() + " WHERE \"ID\"= " + id + ";";
            try {
                SQLiteDatabase db = this.getWritableDatabase();
                db.execSQL(updateSql);
                Log.d("SQLITE_DB", "updateDefectCount: " + getLastAddedRowId());

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("SQLITE_DB", "updateDefectCount: " + e.getMessage());
            }


        }

    }


    private boolean isFoundDefectCount(int qcGarmentDefectID, QCDataModel qcDataModel) {

        String sql = "SELECT * FROM QCGarmentDefectCounts WHERE " +
                "\"QCGarmentDefectID\" = " + qcGarmentDefectID + " AND \"DefectID\" = " + qcDataModel.getDefect().getId() + ";";

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(sql, null);
            return cursor.getCount() != 0;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + e.getMessage());
        }


        return false;
    }

    private int getQCGarmentDefectCountID(int qcGarmentDefectID, QCDataModel qcDataModel) {

        int entryID = 0;

        String sql = "SELECT * FROM QCGarmentDefectCounts WHERE " +
                "\"QCGarmentDefectID\" = " + qcGarmentDefectID + " AND \"DefectID\" = " + qcDataModel.getDefect().getId() + ";";

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {
                    entryID = Integer.parseInt(cursor.getString(0));
                }
            }
            cursor.close();

            return entryID;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "getDefectEntryID: " + e.getMessage());
        }

        return entryID;


    }


    private boolean isFoundDefectCountByQCGarmentDefectID(int qcGarmentDefectID, QCDataModel qcDataModel) {

        String sql = "SELECT * FROM QCGarmentDefectCounts WHERE " +
                "\"QCGarmentDefectID\" = " + qcGarmentDefectID + ";";

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(sql, null);
            return cursor.getCount() != 0;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + e.getMessage());
        }


        return false;


    }

    public void deleteGarmentDefectEntry(QCDataModel qcDataModel) {
        int qcGarmentDefectID = getQCGarmentDefectID(qcDataModel);
        int defectCountID = getQCGarmentDefectCountID(qcGarmentDefectID, qcDataModel);

        String sql = "DELETE FROM \"QCGarmentDefectCounts\" WHERE ID = " + defectCountID + ";";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
            Log.d("SQLITE_DB", "deleteDefectCount: " + defectCountID);

            boolean isFoundDefectCount = isFoundDefectCountByQCGarmentDefectID(qcGarmentDefectID, qcDataModel);
            if (!isFoundDefectCount) {

                String sql2 = "DELETE FROM \"QCGarmentsDefect\" WHERE ID = " + qcGarmentDefectID + " ;";
                db.execSQL(sql2);
                Log.d("SQLITE_DB", "deleteDefect: " + qcGarmentDefectID);
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "deleteDefectCount: " + e.getMessage());
        }

    }


    public List<QCDataModel> getAllQCDataModelsByGarmentNo(String timeStamp, int mGarmentNo, int myLotNo, String myDate) {
        List<QCDataModel> qcDataModelArrayList = new ArrayList<>();
        String sql = DBQueries.selectAllDefectListDataByGarmentsNo + mGarmentNo + " AND LotNo = " + myLotNo + " AND Date = '" + myDate + "';";
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //"ID","Unit","Time","Date","BatchQty","BuyerName","StyleCategory","StyleSubCategory","GarmentsNo","GarmentsPos","DefectName","DefectCount","Color","DefectPos","Size"
                //int id = Integer.parseInt(cursor.getString(0));
                String unit = cursor.getString(0);
                String line = cursor.getString(1);
                String time = cursor.getString(2);
                String date = cursor.getString(3);
                String batchQty = cursor.getString(4);
                String buyerName = cursor.getString(5);
                String styleCat = cursor.getString(6);
                String styleSubCat = cursor.getString(7);
                int garmentNo = Integer.parseInt(cursor.getString(8));
                String garmentPos = cursor.getString(9);
                String defectName = cursor.getString(10);
                int defectCount = Integer.parseInt(cursor.getString(11));
                String color = cursor.getString(12);
                String defectPos = cursor.getString(13);
                String size = cursor.getString(14);
                int lotNo = cursor.getInt(15);
                String po = cursor.getString(16);
                String smv = cursor.getString(17);
                String operatorID = cursor.getString(18);
                String machineID = cursor.getString(19);
                String userID = cursor.getString(20);

                Defect defect = new Defect(defectName, defectCount);
                defect.setId(getDefectIDByName(defectName));

                QCDataModel qcDataModel = new QCDataModel(timeStamp, lotNo,
                        unit, line, time, date, batchQty, buyerName,
                        styleCat, styleSubCat, garmentNo, garmentPos,
                        defect, color, defectPos, size, po, smv
                );

                qcDataModel.setOperatorID(operatorID);
                qcDataModel.setMachineID(machineID);
                qcDataModel.setUserID(userID);

                qcDataModelArrayList.add(qcDataModel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return qcDataModelArrayList;


    }

    //is found any garmentID
    public boolean isFoundGarmentNo(int lotNo, int garmentsNo, String date) {

        String sql = "SELECT * FROM QCGarmentsDefect WHERE QCGarmentsDefect.Date = '" + date + "' AND LotNo = " + lotNo + " AND GarmentsNo = " + garmentsNo + ";";

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(sql, null);
            return cursor.getCount() != 0;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + e.getMessage());
        }


        return false;
    }

    public List<Integer> getLastEntryID(int lotNo, int garmentsNo, String date) {
        String sql = "SELECT * FROM QCGarmentsDefect WHERE QCGarmentsDefect.Date = '" + date + "' AND LotNo = " + lotNo + " AND GarmentsNo = " + garmentsNo + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        List<Integer> ids = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                ids.add(id);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return ids;
    }

    public void deleteDefectsFromQCDefectCount(int qcGarmentDefectID) {
        String sql = "DELETE FROM QCGarmentDefectCounts \n" +
                "WHERE QCGarmentDefectCounts.QCGarmentDefectID = " + qcGarmentDefectID + ";";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + e.getMessage());
        }

    }

    public void deleteDefectsFromQCDefects(int qcGarmentDefectID) {
        String sql = "DELETE FROM QCGarmentsDefect \n" +
                "WHERE QCGarmentsDefect.id = " + qcGarmentDefectID + ";";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + e.getMessage());
        }

    }

    public void insertNoDefect(QCDataModel qcDataModel) {

        String sql = "INSERT INTO \"QCGarmentsDefect\"(\"Unit\",\"Line\",\"Date\",\"Time\",\"BuyerName\",\"BatchQty\",\"StyleCategory\",\"StyleSubCategory\"," +
                "\"GarmentsNo\",\"GarmentPos\",\"Color\",\"DefectPos\",\"Size\",\"LotNo\",\"PO\",\"SMV\",\"OperatorID\",\"MachineID\",\"UserID\") \n" +
                "VALUES (" +
                "'" + qcDataModel.getUnit() + "'," +
                "'" + qcDataModel.getLine() + "'," +
                "'" + qcDataModel.getDate() + "'," +
                "'" + qcDataModel.getTime() + "'," +
                "\"" + qcDataModel.getBuyerName() + "\"," +
                "'" + qcDataModel.getBatchQty() + "'," +
                "'" + qcDataModel.getStyleCat() + "'," +
                "\"" + qcDataModel.getStyleSubCat() + "\"," +
                "'" + qcDataModel.getGarmentNo() + "'," +
                "'na'," +
                "'" + qcDataModel.getColor() + "'," +
                "'na'," +
                "'" + qcDataModel.getSize() + "'," +
                "'" + qcDataModel.getLotNo() + "'," +
                "'" + qcDataModel.getPo() + "'," +
                "'" + qcDataModel.getSmv() + "'," +
                "'" + qcDataModel.getOperatorID() + "'," +
                "'" + qcDataModel.getMachineID() + "'," +
                "'" + qcDataModel.getUserID() + "'" +
                ");";

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);

            Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + getLastAddedRowId());


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + e.getMessage());
        }


    }


    public int totalGarmentsEntryInADay(String date) {

        String sql = DBQueries.totalGarmentsEntryInADay + date + "';";
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(sql, null);
            return cursor.getCount();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "totalGarmentsEntryInADay: " + e.getMessage());
        }


        return 0;
    }


    public List<QCDataModel> getAllQCDataModelsForSendingToDB(String timeStamp, String mydate) {
        List<QCDataModel> qcDataModelArrayList = new ArrayList<>();
        String sql = DBQueries.selectAllDefectListDataForSending + mydate + "' AND QCGarmentsDefect.isSynced = 0 ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //"ID","Unit","Time","Date","BatchQty","BuyerName","StyleCategory","StyleSubCategory","GarmentsNo","GarmentsPos","DefectName","DefectCount","Color","DefectPos","Size"
                //int id = Integer.parseInt(cursor.getString(0));
                String unit = cursor.getString(0);
                String line = cursor.getString(1);
                String time = cursor.getString(2);
                String date = cursor.getString(3);
                String batchQty = cursor.getString(4);
                String buyerName = cursor.getString(5);
                String styleCat = cursor.getString(6);
                String styleSubCat = cursor.getString(7);
                int garmentNo = Integer.parseInt(cursor.getString(8));
                String garmentPos = cursor.getString(9);
                String defectName = cursor.getString(10);
                int defectCount = Integer.parseInt(cursor.getString(11));
                String color = cursor.getString(12);
                String defectPos = cursor.getString(13);
                String size = cursor.getString(14);
                int lotNo = Integer.parseInt(cursor.getString(15));
                String po = cursor.getString(16);
                String smv = cursor.getString(17);
                int id = Integer.parseInt(cursor.getString(18));
                String operatorID = cursor.getString(19);
                String machineID = cursor.getString(20);
                String userID = cursor.getString(21);

                Defect defect = new Defect(defectName, defectCount);

                QCDataModel qcDataModel = new QCDataModel(timeStamp, lotNo,
                        unit, line, time, date, batchQty, buyerName,
                        styleCat, styleSubCat, garmentNo, garmentPos,
                        defect, color, defectPos, size, po, smv
                );
                qcDataModel.setGarmentsID();

                qcDataModel.setId(id);
                qcDataModel.setMachineID(machineID);
                qcDataModel.setOperatorID(operatorID);
                qcDataModel.setUserID(userID);

                qcDataModelArrayList.add(qcDataModel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return qcDataModelArrayList;


    }

    public void updateSyncData(int id) {

        String updateSql = "UPDATE QCGarmentsDefect SET isSynced = 1 WHERE QCGarmentsDefect.ID = " + id + ";";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(updateSql);
            Log.d("SQLITE_DB", "update sync data: ");

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "update sync data: ");
        }
    }


    public List<QCDataModel> getAllQCDataModelsByGarmentNoForDeletingFromServer(String timeStamp, int mGarmentNo, int myLotNo, String myDate) {
        List<QCDataModel> qcDataModelArrayList = new ArrayList<>();
        String sql = DBQueries.selectAllDefectListDataByGarmentsNoForDeleting + mGarmentNo + " AND LotNo = " + myLotNo + " AND Date = '" + myDate + "';";
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //"ID","Unit","Time","Date","BatchQty","BuyerName","StyleCategory","StyleSubCategory","GarmentsNo","GarmentsPos","DefectName","DefectCount","Color","DefectPos","Size"
                //int id = Integer.parseInt(cursor.getString(0));
                String unit = cursor.getString(0);
                String line = cursor.getString(1);
                String time = cursor.getString(2);
                String date = cursor.getString(3);
                String batchQty = cursor.getString(4);
                String buyerName = cursor.getString(5);
                String styleCat = cursor.getString(6);
                String styleSubCat = cursor.getString(7);
                int garmentNo = Integer.parseInt(cursor.getString(8));
                String garmentPos = cursor.getString(9);
                String defectName = cursor.getString(10);
                int defectCount = Integer.parseInt(cursor.getString(11));
                String color = cursor.getString(12);
                String defectPos = cursor.getString(13);
                String size = cursor.getString(14);
                int lotNo = Integer.parseInt(cursor.getString(15));
                String po = cursor.getString(16);
                String smv = cursor.getString(17);
                int id = Integer.parseInt(cursor.getString(18));
                String operatorID = cursor.getString(19);
                String machineID = cursor.getString(20);
                String userID = cursor.getString(21);

                Defect defect = new Defect(defectName, defectCount);

                QCDataModel qcDataModel = new QCDataModel(timeStamp, lotNo,
                        unit, line, time, date, batchQty, buyerName,
                        styleCat, styleSubCat, garmentNo, garmentPos,
                        defect, color, defectPos, size, po, smv
                );
                qcDataModel.setGarmentsID();

                qcDataModel.setId(id);
                qcDataModel.setMachineID(machineID);
                qcDataModel.setOperatorID(operatorID);
                qcDataModel.setUserID(userID);

                qcDataModelArrayList.add(qcDataModel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return qcDataModelArrayList;


    }


}
