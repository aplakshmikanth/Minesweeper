package cs.dal.lakshmikanth_minesweeper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


/**
 * Created by lakshmikanthgowda on 2017-10-11.
 */

public class CustGridAdapter extends BaseAdapter {

    private Context context;
    private int[] cellVal;
    LayoutInflater inflater;
    boolean flag;


    public CustGridAdapter(Context context, int[] cellVal,boolean flag ){
        this.context = context;
        this.cellVal = cellVal;
        this.flag=flag;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return cellVal.length;
    }

    @Override
    public Object getItem(int position) {
        return cellVal[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = inflater.inflate(R.layout.cell, null);

        }
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageResource(R.mipmap.cellimage);

       if(flag)
       {
           if(cellVal[position]==99)
           {
               iv.setImageResource(R.mipmap.b);

           }
       }
        return view;
    }
}