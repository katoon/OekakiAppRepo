package com.katoon.touch;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BluetoothControler extends Activity{
	private static final int REQUEST_ENABLE_BLUETOOTH = 101;
	String TAG = "BluetoolControler";

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    Log.d(TAG,"onCreate");

		//BluetoothAdapter取得
		BluetoothAdapter Bt = BluetoothAdapter.getDefaultAdapter();
	    Log.d(TAG,"BluetoothAdapter");
		//if(!Bt.equals(null)){
		if(null==Bt){
		    //Bluetooth非対応端末の場合の処理
		    Log.d(TAG,"Bluetoothがサポートれていません。");
		    Toast toast = Toast.makeText(this, "Bluetoothがサポートれていません。", Toast.LENGTH_SHORT);
		    toast.show();
		    finish();
		    return;
		}else{
		    //Bluetooth対応端末の場合の処理
		    Log.d(TAG,"Bluetoothがサポートされてます。");
		   // Toast toast = Toast.makeText(this, "Bluetoothがサポートされてます。", Toast.LENGTH_SHORT);
		   // toast.show();
		}
		
	    Log.d(TAG,"BluetoothAdapter2");

	    boolean btEnable = Bt.isEnabled();
	    if(btEnable == true){
	        //BluetoothがONだった場合の処理
		    Log.d(TAG,"BluetoothがONだった場合の処理");
	    }else{
	        //OFFだった場合、ONにすることを促すダイアログを表示する画面に遷移
	        Intent btOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	        startActivityForResult(btOn, REQUEST_ENABLE_BLUETOOTH);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int ResultCode, Intent date){
	        if(requestCode == REQUEST_ENABLE_BLUETOOTH){
	            if(ResultCode == Activity.RESULT_OK){
	                //BluetoothがONにされた場合の処理
	                Log.d(TAG,"BluetoothをONにしてもらえました。");
	            }else{
	                Log.d(TAG,"BluetoothがONにしてもらえませんでした。");
	                finish();
	            }
	        }
	}
}
