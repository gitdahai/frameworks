package lib.framework.hollo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import lib.framework.hollo.baidumap.AbstractSupportMapFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mapview_container, new AbstractSupportMapFragment());
        ft.commit();
    }


}
