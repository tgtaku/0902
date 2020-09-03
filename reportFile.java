package com.example.pdfview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class reportFile extends AppCompatActivity {
    public static ArrayList<Bitmap> bitmapReportPic;
    public static ArrayList<String> reportPic;
    public static String[] comment;
    String reportName;
    String reporter;
    public static ArrayList<String> date;
    String now;
    String projectId;
    String userId;
    String companyId;
    String place;
    int num;
    String updateReportInfo = "http://10.20.170.52/sample/update_report_information.php";
    String updateReportName = "http://10.20.170.52/sample/update_report_name.php";

    public GradientDrawable drawable1, drawable2;
    EditText[] editComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_file);

        //記載情報の取得
        //画像配列
        bitmapReportPic = new ArrayList<>();
        bitmapReportPic = CreateReportFile.bitmapReportArrayList;
        //写真名
        reportPic = new ArrayList<>();
        reportPic = getIntent().getStringArrayListExtra("picturesName");
        //写真の日付
        date = new ArrayList<>();
        date = getIntent().getStringArrayListExtra("date");
        //報告者
        reporter = getIntent().getStringExtra("reporter");
        //ユーザーID
        userId = MainActivity.userId;
        //プロジェクトID
        projectId = MyPage.projects_id;
        //会社ID
        companyId = MainActivity.companyId;
        //施工箇所名
        place = showPDF.title;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //現在の日時を取得。
        Date time = new Date(System.currentTimeMillis());
        //取得した日時データを「yyyyMMddHHmmss」形式に整形した文字列を生成。
        now = dateFormat.format(time);
        //タイトル
        reportName = now + "_" + place + "_" + reporter;
        //レイアウト数の整理
        num = bitmapReportPic.size();
        //コメント数の定義
        comment = new String[num];
        editComment = new EditText[num];
        /*
        System.out.println(companyId);
        System.out.println(projectId);
        System.out.println(userId);
        System.out.println(reporter);
        System.out.println(now);
        System.out.println(reportPic);
        System.out.println(reportName);
        System.out.println(bitmapReportPic);
        System.out.println(reportName);
*/
        //背景の定義①
        drawable1 = new GradientDrawable();
        drawable1.setStroke(3, Color.parseColor("#000000"));
        drawable1.setCornerRadius(3);
        drawable1.setColor(Color.parseColor("#FFE4C4"));

        //背景の定義②
        drawable2 = new GradientDrawable();
        drawable2.setStroke(3, Color.parseColor("#000000"));
        drawable2.setCornerRadius(3);

        reports rp = new reports();
        rp.execute(updateReportInfo, updateReportName);
    }

    private class reports extends AsyncTask<String, Void, String> {
        @Override
        public String doInBackground(String... params){
            String judge = "";
            String params0_url = params[0];
            String params1_url = params[1];
            HttpURLConnection con = null;
            //http接続のレスポンスデータとして取得するInputStreamオブジェクトを宣言（try外）
            InputStream is = null;
            //返却用の変数
            StringBuffer conResult;// = new StringBuffer();
            for(int i = 0; i < num; i++) {
                conResult = new StringBuffer();
                try {
                    comment[i] = "test";
                    //String project_information = params[0];
                    //String dates = "2020-05-13";
                    URL url = new URL(params0_url);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    OutputStream outputStream = con.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    //POSTデータの編集
                    //comment[i] = editComment[i].getText().toString();
                    String post_data = URLEncoder.encode("reports_name", "UTF-8") + "=" + URLEncoder.encode(reportName, "UTF-8") + "&" +
                            URLEncoder.encode("pictures_id", "UTF-8") + "=" + URLEncoder.encode(reportPic.get(i), "UTF-8") + "&" +
                            URLEncoder.encode("comment", "UTF-8") + "=" + URLEncoder.encode(comment[i], "UTF-8");
                    System.out.println(post_data);
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = con.getInputStream();
                    String encoding = con.getContentEncoding();
                    if (null == encoding) {
                        encoding = "UTF-8";
                    }
                    InputStreamReader inReader = new InputStreamReader(inputStream, encoding);
                    BufferedReader bufferedReader = new BufferedReader(inReader);
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        conResult.append(line);
                        line = bufferedReader.readLine();
                    }
                    System.out.println(conResult.toString());
                    bufferedReader.close();
                    inputStream.close();
                    con.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            conResult = new StringBuffer();
            try {

                URL url = new URL(params1_url);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);
                OutputStream outputStream = con.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //POSTデータの編集
                //comment[i] = editComment[i].getText().toString();
                String post_data = URLEncoder.encode("reports_name", "UTF-8") + "=" + URLEncoder.encode(reportName, "UTF-8") + "&" +
                        URLEncoder.encode("users_id", "UTF-8") + "=" + URLEncoder.encode(userId, "UTF-8") + "&" +
                        URLEncoder.encode("projects_id", "UTF-8") + "=" + URLEncoder.encode(projectId, "UTF-8") + "&" +
                        URLEncoder.encode("reporter", "UTF-8") + "=" + URLEncoder.encode(reporter, "UTF-8") + "&" +
                        URLEncoder.encode("company_id", "UTF-8") + "=" + URLEncoder.encode(companyId, "UTF-8");
                System.out.println(post_data);
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = con.getInputStream();
                String encoding = con.getContentEncoding();
                if (null == encoding) {
                    encoding = "UTF-8";
                }
                InputStreamReader inReader = new InputStreamReader(inputStream, encoding);
                BufferedReader bufferedReader = new BufferedReader(inReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    conResult.append(line);
                    line = bufferedReader.readLine();
                }
                System.out.println(conResult.toString());
                bufferedReader.close();
                inputStream.close();
                con.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return judge;
        }

        @Override
        public void onPostExecute(String string) {
            System.out.println("--------------");
            finish();
        }


    }
}
