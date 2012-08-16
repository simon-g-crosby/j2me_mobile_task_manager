package com.substanceofcode.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public class ItemIdAllocator {
	
	private static ItemIdAllocator allocator = null;
	private static String rmsRecordStoreName = "rms_id_store"; 
	
	public static ItemIdAllocator getIdAllocator() {
		if (allocator == null) {
			allocator = new ItemIdAllocator();
		}
		return allocator;
	}
	
	
	private RecordStore recordStore = null;
	
	// only one record is stored, and this the record id for it
	private int nextItemIdRecordId;
	
	private ItemIdAllocator()
	{
		// open recordstore, initialize if necessary 
		try {
			recordStore = RecordStore.openRecordStore(rmsRecordStoreName, true);
		
			int numRecords = recordStore.getNumRecords(); 
			if (numRecords == 0) {
				long initialId = 1;
				byte[] data = longToBytes(initialId);
				nextItemIdRecordId = recordStore.addRecord(data, 0, data.length);
			}
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}		
	}
	
	public long getNextItemId()
	{
		long nextItemId = -1;		
		try {
			byte[] readData = recordStore.getRecord(nextItemIdRecordId);
			nextItemId = bytesToLong(readData) + 1;
			byte[] dataToWrite = longToBytes(nextItemId);
			recordStore.setRecord(nextItemIdRecordId, dataToWrite, 0, dataToWrite.length);			
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
		return nextItemId;
		
	}
	
	private byte[] longToBytes(long num)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
			dos.writeLong(nextItemIdRecordId);
			dos.close();
	        baos.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}                        
        
        byte[] bytes = baos.toByteArray();
        return bytes;        
	}
	
	private long bytesToLong(byte[] data)
	{
		DataInputStream dos = new DataInputStream(new ByteArrayInputStream(data));
		long num = -1;
		try {
			num = dos.readLong();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return num;
	}		
}
