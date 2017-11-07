package com.example.wiesiek.beatroullete2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Wiesiek on 2017-08-10.
 */

public class ButtonList extends AppCompatActivity implements View.OnClickListener {
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15;
    List<Integer> listWheel1;
    ManagerDatabase managerDatabase;

    ImageView imageView1;
    ImageView imageViewUp;

    int [] tablica_losowa;
    int [] array_photo = {R.mipmap.q1,R.mipmap.q2,R.mipmap.q3,R.mipmap.q4,R.mipmap.q5,R.mipmap.q6,R.mipmap.q7,R.mipmap.q8,R.mipmap.q9,R.mipmap.q10,R.mipmap.q11,R.mipmap.q12,R.mipmap.q13,R.mipmap.q14,R.mipmap.q15,R.mipmap.q16,R.mipmap.q17,R.mipmap.q18,R.mipmap.q19};
//    int [] array_phot_instagram = {}; ,R.mipmap.q13,R.mipmap.q14,R.mipmap.q15,R.mipmap.q16,R.mipmap.q17,R.mipmap.q18,R.mipmap.q19,R.mipmap.q20

    Random random;
    int los;
    int overAllCount = 0;
    TreeMap<Integer, Integer> repetitions;

    LinearLayout linearLayout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.button_list);
        super.onCreate(savedInstanceState);

//        setTablica_losowa();


        imageView1 = (ImageView) findViewById(R.id.imageId);
        imageViewUp = (ImageView) findViewById(R.id.imageview_up_id);

        imageView1.setOnClickListener(this);
        imageViewUp.setOnClickListener(this);


        button1 = (Button) findViewById(R.id.kolo1);
        button2 = (Button) findViewById(R.id.kolo2);
        button3 = (Button) findViewById(R.id.kolo3);
        button4 = (Button) findViewById(R.id.kolo4);
        button5 = (Button) findViewById(R.id.kolo5);
        button6 = (Button) findViewById(R.id.kolo6);
        button7 = (Button) findViewById(R.id.kolo7);
        button8 = (Button) findViewById(R.id.kolo8);
        button9 = (Button) findViewById(R.id.kolo9);
        button10 = (Button) findViewById(R.id.kolo10);
        button11 = (Button) findViewById(R.id.kolo11);
        button12 = (Button) findViewById(R.id.kolo12);
        button13 = (Button) findViewById(R.id.kolo13);
        button14 = (Button) findViewById(R.id.kolo14);
        button15 = (Button) findViewById(R.id.kolo15);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
        button15.setOnClickListener(this);

        linearLayout = (LinearLayout) findViewById(R.id.linearEditText);
        sharedPreferences = getSharedPreferences("mainfile", MODE_PRIVATE);
        String button1Str = sharedPreferences.getString("name1", "");
        String button2Str = sharedPreferences.getString("name2", "");
        String button3Str = sharedPreferences.getString("name3", "");
        String button4Str = sharedPreferences.getString("name4", "");
        String button5Str = sharedPreferences.getString("name5", "");
        String button6Str = sharedPreferences.getString("name6", "");
        String button7Str = sharedPreferences.getString("name7", "");
        String button8Str = sharedPreferences.getString("name8", "");
        String button9Str = sharedPreferences.getString("name9", "");
        String button10Str = sharedPreferences.getString("name10", "");
        String button11Str = sharedPreferences.getString("name11", "");
        String button12Str = sharedPreferences.getString("name12", "");
        String button13Str = sharedPreferences.getString("name13", "");
        String button14Str = sharedPreferences.getString("name14", "");
        String button15Str = sharedPreferences.getString("name15", "");

        button1.setText(button1Str);
        button2.setText(button2Str);
        button3.setText(button3Str);
        button4.setText(button4Str);
        button5.setText(button5Str);
        button6.setText(button6Str);
        button7.setText(button7Str);
        button8.setText(button8Str);
        button9.setText(button9Str);
        button10.setText(button10Str);
        button11.setText(button11Str);
        button12.setText(button12Str);
        button13.setText(button13Str);
        button14.setText(button14Str);
        button15.setText(button15Str);

        setRandomPhotoInImageView();
        LongClicki();
    }

//    public void setTablica_losowa(){
//        Random random = new Random();
//        int a = random.nextInt(2);
//
//        if (a ==0){
//            tablica_losowa = array_phot_instagram.clone();
//        } else if (a==1){
//            tablica_losowa = array_photo.clone();
//
//        }
//        Log.d("printt", "liczba losowa to "+a +"  "+ tablica_losowa.length);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.button_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sendAllId) {
            SendingEmail();
        }
        return super.onOptionsItemSelected(item);
    }

    public String Calculations(String kolumna, Button button) {
        managerDatabase = new ManagerDatabase(this);
        //zwrot pierwszej kolumny
        StringBuilder sb = new StringBuilder();
        String allDatabase = managerDatabase.ReturnAllDataAsAString(kolumna);
        if (allDatabase.equals("") || allDatabase == null) {
            allDatabase = " ";
        }
        listWheel1 = new ArrayList<>();
        listWheel1 = TransformEditTextToListOfIntegersButtonClass(allDatabase);
        repetitions = CountingNumbersReceiveHashMap(listWheel1);
        String wheel1 = CreatingStringFromRepeatedNumbers(repetitions, button);
        sb.append(wheel1 + "\n");
        return sb.toString();
    }

    public String SendAllDataToEmail() {
        StringBuilder sb = new StringBuilder();
        String wheel1 = Calculations(ManagerDatabase.DatabaseClass.Col, button1);
        String wheel2 = Calculations(ManagerDatabase.DatabaseClass.Col2, button2);
        String wheel3 = Calculations(ManagerDatabase.DatabaseClass.Col3, button3);
        String wheel4 = Calculations(ManagerDatabase.DatabaseClass.Col4, button4);
        String wheel5 = Calculations(ManagerDatabase.DatabaseClass.Col5, button5);
        String wheel6 = Calculations(ManagerDatabase.DatabaseClass.Col6, button6);
        String wheel7 = Calculations(ManagerDatabase.DatabaseClass.Col7, button7);
        String wheel8 = Calculations(ManagerDatabase.DatabaseClass.Col8, button8);
        String wheel9 = Calculations(ManagerDatabase.DatabaseClass.Col9, button9);
        String wheel10 = Calculations(ManagerDatabase.DatabaseClass.Col10, button10);
        String wheel11 = Calculations(ManagerDatabase.DatabaseClass.Col11, button11);
        String wheel12 = Calculations(ManagerDatabase.DatabaseClass.Col12, button12);
        String wheel13 = Calculations(ManagerDatabase.DatabaseClass.Col13, button13);
        String wheel14 = Calculations(ManagerDatabase.DatabaseClass.Col14, button14);
        String wheel15 = Calculations(ManagerDatabase.DatabaseClass.Col15, button15);

//        Log.d("wheel1", wheel2);
        sb.append(wheel1);
        sb.append(wheel2);
        sb.append(wheel3);
        sb.append(wheel4);
        sb.append(wheel5);
        sb.append(wheel6);
        sb.append(wheel7);
        sb.append(wheel8);
        sb.append(wheel9);
        sb.append(wheel10);
        sb.append(wheel11);
        sb.append(wheel12);
        sb.append(wheel13);
        sb.append(wheel14);
        sb.append(wheel15);

        Log.d("all", sb.toString());
        return sb.toString();


    }

    public List<Integer> TransformEditTextToListOfIntegersButtonClass(String dataStr) {
        List<Integer> listOfInt = new ArrayList<>();
        int num_i = 0;
        int num_j = 0;
        int numberRecovered = 0;

        for (int i = 0; i < dataStr.length() - 1; i++) {
            if (dataStr.charAt(i) == ' ') {
                num_i = i;

                for (int j = i + 1; j < dataStr.length(); j++) {

                    if (dataStr.charAt(j) == ' ') {
                        num_j = j;
                        Log.d("num", "" + num_i + " " + num_j);
                        String numRecovered = dataStr.substring(num_i + 1, num_j);
                        numberRecovered = Integer.parseInt(numRecovered);
                        listOfInt.add(numberRecovered);
                        Log.d("num", "" + numRecovered);
                        break;
                    }
                }
            }
        }

//        listOfInt.sort();
        return listOfInt;
    }

    public TreeMap<Integer, Integer> CountingNumbersReceiveHashMap(List<Integer> listOfInt) {
        TreeMap<Integer, Integer> repetitions1 = new TreeMap<Integer, Integer>();
//        repetitions1 = new HashMap<Integer, Integer>();
        overAllCount = 0;

        Collections.sort(listOfInt);
        for (int i = 0; i < listOfInt.size(); i++) {
            Log.d("ooo", "" + listOfInt.get(i));
        }

        for (int i = 0; i < listOfInt.size(); ++i) {
            int item = listOfInt.get(i);
            overAllCount += 1;

            if (repetitions1.containsKey(item))
                repetitions1.put(item, repetitions1.get(item) + 1);
            else
                repetitions1.put(item, 1);
        }

//        repetitions1 = MapUtil.sortByValue( repetitions1 );
        return repetitions1;
//        repeatedNumStr = CreatingStringFromRepeatedNumbers(repetitions);
//        Log.d("sb", ""+repeatedNumStr);
//        return repeatedNumStr;
    }

    public String CreatingStringFromRepeatedNumbers(TreeMap<Integer, Integer> repetitions, Button button) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Integer> e : repetitions.entrySet()) {
            if (e.getValue() > -1) {

                sb.append("\n");
                sb.append(e.getKey());
                sb.append(" ");
                sb.append(e.getValue());
            }

        }
        sb.append("\n");
        String repeatedNumbersStr = sb.toString();
        String overAllCountStr = String.valueOf(overAllCount);
        repeatedNumbersStr = button.getText().toString() + " Ilosc liczb to " + overAllCountStr + "\n" + repeatedNumbersStr;

        return repeatedNumbersStr;
    }

    public void SendingEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"janik.w777@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Numbers from apk");
        String summaries = SendAllDataToEmail();
        i.putExtra(Intent.EXTRA_TEXT, summaries);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
        }
        //IncreseFontSize();
    }


    @Override
    protected void onStop() {
        super.onStop();
//        sharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name1", button1.getText().toString());
        editor.putString("name2", button2.getText().toString());
        editor.putString("name3", button3.getText().toString());
        editor.putString("name4", button4.getText().toString());
        editor.putString("name5", button5.getText().toString());
        editor.putString("name6", button6.getText().toString());
        editor.putString("name7", button7.getText().toString());
        editor.putString("name8", button8.getText().toString());
        editor.putString("name9", button9.getText().toString());
        editor.putString("name10", button10.getText().toString());
        editor.putString("name11", button11.getText().toString());
        editor.putString("name12", button12.getText().toString());
        editor.putString("name13", button13.getText().toString());
        editor.putString("name14", button14.getText().toString());
        editor.putString("name15", button15.getText().toString());

        editor.commit();

//        setTablica_losowa();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.kolo1) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("wheel1", button1.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo2) {
            Intent intent = new Intent(this, Wheel2.class);
            intent.putExtra("wheel2", button2.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo3) {
            Intent intent = new Intent(this, Wheel3.class);
//            linearLayout.setBackgroundColor(Color.BLUE);
            intent.putExtra("wheel3", button3.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo4) {
            Intent intent = new Intent(this, Wheel4.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel4", button4.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo5) {
            Intent intent = new Intent(this, Wheel5.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel5", button5.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo6) {
            Intent intent = new Intent(this, Wheel6.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel6", button6.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo7) {
            Intent intent = new Intent(this, Wheel7.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel7", button7.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo8) {
            Intent intent = new Intent(this, Wheel8.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel8", button8.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo9) {
            Intent intent = new Intent(this, Wheel9.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel9", button9.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo10) {
            Intent intent = new Intent(this, Wheel10.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel10", button10.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo11) {
            Intent intent = new Intent(this, Wheel11.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel11", button11.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo12) {
            Intent intent = new Intent(this, Wheel12.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel12", button12.getText().toString());
            startActivity(intent);
        }else if (v.getId() == R.id.kolo13) {
            Intent intent = new Intent(this, Wheel13.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel13", button13.getText().toString());
            startActivity(intent);
        } else if (v.getId() == R.id.kolo14) {
            Intent intent = new Intent(this, Wheel14.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel14", button14.getText().toString());
            startActivity(intent);
        }  else if (v.getId() == R.id.kolo15) {
            Intent intent = new Intent(this, Wheel15.class);
//            linearLayout.setBackgroundColor(Color.GREEN);
            intent.putExtra("wheel15", button15.getText().toString());
            startActivity(intent);
        }
        else if (v.getId()==R.id.imageId){

            setRandomPhotoInImageView();
        }
        else if (v.getId()==R.id.imageview_up_id){
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.facebook_sound);
            mp.start();
        }
    }

    private void setRandomPhotoInImageView(){
        random = new Random();
        los = random.nextInt(array_photo.length);
        imageView1.setImageResource(array_photo[los]);
    }

    private void showDialog(final Button btn) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("input text");
        View view = LayoutInflater.from(this).inflate(R.layout.change_button_text, null);
        final EditText edit_dialog = (EditText) view.findViewById(R.id.editTextButtonChange);
//        edit_dialog.setText(str);
        edit_dialog.setText(btn.getText().toString());
        edit_dialog.setSelection(btn.getText().toString().length());

        builder.setView(view);
        builder.setNegativeButton("cancel", null);
        builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn.setText(edit_dialog.getText().toString());
            }
        });
        builder.show();
//        edit_dialog.performClick();
//        edit_dialog.setPressed(true);
//        imm.sh
//        Keyboard keyboard = i
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(edit_dialog,InputMethodManager.SHOW_IMPLICIT);
//        edit_dialog.setRawInputType(InputType.TYPE_CLASS_TEXT);
//        edit_dialog.setFocusable(true);
//        edit_dialog.setFocusableInTouchMode(true);
    }

    public void LongClicki() {
        button1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button1);
                return true;
            }
        });
        button2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button2);
                return true;
            }
        });
        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button3);
                return false;
            }
        });
        button4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button4);
                return false;
            }
        });
        button5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button5);
                return false;
            }
        });
        button6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button6);
                return false;
            }
        });
        button7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button7);
                return false;
            }
        });
        button8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button8);
                return false;
            }
        });
        button9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button9);
                return false;
            }
        });
        button10.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button10);
                return false;
            }
        });
        button11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button11);
                return false;
            }
        });
        button12.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button12);
                return false;
            }
        });
        button13.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button13);
                return false;
            }
        });

        button14.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button14);
                return false;
            }
        });

        button15.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(button15);
                return false;
            }
        });
    }
}
