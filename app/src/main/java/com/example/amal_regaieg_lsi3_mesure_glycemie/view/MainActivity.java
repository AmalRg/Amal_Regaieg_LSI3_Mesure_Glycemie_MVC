package com.example.amal_regaieg_lsi3_mesure_glycemie.view;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.amal_regaieg_lsi3_mesure_glycemie.R;
import com.example.amal_regaieg_lsi3_mesure_glycemie.controller.Controller;
public class MainActivity extends AppCompatActivity {
    private TextView tvage;
    private SeekBar sbAge;
    private RadioButton rbtOui;
    private RadioButton rbtNon;
    private EditText ValMes;
    private Button btnConsulter;
    private TextView TvRes;
    private Controller controller = Controller.getInstance();
    private void init() {
        tvage = (TextView) findViewById(R.id.tvage);
        sbAge = (SeekBar) findViewById(R.id.sbAge);
        rbtOui = (RadioButton) findViewById(R.id.rbtOui);
        rbtNon = (RadioButton) findViewById(R.id.rbtNon);
        ValMes = (EditText) findViewById(R.id.ValMes);
        btnConsulter = (Button) findViewById(R.id.btnConsulter);
        TvRes = (TextView) findViewById(R.id.TvRes);
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged " + progress);
                // affichage dans le Log de Android studio pour voir les changements détectés sur le SeekBar ..
                tvage.setText("Votre âge : " + progress);
                // Mise à jour du TextView par la valeur: progress à chaque changement.
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = sbAge.getProgress();
                System.out.println("age =" + age);
                String valmesure = (ValMes.getText().toString());
                System.out.println("val =" + valmesure);
                if (age == 0 && valmesure.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Veuillez saisir votre age et une valeur mesurée !", Toast.LENGTH_SHORT).show();
                } else if (age == 0) {
                    Toast.makeText(getApplicationContext(),"Veuillez saisir votre age !", Toast.LENGTH_SHORT).show();
                } else if (valmesure.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Veuillez saisir une valeur mesurée !", Toast.LENGTH_SHORT).show();
                } else {
                    float valMes = Float.parseFloat(valmesure);
                    boolean jeun = rbtOui.isChecked();
                    if (jeun) {
                        if (age > 13) {
                            if (valMes >= 5.0 && valMes <= 7.2) {
                                TvRes.setText("Niveau de glycémie est normale");
                            } else if (valMes < 5.0) {
                                TvRes.setText("Niveau de glycémie est trop bas");
                            } else if (valMes > 7.2) {
                                TvRes.setText("Niveau de glycémie est trop élevée");
                            }
                        } else if (age >= 6 && age <= 12) {
                            if (valMes >= 5.0 && valMes <= 10.0) {
                                TvRes.setText("Niveau de glycémie est normale");
                            } else {
                                TvRes.setText("Niveau de glycémie est trop bas");
                            }
                        } else if (age < 6) {
                            if (valMes >= 5.5 && valMes <= 10.0) {
                                TvRes.setText("Niveau de glycémie est normale");
                            } else {
                                TvRes.setText("Niveau de glycémie est trop bas");
                            }
                        }
                    } else {
                        if (valMes < 10.5) {
                            TvRes.setText("Niveau de glycémie est normale");
                        } else {
                            TvRes.setText("Niveau de glycémie est trop élevée");
                        }
                    }
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}