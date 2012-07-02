package com.katoon.touch;

import java.util.ArrayList;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class penView extends View {
    private static String TAG = "penView";

	private Paint paint;
	private float posx = 0f;  //x���W
	private float posy = 0f;  //y���W
	private Path path = null;  

	private Bitmap bitmapCache = null; //View�̏�Ԃ�ۑ����邽�߂�Bitmap
	private boolean movinflag = false;
	
//	private Bitmap bitmapKeshigomIcon= null;
	private Paint Keshipaint;

	/**
	 * �`��pPaint�C���X�^���X�̏��ݒ�
	 * �����S���pPaint�C���X�^���X�̏��ݒ�
	 * @param context
	 */
	public penView(Context context) {
		super(context);
		paint = new Paint();  
		paint.setColor(Color.BLACK);  
		paint.setAntiAlias(true);  				//�Ȃ߂炩
		paint.setStyle(Paint.Style.STROKE);		//  
		paint.setStrokeWidth(10); 					//�X�g���[�N�̕�
		paint.setStrokeCap(Paint.Cap.ROUND);  		//�h���̃L���b�v
		paint.setStrokeJoin(Paint.Join.ROUND);  	//�h���̌���
		
		Keshipaint = new Paint();  
		Keshipaint.setColor(Color.WHITE);
		Keshipaint.setStrokeWidth(30);
		Keshipaint.setAntiAlias(true);  				//�Ȃ߂炩
		Keshipaint.setStyle(Paint.Style.STROKE);		//  
		Keshipaint.setStrokeCap(Paint.Cap.ROUND);  		//�h���̃L���b�v
		Keshipaint.setStrokeJoin(Paint.Join.ROUND);  	//�h���̌���
		
		//Bitmap bitmap0 = BitmapFactory.decodeResource(getResources(),R.drawable.rider);
		//bitmapKeshigomIcon = Bitmap.createBitmap(bitmap0);
	}
	
	private void clearLatestData(){
		posx = posy = 0f;
		path = null;
	}
	public void clearPaint(){
		bitmapCache = null;
		this.clearLatestData();
		invalidate();
	}
	
	private static int TOTAL_COLOR_NUMBER = 7;
	private int a = TOTAL_COLOR_NUMBER;
    private int getColor(){
		int color = Color.WHITE;
		a ++;
		
		switch(a%TOTAL_COLOR_NUMBER){
		case 1:	color = Color.BLUE;		break;
		case 2:	color = Color.GREEN;	break;
		case 3:	color = Color.RED;		break;
		case 4:	color = Color.YELLOW;	break;
		case 5:	color = Color.GRAY;		break;
		case 6: color = Color.BLACK;	break;
		default:color = Color.WHITE;	break;
		}
		return color;
    }
    
	public void update(){invalidate();}
	public int changeColor(){
		if(keshigomu){
			return paint.getColor();
		}
		int color = getColor();
		paint.setColor(color); 
		return color;
	}
	
	private int width = 2;
	public int changeStrokeWidth(){
		if(width < 3){	
			width++;
		}else{
			width = 1;
		}
		Float set = (float) 6;
		switch(width){
		case 1:	set = (float)6;	 break;
		case 2:	set = (float)10; break;
		case 3:	set = (float)15; break;
		default : break;
		}
		paint.setStrokeWidth(set);  
		return width;
	}

	 public void onDraw(Canvas canvas) {
		if((movinflag)&&(bitmapCache != null)){
			canvas.drawBitmap(bitmapCache, posx-bitmapCache.getWidth()/2, posy-bitmapCache.getHeight()/2,null);
			return;
		}
		if(bitmapCache != null) canvas.drawBitmap(bitmapCache, 0, 0, null);	//�ߋ����̕`��
		if(keshigomu){
			if(posx != 0f) canvas.drawPoint(posx, posy, Keshipaint);
			if(path != null) canvas.drawPath(path, Keshipaint); 
			return;
		}
		if(posx != 0f) canvas.drawPoint(posx, posy, paint);				//�_�̕`��i����j
		if(path != null) canvas.drawPath(path, paint); 		 			//���̕`��i����j	
		
		/*
		if(keshigomu){
			canvas.drawBitmap(bitmapKeshigomIcon, posx, posy, null);
		}
		*/
	}  

	private boolean keshigomu = false;
	public boolean chgKeshigomuMode(){
		return keshigomu = !keshigomu;
	}
	
	/**
	 * �摜�ړ����[�h�֘A
	 * @return
	 */
	public boolean getMovin(){return movinflag;}
	public boolean changeMovin(){
		movinflag = movinflag ? false : true;		//���샂�[�h�ؑ�
		if(!movinflag){		//�`�惂�[�h
			this.clearLatestData();
			invalidate();
		}
		//return movinflag = movinflag ? false : true;		//���샂�[�h�ؑ�
		return movinflag;
	}

	public boolean onTouchEvent(MotionEvent e){
		if(movinflag){
			onTouchEventMoving(e);
		}else{
			onTouchEventDrowing(e);
		}
		return true;
	}

	private void onTouchEventMoving(MotionEvent e){
		posx = e.getX();
        posy = e.getY();
        invalidate();
	}
	
	private void onTouchEventDrowing(MotionEvent e){
		switch(e.getAction()){  
		 case MotionEvent.ACTION_DOWN: //�ŏ��̃|�C���g  
			 path = new Path();  
			 posx = e.getX();  
			 posy = e.getY();  
			 path.moveTo(e.getX(), e.getY());  
			 break;  
		 case MotionEvent.ACTION_MOVE: //�r���̃|�C���g  
			 posx += (e.getX()-posx)/1.4;  
			 posy += (e.getY()-posy)/1.4;  
			 path.lineTo(posx, posy);  
			 invalidate();
			 break;  
		 case MotionEvent.ACTION_UP: //�Ō�̃|�C���g  
			 path.lineTo(e.getX(), e.getY());  
			 setDrawingCacheEnabled(true);
			 bitmapCache = Bitmap.createBitmap(getDrawingCache());	//paint�f�[�^��bitmap�ŕۑ�
			 setDrawingCacheEnabled(false);
			 invalidate();
			 break;  
		 default:  
			 break;  
		}  
	}
}
