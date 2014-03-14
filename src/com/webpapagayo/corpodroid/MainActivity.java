package com.webpapagayo.corpodroid;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	private EditText textNIC;
	private Button btnConsultar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textNIC = (EditText)findViewById(R.id.textNIC);
		btnConsultar=(Button)findViewById(R.id.btnConsultar);
		
		btnConsultar.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
        	//ejecutar(v);
        	new cargarDatos2().execute();
         }
      });
	}
	
	public void ejecutar(View view) {
        Intent i = new Intent(this, Resultados.class);
        i.putExtra("nic", textNIC.getText().toString());
        startActivity(i);
	}
	
	public class cargarDatos2 extends AsyncTask<String, Void, MainActivity>{

		@Override
		protected MainActivity doInBackground(String... params) {
			Document doc;
			try {
		 
				// need http protocol
				doc = Jsoup.connect("http://cobrosweb.cadafe.com.ve/enlinea/consultadeuda.aspx?nic=3169182").get();
				Elements nic2 = doc.select("input");
				String valorNIC = "";
				String cliente="";
				String deuda="";
				
				for (Element nic1 : nic2){
					String valor = nic1.attr("name");
									
					if(valor.equals("TextBox1")){
						valorNIC = nic1.attr("value");
					}
					else if(valor.equals("TextBox2")){
						//System.out.println("TEXTO 2");
						cliente = nic1.attr("value");
					}
					else if(valor.equals("TextBox7")){
						deuda =nic1.attr("value");
						
					}
					}
			    
				System.out.println("NIC: "+valorNIC);
				System.out.println("Cliente: "+ cliente);
				System.out.println("Deuda: " + deuda);
				//cliente2.setText("aaaaaa");
					
		 
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
	}

}
