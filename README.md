# Лабораторная работа № 3

## Тема:
Создание многостраничного приложения.

## Цель: 
Научиться работать с Activity.

## Задания:
1. Разработать мобильное приложение с использованием перехода от одной Activity к другой;

## Порядок выполнения:
1. Создадим новый проект;
2. Добавим пустой Activity;
3. Добавим кнопку на MainActivity;
4. Создадим обработчик на кнопке onClick и напишем код:
```java
public void onClick(View view)
{
    Intent intent = new Intent(this, SecondActivity.class);
    startActivity(intent);
}
```
5. Сделаем вывод на второй Activity:
```java
TextView textView = new TextView(this);
textView.setTextSize(20);
textView.setPadding(16, 16, 16, 16);
textView.setText("Лабораторная работа № 3. Работу выполнил …..");
setContentView(textView);
setContentView(R.layout.activity_second);
```
6. Составить отчёт.

## Файлы
* [app/src/main/java/com/badlogic/sokolovtask_lab3/MainActivity.java](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab3/app/src/main/java/com/badlogic/sokolovtask_lab3/MainActivity.java)
* [app/src/main/java/com/badlogic/sokolovtask_lab3/SecondActivity.java](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab3/app/src/main/java/com/badlogic/sokolovtask_lab3/SecondActivity.java)
* [app/src/main/res/layout/activity_main.xml](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab3/app/src/main/res/layout/activity_main.xml)
* [app/src/main/res/layout/activity_second.xml](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab3/app/src/main/res/layout/activity_second.xml)

## Дополнительная информация
Данный код работает некорректно:
```java
setContentView(textView);
setContentView(R.layout.activity_second);
```
По задумке должно отображать текст, однако сначала будет отображаться текст, а потом `activity_second.xml`. Чтобы отображать одновременно два View необходим данный код:
```java
View activity_second = getLayoutInflater().inflate(R.layout.activity_second, null);
ViewGroup viewGroup = (ViewGroup) activity_second;
viewGroup.addView(textView);

setContentView(activity_second);
```
