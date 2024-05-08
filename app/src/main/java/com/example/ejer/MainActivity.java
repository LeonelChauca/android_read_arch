package com.example.ejer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ArrayList<ArrayList<String>> kardex = new ArrayList<>();
    ArrayList<ArrayList<String>> periodos = new ArrayList<>();
    ArrayList<ArrayList<String>> docentes = new ArrayList<>();
    ArrayList<ArrayList<String>> materias = new ArrayList<>();
    ArrayList<ArrayList<String>> estudiantes = new ArrayList<>();
    Button btn;
    TextView tx;
    EditText inp;
    TextView tx2;
    String textfin = "";
    String tex = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        inp = findViewById(R.id.editTextText2);
        tx = findViewById(R.id.textView);
        tx2 = findViewById(R.id.textView2);
        accessFile("kardex.csv", kardex);
        accessFile("periodos.csv", periodos);
        accessFile("docentes.csv", docentes);
        accessFile("materias.csv", materias);
        accessFile("estudiantes.csv", estudiantes);
    }

    public void btn(View v) {
        //textfin="";
        //textfin+=" CARGO \t  LIPAG \t CASOS \n";
        //ejer2();
        textfin = "";
        tex = "";
        ejer4(inp.getText().toString());
        tx.setText(textfin);
        tx2.setText(tex);
    }
//    public void ejer3Periodo(String dato){
//        int cont=0;
//        int cont2R=0;
//        int cont3A=0;
//        int TotAP=0;
//        int TotRP=0;
//        int TotAB=0;
//        for (int i = 1; i < periodos.size(); i++) {
//            cont=0;
//            cont2R=0;
//            cont3A=0;
//            for (int j = 1; j < kardex.size(); j++) {
//                if(periodos.get(i).get(0).equals(kardex.get(j).get(3)) && kardex.get(j).get(1).equals(dato)){
//                    if(kardex.get(j).get(5).equals("Aprobado")){
//                        cont++;
//                    } else if (kardex.get(j).get(5).equals("Abandono")) {
//                        cont3A++;
//                    } else{
//                        cont2R++;
//                    }
//                }
//            }
//            TotAP+=cont;
//            TotRP+=cont2R;
//            TotAB+=cont3A;
//            textfin+=periodos.get(i).get(1)+"\n";
//            textfin+="Nro de aprobados : "+cont+"\n";
//            textfin+="Nro de reprobados : "+cont2R+"\n";
//            textfin+="Nro de abandono : "+cont3A+"\n";
//        }
//        tex+="-----------------------------------------------------\n";
//        tex+="TOTAL APROBADOS : "+  TotAP+"\n";
//        tex+="TOTAL REPROBADOS : "+  TotRP+"\n";
//        tex+="TOTAL ABANDONADOS : "+  TotAB+"\n";
//    }

    public void ejer4(String dato) {
        for (int i = 1; i < periodos.size(); i++) {
            textfin += periodos.get(i).get(1) + "\n";
            textfin += "---------------------\n";
            for (int j = 1; j < kardex.size(); j++) {
                if (kardex.get(j).get(0).equals(dato) && kardex.get(j).get(5).equals("Aprobado")) {
                    textfin += "" + kardex.get(j).get(1) + "   ";
                    textfin += "" + encontrarNota(kardex.get(j).get(1)) + "    ";
                    textfin += "" + kardex.get(j).get(4)+"   \n";
                    textfin+="\n";
                }
            }
            textfin += "---------------------\n";
        }
    }

    public String encontrarNota(String dato) {
        for (int i = 1; i < materias.size(); i++) {
            if (materias.get(i).get(0).equals(dato)) {
                return materias.get(i).get(1);
            }
        }
        return "";
    }













    private void accessFile(String archivo, ArrayList<ArrayList<String>> arr) {
        String estado = Environment.getExternalStorageState();
        if (!estado.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "No se encuentra la tarjeta SD", Toast.LENGTH_SHORT).show();
            return;
        }

        File dir = Environment.getExternalStorageDirectory();
        File p = new File(dir.getAbsolutePath() + File.separator + archivo);
        try {
            BufferedReader lector = new BufferedReader(new FileReader(p));
            StringBuilder res = new StringBuilder();
            String linea;

            while ((linea = lector.readLine()) != null) {
                arr.add(new ArrayList<>(List.of(linea.split(";"))));

            }
            lector.close();
        } catch (IOException e) {
            Toast.makeText(this,  e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void accessFile2(String archivo, ArrayList<ArrayList<String>> arr) {
        String estado = Environment.getExternalStorageState();
        if (!estado.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "No se encuentra la tarjeta SD", Toast.LENGTH_SHORT).show();
            return;
        }

        File dir = Environment.getExternalStorageDirectory();
        File p = new File(dir.getAbsolutePath() + File.separator + archivo);
        try {

            Scanner lee=new Scanner(new FileReader(p));
            String linea;
            String res="";
            while(lee.hasNext()){
                linea=lee.nextLine();
                res=res+linea+"\n";

            }
            textfin=textfin+res;

            //BufferedReader lector = new BufferedReader(new FileReader(p));
            //StringBuilder res = new StringBuilder();
            //String linea;

            //while ((linea = lector.readLine()) != null) {
            //    arr.add(new ArrayList<>(List.of(linea.split(";"))));
            //}
            //lector.close();
        } catch (IOException e) {
            Toast.makeText(this,  e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}