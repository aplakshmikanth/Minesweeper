package cs.dal.lakshmikanth_minesweeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashSet;
import java.util.Random;

public class Functionality extends AppCompatActivity {

    String gameLevel;
    Random randNum = new Random();
    int[] cellValues=new int[81];
    GridView gView;
    CustGridAdapter gAdapter;
    TextView tv;
    ImageView iv;
    int count = 0;
     MediaPlayer mPlayer;
    int randomNumber=0;
    int clickCount=0;
    HashSet<Integer> cellPos=new HashSet<Integer>();
    Chronometer elapseTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functionality);

        mPlayer = MediaPlayer.create(this, R.raw.gamemusic);
        gameLevel = getIntent().getStringExtra("selection");
        startingfun();

        gView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                iv = (ImageView) view.findViewById(R.id.imageView);


                if(cellValues[position]==99)
                {
                    iv.setImageResource(R.mipmap.b);
                    mPlayer.stop();
                    elapseTime.stop();

                    MediaPlayer mPlayer = MediaPlayer.create(Functionality.this, R.raw.bombmusic);
                    mPlayer.start();

                    gView = (GridView) findViewById(R.id.gridBlock);
                    gAdapter = new CustGridAdapter(Functionality.this, cellValues,true);
                    gView.setAdapter(gAdapter);

                }

                else

                {
                           cellReplication(position);


                }
            }

            });



        gView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (!(clickCount == 81)) {
                    iv = (ImageView) view.findViewById(R.id.imageView);
                    iv.setImageResource(R.mipmap.flag);
                    return true;
                }

                AlertDialog.Builder builderFlag = new AlertDialog.Builder(view.getContext());

                builderFlag.setTitle("You are not allowed to flag all block!!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })

                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return false;
            }
        });

    }

    /*
    Determine level selected, starts music, starst timer and populates
    required number of mines in random grid cells.
     */

    public void startingfun()
    {
        mPlayer = MediaPlayer.create(this, R.raw.gamemusic);

        mPlayer.start();

        elapseTime=(Chronometer)findViewById(R.id.chronometer);

        elapseTime.setBase(SystemClock.elapsedRealtime());
        elapseTime.start();

        clickCount=0;
        cellValues=new int[81];
        for(int v=0;v<81;v++)
        {
            cellValues[v]=v;
        }


        switch (gameLevel) {
            case "beginner":
                HashSet<Integer> uniqueRandNum=new HashSet<Integer>();

                for (int i = 0; i < 8; i++) {

                    randomNumber = randNum.nextInt(80);
                    if(uniqueRandNum.add(randomNumber)) {

                        cellValues[randomNumber] = Integer.parseInt("99");
                    }
                    else
                    {
                        --i;
                    }
                }
                break;
            case "intermediate":

                HashSet<Integer> uniqueRandNumb=new HashSet<Integer>();

                for (int j = 0; j < 24; j++) {
                    randomNumber = randNum.nextInt(80);
                    if(uniqueRandNumb.add(randomNumber)) {

                        cellValues[randomNumber] = Integer.parseInt("99");
                    }
                    else
                    {
                        --j;
                    }
                }
                break;
            case "expert":

                HashSet<Integer> uniqueRandNumber=new HashSet<Integer>();

                for (int k = 0; k < 40; k++) {

                    randomNumber = randNum.nextInt(80);
                    if(uniqueRandNumber.add(randomNumber)) {

                        cellValues[randomNumber] = Integer.parseInt("99");
                    }
                    else
                    {
                        --k;
                    }
                }
                break;

        }

        gView = (GridView) this.findViewById(R.id.gridBlock);
        gAdapter = new CustGridAdapter(this, cellValues,false);
        gView.setAdapter(gAdapter);
    }

    /*
    Determines number of surrounding mines and
    displays the count in clicked cell.
    If there are no mines surrounding,
    opens up cell until mines are found in their adjacent cells.
     */


    public void cellReplication(int pos) {

        HashSet<Integer> traverseCell=new HashSet<Integer>();
        count=0;

        //left side traverse
        if (!((pos % 9) == 0))
        {
            if(cellValues[pos-1]==99)
            {
                count++;
                if(!cellPos.contains(pos-1)) {
                    traverseCell.add(pos - 1);
                }
            }

            //top traverse
            if(pos>8)
            {
                if(cellValues[pos-10]==99)
                {
                    count++;
                    if(!cellPos.contains(pos-10)) {

                        traverseCell.add(pos - 10);
                    }

                }
                if(cellValues[pos-9]==99)
                {
                    count++;
                    if(!cellPos.contains(pos-9)) {

                        traverseCell.add(pos - 9);
                    }

                }

            }

            //bottom traverse

            if(pos<72)
            {
                if(cellValues[pos+8]==99)
                {
                    count++;
                    if(!cellPos.contains(pos+8)) {

                        traverseCell.add(pos + 8);

                    }
                }

                if(cellValues[pos+9]==99)
                {
                    count++;
                    if(!cellPos.contains(pos+9)) {

                        traverseCell.add(pos + 9);
                    }
                }
            }
        }

        //Right side traverse

        if(!(((pos+1)%9)==0))
        {
            if(cellValues[pos+1]==99)
            {
                count++;
                if(!cellPos.contains(pos+1)) {

                    traverseCell.add(pos + 1);
                }
            }

            // top traverse
            if(pos>8)
            {
                if(cellValues[pos-8]==99)
                {
                    count++;
                    if(!cellPos.contains(pos-8)) {

                        traverseCell.add(pos - 8);

                    }
                }
            }

            //bottom traverse

            if(pos<72)
            {
                if(cellValues[pos+10]==99)
                {
                    count++;
                    if(!cellPos.contains(pos+10)) {

                        traverseCell.add(pos + 10);
                    }

                }
            }

        }

        clickCount++;
        switch (count)
        {

            case 1: iv.setImageResource(R.mipmap.n1);
                cellPos.add(pos);
                return;

            case 2: iv.setImageResource(R.mipmap.n2);
                cellPos.add(pos);
                return;

            case 3: iv.setImageResource(R.mipmap.n3);
                cellPos.add(pos);
                return;

            case 4: iv.setImageResource(R.mipmap.n4);
                cellPos.add(pos);
                return;

            case 5: iv.setImageResource(R.mipmap.n5);
                cellPos.add(pos);
                return;

            case 6: iv.setImageResource(R.mipmap.n6);
                cellPos.add(pos);
                return;

            case 7: iv.setImageResource(R.mipmap.n7);
                cellPos.add(pos);
                return;

            case 8: iv.setImageResource(R.mipmap.n8);
                cellPos.add(pos);
                return;


            case 0: iv.setImageResource(R.mipmap.empty);
                cellPos.add(pos);

                for(Integer cell:traverseCell)
                {
                        cellReplication(cell);
                }
                return;
        }

    }


    public void reset(final View viewButton)
    {

        startingfun();
    }

    public void mainMenu(final View viewButton)
    {

        Intent intent = new Intent(Functionality.this, MainActivity.class);
        startActivity(intent);
        elapseTime.stop();
    }


}
