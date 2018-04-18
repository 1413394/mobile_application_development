package com.example.huutai.currencyconverter;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    class Current_currency
    {
        String from_cur;
        String to_cur;
        Current_currency(String f_cur,Double f_value,String t_cur,Double t_value)
        {
            from_cur=f_cur;
            to_cur=t_cur;
        }
    }
    ArrayList<Currency> cur_list=new ArrayList<Currency>();
    Current_currency cc=new Current_currency("USD",0.0,"VND",0.0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        registerSpinner(R.id.spinner_1);
        registerSpinner(R.id.spinner_2);
        registerEditText(R.id.from_edit);
        registerEditText(R.id.to_edit);
        registerButton(R.id.btn_0);
        registerButton(R.id.btn_1);
        registerButton(R.id.btn_2);
        registerButton(R.id.btn_3);
        registerButton(R.id.btn_4);
        registerButton(R.id.btn_5);
        registerButton(R.id.btn_6);
        registerButton(R.id.btn_7);
        registerButton(R.id.btn_8);
        registerButton(R.id.btn_9);
        registerButton(R.id.btn_dot);
        registerButton(R.id.btn_del);
        registerImageButton(R.id.convert_button);

        cur_list.add(new Currency("USD",1.0,"$"));
        cur_list.add(new Currency("EUR",0.810663,"€"));
        cur_list.add(new Currency("GBP",0.702370,"£"));
        cur_list.add(new Currency("AUD",1.28742,"$"));
        cur_list.add(new Currency("JPY",107.325,"¥"));
        cur_list.add(new Currency("CNY",6.27124,"¥"));
        cur_list.add(new Currency("CAD",1.26025,"$"));
        cur_list.add(new Currency("RUB",62.1106,"\u20BD"));
        cur_list.add(new Currency("SGD",1.31231,"$"));
        cur_list.add(new Currency("VND",22793.87,"₫"));

    }
    public void registerSpinner(final int resid)
    {
        Spinner spinner = (Spinner) findViewById(resid);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currencies_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                           int pos, long id) {
                String choice=parent.getItemAtPosition(pos).toString();
                Resources res = getResources();
                String x=res.getResourceEntryName(parent.getId());
                if(x.equals("spinner_1"))
                {
                    cc.from_cur=choice;

                }
                else if(x.equals("spinner_2"))
                {
                    cc.to_cur=choice;

                }
                if(cc.from_cur!=null && cc.to_cur!=null)
                    check_conversion();
                //TextView spinner_item_text = (TextView) view;
                //spinner_item_text.setText(choice);
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        if(resid==R.id.spinner_1)
            spinner.setSelection(adapter.getPosition("USD"));
        else if (resid==R.id.spinner_2)
            spinner.setSelection(adapter.getPosition("VND"));
    }
    public void registerEditText(final int resid) {
        // Find the EditText 'resid'
        EditText edittext = (EditText) findViewById(resid);
        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override public void onFocusChange(View v, boolean hasFocus) {
                if( v!=null) ((InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( v!=null) ((InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
                EditText edit_text = (EditText) v;
                String data = edit_text.getText().toString();

            }
        });
        edittext.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
            }

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }
        });
    }
    public void registerButton(final int resid)
    {
        Button btn = (Button) findViewById(resid);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                Resources res = getResources();
                String id= res.getResourceEntryName(v.getId());
                EditText from_edittext = (EditText) findViewById(R.id.from_edit);
                EditText to_edittext = (EditText) findViewById(R.id.to_edit);
                if(from_edittext.hasFocus()) {
                    textChange(from_edittext,id);
                    //cc.from_value=Double.parseDouble(from_edittext.getText().toString());
                }
                else if(to_edittext.hasFocus())
                {
                    textChange(to_edittext,id);
                    //cc.to_value=Double.parseDouble(from_edittext.getText().toString());
                }


            }
        });
    }

    public void textChange(EditText editText,String btn_id)
    {
        int start = Math.max(editText.getSelectionStart(), 0);
        int end = Math.max(editText.getSelectionEnd(), 0);
        if(start!=0) {
            switch (btn_id) {
                case "btn_0": {
                    String textToInsert = "0";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_1": {
                    String textToInsert = "1";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_2": {
                    String textToInsert = "2";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_3": {
                    String textToInsert = "3";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_4": {
                    String textToInsert = "4";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_5": {
                    String textToInsert = "5";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_6": {
                    String textToInsert = "6";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_7": {
                    String textToInsert = "7";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_8": {
                    String textToInsert = "8";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_9": {
                    String textToInsert = "9";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_dot": {
                    String textToInsert = ".";
                    editText.getText().replace(Math.min(start, end), Math.max(start, end),
                            textToInsert, 0, textToInsert.length());
                    break;
                }
                case "btn_del": {
                    if(start==end) {
                        if(start!=1) {
                            start--;
                            String textToInsert = "";
                            editText.getText().replace(Math.min(start, end), Math.max(start, end),
                                    textToInsert, 0, textToInsert.length());
                        }
                    }
                    else
                    {
                        String textToInsert = "";
                        editText.getText().replace(Math.min(start, end), Math.max(start, end),
                                textToInsert, 0, textToInsert.length());
                    }
                    break;
                }
            }
        }

    }
    public void registerImageButton(final int resid)
    {
        ImageButton btn = (ImageButton) findViewById(resid);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton btn = (ImageButton) v;
                check_conversion();
            }
        });
    }
    public void check_conversion()
    {
        EditText from_edittext = (EditText) findViewById(R.id.from_edit);
        EditText to_edittext = (EditText) findViewById(R.id.to_edit);
        String from_str=from_edittext.getText().toString();
        String to_str=to_edittext.getText().toString();
        if(from_str.length()>1 &&to_str.length()>1 &&countOccurrences(from_str,'.')<2&&countOccurrences(to_str,'.')<2) {
            String from_first_char=from_str.substring(0, 1);
            String to_first_char=to_str.substring(0, 1);
            from_str = from_str.substring(1, from_str.length());
            to_str = to_str.substring(1, to_str.length());
            if (from_str.matches("[0-9.]+") && to_str.matches("[0-9.]+") &&from_first_char.matches("[0-9]+")==false&&to_first_char.matches("[0-9]+")==false) {
                double from_d = Double.parseDouble(from_str);
                double to_d = Double.parseDouble(to_str);
                double rateToDollars = 0;
                double rateToTarget = 0;
                String from_symbol = "";
                String to_symbol = "";
                for (int i = 0; i < cur_list.size(); i++) {
                    if (cur_list.get(i).name.equals(cc.from_cur)) {
                        rateToDollars = cur_list.get(i).rate;
                        from_symbol = cur_list.get(i).symbol;
                    }
                    if (cur_list.get(i).name.equals(cc.to_cur)) {
                        rateToTarget = cur_list.get(i).rate;
                        to_symbol = cur_list.get(i).symbol;
                    }
                }
                double targetAmount = (from_d / rateToDollars) * rateToTarget;
                DecimalFormat df = new DecimalFormat("#.#####");
                //df.setMaximumFractionDigits(340);
                from_edittext.setText(from_symbol + from_str);
                to_edittext.setText(to_symbol + df.format(targetAmount));

            }
        }

    }
    class Currency
    {
        String name;
        Double rate;
        String symbol;
        Currency()
        {
            name="";
            rate=0.0;
            symbol="";
        }
        Currency(String c_name,Double c_rate,String c_symbol)
        {
            name=c_name;
            rate=c_rate;
            symbol=c_symbol;
        }
    }
    private int countOccurrences(String haystack, char needle)
    {
        int count = 0;
        for (int i=0; i < haystack.length(); i++)
        {
            if (haystack.charAt(i) == needle)
            {
                count++;
            }
        }
        return count;
    }
}
