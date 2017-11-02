package com.example.wiesiek.beatroullete2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Wiesiek on 2017-08-18.
 */


public class BaseClass extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    TextView textView;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,bs,bd, b0;
    ManagerDatabase managerDatabase;
    List<Integer> listOfInteger;
    TreeMap<Integer, Integer> repetitions;
    String repeatedNumStr ="";
    int overAllCount=0;
    String countedNumbersStr="";
    //może stworzyć klasę nową i zrobić instancję tej klasy i metodę która zwraca tablicę
    int [] array_photo = { R.mipmap.choose,R.mipmap.face1,R.mipmap.face2,R.mipmap.face3,R.mipmap.face4,R.mipmap.face5,R.mipmap.face6,R.mipmap.face7,R.mipmap.face8,R.mipmap.face9};
    ImageView imageViewCenter;
    ImageView imageViewUp;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        b1 = (Button) findViewById(R.id.num1);
        b2 = (Button) findViewById(R.id.num2);
        b3 = (Button) findViewById(R.id.num3);
        b4 = (Button) findViewById(R.id.num4);
        b5 = (Button) findViewById(R.id.num5);
        b6 = (Button) findViewById(R.id.num6);
        b7 = (Button) findViewById(R.id.num7);
        b8 = (Button) findViewById(R.id.num8);
        b9 = (Button) findViewById(R.id.num9);
        bs = (Button) findViewById(R.id.space);
        bd = (Button) findViewById(R.id.deleteId);
        b0 = (Button) findViewById(R.id.num0);
        managerDatabase = new ManagerDatabase(this);
        editText.setRawInputType(InputType.TYPE_NULL);
        editText.setFocusable(true);
        imageViewCenter = (ImageView) findViewById(R.id.middle_photo_id);
        imageViewUp = (ImageView) findViewById(R.id.imageview_up_id);

        imageViewUp.setOnClickListener(this);
        imageViewCenter.setOnClickListener(this);

//        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
//        ImageAdapter adapter = new ImageAdapter(this);
//        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.emailId:
                SendingEmail();
                return true;
            case R.id.disableKeyboard:
                SwitchKeyboard(item);
                return true;
            case R.id.deleteAll:
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("Usunąć liczby z tego koła?");
                builder.setNegativeButton("Nie chcę usunąć liczb!!!" ,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        managerDatabase.DeleteColumn(ManagerDatabase.DatabaseClass.Col);
                        editText.setText(" ");
                        editText.setSelection(editText.getText().toString().length());

                    }
                });
// 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            case R.id.listOfNumbersid:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                listOfInteger= TransformEditTextToListOfIntegers(editText);
                //countedNumbersStr
                repetitions= CountingNumbersReceiveHashMap(listOfInteger);

                countedNumbersStr=CreatingStringFromRepeatedNumbers(repetitions);
                Log.d("print", ""+countedNumbersStr);
                builder1.setMessage(countedNumbersStr);
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
                return true;

            case R.id.increaseId:
                IncreseFontSize();
                return true;
            case R.id.decreaseId:
                DecreaseFontSize();


        }
        return super.onOptionsItemSelected(item);
    }

    public void GetExtraFromIntent (String wheelNum){
        Intent intent = getIntent();
        String kolo = intent.getExtras().getString(wheelNum);
        textView.setText(kolo);
    }

    @Override
    public void onClick(View v) {
        String etStr= editText.getText().toString();

        if (v.getId()==R.id.deleteId){
            if (etStr.length()>=1){
                DeleteNumber();
                SetColorsToWhite();
                return;
            }
            SetColorsToWhite();
        }

        else if (v.getId()==R.id.num1){
            String b1Str = b1.getText().toString();
            if (!(etStr.substring(etStr.length()-1).equals(" "))){
                b1Str+= " ";
                SetColorsToWhite();
            }
            else if (etStr.substring(etStr.length()-1).equals(" ")){
                b1.setTextColor(Color.GREEN);
//                b1.setBackgroundColor(Color.GRAY);
            }

            etStr+=b1Str;
            editText.setText(etStr);
        }
        else if (v.getId()==R.id.num2){
            String b2Str = b2.getText().toString();
            if (!(etStr.substring(etStr.length()-1).equals(" "))){
                b2Str+= " ";
                SetColorsToWhite();
            } else if (etStr.substring(etStr.length()-1).equals(" ")){
                b2.setTextColor(Color.GREEN);
//                b2.setBackgroundColor(Color.GRAY);

            }
            etStr+=b2Str;
            editText.setText(etStr);
        }
        else if (v.getId()==R.id.num3){
            String b3Str = b3.getText().toString();

            if (!(etStr.substring(etStr.length()-1).equals(" "))){
                b3Str+= " ";
                SetColorsToWhite();
            }

            else if (etStr.substring(etStr.length()-1).equals(" ")){
                b3.setTextColor(Color.GREEN);
//                b3.setBackgroundColor(Color.GRAY);

            }

            etStr+=b3Str;
            editText.setText(etStr);
        }
        else if (v.getId()==R.id.num0){
            String b0Str = b0.getText().toString();
            b0Str+=" ";
            etStr+=b0Str;
            editText.setText(etStr);
            SetColorsToWhite();
        }

        else if (v.getId()==R.id.num4){
            String b4Str = b4.getText().toString();
            b4Str+=" ";

            etStr+=b4Str;
            editText.setText(etStr);
            SetColorsToWhite();
        }
        else if (v.getId()==R.id.num5){
            String b5Str = b5.getText().toString();
            b5Str+=" ";
            etStr+=b5Str;
            editText.setText(etStr);
            SetColorsToWhite();
        }
        else if (v.getId()==R.id.num6){
            String b6Str = b6.getText().toString();
            b6Str+=" ";
            etStr+=b6Str;
            editText.setText(etStr);
            SetColorsToWhite();
        }
        else if (v.getId()==R.id.num7){
            if ((etStr.substring(etStr.length()-1).equals("3"))){
                Toast.makeText(this,"incorrect num",Toast.LENGTH_SHORT).show();
                return;

            }

            String b7Str = b7.getText().toString();
            b7Str+=" ";
            etStr+=b7Str;
            editText.setText(etStr);
            SetColorsToWhite();
        }
        else if (v.getId()==R.id.num8){
            if ((etStr.substring(etStr.length()-1).equals("3"))){
                Toast.makeText(this,"incorrect num",Toast.LENGTH_SHORT).show();
                return;

            }

            String b8Str = b8.getText().toString();
            b8Str+=" ";
            etStr+=b8Str;
            editText.setText(etStr);
            SetColorsToWhite();
        }
        else if (v.getId()==R.id.num9){
            if ((etStr.substring(etStr.length()-1).equals("3"))){
                Toast.makeText(this,"incorrect num",Toast.LENGTH_SHORT).show();
                return;

            }
            String b9Str = b9.getText().toString();
            b9Str+=" ";

            etStr+=b9Str;
            editText.setText(etStr);
            SetColorsToWhite();
        }
        else if (v.getId()==R.id.space){


            String bsStr = bs.getText().toString();
            if (etStr.substring(etStr.length()-1).equals(" ")){
                bsStr+=" ";
            }
            else {
                etStr+=bsStr;
                editText.setText(etStr);
            }
            SetColorsToWhite();
        }

        else if (v.getId()==R.id.middle_photo_id){
            int arr[] = getResources().getIntArray(R.array.photos_facebook);
            Random random = new Random();
            int a = random.nextInt(array_photo.length);
            imageViewCenter.setImageResource(array_photo[a]);
        }

        else if (v.getId()==R.id.imageview_up_id){
                            final MediaPlayer mp = MediaPlayer.create(this, R.raw.facebook_sound);
            mp.start();

        }

        editText.setSelection(etStr.length());


    }

    public void SetColorsToWhite(){
        b1.setTextColor(Color.GRAY);
        b1.setBackgroundColor(Color.WHITE);
        b2.setTextColor(Color.GRAY);
        b2.setBackgroundColor(Color.WHITE);
        b3.setTextColor(Color.GRAY);
        b3.setBackgroundColor(Color.WHITE);

    }

    public void SwitchKeyboard(MenuItem item){
        Log.d("key","  "+editText.getInputType());
        if (editText.getInputType()== InputType.TYPE_CLASS_TEXT || editText.getInputType()== 131073){
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

//            editText.setInputType(0);
            item.setTitle("OFF");
        } else{
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            item.setTitle("ON");
//        editText.setTextIsSelectable(true);
        }

    }

    public void SendingEmail (){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"janik.w777@gmail.com.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , editText.getText().toString() );
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(BaseClass.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
        //IncreseFontSize();
    }

    public void DeleteNumber(){
        String etStr = editText.getText().toString();
        int a = 0;

        int selEnd = editText.getSelectionEnd();

        if (selEnd == editText.length()){

            for(int i=selEnd-2; i>0;i--){
                if (etStr.charAt(i)==' '){
                    a = i;
                    break;
                }
            }
//        Log.d("info", ""+editText.getSelectionEnd() + " " + editText.getSelectionStart() + " " + editText.length());


            etStr = etStr.substring(0, a+1);
            editText.setText(etStr);
            editText.setSelection(etStr.length());
        }

        else {
            String endEditText = etStr.substring(selEnd,etStr.length());
            String frontEditText = etStr.substring(0, selEnd);

            for(int i=selEnd-2; i>0;i--){
                if (etStr.charAt(i)==' '){
                    a = i;
                    frontEditText = frontEditText.substring(0,i);
                    etStr = frontEditText+endEditText;
                    editText.setText(etStr);
                    editText.setSelection(editText.length());
                    break;
                }

            }
        }


    }

    public List<Integer> TransformEditTextToListOfIntegers(EditText editText){
        List<Integer> listOfInt = new ArrayList<>();
        int num_i = 0;
        int num_j = 0;
        int numberRecovered = 0;
        String edtStr = editText.getText().toString();

        for (int i = 0;i<editText.length()-1;i++){
            if (edtStr.charAt(i) == ' '){
                num_i = i;

                for (int j = i+1;j<editText.length();j++){

                    if (edtStr.charAt(j) == ' '){
                        num_j = j;
                        Log.d("num", ""+ num_i+" "+num_j);
                        String numRecovered = edtStr.substring(num_i+1,num_j);
                        numberRecovered = Integer.parseInt(numRecovered);
                        listOfInt.add(numberRecovered);
                        Log.d("num", ""+ numRecovered);
                        break;
                    }
                }
            }
        }

//        listOfInt.sort();
        return listOfInt;
    }

    public TreeMap<Integer, Integer> CountingNumbersReceiveHashMap(List<Integer> listOfInt){
        TreeMap<Integer, Integer> repetitions1 = new TreeMap<Integer, Integer>();
//        repetitions1 = new HashMap<Integer, Integer>();
        overAllCount = 0;

        Collections.sort(listOfInt);
        for (int i = 0; i<listOfInt.size();i++){
            Log.d("ooo",""+listOfInt.get(i));
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

    public void DatabaseAndSetSelection (String kolumna){
        String allDatabase = managerDatabase.ReturnAllDataAsAString(kolumna);
        if (allDatabase.equals("") || allDatabase==null){
            allDatabase = " ";
        }
        editText.setText(allDatabase);
//        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setSelection(allDatabase.length());
    }

    public String CreatingStringFromRepeatedNumbers(TreeMap<Integer,Integer> repetitions){
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Integer> e : repetitions.entrySet()) {
            if (e.getValue() > -1) {

                sb.append("\n");
                sb.append(e.getKey());
                sb.append(":");
                sb.append(e.getValue());
                sb.append(" times");
            }
        }
        String repeatedNumbersStr = sb.toString();
        String overAllCountStr = String.valueOf(overAllCount);
        repeatedNumbersStr = textView.getText().toString()+ " Ilosc liczb to "+overAllCountStr+"\n"+repeatedNumbersStr;

        return repeatedNumbersStr;
    }

    public void IncreseFontSize() {
        editText.setTextSize((editText.getTextSize() * 0.6f));
    }
    public void DecreaseFontSize() {
        editText.setTextSize((editText.getTextSize() * 0.4f));
    }

}
