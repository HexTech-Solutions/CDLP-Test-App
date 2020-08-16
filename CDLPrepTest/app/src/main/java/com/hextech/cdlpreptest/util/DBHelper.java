package com.hextech.cdlpreptest.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CDLPDatabase.db";
    public static final String TABLE_NAME_MAIN = "QuestionAnswerTable";
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_MAIN + " ("
                + DatabaseTableColumns.QUESTION_ID.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + DatabaseTableColumns.QUESTION.toString() + " VARCHAR(200), "
                + DatabaseTableColumns.CATEGORY.toString() + " VARCHAR(50), "
                + DatabaseTableColumns.CORRECT_ANSWER.toString() + " VARCHAR(200), "
                + DatabaseTableColumns.WRONG_ANSWER_1.toString() + " VARCHAR(200), "
                + DatabaseTableColumns.WRONG_ANSWER_2.toString() + " VARCHAR(200),"
                + DatabaseTableColumns.WRONG_COUNT.toString() + " INTEGER DEFAULT 0, "
                + DatabaseTableColumns.CORRECT_COUNT.toString() + " INTEGER DEFAULT 0, "
                + DatabaseTableColumns.FAVORITE.toString() + " INTEGER DEFAULT 0)";
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

        populateDatabaseFromXML(context, sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //Returns data from whole row according to a where clause
    public ArrayList<Question> getQuestionDataWithSelection(String whereClause, int limit) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {DatabaseTableColumns.QUESTION_ID.toString(), DatabaseTableColumns.QUESTION.toString(), DatabaseTableColumns.CATEGORY.toString(), DatabaseTableColumns.CORRECT_ANSWER.toString(), DatabaseTableColumns.WRONG_ANSWER_1.toString(), DatabaseTableColumns.WRONG_ANSWER_2.toString(), DatabaseTableColumns.FAVORITE.toString()};
        Cursor cursor = db.query(TABLE_NAME_MAIN, projection, whereClause, null, null, null, "RANDOM() LIMIT " + limit);
        ArrayList<Question> questionList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int questionId = cursor.getInt(cursor.getColumnIndex(DatabaseTableColumns.QUESTION_ID.toString()));
            String questionText = cursor.getString(cursor.getColumnIndex(DatabaseTableColumns.QUESTION.toString()));
            String category = cursor.getString(cursor.getColumnIndex(DatabaseTableColumns.CATEGORY.toString()));
            String correctAnswer = cursor.getString(cursor.getColumnIndex(DatabaseTableColumns.CORRECT_ANSWER.toString()));
            String wrongAnswer1 = cursor.getString(cursor.getColumnIndex(DatabaseTableColumns.WRONG_ANSWER_1.toString()));
            String wrongAnswer2 = cursor.getString(cursor.getColumnIndex(DatabaseTableColumns.WRONG_ANSWER_2.toString()));
            int isFavorite = cursor.getInt(cursor.getColumnIndex(DatabaseTableColumns.FAVORITE.toString()));

            Question question = new Question(questionId, questionText, category, correctAnswer, wrongAnswer1, wrongAnswer2, isFavorite);
            questionList.add(question);
        }
        cursor.close();
        return questionList;
    }

    //Returns data from whole row according to a where clause
    public ArrayList<Question> getQuestionAndAnsCountWithSelection(String whereClause) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {DatabaseTableColumns.QUESTION_ID.toString(), DatabaseTableColumns.QUESTION.toString(), DatabaseTableColumns.CATEGORY.toString(), DatabaseTableColumns.CORRECT_ANSWER.toString(), DatabaseTableColumns.WRONG_ANSWER_1.toString(), DatabaseTableColumns.WRONG_ANSWER_2.toString(), DatabaseTableColumns.WRONG_COUNT.toString(), DatabaseTableColumns.CORRECT_COUNT.toString(), DatabaseTableColumns.FAVORITE.toString()};
        Cursor cursor = db.query(TABLE_NAME_MAIN, projection, whereClause, null, null, null, null);
        ArrayList<Question> questionList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int questionId = cursor.getInt(cursor.getColumnIndex(DatabaseTableColumns.QUESTION_ID.toString()));
            String questionText = cursor.getString(cursor.getColumnIndex(DatabaseTableColumns.QUESTION.toString()));
            String category = cursor.getString(cursor.getColumnIndex(DatabaseTableColumns.CATEGORY.toString()));
            String correctAnswer = cursor.getString(cursor.getColumnIndex(DatabaseTableColumns.CORRECT_ANSWER.toString()));
            String wrongAnswer1 = cursor.getString(cursor.getColumnIndex(DatabaseTableColumns.WRONG_ANSWER_1.toString()));
            String wrongAnswer2 = cursor.getString(cursor.getColumnIndex(DatabaseTableColumns.WRONG_ANSWER_2.toString()));
            int correctCount = cursor.getInt(cursor.getColumnIndex(DatabaseTableColumns.CORRECT_COUNT.toString()));
            int wrongCount = cursor.getInt(cursor.getColumnIndex(DatabaseTableColumns.WRONG_COUNT.toString()));
            int isFavorite = cursor.getInt(cursor.getColumnIndex(DatabaseTableColumns.FAVORITE.toString()));

            Question question = new Question(questionId, questionText, category, correctAnswer, wrongAnswer1, wrongAnswer2, isFavorite);
            question.setCorrectCount(correctCount);
            question.setWrongCount(wrongCount);
            questionList.add(question);
        }
        cursor.close();
        return questionList;
    }

    //Toggles the favorite status of a question
    public int addToFavorites(int questionId, int favorite) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseTableColumns.FAVORITE.toString(), favorite);
        return db.update(TABLE_NAME_MAIN, values, DatabaseTableColumns.QUESTION_ID.toString() + "=" + questionId, null);
    }

    //Increases the wrong/correct count of the passed question ids by 1
    public void increaseAnswerCount(ArrayList<Integer> questionIds, Boolean isWrong){
        SQLiteDatabase db = this.getWritableDatabase();

        String columnName;
        if(isWrong){
            columnName = DatabaseTableColumns.WRONG_COUNT.toString();
        }else{
            columnName = DatabaseTableColumns.CORRECT_COUNT.toString();
        }
        for(int i: questionIds){
            int initialCount = getInitialCount(i, columnName);

            ContentValues values = new ContentValues();
            values.put(columnName, ++initialCount);

            db.update(TABLE_NAME_MAIN, values, DatabaseTableColumns.QUESTION_ID.toString() + "=?", new String[] {String.valueOf(i)});
        }
        db.close();
    }

    //Gets the initial wrong/correct count of the questions
    private int getInitialCount (int questionId, String columnName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {DatabaseTableColumns.QUESTION_ID.toString(), columnName};
        Cursor cursor = db.query(TABLE_NAME_MAIN, projection , DatabaseTableColumns.QUESTION_ID.toString() + "=?", new String[] {String.valueOf(questionId)}, null, null, null, null);

        if (cursor !=null) {
            cursor.moveToFirst();
            return cursor.getInt(cursor.getColumnIndex(columnName));
        }
        cursor.close();
        return 0;
    }

    //This method populates the database on first creation from the xml
    private static void populateDatabaseFromXML(Context context, SQLiteDatabase sqLiteDatabase){
        try {
            InputStream is = context.getAssets().open("questiondata.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("question");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    insertQuestionDataToDatabase(sqLiteDatabase, getValue("question_text", element2), getValue("category", element2), getValue("correct_answer", element2), getValue("wrong_answer_1", element2), getValue("wrong_answer_2", element2));
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    //Inserts the question and answer read from the xml to the database
    private static void insertQuestionDataToDatabase(SQLiteDatabase sqLiteDatabase, String question, String category, String correctAnswer, String wrongAns1, String wrongAns2) {
        ContentValues values = new ContentValues();
        values.put(DatabaseTableColumns.QUESTION.toString(), question);
        values.put(DatabaseTableColumns.CATEGORY.toString(), category);
        values.put(DatabaseTableColumns.CORRECT_ANSWER.toString(), correctAnswer);
        values.put(DatabaseTableColumns.WRONG_ANSWER_1.toString(), wrongAns1);
        values.put(DatabaseTableColumns.WRONG_ANSWER_2.toString(), wrongAns2);

        sqLiteDatabase.insert(TABLE_NAME_MAIN, null, values);
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public enum DatabaseTableColumns {
        QUESTION_ID, CATEGORY, QUESTION, CORRECT_ANSWER, WRONG_ANSWER_1, WRONG_ANSWER_2, WRONG_COUNT, CORRECT_COUNT, FAVORITE
    }

}