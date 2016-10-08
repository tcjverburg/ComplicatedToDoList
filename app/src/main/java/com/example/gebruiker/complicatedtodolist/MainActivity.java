package com.example.gebruiker.complicatedtodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private String list = read();
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        Bundle bundle = new Bundle();
        bundle.putStringArray("edttext", list.split(","));
        // set Fragmentclass Arguments
        TitlesFragment fragobj = new TitlesFragment();
        fragobj.setArguments(bundle);
    }

    public String read() {
        //reading text from file
        //http://stackoverflow.com/questions/5283444/convert-array-of-strings-into-a-string-in-java
        try {
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            //change reading
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            list = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                list += readstring;
            }
            InputRead.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



}


