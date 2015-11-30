package main.java.com.caao;

import android.app.import android.content.Intent;
import android.os.Bundle;

/**
 * Created by z1 on 11/30/15.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, com.caao.MainActivity.class);
        startActivity(intent);
        finish();
    }
}