package com.baugdev.encryptor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ui extends Activity {
	protected static final int LAUNCH_ENCRYPTION = 0;
	/** Called when the activity is first created. */
	public static String inputString = "Text";
	public static String inputType = "InvocationType";
	public static String inputAction = "ActionType";

	public static String outputString = "Data";
	public static String outputKey = "Key";
	public static String outputAction = "ActionType";

	private int typeOfAction = 0;
	private String inputText = new String();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this, "Launched done", Toast.LENGTH_LONG);

		Intent intent = getIntent();
		inputText = intent.getStringExtra(inputString);
		// inputText = "6EC9DB1469498D0AD15F647DB9FEBEC6";
		int type = intent.getIntExtra(inputType, 0);

		typeOfAction = intent.getIntExtra(inputAction, 0);
		Log.d("encryptor", "type of action: " + typeOfAction);
		Log.d("encryptor", "input text: " + inputText);
		// typeOfAction = 1;
		if (type == 0)// launch UI Context
		{
			setContentView(R.layout.main);
			Spinner PasswordIDsSpinner = (Spinner) findViewById(R.id.PasswordIDSpinner);
			// get from db the passkeys stored

			ArrayList<String> passkeysArray = new ArrayList<String>();
			passkeysArray.add("mykey");

			ArrayAdapter<String> passkeyAdapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_spinner_item, passkeysArray);
			passkeyAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			PasswordIDsSpinner.setAdapter(passkeyAdapter);

			Spinner alogTypesSpinner = (Spinner) findViewById(R.id.AlgoTypeSpinner);
			ArrayList<String> algosArray = new ArrayList<String>();
			algosArray.add("SimpleCipher");
			algosArray.add("ComplexCipher");
			ArrayAdapter<String> algosAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, algosArray);
			algosAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			alogTypesSpinner.setAdapter(algosAdapter);

			Button startEncryption = (Button) findViewById(R.id.ContinueButton);
			startEncryption.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {

					// Intent intent = new
					// Intent("com.baugdev.encryptor.ui.ACTION");
					// intent.putExtra(outputAction, typeOfAction);
					// intent.putExtra(outputString, inputText);
					// intent.putExtra(outputKey, "mykey");
					// try{
					// startActivityForResult(intent, 2);
					// }
					// catch(Exception e)
					// {
					// int a = 0;
					// a = a + 4;
					// }
					//					
					// TODO Call Encryption/Decryption Intent based on choice
					Intent encryptionIntent = new Intent(ui.this,
							com.baugdev.encryptor.algo.aes.AESAlgorithm.class);
					encryptionIntent.putExtra(outputAction, typeOfAction);
					encryptionIntent.putExtra(outputString, inputText);
					Spinner PasswordIDsSpinner = (Spinner) findViewById(R.id.PasswordIDSpinner);

					encryptionIntent.putExtra(outputKey,
							(String) PasswordIDsSpinner.getSelectedItem());

					try {
						startActivityForResult(encryptionIntent,
								LAUNCH_ENCRYPTION);
					} catch (Exception e) {
						int a = 1;
						a = a + 21;
					}

				}

			});

		} else {
			// do the work silently and exit
		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == LAUNCH_ENCRYPTION) {
			setResult(resultCode, data);
			finish();
		}
	}
}