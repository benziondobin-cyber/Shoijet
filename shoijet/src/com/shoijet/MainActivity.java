package com.shoijet;


import android.app.Activity;
import android.app.AlertDialog;
import com.shoijet.R;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Bitmap;
import android.os.*;
import android.os.Bundle;


import android.view.View;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Button;
import android.widget.Toast;

import android.webkit.WebChromeClient;
import android.webkit.JsResult;
import android.webkit.JavascriptInterface;
import android.net.Uri;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

public class MainActivity extends Activity
{
    public boolean paralo=true,parado=true,regresalo=false,ponle=false,en_baka=false,estoy_enbaca=false,hice_log=false;
  //  public Handler h=new Handler(); 
Handler h = new Handler(Looper.getMainLooper());
    public View layout;
    public LayoutInflater inflater;
	private WebView mWebView1;
	public static Button mGoBtn,mGoBtn2;
    public String str="hola", nombre="",clave="",
  
      //rea="file:///android_asset/rea.html",
                      
        rea="https://shoijet.rf.gd/index.html",
		bak="https://shoijet.rf.gd/bakashot.php",

        la_aplica="https://shoijet.rf.gd/shjita.apk",
        re="ריאה",ba="מזכירות",
		bak_original=bak,ba_original=ba,relo="טעינה";
    public int cu=0;
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
             estoy_enbaca=false;en_baka=false;	
	//mWebView1 = (WebView)findViewById(R.id.webView1);
 	 mWebView1 = (WebView)findViewById(R.id.webView1);
		mWebView1.addJavascriptInterface(new JavascriptHandler(),"miapi");
		mWebView1.getSettings().setJavaScriptEnabled(true);
		mWebView1.setWebChromeClient(new WebChromeClient() { 
	@Override
	public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
	return super.onJsAlert(view, url, message, result); } });
		mWebView1.setWebViewClient(new WebViewClient()
		{   
			@Override
			public void onPageFinished(WebView view, String url)
			{
			    String a="",b="",temp="";
				super.onPageFinished(view, url);
			if (url.equals(rea) ){a=ba;b=re;url=bak;}
			else {a=re;b=ba;url=rea;}
			    str=a;
				mGoBtn.setEnabled(true); 
				mGoBtn2.setEnabled(true); 
				mGoBtn.setText(a);
				mGoBtn2.setText(b);
			if (en_baka){ 
			en_baka=false;
			if (estoy_enbaca & hice_log){cu=0; } else {
			temp="javascript:ponusuario('"+nombre+"','"+clave+"')";
			mWebView1.loadUrl(temp);
			hice_log=true;}
			}
			}
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon)
			{  // String s="טעינה";
				super.onPageStarted(view, url, favicon);
				mGoBtn.setEnabled(false);
				mGoBtn2.setEnabled(false);
				mGoBtn.setText("רק רגע...");
			}
		});
		//mWebView1.getSettings().setDomStorageEnabled(true);
		mWebView1.getSettings().setJavaScriptEnabled(true);
		str=ba;
		mGoBtn = (Button)findViewById(R.id.goBtn);
		mGoBtn2 = (Button)findViewById(R.id.goBtn2);
		mGoBtn.setOnClickListener(mGoBtnClick);
		mGoBtn2.setOnClickListener(mGoBtn2Click); mGoBtn2.setText(re);
			 mWebView1.loadUrl(rea);
	}
	private  View.OnClickListener mGoBtnClick = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			String a=str, url=rea;
		   if (a.equals(re) || a.isEmpty()){url=rea;}
		   if (a.equals(ba) || a.isEmpty()){url=bak;}
		   if (url.isEmpty()){url=rea;
			Alert(getApplicationContext(), "לידיעתך", "יופל דף הרשי", "Ok",null,null,null, null,1,0,0,0,null);
			}
			if (url.equals(bak_original)){
				en_baka=true;estoy_enbaca=false;
				//Toast.makeText(getApplicationContext(),"orig",Toast.LENGTH_LONG).show();
			} else {en_baka=false;}
			mWebView1.loadUrl(url);
		}
	};
	private  View.OnClickListener mGoBtn2Click = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			String a=str, url=rea;
		   if (a.equals(re) || a.isEmpty()){url=bak;}
		   if (a.equals(ba) || a.isEmpty()){url=rea;}
			if (url.equals(bak_original)){
				en_baka=true;estoy_enbaca=false;hice_log=false;
				//Toast.makeText(getApplicationContext(),"orig",Toast.LENGTH_LONG).show();
			} else {en_baka=false;}
			mWebView1.loadUrl(url);
		}
	};
    @Override
    public void onBackPressed()
	{
			if (mWebView1.canGoBack()){mWebView1.goBack(); }
			else { 
			if (cu==0){
	 	   Toast.makeText(getApplicationContext()," כדי לצאת ללחוץ פעם נוספת",Toast.LENGTH_LONG).show();
	 	  // Toast.makeText(getApplicationContext(),"xxx",Toast.LENGTH_LONG).show();
			}
			if (cu==1) {pregunta();} 
	        cu=1;
			//Handler h=new Handler();
			Handler h = new Handler(Looper.getMainLooper());
			h.postDelayed(new Runnable(){
			@Override
			public void run(){cu=0;}
			},3000);
			return;}   
      }
 //****************************************************
@Override
public void onPause()
	{
      super.onPause();
      paralo=true;
}
@Override
public void onResume()
	{
      super.onResume();
      }
@Override
protected void onDestroy(){
		super.onDestroy();
		paralo=true;
		mWebView1.clearHistory();
		Toast.makeText(this,"להתראות.",Toast.LENGTH_LONG).show();
		}
//****************************************************
   @Override
    public boolean onCreateOptionsMenu(Menu menu){
    menu.add(0, 0, 0," יציאה ");
    menu.add(0, 1, 1," בטל ");
    return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()){
    case 0:
    pregunta();
    return true;
    case 1:
    return true;
    }
    return false;
    }
//****************************************************
    public void pregunta (){
     inflater = getLayoutInflater();
     layout = inflater.inflate(R.layout.my_dialog, null);
	   Alert(MainActivity.this, "יציאה!", "בטוח רוצה לצאת?", "כן","לא",null,
	       new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,  int which) {
	             		Toast.makeText( getApplicationContext() ,"שלום!",Toast.LENGTH_LONG).show();
	            finish();}
	                },
	     new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog,  int which) { }
	           },         
	                1,1,0,1,layout);
}
	public static void Alert(Context context, String title, String message, 
	String positiveBtnText, String negText,String neutText,
	DialogInterface.OnClickListener positive,DialogInterface.OnClickListener negativo,
	int p,int ne,int nu,int x,View layout) 
	{
        AlertDialog dialog = new AlertDialog.Builder(context).create();
    	/*          
           	 dialog.setPositiveButton("כן", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,  int which) { finish(); }
	                });
           	 diaog.setNeutralButton("לא", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,  int which) {    }
	                });
	*/     
	   if (x==1) {dialog.setCustomTitle(layout);} else
	             {dialog.setTitle(title); dialog.setMessage(message);}
        dialog.setCancelable(false);
        if (p==1)    
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, positiveBtnText, positive);
        if (ne==1)
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, negText, negativo);
        if (nu==1)
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, neutText, positive);
        dialog.show();
    }
 //****************************************************
public void repitelo(int t){
			h.postDelayed(new Runnable(){
			@Override
			public void run(){
			  if (ponle | regresalo){ponle=false;regresalo=false;
			  if (!estoy_enbaca){mGoBtn.setText(ba);mGoBtn2.setText(re);}
			  }
			  if (!paralo){parado=false;repitelo(6000);} else {parado=true;}
			 }
			},t);
}
//****************************************************
public void albrow(){
    Intent i=new
        Intent(android.content.Intent.ACTION_VIEW,
        Uri.parse(la_aplica));
        startActivity(i);
}
public class JavascriptHandler {
@JavascriptInterface
public void baja_apli() {
albrow();
}
@JavascriptInterface 
public void pasa(String s,String nom,String cla) {
	if (s.equals("pase")){regresalo=false;ponle=true;nombre=nom;clave=cla;hice_log=false;estoy_enbaca=false;
	bak=rea;ba=re;} 
	if (s.equals("regresa")) {regresalo=true;
	ponle=false;nombre=nom;clave=cla;hice_log=false;estoy_enbaca=false;
	bak=bak_original;ba=ba_original;}
	str=ba;
	if (s.equals("baca")){ponle=false;str=re;estoy_enbaca=true;}
	if (parado){repitelo(1000);}
}
}      
}
