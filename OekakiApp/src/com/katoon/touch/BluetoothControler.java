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

		//BluetoothAdapter�擾
		BluetoothAdapter Bt = BluetoothAdapter.getDefaultAdapter();
	    Log.d(TAG,"BluetoothAdapter");
		//if(!Bt.equals(null)){
		if(null==Bt){
		    //Bluetooth��Ή��[���̏ꍇ�̏���
		    Log.d(TAG,"Bluetooth���T�|�[�g��Ă��܂���B");
		    Toast toast = Toast.makeText(this, "Bluetooth���T�|�[�g��Ă��܂���B", Toast.LENGTH_SHORT);
		    toast.show();
		    finish();
		    return;
		}else{
		    //Bluetooth�Ή��[���̏ꍇ�̏���
		    Log.d(TAG,"Bluetooth���T�|�[�g����Ă܂��B");
		   // Toast toast = Toast.makeText(this, "Bluetooth���T�|�[�g����Ă܂��B", Toast.LENGTH_SHORT);
		   // toast.show();
		}
		
	    Log.d(TAG,"BluetoothAdapter2");

	    boolean btEnable = Bt.isEnabled();
	    if(btEnable == true){
	        //Bluetooth��ON�������ꍇ�̏���
		    Log.d(TAG,"Bluetooth��ON�������ꍇ�̏���");
	    }else{
	        //OFF�������ꍇ�AON�ɂ��邱�Ƃ𑣂��_�C�A���O��\�������ʂɑJ��
	        Intent btOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	        startActivityForResult(btOn, REQUEST_ENABLE_BLUETOOTH);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int ResultCode, Intent date){
	        if(requestCode == REQUEST_ENABLE_BLUETOOTH){
	            if(ResultCode == Activity.RESULT_OK){
	                //Bluetooth��ON�ɂ��ꂽ�ꍇ�̏���
	                Log.d(TAG,"Bluetooth��ON�ɂ��Ă��炦�܂����B");
	            }else{
	                Log.d(TAG,"Bluetooth��ON�ɂ��Ă��炦�܂���ł����B");
	                finish();
	            }
	        }
	}
}
