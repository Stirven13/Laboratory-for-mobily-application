# Лабораторная работа № 4

## Тема:
Создание дизайна.

## Цель: 
Разработать дизайн приложения с выводом анимации.

## Задания:
1. Разработать мобильное приложение с дизайном для управления анимацией;

## Порядок выполнения:
1. Создадим новый проект;
2. Добавить все эти изображения в проект в папку *res/drawable*;
3. Добавим в эту же папку добавим новый xml-файл с именем *rabit_animation.xml*;
4. Добавим в него код:
```xml
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="false" >
    <item android:drawable="@drawable/a1" android:duration="250" />
    <item android:drawable="@drawable/a2" android:duration="250" />
    <item android:drawable="@drawable/a3" android:duration="250" />
    <item android:drawable="@drawable/a4" android:duration="250" />
    <item android:drawable="@drawable/a5" android:duration="250" />
</animation-list>
```
5. Поместим компоненты *RelativeLayout* и  *ImageView* и установим разметки:
```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <ImageView android:id="@+id/animationView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/rabit_animation"
        android:adjustViewBounds="true" android:padding="40dip"/>
</RelativeLayout>
```
6. Добавить запуск анимации:
```java
setContentView(R.layout.activity_main);
ImageView img = (ImageView)findViewById(R.id.animationView);
img.setBackgroundResource(R.drawable.rabit_animation);
AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
frameAnimation.setOneShot(false);
frameAnimation.start();
```
7. Добавить небольшой дизайн и проверить работоспособность;
8. Составить отчёт.

## Файлы
* [Кастомные изображения для гифки](https://github.com/Stirven13/Laboratory-for-mobily-application/tree/Lab4/app/src/main/res/drawable) (`cat_walk_frame_1.png` - `cat_walk_frame_6.png`)
* [app/src/main/res/drawable/cat_walk_animation.xml](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab4/app/src/main/res/drawable/cat_walk_animation.xml)
* [app/src/main/res/layout/activity_main.xml](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab4/app/src/main/res/layout/activity_main.xml)

## Пример работы приложения
![image](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab4/example_gif_lab4.gif)

## Дополнительная информация
Из файла `activity_main.xml` необходимо было удалить строку `android:src="@drawable/rabit_animation"`, чтобы картинка отображалась корректно. Если не удалить, то поверх "гифки" будет отображаться первый кадр.
