# Лабораторная работа № 5

## Тема:
Работа с мультимедиа.

## Цель:
Разработать свой видео **или** аудио проигрыватель.

## Задания:
1. Разработать мобильное приложение для воспроизведения звука или видео;

## Порядок выполнения:
1. Создадим новый проект;
2. Добавить видео/аудио файл в проект в папку *res/raw*;
3. Добавить 3 кнопки для управления и создадим для них обработчики событий;
4. **Добавим в них код (видео):**
5. 
```java
public void onStartClick(View view)
{
    MyvideoPlayer.start();
}
public void onPauseClick(View view)
{
    MyvideoPlayer.pause();
}
public void onStopClick(View view)
{
    MyvideoPlayer.stopPlayback();
    MyvideoPlayer.resume();
}
```
6. Добавить переменные и ссылку на видео;
7. 
```java
public class MainActivity extends AppCompatActivity
{
    VideoView MyvideoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyvideoPlayer =  (VideoView)findViewById(R.id.videoView);
        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.systemf);
        MyvideoPlayer.setVideoURI(myVideoUri);
        MediaController mediaController = new MediaController(this);
        MyvideoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(MyvideoPlayer);
    }
}
```
8. Здесь `systemf` название видеофайла;
9. Запустить демонстрацию работы видеозаписи;
10. **Добавим в них код (аудио):**
11.
```java
public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {
    MediaPlayer mediaPlayer;
    Button startButton, pauseButton, stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
        startButton = (Button) findViewById(R.id.buttonStart);
        pauseButton = (Button) findViewById(R.id.buttonPause);
        stopButton = (Button) findViewById(R.id.buttonStop);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
    }

    private void stopPlay(){
        mediaPlayer.stop();
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
            startButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onStartClick(View view)
    {
        mediaPlayer.start();
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
    }

    public void onPauseClick(View view)
    {
        mediaPlayer.pause();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
    }

    public void onStopClick(View view)
    {
        stopPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            stopPlay();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
    }
}
```
12. Здесь обработчики событий кнопок - `onStartClick(View view)`, `onPauseClick(View view)`, `onStopClick(View view)`;
13. Здесь `music` название аудиофайла;
14. Запустить демонстрацию работы аудиозаписи;
15. Составить отчёт.

## Файлы
* [app/src/main/res/raw/random_video.mp4](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab5/app/src/main/res/raw/random_video.mp4)
* [app/src/main/res/layout/activity_main.xml](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab5/app/src/main/res/layout/activity_main.xml)
* [app/src/main/java/com/badlogic/sokolovtask_lab5/MainActivity.java](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab5/app/src/main/java/com/badlogic/sokolovtask_lab5/MainActivity.java)

## Пример работы
![image](https://github.com/Stirven13/Laboratory-for-mobily-application/blob/Lab5/example_gif_lab5.gif)

## Дополнительная информация
В моей работе одновременно есть видео и аудио проигрыватель, однако необходимо сделать [одно из двух](#Цель).
