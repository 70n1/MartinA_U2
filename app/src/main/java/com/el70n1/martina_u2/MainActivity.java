package com.el70n1.martina_u2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost tabHost;

    private double operando_1 = 0;
    private double operando_2 = 0;
    private double ratio_euro = 166.39;
    private boolean realizando_operacion = false;
    private boolean en_euros = true;
    private boolean reseteando = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Asteroides"),
                Tab1.class, null);

        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Botón"),
                Tab2.class, null);

        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Calculadora"),
                Tab3.class, null);

    }
    public void sePulsa(View view){
        Toast.makeText(this, "Pulsado", Toast.LENGTH_SHORT).show();
    }


    public void pulsado_boton_calculadora(View view) {
        String resourceEntryName = getResources().getResourceEntryName(view.getId());


        if ((reseteando) && (!(resourceEntryName.equals("button_pasar_moneda")))) {
            operando_1 = 0;
            operando_2 = 0;
            realizando_operacion = false;
            reseteando= false;
        }
        if (resourceEntryName.equals("button_calc_0")) {
            anyadir_numero_calculadora(0);
        }
        if (resourceEntryName.equals("button_calc_1")) {
            anyadir_numero_calculadora(1);
        }
        if (resourceEntryName.equals("button_calc_2")) {
            anyadir_numero_calculadora(2);
        }
        if (resourceEntryName.equals("button_calc_3")) {
            anyadir_numero_calculadora(3);
        }
        if (resourceEntryName.equals("button_calc_4")) {
            anyadir_numero_calculadora(4);
        }
        if (resourceEntryName.equals("button_calc_5")) {
            anyadir_numero_calculadora(5);
        }
        if (resourceEntryName.equals("button_calc_6")) {
            anyadir_numero_calculadora(6);
        }
        if (resourceEntryName.equals("button_calc_7")) {
            anyadir_numero_calculadora(7);
        }
        if (resourceEntryName.equals("button_calc_8")) {
            anyadir_numero_calculadora(8);
        }
        if (resourceEntryName.equals("button_calc_9")) {
            anyadir_numero_calculadora(9);
        }

        if (resourceEntryName.equals("button_calc_mas")) {
            operando_1 = operando_1 + operando_2;
            operando_2 = 0;
            realizando_operacion = true;
        }

        if (resourceEntryName.equals("button_calc_igual")) {
            operando_1 = operando_1 + operando_2;
            operando_2 = 0;
            realizando_operacion = false;
        }
        if (resourceEntryName.equals("button_calc_c")) {
            operando_1 = 0;
            operando_2 = 0;
            realizando_operacion = false;
        }
        if (resourceEntryName.equals("button_pasar_moneda")){
            if (en_euros) {
                operando_1 = operando_1 * ratio_euro;
                operando_2 = operando_2 * ratio_euro;
                ((Button) findViewById(R.id.button_pasar_moneda)).setText("pasar a Euros");
            } else {
                if (operando_1>0) {
                    operando_1 = operando_1 / ratio_euro;
                } else {
                    operando_1 = 0;
                }
                if (operando_2>0) {
                    operando_2 = operando_2 / ratio_euro;
                } else {
                    operando_2 = 0;
                }
                ((Button) findViewById(R.id.button_pasar_moneda)).setText("pasar a pesetas");
            }
            en_euros = !(en_euros);
            reseteando = true;
        }
        mostrar_display();
    }

    private void mostrar_display() {
        String numero_a_mostrar="";
        if (realizando_operacion) {
            numero_a_mostrar = prettyPrint(operando_2);
        } else{
            numero_a_mostrar = prettyPrint(operando_1);
        }
        if (en_euros) {
            ((TextView) findViewById(R.id.display_calculadora)).setText(numero_a_mostrar +" €");
        } else {
            ((TextView) findViewById(R.id.display_calculadora)).setText(numero_a_mostrar +" ptas");
        }
    }

    public static String prettyPrint(double d) {
        int i = (int) d;
        return d == i ? String.valueOf(i) : String.format("%.2f", d);
    }

    private void anyadir_numero_calculadora(int numero){
        if (realizando_operacion) {
            operando_2 = (operando_2 *10) + numero;
        } else {
            operando_1 = (operando_1 *10) + numero;
        }
    }

}


