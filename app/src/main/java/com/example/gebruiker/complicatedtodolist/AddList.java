package com.example.gebruiker.complicatedtodolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Gebruiker on 8-10-2016.
 */
public class AddList extends Activity {
    private EditText textmsg;
    private String list;

    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list);

        Button btnAddOneList = (Button)findViewById(R.id.addOneList);
        textmsg = (EditText) findViewById(R.id.editTextList);

        btnAddOneList.setOnClickListener(new View.OnClickListener() {

                                             @Override
                                             public void onClick(View v) {
                                                 write();
                                             }
                                             });


    }

    public void write() {
        // add-write text into file
        try {
            String story = read();
            FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);

            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(textmsg.getText().toString() + "," + story);
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
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


