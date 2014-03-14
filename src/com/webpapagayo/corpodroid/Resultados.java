package com.webpapagayo.corpodroid;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class Resultados extends Activity {
	private ProgressDialog dialog;
	private String nic;
	private String cliente = "";
	public TextView cliente2;
	protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados);

    	cliente2 = (TextView)findViewById(R.id.clienteText);
    	//cliente2.setText("aaaa");
    	
		
        /*dialog = new ProgressDialog(this);
        dialog.setMessage("Descargando...");
        dialog.setTitle("Progreso");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);*/
       

        Bundle bundle = getIntent().getExtras();
        nic = bundle.getString("nic");
        System.out.println("NIC DE USUARIO --- >" + nic);
        //webView1.loadUrl("http://" + bundle.getString("direccion"));
        
      
        
        
        new CargarDatos().execute();
        System.out.println("test --->" +cliente);
 
    }
	
	public class CargarDatos extends AsyncTask<String, Void, Resultados>{
		
		 
		 
		/*protected void onPreExecute() {
            dialog.setProgress(0);
            dialog.setMax(100);
                dialog.show(); //Mostramos el diálogo antes de comenzar
                System.out.println("dentro del preExecute");
         }*/
		@Override
		protected Resultados doInBackground(String... params) {
			// TODO Auto-generated method stub
			Document doc;
			try {
		 
				// need http protocol
				doc = Jsoup.connect("http://cobrosweb.cadafe.com.ve/enlinea/consultadeuda.aspx?nic="+nic).get();
				Elements nic2 = doc.select("input");
				String valorNIC = "";
				
				String deuda="";
				
				for (Element nic1 : nic2){
					String valor = nic1.attr("name");
					//System.out.println("NAME --->"+ nic.attr("name"));
					//System.out.println("VALUE -->" +  nic.attr("value"));
					
					//System.out.println("INPUNT --> " + nic.text(""));
					
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
		
		/* protected void onProgressUpdate (Float... valores) {
             int p = Math.round(100*valores[0]);
             dialog.setProgress(p);
         }
		 
		 protected void onPostExecute(Integer bytes) {
             dialog.dismiss();
         }*/
		
	}

}
