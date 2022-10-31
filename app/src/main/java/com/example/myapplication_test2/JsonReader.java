//package com.example.myapplication_test2;
//import android.os.AsyncTask;
//
//
//public class jsonReader extends AsyncTask<String, Integer, Integer> {
//    @Override
//    protected Integer doInBackground(String... parameter) {
//        int myProgress = 0;
//        // [... Выполните задачу в фоновом режиме, обновите переменную myProgress...]
//        publishProgress(myProgress);
//        // [... Продолжение выполнения фоновой задачи ...]
//        // Верните значение, ранее переданное в метод onPostExecute
//        return myProgress;
//    }
//
//    @Override
//    protected void onProgressUpdate(Integer... progress) {
//        // [... Обновите индикатор хода выполнения, уведомления или другой
//        // элемент пользовательского интерфейса ...]
//    }
//
//    protected void onPostExecute(Integer... result) {
//        // [... Сообщите о результате через обновление пользовательского
//        // интерфейса, диалоговое окно или уведомление ...]
//    }
//}
