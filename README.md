# Лабораторная работа № 8

## Тема: 
Работа с базой данных SQLite.

## Цель: 
Научиться работать с основными командами SQL.

## Задание:
1. Разработать мобильное приложение на основе базы данных для реализации команд добавить, удалить, обновить данные;

## Порядок выполнения:
1. Создадим новый проект;
2. Добавим на форму 3 компонента «EditText*», а также 5 компонентов «Button» с соответствующими именами «ID», «Name», «E-mail», «Добавить», «Удалить», «Очистить», «Считать», «Обновить».
3. Добавим новый класс для работы с базой данных и назовём его «DBHelper»;
4. Расширим этот класс методами «onCreate» и «onUpdate»;
5. Выберем конструктор суперкласса «SQLLiteOpenHelper» с параметрами – «Context», «Name», «Factory», «Version» и удалим лишние параметры:
```java
public DBHelper(@Nullable Context context)
{
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
```
6. Запишем код: 
```java
public class DBHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myBase";
    public static final String TABLE_PERSONS = "persons";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIL = "mail";

    public DBHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_PERSONS + "(" + KEY_ID + " integer primary key," + KEY_NAME + " text," + KEY_MAIL + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + TABLE_PERSONS);
        onCreate(db);
    }
}
```
7. Расширим класс «MainActivity» и добавим наши переменные:
8.
```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
     Button buttonAdd, buttonDelete, buttonClear, buttonRead, buttonUpdate;
     EditText etName, etEmail, etID;
     DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

        buttonRead = (Button) findViewById(R.id.buttonRead);
        buttonRead.setOnClickListener(this);

        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(this);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(this);

        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);

        etID = (EditText) findViewById(R.id.etID);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);

        dbHelper = new DBHelper(this);
    }
}
```
9. Создадим 1 свой обработчик на все кнопки:
```java
@Override
public void onClick(View v)
{
    String ID = etID.getText().toString();
    String name = etName.getText().toString();
    String email = etEmail.getText().toString();

    SQLiteDatabase database = dbHelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues(); // класс для добавления новых строк в таблицу

    switch (v.getId())
    {
        case R.id.buttonAdd:
            contentValues.put(DBHelper.KEY_NAME, name);
            contentValues.put(DBHelper.KEY_MAIL, email);
            database.insert(DBHelper.TABLE_PERSONS, null, contentValues);
            break;

        case R.id.buttonRead:
            Cursor cursor = database.query(DBHelper.TABLE_PERSONS, null, null, null,
                    null, null, null); // все поля без сортировки и группировки

            if (cursor.moveToFirst())
            {
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                do {
                    Log.d("mLog", "ID =" + cursor.getInt(idIndex) +
                            ", name = " + cursor.getString(nameIndex) +
                            ", email = " + cursor.getString(emailIndex));

                    } while (cursor.moveToNext());
            } else
                Log.d("mLog", "0 rows");

            cursor.close(); // освобождение памяти
            break;

        case R.id.buttonClear:
            database.delete(DBHelper.TABLE_PERSONS, null, null);
            break;

        case R.id.buttonDelete:
            if (ID.equalsIgnoreCase(""))
            {
                break;
            }
            int delCount = database.delete(DBHelper.TABLE_PERSONS, DBHelper.KEY_ID + "= " + ID, null);
            Log.d("mLog", "Удалено строк = " + delCount);

        case R.id.buttonUpdate:
            if (ID.equalsIgnoreCase(""))
            {
                break;
            }
            contentValues.put(DBHelper.KEY_MAIL, email);
            contentValues.put(DBHelper.KEY_NAME, name);
            int updCount = database.update(DBHelper.TABLE_PERSONS, contentValues, DBHelper.KEY_ID + "= ?", new String[] {ID});
            Log.d("mLog", "Обновлено строк = " + updCount);
    }
        dbHelper.close(); // закрываем соединение с БД
}
```
10. Найти созданную вами базу данных через путь: View–>Tool Windows–>Device File Explorer–>…..databases–>имя_БД;<br/>
База данных находится по пути data/data/com.name.application/database/name_database. [(Пример)](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab8/path_database.png)
12. Проверить работоспособность всех кнопок через окно «Logcat»;
13. Составить отчёт.

## Файлы
* [app/src/main/res/layout/activity_main.xml](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab8/app/src/main/res/layout/activity_main.xml)
* [app/src/main/java/com/badlogic/sokolovtask_lab8/DBHelper.java](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab8/app/src/main/java/com/badlogic/sokolovtask_lab8/DBHelper.java)
* [app/src/main/java/com/badlogic/sokolovtask_lab8/MainActivity.java](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab8/app/src/main/java/com/badlogic/sokolovtask_lab8/MainActivity.java)

## Пример работы приложения
![image](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab8/example_png_lab8.png)

### Logcat
#### Clear -> Read | Read
```
2023-02-07 23:31:45.229 18376-18376 mLog   com.badlogic.sokolovtask_lab8   0 rows
```

#### Add -> Read
```
2023-02-07 23:30:48.438 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =1, name = Name, email = mail@example.com
```

#### Add x3 -> Read
```
2023-02-07 23:31:20.610 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =1, name = Name, email = mail@example.com
2023-02-07 23:31:20.610 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =2, name = Name, email = mail@example.com
2023-02-07 23:31:20.611 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =3, name = Name, email = mail@example.com
```

#### Update (name=Surname) -> Read
```
2023-02-07 23:32:16.954 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =1, name = Surname, email = mail@example.com
2023-02-07 23:32:16.954 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =2, name = Name, email = mail@example.com
2023-02-07 23:32:16.954 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =3, name = Name, email = mail@example.com
```

#### Delete (ID=1) -> Read
```
2023-02-07 23:32:52.508 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =2, name = Name, email = mail@example.com
2023-02-07 23:32:52.508 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =3, name = Name, email = mail@example.com
```

#### Delete -> Read -> Add
```
2023-02-07 23:33:09.948 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =2, name = Name, email = mail@example.com
2023-02-07 23:33:09.948 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =3, name = Name, email = mail@example.com
2023-02-07 23:33:09.949 18376-18376 mLog   com.badlogic.sokolovtask_lab8   ID =4, name = Name, email = mail@example.com
```

## Дополнительная информация
Для данного проекта нет дополнительных данных и информации.
