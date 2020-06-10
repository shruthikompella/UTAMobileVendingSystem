package com.example.vendingmachineapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Database
    public static final String DATABASE_NAME = "VMApp.db";



    //systemusertable
    public static final String USER_TABLE = "systemusertable";
    public static final String FIRSTNAME = "FNAME";
    public static final String LASTNAME = "LNAME";
    public static final String LOGIN_USERNAME = "UNAME";
    public static final String LOGIN_PASSWORD = "PASSWORD";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    public static final String STREET = "STREET";
    public static final String CITY = "CITY";
    public static final String STATE = "STATE";
    public static final String ZIP = "ZIP";
    public static final String ROLE = "ROLE";
    public static final String CREATE_USER_TABLE  = "CREATE TABLE " + USER_TABLE + "("+FIRSTNAME+" TEXT,"+LASTNAME+" TEXT," + LOGIN_USERNAME +" TEXT PRIMARY KEY," +LOGIN_PASSWORD +" TEXT,"+ PHONE+" TEXT,"+EMAIL +" TEXT,"+ STREET+" TEXT,"+ CITY+" TEXT,"+STATE+" TEXT,"+ ZIP+" INTEGER,"+ ROLE+" TEXT)";



    //vehicletable
    public static final String VEHICLE_TABLE = "vehicletable";
    public static final String VEHICLE_NAME = "VEHICLE_NAME";
    public static final String VENDING_TYPE = "VENDING_TYPE";
    public static final String CREATE_VEHICLE_TABLE = "CREATE TABLE " + VEHICLE_TABLE + "("+VEHICLE_NAME+" TEXT PRIMARY KEY,"+VENDING_TYPE+" TEXT)";


    //inventorytable
    public static final String INVENTORY_TABLE_NAME = "inventorytable";
    public static final String V_NAME = "VEHICLE_NAME";
    public static final String DRINKS_R = "DRINKS_R";
    public static final String SNACKS_R = "SNACKS_R";
    public static final String SANDWICHES_R = "SANDWICHES_R";
    public static final String CREATE_INVENTORY_TABLE = "CREATE TABLE " + INVENTORY_TABLE_NAME + "("+V_NAME +" TEXT PRIMARY KEY,"+DRINKS_R +" INTEGER,"+ SNACKS_R+" INTEGER,"+ SANDWICHES_R +" INTEGER, FOREIGN KEY("+ V_NAME +") REFERENCES "+ VEHICLE_TABLE +"("+VEHICLE_NAME+"))";





    //locationtable
    public static final String LOCATION_TABLE = "locationtable";
    public static final String LOCATION_ID = "LOCATION_ID";
    public static final String LOCATIION_INTERSECTION = "LOCATIION_INTERSECTION";
    public static final String DURATION = "DURATION";
    public static final String CREATE_LOCATION_TABLE = "CREATE TABLE " + LOCATION_TABLE + "("+LOCATION_ID+" TEXT PRIMARY KEY,"+LOCATIION_INTERSECTION+" TEXT," + DURATION +" INTEGER)";



    //scheduletable
    public static final String SCHEDULE_TABLE = "scheduletable";
    public static final String SCHEDULE_V_NAME = "VEHICLE_NAME";
    public static final String SCHEDULE_L_ID = "LOCATION_ID";
    public static final String SCHEDULE_S_TIME = "START_TIME";
    public static final String SCHEDULE_E_TIME = "END_TIME";
    public static final String SCHEDULE_DATE = "DATE";
    public static final String SCHEDULE_OP_NAME = "OPERATOR_NAME";
    public static final String CREATE_SCHEDULE_TABLE = "CREATE TABLE " + SCHEDULE_TABLE + "("+SCHEDULE_V_NAME+" TEXT,"+SCHEDULE_L_ID+" TEXT," + SCHEDULE_S_TIME +" TEXT,"+SCHEDULE_E_TIME+" TEXT,"+SCHEDULE_DATE+" TEXT,"+SCHEDULE_OP_NAME+" TEXT,PRIMARY KEY("+SCHEDULE_V_NAME+","+SCHEDULE_S_TIME+","+SCHEDULE_E_TIME+","+SCHEDULE_DATE+"),FOREIGN KEY("+SCHEDULE_V_NAME+") REFERENCES "+VEHICLE_TABLE +"("+VEHICLE_NAME+"),FOREIGN KEY("+SCHEDULE_L_ID+") REFERENCES "+LOCATION_TABLE+"("+LOCATION_ID+"))";



    //availablerevenue
    public static final String AVAILABLE_REVENUE_TABLE = "availablerevenuetable";
    public static final String AVAILABLE_V_NAME = "VEHICLE_NAME";
    public static final String AVAILABLE_DATE = "DATE";
    public static final String AVAILABLE_FLAG = "FLAG";
    public static final String AVAILABLE_REVENUE = "REVENUE";
    public static final String CREATE_AVAILABLE_REVENUE_TABLE = "CREATE TABLE " + AVAILABLE_REVENUE_TABLE + "("+AVAILABLE_V_NAME+" TEXT,"+AVAILABLE_DATE+" TEXT," + AVAILABLE_FLAG +" INTEGER,"+AVAILABLE_REVENUE+" NUMERIC, PRIMARY KEY("+AVAILABLE_V_NAME+","+AVAILABLE_DATE+"),FOREIGN KEY("+AVAILABLE_V_NAME+") REFERENCES "+VEHICLE_TABLE+"("+VEHICLE_NAME+"))";



    //dummyoperatortable
    public static final String DUMMY_OPERATOR_TABLE = "dummyoperatortable";
    public static final String OPERATOR_UNAME = "OPERATOR_UNAME";
    public static final String CREATE_DUMMY_TABLE = "CREATE TABLE " + DUMMY_OPERATOR_TABLE + "("+OPERATOR_UNAME+" TEXT PRIMARY KEY)";

    //carttable
    public static final String CART_TABLE = "carttable";
    public static final String SUBTOTAL = "SUBTOTAL";
    public static final String NO_DRINKS = "NO_DRINKS";
    public static final String NO_SNACKS = "NO_SNACKS";
    public static final String NO_SANDWICHES = "NO_SANDWICHES";
    public static final String CREATE_CART_TABLE = "CREATE TABLE " + CART_TABLE + "("+NO_DRINKS +" INTEGER,"+NO_SANDWICHES +" INTEGER,"+ NO_SNACKS+" INTEGER,"+ SUBTOTAL +" NUMERIC)";



    //ordertable
    public static final String ORDER_TABLE = "ordertable";
    public static final String CONF_NUMBER = "CONF_NUMBER";
    public static final String USER_NAME = "USER_NAME";
    public static final String VEHICLE_TYPE = "VEHICLE_TYPE";
    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
    public static final String VEHICLE_N = "VEHICLE_N";
    public static final String LOCATION_INTER = "LOCATION_INTER";
    public static final String NUM_DRINKS = "NUM_DRINKS";
    public static final String NUM_SNACKS = "NUM_SNACKS";
    public static final String NUM_SANDWICHES = "NUM_SANDWICHES";
    public static final String SUBT = "SUBT";
    public static final String TOT = "TOT";
    public static final String CREATE_ORDER_TABLE  = "CREATE TABLE " + ORDER_TABLE + "("+CONF_NUMBER+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            USER_NAME+" TEXT," +DATE+" TEXT," + TIME +" TEXT," +VEHICLE_N +
            " TEXT,"+ LOCATION_INTER+" TEXT,"+ VEHICLE_TYPE+" TEXT,"+NUM_DRINKS +" INTEGER,"+ NUM_SANDWICHES+" INTEGER,"+ NUM_SNACKS+" INTEGER,"+SUBT+
            " NUMERIC,"+ TOT+" NUMERIC)";






    public DatabaseHelper(@Nullable Context context) {
        super(context , DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_VEHICLE_TABLE);
        db.execSQL(CREATE_LOCATION_TABLE);
        db.execSQL(CREATE_DUMMY_TABLE);
        db.execSQL(CREATE_CART_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_SCHEDULE_TABLE);
        db.execSQL(CREATE_AVAILABLE_REVENUE_TABLE);
        db.execSQL(CREATE_INVENTORY_TABLE);



        //inserting systemusertable
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Tony','Stark','tony1','tony1234','123456709','tony@gmail.com','307 s cooper','San-Fransico','CA',34513,'user')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Steve','Jobs','steve1' ,'steve1234','9876500021','steve@gmail.com','357 William Blvd','Washington','WA',36076,'user')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Clint','Red', 'clint1', 'clint1234','9875364200','clint@gmail.com','371 Russel St','Fall River','MA',23241,'user')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Nat','Josh','nat1' , 'nat1234','9876543210','nat@gmail.com','13 Jungle Rd','Jersey City','NJ',76013,'user')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Kruti','abc','kruti123','123456','123456709','kruti@gmail.com','212 s cooper','arlington','TX',76013,'operator')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Shruthi','komp','shruthi123','123456','123456709','shruthi@gmail.com','212 s cooper','arlington','TX',76013,'operator')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Apoorva','Sh','apoorva123','123456','123456709','apoorva@gmail.com','308 s cooper','Arlington','TX',76013,'operator')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('vatsal','patel','vatsal123','123456','123456709','vatsal@gmail.com','212 s cooper','arlington','TX',76013, 'operator')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Milind','K','milind123','123456','123456709','milind@gmail.com','308 s cooper','Arlington','TX',76013,'operator')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Rohith','R','rohith123','123456','123456709','rohith@gmail.com','308 s cooper','Arlington','TX',76013,'operator')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Saumya','Bhatt','saumya123','123456','123456709','saumya@gmail.com','308 s cooper','Arlington','TX',76013,'operator')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('John','Archer','John12','123456','123456709','johnarch@gmail.com','400 s cooper','Arlington','TX',76013,'manager')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Sam','Johnson','SammyJ','Password12$', '817-777-2000', 'sammyJ@mavs.uta.edu','1234 Anywhere Ln','Arlington','Tx', '76019','manager')");
        db.execSQL("INSERT INTO " +USER_TABLE +" VALUES('Susan','Queen','SuzieQ','Password$34', '817-777-2345', 'SuzieQ@mavs.uta.edu','5678 Straight Dr','Arlington','Tx', '76019','operator')");

        //inserting vehicletable
        db.execSQL("INSERT INTO " +VEHICLE_TABLE +" VALUES('T1','Food Truck')");
        db.execSQL("INSERT INTO " +VEHICLE_TABLE +" VALUES('T2','Food Truck')");
        db.execSQL("INSERT INTO " +VEHICLE_TABLE +" VALUES('C1','Cart')");
        db.execSQL("INSERT INTO " +VEHICLE_TABLE +" VALUES('C2','Cart')");
        db.execSQL("INSERT INTO " +VEHICLE_TABLE +" VALUES('C3','Cart')");
        db.execSQL("INSERT INTO " +VEHICLE_TABLE +" VALUES('C4','Cart')");
        db.execSQL("INSERT INTO " +VEHICLE_TABLE +" VALUES('C5','Cart')");


        //inserting locationtable
        db.execSQL("INSERT INTO " +LOCATION_TABLE +" VALUES('Location 1','Cooper & UTA Blvd',2)");
        db.execSQL("INSERT INTO " +LOCATION_TABLE +" VALUES('Location 2','W Nedderman & Greek Row',1)");
        db.execSQL("INSERT INTO " +LOCATION_TABLE +" VALUES('Location 3','S Davis & W Mitchell',2)");
        db.execSQL("INSERT INTO " +LOCATION_TABLE +" VALUES('Location 4','Cooper & W Mitchell',3)");
        db.execSQL("INSERT INTO " +LOCATION_TABLE +" VALUES('Location 5','S Oak & UTA Blvd',2)");
        db.execSQL("INSERT INTO " +LOCATION_TABLE +" VALUES('Location 6','Spaniolo & W 1st',4)");
        db.execSQL("INSERT INTO " +LOCATION_TABLE +" VALUES('Location 7','Spaniolo & W Mitchell',2)");
        db.execSQL("INSERT INTO " +LOCATION_TABLE +" VALUES('Location 8','S Center & W Mitchell',1)");


        //creating and inserting scheduletable
        /*************T1*************************/
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('T1','Location 1','08:00','10:00','05/07/2020','shruthi123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('T1','Location 2','10:00','11:00','05/07/2020','shruthi123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('T1','Location 3','12:00','14:00','05/07/2020','shruthi123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('T1','Location 4','14:00','17:00','05/07/2020','shruthi123')");
        /*************T1*************************/



        /*************T2*************************/
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('T2','Location 2','08:00','09:00','05/07/2020','saumya123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('T2','Location 8','09:00','10:00','05/07/2020','saumya123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('T2','Location 6','12:00','16:00','05/07/2020','saumya123')");
        /*************T2*************************/



        /*************C1*************************/
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C1','Location 7','08:00','10:00','05/07/2020','SuzieQ')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C1','Location 1','11:00','13:00','05/07/2020','SuzieQ')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C1','Location 8','13:00','14:00','05/07/2020','SuzieQ')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C1','Location 3','14:00','16:00','05/07/2020','SuzieQ')");
        /*************C1*************************/



        /*************C2*************************/
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C2','Location 4','08:00','11:00','05/07/2020','rohith123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C2','Location 8','11:00','12:00','05/07/2020','rohith123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C2','Location 5','12:00','14:00','05/07/2020','rohith123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C2','Location 7','14:00','16:00','05/07/2020','rohith123')");
        /*************C2*************************/






        /*************C3*************************/
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C3','Location 6','08:00','12:00','05/07/2020','apoorva123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C3','Location 2','14:00','15:00','05/07/2020','apoorva123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C3','Location 8','16:00','17:00','05/07/2020','apoorva123')");
        /*************C3*************************/





        /*************C4*************************/
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C4','Location 3','08:00','10:00','05/07/2020','kruti123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C4','Location 8','10:00','11:00','05/07/2020','kruti123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C4','Location 2','11:00','12:00','05/07/2020','kruti123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C4','Location 7','12:00','14:00','05/07/2020','kruti123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C4','Location 5','14:00','16:00','05/07/2020','kruti123')");

        /*************C4*************************/





        /*************C5*************************/
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C5','Location 8','08:00','09:00','05/07/2020','vatsal123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C5','Location 2','09:00','10:00','05/07/2020','vatsal123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C5','Location 4','11:00','14:00','05/07/2020','vatsal123')");
        db.execSQL("INSERT INTO " + SCHEDULE_TABLE +" VALUES('C5','Location 1','14:00','16:00','05/07/2020','vatsal123')");
        /*************C5*************************/




        //inserting revenuetable

        /*05/05/2020*/


        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('T1','05/05/2020',1, 18.94375)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('T2','05/05/2020',1, 17.049375)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C1','05/05/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C2','05/05/2020',1,18.131875)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C3','05/05/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C4','05/05/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C5','05/05/2020',1,0.00)");

        /*05/06/2020*/


        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('T1','05/06/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('T2','05/06/2020',1, 8.930625)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C1','05/06/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C2','05/06/2020',1, 9.20125)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C3','05/06/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C4','05/06/2020',1,36.805)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C5','05/06/2020',1,0.00)");
        /*05/07/2020*/

        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('T1','05/07/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('T2','05/07/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C1','05/07/2020',1, 18.4025)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C2','05/07/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C3','05/07/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C4','05/07/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C5','05/07/2020',1,0.00)");


        /*05/08/2020*/

        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('T1','05/08/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('T2','05/08/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C1','05/08/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C2','05/08/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C3','05/08/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C4','05/08/2020',1,0.00)");
        db.execSQL("INSERT INTO " +AVAILABLE_REVENUE_TABLE +" VALUES('C5','05/08/2020',1,0.00)");


        //inserting inventorytable
        db.execSQL("INSERT INTO " +INVENTORY_TABLE_NAME +" VALUES('T1',50,40,35)");
        db.execSQL("INSERT INTO " +INVENTORY_TABLE_NAME +" VALUES('T2',50,40,35)");
        db.execSQL("INSERT INTO " +INVENTORY_TABLE_NAME +" VALUES('C1',30,30,5)");
        db.execSQL("INSERT INTO " +INVENTORY_TABLE_NAME +" VALUES('C2',30,30,5)");
        db.execSQL("INSERT INTO " +INVENTORY_TABLE_NAME +" VALUES('C3',30,30,5)");
        db.execSQL("INSERT INTO " +INVENTORY_TABLE_NAME +" VALUES('C4',30,30,5)");
        db.execSQL("INSERT INTO " +INVENTORY_TABLE_NAME +" VALUES('C5',30,30,5)");



        //inserting the ordertable
        /*05/05/2020*/
        db.execSQL("INSERT INTO " +ORDER_TABLE +" VALUES(5324001,'steve1','05/05/2020','11:00','C2','W Nedderman & Greek Row','Cart',1,1,1,8.5,9.20125)");
        db.execSQL("INSERT INTO " +ORDER_TABLE +" VALUES(5324002,'tony1','05/05/2020','13:00','T1','W Nedderman & Greek Row','Food Truck',4,2,0,17.5,18.94375)");
        db.execSQL("INSERT INTO " +ORDER_TABLE +" VALUES(5324006,'steve1','05/05/2020','14:00','C2','S Davis & W Mitchell','Food Truck',3,0,3,8.25,8.930625)");
        db.execSQL("INSERT INTO " +ORDER_TABLE +" VALUES(5324003,'tony1','05/05/2020','15:00','T2','Cooper & W Mitchell','Food Truck',2,2,1,15.75,17.049375)");
        /*05/06/2020*/
        db.execSQL("INSERT INTO " +ORDER_TABLE +" VALUES(5324004,'nat1','05/06/2020','10:00','C4','Spaniolo & W Mitchell','Cart',0,2,1,12.75,13.801875)");
        db.execSQL("INSERT INTO " +ORDER_TABLE +" VALUES(5324007,'nat1','05/06/2020','11:00','C4','Spaniolo & W 1st','Cart',0,2,1,12.75,13.801875)");
        db.execSQL("INSERT INTO " +ORDER_TABLE +" VALUES(5324008,'steve1','05/06/2020','11:00','C4','Spaniolo & W 1st','Cart',1,1,1,8.5,9.20125)");
        db.execSQL("INSERT INTO " +ORDER_TABLE +" VALUES(5324005,'clint1','05/06/2020','13:00','C2','S Davis & W Mitchell','Cart',1,1,1,8.5,9.20125)");
        db.execSQL("INSERT INTO " +ORDER_TABLE +" VALUES(5324009,'tony1','05/06/2020','14:00','T2','Cooper & W Mitchell','Food Truck',3,0,3,8.25,8.930625)");
        /*05/07/2020*/
        db.execSQL("INSERT INTO " +ORDER_TABLE +" VALUES(5324010,'steve1','05/07/2020','11:00','C3','Cooper & UTA Blvd','Cart',2,2,2,17,18.4025)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + INVENTORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VEHICLE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + AVAILABLE_REVENUE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LOCATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DUMMY_OPERATOR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CART_TABLE);
        onCreate(db);
    }



    public boolean insertData(String fname, String lname, String uname, String pwd, String phone, String email, String street, String city, String state,Integer zip, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(ID,id);
        contentValues.put(FIRSTNAME,fname);
        contentValues.put(LASTNAME,lname);
        contentValues.put(LOGIN_USERNAME,uname);
        contentValues.put(LOGIN_PASSWORD,pwd);
        contentValues.put(PHONE,phone);
        contentValues.put(EMAIL,email);
        contentValues.put(STREET,street);
        contentValues.put(CITY,city);
        contentValues.put(STATE,state);
        contentValues.put(ZIP,zip);
        contentValues.put(ROLE,role);
        long result = db.insert(USER_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from systemusertable ORDER BY FNAME",null);
        return res;
    }

    public Cursor viewSpecificSchedule(String name,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select s.VEHICLE_NAME,v.VENDING_TYPE,s.DATE,s.START_TIME,s.END_TIME,s.LOCATION_ID,l.LOCATIION_INTERSECTION from scheduletable s INNER JOIN vehicletable v ON v.VEHICLE_NAME = s.VEHICLE_NAME INNER JOIN locationtable l ON s.LOCATION_ID = l.LOCATION_ID WHERE s.OPERATOR_NAME = '"+name+"' AND s.DATE ='" + date+ "'",null);
        return res;
    }


    public Cursor getVehicleData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT v.VEHICLE_NAME, v.VENDING_TYPE, l.LOCATION_ID, l.LOCATIION_INTERSECTION, s.DATE, s.START_TIME, s.END_TIME from locationtable l INNER JOIN scheduletable s ON l.LOCATION_ID = s.LOCATION_ID INNER JOIN vehicletable v ON v.VEHICLE_NAME= s.VEHICLE_NAME ORDER BY v.VEHICLE_NAME" ,null);
        return res;
    }

    public Cursor getInventoryData(String vname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT DRINKS_R,SNACKS_R,SANDWICHES_R from inventorytable WHERE VEHICLE_NAME='"+vname+"'",null);
        return res;
    }

    public Cursor getRevenue(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * from availablerevenuetable a WHERE a.DATE = '"+date+"' ORDER BY VEHICLE_NAME" ,null);
        return res;
    }

    public Cursor getMyInventory(String opname,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT s.VEHICLE_NAME, v.VENDING_TYPE, i.DRINKS_R,i.SNACKS_R,i.SANDWICHES_R,  s.START_TIME,s.END_TIME, r.REVENUE,l.LOCATIION_INTERSECTION FROM inventorytable i INNER JOIN scheduletable s ON i.VEHICLE_NAME = s.VEHICLE_NAME INNER JOIN availablerevenuetable r ON s.VEHICLE_NAME = r.VEHICLE_NAME INNER JOIN vehicletable v ON s.VEHICLE_NAME = v.VEHICLE_NAME INNER JOIN locationtable l ON s.LOCATION_ID = l.LOCATION_ID WHERE OPERATOR_NAME = '"+ opname +"' AND r.FLAG <> 0 AND r.DATE ='"+date+"'",null);
        return res;
    }


    public Cursor getInventory(String v_name,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT s.VEHICLE_NAME, v.VENDING_TYPE, i.DRINKS_R,i.SNACKS_R,i.SANDWICHES_R,  s.START_TIME,s.END_TIME, r.REVENUE,s.LOCATION_ID, s.OPERATOR_NAME FROM inventorytable i INNER JOIN scheduletable s ON i.VEHICLE_NAME = s.VEHICLE_NAME INNER JOIN availablerevenuetable r ON s.VEHICLE_NAME = r.VEHICLE_NAME INNER JOIN vehicletable v ON s.VEHICLE_NAME = v.VEHICLE_NAME WHERE s.VEHICLE_NAME ='"+v_name+"' AND r.FLAG <> 0 AND r.DATE ='"+date+"' ORDER BY s.VEHICLE_NAME",null);

        return res;
    }

    public Cursor getDistinctVehiclesFromSchedule(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT DISTINCT s.VEHICLE_NAME FROM inventorytable i INNER JOIN scheduletable s ON i.VEHICLE_NAME = s.VEHICLE_NAME INNER JOIN availablerevenuetable r ON s.VEHICLE_NAME = r.VEHICLE_NAME INNER JOIN vehicletable v ON s.VEHICLE_NAME = v.VEHICLE_NAME WHERE r.FLAG <> 0 AND r.DATE ='"+date+"'",null);
        return res;
    }

    public Cursor getOperatorNullCheck(String v_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT DISTINCT OPERATOR_NAME FROM scheduletable WHERE VEHICLE_NAME='"+v_name+"'",null);
        int res = result.getCount();
        Log.d("OperatorNullCheck","OperatorNullCheck "+ res);
        return result;

    }

    public Cursor getAvailableVehicle(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select a.VEHICLE_NAME,v.VENDING_TYPE, a.DATE from availablerevenuetable a INNER JOIN vehicletable v ON  a.VEHICLE_NAME = v.VEHICLE_NAME WHERE a.FLAG <> 0 AND  a.DATE = '"+date+"' ORDER BY a.VEHICLE_NAME ",null);
        return res;
    }

    public boolean insertSchedule(String v_name, String location,String sTime, String eTime, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHEDULE_V_NAME,v_name);
        contentValues.put(SCHEDULE_L_ID,location);
        contentValues.put(SCHEDULE_S_TIME,sTime);
        contentValues.put(SCHEDULE_E_TIME,eTime);
        contentValues.put(SCHEDULE_DATE,date);
        contentValues.put(SCHEDULE_OP_NAME,"");
        long result = db.insert(SCHEDULE_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean insertInDummy(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OPERATOR_UNAME,name);
        long result = db.insert(DUMMY_OPERATOR_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean searchDummy(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + DUMMY_OPERATOR_TABLE + " WHERE " + OPERATOR_UNAME + "='" + name+"'", null);

        int res = result.getCount();
        if(res == 1)
            return true;
        else
            return false;

    }


    public Cursor fetchOperator(String v_name, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor result = db.rawQuery("SELECT DISTINCT OPERATOR_NAME FROM scheduletable WHERE VEHICLE_NAME  = '"+v_name+"' AND DATE ='"+date+"' AND OPERATOR_NAME IS NOT '' ",null);

        return result;
    }


    public boolean updateScheduleData(String v_name,String date,String u_name) {
        Log.d("q",v_name);
        Log.d("q1",date);
        Log.d("q2",u_name);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCHEDULE_OP_NAME,u_name);
        db.update(SCHEDULE_TABLE,contentValues,"VEHICLE_NAME = ?"+" AND"+" DATE= ?" ,new String[] {v_name,date});
        return true;}


    public boolean updateData(String username,String fname, String lname, String phone, String email, String street, String city, String state,Integer zip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRSTNAME,fname);
        contentValues.put(LASTNAME,lname);
        contentValues.put(PHONE,phone);
        contentValues.put(EMAIL,email);
        contentValues.put(STREET,street);
        contentValues.put(CITY,city);
        contentValues.put(STATE,state);
        contentValues.put(ZIP,zip);
        db.update(USER_TABLE,contentValues,"UNAME = ?" ,new String[] {username });
        return true;

    }



    public boolean insertCart(int drinks,int sandwiches,int snacks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        double subtotal = (drinks*1.5) + (sandwiches*5.75) + (snacks *1.25);
        contentValues.put(NO_DRINKS,drinks);
        contentValues.put(NO_SANDWICHES,sandwiches);
        contentValues.put(NO_SNACKS,snacks);
        contentValues.put(SUBTOTAL,subtotal);
        long result = db.insert(CART_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getCart(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + CART_TABLE,null);
        return res;

    }
    public Cursor fetchOrder(String uname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT CONF_NUMBER FROM " + ORDER_TABLE +" WHERE CONF_NUMBER=(SELECT MAX("+CONF_NUMBER+") FROM ordertable)",null);
        return res;
    }

    public Cursor fetchSpecificOrder(int conf_number){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + ORDER_TABLE +" WHERE "+ CONF_NUMBER +"="+conf_number,null);
        return res;
    }

    public boolean insertOrder(String username,String date, String time, String vname, String vType,String loc_int, int drinks, int sandwiches, int snacks, double subtotal, double total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME,username);
        contentValues.put(DATE,date);
        contentValues.put(TIME,time);
        contentValues.put(VEHICLE_TYPE,vType);
        contentValues.put(VEHICLE_N,vname);
        contentValues.put(LOCATION_INTER,loc_int);
        contentValues.put(NUM_DRINKS,drinks);
        contentValues.put(NUM_SANDWICHES,sandwiches);
        contentValues.put(NUM_SNACKS,snacks);
        contentValues.put(SUBT,subtotal);
        contentValues.put(TOT,total);
        long result = db.insert(ORDER_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }


    public boolean updateCart(int drinks,int sandwiches,int snacks) {
        double subtotal = (drinks*1.5) + (sandwiches*5.75) + (snacks *1.25);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NO_DRINKS,drinks);
        contentValues.put(NO_SANDWICHES,sandwiches);
        contentValues.put(NO_SNACKS,snacks);
        contentValues.put(SUBTOTAL,subtotal);
        db.update(CART_TABLE,contentValues, "",null);
        return true;}



    public Cursor fetchPastOrders(String date,String uname){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT o.DATE, o.TIME, o.CONF_NUMBER FROM "+ ORDER_TABLE +" o INNER JOIN systemusertable s  ON s.UNAME=o.USER_NAME WHERE o.DATE= '"+ date +"' AND UNAME = '"+ uname +"' ORDER BY 1,2 DESC",null);
        return res;

    }



    public Cursor updateInventory(String vname, int drinks, int sandwiches, int snacks){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("UPDATE inventorytable SET DRINKS_R = DRINKS_R - "+ drinks +",SNACKS_R = SNACKS_R - "+ snacks +",SANDWICHES_R = SANDWICHES_R - " + sandwiches + " WHERE VEHICLE_NAME =  "+"'"+ vname +"'" ,null);
        Log.d("UPDATE",""+res.getCount());
        return res;
    }

    public Cursor updateRevenue(String date, String vname, double revenue){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("UPDATE availablerevenuetable SET REVENUE = REVENUE + "+ revenue + " WHERE VEHICLE_NAME =  "+"'"+ vname +"' AND DATE = "+"'"+ date + "'"   ,null);
        Log.d("UPDATE REVENUE",""+res.getCount());
        db.execSQL("delete from carttable");
        return res;
    }

    public Cursor ViewPastOrders(String date, String lname,String uname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select o.DATE,o.TIME, o.CONF_NUMBER,u.FNAME,u.LNAME from ordertable o INNER JOIN systemusertable u ON u.UNAME = o.USER_NAME WHERE DATE = "+"'"+ date + "'"+" AND u.LNAME = "+"'"+ lname + "'" +" AND o.VEHICLE_N = (SELECT DISTINCT VEHICLE_NAME FROM SCHEDULETABLE WHERE OPERATOR_NAME="+"'"+ uname + "') ORDER BY 1,2 DESC"   ,null);
        return res;


    }

}
