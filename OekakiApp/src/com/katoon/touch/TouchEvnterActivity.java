package com.katoon.touch;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TouchEvnterActivity extends Activity implements OnClickListener, AnimationListener{
	private static final String TAG = "TouchEvnterActivity";

	private Button button1,button2,button3,button4,button5,button6,button7;

	private penView pen;
    
	 private soundPlayer soundplay;
//	 private boolean soundOnOff = true;
	 AnimationSet animeSet;
	 
	FrameLayout framelayout;
	
	/*
	private Activity _context;
	public TouchEvnterActivity(Context context){
		super(context);
		 _context = (Activity)context;
	}
*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG,"onCreate");
		super.onCreate(savedInstanceState);

		soundplay = new soundPlayer(this);
		animeSet = new AnimationSet(true);
	    
		framelayout = new FrameLayout(this);
		setContentView(framelayout);
		
		imageViewCamera = new ImageView(this);
		framelayout.addView(imageViewCamera);
		
		pen = new penView(this);
		
		framelayout.addView(pen);
		
		LinearLayout linearLayout = new LinearLayout(this);

		button1 = new Button(this);
		button1.setText("けす");
		button1.setOnClickListener(this);
		linearLayout.addView(button1, new LinearLayout.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

		button2 = new Button(this);
		button2.setText("いろ");
		button2.setOnClickListener(this);
		linearLayout.addView(button2, new LinearLayout.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

		button4 = new Button(this);
		button4.setText("ふとさ");
		button4.setOnClickListener(this);
		linearLayout.addView(button4, new LinearLayout.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

//		button3 = new Button(this);
//		button3.setText("けしごむ");
//		button3.setOnClickListener(this);
//		linearLayout.addView(button3, new LinearLayout.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

//		button5 = new Button(this);
//		button5.setText("なし");
//		button5.setOnClickListener(this);
//		linearLayout.addView(button5, new LinearLayout.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

		button6 = new Button(this);
		button6.setText("さわる");
		button6.setOnClickListener(this);
		linearLayout.addView(button6, new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

		button7 = new Button(this);
		button7.setText("うごく");
		button7.setOnClickListener(this);
		linearLayout.addView(button7, new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

		/*
//		Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.rider);
//		BitmapDrawable bitmapDrawable=new BitmapDrawable(bitmap);
//		button1.setBackgroundDrawable(bitmapDrawable);
		button1.setBackgroundColor(Color.GRAY);
		button2.setBackgroundColor(Color.GRAY);
		button3.setBackgroundColor(Color.GRAY);
		button4.setBackgroundColor(Color.GRAY);
		button5.setBackgroundColor(Color.GRAY);
		button6.setBackgroundColor(Color.GRAY);
		button7.setBackgroundColor(Color.GRAY);
		*/
		
		framelayout.addView(linearLayout);
	}
	
    
    public void onPause(){
		Log.i(TAG,"onPause");
		super.onPause();
    	soundplay.stopMusic();
    }
    
    public void onResume(){
		Log.i(TAG,"onResume");
		super.onResume();

    }
    
	@Override
	public boolean onTouchEvent(MotionEvent e){
		Log.i(TAG,"onTouchActivity");
		return true;
	}
	
	static final int REQUEST_CODE_CAPTURE_IMAGE = 100;		//識別用定義

	/**
	 * ボタン押されたとき
	 */
	@Override
    public void onClick(View v) {
		Log.i(TAG,"onClick");
        if (v == button1){
        	pen.clearPaint();
        	soundplay.startSound(1);
	    	imageViewCamera.setImageBitmap(null);
	    	if(pen.getMovin()){
	    		button6.setTextColor(pen.changeMovin() ? Color.GRAY : Color.BLACK);
	    	}
        }else if (v == button2){
        	 int color = pen.changeColor();
        	//button2.setBackgroundColor(color);
        	button2.setTextColor(color);
        }else if (v == button3){
        	button3.setTextColor(pen.chgKeshigomuMode() ? Color.MAGENTA : Color.BLACK);
        }else if (v == button4){
        	int width = pen.changeStrokeWidth();
        	switch(width){
        	case 1: button4.setText("ほそい"); break;
        	case 2: button4.setText("ふつう"); break;
        	case 3: button4.setText("ふとい"); break;
        	default: button4.setText("線"); break;
        	}
        	//soundplay.stopMusic();
        }else if (v == button5){
	//		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	//		startActivityForResult(intent, REQUEST_CODE_CAPTURE_IMAGE);
	    }else if (v == button6){
	    	//imageViewCamera.setImageBitmap(null);
    		button6.setTextColor(pen.changeMovin() ? Color.MAGENTA : Color.BLACK);
	    }else if (v == button7){
    		Log.d(TAG,String.valueOf(animeSet.hasStarted()));
	    	if(animeSet.hasStarted()){
	        	pen.clearAnimation();
	        	imageViewCamera.clearAnimation();
	        	animeSet = new AnimationSet(true);
	    	}else{
	    		settingAnimation();
	        	pen.startAnimation(animeSet);
	        	imageViewCamera.startAnimation(animeSet);
	    	}
	    }
    }


    /**
     * アニメーション関連
     */
    private void settingAnimation(){
		Log.i(TAG,"settingAnimation");
		animeSet = new AnimationSet(true);

//	    AlphaAnimation alpha = new AlphaAnimation(0.9f, 0.2f);			//フェード
	    RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);	//回転
	    ScaleAnimation scale = new ScaleAnimation(1, 2, 1, 2, 400, 0);	//ズーム
//	    TranslateAnimation translate = new TranslateAnimation(50, 0, 200, 0);	//移動
//	    animeSet.addAnimation(alpha); 
	    animeSet.addAnimation(rotate);
	    animeSet.addAnimation(scale);
//	    animeSet.addAnimation(translate);
	    animeSet.setInterpolator(new CycleInterpolator(5F));
	    animeSet.setAnimationListener(this);
    	animeSet.setDuration(24000);
    }
    
	@Override
	public void onAnimationStart(Animation animation) {
		Log.i(TAG,"onAnimationStart");
			//if(soundOnOff) soundplay.startMusic();
			soundplay.startMusic();
			button7.setText("とめる");
	  }
	
	@Override
    public void onAnimationEnd(Animation arg0) {
		Log.i(TAG,"onAnimationEnd");
    	soundplay.stopMusic();
		button7.setText("うごく");
		pen.update();
   }

	@Override
   public void onAnimationRepeat(Animation animation) {
		Log.i(TAG,"onAnimationRepeat");
   }
   
    /**
     * カメライベントの受付
     */
	ImageView imageViewCamera;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(REQUEST_CODE_CAPTURE_IMAGE == requestCode && resultCode == Activity.RESULT_OK ){
			Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
			imageViewCamera.setImageBitmap(capturedImage);
		}
	}

    
    private static final int MENU_ID_MENU1 = (Menu.FIRST + 1);
    private static final int MENU_ID_MENU2 = (Menu.FIRST + 2);
    private static final int MENU_ID_MENU3 = (Menu.FIRST + 3);
 //   private boolean visible = true;
    // オプションメニューが最初に呼び出される時に1度だけ呼び出されます
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューアイテムを追加します
        menu.add(Menu.NONE, MENU_ID_MENU1, Menu.NONE, "PHOTO");
        menu.add(Menu.NONE, MENU_ID_MENU2, Menu.NONE, "SAVE");
        menu.add(Menu.NONE, MENU_ID_MENU3, Menu.NONE, "BT");
        return super.onCreateOptionsMenu(menu);
    }

    // オプションメニューが表示される度に呼び出されます
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    //    menu.findItem(MENU_ID_MENU2).setVisible(visible);
    //    visible = !visible;
    	/*
    	String menu2;
    	if(soundOnOff){
    		menu2 = "SOUND OFF";
    	}else{
    		menu2 = "SOUND ON";
    	}
    	menu.findItem(MENU_ID_MENU2).setTitle(menu2);
    	*/
        return super.onPrepareOptionsMenu(menu);
    }

    // オプションメニューアイテムが選択された時に呼び出されます
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean ret = true;
        switch (item.getItemId()) {
        default:
            ret = super.onOptionsItemSelected(item);
            break;
        case MENU_ID_MENU1:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, REQUEST_CODE_CAPTURE_IMAGE);
            ret = true;
            break;
        case MENU_ID_MENU2:
        	saveBitmapToSd(pen.getBitmap());
        	//soundOnOff = !soundOnOff;
        	//button3.setTextColor(soundOnOff ? Color.BLACK : Color.GRAY);
        	/*
        	if(!soundOnOff){
        		soundplay.stopMusic();
        	}
        	*/
            ret = true;
            break;
        case MENU_ID_MENU3:
        	Intent i = new Intent(getApplicationContext(),BluetoothControler.class);
        	startActivity(i);
        	ret = true;
            break;

        }
        return ret;
    }
    
    /**
     * SDカードフォルダに日付名のファイルで画像イメージを保存する
     * @param mBitmap
     */
    public void saveBitmapToSd(Bitmap mBitmap) {
    	/*
    	 try {
    	 // sdcardフォルダを指定
    	 File root = Environment.getExternalStorageDirectory();

    	 // 日付でファイル名を作成　
    	 Date mDate = new Date();
    	 SimpleDateFormat fileName = new SimpleDateFormat("yyyyMMdd_HHmmss");

    	 // 保存処理開始
    	 FileOutputStream fos = null;
    	 fos = new FileOutputStream(new File(root, fileName.format(mDate) + ".jpg"));

    	 // jpegで保存
    	 mBitmap.compress(CompressFormat.JPEG, 100, fos);

    	 // 保存処理終了
    	 fos.close();
    	 } catch (Exception e) {
    	 Log.e("Error", "" + e.toString());
    	 }
    	}
    public void saveToFile(){
    */

    	 if(!sdcardWriteReady()){
	    	 Toast.makeText(getBaseContext(), "SDcardが認識されません。", Toast.LENGTH_SHORT).show();
	    	 return;
    	 }
    	 
    	 File file = new File(Environment.getExternalStorageDirectory().getPath()+"/drawbm/");
    	 
    	 try{
	    	 if(!file.exists()){
	    		 file.mkdir();
	    	 }
    	 }catch(SecurityException e){
        	 Log.e("Error", "" + e.toString());
    	 }
    	 
    	 String AttachName = file.getAbsolutePath() + "/";
    	 AttachName += System.currentTimeMillis()+".jpg";
    	 File saveFile = new File(AttachName);

    	 while(saveFile.exists()) {
	    	 AttachName = file.getAbsolutePath() + "/" + System.currentTimeMillis() +".jpg";
	    	 saveFile = new File(AttachName);
    	 }
    	 
    	 try {
	    	 FileOutputStream out = new FileOutputStream(AttachName);
	    	 mBitmap.compress(CompressFormat.JPEG, 100, out);
	    	 out.flush();
	    	 out.close();
	    	 Toast.makeText(getBaseContext(), "保存されました。", Toast.LENGTH_SHORT).show();
    	 } catch(Exception e) {
    		 Toast.makeText(getBaseContext(), "例外発生", Toast.LENGTH_SHORT).show();
    	 }
    }

   	 private boolean sdcardWriteReady(){
	   	 String state = Environment.getExternalStorageState();
	   	 return (Environment.MEDIA_MOUNTED.equals(state));
     }
}

