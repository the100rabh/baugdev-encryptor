### Description of the Interfaces ###

**com.baugdev.encryptor.ui** is the activity to be invoked by others. The intent that needs to be passed to this needs to have the following extras.

  * Key = "Text" Type = String

  * Key = "InvocationType" Type = Int (UI Mode == 0, SilentMode == 1)

  * Key = "ActionType" Type=Int (Encrypt ==0, Decrypt==1)

The output or the encrypted text is recieved via the data in the OnActivityComplete

  * Key="OutText" Type= String

### Sample code to call Encrypotor ###

Following will encrypt the `inputText` String

```
Intent intent = new Intent("com.baugdev.encryptor.ui.ACTION");

intent.putExtra("ActionType", 0);

intent.putExtra("Text", inputText);

intent.putExtra("InvocationType", 0);

try{

     startActivityForResult(intent, 2);

}

catch(Exception e)

{
       //Exception Handling here
} `
```


Receiving and processing the data from the Encryptor

```
protected void onActivityResult(int requestCode, int resultCode,
                        Intent data) {
     String doneData = data.getStringExtra("OutText");
     //this doneData will have the processed data.
}
```