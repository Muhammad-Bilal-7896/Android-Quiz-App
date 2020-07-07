package com.example.intermediatequizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "INTERMEDIATE QUIZ APP.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                QuizContract.ChaptersTable.TABLE_NAME + "( " +
                QuizContract.ChaptersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.ChaptersTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuizContract.QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_CHAPTER_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuizContract.QuestionsTable.COLUMN_CHAPTER_ID + ") REFERENCES " +
                QuizContract.ChaptersTable.TABLE_NAME + "(" + QuizContract.ChaptersTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.ChaptersTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Chapter c1 = new Chapter("Measurements");
        addCategory(c1);
        Chapter c2 = new Chapter("Vectors_and_equilibrium");
        addCategory(c2);
        Chapter c3 = new Chapter("Motion_and_Force");
        addCategory(c3);
        Chapter c4 = new Chapter("Work_and_energy");
        addCategory(c4);
        Chapter c5 = new Chapter("Circular_Motion");
        addCategory(c5);
        Chapter c6 = new Chapter("Fluid_dynamics");
        addCategory(c6);
        Chapter c7 = new Chapter("Oscillations");
        addCategory(c7);
        Chapter c8 = new Chapter("Waves");
        addCategory(c8);
        Chapter c9 = new Chapter("Physical_optics");
        addCategory(c9);
    }

    private void addCategory(Chapter category) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.ChaptersTable.COLUMN_NAME, category.getName());
        db.insert(QuizContract.ChaptersTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q0 = new Question("The number of derived units are?",
                "7", "8", "9","None of these", 4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q0);
        Question q1 = new Question("The branch of physics which is concerned with ultimate particles of which matter is composed of?",
                "Atomic Physics", "Nuclear Physics", "Plasma Physics","Particle Physics", 4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q1);
        Question q2 = new Question("Silicon is obtained from:",
                "Water", "Metals", "Wood","Sand", 4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q2);
        Question q3 = new Question("The branch of physics which deals with ultimate particles of which the matter is composed is?",
                "Plasma Physics", "Atomic Physics", "Nuclear Physics","Particle Physics", 4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q3);
        Question q4 = new Question("The branch of Physics which deals with the atomic nuclie is called ?",
                "Astro Physics", "Thermodynamics", "Meta Physics", "Nuclear Physics",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q4);
        Question q5 = new Question("The basic component of computer chip is ?",
                "Silicon", "Cadmium", "Selenium", "Glass",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q5);
        Question q6 = new Question("Mass is a quantity",
                "Derived", "Base", "Both derived and base","None of these", 2,
                Question.DIFFICULTY_MEDIUM, Chapter.Measurements);
        addQuestion(q6);
        Question q7 = new Question("Which is a base quantity?",
                "Time", "Force", "Power","Velocity", 1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q7);
        Question q8 = new Question("An example of base quantity is ?",
                "Area", "Length", "Velocity","Volume", 2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q8);
        Question q9 = new Question("S.I unit of intensity of light is:",
                "ampere", "mole", "candela","joule", 3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q9);
        Question q10 = new Question("One light year is equal to",
                "9.46 * 10^15 cm", "9.46 * 10^15", "9.46 * 10^15 km", "9.46 * 10^17",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q10);
        Question q11 = new Question("Light year is a unit of",
                "Light", "Time", "Velocity", "Distance",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q11);
        Question q12 = new Question("Solid angle subtended at the centre of sphere of radius 'r' in steradian is:",
                "2 pi", "4 pi", "6 pi","8 pi", 2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q12);
        Question q13 = new Question("An example of derived unit is",
                "candela", "ampere", "coulumb","mole", 3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q13);
        Question q14 = new Question("Which is not a base unit in SI units",
                "kilogram", "joule", "ampere","kelvin", 2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q14);
        Question q15 = new Question("Which of the following is a deriverd unit",
                "Newton", "Meter", "Candela","Mole", 4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q15);
        Question q16 = new Question("The SI unit of intensity of light is ",
                "ampere", "mole", "candela", "joule",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q16);
        Question q17 = new Question("The unit of work in base units is",
                "kgms^(-2)", "kgms^(-1)", "kgm^(2)s^(-2)", "kgm^(-1)s^(-2)",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q17);
        Question q18 = new Question("The total base units are____________",
                "7", "9", "6","8", 1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q18);
        Question q19 = new Question("The SI units are build from how many kinds of units",
                "7", "3", "2","4", 2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q19);
        Question q20 = new Question("Supplementary units are",
                "2", "3", "4","5", 1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q20);
        Question q21 = new Question("The SI unit of solid angle is",
                "degree", "steradian", "revolution","radian", 2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q21);
        Question q22 = new Question("The angle made by Ice Cone at its edge is a",
                "Plane angle", "Solid angle", "Critical angle", "Abtuse angle",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q22);
        Question q23 = new Question("Candela is the SI unit of",
                "Charge", "Luminous intensity", "Power", "Refractive index",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q23);
        Question q24 = new Question("The angle made by ice cone at its edge is a",
                "Plane angle", "Solid angle", "Critical angle","Abtuse angle", 2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q24);
        Question q25 = new Question("The SI unit of power in terms of base units are ",
                "kgm^(-1)s^(-2)", "kgm^(-1)s^(-3)", "kgms^(-2)","kgm^(2)s^(-3)",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q25);
        Question q26 = new Question("Which of the following is a derived quantity? ",
                "Force", "Mass", "Length","Time",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q26);
        Question q27 = new Question("The solid angle subtended at the " +
                "centre of sphere by an area of its surface equal to the square of radius",
                "degree", "radian", "minute","steradian",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q27);
        Question q28 = new Question("The example of derived of derived unit is",
                "candela", "ampere", "coulombs","None",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q28);
        Question q29 = new Question("Which is derived unit?",
                "candela", "ampere", "kelvin","newton",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q29);
        Question q30 = new Question("Which one is not a base unit in SI units",
                "kilogram", "joule", "kelvin","ampere",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q30);
        Question q31 = new Question("Unit used for the factor(l/g)^(1/2)",
                "meter", "second", "kilogram","radian",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q31);
        Question q32 = new Question("One Peta is equal to",
                "10^(18)", "10^(15)", "10^(12)","10^(-12)",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q32);
        Question q33 = new Question("The ratio of 1 nanometer to 1 attometer is",
                "10^9", "10^(8)", "10^(-9)","10(6)",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q33);
        Question q34 = new Question("The prefix pico is equal to ",
                "10^(-12)", "10^(-15)", "10^(-9)","10^(9)",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q34);
        Question q35 = new Question("Which of the following is the least multiple",
                "Atto", "Femto", "Nano","Pico",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q35);
        Question q36 = new Question("One tera is equal to",
                "10^(10)", "10^(9)", "10^(12)","10^(11)",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q36);
        Question q37 = new Question("One Peta is equal to",
                "10^(16)", "10^(15)", "10^(12)","10^(9)",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q37);
        Question q38 = new Question("The ratio of 1 nanometer to 1 attometer is",
                "10^(9)", "10^(8)", "10^(-9)","10^(-8)",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q38);
        Question q39 = new Question("Which of the following is least multiple ",
                "pico", "femto", "nano","atto",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q39);
        Question q40 = new Question("The Prefix atto stands for:",
                "10^(-9)", "10^(9)", "10^(18)","10^(18)",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q40);
        Question q41 = new Question("One pico is equal to",
                "10^(12)", "10^(-12)", "10^(-6)","10^(6)",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q41);
        Question q42 = new Question("How many nanometers in a meter?",
                "10^(-19)", "10^(+19)", "10^(9)","10^(-9)",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q42);
        Question q43 = new Question("One Femto is equal to:",
                "10^(-12)", "10^(-13)", "10^(-14)","10^(-15)",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q43);
        Question q44 = new Question("Significant figures in 0.00876 are ",
                "3", "4", "5","6",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q44);
        Question q45 = new Question("Significant figures in 1.00110 are ",
                "6", "5", "3","1",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q45);
        Question q46 = new Question("Significant figures in 0.00846 are",
                "3", "4", "6","7",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q46);
        Question q47 = new Question("The sum aof three numbers 2.7543,4.10,1.273 up to" +
                " correct decimal place is ",
                "8.12", "8.13", "8.1273","8.127",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q47);
        Question q48 = new Question("Which is a correct for the diameters of wire when measured by a" +
                " screw gauge of least count 0.001 cm?",
                "2.3cm", "2.31cm", "2.312cm","2.3124cm",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q48);
        Question q49 = new Question("The number 56.8546 is rounded off to three significant figures as ",
                "57.0", "56.8", "56.9","56.7",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q49);
        Question q50 = new Question("In scientific notation the number 0.0001 may be written as",
                "10^(-2)", "10^(-3)", "10^(1 * 10^(-4))","10 * 10^(4)",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q50);
        Question q51 = new Question("A student added three figures 72.1,3.32, and 0.003.The correct answer regarding " +
                "the rules of the addition of the significant figures will be",
                "75.423", "75.42", "75.4","75.5",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q51);
        Question q52 = new Question("The no. of significant figures in 0.0173 are",
                "2", "3", "4","5",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q52);
        Question q53 = new Question("Number of significant figures in 01.020mm are",
                "2", "3", "4","5",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q53);
        Question q54 = new Question("73.650 is rounded up to one decimal is",
                "74", "73.7", "73.67","73.6",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q54);
        Question q55 = new Question("275.00 has the significant digits___________",
                "2", "3", "4","5",5,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q55);
        Question q56 = new Question("Significant figures in 0.0010 are:",
                "1", "2", "3","4",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q56);
        Question q57 = new Question("Which one of the following is the correct record for the " +
                "diameter of a wire when measured with a screw gauge of least count of 0.001cm:",
                "2.3", "2.31", "2.312cm","2.3124cm",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q57);
        Question q58 = new Question("Significant figures in 0.00567 are",
                "2", "3", "4","5",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q58);
        Question q59 = new Question("(Past Paper)Absolute uncertainty in a measuring instrument is equal" +
                "to:",
                "Least count of instrument", "Fractional uncertainty", "Accuracy","Percentage uncertainty",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q59);
        Question q60 = new Question("The percentage uncertainty in mass and velocity are 2% and 3%.The " +
                "maximum uncertainty in the measurement of kinetic energy is",
                "11%", "8%", "6%","1%",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q60);
        Question q61 = new Question("If x1 = 10.5 +- 0.1 cm and x2 = 26.8.Then  x=+-0.1cm,x2-x1 is given by",
                "16.3 +- 0.1cm", "16.3 +- 0.2cm^(3)", "16.0 +- 0cm","16.3 +- 0.2cm",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q61);
        Question q62 = new Question("The percentage uncertainty in radius of a sphere is 2%.The total" +
                "percentage in the volume of a sphere is:",
                "2%", "4%", "6%","8%",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q62);
        Question q63 = new Question("Which pair has the same dimension ?",
                "Work and Power", "Force and Torque", "Torque and Power","None",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q63);
        Question q64 = new Question("The dimension of work are",
                "[MLT^(-1)]", "[MLT^(2)]", "[ML^(2)T^(-1)]","[MLT]",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q64);
        Question q65 = new Question("The dimension [M^(0)LT^(0)] represents the quantity",
                "Length", "Mass", "Time","velocity",1 ,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q65);
        Question q66 = new Question("The dimensional formula for the quantity 'light year' is:",
                "[LT^(-1)]", "[T]", "[ML^(-2)T^(-2)]","[L]",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q66);
        Question q67 = new Question("Which of the following pair has the same dimensions?",
                "Work and Power", "Momentum and Energy", "Power and Pressure","Work and Torque",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q67);
        Question q68 = new Question("The dimensions[ML^(2)T^(-2)] belongs to",
                "Pressure", "Momentum", "Power","Heat and Energy",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q68);
        Question q69 = new Question("The dimensions of density are",
                "[ML^(-2)]", "[M^(2)L^(-2)]", "[ML^(-3)]","[M^(-1)L^(-1)]",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q69);
        Question q70 = new Question("[M^(0)L^(0)T^(-1)] refers to",
                "Velocity", "Time period", "Frequency","Force",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q70);
        Question q71 = new Question("The dimensions of torque are",
                "[ML^(-1)T]", "[ML^(2)T^(1)]", "[ML^(2)T^(-2)]","[ML^(-1)T^(2)]",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q71);
        Question q72 = new Question("The dimensions of acceleration due to gravity is",
                "[MLT^(-2)]", "[MLT]", "[LT^(-2)]","[M^(0)Lr^(0)]",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q72);
        Question q73 = new Question("The dimensions of angular velocity are:",
                "[LT^(-1)]", "[LT^(-2)]", "[L^(-1)T]","[T^(-1)]",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q73);
        Question q74 = new Question("Which pair has same dimensions?",
                "Work and power", "Momentum and impulsive", "Force and torque","Torque and power",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q74);
        Question q75 = new Question("One light year is equal to__________",
                "9.46 * 10^(15)mm", "9.46 * 10^(15)cm", "9.46 * 10^(15)m","9.46 * 10^(15)km",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q75);
        Question q76 = new Question("Number of colors used in the process of color printing to produce the entire range of colors",
                "4", "5", "6","7",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q76);
        Question q77 = new Question("Light year is the unit of ",
                "Length", "Mass", "Time","Distance",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q77);
        Question q78 = new Question("The main frontiers of fundamental sciences are ",
                "One", "Two", "Three","Four",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q78);
        Question q79 = new Question("Which one of the following is not a unit of energy?",
                "kilowatt", "Erg", "Joule","Kilowatt hour",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q79);
        Question q80 = new Question("Steradian is the unit of",
                "Plane angle", "Temperature", "Solid angle","Intensity of light",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q80);
        Question q81 = new Question("Which one of the following is a base quantity",
                "Area", "Charge", "Current","Force",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q81);
        Question q82 = new Question("If p is the momentum of an object of mass m,the expression((p^(2)/2m) has base " +
                "units identical to",
                "Energy", "Force", "Power","Velocity",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q82);
        Question q83 = new Question("By increaing the number of observations in an experiment,the error in measurement:",
                "Increases", "Decreases", "Remains same","None of these",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q83);
        Question q84 = new Question("How many significant zeros are there in 0.0005010?",
                "3", "4", "2","6",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q84);
        Question q85 = new Question("Error in the measurement of radius of sphere is 1%.The error in the calculated value of " +
                "its volume is :",
                "7%", "5%", "3%","1%",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q85);
        Question q86 = new Question("The percentage errors in the measurement of mass and speed are 2% and 3% respectively" +
                "The maximum error in the calculation of K.E will be",
                "1%", "5%", "8%","11%",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q86);
        Question q87 = new Question("How many significant zeros are there in the 5.00 * 10^(-3)",
                "3", "4", "2","6",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q87);
        Question q88 = new Question("Zero error os the example of",
                "Personal error", "Random error", "Systematic error","All of these",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q88);
        Question q89 = new Question("Poor calibration is the example of",
                "Personal error", "Random error", "Systematic error","None of these",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q89);
        Question q90 = new Question("((F*l)/m)^(1/2) has the dimension of",
                "velocity", "Acceleration", "Momentum","None of these",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q90);
        Question q91 = new Question("The unit of energy is_________and its unit symbol is:",
                "Joule,j", "joule,J", "JouleJ","joule,j",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q91);
        Question q92 = new Question("The dimensions of light year are",
                "[LT^(-1)]", "[M^(0)L^(0)T]", "[ML^(2)T^(-2)]","[M^(0)LT^(0)]",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q92);
        Question q93 = new Question("Which one of the following is not dimensionally correct equation",
                "E=mc^(2)", "vf=vi + at", "S = vt^(2)","S=(1/2)at^(2)",3,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q93);
        Question q94 = new Question("The gravitational constant G has dimensions",
                "[ML^(-1)T^(-1)]", "[M^(0)L^(0)T]", "[ML^(2)T^(-2)","[ML^(-1)T^(0)]",1,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q94);
        Question q95 = new Question("Which of the following has the same dimensions as that of momentum",
                "Force", "Energy", "stress","Impulse",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q95);
        Question q96 = new Question("SI unit of light year is ",
                "candela", "Meter", "Second","None of these",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q96);
        Question q97 = new Question("Dimensions of strain is",
                "[ML^(-1)T^(-2)]", "[ML^(-1)T^(-1)]", "[ML^(-2)T^(-1)]","none of these",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q97);
        Question q98 = new Question("Symbol of Focal length is 'F' so its dimension are",
                "[MLT^(-2)]", "[L]", "[MLT^(-1)]","none of these",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q98);
        Question q99 = new Question("Dimensions of Refractive index",
                "[L]", "[ML]", "[ML^(2)]","none of these",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q99);
        Question q100 = new Question("Dimesion of elastic modulus",
                "[ML^(-1)T^(-1)]", "[ML^(-1)T^(-2)]", "[ML^(-2)T^(-1)]","[ML^(-1)]",2,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q100);
        Question q101 = new Question("Mathematically the unit vector is given by?",
                "A=AA", "A=A/A", "A=A/A","None",1,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q101);
        Question q102 = new Question("A single vector having the same effect as all the original vectors " +
                "taken together called",
                "Resultant vector", "Equal vector", "Position vector","Unit vector",1,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q102);
        Question q103 = new Question("Which one is vector quantity?",
                "Length", "Volume", "Velocity","Work",3,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q103);
        Question q104 = new Question("The unit vector along y-axis is",
               "i", "j", "k","None of these",2,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q104);
        Question q105 = new Question("The magnitude of vector can never be",
                "Positive", "Negative", "Both A and B","None",2,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q105);
        Question q106 = new Question("The vector 1/2^(1/2)i+1/2^(1/2)j is a ",
                "Null vector", "Unit vector", "Vector of magnitude 2^(1/2)","None",2,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q106);
        Question q107 = new Question("When two vectors are parallel and anti-parallel",
                "Zero", "180 degree", "90 degree","270 degree",2,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q107);
        Question q108 = new Question("Which is vector quantity?",
                "Power", "Inertia", "Entropy","Tension",4,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q108);
        Question q109 = new Question("The direction of vector in space is specified by:",
                "1-angle", "2-angles", "3-angles","4-angles",3,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q109);
        Question q110 = new Question("If Ax and Ay both are negative, the resultant vector will lie in",
                "First Quadrant", "Second Quadrant", "Third Quadrant","Fourth Quadrant",3,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q110);
        Question q111 = new Question("If a vector of magnitude 10 N is along y-axis,then its component " +
                "along x-axis is",
                "0 N", "5 N", "8.66 N","10 N",1,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q111);
        Question q112 = new Question("A force of 20 N acts along x-axis,its x-component is",
                "0 N", "10 N", "20 N","30 N",3,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q112);
        Question q113 = new Question("Vector has both of its component as negative,lies in:",
                "I Quadrant", "II Quadrant", "III Quadrant","IV Quadrant",3,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q113);
        Question q114 = new Question("If A = 2i + j + 2k then |A| is:",
                "Zero", "3", "5","9",2,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q114);
        Question q115 = new Question("If B= 4i + 5k, then its magnitude will be:",
                "9", "(41)^(1/2)", "7","3",2,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q115);
        Question q116 = new Question("A vector in space has components:",
                "1", "2", "3","4",3,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q116);
        Question q117 = new Question("If Rx is negative and Ry is positive then the resultant vector " +
                "lies in the __________ quadrant",
                "2nd", "3rd", "4th","5th",1,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q117);
        Question q118 = new Question("A force of 10 N is acting along x-axis,its y-component will be",
                "10 N", "(10)^(1/2)", "0","1 N",3,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q118);
        Question q119 = new Question("A force of 10 N makes an angle of 30 degree with y-axis.The magnitude" +
                " of x-component will be",
                "5 N", "8.66 N", "10 N","Zero",1,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q119);
        Question q120 = new Question("If Rx and Ry both are negative,then the direction of resultant " +
                "vector lies in which quadrant",
                "1", "2", "3","4",3,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q120);
        Question q121 = new Question("The vector in space has components",
                "2", "3", "4","5",2,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q121);
        Question q122 = new Question("The magnitude of rectangular components are equal if the angle with the " +
                "x-axis is",
                "30 degree", "45 degree", "60 degree","90 degree",2,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q122);
        Question q123 = new Question("If Rx is positive and Ry is negative, the resultant lies" +
                " in which quadrant",
                "1st", "2nd", "3rd","4th",4,
                Question.DIFFICULTY_EASY, Chapter.Measurements);
        addQuestion(q123);
        Question q124 = new Question("If a vector of 6 magnitude is along y-axis,its component along x-axis is",
                "0 N", "5 N", "8.66 N","10 N",1,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q124);
        Question q125 = new Question("If a vector of magnitude 10 N is along y-axis,its components along x-axis is",
                "0 N", "5 N", "8.66 N","10 N",1,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q125);
        Question q126 = new Question("If vector A is along the x-axis then its y-component will be",
                "A sin theta", "A cos theta", "A tan theta","Zero",4,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
        addQuestion(q126);
        Question q127 = new Question("The angle b/w rectangular components of a vector is",
                "60 degree", "90 degree", "180 degree","Zero",2,
                Question.DIFFICULTY_EASY, Chapter.Vectors_and_Equilibrium);
//        addQuestion(q127);
//        Question q32 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q32);
//        Question q33 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q33);
//        Question q34 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q34);
//        Question q35 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q35);
//        Question q36 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q36);
//        Question q37 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q37);
//        Question q38 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q38);
//        Question q39 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q39);
//        Question q40 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q40);
//        Question q25 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q25);
//        Question q26 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q26);
//        Question q27 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q27);
//        Question q28 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q28);
//        Question q29 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q29);
//        Question q30 = new Question("",
//                "", "", "","", ,
//                Question.DIFFICULTY_EASY, Chapter.Measurements);
//        addQuestion(q30);
//        Question q31 = new Question("",
//        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
        //Question q25 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q25);
        //Question q26 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q26);
        //Question q27 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q27);
        //Question q28 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q28);
        //Question q29 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q29);
        //Question q30 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q30);
        //Question q31 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q31);
        //Question q32 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q32);
        //Question q33 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q33);
        //Question q34 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q34);
        //Question q35 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q35);
        //Question q36 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q36);
        //Question q37 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q37);
        //Question q38 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q38);
        //Question q39 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q39);
        //Question q40 = new Question("",
        //        "", "", "","", ,
        //        Question.DIFFICULTY_EASY, Chapter.Measurements);
        //addQuestion(q40);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuizContract.QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuizContract.QuestionsTable.COLUMN_CHAPTER_ID, question.getChapterID());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Chapter> getAllCategories() {
        List<Chapter> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.ChaptersTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Chapter category = new Chapter();
                category.setId(c.getInt(c.getColumnIndex(QuizContract.ChaptersTable._ID)));
                category.setName(c.getString(c.getColumnIndex(QuizContract.ChaptersTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_DIFFICULTY)));
                question.setChapterID(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_CHAPTER_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuizContract.QuestionsTable.COLUMN_CHAPTER_ID + " = ? " +
                " AND " + QuizContract.QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuizContract.QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_DIFFICULTY)));
                question.setChapterID(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_CHAPTER_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}