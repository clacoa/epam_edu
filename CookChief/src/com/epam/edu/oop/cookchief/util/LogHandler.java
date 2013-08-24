package com.epam.edu.oop.cookchief.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class LogHandler extends Handler{
	
	FileOutputStream logOutputStream;
		
	public LogHandler() throws SecurityException, IOException{
		setFormatter(new SimpleFormatter());
		try {
			logOutputStream = new FileOutputStream("CooChif.log");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void publish(LogRecord record) {
		try {
			String msg = getFormatter().format(record);
			logOutputStream.write((msg+"\r\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void flush() {
		try {
			logOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws SecurityException {
		try {
			logOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
