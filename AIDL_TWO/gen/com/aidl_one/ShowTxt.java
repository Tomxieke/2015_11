/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\android\\2015_11\\AIDL_TWO\\src\\com\\aidl_one\\ShowTxt.aidl
 */
package com.aidl_one;
public interface ShowTxt extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.aidl_one.ShowTxt
{
private static final java.lang.String DESCRIPTOR = "com.aidl_one.ShowTxt";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.aidl_one.ShowTxt interface,
 * generating a proxy if needed.
 */
public static com.aidl_one.ShowTxt asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.aidl_one.ShowTxt))) {
return ((com.aidl_one.ShowTxt)iin);
}
return new com.aidl_one.ShowTxt.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_show:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.show(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.aidl_one.ShowTxt
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void show(java.lang.String string) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(string);
mRemote.transact(Stub.TRANSACTION_show, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_show = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void show(java.lang.String string) throws android.os.RemoteException;
}
