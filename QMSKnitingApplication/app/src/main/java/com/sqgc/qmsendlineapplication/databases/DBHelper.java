/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/24/20 1:34 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/24/20 12:18 PM
 *
 */

package com.sqgc.qmsendlineapplication.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sqgc.qmsendlineapplication.models.Defect;
import com.sqgc.qmsendlineapplication.models.api_models.DefectList;
import com.sqgc.qmsendlineapplication.models.api_models.DefectPosition;
import com.sqgc.qmsendlineapplication.models.api_models.SilhouetteGridList;
import com.sqgc.qmsendlineapplication.models.db_models.QMSGarmentDefectInfoDataModel;
import com.sqgc.qmsendlineapplication.models.db_models.QmsDetailsInformation;
import com.sqgc.qmsendlineapplication.models.db_models.StyleImageDataModel;
import com.sqgc.qmsendlineapplication.models.sync_model.SyncKeys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sqgc.qmsendlineapplication.databases.DBCreateTableQueries.createDefectListTable;
import static com.sqgc.qmsendlineapplication.databases.DBCreateTableQueries.createImageListTable;
import static com.sqgc.qmsendlineapplication.databases.DBCreateTableQueries.createQCGarmentsDefectInfoTable;
import static com.sqgc.qmsendlineapplication.databases.DBCreateTableQueries.createSilhouetteGridsTable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "qms_el_full.db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createSilhouetteGridsTable);
        sqLiteDatabase.execSQL(createImageListTable);
        sqLiteDatabase.execSQL(createDefectListTable);
        sqLiteDatabase.execSQL(createQCGarmentsDefectInfoTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SilhouetteGrids;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS StyleImage;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DefectListbyPositions;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS QCGarmentsDefectInfo;");

    }

    public void insertScheoutteeData(SilhouetteGridList silhouetteGrid, int styleID, String styleName, String silhouetteName, int silhouetteID) {

        String sql = "INSERT INTO \"SilhouetteGrids\" (\"GridNo\",\"DefectPositionId\",\"FrontOrBack\",\"DefectPositionName\",\"StyleId\",\"StyleName\",\"SilhouetteId\",\"SilhouetteName\") VALUES " +
                "(" +
                silhouetteGrid.getGridNo() +
                "," + silhouetteGrid.getDefectPositionId() +
                "," + silhouetteGrid.getFrontorBack() +
                ",'" + silhouetteGrid.getDefectPositionName() + "'" +
                "," + styleID +
                ",'" + styleName + "'" +
                "," + silhouetteID +
                ",'" + silhouetteName + "'" +
                ");";

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
            Log.d("SQLITE_DB", "insertScheoutteeData: ");
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertScheoutteeData: " + e.getMessage());
        }


    }

    public void deleteScheoutteeData(int styleID) {
        String sql = "DELETE FROM SilhouetteGrids WHERE StyleID = " + styleID + ";";

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
            Log.d("SQLITE_DB", "deleteScheoutteeData: ");
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "deleteScheoutteeData: " + e.getMessage());
        }
    }

    public void insertImageData(int styleID, String frontImgPath, String backImagePath) {

        deleteImageData(styleID);
        String sql = "INSERT INTO StyleImage (\"StyleID\",\"FrontImgPath\",\"BackImagePath\") VALUES " +
                "(" + styleID + "" +
                ",'" + frontImgPath + "'" +
                ",'" + backImagePath + "'" +
                ");";

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
            Log.d("SQLITE_DB", "insertImageData: ");
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertImageData: " + e.getMessage());
        }


    }

    public StyleImageDataModel getImageByStyleID(int styleID) {
        StyleImageDataModel styleImageDataModel = null;
        String sql = "SELECT * FROM StyleImage WHERE StyleId = " + styleID + ";";
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = Integer.parseInt(cursor.getString(0));
                    int myStyleID = Integer.parseInt(cursor.getString(1));
                    String frontImgPath = cursor.getString(2);
                    String backImgPath = cursor.getString(3);

                    styleImageDataModel = new StyleImageDataModel(id, myStyleID, frontImgPath, backImgPath);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "deleteImageData: " + e.getMessage());
        }

        return styleImageDataModel;
    }


    public void deleteImageData(int styleID) {
        String sql = "DELETE FROM StyleImage WHERE StyleID = " + styleID + ";";

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
            Log.d("SQLITE_DB", "deleteImageData: ");
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "deleteImageData: " + e.getMessage());
        }
    }

    public void insertDefectList(DefectPosition defectPosition, DefectList defect) {
        String sql = "INSERT INTO DefectListbyPositions " +
                "(GridNo,DefectPositionId,FrontorBack,DefectPositionName,DefectId,DefectName) VALUES " +
                "(" + defectPosition.getGridNo() + "" +
                "," + defectPosition.getDefectPositionId() +
                "," + defectPosition.getFrontorBack() + "" +
                ",'" + defectPosition.getDefectPositionName() + "'" +
                "," + defect.getDefectId() + "" +
                ",'" + defect.getDefectName() + "');\n";

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
            Log.d("SQLITE_DB", "insertDefectList: ");
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertDefectList: " + e.getMessage());

        }

    }

    public void deleteDefectList(int DefectPositionId) {
        String sql = "DELETE From DefectListbyPositions WHERE DefectPositionId = " + DefectPositionId + ";";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
            Log.d("SQLITE_DB", "insertDefectList: ");

            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertDefectList: " + e.getMessage());

        }

    }


    public List<Defect> getAllDefectByGrid(int gridNo, int styleID, int isBack) {
        List<Defect> defectList = new ArrayList<>();
        String sql = "SELECT DefectListByPositions.DefectId, DefectListByPositions.DefectName, SilhouetteGrids.DefectPositionId, SilhouetteGrids.DefectPositionName  FROM SilhouetteGrids  JOIN DefectListByPositions \n" +
                "ON SilhouetteGrids.DefectPositionID = DefectListByPositions.DefectPositionID\n" +
                "WHERE SilhouetteGrids.GridNo = " + gridNo + " and StyleId = " + styleID + " AND SilhouetteGrids.FrontOrBack = " + isBack + " ;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int defectPosID = Integer.parseInt(cursor.getString(2));
                String defectPosName = cursor.getString(3);
                defectList.add(new Defect(id, name, defectPosID, defectPosName));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return defectList;

    }


    //QCGarmentsInfo starts
    public void insertQCGarmentsInfo(QMSGarmentDefectInfoDataModel qmsGarmentDefectInfoDataModel) {

        if (!isFoundQCGarmentsInfo(qmsGarmentDefectInfoDataModel)) {

            String sql = "INSERT INTO \"QCGarmentsDefectInfo\" (\"Date\",\"Time\",\"DefectId\",\"DefectName\",\"DefectPositionId\",\"DefectPositionName\",\"GarmentsNo\",\"DefectQuantity\",\"ProductionUnitId\",\"QmsMasterKey\",\"LotNo\") " +
                    "VALUES " +
                    "(\"" + qmsGarmentDefectInfoDataModel.getEntryDate() + "\"" +
                    ",\"" + qmsGarmentDefectInfoDataModel.getEntryTime() + "\"" +
                    ",\"" + qmsGarmentDefectInfoDataModel.getDefectId() + "\"" +
                    ",\"" + qmsGarmentDefectInfoDataModel.getDefectName() + "\"" +
                    ",\"" + qmsGarmentDefectInfoDataModel.getDefectPositionId() + "\"" +
                    ",\"" + qmsGarmentDefectInfoDataModel.getDefectPositionName() + "\"" +
                    ",\"" + qmsGarmentDefectInfoDataModel.getGarmentsNo() + "\"" +
                    ",\"" + qmsGarmentDefectInfoDataModel.getDefectQuantity() + "\"" +
                    ",\"" + qmsGarmentDefectInfoDataModel.getProductionUnitID() + "\"" +
                    ",\"" + qmsGarmentDefectInfoDataModel.getQmsMasterKey() + "\"" +
                    ",\"" + qmsGarmentDefectInfoDataModel.getLotNo() + "\");";

            try {
                SQLiteDatabase db = this.getWritableDatabase();
                db.execSQL(sql);
                Log.d("SQLITE_DB", "insert Data: success");
                //db.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("SQLITE_DB", "insert Data: " + e.getMessage());
            }
        } else {
            updateQCGarmentsInfo(qmsGarmentDefectInfoDataModel);
        }


    }

    private void updateQCGarmentsInfo(QMSGarmentDefectInfoDataModel qmsGarmentDefectInfoDataModel) {
        String sql = "UPDATE QCGarmentsDefectInfo SET DefectQuantity = " + qmsGarmentDefectInfoDataModel.getDefectQuantity() +
                " WHERE \n" +
                "GarmentsNo='" + qmsGarmentDefectInfoDataModel.getGarmentsNo() + "' \n" +
                "AND QCGarmentsDefectInfo.DefectId = '" + qmsGarmentDefectInfoDataModel.getDefectId() + "' \n" +
                "AND QCGarmentsDefectInfo.DefectPositionId = \"" + qmsGarmentDefectInfoDataModel.getDefectPositionId() + "\" \n" +
                "AND QCGarmentsDefectInfo.QmsMasterKey = '" + qmsGarmentDefectInfoDataModel.getQmsMasterKey() + "' \n" +
                "AND QCGarmentsDefectInfo.LotNo = \"" + qmsGarmentDefectInfoDataModel.getLotNo() + "\" \n" +
                "AND QCGarmentsDefectInfo.Date = '" + qmsGarmentDefectInfoDataModel.getEntryDate() + "';";

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
            Log.d("SQLITE_DB", "update Data: success");

            // db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "update Data: " + e.getMessage());
        }

    }


    private boolean isFoundQCGarmentsInfo(QMSGarmentDefectInfoDataModel qmsGarmentDefectInfoDataModel) {
        String sql = "SELECT * FROM QCGarmentsDefectInfo WHERE \n" +
                "GarmentsNo='" + qmsGarmentDefectInfoDataModel.getGarmentsNo() + "' \n" +
                "AND QCGarmentsDefectInfo.DefectId = '" + qmsGarmentDefectInfoDataModel.getDefectId() + "' \n" +
                "AND QCGarmentsDefectInfo.DefectPositionId = \"" + qmsGarmentDefectInfoDataModel.getDefectPositionId() + "\" \n" +
                "AND QCGarmentsDefectInfo.QmsMasterKey = '" + qmsGarmentDefectInfoDataModel.getQmsMasterKey() + "' \n" +
                "AND QCGarmentsDefectInfo.LotNo = \"" + qmsGarmentDefectInfoDataModel.getLotNo() + "\" \n" +
                "AND QCGarmentsDefectInfo.Date = '" + qmsGarmentDefectInfoDataModel.getEntryDate() + "';";
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

    private int getQCGarmentsInfoID(QMSGarmentDefectInfoDataModel qmsGarmentDefectInfoDataModel) {
        String sql = "SELECT * FROM QCGarmentsDefectInfo WHERE \n" +
                "GarmentsNo='" + qmsGarmentDefectInfoDataModel.getGarmentsNo() + "' \n" +
                "AND QCGarmentsDefectInfo.DefectId = '" + qmsGarmentDefectInfoDataModel.getDefectId() + "' \n" +
                "AND QCGarmentsDefectInfo.DefectPositionId = \"" + qmsGarmentDefectInfoDataModel.getDefectPositionId() + "\" \n" +
                "AND QCGarmentsDefectInfo.QmsMasterKey = '" + qmsGarmentDefectInfoDataModel.getQmsMasterKey() + "' \n" +
                "AND QCGarmentsDefectInfo.LotNo = \"" + qmsGarmentDefectInfoDataModel.getLotNo() + "\" \n" +
                "AND QCGarmentsDefectInfo.Date = '" + qmsGarmentDefectInfoDataModel.getEntryDate() + "';";
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {
                    return Integer.parseInt(cursor.getString(0));
                }
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "getGarmentsDefectEntryID: " + e.getMessage());
        }

        return -1;
    }


    public void deleteQCGarmentsInfo(QMSGarmentDefectInfoDataModel qmsGarmentDefectInfoDataModel) {

        int id = getQCGarmentsInfoID(qmsGarmentDefectInfoDataModel);
        if (id != -1) {

            String sql = "DELETE FROM \"QCGarmentsDefectInfo\" WHERE QCGarmentsDefectInfo.ID = '" + id + "';";

            try {
                SQLiteDatabase db = this.getWritableDatabase();
                db.execSQL(sql);
                Log.d("SQLITE_DB", "delete Data: success");

                // db.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("SQLITE_DB", "delete Data: " + e.getMessage());
            }
        }


    }
    //QCGarmentsInfo ends

    //No defect entry starts
    //is found any garmentID
    public boolean isFoundGarmentNo(int lotNo, int garmentsNo, String date) {

        String sql = "SELECT * FROM QCGarmentsDefectInfo WHERE QCGarmentsDefectInfo.Date = '" + date + "' AND LotNo = " + lotNo + " AND GarmentsNo = " + garmentsNo + ";";

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(sql, null);
            return cursor.getCount() != 0;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "isFoundGarmentNo: " + e.getMessage());
        }


        return false;
    }

    //  total garments count in a day
    public int totalGarmentsEntryInADay(String date) {

        String sql = "SELECT DISTINCT LotNo, GarmentsNo\n" +
                "FROM QCGarmentsDefectInfo ";
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


    //No defect entry ends

    //all inserted defects starts

    public List<QmsDetailsInformation> getAllDefectDataForSending(String date) {

        List<QmsDetailsInformation> qmsDetailsInformations = new ArrayList<>();
        String sql = "SELECT DefectId, DefectPositionId, DefectQuantity, GarmentsNo, QCGarmentsDefectInfo.Date, QCGarmentsDefectInfo.Time, LotNo FROM QCGarmentsDefectInfo WHERE QCGarmentsDefectInfo.Date = '" + date + "' AND isSynced = 0";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                int defectID = Integer.parseInt(cursor.getString(0));
                int defectPositionID = Integer.parseInt(cursor.getString(1));
                int defectCount = Integer.parseInt(cursor.getString(2));
                int garmentNo = Integer.parseInt(cursor.getString(3));
                String entryDate = cursor.getString(4);
                String entryTime = cursor.getString(5);
                int lotNo = Integer.parseInt(cursor.getString(6));

                QmsDetailsInformation qmsDetailsInformation = new QmsDetailsInformation();
                qmsDetailsInformation.setDefectId(defectID);
                qmsDetailsInformation.setDefectPositionId(defectPositionID);
                qmsDetailsInformation.setDefectQuantity(defectCount);
                qmsDetailsInformation.setEntryDate(entryDate);
                qmsDetailsInformation.setEntryTime(entryTime);
                qmsDetailsInformation.setGarmentsNo(getGarmentsID(garmentNo, lotNo));

                qmsDetailsInformations.add(qmsDetailsInformation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return qmsDetailsInformations;
    }

    //all inserted defects ends
    public String getGarmentsID(int garmentNo, int lotNo) {
        long temp_garmentNo = garmentNo;

        return getDateForUniqueGarment() + String.format("%02d", lotNo) + String.format("%03d", temp_garmentNo);

    }

    private String getDateForUniqueGarment() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            String currentDate = dateFormat.format(new Date()); // Find todays date

            return currentDate;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public void updateSyncData(QmsDetailsInformation qmsDetailsInformation) {

        String updateSql = "UPDATE QCGarmentsDefectInfo SET isSynced = 1 WHERE QCGarmentsDefectInfo.Date = '" + qmsDetailsInformation.getEntryDate() + "' AND QCGarmentsDefectInfo.Time = '" + qmsDetailsInformation.getEntryTime() + "';";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(updateSql);
            Log.d("SQLITE_DB", "update sync data: ");

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "update sync data: failed ");
        }
    }

    public List<QmsDetailsInformation> getAllQCDataModelsByGarmentNoForDeletingFromServer(int myGarmentNo, int myLotNo, String date) {

        List<QmsDetailsInformation> qmsDetailsInformations = new ArrayList<>();
        String sql = "SELECT DefectId, DefectPositionId, DefectQuantity, GarmentsNo, QCGarmentsDefectInfo.Date, QCGarmentsDefectInfo.Time, LotNo FROM QCGarmentsDefectInfo \n" +
                "WHERE QCGarmentsDefectInfo.Date = '" + date + "' AND GarmentsNo = '" + myGarmentNo + "' AND LotNo = '" + myLotNo + "';";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                int defectID = Integer.parseInt(cursor.getString(0));
                int defectPositionID = Integer.parseInt(cursor.getString(1));
                int defectCount = Integer.parseInt(cursor.getString(2));
                int garmentNo = Integer.parseInt(cursor.getString(3));
                String entryDate = cursor.getString(4);
                String entryTime = cursor.getString(5);
                int lotNo = Integer.parseInt(cursor.getString(6));

                QmsDetailsInformation qmsDetailsInformation = new QmsDetailsInformation();
                qmsDetailsInformation.setDefectId(defectID);
                qmsDetailsInformation.setDefectPositionId(defectPositionID);
                qmsDetailsInformation.setDefectQuantity(defectCount);
                qmsDetailsInformation.setEntryDate(entryDate);
                qmsDetailsInformation.setEntryTime(entryTime);
                qmsDetailsInformation.setGarmentsNo(getGarmentsID(garmentNo, lotNo));

                qmsDetailsInformations.add(qmsDetailsInformation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return qmsDetailsInformations;
    }


    public List<Integer> getLastEntryID(int lotNo, int garmentsNo, String date) {
        String sql = "SELECT * FROM QCGarmentsDefectInfo WHERE QCGarmentsDefectInfo.Date = '" + date + "' AND LotNo = " + lotNo + " AND GarmentsNo = " + garmentsNo + ";";

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

    public void deleteDefectsFromQCDefectInfo(Integer id) {
        String sql = "DELETE FROM QCGarmentsDefectInfo \n" +
                "WHERE QCGarmentsDefectInfo.id = " + id + ";";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLITE_DB", "insertGarmentsDefectEntry: " + e.getMessage());
        }
    }

    public List<QMSGarmentDefectInfoDataModel> getAllQCDataModelsByGarmentNo(int mGarmentNo, int myLotNo, String myDate) {
        List<QMSGarmentDefectInfoDataModel> qcDataModelArrayList = new ArrayList<>();
        String sql = "SELECT * FROM QCGarmentsDefectInfo WHERE QCGarmentsDefectInfo.Date = '" + myDate + "' AND GarmentsNo = '" + mGarmentNo + "' AND LotNo = '" + myLotNo + "';";
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String date = cursor.getString(1);
                String time = cursor.getString(2);
                int defectID = Integer.parseInt(cursor.getString(3));
                String defectName = cursor.getString(4);
                int defectPosID = Integer.parseInt(cursor.getString(5));
                String defectPosName = cursor.getString(6);
                int garmentNo = Integer.parseInt(cursor.getString(7));
                int defectQty = Integer.parseInt(cursor.getString(8));
                int productionUniID = Integer.parseInt(cursor.getString(9));
                int qmsMasterKey = Integer.parseInt(cursor.getString(10));
                int lotNo = Integer.parseInt(cursor.getString(11));

                QMSGarmentDefectInfoDataModel qmsGarmentDefectInfoDataModel = new QMSGarmentDefectInfoDataModel();
                qmsGarmentDefectInfoDataModel.setId(id);
                qmsGarmentDefectInfoDataModel.setEntryDate(date);
                qmsGarmentDefectInfoDataModel.setEntryTime(time);
                qmsGarmentDefectInfoDataModel.setDefectId(defectID);
                qmsGarmentDefectInfoDataModel.setDefectName(defectName);
                qmsGarmentDefectInfoDataModel.setDefectPositionId(defectPosID);
                qmsGarmentDefectInfoDataModel.setDefectPositionName(defectPosName);
                qmsGarmentDefectInfoDataModel.setGarmentsNo(garmentNo);
                qmsGarmentDefectInfoDataModel.setDefectQuantity(defectQty);
                qmsGarmentDefectInfoDataModel.setProductionUnitID(productionUniID);
                qmsGarmentDefectInfoDataModel.setQmsMasterKey(qmsMasterKey);
                qmsGarmentDefectInfoDataModel.setLotNo(lotNo);


                qcDataModelArrayList.add(qmsGarmentDefectInfoDataModel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return qcDataModelArrayList;
    }


    public List<SyncKeys> getAllQMSMasterKeys() {
        List<SyncKeys> unSycMasterKeys = new ArrayList<>();
        String sql = "SELECT QmsMasterKey, ProductionUnitId FROM QCGarmentsDefectInfo WHERE isSynced = 0 GROUP BY QmsMasterKey;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int qmsMasterKey = Integer.parseInt(cursor.getString(0));
                int productionUnitId = Integer.parseInt(cursor.getString(1));

                SyncKeys syncKeys = new SyncKeys(productionUnitId, qmsMasterKey);
                unSycMasterKeys.add(syncKeys);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return unSycMasterKeys;
    }

    public List<QmsDetailsInformation> getAllUnSyncDefectDataForSending(int qmsMasterKey, String date) {

        List<QmsDetailsInformation> qmsDetailsInformations = new ArrayList<>();
        String sql = "SELECT DefectId, DefectPositionId, DefectQuantity, GarmentsNo, QCGarmentsDefectInfo.Date, QCGarmentsDefectInfo.Time, LotNo FROM QCGarmentsDefectInfo WHERE QCGarmentsDefectInfo.Date = '" + date + "' AND QmsMasterKey= '" + qmsMasterKey + "' AND isSynced = 0";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                int defectID = Integer.parseInt(cursor.getString(0));
                int defectPositionID = Integer.parseInt(cursor.getString(1));
                int defectCount = Integer.parseInt(cursor.getString(2));
                int garmentNo = Integer.parseInt(cursor.getString(3));
                String entryDate = cursor.getString(4);
                String entryTime = cursor.getString(5);
                int lotNo = Integer.parseInt(cursor.getString(6));

                QmsDetailsInformation qmsDetailsInformation = new QmsDetailsInformation();
                qmsDetailsInformation.setDefectId(defectID);
                qmsDetailsInformation.setDefectPositionId(defectPositionID);
                qmsDetailsInformation.setDefectQuantity(defectCount);
                qmsDetailsInformation.setEntryDate(entryDate);
                qmsDetailsInformation.setEntryTime(entryTime);
                qmsDetailsInformation.setGarmentsNo(getGarmentsID(garmentNo, lotNo));

                qmsDetailsInformations.add(qmsDetailsInformation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return qmsDetailsInformations;
    }


    public void clearDBEntryData() {

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            Log.d("SQLite_DB", "Clear DB table");

            db.execSQL("DROP TABLE IF EXISTS SilhouetteGrids;");
            db.execSQL("DROP TABLE IF EXISTS StyleImage;");
            db.execSQL("DROP TABLE IF EXISTS DefectListbyPositions;");
            db.execSQL("DROP TABLE IF EXISTS QCGarmentsDefectInfo;");

            Log.d("SQLite_DB", "Create DB table");
            db.execSQL(createSilhouetteGridsTable);
            db.execSQL(createImageListTable);
            db.execSQL(createDefectListTable);
            db.execSQL(createQCGarmentsDefectInfoTable);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
