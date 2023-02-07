# Лабораторная работа № 6

## Тема:
Настройки и состояние приложения.

## Цель:
Научиться сохранять данные при изменении ориентации смартфона.

## Задания:
1. Разработать мобильное приложение для сохранения данных из полей;

## Порядок выполнения:
1. Создадим новый проект;
2. Добавить поле EditText тип textMultiLine;
3. Добавить 2 кнопки – Получить данные и сохранить данные и создать для них обработчики событий;
4.
```java
public void restoreField(View view)
{
    TextView nameView = (TextView) findViewById(R.id.saveTextView);
    nameView.setText(name);
}
```
5. 
```java
public void saveField(View view)
{
    TextView nameBox = (TextView) findViewById(R.id.editTextTextMultiLine);
    name = nameBox.getText().toString();
}
```
6. Добавить поле TextView;
7. Добавим в них код:
8.
```java
public class MainActivity extends AppCompatActivity 
{
    String name = "неопределено";
    final static String nameVariableKey = "NAME_VAR";
    final static String textViewTexKey = "TEXT_VIEW";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putString(nameVariableKey, name);
        TextView nameView = (TextView) findViewById(R.id.saveTextView);
        outState.putString(textViewTexKey, nameView.getText().toString());
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        name = savedInstanceState.getString(nameVariableKey);
        String textViewText= savedInstanceState.getString(textViewTexKey);
        TextView nameView = (TextView) findViewById(R.id.saveTextView);
        nameView.setText(textViewText);
    }
}
```
9. Проверить работоспособность;
10. Составить отчёт.

## Файлы
* [app/src/main/res/layout/activity_main.xml](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab6/app/src/main/res/layout/activity_main.xml)
* [app/src/main/java/com/badlogic/sokolovtask_lab6/MainActivity.java](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab6/app/src/main/java/com/badlogic/sokolovtask_lab6/MainActivity.java)

## Пример работы
![image](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab6/example_gif_lab6.gif)

## Дополнительная информация
Для данного проекта нет дополнительных данных и информации.
