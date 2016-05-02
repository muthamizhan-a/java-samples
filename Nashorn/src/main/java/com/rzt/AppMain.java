package com.rzt;

import javax.script.ScriptException;

public class AppMain {

	public static void main( String[] args ) throws ScriptException
	{
		String script = "print('java script')";
		NashornEngine engineInstance = NashornEngine.getInstance();
		engineInstance.runScript(script);
		engineInstance.runScriptFile("resource/Script.js");

	}

}
