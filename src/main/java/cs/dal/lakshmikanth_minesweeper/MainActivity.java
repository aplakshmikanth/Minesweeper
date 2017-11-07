package cs.dal.lakshmikanth_minesweeper;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void beginner(final View viewButton)
    {
        Intent intent = new Intent(this, Functionality.class);
        intent.putExtra("selection", "beginner");
        startActivity(intent);

    }

    public void intermediate(final View viewButton)
    {
        Intent intent = new Intent(this, Functionality.class);
        intent.putExtra("selection", "intermediate");
        startActivity(intent);

    }

    public void expert(final View viewButton)
    {
        Intent intent = new Intent(this, Functionality.class);
        intent.putExtra("selection", "expert");
        startActivity(intent);

    }

}
