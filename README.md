# Лабораторная работа № 7

## Тема: 
Работа с файловой системой.

## Цель: 
Научиться сохранять данные в текстовый файл и считывать из него.

## Задание:
1. Разработать простое мобильное приложение для сохранения данных из поля в тестовый файл, а также считывать информацию из него;

## Порядок выполнения:
1. Создадим новый проект;
2. Добавить поле Plain Text, TextView, а также 2 кнопки – «Сохранить в файл» и «Открыть из файла»;
3. Напишем обработчик события для кнопки «Сохранить в файл»:
```java
public void buttonSaveClick(View view)
{
    try
    {
        EditText textBox = (EditText) findViewById(R.id.save_text);
        String text = textBox.getText().toString();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(text.getBytes());
        fos.close();
        Toast.makeText(this, "Текстовый файл успешно сохранён!", Toast.LENGTH_SHORT).show();
    } catch (FileNotFoundException e)
    {
        e.printStackTrace();
        Toast.makeText(this, "Файл не найден!", Toast.LENGTH_SHORT).show();
    } catch (IOException e)
    {
        e.printStackTrace();
        Toast.makeText(this, "Ошибка сохранения файла!", Toast.LENGTH_SHORT).show();
    }
}
```
4. Напишем обработчик события для кнопки «Открыть из файла»:
5.
```java
public void buttonOpenClick(View view)
{
     try
     {
        FileInputStream fin = new FileInputStream(file);
        byte[] bytes = new byte[fin.available()];
        fin.read(bytes);
        String text = new String(bytes);
        TextView textView = (TextView) findViewById(R.id.open_text);
        textView.setText(text);
        fin.close();
    } catch (IOException ex)
     {
        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
     }
}
```
6. Установим разрешение на доступ в файле «AndroidManifest.xml»:
```xml 
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myfilesystemapplication">
    
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    ...
```
7. Назначим имя файла и установим путь для него:
8.
```java
public class MainActivity extends AppCompatActivity
{
    String fileName = "content.txt";
    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName);
    ...
```
9. Проверить работоспособность;
10. Составить отчёт.

## Файлы
* [app/src/main/AndroidManifest.xml](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab7/app/src/main/AndroidManifest.xml)
* [app/src/main/res/layout/activity_main.xml](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab7/app/src/main/res/layout/activity_main.xml)
* [app/src/main/java/com/badlogic/sokolovtask_lab7/MainActivity.java](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab7/app/src/main/java/com/badlogic/sokolovtask_lab7/MainActivity.java)
* [app/src/main/java/com/badlogic/sokolovtask_lab7/PermissionUtils.java](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab7/app/src/main/java/com/badlogic/sokolovtask_lab7/PermissionUtils.java)

## Пример работы приложения
![image](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab7/example_gif_lab7.gif)

## Дополнительная информация
Так как теория из лабораторной не распространяется на современные версии, то приходится использовать другие методы. Для добавления разрешения на редактирования памяти необходимо вписать следующий код в `AndroidManifest.xml`.
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="29"/>
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" tools:ignore="ScopedStorage"/>
```
Чтобы запросить разрешение необходимо написать следующий код, я его написал в классе [PermissionUtils.java](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab7/app/src/main/java/com/badlogic/sokolovtask_lab7/PermissionUtils.java).
