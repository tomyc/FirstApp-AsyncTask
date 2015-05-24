package net.cieplak.firstapp_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class AsyncTaskMainActivity extends ActionBarActivity {

    private TextView textView;
    private TextView textKliki;
    private ProgressBar progressBar;
    private int kliki;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_main);
        textView=(TextView)findViewById(R.id.output);
        textKliki=(TextView)findViewById(R.id.textView);
        kliki=0;
        textKliki.setText(""+kliki);
    }

    public void onClick(View view) {
        new LongOperation().execute("");

    }

    public void onKliki(View view){
        kliki=kliki+1;
        textKliki.setText(""+kliki);
    }

    private class LongOperation extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            for (int i = 1; i <= 100; i++) {
                //progressBar.setProgress(i);
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "Wykonane";
        }

        @Override
        protected void onPostExecute(String result) {
            //TextView txt = (TextView) findViewById(R.id.output);
            textView.setText(result); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {
            progressBar = (ProgressBar)findViewById(R.id.progressBar);
            textView.setText("Rozpoczyna...");
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText("Działa..."+values[0]);
            //setProgress(10);
            progressBar.setProgress(values[0]);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_async_task_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
