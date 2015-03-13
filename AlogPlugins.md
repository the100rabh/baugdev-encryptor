### How to make an algo plugin ###

All algo plugins for encryptor must have the following package name com.baugdev.encryptor.algo.

In the onCreate it should handle the intent with following information

  * Key="ActionType" Type=Int(Encrypt==0, Decrypt == 1 )

  * Key="Key" Type=String

  * Key="Data" Type=String


The output or the encrypted text is recieved via the data in the OnActivityComplete

  * Key="OutText" Type=String